package com.zhongtian.datascene;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.stats.IndexStats;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import com.zhongtian.datascene.elasticsearch.vo.AlarmInfoEntity;

 
public class MainClass3 {  
       
    @Autowired(required = false)
	public static void main(String[]  args) {  
        System.out.println("date:" + new Date().toString());  
        
        try {
        	Settings settings = Settings.settingsBuilder()
        			.put("client.transport.sniff", true)
        	        .put("cluster.name", "elastic").build();
			Client client = TransportClient.builder().settings(settings).build()
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("9.123.130.151"), 9300));
			String[] indices = client.admin().indices().getIndex(new GetIndexRequest()).actionGet().getIndices();
			for(String indice : indices){
				System.out.println(indice);
			}
			Map<String, IndexStats> indicesStats = client.admin().indices().stats(new IndicesStatsRequest()).actionGet().getIndices();
			for(Map.Entry<String, IndexStats> entry : indicesStats.entrySet()){
				String key =  entry.getKey();
				IndexStats iStats = entry.getValue();
				System.out.println("key="+key);
				
			}
			
			List<AlarmInfoEntity> list = new ArrayList<AlarmInfoEntity>();
			
			AlarmInfoEntity entity = new AlarmInfoEntity();
			entity.setBuSysName("bii");
			entity.setFilePath("/was_dump/cluserver1/SystemOut.log");
			entity.setHost("bii01");
			entity.setIp("192.168.1.111");
			entity.setKeyword("OutOfMemory");
			entity.setMessage("...OutOfMemory");
			entity.setPrdType("WAS");
			entity.setRisk("高");
			entity.setSuggest("检查服务器运行状况");
			entity.setTimestamp("2016-02-18T09:11:46.828Z");
			list.add(entity);
			
			XContentBuilder builder;
			try {
				builder = XContentFactory.jsonBuilder()
				        .startObject()
				        .field("buSysName", entity.getBuSysName())
				        .field("filePath", entity.getFilePath())
				        .field("host", entity.getHost())
				        .field("ip", entity.getIp())
				        .field("keyword", entity.getKeyword())
				        .field("message", entity.getMessage())
				        .field("prdType", entity.getPrdType())
				        .field("risk", entity.getRisk())
				        .field("suggest", entity.getSuggest())
				        .field("timestamp", entity.getTimestamp())
				        .endObject();
				IndexResponse response = client.prepareIndex("alarm-2016.02.25", "alarm")
	                    .setSource(builder)
	                    .execute()
	                    .actionGet();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
//			XContentBuilder builder0 = XContentFactory.jsonBuilder()
//                    .startObject()
//                    .field("user", "jason ling")
//                    .field("postDate", new Date())
//                    .field("message", "hello jason ling!")
//                    .field("age", 30)
//                    .endObject();
//			BulkRequestBuilder bulkRequest = client.prepareBulk();
//	        bulkRequest.add(client.prepareIndex("twitter", "tweet", "7").setSource(builder0))));
//	                         
//	        BulkResponse bulkResponse =  bulkRequest.execute().actionGet();
//	                         
//	        if(bulkResponse.hasFailures()){
//	                             
//	        }
	        
//            List<IndexQuery> queries = new ArrayList<IndexQuery>();
//            for (AlarmInfoEntity obj : list) {
//            	IndexQuery indexQuery = new IndexQueryBuilder().withObject(entity).build();
//                queries.add(indexQuery);
//            }
            
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    }  
}