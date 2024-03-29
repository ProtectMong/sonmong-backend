package poten012.sonmong.Poten403.domain.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import poten012.sonmong.Poten403.common.exception.ApiException;
import poten012.sonmong.Poten403.domain.security.code.SecurityErrorCode;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtProvider {

    private final JwtParser jwtParser;
    private final SecretKey secretKey;
    private final Long expirationPeriodMilliseconds;

    public JwtProvider(
            @Value("${jwt.secret-key}") String secret,
            @Value("${jwt.expiration-period-milliseconds}") Long expirationPeriodMilliseconds
    ) {
        this.expirationPeriodMilliseconds = expirationPeriodMilliseconds;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secret));
        this.jwtParser = Jwts.parser().verifyWith(this.secretKey).build();
    }

    public String generate(String phoneNumber) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationPeriodMilliseconds);
        return Jwts.builder()
                .subject(phoneNumber)
                .expiration(expiryDate)
                .issuedAt(now)
                .signWith(this.secretKey)
                .compact();
    }

    public String extractJwsFromRequest(HttpServletRequest request) {
        final String AUTHORIZATION_HEADER_KEY = "Authorization";
        final String authorization = request.getHeader(AUTHORIZATION_HEADER_KEY);

        if (authorization == null || authorization.isEmpty()) {
            throw new ApiException(SecurityErrorCode.EMPTY_AUTHORIZATION_HEADER);
        }
        String AUTHENTICATION_TYPE = "Bearer";
        if (!authorization.startsWith(AUTHENTICATION_TYPE)) {
            throw new ApiException(SecurityErrorCode.INVALID_AUTHORIZATION_TYPE);
        }
        try {
            return authorization.substring(AUTHENTICATION_TYPE.length() + 1);
        } catch (IndexOutOfBoundsException e) {
            throw new ApiException(SecurityErrorCode.EMPTY_TOKEN);
        }
    }

    public String getSubjectFromJws(String jws) {
        return getJwsClaims(jws)
                .getPayload()
                .getSubject();
    }

    private Jws<Claims> getJwsClaims(String jws) {
        try {
            return jwtParser.parseSignedClaims(jws);
        } catch (ExpiredJwtException e) {
            throw new ApiException(SecurityErrorCode.EXPIRED_TOKEN);
        } catch (JwtException | IllegalArgumentException e) {
            throw new ApiException(SecurityErrorCode.INVALID_TOKEN);
        }
    }
}