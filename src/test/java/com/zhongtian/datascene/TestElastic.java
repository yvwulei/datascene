//package com.zhongtian.datascene;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.search.SearchType;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.search.SearchHit;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//public class TestElastic {
//	
//	
//	@Before
//	public void setUp() {
//	}
//	
//	@After
//	public void tearDown() {
//	}
//	
//	@Test
//	public void testOperElastic() {
//		try{
//			Settings settings = Settings.settingsBuilder()
//	                .put("client.transport.sniff", true)
//	                .put("cluster.name", "elastic").build();
//	        InetAddress addr = InetAddress.getByName("9.123.130.151");
//	        TransportClient client = TransportClient.builder().settings(settings).build().
//	                addTransportAddress(new InetSocketTransportAddress(addr, 9300));
//	        
//	       /*SearchResponse response = client.prepareSearch("fault-2016-02-18")  
//	        		.setQuery(QueryBuilders.matchAllQuery())  
//	        		.setFrom(10)  
//	        		.setSize(20)  
//	        		.execute().actionGet(); 
//	        for(SearchHit hit: response.getHits().getHits()) { 
//	        	System.out.println(hit.getId());
//	        	System.out.println("field.title: "+ hit.getFields().get("host").getValue());
//	        }*/
//			
//	        System.out.println(1);
//	        
//	        SearchResponse response = client.prepareSearch("fault-2016-02-18")
//	                .setTypes("error")
//	                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//	                //.setQuery(QueryBuilders.termQuery("multi", "test"))                 // Query
//	                //.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
//	                .setFrom(0).setSize(60).setExplain(true)
//	                .execute()
//	                .actionGet();
//	        System.out.println(response.getHits().getHits().length);
//	        for(SearchHit hit: response.getHits().getHits()) { 
//	        	System.out.println(hit.getId());
//	        	System.out.println("field.title: "+ hit.getFields().get("host").getValue());
//	        }
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//}
