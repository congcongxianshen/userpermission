package com.kj.permission.service;

import java.util.List;
import java.util.Map;

import com.kj.permission.bean.User;

public interface UserService {

	List<User> getAllUser();

	User getUserByAcctAndPswd(User user);

	Integer getRecordByCondition(Map<String, Object> map);

	List<User> getAllUserByCondition(Map<String, Object> map);

	void insertUser(User user);

	User getUserById(Integer id);

	void updateUer(User user);

	void removeUser(Integer id);

	

}
