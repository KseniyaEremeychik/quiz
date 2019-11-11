package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.UserViewModel;

import java.util.List;

public interface UserDataService {
    UserViewModel saveUser(UserViewModel user);
    UserViewModel findByEmail(String email);
    List<UserViewModel> getAll();
}
