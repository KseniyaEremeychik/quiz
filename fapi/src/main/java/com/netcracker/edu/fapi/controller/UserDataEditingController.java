package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/userEditing")
public class UserDataEditingController {
    @Autowired
    private UserDataService userDataService;

    @GetMapping()
    public ResponseEntity<List<UserViewModel>> getAllUsers() {
        return ResponseEntity.ok(userDataService.getAll());
    }
}
