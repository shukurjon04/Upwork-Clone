package com.example.onlinejobs.Repository;

import com.example.onlinejobs.Entity.ProfileTable.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<UserProfile, UUID> {
}
