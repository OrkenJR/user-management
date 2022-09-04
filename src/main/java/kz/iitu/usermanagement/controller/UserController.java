package kz.iitu.usermanagement.controller;

import kz.iitu.usermanagement.model.User;
import kz.iitu.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/byUsername")
    public ResponseEntity<User> byUsername(@RequestParam String username) {
        return new ResponseEntity<>(userService.byUsername(username), HttpStatus.OK);
    }
}
