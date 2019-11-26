package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserDataController {
    @Autowired
    private UserDataService userDataService;

    //todo backend validation for new value from frontend
    @RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public ResponseEntity<UserViewModel> saveUser(@RequestBody UserViewModel user) {
        if(user != null) {
            return ResponseEntity.ok(userDataService.saveUser(user));
        }
        return null;
    }

    @RequestMapping(value = "/api/userLogin", method = RequestMethod.POST)
    public UserViewModel findUserByEmail(@RequestBody UserViewModel userLogin) {
        return userDataService.findByEmail(userLogin.getEmail(), userLogin.getPassword());
    }

    @RequestMapping(value="/api/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String id) {
        userDataService.deleteUser(Integer.valueOf(id));
    }
}
