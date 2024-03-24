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

    @PostMapping("/prompt")
    public ResponseEntity<ApiResponse> sendPrompt(@RequestBody CompletionRequestDto completionRequestDto) {
        val result = chatGPTFacade.sendPrompt(completionRequestDto);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_POST_PROMPT.getMessage(), result));
    }
}
