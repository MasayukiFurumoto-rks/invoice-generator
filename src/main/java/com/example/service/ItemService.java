package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ItemDao;
import com.example.entity.Item;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	ItemDao itemDao;
	
	 public Item findById(Integer id,Integer companyId) {
		 return this.itemDao.findById(id,companyId);
	 }
	 
	 public List<Item> findByCompanyId(Integer companyId){
		 return this.itemDao.findByCompanyId(companyId);
	 }
	 
	 public int updateItem(Item item) {
		 return this.itemDao.updateItem(item);
	 }
	 
	 public int insertItem(Item item) {
		 return this.itemDao.insertItem(item);
	 }
}
