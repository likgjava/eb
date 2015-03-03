package com.gpcsoft.bizplatform.base.exception;

public class CustomerException extends UnsupportedOperationException{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	public CustomerException(String msg) {
		super(msg);
	}
	
	public CustomerException(String msg, Throwable t) {
		super(msg,t);
	}
}
