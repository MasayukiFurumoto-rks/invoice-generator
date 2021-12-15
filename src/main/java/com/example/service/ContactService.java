package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ContactDao;
import com.example.entity.Contact;

@Service
@Transactional
public class ContactService {

	@Autowired
	ContactDao contactDao;
	
	 public Contact findById(Integer id,Integer companyId) {
		 return this.contactDao.findById(id,companyId);
	 }
	 
	 public List<Contact> findByCompanyId(Integer companyId){
		 return this.contactDao.findByCompanyId(companyId);
	 }
	 
	 public int updateContact(Contact contact) {
		 return this.contactDao.updateContact(contact);
	 }
	 
	 public int insertContact(Contact contact) {
		 return this.contactDao.insertContact(contact);
	 }
}

