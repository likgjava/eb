/**
 * 
 */
package com.gpcsoft.epp.project.domain;

import java.util.Comparator;
/**
 * @author shenjz
 *
 */
public class MessageCode {
	
	private String code;
	
	private String message;
	public MessageCode(){
	}
	public MessageCode(String code,String message){
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 获得编号
	 */
	public static class MessageCodeComparator implements Comparator{
		public int compare(Object o1, Object o2) {
			int code1 = Integer.parseInt(((MessageCode)o1).getCode());
			int code2 = Integer.parseInt(((MessageCode)o2).getCode());
			
			return code1 > code2 ? 1 : (code1 == code2 ? 0 : -1);
		}
	}
}
