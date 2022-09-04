package kz.iitu.usermanagement.service;

import kz.iitu.usermanagement.model.User;

public interface UserService {
    User byUsername(String username);
}
