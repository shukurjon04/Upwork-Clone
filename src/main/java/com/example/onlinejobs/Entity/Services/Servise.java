package com.example.onlinejobs.Entity.Services;

import com.example.onlinejobs.Entity.Category.Categories;
import com.example.onlinejobs.Entity.ControlFunctionTable.AuditTable;
import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Servise extends AuditTable {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String description;
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)
    private Categories categories;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<UserJobs> employee;

    public Servise(UUID id, String title, String description, double price, Categories categories, List<UserJobs> employee) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.categories = categories;
        this.employee = employee;
    }

    public Servise() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public List<UserJobs> getEmployee() {
        return employee;
    }

    public void setEmployee(List<UserJobs> employee) {
        this.employee = employee;
    }
}
