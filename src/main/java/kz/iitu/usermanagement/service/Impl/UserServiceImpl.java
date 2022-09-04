package kz.iitu.usermanagement.service.Impl;

import kz.iitu.usermanagement.model.User;
import kz.iitu.usermanagement.repository.UserRepository;
import kz.iitu.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("UserService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User byUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new IllegalStateException(String.format("Could not find user by %s username", username)));
    }
}
