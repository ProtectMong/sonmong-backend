package poten012.sonmong.Poten403.domain.aiconsulting.presentation;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poten012.sonmong.Poten403.common.ApiResponse;
import poten012.sonmong.Poten403.common.message.ResponseMessage;
import poten012.sonmong.Poten403.domain.aiconsulting.dto.request.CurationRequestDto;
import poten012.sonmong.Poten403.domain.aiconsulting.facade.AiConsultingFacade;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ai-consulting")
public class AiConsultingController {

    private final AiConsultingFacade aiConsultingFacade;

    @GetMapping("") // AiConsulting main화면 조회
    public ResponseEntity<ApiResponse> getMain(@RequestParam Long userId) {
        val result = aiConsultingFacade.getMain(userId);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_GET_MAIN.getMessage(), result));
    }

    @PostMapping("/curation")
    public ResponseEntity<ApiResponse> sendCuration(@RequestBody CurationRequestDto curationRequestDto) {
        val result = aiConsultingFacade.sendCuration(curationRequestDto);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCES_SEND_CURATION.getMessage(), result));
    }
}
