package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.entity.Trade;

@Mapper
public interface TradeDao {
	// 1件検索
	Trade findById(@Param("id") Integer id, @Param("companyId") Integer companyId);

	// 全件検索
	List<Trade> findByCompanyId(Integer companyId);

	// ユーザーテーブルにデータを挿入
	int insertTrade(Trade trade);

	// ユーザーテーブルにあるアイテムデータを更新
	int updateTrade(Trade trade);

}
