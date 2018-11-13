package com.kj.permission.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kj.permission.bean.User;
import com.kj.permission.service.UserService;
import com.kj.permission.util.ResultVO;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
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
