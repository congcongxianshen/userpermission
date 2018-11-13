package com.kj.permission.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerStartupListener implements ServletContextListener{
	
	public void contextInitialized(ServletContextEvent sc) {
		ServletContext servletContext = sc.getServletContext();
		String contextPath = servletContext.getContextPath();
		servletContext.setAttribute("APP_PATH", contextPath);
		System.out.println();
	}
	
	public void contextDestroyed(ServletContextEvent sc) {

	}



}
