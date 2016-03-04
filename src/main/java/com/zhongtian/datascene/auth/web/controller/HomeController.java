package com.zhongtian.datascene.auth.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhongtian.datascene.auth.annotation.AuthRequired;

@Controller
@RequestMapping(value = "/home")
public class HomeController {  

	@AuthRequired
    @RequestMapping(value = "/index")
    public String index() { 	
        return "home/index";
    }  
    
    @RequestMapping(value = "/notfound")
    public ModelAndView notfound() { 
    	ModelAndView mv = new ModelAndView();  
    	mv.setViewName("404");  
    	return mv;  
    }
}  
