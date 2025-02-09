package com.example.onlinejobs.Controller.Registration;

import com.example.onlinejobs.Dtos.Response.ApiResponse;
import com.example.onlinejobs.Dtos.Authentication.LoginDto;
import com.example.onlinejobs.Dtos.Authentication.UserDto;
import com.example.onlinejobs.Dtos.Authentication.VerifyDto;
import com.example.onlinejobs.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class RegisterController {
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> regsiter(@RequestBody UserDto userDto) throws ClassNotFoundException {
        ApiResponse register = userService.register(userDto);
        return ResponseEntity.status(register.status()).body(register);
    }
    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody VerifyDto verifyDto){
        ApiResponse verify = userService.verify(verifyDto);
        return ResponseEntity.status(verify.status()).body(verify);
    }
    @PostMapping("/login1")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
         ApiResponse login = userService.login(loginDto);
        return ResponseEntity.status(login.status()).body(login);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        ApiResponse logout = userService.logout();
        return ResponseEntity.status(logout.status()).body(logout);
    }
    @GetMapping("/oauth")
    public ResponseEntity<?> oauth(Principal token){
        return ResponseEntity.ok(token);
    }

}
