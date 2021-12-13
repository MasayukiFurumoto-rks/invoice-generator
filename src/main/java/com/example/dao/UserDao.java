package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.User;

@Mapper
public interface UserDao{
	// 1件検索
	User findById(User user);
	
	//　メールアドレスで検索
	List<User> findByEmail(User user);

	//　企業IDで検索
	List<User> findByCompanyId(Integer companyId);
	
	
	// 全件検索
	List<User> findAll();
	
	// ユーザーテーブルにデータを挿入
	int insertUser(User user);

}
