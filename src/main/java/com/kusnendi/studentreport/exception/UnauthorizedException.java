package com.kusnendi.studentreport.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	
	public UnauthorizedException() {
		super("you are not authorized to access this page");
	}
	
	public UnauthorizedException(String username) {
		super(String.format("password for username %s is wrong or not registered", username));
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
}
