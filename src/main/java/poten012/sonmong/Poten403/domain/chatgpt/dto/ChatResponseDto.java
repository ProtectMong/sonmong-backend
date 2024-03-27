package poten012.sonmong.Poten403.domain.chatgpt.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import poten012.sonmong.Poten403.domain.chatgpt.domain.Message;

import java.util.List;

@Getter
@Setter
public class ChatResponseDto {

    private List<Choice> choices;

    public ChatResponseDto(){

    }

    public ChatResponseDto(List<Choice> choices) {
        this.choices = choices;
    }

    @Getter
    @Setter
    public static class Choice {

        private int index;
        private Message message;

        public Choice(int index, Message message) {
            this.index = index;
            this.message = message;
        }
    }
}
