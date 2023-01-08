package kz.iitu.usermanagement.controller;

import kz.iitu.cfaslib.controller.AbstractWrapper;
import kz.iitu.usermanagement.model.User;
import kz.iitu.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Основной контроллер этого микросервиса
 *
 * @author Orken
 **/
@RestController
@RequiredArgsConstructor
public class UserController extends AbstractWrapper {

    private final UserService userService;

    @GetMapping("/username/{username}")
    public ResponseEntity<User> byUsername(@PathVariable String username) {
        return responseWrap(userService.byUsername(username));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        return responseWrap(userService::findAll);
    }

    @GetMapping("/users/subordinates/{parent}")
    public ResponseEntity<List<User>> childrenByParent(@PathVariable String parent) {
        return responseWrap(() -> userService.getChildrenByParent(parent));
    }

    @GetMapping("/users/chef/{children}")
    public ResponseEntity<User> parentByChildren(@PathVariable String children) {
        return responseWrap(userService.getParentByChildren(children));
    }

}
