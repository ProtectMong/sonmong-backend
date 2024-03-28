package poten012.sonmong.Poten403.domain.user.dto.response;

import lombok.Builder;
import poten012.sonmong.Poten403.domain.user.domain.User;

@Builder
public record UserDataResponseDto (
        Long id,
        String name,
        String phoneNumber
) {
    public static UserDataResponseDto of(User user) {
        return UserDataResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .build();

    }
}
