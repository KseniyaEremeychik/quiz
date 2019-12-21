package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Page<User> getAllUsers(Integer page, Integer size, String sortParam, Integer sortFormat) {
        Pageable pageable = null;
        if (sortParam == null) {
            pageable = PageRequest.of(page, size);
        } else if (sortFormat == 1) {
            pageable = PageRequest.of(page, size, Sort.by(sortParam).ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sortParam).descending());
        }

        return userRepository.findAll(pageable);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Boolean isUserNameExist(String userName) {
        return userRepository.existsUserByUserName(userName);
    }

    @Override
    public Boolean isEmailExist(String email) {
        return userRepository.existsUserByEmail(email);
    }
}
