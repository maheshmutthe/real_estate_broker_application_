package com.reba.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "user")
@Table(name = "user_reba_jwt")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("U")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
	@SequenceGenerator(sequenceName = "user_reba_seq", allocationSize = 1, name = "user_id_seq")
	private int userId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_password")
	@JsonIgnore
	private String userPassword;
	
	@Column(name = "user_role")
	private String userRole;
	
	@Column(name = "user_mobile")
	private String userMobile;
	
	@Column(name = "user_email")
	private String userEmail;
	
	@Column(name = "user_city")
	private String userCity;
	
	public User() {
		
	}

	public User(int userId, String userPassword, String userRole, String userMobile, String userEmail,
			String userCity) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userRole = userRole;
		this.userMobile = userMobile;
		this.userEmail = userEmail;
		this.userCity = userCity;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	
	
}
