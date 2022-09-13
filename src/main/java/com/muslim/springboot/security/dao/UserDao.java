package com.muslim.springboot.security.dao;

import com.muslim.springboot.security.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    User getUser(int id);

    List<User> listUsers();

    void deleteUser(int id);

    User getUserByUsername(String username);
}
