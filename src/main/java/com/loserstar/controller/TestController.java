package com.loserstar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfinal.plugin.activerecord.Record;
import com.loserstar.dao.UserDao;
import com.loserstar.entity.User;
import com.loserstar.jfinal.service.UserService;
import com.loserstar.utils.db.jfinal.vo.VResult;

@Controller
@RequestMapping(value="/test")
public class TestController extends BaseController{
	
	private UserService userService = new UserService();
	/**
	 * 简单的一个页面
	 * @return
	 */
	@RequestMapping(value="loser")
	public String home() {
		System.out.println("homehomehomehomehomehomehomehomehomehomehomehomehome");
		return "home";
	}
	
	/**
	 * 使用mybatis查询数据
	 * @return
	 */
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
	
	@RequestMapping(value="jfinal")
	@ResponseBody
	public VResult jfinal(){
		VResult result = new VResult();
		try {
			List<Record> userList = userService.getList(null);
			result.ok("获取到数据");
			result.setData(getColumns(userList));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
