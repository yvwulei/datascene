/*package com.zhongtian.datascene.elasticsearch.util;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

   @Configuration
   @EnableElasticsearchRepositories(basePackages = "org/springframework/data/elasticsearch/repositories")
   public class ESConfig {

        @Value("${esearch.port}") int port;
        @Value("${esearch.host}") String hostname;

        @Bean
        public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
         }

        @Bean
        public Client client(){
            TransportClient client= new TransportClient();
            TransportAddress address = new InetSocketTransportAddress(hostname, port); 
            client.addTransportAddress(address);
            return client;
        }
   }*/