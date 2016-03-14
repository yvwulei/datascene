package com.zhongtian.datascene.auth.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class LoginController {  

	@RequestMapping(value="/login",method=RequestMethod.GET)
    public String login() { 	
        return "home/index";
    }  
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username,String password) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		String emsg = null;
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			emsg = e.getMessage();
		}
		return null;
	}
    
    @RequestMapping(value = "/notfound")
    public ModelAndView notfound() { 
    	ModelAndView mv = new ModelAndView();  
    	mv.setViewName("404");  
    	return mv;  
    }
}  
