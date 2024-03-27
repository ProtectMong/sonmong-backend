package poten012.sonmong.Poten403.domain.chatgpt.dto;

import lombok.Getter;
import lombok.Setter;
import poten012.sonmong.Poten403.domain.chatgpt.domain.Message;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ChatRequestDto {

    private String model;
    private List<Message> messages;
    private int n;
    private double temperature;

    public ChatRequestDto(String model, String prompt) {
        this.model = model;
        this.n = 1;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }

}
