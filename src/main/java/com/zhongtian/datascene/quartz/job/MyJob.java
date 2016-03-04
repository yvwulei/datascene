package com.zhongtian.datascene.quartz.job;
import java.util.Date;

import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
 
public class MyJob {  
	
	@Autowired
	@Qualifier(value="esClient")
	private Client esClient;
	
	
    public void work() {  
        System.out.println("date:" + new Date().toString());  
        
        /*try {
        	Settings settings = Settings.settingsBuilder()
        			.put("client.transport.sniff", true)
        	        .put("cluster.name", "elastic").build();
			Client client = TransportClient.builder().settings(settings).build()
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("9.123.130.151"), 9300));
			String[] indices = client.admin().indices().getIndex(new GetIndexRequest()).actionGet().getIndices();
			for(String indice : indices){
				System.out.println(indice);
			}
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}*/
        String[] indices = esClient.admin().indices().getIndex(new GetIndexRequest()).actionGet().getIndices();
        for(String indice : indices){
			System.out.println(indice);
		}
    }  
}