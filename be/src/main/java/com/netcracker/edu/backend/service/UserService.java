package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    User findByEmail(String email);
    Iterable<User> getAllUsers();
    void deleteUser(Integer id);
    Optional<User> getUserById(Integer id);
    Boolean isUserNameExist(String userName);
    Boolean isEmailExist(String email);
}
