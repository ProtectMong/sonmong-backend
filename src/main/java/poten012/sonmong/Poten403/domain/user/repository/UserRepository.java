package poten012.sonmong.Poten403.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poten012.sonmong.Poten403.domain.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserById(Long userId);
}
