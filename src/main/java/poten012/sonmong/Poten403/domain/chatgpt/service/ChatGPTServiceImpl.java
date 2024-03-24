package poten012.sonmong.Poten403.domain.chatgpt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import poten012.sonmong.Poten403.config.chatgpt.ChatGPTConfig;
import poten012.sonmong.Poten403.domain.chatgpt.dto.CompletionRequestDto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@NoArgsConstructor
public class ChatGPTServiceImpl implements ChatGPTService{
    @Autowired
    private ChatGPTConfig chatGPTConfig;


    /**
     * ChatGTP 프롬프트 검색
     *
     * @param completionRequestDto
     * @return
     */
    @Override
    public Map<String, Object> prompt(CompletionRequestDto completionRequestDto) {
        log.debug("[+] 프롬프트를 수행합니다.");

        Map<String, Object> result = new HashMap<>();

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = chatGPTConfig.httpHeaders();

        String requestBody = "";
        ObjectMapper om = new ObjectMapper();

        // [STEP3] properties의 model을 가져와서 객체에 추가합니다.
        completionRequestDto = CompletionRequestDto.builder()
                .model(chatGPTConfig.getModel())
                .prompt(completionRequestDto.getPrompt())
                .temperature(0.8f)
                .build();

        try {
            // [STEP4] Object -> String 직렬화를 구성합니다.
            requestBody = om.writeValueAsString(completionRequestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // [STEP5] 통신을 위한 RestTemplate을 구성합니다.
        HttpEntity requestEntity = new HttpEntity<>(completionRequestDto, headers);
        ResponseEntity response = chatGPTConfig.restTemplate()
                .exchange(
                        "https://api.openai.com/v1/completions",
                        HttpMethod.POST,
                        requestEntity,
                        String.class);
        try {
            // [STEP6] String -> HashMap 역직렬화를 구성합니다.
            result = om.readValue((String) response.getBody(), new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
