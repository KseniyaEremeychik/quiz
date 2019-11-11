package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserDataController {
    @Autowired
    private UserDataService userDataService;

    //todo backend validation for new value from frontend
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserViewModel> saveUser(@RequestBody UserViewModel user) {
        if(user != null) {
            return ResponseEntity.ok(userDataService.saveUser(user));
        }
        return null;
    }

    @GetMapping()
    public UserViewModel getUserByEmail(@RequestParam(name = "email") String email) {
        return userDataService.findByEmail(email);
    }
}
