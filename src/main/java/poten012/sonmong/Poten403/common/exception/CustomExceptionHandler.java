package poten012.sonmong.Poten403.common.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import poten012.sonmong.Poten403.common.response.Api;
import poten012.sonmong.Poten403.common.response.ResponseCode;

@RestControllerAdvice
@Order(value = Integer.MIN_VALUE)
public class CustomExceptionHandler {

    // 서버에서 직접 발생시키는 Exception(ApiException)을 핸들링 하기 위한 코드
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Api> apiException(ApiException apiException) {
        return new ExceptionResponseBuilder(apiException.getResponseCode())
                .build();
    }

    // DTO 등의 @Valid를 통해 매개변수가 잘못 들어온 경우, 발생하는 Exception을 커스텀 핸들링
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Api> customMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ResponseCode responseCode = GlobalErrorCode.METHOD_ARGUMENT_NOT_VALID;
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ExceptionResponseBuilder(responseCode, message)
                .build();
    }

    // 잘못된 URI로 호출의 경우, 발생하는 Exception을 커스텀 핸들링
    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<Api> customNoResourceFoundException(NoResourceFoundException e) {
        ResponseCode responseCode = GlobalErrorCode.NO_RESOURCE_FOUND;
        String resourcePath = e.getResourcePath();
        return new ExceptionResponseBuilder(responseCode, resourcePath + " : " + "해당 자원이 존재하지 않습니다.")
                .build();
    }
}
