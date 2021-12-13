package com.example.entity;

import java.util.List;

import lombok.Data;

@Data
public class UserCompany {
	private Integer id;
	private String name;
	private boolean deleted;
	private List<UserDepartment> departmentList;

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

	public List<UserDepartment> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<UserDepartment> departmentList) {
		this.departmentList = departmentList;
	}

	@Override
	public String toString() {
		return "UserCompany [id=" + id + ", name=" + name + ", deleted=" + deleted + ", departmentList="
				+ departmentList + "]";
	}

}
