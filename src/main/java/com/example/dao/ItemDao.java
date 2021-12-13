package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.Item;

@Mapper
public interface ItemDao{
	// 1件検索
	Item findById(Item item);
	
	//　メールアドレスで検索
	List<Item> findByEmail(Item item);

	//　Joinした結果をメールアドレスで検索
	List<Item> findByEmailAsJoin(Item item);
	
	// 全件検索
	List<Item> findAll();
	
	// ユーザーテーブルにデータを挿入
	int insertItem(Item item);

	// ユーザーテーブルにあるアイテムデータを更新
	int updateItem(Item item);

}
