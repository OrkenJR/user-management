package kz.iitu.usermanagement.repository;

import kz.iitu.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByUsername(String username);

    List<User> findUsersByParentUsername(String parentUsername);

    List<User> findUsersByParent(User parent);
}
