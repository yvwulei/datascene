package com.zhongtian.datascene.auth.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhongtian.datascene.sys.viewvo.Response;

/**
 * 视图控制器,返回jsp视图给前端
 * 
 * @author 
 * @since 
 **/
@RestController
public class PageController {
	protected static final Log logger = LogFactory.getLog(PageController.class);

    /**
     * 404页
     */
    @RequestMapping(value="/404" ,method=RequestMethod.GET)
    public String error404() {
        return "404";
    }

    /**
     * 401页
     */
    @RequestMapping(value="/401" ,method=RequestMethod.GET)
    public Response error401() {
    	 return new Response().failure("unauth");
    }

    /**
     * 500页
     */
    @RequestMapping(value="/500" ,method=RequestMethod.GET)
    public String error500() {
        return "500";
    }

}