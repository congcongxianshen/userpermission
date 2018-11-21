package com.kj.permission.service;

import java.util.List;
import java.util.Map;

import com.kj.permission.bean.Permission;
import com.kj.permission.bean.User;

public interface PermissionService {

	List<Permission> queryChildrenByParentId(Integer id);

	List<Permission> queryAll();

	void insertPermission(Permission permission);

	Permission getPermissionById(Integer id);

	void updatePermission(Permission permission);

	void deletePermission(Integer id);

	List<Integer> getPermissionByRoleid(Integer roleid);

	void insertRolePermission(Map<String, Object> map);

	List<Permission> getUserPermission(User user);

}
