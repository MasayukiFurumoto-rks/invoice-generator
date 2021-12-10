package com.example.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.UserDao;
import com.example.entity.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	 public User findById(Integer id) {
		 User user = new User();
		 user.setId(id);
		 return this.userDao.findById(user);
	 }
	 
	 public List<User> findByEmail(String email){
		 User user = new User();
		 user.setEmail(email);
		 return this.userDao.findByEmailAsJoin(user);
	 }
	 
	 public User signIn(String email, String password) {
			List<User> user = this.findByEmail(email);
			if(user == null || user.size()==0) {
				return null;
			}
			
			if (!passwordEncoder.matches(password, user.get(0).getPassword())) {
				return null;
			}
			
			return user.get(0);
		}
	 
	 public User insert(User user) {
		 user.setPassword(passwordEncoder.encode(user.getPassword()));
		 userDao.insertUser(user);
		System.out.println("inserted user: "+ user); 
		
		if(Objects.isNull(user)) {
			return null;
		}
		
		return user;
		
		
	 }
	 
}
