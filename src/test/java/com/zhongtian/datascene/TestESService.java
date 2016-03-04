/*package com.zhongtian.datascene;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"/spring-base.xml"})
@ContextConfiguration(locations = { "classpath*:applicationContext-es.xml"})   
public class TestESService {
	@Inject
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testInit() {
		if(elasticsearchTemplate == null){
			System.out.println("this is null");
		}else{
			System.out.println("this is not null");
		}
	}
	
}
*/