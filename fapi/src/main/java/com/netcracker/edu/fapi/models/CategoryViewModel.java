package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryViewModel {

    private int id;

    @NotEmpty()
    @Size(max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9!?,._-][a-zA-Z0-9!?,._\\s-]+$")
    private String name;

    public CategoryViewModel() {
    }

    public CategoryViewModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
