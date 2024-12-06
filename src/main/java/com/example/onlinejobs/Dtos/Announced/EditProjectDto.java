package com.example.onlinejobs.Dtos.Announced;

import com.example.onlinejobs.Entity.Category.Enums.CategoryType;

import java.util.UUID;

public class EditProjectDto {

    private UUID projectId;
    private UUID clientId;
    private String title;
    private String description;
    private boolean isCompleted;
    private boolean isLiked;


    public EditProjectDto(UUID projectId, String title, String description, boolean isCompleted, boolean isLiked) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.isLiked = isLiked;
    }

    public EditProjectDto(UUID projectId, UUID clientId, String title, String description, boolean isCompleted, boolean isLiked) {
        this.projectId = projectId;
        this.clientId = clientId;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.isLiked = isLiked;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public UUID getProjectId() {
        return projectId;
    }


    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

}
