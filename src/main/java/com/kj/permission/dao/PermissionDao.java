package com.kj.permission.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.kj.permission.bean.Permission;
import com.kj.permission.bean.User;

public interface PermissionDao {

	@Select("select * from t_permission where pid=#{id}")
	List<Permission> queryChildrenByParentId(Integer id);
	
	@Select("select * from t_permission")
	List<Permission> queryAll();

	void insertPermission(Permission permission);
	
	@Select("select * from t_permission where id=#{id}")
	Permission getPermissionById(Integer id);

	void updatePermission(Permission permission);

	void deletePermission(Integer id);

	List<Integer> getPermissionByRoleid(Integer roleid);

	void deleteRolePermission(Map<String, Object> map);

	void insertRolePermission(Map<String, Object> map);

	List<Permission> getUserPermission(User user);

}
