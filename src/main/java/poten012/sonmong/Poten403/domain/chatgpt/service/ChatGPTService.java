package poten012.sonmong.Poten403.domain.chatgpt.service;

import poten012.sonmong.Poten403.domain.chatgpt.dto.CompletionRequestDto;

import java.util.Map;

public interface ChatGPTService {

    Map<String, Object> prompt(CompletionRequestDto completionRequestDto);

}
