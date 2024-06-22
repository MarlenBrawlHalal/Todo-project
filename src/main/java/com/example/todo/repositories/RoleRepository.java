package com.example.todo.repositories;

import com.example.todo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findRoleByName(String name);
}
