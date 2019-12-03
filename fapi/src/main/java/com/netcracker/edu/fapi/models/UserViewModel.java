package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserViewModel {
    private int id;

    @NotEmpty(message = "Username is required")
    @Size(min = 4, message = "Username should be at least 4 characters")
    @Pattern(regexp = "[a-zA-Z_]+", message = "Username should contain only latin letters and underscores")
    private String userName;

    @NotEmpty(message = "Email is required")
    @Pattern(regexp = "[a-zA-Z_.]+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}", message="Email is invalid")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password should be at least 8 characters")
    private String password;
    private String role;
    private Map<String, String> errors;

    public UserViewModel() {
    }

    public UserViewModel(int id, String userName, String email, String password, String role, Map<String, String> errors) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.errors = errors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
