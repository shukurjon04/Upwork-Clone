package com.example.onlinejobs.Dtos.Announced;

import com.example.onlinejobs.Entity.Category.Enums.CategoryType;

import java.util.UUID;

public class ProjectDto {
    private UUID clientId;
    private String title;
    private String description;
    private boolean isCompleted;
    private boolean isLiked;
    private CategoryType categoryName;

    public ProjectDto(String title, String description, boolean isCompleted, boolean isLiked, CategoryType categoryName) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.isLiked = isLiked;
        this.categoryName = categoryName;
    }

    public ProjectDto(UUID clientId, String title, String description, boolean isCompleted, boolean isLiked, CategoryType categoryName) {
        this.clientId = clientId;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.isLiked = isLiked;
        this.categoryName = categoryName;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public ProjectDto() {
    }

    public CategoryType getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryType categoryName) {
        this.categoryName = categoryName;
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
