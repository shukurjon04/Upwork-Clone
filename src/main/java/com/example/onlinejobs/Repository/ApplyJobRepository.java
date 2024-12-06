package com.example.onlinejobs.Repository;

import com.example.onlinejobs.Entity.Applying.ApllyJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApplyJobRepository extends JpaRepository<ApllyJob, UUID> {
}
