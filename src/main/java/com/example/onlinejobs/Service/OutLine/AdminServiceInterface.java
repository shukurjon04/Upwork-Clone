package com.example.onlinejobs.Service.OutLine;

import com.example.onlinejobs.Dtos.Response.ApiResponse;

import java.util.UUID;

public interface AdminServiceInterface {
    ApiResponse ViewAllEmployees(int page, int size);
    ApiResponse VIewAllClients(int page, int size);
    ApiResponse GetUser(UUID id);
    ApiResponse DeleteUser(UUID id);
    ApiResponse BlockUser(UUID id);
    ApiResponse UnblockUser(UUID id);
}
