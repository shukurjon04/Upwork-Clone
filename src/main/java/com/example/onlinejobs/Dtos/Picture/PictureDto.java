package com.example.onlinejobs.Dtos.Picture;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class PictureDto {

    private UUID profileId;

    private MultipartFile picture;

    public PictureDto(UUID profileId, MultipartFile picture) {
        this.profileId = profileId;
        this.picture = picture;
    }

    public UUID getProfileId() {
        return profileId;
    }

    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }
}
