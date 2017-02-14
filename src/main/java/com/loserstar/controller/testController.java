package com.loserstar.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public Map<String, String> json(){
		Map<String, String> returnMap = new HashMap<String,String>();
		returnMap.put("1", "json");
		returnMap.put("2", "html");
		returnMap.put("3", "javacript");
		returnMap.put("code", "1");
		
		return returnMap;
	}

}
