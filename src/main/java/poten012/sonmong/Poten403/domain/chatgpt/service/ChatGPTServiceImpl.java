package poten012.sonmong.Poten403.domain.chatgpt.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import poten012.sonmong.Poten403.config.chatgpt.ChatGPTConfig;
import poten012.sonmong.Poten403.domain.chatgpt.dto.CompletionRequestDto;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@NoArgsConstructor
public class ChatGPTServiceImpl implements ChatGPTService{
    @Autowired
    private ChatGPTConfig chatGPTConfig;

    @Value("${openai.model}")
    private String model;

    /**
     * 사용 가능한 모델 리스트를 조회하는 비즈니스 로직
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> modelList() {
        log.debug("[+] 모델 리스트를 조회합니다.");
        List<Map<String, Object>> resultList = null;

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = chatGPTConfig.httpHeaders();

        // [STEP2] 통신을 위한 RestTemplate을 구성합니다.
        ResponseEntity response = chatGPTConfig.restTemplate()
                .exchange(
                        "https://api.openai.com/v1/models",
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        String.class);

        try {
            // [STEP3] Jackson을 기반으로 응답값을 가져옵니다.
            ObjectMapper om = new ObjectMapper();
            Map<String, Object> data = om.readValue((String) response.getBody(), new TypeReference<Map<String, Object>>() {});

            // [STEP4] 응답 값을 결과값에 넣고 출력을 해봅니다.
            resultList = (List<Map<String, Object>>) data.get("data");
            for (Map<String, Object> object : resultList) {
                log.debug("ID: " + object.get("id"));
                log.debug("Object: " + object.get("object"));
                log.debug("Created: " + object.get("created"));
                log.debug("Owned By: " + object.get("owned_by"));
            }
        } catch (JsonMappingException e) {
            log.debug("JsonMappingException :: " + e.getMessage());
        } catch (JsonProcessingException e) {
            log.debug("RuntimeException :: " + e.getMessage());
        }
        return resultList;
    }

    /**
     * 모델이 유효한지 확인하는 비즈니스 로직
     *
     * @param modelName
     * @return
     */
    @Override
    public Map<String, Object> isValidModel(String modelName) {
        log.debug("[+] 모델이 유효한지 조회합니다. 모델 : " + modelName);
        Map<String, Object> result;

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = chatGPTConfig.httpHeaders();

        // [STEP2] 통신을 위한 RestTemplate을 구성합니다.
        ResponseEntity response = chatGPTConfig.restTemplate()
                .exchange(
                        "https://api.openai.com/v1/models/" + modelName,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        String.class);
        try {
            // [STEP3] Jackson을 기반으로 응답값을 가져옵니다.
            ObjectMapper om = new ObjectMapper();
            result = om.readValue((String) response.getBody(), new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

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
                .model(model)
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
