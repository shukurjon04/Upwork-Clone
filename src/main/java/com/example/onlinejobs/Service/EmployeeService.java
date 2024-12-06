package com.example.onlinejobs.Service;

import com.example.onlinejobs.Dtos.Announced.ApplyProject;
import com.example.onlinejobs.Dtos.EditServiceDto;
import com.example.onlinejobs.Dtos.Response.ApiResponse;
import com.example.onlinejobs.Dtos.ServiceDto;
import com.example.onlinejobs.Entity.Applying.ApllyJob;
import com.example.onlinejobs.Entity.Category.Categories;
import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import com.example.onlinejobs.Entity.Orders.OrderProject;
import com.example.onlinejobs.Entity.Services.Servise;
import com.example.onlinejobs.Repository.*;
import com.example.onlinejobs.Service.OutLine.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ApplyJobRepository applyJobRepository;
    private final ServiceRepository serviceRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public EmployeeService(UserRepository userRepository, ProjectRepository projectRepository, ApplyJobRepository applyJobRepository, ServiceRepository serviceRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.applyJobRepository = applyJobRepository;
        this.serviceRepository = serviceRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ApiResponse ApplyForJob(ApplyProject applyProject) {
        Optional<UserJobs> EmployeeOptional = userRepository.findById(applyProject.getEmployeeId());
        if (EmployeeOptional.isEmpty())
            return new ApiResponse("Employee not found", HttpStatus.NOT_FOUND.value(), false, null);
        UserJobs employee = EmployeeOptional.get();
        Optional<UserJobs> ClientOptional = userRepository.findById(applyProject.getClientId());
        if (ClientOptional.isEmpty())
            return new ApiResponse("Client not found", HttpStatus.NOT_FOUND.value(), false, null);
        UserJobs client = ClientOptional.get();
        Optional<OrderProject> orderProject = projectRepository.findById(applyProject.getProjectId());
        if (orderProject.isEmpty())
            return new ApiResponse("Project not found", HttpStatus.NOT_FOUND.value(), false, null);
        OrderProject project = orderProject.get();

        ApllyJob applyJob = new ApllyJob();
        applyJob.setClient(client);
        applyJob.setEmployee(employee);
        applyJob.setOrderProject(project);

        ApllyJob save = applyJobRepository.save(applyJob);
        return new ApiResponse("success", HttpStatus.OK.value(), true, save);
    }

    @Override
    public ApiResponse AddService(ServiceDto dto) {
        Servise servise = new Servise();
        return servise(servise, dto);
    }

    @Override
    public ApiResponse DeleteService(UUID id) {
        Optional<Servise> byId = serviceRepository.findById(id);
        if (byId.isEmpty())
            return new ApiResponse("service not found", HttpStatus.NOT_FOUND.value(), false, null);
        serviceRepository.delete(byId.get());
        return new ApiResponse("success", HttpStatus.OK.value(), true, null);
    }

    @Override
    public ApiResponse EditService(EditServiceDto dto) {
        Optional<Servise> byId = serviceRepository.findById(dto.getServiceId());
        if (byId.isEmpty())
            return new ApiResponse("service not found", HttpStatus.NOT_FOUND.value(), false, null);
        Servise servise = byId.get();
        return servise(servise, dto);
    }

    private ApiResponse servise(Servise servise, ServiceDto dto) {
        servise.setPrice(dto.getPrice());
        servise.setDescription(dto.getDescription());
        servise.setName(dto.getTitle());
        Optional<UserJobs> optionalEmployee = userRepository.findById(dto.getEmployeeId());
        if (optionalEmployee.isEmpty()) {
            return new ApiResponse("user not found", HttpStatus.NOT_FOUND.value(), false, null);
        }
        UserJobs employee = optionalEmployee.get();
        Optional<Categories> optionalCategories = categoryRepository.findById(employee.getCategories().getId());
        if (optionalCategories.isEmpty()) {
            return new ApiResponse("category not found", HttpStatus.NOT_FOUND.value(), false, null);
        }
        Categories categories = optionalCategories.get();
        servise.setCategories(categories);
        if (servise.getEmployee() != null) {
            servise.getEmployee().add(employee);
        }
        servise.setEmployee(List.of(employee));
        Servise save = serviceRepository.save(servise);
        return new ApiResponse("success", HttpStatus.OK.value(), true, save);
    }

}
