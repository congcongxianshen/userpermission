package com.kj.permission.dao;

import java.util.List;
import java.util.Map;

import com.kj.permission.bean.Permission;
import com.kj.permission.bean.Role;
import com.kj.permission.bean.User;

public interface RoleDao {

	List<Role> getAllRole();

	List<Role> getAllRoleById(Integer id);

	Integer getRecordByCondition(Map<String, Object> map);

	List<User> getAllUserByCondition(Map<String, Object> map);

	List<User> getAllRoleByCondition(Map<String, Object> map);


}
