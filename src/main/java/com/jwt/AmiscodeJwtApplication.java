package com.jwt;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jwt.entity.Role;
import com.jwt.entity.User;
import com.jwt.entity.service.UserService;

@SpringBootApplication
public class AmiscodeJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmiscodeJwtApplication.class, args);
	}

	
	@Bean
	CommandLineRunner run(UserService userService)
	{
		return args ->{
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
			
			
			userService.saveUser(new User(null, "john Travolta", "john", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "will smith", "will", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "jim carry", "jim", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "john musha", "musha", "1234", new ArrayList<>()));
			
			
			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("will", "ROLE_MANAGER");
			userService.addRoleToUser("jim", "ROLE_ADMIN");
			userService.addRoleToUser("musha", "ROLE_SUPER_ADMIN");
		};
	}
}
