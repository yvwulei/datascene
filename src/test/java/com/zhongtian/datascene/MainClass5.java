package com.zhongtian.datascene;

import java.util.Date;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MainClass5 {  
       
	@Test
	public void test01() {  
        System.out.println("date:" + new Date().toString());  
        
        try {
        	
        	ClassPathXmlApplicationContext  context = new ClassPathXmlApplicationContext(
        	        new String[] {"applicationContext-es.xml"}); 
			Client client = (TransportClient)context.getBean("esClient");
			SearchRequestBuilder builder  = client.prepareSearch().setIndices("alarm-2016.02.25")
					.setTypes("Systemout")
					.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
					.setFrom(0)
					.setSize(10);
			
			
			ObjectMapper mapper = new ObjectMapper();
			SearchResponse response =builder.execute().actionGet();
			for(SearchHit hit: response.getHits().getHits()) {  
				String alarmId = hit.getId();
				System.out.println(hit.getType());
//		        Map<String, Object> result = hit.getSource();
				String json = hit.getSourceAsString();
				System.out.println(json);
				/*AlarmInfoEntity entity = mapper.readValue(json, AlarmInfoEntity.class);
				entity.setAlarmId(alarmId);
				System.out.println(entity);*/
			}
			
			context.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }  
}