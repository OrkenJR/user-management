package kz.iitu.usermanagement.service.impl;

import kz.iitu.usermanagement.model.User;
import kz.iitu.usermanagement.repository.UserRepository;
import kz.iitu.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Простенький сервис для взаимодействия с юзерами
 *
 * @author Orken
 * @version 1.0.0
 **/
@Service("UserService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User byUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new IllegalStateException(String.format("Could not find user by %s username", username)));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getChildrenByParent(String parentUsername) {
        return userRepository.findUsersByParentUsername(parentUsername);
    }

    @Override
    public User getParentByChildren(String childUsername) {
        Optional<User> child = userRepository.findUserByUsername(childUsername);
        return child.map(User::getParent).orElse(null);
    }
}
