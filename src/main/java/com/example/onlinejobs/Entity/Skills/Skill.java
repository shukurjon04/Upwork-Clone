package com.example.onlinejobs.Entity.Skills;

import com.example.onlinejobs.Entity.ControlFunctionTable.AuditTable;
import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Skill extends AuditTable {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<UserJobs> employee;

    public Skill(UUID id, String name, List<UserJobs> employee) {
        this.id = id;
        this.name = name;
        this.employee = employee;
    }

    public Skill() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserJobs> getEmployee() {
        return employee;
    }

    public void setEmployee(List<UserJobs> employee) {
        this.employee = employee;
    }
}
