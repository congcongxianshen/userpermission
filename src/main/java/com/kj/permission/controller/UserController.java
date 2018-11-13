package com.kj.permission.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
