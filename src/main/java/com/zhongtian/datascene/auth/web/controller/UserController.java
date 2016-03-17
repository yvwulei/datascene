package com.zhongtian.datascene.auth.web.controller;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhongtian.datascene.auth.service.IUserService;
import com.zhongtian.datascene.auth.vo.UserEntity;
import com.zhongtian.datascene.auth.web.viewvo.UserViewVO;
import com.zhongtian.datascene.sys.viewvo.Response;

	  
  
@RestController
public class UserController {  
	protected static final Log logger = LogFactory.getLog(UserController.class);

    @Resource(name="userService")  
    private IUserService userService;  
    
    @RequiresPermissions(value={"user:create"})
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Response createUser(@RequestBody @Valid UserViewVO user) {
    	String username = user.getUsername();
    	Boolean retVal = userService.userExist(username);
    	if(Boolean.TRUE==retVal)return new Response().failure("user is exists");
    	UserEntity ue = userService.addUser(user);
        return new Response().success(ue);
    }
    
    @RequiresPermissions(value={"user:read"})
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Response listAllUsers() {
        List<UserEntity> users = userService.findAllUsers();
        if(users.isEmpty()){
        	return new Response().failure("user_failure");
        }
        return new Response().success(users);
    }
    
    /**
     * 获取单个用户信息
     * @param advertiserId
     * @return
     */
    @RequiresPermissions(value={"user:read"})
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public Response getUser(@PathVariable("id") String userId) {
        UserEntity user =  new UserEntity();
        return new Response().success(user);
    }
    
    @RequiresPermissions(value={"user:update"})
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public Response updateAdvertiser(@PathVariable("id") String userId,	@RequestBody @Valid UserEntity user) {
        //userService.updateUser
        return new Response().success();
    }
    
    @RequiresPermissions(value={"user:delete"})
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public Response deleteAdvertiser(@PathVariable("id") String advertiserId) {
    	//userService.deleteUser
        return new Response().success();
    }
}  