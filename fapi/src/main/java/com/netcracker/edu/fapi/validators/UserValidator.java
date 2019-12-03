package com.netcracker.edu.fapi.validators;

import com.netcracker.edu.fapi.models.UserViewModel;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserViewModel.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        UserViewModel user = (UserViewModel) obj;

        String regExUserName = "[a-zA-Z_]+";
        if(user.getUserName().isEmpty() || user.getUserName().length() < 4) {
            errors.rejectValue("userName", null, "Username should be at least 4 characters");
        }

        if(!user.getUserName().matches(regExUserName)) {
            errors.rejectValue("userName", null, "Username should contain only latin letters and underscores");
        }

        String regExEmail = "[a-zA-Z_.]+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}";
        if(user.getEmail().isEmpty() || (!user.getEmail().matches(regExEmail))) {
            errors.rejectValue("email", null, "Email is invalid");
        }

        if(user.getPassword().length() < 8) {
            errors.rejectValue("password", null, "Password should be at least 8 characters");
        }
    }
}
