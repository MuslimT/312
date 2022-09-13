package com.muslim.springboot.security.service;

import com.muslim.springboot.security.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    User getUser(int id);

    List<User> listUsers();

    void deleteUser(int id);

    User getUserByUsername(String username);
}
