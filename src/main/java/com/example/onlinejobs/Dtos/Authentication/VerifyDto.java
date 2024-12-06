package com.example.onlinejobs.Dtos.Authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class VerifyDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String verificationCode;


    public VerifyDto(String email, String verificationCode) {
        this.email = email;
        this.verificationCode = verificationCode;
    }

    public  String getEmail() {
        return this.email;
    }

    public String getVerificationCode() {
        return this.verificationCode;
    }

}
