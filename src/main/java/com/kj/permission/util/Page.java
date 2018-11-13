package com.kj.permission.util;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	
	private Integer pagesize; // 页码
	private Integer total;	// 总记录数
	private Integer pagenototal; //总页数
	private List<T> datas=new ArrayList<T>();

	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPagenototal() {
		return pagenototal;
	}
	public void setPagenototal(Integer pagenototal) {
		this.pagenototal = pagenototal;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
	
}
