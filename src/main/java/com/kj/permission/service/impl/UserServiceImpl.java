package com.kj.permission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kj.permission.bean.User;
import com.kj.permission.dao.UserDao;
import com.kj.permission.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

}
