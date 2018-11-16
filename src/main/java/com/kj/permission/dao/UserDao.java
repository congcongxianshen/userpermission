package com.kj.permission.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.kj.permission.bean.User;

public interface UserDao {

	@Select("select * from t_user")
	List<User> getAllUser();

	@Select("select * from t_user where loginacct=#{loginacct} and userpswd=#{userpswd}")
	User getUserByAcctAndPswd(User user);

	Integer getRecordByCondition(Map<String, Object> map);

	List<User> getAllUserByCondition(Map<String, Object> map);
	
	void insertUser(User user);

	@Select("select * from t_user where id=#{id}")
	User getUserById(Integer id);

	void updateUer(User user);

	void removeUser(Integer id);

	void removeUsers(List<Integer> ids);

	void addRoleInfo(HashMap<String, Object> map);

	void deleteRoleInfo(HashMap<String, Object> map);

}
