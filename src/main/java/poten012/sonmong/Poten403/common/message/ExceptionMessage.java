package poten012.sonmong.Poten403.common.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {
    /** userException **/
    NOT_FOUND_USER("해당하는 유저를 찾을 수 없습니다 : "),

    /** curationException **/
    NOT_FOUND_CURATION("해당하는 큐레이션을 찾을 수 없습니다 : "),
    NOT_CURATION_OF_USER("유저의 큐레이션이 아닙니다."),

    /** curationAnswerException **/
    NOT_FOUND_CURATION_ANSWER("해당하는 큐레이션 답변을 찾을 수 없습니다 : ");

    private final String message;
}
