package poten012.sonmong.Poten403.domain.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import poten012.sonmong.Poten403.domain.security.domain.CustomUserDetails;
import poten012.sonmong.Poten403.domain.security.service.CustomUserDetailsService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService userDetailsService;
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        if (isJwtAuthenticationRequiredRequest(request.getMethod(), request.getRequestURI())) {
            String jws = jwtProvider.extractJwsFromRequest(request);
            String phoneNumber = jwtProvider.getSubjectFromJws(jws);
            CustomUserDetails userDetails = userDetailsService.loadUserByUsername(phoneNumber);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, userDetails.getPassword(),
                    userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private boolean isJwtAuthenticationRequiredRequest(String requestMethod, String requestURI) {
        final String POST_METHOD = "POST";

        final String AUTH_URI = "/api/v1/auth";

        return !(requestMethod.equals(POST_METHOD) && requestURI.startsWith(AUTH_URI));
    }
}
