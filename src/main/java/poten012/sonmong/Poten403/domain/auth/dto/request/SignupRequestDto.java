package poten012.sonmong.Poten403.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import poten012.sonmong.Poten403.domain.user.domain.User;

public record SignupRequestDto(
        @Size(message = "유효하지 않은 휴대폰 번호입니다. (입력 예: 010xxxxxxxx)", min = 11, max = 11)
        @NotBlank(message = "휴대폰 번호를 입력해주세요.")
        String phoneNumber,

        @Size(message = "유효하지 않은 이름입니다. (최대 50자)", max = 50)
        @NotBlank(message = "이름을 입력해주세요.")
        String name
) {
        public User toEntity() {
                return User.builder()
                        .phoneNumber(this.phoneNumber)
                        .name(this.name)
                        .build();
        }
}
