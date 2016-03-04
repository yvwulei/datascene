package com.zhongtian.datascene.basic.excepiton;
public class SysException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SysException() {
		super();
	}

	public SysException(String arg0) {
		super(arg0);
	}

	public SysException(Throwable arg0) {
		super(arg0);
	}
	
	public SysException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
