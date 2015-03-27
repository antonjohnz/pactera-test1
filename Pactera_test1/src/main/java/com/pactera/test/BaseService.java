package com.pactera.test;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseService {
	

	ApplicationContext context;
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;
		
	}
	
	public ApplicationContext getApplicationContext() {
		return context;
	}
	
	public BaseService(){
		context = new ClassPathXmlApplicationContext("/application-config.xml");
	}
}
