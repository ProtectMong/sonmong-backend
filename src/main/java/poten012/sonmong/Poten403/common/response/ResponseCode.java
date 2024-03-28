package poten012.sonmong.Poten403.common.response;

import org.springframework.http.HttpStatus;

public interface ResponseCode {
    HttpStatus getStatus();

    String getCode();

    String getMessage();
}
