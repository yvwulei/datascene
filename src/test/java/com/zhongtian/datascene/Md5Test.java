package com.zhongtian.datascene;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class Md5Test {

	@Test
	public void md5str(){
		String username = "admin";
		String password = "admin123";
		String encStr = new Md5Hash(password, username).toHex();
		System.out.println(encStr);
	}
}
