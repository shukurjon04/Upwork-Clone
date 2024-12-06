package com.example.onlinejobs.Repository;

import com.example.onlinejobs.Entity.Orders.OrderProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProjectRepository extends JpaRepository<OrderProject, UUID> {
}
