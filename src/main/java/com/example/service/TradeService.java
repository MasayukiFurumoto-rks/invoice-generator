package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.TradeDao;
import com.example.dao.TradeStatusDao;
import com.example.entity.Trade;
import com.example.entity.TradeStatus;

@Service
@Transactional
public class TradeService {

	@Autowired
	TradeDao tradeDao;
	
	@Autowired
	TradeStatusDao tradeStatusDao;
	
	 public Trade findById(Integer id,Integer companyId) {
		 return this.tradeDao.findById(id,companyId);
	 }
	 
	 public List<Trade> findByCompanyId(Integer companyId){
		 return this.tradeDao.findByCompanyId(companyId);
	 }
	 
	 
	 public int updateTrade(Trade trade) {
		 return this.tradeDao.updateTrade(trade);
	 }
	 
	 public int insertTrade(Trade trade) {
		 return this.tradeDao.insertTrade(trade);
	 }
	 
	 public List<TradeStatus> getTradeStatusList(){
		 return this.tradeStatusDao.findAll();
	 }
}

