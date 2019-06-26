package com.heb.dao;

import java.util.List;

import com.heb.model.User;

public interface UserDao {

	User findByName(String name);
	
	List<User> findAll();

}