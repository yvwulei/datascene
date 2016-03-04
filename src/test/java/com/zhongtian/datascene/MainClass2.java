//package com.zhongtian.datascene;
//
//import java.net.InetAddress;
//
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.search.SearchHit;
//
//public class MainClass2 {
//
//	public static void main(String[] args) {
//	/*	try{
//			Settings settings = Settings.settingsBuilder()
//	                .put("client.transport.sniff", true)
//	                .put("cluster.name", "elastic").build();
//	        InetAddress addr = InetAddress.getByName("9.123.130.151");
//	        TransportClient client = TransportClient.builder().settings(settings).build().
//	                addTransportAddress(new InetSocketTransportAddress(addr, 9300));
//	        GetResponse response = client.prepareGet("fault-2016-02-18", "error", "AVLyqP_EmTD08kMX10xx")  
//	        		.execute().actionGet();  
//	        System.out.println(response.getSource().get("_source"));
//		}catch(Exception e){
//			e.printStackTrace();
//		}*/
//	}
//
//}
