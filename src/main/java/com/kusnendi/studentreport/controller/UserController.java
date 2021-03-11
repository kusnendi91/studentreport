package com.kusnendi.studentreport.controller;


import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusnendi.studentreport.exception.AlreadyLoggedInException;
import com.kusnendi.studentreport.exception.DataNotFoundException;
import com.kusnendi.studentreport.exception.UnauthorizedException;
import com.kusnendi.studentreport.model.User;
import com.kusnendi.studentreport.pojo.BasePojo;
import com.kusnendi.studentreport.pojo.ResponsePojo;
import com.kusnendi.studentreport.pojo.UserChangeName;
import com.kusnendi.studentreport.pojo.UserChangePwd;
import com.kusnendi.studentreport.pojo.UserLoginPojo;
import com.kusnendi.studentreport.pojo.UserPojo;
import com.kusnendi.studentreport.repository.UserRepository;
import com.kusnendi.studentreport.util.LoginUtil;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@PostMapping("/users/register")
	public ResponsePojo Register(@RequestBody UserPojo req) {
		User userex = userRepo.findByUsername(req.getUsername());
		if(userex != null) {
			return new ResponsePojo("500","Username already taken");
		}else {
			User user = new User();
			user.setUsername(req.getUsername());
			user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt()));
			userRepo.save(user);
			return new ResponsePojo("200","OK");
		}


	}
	
	@PostMapping("/users/login")
	public UserLoginPojo Login(@RequestBody UserPojo req) {
		User user = userRepo.findByUsername(req.getUsername());
		if(user == null) {
			throw new UnauthorizedException(req.getUsername());
		}else if(user.getToken() != null) {
			throw new AlreadyLoggedInException(req.getUsername());
		}
		
		if(LoginUtil.checkPwd(req.getPassword(), user.getPassword())) {
			user.setToken(UUID.randomUUID().toString());
			userRepo.save(user);
			return new UserLoginPojo(user.getUsername(),user.getToken());
		}else {
			throw new UnauthorizedException(req.getUsername());
		}
		
	}
	
	@PostMapping("/users/logout")
	public ResponsePojo Logout(@RequestBody BasePojo req) {
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			user.setToken(null);
			userRepo.save(user);
			return new ResponsePojo("200","OK");
		}
	}
	
	@PostMapping("/users/forgotpwd")
	public ResponsePojo changePassword(@RequestBody UserChangePwd req) {
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			user.setPassword(BCrypt.hashpw(req.getNewPassword(), BCrypt.gensalt()));
			userRepo.save(user);
			return new ResponsePojo("200","OK");
		}
	}
	
	@PostMapping("/users/changename")
	public ResponsePojo changeUsername(@RequestBody UserChangeName req) {
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			user.setUsername(req.getNewUsername());
			userRepo.save(user);
			return new ResponsePojo("200","OK");
		}
	}
	
	@DeleteMapping("/users/delete/{id}")
	public ResponsePojo deleteUser(@PathVariable(value = "id") Long id,@RequestBody BasePojo req){
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			User userdel = userRepo.findById(id).orElseThrow(()-> new DataNotFoundException(String.valueOf(id), "User"));
			userRepo.delete(userdel);
			return new ResponsePojo("200","OK");


		}
	}
	
	
	
	
	
	
	

}
