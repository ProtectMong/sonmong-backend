package poten012.sonmong.Poten403.domain.security.code;

import org.springframework.http.HttpStatus;
import poten012.sonmong.Poten403.common.response.ResponseCode;

public enum SecurityErrorCode implements ResponseCode {
    EMPTY_AUTHORIZATION_HEADER(HttpStatus.BAD_REQUEST, "error.security.4002", "Authorization 헤더가 존재하지 않습니다."),
    INVALID_AUTHORIZATION_TYPE(HttpStatus.BAD_REQUEST, "error.security.4003", "유효하지 않은 Authorization 헤더 타입입니다."),
    EMPTY_TOKEN(HttpStatus.UNAUTHORIZED, "error.security.4004", "액세스 토큰이 없습니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "error.security.4005", "만료된 액세스 토큰입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "error.security.4006", "유효하지 않은 토큰입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    SecurityErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
