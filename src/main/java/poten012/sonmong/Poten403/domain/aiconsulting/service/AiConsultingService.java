package poten012.sonmong.Poten403.domain.aiconsulting.service;

import poten012.sonmong.Poten403.domain.aiconsulting.dto.request.CurationRequestDto;
import poten012.sonmong.Poten403.domain.aiconsulting.dto.response.AiConsultingMainResponseDto;
import poten012.sonmong.Poten403.domain.aiconsulting.dto.response.CurationDetailResponseDto;
import poten012.sonmong.Poten403.domain.aiconsulting.dto.response.CurationResponseDto;

public interface AiConsultingService {
    AiConsultingMainResponseDto getMain(Long userId);
    CurationResponseDto sendCuration(Long userId, CurationRequestDto curationRequestDto);
    CurationDetailResponseDto getCurationDetail(Long userId, Long curationId);
}
