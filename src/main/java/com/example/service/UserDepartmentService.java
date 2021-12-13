package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.UserDepartmentDao;
import com.example.entity.UserDepartment;

@Service
@Transactional
public class UserDepartmentService {
	
	@Autowired
	UserDepartmentDao userDepartmentDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	 public UserDepartment findById(Integer id) {
		 UserDepartment userDepartment = new UserDepartment();
		 userDepartment.setId(id);
		 return this.userDepartmentDao.findById(userDepartment);
	 }
	 
	 public List<UserDepartment> findByCompanyId(Integer companyId){
		 return this.userDepartmentDao.findByCompanyId(companyId);
	 }
	 
		
	 
}
