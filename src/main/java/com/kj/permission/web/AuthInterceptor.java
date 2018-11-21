package com.kj.permission.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kj.permission.bean.Permission;
import com.kj.permission.service.PermissionService;

public class AuthInterceptor implements HandlerInterceptor {
	
	@Autowired
	private PermissionService permissionService;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		// 获取用户的请求地址
		String requestURI = request.getRequestURI(); 
		
		HttpSession session = request.getSession();
		String path = session.getServletContext().getContextPath();
		//判断当前请求是否需要验证  url集合
		List<Permission> allPermission = permissionService.queryAll();
		
		Set<String> hashSet = new HashSet<String>();
		for (Permission permission : allPermission) {
			if(permission.getUrl()!=null && !permission.getUrl().equals("")){
				hashSet.add(path+permission.getUrl());
			}
		}
		
		if(hashSet.contains(requestURI)){  //需要校验
			Set<String> authUriSet = (Set<String>)request.getSession().getAttribute("authUriSet");
			if(authUriSet.contains(requestURI)){
				return true;
			}else{
				response.sendRedirect(path + "/error");
				return false;
			}
			
		}else{
			return true;
		}
		
	}

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}





}
