package com.example.onlinejobs.Dtos.Response;


public record ApiResponse(String message, int status, boolean success, Object data) {
}
