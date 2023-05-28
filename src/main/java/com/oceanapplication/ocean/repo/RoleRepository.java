package com.oceanapplication.ocean.repo;

import com.oceanapplication.ocean.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Boolean existsByName(String name);
    Role findByName(String name);
}
