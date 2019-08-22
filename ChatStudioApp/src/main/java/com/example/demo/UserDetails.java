package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserDetails {

	@Id
	@Column(name = "username")
	private String username;
	private String name;

	private String password;
	private int age;
	private String gender;
	private String email_address;
	private String profession;

	public UserDetails(String username, String name, String password, int age, String gender,
			String email_address, String profession) {
		super();
		this.username = username;
		this.name = name;
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.email_address = email_address;
		this.profession = profession;
	}

	public UserDetails() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

}
