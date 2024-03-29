package poten012.sonmong.Poten403.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record Api(

        Integer status,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        String code,

        String message,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        Object data
) {

    public static Api ok(ResponseCode responseCode) {
        return Api.builder()
                .status(responseCode.getStatus().value())
                .code(responseCode.getCode())
                .message(responseCode.getMessage())
                .build();
    }

    public static Api ok(ResponseCode responseCode, Object data) {
        return Api.builder()
                .status(responseCode.getStatus().value())
                .code(responseCode.getCode())
                .message(responseCode.getMessage())
                .data(data)
                .build();
    }

    public static Api error(ResponseCode responseCode, String message) {
        return Api.builder()
                .status(responseCode.getStatus().value())
                .code(responseCode.getCode())
                .message(message)
                .build();
    }
}
