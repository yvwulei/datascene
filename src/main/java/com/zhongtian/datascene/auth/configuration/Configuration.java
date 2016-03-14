package com.zhongtian.datascene.auth.configuration;

import com.zhongtian.datascene.basic.util.PropertiesUtil;

public class Configuration {
	public static final String userAuth;//常量声明时不马上初始化
    
    static {
    	userAuth = PropertiesUtil.getInstance().load("configuration").getProperty("userAuth");
    }
}
