package com.zhongtian.datascene.auth.realm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.alibaba.druid.util.StringUtils;
import com.zhongtian.datascene.auth.service.IAclService;
import com.zhongtian.datascene.auth.service.IMenuResService;
import com.zhongtian.datascene.auth.service.IRoleService;
import com.zhongtian.datascene.auth.service.IUserService;
import com.zhongtian.datascene.auth.vo.MenuResEntity;
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
    
    @Resource
    private IMenuResService menuResService;
    
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserEntity u = (UserEntity)principals.getPrimaryPrincipal();
		final UserEntity user = userService.findUser(u.getUsername());
		
		/**
		 * 添加角色权限
		 */
        final List<RoleEntity> roleInfos = roleService.listRolesByUserId(user.getId());
        for (RoleEntity role : roleInfos) {
        	authorizationInfo.addRole(role.getSn());
        	/**
             * 添加资源权限（菜单）
             */
        	List<MenuResEntity> permissions = menuResService.listByRoleId(role.getId());
        	for (MenuResEntity permission : permissions){
        		String href = permission.getHref();
        		if(StringUtils.isEmpty(href)) continue;
        		authorizationInfo.addStringPermission(permission.getHref());
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
        UserEntity u = userService.findUser(username);
        if(null == u) throw new UnknownAccountException("用户名或者密码出错");
        if(!u.getPassword().equals(new Md5Hash(password, username).toHex()))
			throw new IncorrectCredentialsException("用户名或者密码出错");
        if(1==u.getStatus()) throw new LockedAccountException("用户已经被锁定");
        
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(u, u.getPassword(), getName());
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(u.getUsername()));
        return authenticationInfo;
	}
}
