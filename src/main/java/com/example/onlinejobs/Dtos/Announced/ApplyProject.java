package com.example.onlinejobs.Dtos.Announced;

import java.util.UUID;

public class ApplyProject {
    private UUID projectId;
    private UUID employeeId;
    private UUID clientId;

    public ApplyProject(UUID projectId, UUID employeeId, UUID clientId) {
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.clientId = clientId;
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

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }
}
