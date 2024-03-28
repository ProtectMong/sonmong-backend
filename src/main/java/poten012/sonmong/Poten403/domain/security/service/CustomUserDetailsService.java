package poten012.sonmong.Poten403.domain.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import poten012.sonmong.Poten403.common.exception.ApiException;
import poten012.sonmong.Poten403.domain.security.domain.CustomUserDetails;
import poten012.sonmong.Poten403.domain.user.code.UserErrorCode;
import poten012.sonmong.Poten403.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * @param phoneNumber 유저의 휴대폰 번호
     */
    @Override
    public CustomUserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepository.getUserByPhoneNumber(phoneNumber)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new ApiException(UserErrorCode.NOT_EXISTS));
    }
}
