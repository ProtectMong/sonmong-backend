package poten012.sonmong.Poten403.domain.aiconsulting.dto.request;

import java.time.LocalDateTime;

public record CurationRequestDto(
        Long userId,
        LocalDateTime birthday,
        boolean gender,
        String jobOrHobby,
        String whereDoesItHurt,
        String position,
        int levelOfPain,
        LocalDateTime howLong,
        String howSick,
        String whatActivities,
        boolean putStrainOnWrist,
        boolean pastMedicalHistory,
        boolean differentPastMedicalHistory
) {
}
