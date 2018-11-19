package com.kj.permission.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kj.permission.bean.Role;
import com.kj.permission.bean.User;
import com.kj.permission.service.RoleService;
import com.kj.permission.service.UserService;
import com.kj.permission.util.Page;
import com.kj.permission.util.ResultVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService; 
	
	@ResponseBody
	@RequestMapping("/deleterole")
	public Object deleteroles(Integer[] assignroleid,Integer userid){
		ResultVO resultVO = new ResultVO();
		try {
			HashMap<String, Object> map = new HashMap<String,Object>();
			
			map.put("userid", userid);
			map.put("roleids", assignroleid);
			
			userService.deleteRoleInfo(map);
			resultVO.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultVO.setSuccess(false);
		}
		return resultVO;
	}
	
	@ResponseBody
	@RequestMapping("/addrole")
	public Object addrole(Integer[] noassignroleid,Integer userid){
		ResultVO resultVO = new ResultVO();
		try {
			HashMap<String, Object> map = new HashMap<String,Object>();
			
			map.put("userid", userid);
			map.put("roleids", noassignroleid);
			
			userService.addRoleInfo(map);
			resultVO.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultVO.setSuccess(false);
		}
		return resultVO;
	}
	
	@ResponseBody
	@RequestMapping("/doassign")
	public Object doassign(Integer id){
		ResultVO resultVO = new ResultVO();
		try {
			
			List<Role> allroles = roleService.getAllRole();
			
			List<Role> haveRole = roleService.getAllRoleById(id);
			
			List<Role> assignRole = new ArrayList<Role>();
			List<Role> noassignRole = new ArrayList<Role>();
			
			
			for (Role role : allroles) {
				if(haveRole.contains(role)){
					assignRole.add(role);
				}else{
					noassignRole.add(role);
				}
			}
			
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("assignRole", assignRole);
			map.put("noassignRole", noassignRole);
			
			resultVO.setData(map);
			resultVO.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultVO.setSuccess(false);
		}
		return resultVO;
	}
	
	@RequestMapping("/toassign")
	public String toassign(Integer id,Model model){
		model.addAttribute("userid", id);
		return "user/assign";
	}
	
	@ResponseBody
	@RequestMapping("/removes")
	public Object removeUsers(Integer[] userids){
		ResultVO resultVO = new ResultVO();
		try {
			List<Integer> ids = Arrays.asList(userids);
			userService.removeUsers(ids);
			resultVO.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultVO.setSuccess(false);
		}
		return resultVO;
	}
	
	@ResponseBody
	@RequestMapping("/remove")
	public Object removeUser(Integer id){
		ResultVO resultVO = new ResultVO();
		try {
			userService.removeUser(id);
			resultVO.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultVO.setSuccess(false);
		}
		return resultVO;
	}
	
	
	@ResponseBody
	@RequestMapping("/doedit")
	public Object doedit(User user){
		ResultVO resultVO = new ResultVO();
		try {
			userService.updateUer(user);
			resultVO.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultVO.setSuccess(false);
		}
		return resultVO;
	}
	
	
	@RequestMapping("/toedit")
	public String toedit(Integer id,Model model){
		
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "user/edit";
	}
	
	@RequestMapping("/doAdd")
	@ResponseBody
	public Object doAdd(User user){
		ResultVO resultVO = new ResultVO();
		try {
			user.setCreatetime(new Date());
			user.setUserpswd("123");
			userService.insertUser(user);
			resultVO.setSuccess(true);
		} catch (Exception e) {
			resultVO.setSuccess(false);
		}
		return resultVO;
	}
	
	@RequestMapping("/toadd")
	public String toAdd(){
		return "user/add";
	}
	
	@RequestMapping("/getPageInfo")
	@ResponseBody
	public Object getPageInfo(Integer pageno,Integer pagesize,String queryText){
		ResultVO resultVO = new ResultVO();
		Page<User> page = new Page<User>();
		
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("start", (pageno-1)*pagesize);
			map.put("pagesize", pagesize);
			map.put("loginacct", queryText);
			Integer pagenototal = 0;
			
			//查询总记录数
			Integer total = userService.getRecordByCondition(map);
			if(total % pagesize==0){
				pagenototal=total/pagesize;
			}else{
				pagenototal=(total/pagesize)+1;
			}
		
			List<User> users = userService.getAllUserByCondition(map);
			
			page.setTotal(total);
			page.setPagesize(pagesize);
			page.setPagenototal(pagenototal);
			page.setDatas(users);
			resultVO.setData(page);
			resultVO.setSuccess(true);
		} catch (Exception e) {
			resultVO.setSuccess(false);
			e.printStackTrace();
		}
		return resultVO;
	}
	
	
	@RequestMapping("/index")
	public String goUserIndexJsp(){
		//查询所有user
		//List<User> users = userService.getAllUser();
		
		return "user/index";
	}
	
	@RequestMapping("/index2")
	public String goUserIndexJsp2(){
		//查询所有user
		//List<User> users = userService.getAllUser();
		System.out.println("这是我的分支代码书写branch");
		System.out.println("这是我的分支代码书写branch第二次提交代码");
		System.out.println("这是我的分支代码书写branch第三次提交代码");
		return "user/index2";
	}
}
