package poten012.sonmong.Poten403.domain.aiconsulting.dto.response;

import lombok.Builder;
import poten012.sonmong.Poten403.domain.aiconsulting.domain.Curation;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record AiConsultingMainResponseDto(
        List<AiConsultingMainResponseVo> curationList
) {
    public static AiConsultingMainResponseDto of (List<Curation> curationList) {
        return AiConsultingMainResponseDto.builder()
                .curationList(curationList.stream().map(AiConsultingMainResponseVo::of).toList())
                .build();
    }
}

@Builder
record AiConsultingMainResponseVo(
    int levelOfPain,
    LocalDateTime createdAt
) {
    public static AiConsultingMainResponseVo of (Curation curation) {
        return AiConsultingMainResponseVo.builder()
                .levelOfPain(curation.getLevelOfPain())
                .createdAt(curation.getCreatedAt())
                .build();
    }
}