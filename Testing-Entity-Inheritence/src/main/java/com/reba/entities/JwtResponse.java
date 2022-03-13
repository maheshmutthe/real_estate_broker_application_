package com.reba.entities;

import java.io.Serializable;

import com.reba.pojo.UserPojo;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private UserPojo user;

	public JwtResponse(String jwttoken, UserPojo user) {
		this.jwttoken = jwttoken;
		this.user = user;
	}

	public String getToken() {
		return this.jwttoken;
	}
	
	public UserPojo getUser() {
		return this.user;
	}
}