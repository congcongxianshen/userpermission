package com.kj.permission.service;

import java.util.List;

import com.kj.permission.bean.Role;

public interface RoleService {

	List<Role> getAllRole();

	List<Role> getAllRoleById(Integer id);

}
