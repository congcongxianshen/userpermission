package com.kj.permission.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kj.permission.bean.Permission;
import com.kj.permission.bean.Role;
import com.kj.permission.bean.User;
import com.kj.permission.dao.RoleDao;
import com.kj.permission.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	public List<Role> getAllRole() {
		
		return roleDao.getAllRole();
	}
	public List<Role> getAllRoleById(Integer id) {
		
		return roleDao.getAllRoleById(id);
	}
	public Integer getRecordByCondition(Map<String, Object> map) {
		
		return roleDao.getRecordByCondition(map);
	}
	public List<User> getAllUserByCondition(Map<String, Object> map) {
		return roleDao.getAllUserByCondition(map);
	}
	public List<User> getAllRoleByCondition(Map<String, Object> map) {
		return roleDao.getAllRoleByCondition(map);
	}
	
	

}
