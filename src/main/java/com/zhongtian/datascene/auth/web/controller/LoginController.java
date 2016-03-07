package com.zhongtian.datascene.auth.web.controller;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin")
public class LoginController {
	protected static final Log logger = LogFactory.getLog(LoginController.class);


	/** 
	 * 实际的登录代码 
	 * 如果登录成功，跳转至首页；登录失败，则将失败信息反馈对用户 
	 *  
	 * @param request 
	 * @return 
	 */  
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public void doLogin(HttpServletRequest request){
		String username = request.getParameter("username");  
	    String password = request.getParameter("password");  
	    
	    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
	    token.setRememberMe(true);  
	    Subject subject = SecurityUtils.getSubject(); 
	    String msg = "";
	    try {  
	        subject.login(token);  
	        if (subject.isAuthenticated()) {  
	            //return "redirect:/";  
	        } else {  
	            //return "login";  
	        }  
	    } catch (IncorrectCredentialsException e) {  
	        msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";  
	    } catch (ExcessiveAttemptsException e) {  
	        msg = "登录失败次数过多";  
	    } catch (LockedAccountException e) {  
	        msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";  
	    } catch (DisabledAccountException e) {  
	        msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";  
	    } catch (ExpiredCredentialsException e) {  
	        msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";  
	    } catch (UnknownAccountException e) {  
	        msg = "帐号不存在. There is no user with username of " + token.getPrincipal();  
	    } catch (UnauthorizedException e) {  
	        msg = "您没有得到相应的授权！" + e.getMessage();  
	    }  
		
	}
}
