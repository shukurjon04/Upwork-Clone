package com.example.onlinejobs.Controller.AdminContr;

import com.example.onlinejobs.Dtos.Response.ApiResponse;
import com.example.onlinejobs.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @GetMapping("/AllEmployees")
    public ApiResponse ViewAllEmployees(@RequestParam int page , @RequestParam int size) {
        return adminService.ViewAllEmployees(page , size);
    }
    @GetMapping("/AllClients")
    public ApiResponse VIewAllClients(@RequestParam int page , @RequestParam int size) {
        return adminService.VIewAllClients(page , size);
    }
    @DeleteMapping("/DeleteUser/{id}")
    public ApiResponse DeleteUser(@PathVariable UUID id){
        return adminService.DeleteUser(id);
    }
    @GetMapping("/getUser/{id}")
    public ApiResponse GetUser(@PathVariable UUID id) {
        return adminService.GetUser(id);
    }
    @PostMapping("/blockUser/{id}")
    public ApiResponse BlockUser(@PathVariable UUID id) {
        return adminService.BlockUser(id);
    }
    @PostMapping("/unblockUser/{id}")
    public ApiResponse UnblockUser(@PathVariable UUID id) {
        return adminService.UnblockUser(id);
    }
}
