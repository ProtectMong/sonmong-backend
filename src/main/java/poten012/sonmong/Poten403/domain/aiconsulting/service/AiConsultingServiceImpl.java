package poten012.sonmong.Poten403.domain.aiconsulting.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poten012.sonmong.Poten403.common.message.ExceptionMessage;
import poten012.sonmong.Poten403.domain.aiconsulting.domain.Curation;
import poten012.sonmong.Poten403.domain.aiconsulting.dto.request.CurationRequestDto;
import poten012.sonmong.Poten403.domain.aiconsulting.dto.response.AiConsultingMainResponseDto;
import poten012.sonmong.Poten403.domain.aiconsulting.dto.response.CurationResponseDto;
import poten012.sonmong.Poten403.domain.aiconsulting.dto.response.CurationResponseVo;
import poten012.sonmong.Poten403.domain.aiconsulting.repository.AiConsultingRepository;
import poten012.sonmong.Poten403.domain.chatgpt.service.ChatGPTService;
import poten012.sonmong.Poten403.domain.user.domain.User;
import poten012.sonmong.Poten403.domain.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiConsultingServiceImpl implements AiConsultingService{

    private final AiConsultingRepository aiConsultingRepository;
    private final UserRepository userRepository;
    private final ChatGPTService chatGPTService;

    @Override
    public AiConsultingMainResponseDto getMain(Long userId) {
        User user = findUser(userId);
        List<Curation> curationList = user.getCurations();
        return AiConsultingMainResponseDto.of(curationList);
    }

    @Override
    @Transactional
    public CurationResponseDto sendCuration(CurationRequestDto curationRequestDto) {
        User user = findUser(curationRequestDto.userId());
        String prompt = createPrompt(curationRequestDto);
        String chatGPTAnswer = chatGPTService.sendMessage(prompt);

        List<CurationResponseVo> answerList = new ArrayList<>();

        String[] sentences = chatGPTAnswer.split("\n");

        for (int i = 0 ; i < sentences.length ; i++) {
            if (i%3==1 ) {
                String title = null;
                String content = null;
                title = sentences[i-1];
                content = getContentFromSentence(sentences[i]);
                CurationResponseVo curationResponseVo = CurationResponseVo.of(title, content);
                answerList.add(curationResponseVo);
            }
        }

        val curation = Curation.builder()
                .user(user)
                .birthday(curationRequestDto.birthday())
                .gender(curationRequestDto.gender())
                .jobOrHobby(curationRequestDto.jobOrHobby())
                .whereDoesItHurt(curationRequestDto.whereDoesItHurt())
                .position(curationRequestDto.position())
                .levelOfPain(curationRequestDto.levelOfPain())
                .howLong(curationRequestDto.howLong())
                .howSick(curationRequestDto.howSick())
                .whatActivities(curationRequestDto.whatActivities())
                .putStrainOnWrist(curationRequestDto.putStrainOnWrist())
                .pastMedicalHistory(curationRequestDto.pastMedicalHistory())
                .differentPastMedicalHistory(curationRequestDto.differentPastMedicalHistory())
                .build();
        aiConsultingRepository.save(curation);

        return CurationResponseDto.of(answerList);
    }

    private String createPrompt(CurationRequestDto curationRequestDto) {
        String Gender, PutStrainOnWrist, PastMedicalHistory, DifferentPastMedicalHistory;
        long howLong = ChronoUnit.DAYS.between(curationRequestDto.howLong(), LocalDateTime.now());

        if (curationRequestDto.gender()) {Gender = "여자";}
        else {Gender = "남자";}
        if (curationRequestDto.putStrainOnWrist()) { PutStrainOnWrist = "그리고 내 직업이나 취미 활동이 내 손목에 부담을 줘";}
        else {PutStrainOnWrist = "그리고 내 직업이나 취미 활동이 내 손목에 부담을 안 줘";}
        if (curationRequestDto.pastMedicalHistory()) { PastMedicalHistory = " 과거에 손목을 다치거나 수술한 경험이 있고, ";}
        else {PastMedicalHistory = " 과거에 손목을 다치거나 수술한 경험이 없고, ";}
        if (curationRequestDto.differentPastMedicalHistory()) {
            DifferentPastMedicalHistory = "다른 관절이나 근육 관련 질환을 앓은 적이 있어.";
        } else {
            DifferentPastMedicalHistory = "다른 관절이나 근육 관련 질환을 앓은 적이 없어.";
        }


        return "나는 " + curationRequestDto.birthday() + "에 태어난 " + Gender + "이고, " +
                curationRequestDto.jobOrHobby() + "를 하고있어." + curationRequestDto.whereDoesItHurt() + "의 " +
                curationRequestDto.position() + "이 0~10 중" + String.valueOf(curationRequestDto.levelOfPain()) +
                "의 정도로 아파." + String.valueOf(howLong) + "일 전부터 아팠고. \"" + curationRequestDto.howSick() +
                "\" 이렇게 아파. 주로 " + curationRequestDto.whatActivities() + "할 때 더 심해져. " + PutStrainOnWrist +
                PastMedicalHistory + DifferentPastMedicalHistory +
                "아래 형식에 맞춰서 답변해줘" +
                "1. 손목 부담의 원인\n" + "내용 : (너의 답변)\n" +
                "2. 문제의 본질\n" + "내용 : (너의 답변)\n" +
                "3. 일상 속 해결책\n" + "내용 : (너의 답변)\n" +
                "4. 스트레칭 추천\n" + "내용 : (너의 답변)";
    }

    private String getTitleFromSentence(String sentence) {
        // 문장에서 숫자와 마침표를 제외한 부분을 key로 반환
        String[] parts = sentence.split(" ", 2);
        if (parts.length > 1) {
            return parts[1].trim();
        }
        return "";
    }

    private String getContentFromSentence(String sentence) {
        // 문장에서 "내용:" 이후의 내용을 추출하여 반환
        int index = sentence.indexOf("내용:");
        if (index == -1) {
            return sentence.substring(index + 6).trim();
        }
        return "";
    }

    private User findUser(Long userId) {
        return Optional.ofNullable(userRepository.getUserById(userId))
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.NOT_FOUND_USER.getMessage() + userId));
    }
}
