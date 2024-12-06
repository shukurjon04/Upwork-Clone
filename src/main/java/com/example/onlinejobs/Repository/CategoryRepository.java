package com.example.onlinejobs.Repository;

import com.example.onlinejobs.Entity.Category.Categories;
import com.example.onlinejobs.Entity.Category.Enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Categories, UUID> {

    Optional<Categories> findByName(CategoryType name);
}
