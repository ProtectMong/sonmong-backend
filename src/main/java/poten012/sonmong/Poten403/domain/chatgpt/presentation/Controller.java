package poten012.sonmong.Poten403.domain.chatgpt.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import poten012.sonmong.Poten403.config.chatgpt.ChatGPTConfig;
import poten012.sonmong.Poten403.domain.chatgpt.dto.ChatRequestDto;
import poten012.sonmong.Poten403.domain.chatgpt.dto.ChatResponseDto;

@RestController
@RequestMapping("api/v1/chatGPT")
public class Controller {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ChatGPTConfig chatGPTConfig;

    @GetMapping("/chat")
    public String chat(@RequestParam String prompt) {
        // create a request
        ChatRequestDto request = new ChatRequestDto(chatGPTConfig.getModel(), prompt);

        // call the API
        ChatResponseDto response = restTemplate.postForObject(chatGPTConfig.getUrl(), request, ChatResponseDto.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }

        // return the first response
        return response.getChoices().get(0).getMessage().getContent();
    }
}