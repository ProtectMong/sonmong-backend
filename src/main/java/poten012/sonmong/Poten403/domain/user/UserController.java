package poten012.sonmong.Poten403.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poten012.sonmong.Poten403.common.ApiResponse;
import poten012.sonmong.Poten403.domain.user.domain.User;
import poten012.sonmong.Poten403.domain.user.repository.UserRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("")
    public ResponseEntity<ApiResponse> createUser(@RequestBody String userName) {

        val user = User.builder()
                .name(userName)
                .build();
        userRepository.save(user);

        return ResponseEntity.ok(ApiResponse.success("성공"));
    }
}
