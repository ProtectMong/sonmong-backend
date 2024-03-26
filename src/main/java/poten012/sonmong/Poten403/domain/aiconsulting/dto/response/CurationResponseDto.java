package poten012.sonmong.Poten403.domain.aiconsulting.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record CurationResponseDto(
        List<CurationResponseVo> answerList
) {
    public static CurationResponseDto of (List<CurationResponseVo> answerList) {
        return CurationResponseDto.builder()
                .answerList(answerList)
                .build();
    }
}

