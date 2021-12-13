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
	
	 public Item findById(Integer id) {
		 Item item = new Item();
		 item.setId(id);
		 return this.itemDao.findById(item);
	 }
	 
	 public List<Item> findAll(){
		 return this.itemDao.findAll();
	 }
	 
	 public int updateItem(Item item) {
		 return this.itemDao.updateItem(item);
	 }
	 
}
