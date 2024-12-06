package com.example.onlinejobs.Entity.Pay;

import com.example.onlinejobs.Entity.Orders.OrderProject;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Payment {
    @Id
    @GeneratedValue
    private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    private OrderProject orderProject;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private String paymentStatus;
    public Payment(UUID id, OrderProject orderProject, double amount, String paymentStatus) {
        this.id = id;
        this.orderProject = orderProject;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    public Payment() {
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
