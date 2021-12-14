package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.entity.Client;
import com.example.entity.Item;

@Mapper
public interface ClientDao {
	// 1件検索
	Client findById(@Param("id") Integer id, @Param("companyId") Integer companyId);

	// 全件検索
	List<Client> findByCompanyId(Integer companyId);

	// ユーザーテーブルにデータを挿入
	int insertClient(Client client);

	// ユーザーテーブルにあるアイテムデータを更新
	int updateClient(Client client);

}
