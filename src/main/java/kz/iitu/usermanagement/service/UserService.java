package kz.iitu.usermanagement.service;

import kz.iitu.usermanagement.model.User;

import java.util.List;

public interface UserService {
    User byUsername(String username);

    List<User> findAll();

    List<User> getChildrenByParent(String parentUsername);

    User getParentByChildren(String childUsername);
}
