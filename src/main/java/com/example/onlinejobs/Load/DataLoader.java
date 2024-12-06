package com.example.onlinejobs.Load;

import com.example.onlinejobs.Entity.Category.Categories;
import com.example.onlinejobs.Entity.Category.Enums.CategoryType;
import com.example.onlinejobs.Entity.RoleTable.Role;
import com.example.onlinejobs.Entity.RoleTable.RoleName;
import com.example.onlinejobs.Repository.CategoryRepository;
import com.example.onlinejobs.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.sql.init.mode}")
    private String initMode;
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public DataLoader(RoleRepository roleRepository,  CategoryRepository categoryRepository) {
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        if (initMode.equals("always")) {
            Role admin = Role.builder()
                    .roleName(RoleName.Admin)
                    .build();
            Role client = Role.builder()
                    .roleName(RoleName.Client)
                    .build();
            Role employee = Role.builder()
                    .roleName(RoleName.Employee)
                    .build();
            roleRepository.saveAll(List.of(admin, client, employee));

            for (CategoryType categoryType : CategoryType.values()) {
                categoryRepository.save(new Categories(null, categoryType));
            }
        }
    }
}
