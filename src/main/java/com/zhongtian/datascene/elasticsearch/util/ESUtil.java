package com.zhongtian.datascene.elasticsearch.util;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;

public class ESUtil {
	
	private Client client;
	
	/**
	* 检查索引是否已经存在
	* @param indexName
	* @return
	*/
	public boolean exists(String indexName) {
		IndicesExistsResponse existsResponse = client.admin().indices().prepareExists(indexName).execute().actionGet();
		return existsResponse.isExists();
	}
	
	/**
	* 清除索引
	* @param indexName
	*/
	public void deleteIndex(String indexName) {
		IndicesExistsResponse indicesExistsResponse = client.admin().indices()
                .exists(new IndicesExistsRequest(new String[] { indexName }))
                .actionGet();
        if (indicesExistsResponse.isExists()) {
            client.admin().indices().delete(new DeleteIndexRequest(indexName))
                    .actionGet();
//            DeleteIndexRequestBuilder requestBuilder = client.admin().indices().prepareDelete(indexName);
//    		requestBuilder.execute().actionGet();
        }
	}
	/**
	* 初始化索引创建
	* @param indexName
	* @param indexType
	* @param mapping
	*/
	public void addIndex(String indexName,String indexType,XContentBuilder mapping) {
	CreateIndexRequestBuilder requestBuilder = client
	.admin().indices().prepareCreate(indexName);
	requestBuilder.addMapping(indexType,mapping);
	requestBuilder.execute().actionGet();
	}
}
