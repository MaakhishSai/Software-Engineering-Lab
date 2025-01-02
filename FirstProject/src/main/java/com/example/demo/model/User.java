package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// here on deleting userId 4 and creating a new user that user should be alloted 4
	// we are doing that manually
	// so remove this generation
	private long id;
	
	@Column(name = "name")
	private String name;
	
	
	@Column(name = "email")
	private String email;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
