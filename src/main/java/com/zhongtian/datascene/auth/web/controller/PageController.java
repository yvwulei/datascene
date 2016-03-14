package com.zhongtian.datascene.auth.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 视图控制器,返回jsp视图给前端
 * 
 * @author 
 * @since 
 **/
@Controller
@RequestMapping("/page")
public class PageController {
	protected static final Log logger = LogFactory.getLog(PageController.class);

    /**
     * 登录页
     */
    @RequestMapping(value = "/login",method = {RequestMethod.GET}) 
    public String login() {
    	logger.debug("login excute ...");
        return "user/login";
    }

    /**
     * dashboard页
     */
    @RequestMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    /**
     * 404页
     */
    @RequestMapping("/404")
    public String error404() {
        return "404";
    }

    /**
     * 401页
     */
    @RequestMapping("/401")
    public String error401() {
        return "401";
    }

    /**
     * 500页
     */
    @RequestMapping("/500")
    public String error500() {
        return "500";
    }

}