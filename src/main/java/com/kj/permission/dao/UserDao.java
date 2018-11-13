package com.kj.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.kj.permission.bean.User;

public interface UserDao {

	@Select("select * from t_user")
	List<User> getAllUser();

}
