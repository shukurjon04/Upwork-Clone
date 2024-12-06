package com.example.onlinejobs.Controller.EmployeContr;

import com.example.onlinejobs.Dtos.Announced.ApplyProject;
import com.example.onlinejobs.Dtos.EditServiceDto;
import com.example.onlinejobs.Dtos.Response.ApiResponse;
import com.example.onlinejobs.Dtos.ServiceDto;
import com.example.onlinejobs.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/applyForJob")
    public ResponseEntity<?> ApplyForJob(@RequestBody ApplyProject applyProject) {
        ApiResponse response = employeeService.ApplyForJob(applyProject);
        return ResponseEntity.status(response.status()).body(response);
    }
    @PostMapping("/addService")
    public ResponseEntity<?> AddService(@RequestBody ServiceDto dto) {
        ApiResponse response = employeeService.AddService(dto);
        return ResponseEntity.status(response.status()).body(response);
    }
    @DeleteMapping("/deleteService/{id}")
    public ResponseEntity<?> DeleteService(@PathVariable UUID id) {
        ApiResponse response = employeeService.DeleteService(id);
        return ResponseEntity.status(response.status()).body(response);
    }
    @PutMapping("/editService")
    public ResponseEntity<?> EditService(@RequestBody EditServiceDto dto) {
        ApiResponse response = employeeService.EditService(dto);
        return ResponseEntity.status(response.status()).body(response);
    }
}
