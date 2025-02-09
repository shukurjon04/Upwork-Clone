package com.example.onlinejobs.Dtos.Authentication;

import com.example.onlinejobs.Aop.UniqueUsername;
import com.example.onlinejobs.Entity.Category.Enums.CategoryType;
import com.example.onlinejobs.Entity.RoleTable.RoleName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record UserDto(@NotBlank String firstName, String lastName, @NotBlank @Email String email,
                      @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\\\d)(?=.*[@!#$%^&*()])[A-Za-z\\\\d@!#$%^&*()]{6,20}$",
                              message = "siz zaif parol kiritdingiz "
                      ) String password, @NotBlank @Pattern(
        regexp = "^(\\+\\d{1,3}[- ]?)?(\\(?\\d{1,4}\\)?[- ]?)?\\d{1,4}([- ]?\\d{1,4}){1,3}$",
        message = "Telefon raqami noto‘g‘ri formatda"
) String phone, @UniqueUsername @NotBlank String username, @NotBlank RoleName role, @NotBlank CategoryType categoryType) {


    public UserDto(String firstName, String lastName, String email, String password, String phone, String username, RoleName role ,CategoryType categoryType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.categoryType = categoryType;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.username = username;
        this.role = role;
    }


    @Override
    public String firstName() {
        return firstName;
    }

    @Override
    public CategoryType categoryType() {
        return categoryType;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public String phone() {
        return phone;
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public RoleName role() {
        return role;
    }



}
