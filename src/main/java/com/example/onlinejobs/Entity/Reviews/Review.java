package com.example.onlinejobs.Entity.Reviews;

import com.example.onlinejobs.Entity.ControlFunctionTable.AuditTable;
import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import com.example.onlinejobs.Entity.Orders.OrderProject;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Review extends AuditTable {

    @Id
    @GeneratedValue
    private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    private OrderProject orderProject;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserJobs user;
    @Column()
    private int rating;
    @Column(nullable = false)
    private String comment;

    public Review(UUID id, OrderProject orderProject, UserJobs user, int rating, String comment) {
        this.id = id;
        this.orderProject = orderProject;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    public Review() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OrderProject getOrder() {
        return orderProject;
    }

    public void setOrder(OrderProject orderProject) {
        this.orderProject = orderProject;
    }

    public UserJobs getUser() {
        return user;
    }

    public void setUser(UserJobs user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
