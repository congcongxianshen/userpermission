package com.kj.permission.service.impl;

import java.util.List;
import java.util.Map;

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

	public User getUserByAcctAndPswd(User user) {
		
		return userDao.getUserByAcctAndPswd(user);
	}

	public Integer getRecordByCondition(Map<String, Object> map) {
		return userDao.getRecordByCondition(map);
	}

	public List<User> getAllUserByCondition(Map<String, Object> map) {
		return userDao.getAllUserByCondition(map);
	}

	

}
