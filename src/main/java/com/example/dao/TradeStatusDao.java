package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.TradeStatus;

@Mapper
public interface TradeStatusDao{
	// 全件検索
	List<TradeStatus> findAll();
	
	// keyによる1件検索
	TradeStatus findByKey(Integer key);

}
