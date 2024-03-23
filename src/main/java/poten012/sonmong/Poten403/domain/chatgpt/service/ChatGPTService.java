package poten012.sonmong.Poten403.domain.chatgpt.service;

import poten012.sonmong.Poten403.domain.chatgpt.dto.CompletionRequestDto;

import java.util.List;
import java.util.Map;

public interface ChatGPTService {

    List<Map<String, Object>> modelList();

    Map<String, Object> prompt(CompletionRequestDto completionRequestDto);

    Map<String, Object> isValidModel(String modelName);
}
