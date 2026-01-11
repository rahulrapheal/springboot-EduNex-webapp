package com.luminar.service;

import java.util.List;

import com.luminar.entity.User;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    void deleteUser(Long id);
}
