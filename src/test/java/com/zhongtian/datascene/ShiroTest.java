package com.zhongtian.datascene;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhongtian.datascene.auth.security.SecurityRealm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml", "classpath*:applicationContext-shiro.xml", 
		"classpath*:applicationContext-es.xml",  "classpath*:applicationContext-quartz.xml" })   
public class ShiroTest {
	private SecurityRealm securityRealm;
	
	@Before
	public void setUp() {
		
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testInitRes() {
		
	}
	
	@Test
	public void testInitMenuRes() {
		
	}
	
}
