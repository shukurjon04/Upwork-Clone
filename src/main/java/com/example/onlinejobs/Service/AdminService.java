package com.example.onlinejobs.Service;

import com.example.onlinejobs.Dtos.Response.ApiResponse;
import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import com.example.onlinejobs.Entity.ProfileTable.ProfilePictures;
import com.example.onlinejobs.Entity.ProfileTable.UserProfile;
import com.example.onlinejobs.Entity.RoleTable.Role;
import com.example.onlinejobs.Entity.RoleTable.RoleName;
import com.example.onlinejobs.Repository.ProfilePicturesRepository;
import com.example.onlinejobs.Repository.ProfileRepository;
import com.example.onlinejobs.Repository.RoleRepository;
import com.example.onlinejobs.Repository.UserRepository;
import com.example.onlinejobs.Service.OutLine.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminService implements AdminServiceInterface {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProfileRepository profileRepository;
    private final ProfilePicturesRepository profilePicturesRepository;

    @Autowired
    public AdminService(UserRepository userRepository, RoleRepository roleRepository, ProfileRepository profileRepository, ProfilePicturesRepository profilePicturesRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.profileRepository = profileRepository;
        this.profilePicturesRepository = profilePicturesRepository;
    }

    @Override
    public ApiResponse ViewAllEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Optional<Role> byRoleName = roleRepository.findByRoleName(RoleName.Employee);
        if (byRoleName.isEmpty())
            return new ApiResponse("employee role not found ", 404, false, null);
        Role employeeRole = byRoleName.get();
        Page<UserJobs> Employee = userRepository.findUserJobsByRole(employeeRole.getId(), pageable);
        if (Employee.isEmpty())
            return new ApiResponse("employee not found ", 404, false, null);
        return new ApiResponse("success", 200, true, Employee);
    }

    @Override
    public ApiResponse VIewAllClients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Optional<Role> byRoleName = roleRepository.findByRoleName(RoleName.Client);
        if (byRoleName.isEmpty())
            return new ApiResponse("client role not found ", 404, false, null);
        Role clientRole = byRoleName.get();
        Page<UserJobs> Client = userRepository.findUserJobsByRole(clientRole.getId(), pageable);
        if (Client.isEmpty())
            return new ApiResponse("client not found ", 404, false, null);
        return new ApiResponse("success", 200, true, Client);
    }

    @Override
    public ApiResponse GetUser(UUID id) {
        Optional<UserJobs> user = userRepository.findById(id);
        return user.map(
                userJobs -> new ApiResponse("success", 200, true, userJobs)
        ).orElseGet(
                () -> new ApiResponse("user not found ", HttpStatus.NOT_FOUND.value(), false, null)
        );
    }

    @Override
    public ApiResponse DeleteUser(UUID id) {
        Optional<UserJobs> byId = userRepository.findById(id);
        if (byId.isEmpty())
            return new ApiResponse("user not found",HttpStatus.NO_CONTENT.value(), false,null);
        UserJobs user = byId.get();
        UserProfile profile = user.getProfile();
        ProfilePictures picture = profile.getPicture();
        Path path = Path.of(picture.getFilePath());
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        profileRepository.delete(profile);
        profilePicturesRepository.delete(picture);
        userRepository.delete(user);
        return new ApiResponse("success", 200, true, null);
    }

    @Override
    public ApiResponse BlockUser(UUID id) {
        Optional<UserJobs> byId = userRepository.findById(id);
        if (byId.isEmpty())
            return new ApiResponse("user not found",HttpStatus.NO_CONTENT.value(), false,null);
        UserJobs user = byId.get();
        user.setEnabled(false);
        user.setBlocked(true);
        UserJobs save = userRepository.save(user);
        return new ApiResponse("success", 200, true, save);
    }

    @Override
    public ApiResponse UnblockUser(UUID id) {
        Optional<UserJobs> byId = userRepository.findById(id);
        if (byId.isEmpty())
            return new ApiResponse("user not found",HttpStatus.NO_CONTENT.value(), false,null);
        UserJobs user = byId.get();
        user.setEnabled(true);
        user.setBlocked(false);
        UserJobs save = userRepository.save(user);
        return new ApiResponse("success", 200, true, save);
    }
}
