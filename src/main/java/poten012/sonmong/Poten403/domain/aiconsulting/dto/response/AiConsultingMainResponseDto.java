package poten012.sonmong.Poten403.domain.aiconsulting.dto.response;

import lombok.Builder;
import poten012.sonmong.Poten403.domain.aiconsulting.domain.Curation;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@Builder
public record AiConsultingMainResponseDto(
        List<AiConsultingMainResponseVo> curationList
) {
    public static AiConsultingMainResponseDto of(List<Curation> curationList) {
        List<AiConsultingMainResponseVo> responseVoList = IntStream.range(0, curationList.size())
                .mapToObj(i -> AiConsultingMainResponseVo.of(curationList.size() - i, curationList.get(i)))
                .toList();
        return AiConsultingMainResponseDto.builder()
                .curationList(responseVoList)
                .build();
    }
}

@Builder
record AiConsultingMainResponseVo(
        Long curationId,
        int sequence,  // 몇 번째 curation인지를 나타내는 변수
        int levelOfPain,
        String createdAt
) {
    public static AiConsultingMainResponseVo of (int sequence, Curation curation) {
        return AiConsultingMainResponseVo.builder()
                .curationId(curation.getId())
                .sequence(sequence)
                .levelOfPain(curation.getLevelOfPain())
                .createdAt(curation.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }
}