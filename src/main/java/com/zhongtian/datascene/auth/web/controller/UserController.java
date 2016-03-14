package com.zhongtian.datascene.auth.web.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhongtian.datascene.auth.configuration.Configuration;
import com.zhongtian.datascene.auth.service.IUserService;
import com.zhongtian.datascene.auth.vo.UserEntity;
import com.zhongtian.datascene.auth.web.model.UserRegisterModel;
import com.zhongtian.datascene.auth.web.viewvo.UserViewVO;

	  
  
@Controller  
@RequestMapping("/user")  
public class UserController {  
	protected static final Log logger = LogFactory.getLog(UserController.class);

    @Resource(name="userService")  
    private IUserService userService;  
    
	@RequestMapping(value="/login", method = {RequestMethod.POST})
    public String login(UserViewVO user){
		logger.info("dologin post excute...");
		String username = user.getUsername();  
	    String password = user.getPassword();  
	    
	    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//	    token.setRememberMe(true);  
	    Subject subject = SecurityUtils.getSubject(); 
	    String msg = "";
	    try {  
	        subject.login(token);  
	        if (subject.isAuthenticated()) {
	        	logger.info("dologin success...");
	            return "redirect:/views/user/success.jsp";  
	        } else {  
	        	logger.info("dologin fail...");
	            return "user/login";  
	        }  
	    } catch (IncorrectCredentialsException e) {  
	        msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";  
	        logger.error(e);
	    } catch (ExcessiveAttemptsException e) {  
	        msg = "登录失败次数过多";  
	        logger.error(e);
	    } catch (LockedAccountException e) {  
	        msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";  
	        logger.error(e);
	    } catch (DisabledAccountException e) {  
	        msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled."; 
	        logger.error(e);
	    } catch (ExpiredCredentialsException e) {  
	        msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";  
	        logger.error(e);
	    } catch (UnknownAccountException e) {  
	        msg = "帐号不存在. There is no user with username of " + token.getPrincipal();  
	        logger.error(e);
	    } catch (UnauthorizedException e) {  
	        msg = "您没有得到相应的授权！" + e.getMessage();  
	        logger.error(e);
	    }  catch (Exception e){
	    	logger.error(e);
	    }
		
        return "user/login";
    }
	
/*	@RequestMapping(value="/login", method = {RequestMethod.POST})
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
	
*/	

	@RequestMapping(value = "/logout",method = RequestMethod.GET)  
    public String logout(HttpServletRequest request,HttpSession httpSession){ 
		
        UserEntity u = (UserEntity) httpSession.getAttribute(Configuration.userAuth); 
        if(null != u){
        	httpSession.removeAttribute(Configuration.userAuth);
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