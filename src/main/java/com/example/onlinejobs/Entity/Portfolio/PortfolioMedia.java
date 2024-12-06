package com.example.onlinejobs.Entity.Portfolio;

import com.example.onlinejobs.Entity.ControlFunctionTable.AuditTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class PortfolioMedia extends AuditTable {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true,nullable = false)
    private String saveFileName;
    @Column(nullable = false)
    private String mediaUrl;

    public PortfolioMedia(UUID id, String saveFileName, String mediaUrl) {
        this.id = id;
        this.saveFileName = saveFileName;
        this.mediaUrl = mediaUrl;
    }

    public PortfolioMedia() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}
