package com.kj.permission.dao;

import java.util.List;

import com.kj.permission.bean.Role;

public interface RoleDao {

	List<Role> getAllRole();

	List<Role> getAllRoleById(Integer id);

}
