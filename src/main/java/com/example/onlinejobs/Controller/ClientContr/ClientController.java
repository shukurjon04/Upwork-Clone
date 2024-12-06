package com.example.onlinejobs.Controller.ClientContr;

import com.example.onlinejobs.Dtos.Announced.ProjectDto;
import com.example.onlinejobs.Dtos.Response.ApiResponse;
import com.example.onlinejobs.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.onlinejobs.Dtos.Announced.EditProjectDto;

import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @PostMapping("/addProject")
    public ResponseEntity<?> addProject(@RequestBody ProjectDto projectDto){
        ApiResponse apiResponse = clientService.AddProject(projectDto);
        return ResponseEntity.status(apiResponse.status()).body(apiResponse);
    }
    @DeleteMapping("/deleteProject/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        ApiResponse apiResponse = clientService.DeleteProject(id);
        return ResponseEntity.status(apiResponse.status()).body(apiResponse);
    }
    @PutMapping("/editProject")
    public ResponseEntity<?> edit(@RequestBody EditProjectDto dto){
        ApiResponse apiResponse = clientService.EditProject(dto);
        return ResponseEntity.status(apiResponse.status()).body(apiResponse);
    }
}
