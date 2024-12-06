package com.example.onlinejobs.Controller.Profile;

import com.example.onlinejobs.Dtos.Picture.PictureDto;
import com.example.onlinejobs.Dtos.Picture.ProfileDto;
import com.example.onlinejobs.Dtos.Response.ApiResponse;
import com.example.onlinejobs.Dtos.Response.ProfileResponse;
import com.example.onlinejobs.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@RestController
@RequestMapping("/profile")
public class Profile {

    @Value("${spring.upload.directory}")
    private String baseurl;


    private final ProfileService profileService;

    @Autowired
    public Profile( ProfileService profileService) {
        this.profileService = profileService;

    }

    @PostMapping("/setProfile")
    public ResponseEntity<?> setProfile(@ModelAttribute ProfileDto profile) {
        ApiResponse setProfile = profileService.setProfile(profile);
        return ResponseEntity.status(setProfile.status()).body(setProfile);
    }
    @GetMapping("/picture/{filename}")
    public ResponseEntity<?> picture(@PathVariable String filename) throws FileNotFoundException {
        if(filename == null){
            throw new NullPointerException("filename is null");
        }
        File file = new File(baseurl + File.separator + filename);

        if (!file.exists()) {
            throw new FileNotFoundException("File does not exist");
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+filename+"\"")
                .contentLength(file.length())
                .contentType(MediaType.IMAGE_JPEG)
                .body(new FileSystemResource(file));
    }

    @PostMapping("/rePicture")
    public ResponseEntity<?> rePicture(@ModelAttribute PictureDto pictureDto) {
        ApiResponse apiResponse = profileService.rePicture(pictureDto);
        return ResponseEntity.status(apiResponse.status()).body(apiResponse);
    }
    @GetMapping("/getProfile/{id}")
    public ResponseEntity<?> getProfile(@PathVariable UUID id) {
        ProfileResponse profile = profileService.getProfile(id);
        return ResponseEntity.status(profile.getStatus()).body(profile);

    }


}
