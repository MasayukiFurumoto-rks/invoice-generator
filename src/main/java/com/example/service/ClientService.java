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
	
	 public Client findById(Integer id,Integer companyId) {
		 return this.clientDao.findById(id,companyId);
	 }
	 
	 public List<Client> findByCompanyId(Integer companyId){
		 return this.clientDao.findByCompanyId(companyId);
	 }
	 
	 public int updateClient(Client client) {
		 return this.clientDao.updateClient(client);
	 }
	 
	 public int insertClient(Client client) {
		 return this.clientDao.insertClient(client);
	 }
}

