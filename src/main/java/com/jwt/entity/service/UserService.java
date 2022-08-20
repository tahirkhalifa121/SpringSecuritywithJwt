package com.jwt.entity.service;

import java.util.List;

import com.jwt.entity.Role;
import com.jwt.entity.User;

public interface UserService {

	User saveUser(User user);
	
	Role saveRole(Role role);
	
	void addRoleToUser(String username,String roleName);
	
	User getUser(String username);
	
	List<User> getUsers();
}
