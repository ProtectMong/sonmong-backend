package poten012.sonmong.Poten403.common.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {

    /** chatGPT **/
    SUCCESS_GET_MODEL_LIST("모델 리스트 조회를 성공했습니다."),
    SUCCESS_VALID_MODEL("모델 검증을 성공했습니다."),
    SUCCESS_POST_PROMPT("프롬프트 전달을 성공했습니다."),

    /** aiConsulting **/
    SUCCESS_GET_MAIN("ai consulting 메인 조회를 성공했습니다."),
    SUCCES_SEND_CURATION("큐레이션 전달을 성공했습니다.");


    private final String message;
}
