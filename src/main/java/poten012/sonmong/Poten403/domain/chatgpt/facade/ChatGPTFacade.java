package poten012.sonmong.Poten403.domain.chatgpt.facade;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import poten012.sonmong.Poten403.domain.chatgpt.dto.CompletionRequestDto;
import poten012.sonmong.Poten403.domain.chatgpt.service.ChatGPTService;

import java.util.Map;

@Slf4j
@Component
@NoArgsConstructor
public class ChatGPTFacade {

    @Autowired
    private ChatGPTService chatGPTService;

    public Map<String, Object> sendPrompt(CompletionRequestDto completionRequestDto) {
        return chatGPTService.prompt(completionRequestDto);
    }
}
