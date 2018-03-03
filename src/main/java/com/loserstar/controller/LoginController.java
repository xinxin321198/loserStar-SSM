package com.loserstar.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
		token.setRememberMe(true);
		subject.login(token);
		
		boolean hasAdmin = subject.hasRole("admin");
		System.out.println("hasAdmin"+hasAdmin);
		return JsonUtil.toJson(user);
	}
	
}
