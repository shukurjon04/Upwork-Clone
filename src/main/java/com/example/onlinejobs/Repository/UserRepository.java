package com.example.onlinejobs.Repository;

import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import com.example.onlinejobs.Entity.RoleTable.RoleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserJobs, UUID> {

    boolean existsByUsername(String username);

    Optional<UserJobs> findByUsername(String username);

    boolean existsByEmail(String email);

    Optional<UserJobs> findByEmail(String email);

    @Query("select u from UserJobs u join u.role r where r.id = ?1")
    Page<UserJobs> findUserJobsByRole(int id, Pageable pageable);
    @Query("select u from UserJobs u join u.role r where r.roleName = ?1 and u.id = ?2")
    Optional<UserJobs> findUserJobsByRole(RoleName name , UUID id);
}
