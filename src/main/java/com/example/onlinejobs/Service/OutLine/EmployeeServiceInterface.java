package com.example.onlinejobs.Service.OutLine;

import com.example.onlinejobs.Dtos.Announced.ApplyProject;
import com.example.onlinejobs.Dtos.EditServiceDto;
import com.example.onlinejobs.Dtos.Response.ApiResponse;
import com.example.onlinejobs.Dtos.ServiceDto;

import java.util.UUID;

public interface EmployeeServiceInterface {

    ApiResponse ApplyForJob(ApplyProject applyProject);
    ApiResponse AddService(ServiceDto dto);

    ApiResponse DeleteService(UUID id);

    ApiResponse EditService(EditServiceDto dto);

}
