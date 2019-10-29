package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
