package com.example.onlinejobs.Repository;

import com.example.onlinejobs.Entity.Services.Servise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ServiceRepository extends JpaRepository<Servise, UUID> {
}
