package com.example.onlinejobs.Dtos.Announced;

import java.util.UUID;

public class giveProject {
    private UUID projectId;
    private UUID employeeId;

    public giveProject(UUID projectId, UUID employeeId) {
        this.projectId = projectId;
        this.employeeId = employeeId;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }
}
