package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.UserViewModel;

import java.util.List;

public interface UserDataService {
    UserViewModel saveUser(UserViewModel user);
    //UserViewModel findByEmail(String email, String password);
    UserViewModel findByEmail(String email);
    UserViewModel getUserByToken(String emailByToken);
    List<UserViewModel> getAll();
    void deleteUser(Integer id);
    UserViewModel getUserById(Integer id);
}
