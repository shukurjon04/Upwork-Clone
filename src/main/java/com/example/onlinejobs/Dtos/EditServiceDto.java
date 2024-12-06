package com.example.onlinejobs.Dtos;

import java.util.UUID;

public class EditServiceDto extends ServiceDto {

    private UUID serviceId;


    public EditServiceDto(String title, String description, double price, UUID categoryId, UUID employeeId) {
        super(title, description, price, categoryId, employeeId);
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public void setServiceId(UUID serviceId) {
        this.serviceId = serviceId;
    }
}
