package poten012.sonmong.Poten403.common;

import lombok.AccessLevel;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder(access = AccessLevel.PRIVATE)
public record ApiResponse(HttpStatus httpStatus, String message, Object data) {
    public static ApiResponse success(String message, Object data){
        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(message)
                .data(data)
                .build();
    }

    public static ApiResponse success(String message){
        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(message)
                .build();
    }

    public static ApiResponse fail(String message){
        return ApiResponse.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .message(message)
                .build();
    }

}
