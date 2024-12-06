package com.example.onlinejobs.Entity.Category;

import com.example.onlinejobs.Entity.Category.Enums.CategoryType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Categories {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private CategoryType name;


    public Categories(UUID id, CategoryType name) {
        this.id = id;
        this.name = name;
    }
    public Categories() {

    }

    public CategoryType getName() {
        return name;
    }

    public void setName(CategoryType name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
