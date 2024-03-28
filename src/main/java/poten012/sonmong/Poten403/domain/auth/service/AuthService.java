package poten012.sonmong.Poten403.domain.auth.service;

import poten012.sonmong.Poten403.domain.auth.dto.request.SigninRequestDto;
import poten012.sonmong.Poten403.domain.auth.dto.request.SignupRequestDto;
import poten012.sonmong.Poten403.domain.auth.dto.response.TokenResponseDto;

public interface AuthService {

    void signup(SignupRequestDto signupRequestDto);

    TokenResponseDto signin(SigninRequestDto signinRequestDto);
}
