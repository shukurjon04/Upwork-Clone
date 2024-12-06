package com.example.onlinejobs.Service;

import com.example.onlinejobs.Dtos.Picture.PictureDto;
import com.example.onlinejobs.Dtos.Picture.ProfileDto;
import com.example.onlinejobs.Dtos.Response.ApiResponse;
import com.example.onlinejobs.Dtos.Response.ProfileData;
import com.example.onlinejobs.Dtos.Response.ProfileResponse;
import com.example.onlinejobs.Entity.ProfileTable.ProfilePictures;
import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import com.example.onlinejobs.Entity.ProfileTable.UserProfile;
import com.example.onlinejobs.Repository.ProfilePicturesRepository;
import com.example.onlinejobs.Repository.ProfileRepository;
import com.example.onlinejobs.Repository.UserRepository;
import com.example.onlinejobs.Service.OutLine.ProfileServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService implements ProfileServiceInterface {
    @Value("${spring.upload.directory}")
    private String baseurl;

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final ProfilePicturesRepository profilePicturesRepository;

    @Autowired
    public ProfileService(UserRepository userRepository, ProfileRepository profileRepository, ProfilePicturesRepository profilePicturesRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.profilePicturesRepository = profilePicturesRepository;
    }


    @Override
    public ApiResponse setProfile(ProfileDto profile) {
        Optional<UserJobs> userJobs = userRepository.findById(profile.getUserId());
        if (userJobs.isPresent()) {
            UserJobs userJobs1 = userJobs.get();
            MultipartFile pictureProfile = profile.getFile();
            String originalFileName = pictureProfile.getOriginalFilename();
            if (originalFileName == null || !originalFileName.contains(".")) {
                return new ApiResponse("file not found", HttpStatus.BAD_REQUEST.value(), false, null);
            }
            Path filePath = saveFileName(originalFileName);
            String saveFileName = savedName(originalFileName);
            String fileUrl = CreateFileForPicture(filePath, pictureProfile, saveFileName);

            ProfilePictures pictures = new ProfilePictures();
            pictures.setFilePath(fileUrl);
            pictures.setSavefileName(saveFileName);
            ProfilePictures save2 = profilePicturesRepository.save(pictures);

            UserProfile userProfile1 = new UserProfile();
            userProfile1.setDescription(profile.getDescription());
            userProfile1.setYear(profile.getDate());
            userProfile1.setPicture(save2);
            UserProfile save = profileRepository.save(userProfile1);


            userJobs1.setProfile(save);
            UserJobs save1 = userRepository.save(userJobs1);
            return new ApiResponse("success", HttpStatus.OK.value(), true, save1);
        }
        return new ApiResponse("user not found", HttpStatus.BAD_REQUEST.value(), false, null);
    }

    @Override
    public ProfileResponse getProfile(UUID id) {
        Optional<UserJobs> userJobs = userRepository.findById(id);
        if (userJobs.isPresent()) {
            UserJobs userJobs1 = userJobs.get();
            UserProfile profile = userJobs1.getProfile();
            return new ProfileResponse("success",
                    HttpStatus.OK.value(),
                    true,
                    new ProfileData(
                            userJobs1.getFirstname(),
                            userJobs1.getLastname(),
                            userJobs1.getEmail(),
                            userJobs1.getUsername(),
                            profile.getDescription(),
                            profile.getYear(),
                            userJobs1.getPhone(),
                            profile.getPicture().getFilePath()
                    )
            );
        }
        return new ProfileResponse("user not found",
                HttpStatus.BAD_REQUEST.value(),
                false,
                new ProfileData()
        );
    }

    @Override
    public ApiResponse rePicture(PictureDto pictureDto) {
        Optional<UserProfile> profileOptional = profileRepository.findById(pictureDto.getProfileId());
        if (profileOptional.isPresent()) {
            UserProfile userProfile = profileOptional.get();

            ProfilePictures profilePictures = new ProfilePictures();
            MultipartFile picture = pictureDto.getPicture();
            String originalFileName = picture.getOriginalFilename();

            if (originalFileName == null || !originalFileName.contains(".")) {
                return new ApiResponse("original filename not found", HttpStatus.NO_CONTENT.value(), false, null);
            }

            Path filePath = saveFileName(originalFileName);
            String saveFileName = savedName(originalFileName);
            String fileUrl = CreateFileForPicture(filePath, picture, saveFileName);

            profilePictures.setFilePath(fileUrl);
            profilePictures.setSavefileName(saveFileName);
            ProfilePictures save = profilePicturesRepository.save(profilePictures);
            userProfile.setPicture(save);
            profileRepository.save(userProfile);

            return new ApiResponse("uploaded", HttpStatus.CREATED.value(), true, null);

        }
        return new ApiResponse("user not found", HttpStatus.NOT_FOUND.value(), false, null);
    }


    private Path saveFileName(String originalFileName) {
        String saveFileName = savedName(originalFileName);
        return Paths.get(baseurl + File.separator + saveFileName);
    }

    private String savedName(String originalFileName) {
        String split = originalFileName.split("\\.")[originalFileName.split("\\.").length - 1];
        return UUID.randomUUID() + "." + split;
    }

    private String CreateFileForPicture(Path filePath, MultipartFile picture, String saveFileName) {
        try {
            Files.createDirectories(filePath.getParent());

            Files.copy(picture.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/profile/picture/")
                .path(saveFileName)
                .toUriString();
    }
}
