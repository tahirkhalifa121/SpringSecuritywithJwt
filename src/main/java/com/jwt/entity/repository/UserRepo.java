package com.jwt.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.entity.User;

public  interface UserRepo extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}
