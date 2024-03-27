package poten012.sonmong.Poten403.domain.aiconsulting.dto.response;

import lombok.Builder;
import poten012.sonmong.Poten403.domain.aiconsulting.domain.Curation;
import poten012.sonmong.Poten403.domain.aiconsulting.domain.CurationAnswer;
import poten012.sonmong.Poten403.domain.user.domain.User;

import java.time.LocalDateTime;

@Builder
public record CurationDetailResponseDto(
        String userName,
        LocalDateTime birthday,
        String jobOrHobby,
        String whereDoesItHurt,
        String position,
        int levelOfPain,
        LocalDateTime howLong,
        String whatActivities,
        boolean pastMedicalHistory,
        boolean differentPastMedicalHistory,
        String firstAnswer,
        String secondAnswer,
        String thirdAnswer,
        String fourthAnswer
) {
    public static CurationDetailResponseDto of (User user, Curation curation, CurationAnswer curationAnswer) {
        return CurationDetailResponseDto.builder()
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
                .firstAnswer(curationAnswer.getFirstAnswer())
                .secondAnswer(curationAnswer.getSecondAnswer())
                .thirdAnswer(curationAnswer.getThirdAnswer())
                .fourthAnswer(curationAnswer.getFourthAnswer())
                .build();
    }
}
