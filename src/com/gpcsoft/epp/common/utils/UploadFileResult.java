package com.gpcsoft.epp.common.utils;
/** 
 * @Description: 附件上传结果
 * @version V1.0
 * @Create Date 2011-3-14 下午07:14:20 By wanghz   
 */
public class UploadFileResult {
	
	public static String FILE_TYPE_XML = "text/xml";
	public static String FILE_TYPE_EXCEL_OS = "application/octet-stream"; // ie
	public static String FILE_TYPE_EXCEL_MS = "application/vnd.ms-excel"; // firefox
	
	private Object object;
	
	private boolean result = false;
	
	private StringBuffer message = null;

	private Exception e;
	
	private String fielType = UploadFileResult.FILE_TYPE_XML;
	
	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(Object object) {
		this.object = object;
	}

	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * @return the message
	 */
	public StringBuffer getMessage() {
		if (null == this.message) {
			this.message = new StringBuffer();
		}
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(StringBuffer message) {
		this.message = message;
	}

	/**
	 * @return the e
	 */
	public Exception getE() {
		return e;
	}

	/**
	 * @param e the e to set
	 */
	public void setE(Exception e) {
		this.e = e;
	}

	public String getFielType() {
		return fielType;
	}

	public void setFielType(String fielType) {
		this.fielType = fielType;
	}
	
}
 
