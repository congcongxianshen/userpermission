package com.kj.permission.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kj.permission.bean.Permission;
import com.kj.permission.bean.User;
import com.kj.permission.service.PermissionService;
import com.kj.permission.service.RoleService;
import com.kj.permission.util.Page;
import com.kj.permission.util.ResultVO;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	
	@ResponseBody
	@RequestMapping("/doAssign")
	public Object doAssign(Integer roleid,Integer[] permissionids){
		ResultVO resultVO = new ResultVO();
		
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("roleid", roleid);
			map.put("permissionids", permissionids);
			
			permissionService.insertRolePermission(map);
			resultVO.setSuccess(true);
		} catch (Exception e) {
			resultVO.setSuccess(false);
			e.printStackTrace();
		}
		return resultVO;

	}
	
	@ResponseBody
	@RequestMapping("/loadData")
	public Object loadData(Integer roleid){
		
		List<Integer> permissionids = permissionService.getPermissionByRoleid(roleid);
		
		
		List<Permission> arrayList = new ArrayList<Permission>();
		List<Permission> allPermission = permissionService.queryAll();
		Map<Integer, Permission> map = new HashMap<Integer,Permission>();
		for (Permission permission : allPermission) {
			
			if(permissionids.contains(permission.getId())){
				permission.setChecked(true);
			}
			map.put(permission.getId(), permission);
		}
		//对象的引用只有一份儿
		for (Permission permission : allPermission) {
			if(permission.getPid()==0){
				arrayList.add(permission);
			}else{
				Permission parent = map.get(permission.getPid());
				parent.getChildren().add(permission);
			}
		}
		return arrayList;
	}
	
	@RequestMapping("/assignPermission")
	public String toassignPermission(Integer roleid,Model model){
		model.addAttribute("roleid", roleid);
		return "role/assign";
	}
	
	@RequestMapping("/index")
	public String indexjsp(){
		
		return "role/index";
	}
	
	@ResponseBody
	@RequestMapping("/getPageInfo")
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
			Integer total = roleService.getRecordByCondition(map);
			if(total % pagesize==0){
				pagenototal=total/pagesize;
			}else{
				pagenototal=(total/pagesize)+1;
			}
		
			List<User> roles = roleService.getAllRoleByCondition(map);
			
			page.setTotal(total);
			page.setPagesize(pagesize);
			page.setPagenototal(pagenototal);
			page.setDatas(roles);
			resultVO.setData(page);
			resultVO.setSuccess(true);
		} catch (Exception e) {
			resultVO.setSuccess(false);
			e.printStackTrace();
		}
		return resultVO;
	
	}
}
