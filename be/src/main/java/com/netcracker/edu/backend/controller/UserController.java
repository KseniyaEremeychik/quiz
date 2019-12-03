package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByEmail(@RequestParam(name = "email") String email) {
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(name = "id") Integer id) {
        userService.deleteUser(id);
    }

    @RequestMapping(value = "/api/userBe/", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@RequestParam(name = "userId") Integer id) {
        Optional<User> user = userService.getUserById(id);
        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/api/userCheckName", method = RequestMethod.GET)
    public Boolean isUserNameExist(@RequestParam(name = "userName") String userName) {
        return userService.isUserNameExist(userName);
    }

    @RequestMapping(value = "/api/usersCheckEmail", method = RequestMethod.GET)
    public Boolean isEmailExist(@RequestParam(name = "email") String email) {
        return userService.isEmailExist(email);
    }
}
