package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.TradeDao;
import com.example.entity.Trade;

@Service
@Transactional
public class TradeService {

	@Autowired
	TradeDao tradeDao;
	
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
}

