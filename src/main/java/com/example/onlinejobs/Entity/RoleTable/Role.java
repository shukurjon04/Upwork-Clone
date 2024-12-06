package com.example.onlinejobs.Entity.RoleTable;

import jakarta.persistence.*;


@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public Role(int id, RoleName roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    // design pattern
    private Role(RoleBuilder builder){
        this.roleName = builder.roleName;
    }
    public static class RoleBuilder {
        private RoleName roleName;
        public RoleBuilder roleName(RoleName roleName){
            this.roleName = roleName;
            return this;
        }
        public Role build(){
            return new Role(this);
        }
    }
    public static RoleBuilder builder() {
        return new RoleBuilder();
    }

}
