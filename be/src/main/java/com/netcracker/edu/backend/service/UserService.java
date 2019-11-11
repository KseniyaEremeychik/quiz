package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.User;

public interface UserService {
    User saveUser(User user);
    User findByEmail(String email);
    Iterable<User> getAllUsers();
}
