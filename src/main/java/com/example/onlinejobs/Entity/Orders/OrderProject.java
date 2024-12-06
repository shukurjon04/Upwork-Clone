package com.example.onlinejobs.Entity.Orders;

import com.example.onlinejobs.Entity.Category.Categories;
import com.example.onlinejobs.Entity.ControlFunctionTable.AuditTable;
import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class OrderProject extends AuditTable {

    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserJobs employee;
    @ManyToOne(fetch = FetchType.LAZY)
    private Categories categories;
    private boolean isCompleted;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserJobs client;
    private boolean isLiked;

    public OrderProject(UUID id, String title, String description, UserJobs employee, Categories categories, boolean isCompleted, UserJobs client, boolean isLiked) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.employee = employee;
        this.categories = categories;
        this.isCompleted = isCompleted;
        this.client = client;
        this.isLiked = isLiked;
    }

    public OrderProject(UUID id, String title, String description, boolean isCompleted, UserJobs client) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.client = client;
    }

    public OrderProject(UUID id, String title, String description, UserJobs employee, boolean isCompleted, UserJobs client, boolean isLiked) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.employee = employee;
        this.isCompleted = isCompleted;
        this.client = client;
        this.isLiked = isLiked;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public OrderProject() {
    }

    public UserJobs getEmployee() {
        return employee;
    }

    public void setEmployee(UserJobs employee) {
        this.employee = employee;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public UserJobs getClient() {
        return client;
    }

    public void setClient(UserJobs client) {
        this.client = client;
    }
}
