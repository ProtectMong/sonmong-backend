package poten012.sonmong.Poten403.domain.aiconsulting.dto.response;

import lombok.Builder;
import poten012.sonmong.Poten403.domain.aiconsulting.domain.Curation;
import poten012.sonmong.Poten403.domain.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CurationResponseDto(
        String userName,
        String birthday,
        String jobOrHobby,
        String whereDoesItHurt,
        String position,
        int levelOfPain,
        String howLong,
        String whatActivities,
        boolean pastMedicalHistory,
        boolean differentPastMedicalHistory,
        List<CurationResponseVo> answerList,
        String stretching
) {
    public static CurationResponseDto of (User user, Curation curation, List<CurationResponseVo> answerList) {
        return CurationResponseDto.builder()
                .userName(user.getName())
                .birthday(curation.getBirthday())
                .jobOrHobby(curation.getJobOrHobby())
                .whereDoesItHurt(curation.getWhereDoesItHurt())
                .position(curation.getPosition())
                .levelOfPain(curation.getLevelOfPain())
                .howLong(curation.getHowLong())
                .whatActivities(curation.getWhatActivities())
                .pastMedicalHistory(curation.isPastMedicalHistory())
                .differentPastMedicalHistory(curation.isDifferentPastMedicalHistory())
                .answerList(answerList)
                .stretching("https://sonmongebucket.s3.ap-northeast-2.amazonaws.com/%EB%A7%88%EC%9A%B0%EC%8A%A4.gif")
                .build();
    }
}

