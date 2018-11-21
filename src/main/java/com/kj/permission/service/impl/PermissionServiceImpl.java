package com.kj.permission.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kj.permission.bean.Permission;
import com.kj.permission.bean.User;
import com.kj.permission.dao.PermissionDao;
import com.kj.permission.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionDao permissionDao;

	public List<Permission> queryChildrenByParentId(Integer id) {
		
		return permissionDao.queryChildrenByParentId(id);
	}

	public List<Permission> queryAll() {
		return permissionDao.queryAll();
	}

	public void insertPermission(Permission permission) {
		permissionDao.insertPermission(permission);
	}

	public Permission getPermissionById(Integer id) {
		// TODO Auto-generated method stub
		return permissionDao.getPermissionById(id);
	}

	public void updatePermission(Permission permission) {
		permissionDao.updatePermission(permission);
		
	}

	public void deletePermission(Integer id) {
		permissionDao.deletePermission(id);
	}

	public List<Integer> getPermissionByRoleid(Integer roleid) {
		return permissionDao.getPermissionByRoleid(roleid);
	}

	public void insertRolePermission(Map<String, Object> map) {
		permissionDao.deleteRolePermission(map);
		permissionDao.insertRolePermission(map);
	}

	public List<Permission> getUserPermission(User user) {
		return permissionDao.getUserPermission(user);
	}

}
