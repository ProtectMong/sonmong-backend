package poten012.sonmong.Poten403.domain.auth.code;

import org.springframework.http.HttpStatus;
import poten012.sonmong.Poten403.common.response.ResponseCode;

public enum AuthErrorCode implements ResponseCode {

    ALREADY_REGISTERED(HttpStatus.FORBIDDEN, "error.auth.4000", "이미 가입한 유저입니다."),
    NOT_REGISTERED(HttpStatus.UNAUTHORIZED, "error.auth.4001", "회원 가입되지 않은 유저입니다."),

    ;
    private final HttpStatus status;
    private final String code;
    private final String message;

    AuthErrorCode(HttpStatus status, String code, String message) {
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
