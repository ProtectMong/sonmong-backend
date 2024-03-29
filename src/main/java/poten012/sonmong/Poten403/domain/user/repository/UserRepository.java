package poten012.sonmong.Poten403.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poten012.sonmong.Poten403.domain.user.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // TODO: (이야기해보기) 여기에 반환 값을 Optional<User>로 하는게 서비스 로직이 더 깔끔하지 않은지
    User getUserById(Long userId);

    Boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> getUserByPhoneNumber(String phoneNumber);
}
