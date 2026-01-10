package com.luminar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.luminar.entity.User;
import com.luminar.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register user (Admin / Student)
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // Get all users (Admin use)
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // ✅ FIX: Get user by email (USED FOR LOGIN)
    @GetMapping("/by-email")
    public User getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
}
