package poten012.sonmong.Poten403.domain.user.code;

import org.springframework.http.HttpStatus;
import poten012.sonmong.Poten403.common.response.ResponseCode;

public enum UserErrorCode implements ResponseCode {

    NOT_EXISTS(HttpStatus.NOT_FOUND, "error.user.4001", "해당 유저가 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    UserErrorCode(HttpStatus status, String code, String message) {
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
