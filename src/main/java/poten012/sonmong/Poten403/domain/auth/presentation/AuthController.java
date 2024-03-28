package poten012.sonmong.Poten403.domain.auth.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poten012.sonmong.Poten403.common.response.Api;
import poten012.sonmong.Poten403.domain.auth.code.AuthSuccessCode;
import poten012.sonmong.Poten403.domain.auth.dto.request.SigninRequestDto;
import poten012.sonmong.Poten403.domain.auth.dto.request.SignupRequestDto;
import poten012.sonmong.Poten403.domain.auth.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public Api signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        authService.signup(signupRequestDto);
        return Api.ok(AuthSuccessCode.SIGNUP);
    }

    @PostMapping("/signin")
    public Api signin(@Valid @RequestBody SigninRequestDto signinRequestDto) {
        return Api.ok(AuthSuccessCode.SIGNIN, authService.signin(signinRequestDto));
    }
}
