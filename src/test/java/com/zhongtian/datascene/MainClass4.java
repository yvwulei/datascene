package com.zhongtian.datascene;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.stats.IndexStats;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import com.zhongtian.datascene.elasticsearch.vo.AlarmInfoEntity;
import com.zhongtian.datascene.elasticsearch.vo.TaskInfo;

 
public class MainClass4 {  
       
    @Autowired(required = false)
	public static void main(String[]  args) {  
        System.out.println("date:" + new Date().toString());  
        
        try {
        	Settings settings = Settings.settingsBuilder()
        			.put("client.transport.sniff", true)
        	        .put("cluster.name", "elastic").build();
			Client client = TransportClient.builder().settings(settings).build()
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("9.123.130.151"), 9300));
			
			/*//创建索引
			client.admin().indices().prepareCreate("alarm-2016.02.25")
			.setSettings(Settings.builder()             
	                .put("index.number_of_shards", 3)
	                .put("index.number_of_replicas", 2)
	        )
            .get();
			
			//设置Setting
			client.admin().indices().prepareUpdateSettings("alarm-2016.02.25")   
	        .setSettings(Settings.builder()                     
	                .put("index.number_of_replicas", 2)
	        )
	        .get();*/
			
			//设置Mapping
			client.admin().indices().preparePutMapping("alarm-2016.02.25") 
			.setType("alarm")  
	        .setSource("{\n" +               
	                "      \"properties\" : {\n" +
	                "        \"alarmId\" : {\n" +
	                "          \"type\" : \"string\",\n" +
	                "          \"index\" : \"not_analyzed\"\n" +
	                "         },\n" +
	                "        \"buSysName\" : {\n" +
	                "          \"type\" : \"string\",\n" +
	                "          \"index\" : \"not_analyzed\",\n" +
	                "          \"store\" : true\n" +
	                "         },\n" +
	                "        \"filePath\" : {\n" +
	                "          \"type\" : \"string\",\n" +
	                "          \"index\" : \"not_analyzed\",\n" +
	                "          \"store\" : true\n" +
	                "         },\n" +
	                "        \"host\" : {\n" +
	                "          \"type\" : \"string\",\n" +
	                "          \"index\" : \"not_analyzed\",\n" +
	                "          \"store\" : true\n" +
	                "         },\n" +
	                "        \"ip\" : {\n" +
	                "          \"type\" : \"string\",\n" +
	                "          \"index\" : \"not_analyzed\",\n" +
	                "          \"store\" : true\n" +
	                "         },\n" +
	                "        \"keyword\" : {\n" +
	                "          \"type\" : \"string\",\n" +
	                "          \"index\" : \"not_analyzed\",\n" +
	                "          \"store\" : true\n" +
	                "         },\n" +
	                "        \"message\" : {\n" +
	                "          \"type\" : \"string\",\n" +
	                "          \"store\" : true\n" +
	                "         },\n" +
	                "        \"prdType\" : {\n" +
	                "          \"type\" : \"string\",\n" +
	                "          \"index\" : \"not_analyzed\",\n" +
	                "          \"store\" : true\n" +
	                "         },\n" +
	                "        \"risk\" : {\n" +
	                "          \"type\" : \"string\",\n" +
	                "          \"index\" : \"not_analyzed\",\n" +
	                "          \"store\" : true\n" +
	                "         },\n" +
	                "        \"suggest\" : {\n" +
	                "          \"type\" : \"string\",\n" +
	                "          \"index\" : \"not_analyzed\",\n" +
	                "          \"store\" : true\n" +
	                "         },\n" +
	                "        \"timestamp\" : {\n" +
	                "          \"type\" : \"date\",\n" +
	                "          \"format\" : \"date_time\"\n" +
	                "         }\n" +
	                "      }\n" +
	                "  }")
	        .get();
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    }  
}