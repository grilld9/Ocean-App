package com.oceanapplication.ocean.repo;

import com.oceanapplication.ocean.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
     Optional<Role> findByName(Role name);
}
