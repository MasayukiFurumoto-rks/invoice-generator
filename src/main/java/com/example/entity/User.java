package com.example.entity;

import lombok.Data;

@Data
public class User {
	private Integer id;
	private String name;
	private String email;
	private String password;
	private boolean isAdministrator;
	private boolean deleted;
	private Integer userCompaniesId;
	private String belongs;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdministrator() {
		return isAdministrator;
	}

	public void setAdministrator(boolean isAdministrator) {
		this.isAdministrator = isAdministrator;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Integer getUserCompaniesId() {
		return userCompaniesId;
	}

	public void setUserCompaniesId(Integer userCompaniesId) {
		this.userCompaniesId = userCompaniesId;
	}

	public String getBelongs() {
		return belongs;
	}

	public void setBelongs(String belongs) {
		this.belongs = belongs;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", isAdministrator=" + isAdministrator + ", deleted=" + deleted + ", userCompaniesId="
				+ userCompaniesId + ", belongs=" + belongs + "]";
	}

}
