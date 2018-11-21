package com.kj.permission.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kj.permission.bean.Permission;
import com.kj.permission.bean.User;
import com.kj.permission.service.PermissionService;
import com.kj.permission.service.RoleService;
import com.kj.permission.service.UserService;
import com.kj.permission.util.ResultVO;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping("/error")
	public String error() {
		return "error";
	} 
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate(); //销毁session
		return "redirect:login"; //重定向到登录页面
	}
	
	@RequestMapping("/main")
	public String tomainjsp(){
		return "main";
	}
	
	@RequestMapping("/login")
	public String tologinjsp(){
		return "login";
	}
	
	@ResponseBody
	@RequestMapping("/doLogin")
	public Object toLogin(User user,HttpSession session){   
		
		ResultVO resultVO = new ResultVO();
		try {
			user = userService.getUserByAcctAndPswd(user);
			if(user!=null){   //用户存在，存入session中
				session.setAttribute("user", user); 
				
				
				//获得用户权限
				List<Permission> permissionList = new ArrayList<Permission>();
				List<Permission> permissions = permissionService.getUserPermission(user);
				Map<Integer, Permission> map = new HashMap<Integer,Permission>();
				Set<String> uriSet = new HashSet<String>();
				for (Permission permission : permissions) {
					if ( permission.getUrl() != null && !"".equals(permission.getUrl()) ) {
						uriSet.add(session.getServletContext().getContextPath()+permission.getUrl());
					}
					map.put(permission.getId(), permission);
				}
				session.setAttribute("authUriSet", uriSet); 
				Permission root = null;
				for (Permission permission : permissions) {
					if(permission.getPid()==0){
						root = permission;
					}else{
						Permission parent = map.get(permission.getPid());
						parent.getChildren().add(permission);
					}
				}
				session.setAttribute("rootPermission", root); 
				
				resultVO.setSuccess(true);
			}else{
				resultVO.setMsg("用户名或者密码不正确");
				resultVO.setSuccess(false);
			}
			
		} catch (Exception e) {
			resultVO.setMsg("后台异常");
			resultVO.setSuccess(false);
			e.printStackTrace();
		}
		return resultVO;
	}
}
