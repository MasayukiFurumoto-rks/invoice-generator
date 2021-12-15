package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.entity.Contact;
import com.example.entity.Item;

@Mapper
public interface ContactDao {
	// 1件検索
	Contact findById(@Param("id") Integer id, @Param("companyId") Integer companyId);

	// 全件検索
	List<Contact> findByCompanyId(Integer companyId);

	// ユーザーテーブルにデータを挿入
	int insertContact(Contact contact);

	// ユーザーテーブルにあるアイテムデータを更新
	int updateContact(Contact contact);

}
