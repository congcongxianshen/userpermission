package com.kj.permission.controller;

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
import com.kj.permission.service.PermissionService;
import com.kj.permission.util.ResultVO;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;
	
	
	@ResponseBody
	@RequestMapping("/deleteNode")
	public Object deleteNode(Integer id){
		ResultVO resultVO = new ResultVO();
		try {
			
			permissionService.deletePermission(id);
			
			resultVO.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultVO.setSuccess(false);
		}
		return resultVO;
	}
	
	@ResponseBody
	@RequestMapping("/doEdit")
	public Object doEdit(Permission permission){
		ResultVO resultVO = new ResultVO();
		try {
			
			permissionService.updatePermission(permission);
			
			resultVO.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultVO.setSuccess(false);
		}
		return resultVO;
	}
	
	@RequestMapping("/editNode")
	public String editNode(Integer id,Model model){
		
		Permission permission = permissionService.getPermissionById(id);
		model.addAttribute("permission", permission);
		return "permission/edit";
	}
	
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(Permission permission){
		ResultVO resultVO = new ResultVO();
		try {
			
			permissionService.insertPermission(permission);
			resultVO.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultVO.setSuccess(false);
		}
		return resultVO;
	}
	
	@RequestMapping("/addNode")
	public String addNode(Integer id,Model model){
		model.addAttribute("id", id);
		return "permission/add";
	}
	
	@RequestMapping("/index")
	public String indexjsp(){
		return "permission/index";
	}
	
	@ResponseBody
	@RequestMapping("/loadData")
	public Object loadData(){
		
		/*List<Permission> arrayList = null;
		try {
			arrayList = new ArrayList<Permission>();
			
			Permission root = new Permission();
			root.setName("父节点");
			
			Permission children = new Permission();
			children.setName("子节点");
			
			root.getChildren().add(children);
			
			arrayList.add(root);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return arrayList;*/
		/*//1，递归查询
		Permission parent = new Permission();
		parent.setId(0);
		getPermission(parent);
		return parent.getChildren();*/
		
		/*//2,for循环查询一次，程序内部处理
		List<Permission> arrayList = new ArrayList<Permission>();
		List<Permission> allPermission = permissionService.queryAll();
		
		for (Permission permission : allPermission) {
			if(permission.getPid()==0){  //存入根节点
				arrayList.add(permission);
			}else{
				for (Permission parentPermission : allPermission) {
					if(permission.getPid().equals(parentPermission.getId())){
						Permission parent = parentPermission;
						parent.getChildren().add(permission);
					}
				}
			}
		}
		return arrayList;*/
		//3.map处理
		List<Permission> arrayList = new ArrayList<Permission>();
		List<Permission> allPermission = permissionService.queryAll();
		Map<Integer, Permission> map = new HashMap<Integer,Permission>();
		for (Permission permission : allPermission) {
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
	
	private void getPermission(Permission parent){
		//获得子节点
		List<Permission> childrens = permissionService.queryChildrenByParentId(parent.getId());
		
		for (Permission permission : childrens) {
			getPermission(permission);
		}
		parent.setChildren(childrens);
	}
	
}
