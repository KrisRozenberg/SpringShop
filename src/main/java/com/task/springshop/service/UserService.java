package com.task.springshop.service;

import com.task.springshop.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(User user);

    List<User> findAll();
    Optional<User> findUserByLogin(String login);
}
