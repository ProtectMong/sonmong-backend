package poten012.sonmong.Poten403.domain.auth.code;

import org.springframework.http.HttpStatus;
import poten012.sonmong.Poten403.common.response.ResponseCode;

public enum AuthSuccessCode implements ResponseCode {

    SIGNUP(HttpStatus.CREATED, "", "회원가입에 성공했습니다."),
    SIGNIN(HttpStatus.OK, "", "로그인에 성공했습니다.")
    ;

    private final HttpStatus status;

    private final String code;

    private final String message;

    AuthSuccessCode(HttpStatus status, String code, String message) {
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
