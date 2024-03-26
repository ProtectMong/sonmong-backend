package poten012.sonmong.Poten403.common.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {
    /** userException **/
    NOT_FOUND_USER("해당하는 유저를 찾을 수 없습니다 : ");

    private final String message;
}
