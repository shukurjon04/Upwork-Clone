package com.example.onlinejobs.Entity.Applying;

import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import com.example.onlinejobs.Entity.Orders.OrderProject;
import jakarta.persistence.*;

import java.util.UUID;
@Entity
public class ApllyJob {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserJobs employee;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserJobs client;
    @OneToOne(fetch = FetchType.EAGER)
    private OrderProject orderProject;

    public ApllyJob(UUID id, UserJobs employee, UserJobs client, OrderProject orderProject) {
        this.id = id;
        this.employee = employee;
        this.client = client;
        this.orderProject = orderProject;
    }

    public ApllyJob() {
    }

    public UUID getId() {   
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserJobs getEmployee() {
        return employee;
    }

    public void setEmployee(UserJobs employee) {
        this.employee = employee;
    }

    public UserJobs getClient() {
        return client;
    }

    public void setClient(UserJobs client) {
        this.client = client;
    }

    public OrderProject getOrderProject() {
        return orderProject;
    }

    public void setOrderProject(OrderProject orderProject) {
        this.orderProject = orderProject;
    }
}
