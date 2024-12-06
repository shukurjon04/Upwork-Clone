package com.example.onlinejobs.Repository;

import com.example.onlinejobs.Entity.ProfileTable.ProfilePictures;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfilePicturesRepository extends JpaRepository<ProfilePictures, UUID> {
}
