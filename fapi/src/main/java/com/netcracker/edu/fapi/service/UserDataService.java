package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.UserViewModel;

public interface UserDataService {
    UserViewModel saveUser(UserViewModel user);
    UserViewModel findByEmail(String email);
}