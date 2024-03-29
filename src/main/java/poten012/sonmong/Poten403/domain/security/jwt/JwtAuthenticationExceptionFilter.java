package poten012.sonmong.Poten403.domain.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import poten012.sonmong.Poten403.common.exception.ApiException;
import poten012.sonmong.Poten403.common.response.ResponseCode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class JwtAuthenticationExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ApiException e) {
            setErrorResponse(response, e.getResponseCode());
        } catch (IOException e) {
            System.out.println("GHGKS");
        }
    }

    private void setErrorResponse(HttpServletResponse response, ResponseCode responseCode) {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ErrorResponse errorResponse = new ErrorResponse(responseCode);
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        } catch (IOException ignore) {
        }
    }

    @Getter
    private static class ErrorResponse {
        private final Integer status;
        private final String code;
        private final String message;

        ErrorResponse(ResponseCode responseCode) {
            this.status = responseCode.getStatus().value();
            this.code = responseCode.getCode();
            this.message = responseCode.getMessage();
        }
    }

}
