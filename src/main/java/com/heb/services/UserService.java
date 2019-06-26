package com.heb.services;

import java.util.List;

import com.heb.model.User;

public interface UserService {
	
	User findByName(String name);
	List<User> findAll();

}
