package com.zhongtian.datascene.elasticsearch.factory;

import java.net.InetAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.util.StringUtils;

public class TransportClientFactoryBean implements SmartFactoryBean<TransportClient> ,DisposableBean  {
	protected static final Log logger = LogFactory.getLog(TransportClientFactoryBean.class);
	private static final String INIT_PARAM_DELIMITERS = ",; \t\n";
	private String clusterName;
	private String clusterNodes;
	private Boolean sniff = Boolean.TRUE;
	private TransportClient client;
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public void setClusterNodes(String clusterNodes) {
		this.clusterNodes = clusterNodes;
	}

	public void setSniff(Boolean sniff) {
		this.sniff = sniff;
	}

	@Override
	public TransportClient getObject() throws Exception {
		logger.debug("create es TransportClient start...");
		Settings settings = Settings.settingsBuilder()
    			.put("client.transport.sniff", this.sniff)
    	        .put("cluster.name", this.clusterName).build();
		TransportClient client = TransportClient.builder().settings(settings).build();
		String[] array = StringUtils.tokenizeToStringArray(this.clusterNodes, INIT_PARAM_DELIMITERS);
		for(String str :array){
			String[] nodeInfo = str.split(":");
			String ip = nodeInfo[0];
			int port = Integer.parseInt(nodeInfo[1]);
		        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), port));
		}
		this.client = client;
		logger.debug("create es TransportClient end...");
		return this.client;
	}

	@Override
	public Class<?> getObjectType() {
		return TransportClient.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void destroy() throws Exception {
		logger.debug("TransportClient close start...");
		if(null != this.client){
			this.client.close();
			this.client = null;
		}
		logger.debug("TransportClient close end...");
	}

	@Override
	public boolean isEagerInit() {
		return false;
	}

	@Override
	public boolean isPrototype() {
		return false;
	}

}
