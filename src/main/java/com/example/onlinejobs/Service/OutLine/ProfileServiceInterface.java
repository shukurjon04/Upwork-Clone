package com.example.onlinejobs.Service.OutLine;

import com.example.onlinejobs.Dtos.Picture.PictureDto;
import com.example.onlinejobs.Dtos.Picture.ProfileDto;
import com.example.onlinejobs.Dtos.Response.ApiResponse;
import com.example.onlinejobs.Dtos.Response.ProfileResponse;

import java.util.UUID;

public interface ProfileServiceInterface {

    ProfileResponse getProfile(UUID id);
    ApiResponse setProfile(ProfileDto profile);
    ApiResponse rePicture(PictureDto pictureDto);

}
