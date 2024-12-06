package com.example.onlinejobs.Entity.RoleTable;



public enum RoleName {
    Admin("Admin","work with everything"),
    Client("Client","work with employee"),
    Employee("Employee","work with client"),;

    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    RoleName(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
