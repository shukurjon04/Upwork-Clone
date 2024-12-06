package com.example.onlinejobs.Dtos.Picture;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

public class ProfileDto {

    private UUID userId;

    private String description;

    private MultipartFile file;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProfileDto(UUID userId, String description, MultipartFile file, Date date) {
        this.userId = userId;
        this.description = description;
        this.file = file;
        this.date = date;
    }

    public ProfileDto() {
    }

    public ProfileDto(UUID userId, String description, MultipartFile file) {
        this.userId = userId;
        this.description = description;
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
