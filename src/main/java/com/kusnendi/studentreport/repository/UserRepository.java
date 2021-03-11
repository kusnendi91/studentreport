package com.kusnendi.studentreport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kusnendi.studentreport.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

	public List<User> findByUsernameContaining(String username);
	public User findByUsername(String username);
	public User findByToken(String token);
}
