package kz.iitu.usermanagement.config;

import kz.iitu.usermanagement.model.Role;
import kz.iitu.usermanagement.model.User;
import kz.iitu.usermanagement.repository.RoleRepository;
import kz.iitu.usermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * При запуске заполняет базу, только для теста, надо вынести в dev профиль, если возможно
 *
 * @author Orken
 **/
@Component
@RequiredArgsConstructor
public class SetupDb implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        userRepository.deleteAll();
        roleRepository.deleteAll();

        Role ceoRole = roleRepository.save(new Role("CEO"));
        Role firstSectorRole = roleRepository.save(new Role("engine_sector_head_role"));
        Role secondSectorRole = roleRepository.save(new Role("door_sector_head_role"));
        Role firstSectorWorkerRole = roleRepository.save(new Role("engine_sector_role"));
        Role secondSectorWorkerRole = roleRepository.save(new Role("door_sector_role"));

        User ceo = userRepository.save(User.builder()
                .username("ceo")
                .password("test")
                .roles(List.of(ceoRole))
                .build());

        User firstSector = userRepository.save(User.builder()
                .username("engine sector head")
                .password("test")
                .parent(ceo)
                .roles(List.of(firstSectorRole))
                .build());

        User secondSector = userRepository.save(User.builder()
                .username("door sector head")
                .password("test").parent(ceo)
                .roles(List.of(secondSectorRole))
                .build());

        createWorkers(firstSector, firstSectorWorkerRole);
        createWorkers(secondSector, secondSectorWorkerRole);
    }

    private void createWorkers(User sectorHead, Role role) {
        for (int i = 0; i < 3; i++) {
            userRepository.save(User.builder()
                    .username(sectorHead.getUsername() + "-worker-" + i)
                    .password("test")
                    .parent(sectorHead)
                    .roles(List.of(role))
                    .build());
        }
    }
}