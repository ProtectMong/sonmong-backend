package poten012.sonmong.Poten403.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import poten012.sonmong.Poten403.common.response.Api;
import poten012.sonmong.Poten403.common.response.ResponseCode;

@Slf4j
public class ExceptionResponseBuilder {

    private final ResponseCode responseCode;
    private final String message;

    // 모든 Exception의 형태를 통일 시키기 위해 만듬
    public ExceptionResponseBuilder(ResponseCode responseCode) {
        this.responseCode = responseCode;
        this.message = responseCode.getMessage();
    }

    public ExceptionResponseBuilder(ResponseCode responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public ResponseEntity<Api> build() {
        log.error("Handling Exception: " + message);
        return ResponseEntity.ok(Api.error(responseCode, message));
    }
}
