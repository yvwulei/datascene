//package com.zhongtian.datascene;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.elasticsearch.client.transport.TransportClient;
//
//import com.zhongtian.datascene.basic.util.FreemarkerUtil;
//
//public class MainClass {
//
//	public static void main(String[] args) {
//		TransportClient client =null;
//		try{
//			/*	Settings settings = Settings.settingsBuilder()
//	                .put("client.transport.sniff", true)
//	                .put("cluster.name", "elastic").build();
//	        InetAddress addr = InetAddress.getByName("9.123.130.151");
//	        client = TransportClient.builder().settings(settings).build().
//	                addTransportAddress(new InetSocketTransportAddress(addr, 9300));
//	        
//	       SearchResponse response = client.prepareSearch("fault-*")  
//	    		   	.setTypes("error")
//	        		.setQuery(QueryBuilders.matchAllQuery()) 
//	        		.setExplain(true)
//	        		.execute().actionGet(); 
//	        for(SearchHit hit: response.getHits()) { 
//	        	System.out.println("========================");
//	        	for( Map.Entry entity :hit.getSource().entrySet()){
//	        		System.out.println(entity.getKey()+":"+String.valueOf(entity.getValue()));
//	        	}
//	        }*/
//			
//	        
//	       /* SearchResponse response = client.prepareSearch("fault-2016-02-18")
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
//	        }*/
//		FreemarkerUtil fu = FreemarkerUtil.getInstance("/ftl");
//		// 建立数据模型  
//        Map root = new HashMap();
//        List<LogstashConfigEntity> list = new ArrayList<LogstashConfigEntity>();
//        LogstashConfigEntity entity =  new LogstashConfigEntity();
//        entity.setFilePath("/opt/SystemOut.log");
//        entity.setKeyword("CPUHIGH");
//        entity.setAlertType("1");//直接报警
//        entity.setAlertLevel("高");
//        entity.setRegular("\\[");
//        list.add(entity);
//        LogstashConfigEntity entity2 =  new LogstashConfigEntity();
//        entity2.setFilePath("/opt/SystemOut2.log");
//        entity2.setKeyword("ThreadHung");
//        entity2.setAlertType("2");//直接报警
//        entity2.setLowerThreshold("10");
//        entity2.setHighThreshold("20");
//        entity2.setRegular("(?m).*Dump\\sEvent(?<Dump_Event>.*).+1TIDATETIME\\s+Date:\\s*(?<Date>.*).+1TIFILENAME\\s+Javacore\\s*filename:\\s*(?<javacore_filename>.*)");
//        list.add(entity2);
//        root.put("items", list);
//        String port =  "5000";
//        root.put("port", port);
//        fu.sprint(root, "logstash.ftl");
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			
//			if(null != client)
//				client.close();
//		}
//	}
//
//}
