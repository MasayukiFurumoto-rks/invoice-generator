package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.entity.TradingItem;

@Mapper
public interface TradingItemDao {
	// 1件検索
	TradingItem findById(@Param("id") Integer id, @Param("companyId") Integer companyId);

	// 全件検索
	List<TradingItem> findByCompanyId(Integer companyId);

	// ユーザーテーブルにデータを挿入
	int insertTradingItem(TradingItem tradingItem);

	// ユーザーテーブルにあるアイテムデータを更新
	int updateTradingItem(TradingItem tradingItem);

}
