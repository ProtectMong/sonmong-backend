package poten012.sonmong.Poten403.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import poten012.sonmong.Poten403.common.exception.ApiException;
import poten012.sonmong.Poten403.domain.security.domain.CustomUserDetails;
import poten012.sonmong.Poten403.domain.user.code.UserErrorCode;
import poten012.sonmong.Poten403.domain.user.dto.response.UserDataResponseDto;
import poten012.sonmong.Poten403.domain.user.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDataResponseDto getMyData(CustomUserDetails userDetails) {
        return Optional.ofNullable(userRepository.getUserById(userDetails.getUserId()))
                .map(UserDataResponseDto::of)
                .orElseThrow(() -> new ApiException(UserErrorCode.NOT_EXISTS));
    }
}
