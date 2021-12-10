package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ClientDao;
import com.example.entity.Client;

@Service
@Transactional
public class ClientService {
	
	@Autowired
	ClientDao clientDao;
	
	 public Client findById(Integer id) {
		 Client client = new Client();
		 client.setId(id);
		 return this.clientDao.findById(client);
	 }
	 
	 public List<Client> findAll(){
		 return this.clientDao.findAll();
	 }
	 
	 
}
