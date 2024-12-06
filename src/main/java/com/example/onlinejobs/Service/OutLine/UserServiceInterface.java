package com.example.onlinejobs.Service.OutLine;

import com.example.onlinejobs.Dtos.Authentication.LoginDto;
import com.example.onlinejobs.Dtos.Authentication.UserDto;
import com.example.onlinejobs.Dtos.Authentication.VerifyDto;
import com.example.onlinejobs.Dtos.Response.ApiResponse;

public interface UserServiceInterface {
    ApiResponse register(UserDto userDto) throws ClassNotFoundException;

    ApiResponse verify(VerifyDto verifyDto);

    ApiResponse login(LoginDto loginDto);

    ApiResponse logout();

}
