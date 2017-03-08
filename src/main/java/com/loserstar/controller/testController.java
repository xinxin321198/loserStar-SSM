package com.loserstar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loserstar.dao.UserDao;
import com.loserstar.entity.User;

@Controller
@RequestMapping(value="/test")
public class testController{

	
	@RequestMapping(value="loser")
	public String home() {
		System.out.println("homehomehomehomehomehomehomehomehomehomehomehomehome");
		return "home";
	}
	
	@RequestMapping(value="json")
	@ResponseBody
	public Map<String, Object> json(){
		Map<String, Object> returnMap = new HashMap<String,Object>();
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		UserDao userDao = (UserDao)applicationContext.getBean("userDao");
		User userParam = new User();
		userParam.setUserName("b");
		List<User> userList = userDao.findList(userParam);
		
		returnMap.put("code", 1);
		returnMap.put("data", userList);
		
		return returnMap;
	}
	
	

}
