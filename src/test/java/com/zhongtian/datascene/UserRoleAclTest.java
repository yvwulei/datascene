package com.zhongtian.datascene;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.zhongtian.datascene.auth.service.IAclService;
import com.zhongtian.datascene.auth.service.IRoleService;
import com.zhongtian.datascene.auth.service.IUserService;
import com.zhongtian.datascene.auth.vo.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml", "classpath*:applicationContext-shiro.xml"})   
public class UserRoleAclTest {
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource
    private IUserService userService;

    @Resource
    private IRoleService roleService;

    @Resource
    private IAclService aclService;
	
	@Before
	public void setUp() {
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
	}
	
	@After
	public void tearDown() {
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession(); 
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
	}
	
	@Test
	public void testAclRes() {
		String username = "admin";
		
		UserEntity user = userService.findUser(username);
		if(null != user)
			System.out.println(user);
		else
			System.out.println("user is null");
	}
	
	
}
