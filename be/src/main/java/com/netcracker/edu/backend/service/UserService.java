package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    User findByEmail(String email);

    Page<User> getAllUsers(Integer page, Integer size, String sortParam, Integer sortFormat);

    void deleteUser(Integer id);

    Optional<User> getUserById(Integer id);

    Boolean isUserNameExist(String userName);

    Boolean isEmailExist(String email);
}
