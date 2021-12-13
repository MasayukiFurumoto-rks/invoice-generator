package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.UserDepartment;

@Mapper
public interface UserDepartmentDao{
	// 1件検索
	UserDepartment findById(UserDepartment userDepartment);
	
	//　企業IDで検索
	List<UserDepartment> findByCompanyId(Integer companyId);

	// 全件検索
	List<UserDepartment> findAll();
	
	// ユーザーテーブルにデータを挿入
	int insertUserDepartment(UserDepartment userDepartment);

}
