package poten012.sonmong.Poten403.common.exception;

import org.springframework.http.HttpStatus;
import poten012.sonmong.Poten403.common.response.ResponseCode;

// 스프링에서 발생시키는 예외를 직접 커스텀하기 위해 만든 ErrorCode enum
public enum GlobalErrorCode implements ResponseCode {
    METHOD_ARGUMENT_NOT_VALID(HttpStatus.BAD_REQUEST, "error.global.4000", "잘못된 매개변수입니다."),
    NO_RESOURCE_FOUND(HttpStatus.NOT_FOUND, "error.global.4001", "존재하지 않는 리소스입니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "error.global.5000", "서버 에러입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    GlobalErrorCode(HttpStatus status, String code, String message) {
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
