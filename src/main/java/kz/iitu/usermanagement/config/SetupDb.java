package kz.iitu.usermanagement.config;

import kz.iitu.usermanagement.model.User;
import kz.iitu.usermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
/**
 *
 * При запуске заполняет базу, только для теста, надо вынести в dev профиль, если возможно
 *
 * @author Orken
 * @version 1.0.0
 *
 * **/
@Component
@RequiredArgsConstructor
public class SetupDb implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        userRepository.deleteAll();
        userRepository.save(User.builder().username("admin").password("admin").build());
    }
}