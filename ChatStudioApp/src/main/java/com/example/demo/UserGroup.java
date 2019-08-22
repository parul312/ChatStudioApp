package com.example.demo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "user_group")
@Entity
public class UserGroup {

	@EmbeddedId
	private UserGroupId id;

	public UserGroupId getId() {
		return id;
	}

	public void setId(UserGroupId id) {
		this.id = id;
	}

	private String adminInd;

	public String getAdminInd() {
		return adminInd;
	}

	public void setAdminInd(String adminInd) {
		this.adminInd = adminInd;
	}

}
