package com.example.onlinejobs.Service.OutLine;

import com.example.onlinejobs.Dtos.Announced.EditProjectDto;
import com.example.onlinejobs.Dtos.Announced.ProjectDto;
import com.example.onlinejobs.Dtos.Announced.giveProject;
import com.example.onlinejobs.Dtos.Response.ApiResponse;

import java.util.UUID;

public interface ClientServiceInterface {
    ApiResponse AddProject(ProjectDto project);
    ApiResponse DeleteProject(UUID id);
    ApiResponse EditProject(EditProjectDto project);
    ApiResponse GiveProjectToEmployee(giveProject projectdto);
}
