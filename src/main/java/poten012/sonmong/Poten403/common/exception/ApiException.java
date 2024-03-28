package poten012.sonmong.Poten403.common.exception;

import lombok.Getter;
import poten012.sonmong.Poten403.common.response.ResponseCode;

@Getter
public class ApiException extends RuntimeException {

    private final ResponseCode responseCode;

    // 직접 발생시키는 에러(유저가 없다 등)를 위한 Exception. ExceptionHandler를 통해 핸들링
    public ApiException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }
}
