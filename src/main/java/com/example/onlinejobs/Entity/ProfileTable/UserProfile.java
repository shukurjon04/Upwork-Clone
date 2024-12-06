package com.example.onlinejobs.Entity.ProfileTable;

import com.example.onlinejobs.Entity.ControlFunctionTable.AuditTable;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class UserProfile extends AuditTable {
    @Id
    @GeneratedValue
    private UUID id;

    private String description;

    private Date year;

    @OneToOne(fetch = FetchType.LAZY)
    private ProfilePictures picture;

    public UserProfile(UUID id, String description,  Date year) {
        this.id = id;
        this.description = description;
        this.year = year;
    }

    public ProfilePictures getPicture() {
        return picture;
    }

    public void setPicture(ProfilePictures picture) {
        this.picture = picture;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public UserProfile() {
    }



    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
