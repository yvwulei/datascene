package com.zhongtian.datascene.auth.web.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhongtian.datascene.auth.annotation.AuthRequired;
import com.zhongtian.datascene.auth.service.IUserService;
import com.zhongtian.datascene.auth.vo.UserEntity;
import com.zhongtian.datascene.auth.web.interceptor.AuthInterceptor;
import com.zhongtian.datascene.auth.web.model.UserLoginModel;
import com.zhongtian.datascene.auth.web.model.UserRegisterModel;  
  
  
@Controller  
@RequestMapping("/user")  
public class UserController {  
    @Resource(name="userService")  
    private IUserService userService;  
    
	@RequestMapping(value="/login", method = {RequestMethod.GET})
    public String login(Model model){
		if(!model.containsAttribute("contentModel"))
            model.addAttribute("contentModel", new UserLoginModel());
        return "user/login";
    }
	
	@RequestMapping(value="/login", method = {RequestMethod.POST})
	public String login(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") UserLoginModel userLoginModel, BindingResult result) {
		if(result.hasErrors())
            return login(model);
		String username = userLoginModel.getUsername();
		String password = userLoginModel.getPassword();
		UserEntity u = userService.findUser(username, password);
    	if(null == u){
        	result.addError(new FieldError("contentModel","username","用户名或密码错误。"));
        	result.addError(new FieldError("contentModel","password","用户名或密码错误。"));
        	return login(model);
    	}else if(u.getStatus() == 0){
    		//用户状态正常
    	}else if(u.getStatus() == 1){
    		result.addError(new FieldError("contentModel","username","此用户被禁用，不能登录。"));
    		return login(model);
    	}else {
    		result.addError(new FieldError("contentModel","username","此用户当前未被授权，不能登录。"));
    		return login(model);
    	}
    	request.getSession().setAttribute("userAuth", u);	
		String returnUrl="/home/index";
    	return "redirect:"+returnUrl; 	
	}
	
	@AuthRequired
	@RequestMapping(value = "/logout",method = RequestMethod.GET)  
    public String logout(HttpServletRequest request,HttpSession httpSession){ 
		
        UserEntity u = (UserEntity) httpSession.getAttribute(AuthInterceptor.userAuth); 
        if(null != u){
        	httpSession.removeAttribute(AuthInterceptor.userAuth);
        	httpSession.invalidate();
        }
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="/user/login";
    	return "redirect:"+returnUrl; 
    }
	
	
	/**
		@RequestMapping(value = "/logout",method = RequestMethod.GET)  
    public ModelAndView logout(Model model,HttpSession httpSession){ 
		
        UserEntity u = (UserEntity) httpSession.getAttribute(AuthInterceptor.userAuth); 
        if(null != u){
        	httpSession.removeAttribute(AuthInterceptor.userAuth);
        	httpSession.invalidate();
        }
        return new ModelAndView(new RedirectView("../login"));
    }
	 */
	@RequestMapping(value="/register", method = {RequestMethod.GET})
    public String register(Model model){
		if(!model.containsAttribute("contentModel"))
            model.addAttribute("contentModel", new UserRegisterModel());
        return "user/register";
    }
	
	@RequestMapping(value="/register", method = {RequestMethod.POST})
	public String register(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") UserRegisterModel userRegisterModel, BindingResult result) {
		if(!userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword()))
			result.addError(new FieldError("contentModel","confirmPassword","确认密码与密码输入不一致。"));
		//如果有验证错误 返回到form页面
        if(result.hasErrors())
            return register(model);
        else if(userService.userExist(userRegisterModel.getUsername())){
        	result.addError(new FieldError("contentModel","username","该用户名已被注册。"));
            return register(model);
        }      
        userService.save(userRegisterModel);
        
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="/user/login";
    	return "redirect:"+returnUrl; 	
	}
}  