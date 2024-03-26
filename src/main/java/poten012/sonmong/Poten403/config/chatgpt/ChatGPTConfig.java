package poten012.sonmong.Poten403.config.chatgpt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Getter
@Configuration
public class ChatGPTConfig {

    @Value("${openai.secret-key}")
    private String secretKey;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.max-token}")
    private Integer maxToken;

    @Value("${openai.temperature}")
    private float temperature;

    @Value("${openai.top-p}")
    private float topP;

    @Value("${openai.media-type}")
    private String mediaType;

    @Value("${openai.url}")
    private String url;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + secretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
    @Bean
    @Qualifier("openaiRestTemplate")
    public RestTemplate openaiRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + secretKey);
            return execution.execute(request, body);
        });
        return restTemplate;
    }
}
