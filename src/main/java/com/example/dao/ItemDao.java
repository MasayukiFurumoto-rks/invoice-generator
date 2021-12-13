package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.entity.Item;

@Mapper
public interface ItemDao{
	// 1件検索
	Item findById(@Param("id") Integer id,@Param("companyId") Integer companyId);
	
	// 全件検索
	List<Item> findByCompanyId(Integer companyId);
	
	// ユーザーテーブルにデータを挿入
	int insertItem(Item item);

	// ユーザーテーブルにあるアイテムデータを更新
	int updateItem(Item item);

}
