package com.loserstar.SpringManager;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Resource(name="springManager")  
	private ISpring springManager;  
	
    @RequestMapping("/toSuccess.do")
    public String ToSuccess(HttpServletRequest request){  
        WebApplicationContext ac1 = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());  
//        WebApplicationContext ac2=RequestContextUtils.getWebApplicationContext(request);  
        springManager.get();  
        return "success";  
    }  
}
