package com.example.onlinejobs.Service;

import com.example.onlinejobs.Dtos.Announced.EditProjectDto;
import com.example.onlinejobs.Dtos.Announced.ProjectDto;
import com.example.onlinejobs.Dtos.Announced.giveProject;
import com.example.onlinejobs.Dtos.Response.ApiResponse;
import com.example.onlinejobs.Entity.Category.Categories;
import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import com.example.onlinejobs.Entity.Orders.OrderProject;
import com.example.onlinejobs.Entity.RoleTable.RoleName;
import com.example.onlinejobs.Repository.CategoryRepository;
import com.example.onlinejobs.Repository.ProjectRepository;
import com.example.onlinejobs.Repository.UserRepository;
import com.example.onlinejobs.Service.OutLine.ClientServiceInterface;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService implements ClientServiceInterface {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public ClientService(UserRepository userRepository, CategoryRepository categoryRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.projectRepository = projectRepository;
    }


    @Override
    public ApiResponse AddProject(ProjectDto project) {
        Optional<UserJobs> userJobsByRole = userRepository.findUserJobsByRole(RoleName.Client, project.getClientId());
        if (userJobsByRole.isEmpty()){
            return new ApiResponse("client not found ", HttpStatus.NO_CONTENT.value(), false,null);
        }
        UserJobs userJobs = userJobsByRole.get();
        OrderProject order = new OrderProject();
        order.setClient(userJobsByRole.get());
        order.setDescription(project.getDescription());
        order.setTitle(project.getTitle());
        Categories categories = new Categories();
        categories.setName(userJobs.getCategories().getName());
        Categories saveCategory = categoryRepository.save(categories);
        order.setCategories(saveCategory);
        OrderProject saveProject = projectRepository.save(order);
        return new ApiResponse("success",HttpStatus.CREATED.value(),true,saveProject);
    }

    @Override
    public ApiResponse DeleteProject(UUID id) {
        Optional<OrderProject> byId = projectRepository.findById(id);
        if (byId.isEmpty()){
            return new ApiResponse("failed",HttpStatus.NO_CONTENT.value(), false,null);
        }
        OrderProject project = byId.get();
        Categories categories = project.getCategories();
        UserJobs client = project.getClient();
        userRepository.delete(client);
        if (project.getEmployee() != null){
            UserJobs employee = project.getEmployee();
            userRepository.delete(employee);
        }
        categoryRepository.delete(categories);
        projectRepository.delete(project);
        return new ApiResponse("deleted",HttpStatus.OK.value(), true,null);
    }

    @Override
    public ApiResponse EditProject(EditProjectDto project) {
        Optional<OrderProject> byId = projectRepository.findById(project.getProjectId());
        if (byId.isEmpty()){
            return new ApiResponse("failed to found project",HttpStatus.NOT_FOUND.value(),false,null);
        }
        Optional<UserJobs> byId1 = userRepository.findById(project.getClientId());
        if (byId1.isEmpty()){
            return new ApiResponse("user not found",HttpStatus.NOT_FOUND.value(),false,null);
        }
        UserJobs userJobs = byId1.get();
        OrderProject project1 = byId.get();

        Categories categories = project1.getCategories();
        categories.setName(userJobs.getCategories().getName());
        Categories categories1 = categoryRepository.save(categories);

        project1.setCategories(categories1);
        project1.setTitle(project.getTitle());
        project1.setDescription(project.getDescription());

        OrderProject save = projectRepository.save(project1);
        return new ApiResponse("success",HttpStatus.OK.value(), true,save);
    }
    @Override
    public ApiResponse GiveProjectToEmployee(giveProject projectdto){
        Optional<OrderProject> byId = projectRepository.findById(projectdto.getProjectId());
        if (byId.isEmpty()){
            return new ApiResponse("failed to found project",HttpStatus.NOT_FOUND.value(),false,null);
        }
        Optional<UserJobs> byId1 = userRepository.findById(projectdto.getEmployeeId());
        if (byId1.isEmpty()){
            return new ApiResponse("user not found",HttpStatus.NOT_FOUND.value(),false,null);
        }
        UserJobs userJobs = byId1.get();
        OrderProject project = byId.get();
        project.setEmployee(userJobs);
        OrderProject save = projectRepository.save(project);
        return new ApiResponse("success",HttpStatus.OK.value(), true,save);
    }
}
