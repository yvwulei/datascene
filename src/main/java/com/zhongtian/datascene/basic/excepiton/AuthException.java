package com.zhongtian.datascene.basic.excepiton;

public class AuthException extends SysException {
	private static final long serialVersionUID = 1901838066622716867L;

	public AuthException() {
		super();
	}

	public AuthException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AuthException(String arg0) {
		super(arg0);
	}

	public AuthException(Throwable arg0) {
		super(arg0);
	}

}
