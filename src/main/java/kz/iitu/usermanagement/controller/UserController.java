package kz.iitu.usermanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User", description = "The User API")
@RestController
@RequiredArgsConstructor
public class UserController extends AbstractWrapper {

    private final UserService userService;

    @Operation(summary = "Getting user by username")
    @GetMapping("/username/{username}")
    public ResponseEntity<User> byUsername(@PathVariable String username) {
        return responseWrap(userService.byUsername(username));
    }

    @Operation(summary = "Getting list of user, soon will be deprecated")
    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        return responseWrap(userService::findAll);
    }

    @Operation(summary = "Getting list of subordinates")
    @GetMapping("/users/subordinates/{parent}")
    public ResponseEntity<List<User>> childrenByParent(@PathVariable String parent) {
        return responseWrap(() -> userService.getChildrenByParent(parent));
    }

    @Operation(summary = "Getting list of bosses")
    @GetMapping("/users/chef/{children}")
    public ResponseEntity<User> parentByChildren(@PathVariable String children) {
        return responseWrap(userService.getParentByChildren(children));
    }

}
