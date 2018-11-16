package com.kj.permission.controller;

import java.security.interfaces.RSAKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kj.permission.bean.User;
import com.kj.permission.service.UserService;
import com.kj.permission.util.Page;
import com.kj.permission.util.ResultVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
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
}
