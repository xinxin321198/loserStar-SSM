package com.loserstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loserstar.entity.User;
import com.loserstar.utils.JsonUtil;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	@RequestMapping(value="loginPage")
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping(value="login")
	@ResponseBody
	public String login(User user) {
		System.out.println(JsonUtil.toJson(user));
		return JsonUtil.toJson(user);
	}
	
}
