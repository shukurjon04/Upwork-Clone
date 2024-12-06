package com.example.onlinejobs.Dtos.Response;

import java.util.Date;

public class ProfileResponse {
    private String message;
    private int status;
    private boolean success;
    private ProfileData profileData;

    public ProfileResponse() {
    }

    public ProfileResponse(String message, int status, boolean success, ProfileData profileData) {
        this.message = message;
        this.status = status;
        this.success = success;
        this.profileData = profileData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ProfileData getProfileData() {
        return profileData;
    }

    public void setProfileData(ProfileData profileData) {
        this.profileData = profileData;
    }
}