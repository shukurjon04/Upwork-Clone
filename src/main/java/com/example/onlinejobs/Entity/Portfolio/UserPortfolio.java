package com.example.onlinejobs.Entity.Portfolio;

import com.example.onlinejobs.Entity.ControlFunctionTable.AuditTable;
import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class UserPortfolio extends AuditTable {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserJobs user;
    @OneToOne(fetch = FetchType.LAZY)
    private PortfolioMedia media;

    public UserPortfolio(UUID id, String title, String description, UserJobs user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public UserPortfolio() {
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

    public UserJobs getUser() {
        return user;
    }

    public void setUser(UserJobs user) {
        this.user = user;
    }

    public UserPortfolio(UUID id, String title, String description, UserJobs user, PortfolioMedia media) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.media = media;
    }

    public PortfolioMedia getMedia() {
        return media;
    }

    public void setMedia(PortfolioMedia media) {
        this.media = media;
    }
}
