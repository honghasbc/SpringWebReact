package com.heb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heb.dao.UserDao;
import com.heb.model.User;
import com.heb.utils.Constants;

@Service(Constants.USER_SERVICE)
public class UserServiceImpl {

	@Autowired
	private UserDao userDao;
	
	User findByName(String name) {
		return userDao.findByName(name);
	}
	
	public List<User> findAll(){
		return userDao.findAll();
	}

}
