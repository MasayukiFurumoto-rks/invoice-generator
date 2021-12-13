package com.example.entity;

import java.util.List;

import lombok.Data;

@Data
public class UserDepartment {
	private Integer id;
	private String name;
	private boolean deleted;
	private Integer companyId;
	private UserCompany company;
	private List<User> userList;

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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public UserCompany getCompany() {
		return company;
	}

	public void setCompany(UserCompany company) {
		this.company = company;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "UserDepartment [id=" + id + ", name=" + name + ", deleted=" + deleted + ", company=" + company
				+ ", userList=" + userList + "]";
	}

}
