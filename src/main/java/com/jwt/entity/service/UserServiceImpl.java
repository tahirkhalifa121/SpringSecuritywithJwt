package com.jwt.entity.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.entity.Role;
import com.jwt.entity.User;
import com.jwt.entity.repository.RoleRepo;
import com.jwt.entity.repository.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService,UserDetailsService {

	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepo.findByUsername(username);
		
		if(user == null)
		{
			log.error("User Not Found in Data base");
			throw new UsernameNotFoundException("User Not Found in Data base");
		}
		else
		{
				log.info("User Found in the database {}",username);
		}
		
		Collection<SimpleGrantedAuthority>authorities=new ArrayList<SimpleGrantedAuthority>();
		user.getRoles().forEach(role ->{
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
	}
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		log.info("saving new User {} to thr database",user.getName());
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		log.info("saving new Role {} to thr database",role.getName());
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		// TODO Auto-generated method stub
		
		log.info("Adding Role{} to User {} ",roleName,username);
		User user=userRepo.findByUsername(username);
		Role role=roleRepo.findByName(roleName);
		user.getRoles().add(role);
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		log.info("Fetching User {}",username);
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		log.info("Fetching All User");
		return userRepo.findAll();
	}

	

}
