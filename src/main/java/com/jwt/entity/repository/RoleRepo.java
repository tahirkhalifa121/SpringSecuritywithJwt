package com.jwt.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
