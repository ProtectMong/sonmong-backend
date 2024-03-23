package poten012.sonmong.Poten403.domain.chatgpt.presentation;

import lombok.NoArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poten012.sonmong.Poten403.common.ApiResponse;
import poten012.sonmong.Poten403.common.message.ResponseMessage;
import poten012.sonmong.Poten403.domain.chatgpt.dto.CompletionRequestDto;
import poten012.sonmong.Poten403.domain.chatgpt.facade.ChatGPTFacade;

@RestController
@NoArgsConstructor
@RequestMapping("api/v1/chatGPT")
public class controller {

    @Autowired
    private ChatGPTFacade chatGPTFacade;

    /**
     * [API] ChatGPT 모델 리스트 조회
     */
    @GetMapping("/modelList")
    public ResponseEntity<ApiResponse> selectModelList() {
        val result = chatGPTFacade.selectModelList();
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_GET_MODEL_LIST.getMessage(), result));
    }

    /**
     * [API] ChatGPT 유효한 모델인지 조회합니다.
     *
     * @param modelName
     * @return
     */
    @GetMapping("/model")
    public ResponseEntity<ApiResponse> isValidModel(@RequestParam(name = "modelName") String modelName) {
        val result = chatGPTFacade.isValidModel(modelName);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_VALID_MODEL.getMessage(), result));
    }

    /**
     * [API] ChatGPT 모델 리스트를 조회합니다.
     */
    @PostMapping("/prompt")
    public ResponseEntity<ApiResponse> selectPrompt(@RequestBody CompletionRequestDto completionRequestDto) {
        val result = chatGPTFacade.selectPrompt(completionRequestDto);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_POST_PROMPT.getMessage(), result));
    }
}
