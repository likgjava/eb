package com.gpcsoft.epp.common.exception;

public class EsException extends UnsupportedOperationException {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	public EsException(String msg) {
		 super(msg);
	 }
	
	 public EsException(String msg, Throwable t) {
		 super(msg, t);
	 }
}
