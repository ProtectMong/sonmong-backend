package poten012.sonmong.Poten403.domain.chatgpt.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompletionRequestDto {

        private String model;

        private String prompt;

        private float temperature;

        @Builder
        CompletionRequestDto(String model, String prompt, float temperature) {
                this.model = model;
                this.prompt = prompt;
                this.temperature = temperature;
        }

}