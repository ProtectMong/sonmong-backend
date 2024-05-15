package poten012.sonmong.Poten403.domain.aiconsulting.facade;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import poten012.sonmong.Poten403.domain.aiconsulting.dto.request.CurationRequestDto;
import poten012.sonmong.Poten403.domain.aiconsulting.dto.response.AiConsultingMainResponseDto;
import poten012.sonmong.Poten403.domain.aiconsulting.dto.response.CurationDetailResponseDto;
import poten012.sonmong.Poten403.domain.aiconsulting.dto.response.CurationResponseDto;
import poten012.sonmong.Poten403.domain.aiconsulting.service.AiConsultingService;

@Slf4j
@Component
@RequiredArgsConstructor
public class AiConsultingFacade {

    private final AiConsultingService aiConsultingService;

    public AiConsultingMainResponseDto getMain(Long userId) {
        val curationList = aiConsultingService.getMain(userId);
        return curationList;
    }

    public CurationResponseDto sendCuration(Long userId, CurationRequestDto curationRequestDto) {
        val curationResult = aiConsultingService.sendCuration(userId, curationRequestDto);
        return curationResult;
    }

    public CurationDetailResponseDto getCurationDetail(Long curationId) {
        val curationDetailResult = aiConsultingService.getCurationDetail(curationId);
        return curationDetailResult;
    }
}
