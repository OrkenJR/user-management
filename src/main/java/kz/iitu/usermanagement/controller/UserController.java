package kz.iitu.usermanagement.controller;

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
 * Чуть позже добавлю Swagger
 * И все эндпоинты которые возвращают лист по возможности оберну в Page, это обязательно
 * Также надо разделить эндпоинты на microservices/frontend, для удобства, чтобы в дальнейшем не было проблем с security
 *
 * @author Orken
 * @version 1.0.0
 **/
@RestController
@RequiredArgsConstructor
public class UserController extends AbstractController {

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
