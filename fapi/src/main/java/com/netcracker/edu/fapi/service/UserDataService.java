package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.UserViewModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserDataService {
    UserViewModel saveUser(UserViewModel user);

    UserViewModel findByEmail(String email);

    UserViewModel getUserByToken(String emailByToken);

    Page<UserViewModel> getAll(Integer page, Integer size, String sortParam, Integer sortFormat);

    void deleteUser(Integer id);

    UserViewModel getUserById(Integer id);
}
