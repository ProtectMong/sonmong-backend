package poten012.sonmong.Poten403.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poten012.sonmong.Poten403.common.response.Api;
import poten012.sonmong.Poten403.domain.security.domain.CustomUserDetails;
import poten012.sonmong.Poten403.domain.user.code.UserSuccessCode;
import poten012.sonmong.Poten403.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public Api getMyData(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return Api.ok(UserSuccessCode.INQUIRY, userService.getMyData(userDetails));
    }
}
