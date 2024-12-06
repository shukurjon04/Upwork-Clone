package com.example.onlinejobs.Entity.ProfileTable;

import com.example.onlinejobs.Entity.ControlFunctionTable.AuditTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class ProfilePictures extends AuditTable {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String filePath;
    @Column(nullable = false,unique = true)
    private String savefileName;

    public ProfilePictures() {
    }

    public ProfilePictures(UUID id, String filePath, String savefileName) {
        this.id = id;
        this.filePath = filePath;
        this.savefileName = savefileName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSavefileName() {
        return savefileName;
    }

    public void setSavefileName(String savefileName) {
        this.savefileName = savefileName;
    }
}
