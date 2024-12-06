package com.example.onlinejobs.Repository;

import com.example.onlinejobs.Entity.RoleTable.Role;
import com.example.onlinejobs.Entity.RoleTable.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleName roleName);

}
