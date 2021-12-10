package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.Client;

@Mapper
public interface ClientDao{
	// 1件検索
	Client findById(Client client);
	
	// 全件検索
	List<Client> findAll();
	
	// ユーザーテーブルにデータを挿入
	int insertClient(Client client);

}
