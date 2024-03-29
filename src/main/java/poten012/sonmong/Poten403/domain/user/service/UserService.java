package poten012.sonmong.Poten403.domain.user.service;

import poten012.sonmong.Poten403.domain.security.domain.CustomUserDetails;
import poten012.sonmong.Poten403.domain.user.dto.response.UserDataResponseDto;

public interface UserService {

    UserDataResponseDto getMyData(CustomUserDetails userDetails);

}
