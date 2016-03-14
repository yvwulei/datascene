package com.zhongtian.datascene.auth.security;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.zhongtian.datascene.auth.service.IAclService;
import com.zhongtian.datascene.auth.service.IRoleService;
import com.zhongtian.datascene.auth.service.IUserService;
import com.zhongtian.datascene.auth.vo.RoleEntity;
import com.zhongtian.datascene.auth.vo.UserEntity;

/**
 * 用户身份验证,授权 Realm 组件
 * 
 * @author 
 * @since 
 **/
public class SecurityRealm extends AuthorizingRealm {
	protected static final Log logger = LogFactory.getLog(SecurityRealm.class);
	
	@Resource
    private IUserService userService;

    @Resource
    private IRoleService roleService;

    @Resource
    private IAclService aclService;
    
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = String.valueOf(principals.getPrimaryPrincipal());
		final UserEntity user = userService.findUser(username);
		
		/**
		 * 添加角色权限
		 */
        final List<RoleEntity> roleInfos = roleService.selectRolesByUserId(user.getId());
        for (RoleEntity role : roleInfos) {
        	authorizationInfo.addRole(role.getSn());
        	/**
             * 添加资源权限（菜单）
             */
        	List<String> permissions = aclService.listMenuSnByRole(role.getId());
        	for (String permission : permissions){
        		authorizationInfo.addStringPermission(permission);
        	}
        }
        return authorizationInfo;
	}

	/**
	 * 登陆验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        // 通过数据库进行验证
        UserEntity authentication = userService.findUser(username, password);
        if (authentication == null) {
            throw new AuthenticationException("用户名或密码错误.");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        return authenticationInfo;
	}
}
