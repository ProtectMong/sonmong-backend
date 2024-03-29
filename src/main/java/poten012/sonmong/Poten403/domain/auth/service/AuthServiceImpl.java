package poten012.sonmong.Poten403.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poten012.sonmong.Poten403.common.exception.ApiException;
import poten012.sonmong.Poten403.domain.security.jwt.JwtProvider;
import poten012.sonmong.Poten403.domain.auth.code.AuthErrorCode;
import poten012.sonmong.Poten403.domain.auth.dto.request.SigninRequestDto;
import poten012.sonmong.Poten403.domain.auth.dto.request.SignupRequestDto;
import poten012.sonmong.Poten403.domain.auth.dto.response.TokenResponseDto;
import poten012.sonmong.Poten403.domain.user.domain.User;
import poten012.sonmong.Poten403.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        if (userRepository.existsByPhoneNumber(signupRequestDto.phoneNumber())) {
            throw new ApiException(AuthErrorCode.ALREADY_REGISTERED);
        }
        User user = userRepository.save(signupRequestDto.toEntity());
    }

    @Override
    @Transactional(readOnly = true)
    public TokenResponseDto signin(SigninRequestDto signinRequestDto) {
        return userRepository.getUserByPhoneNumber(signinRequestDto.phoneNumber())
                .map(user -> {
                    return new TokenResponseDto(jwtProvider.generate(user.getPhoneNumber()));
                })
                .orElseThrow(() -> new ApiException(AuthErrorCode.NOT_REGISTERED));
    }
}
