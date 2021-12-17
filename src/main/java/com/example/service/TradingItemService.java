package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.TradingItemDao;
import com.example.entity.TradingItem;

@Service
@Transactional
public class TradingItemService {

	@Autowired
	TradingItemDao tradingItemDao;
	
	 public TradingItem findById(Integer id,Integer companyId) {
		 return this.tradingItemDao.findById(id,companyId);
	 }
	 
	 public List<TradingItem> findByCompanyId(Integer companyId){
		 return this.tradingItemDao.findByCompanyId(companyId);
	 }
	 
	 public int updateTradingItem(TradingItem tradingItem) {
		 return this.tradingItemDao.updateTradingItem(tradingItem);
	 }
	 
	 public int insertTradingItem(TradingItem tradingItem) {
		 return this.tradingItemDao.insertTradingItem(tradingItem);
	 }
	 
	 public int deleteTradingItem(TradingItem tradingItem) {
		 return this.tradingItemDao.deleteTradingItem(tradingItem);
	 }
}

