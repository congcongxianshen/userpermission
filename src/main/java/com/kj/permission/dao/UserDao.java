package com.kj.permission.dao;

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

}
