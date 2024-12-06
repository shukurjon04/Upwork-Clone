package com.example.onlinejobs.Dtos.Authentication;

import com.example.onlinejobs.Aop.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public class LoginDto {
    @NotBlank
    @UniqueUsername
    private String username;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\\\d)(?=.*[@!#$%^&*()])[A-Za-z\\\\d@!#$%^&*()]{6,20}$")
    private String password;

    public  String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public void setPassword(@NotBlank @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\\\d)(?=.*[@!#$%^&*()])[A-Za-z\\\\d@!#$%^&*()]{6,20}$") String password) {
        this.password = password;
    }
}