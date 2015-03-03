package com.gpcsoft.agreement.exception;

import com.gpcsoft.srplatform.auth.exception.IllegalDatabaseDataException;


/** 
  *  Comments: <strong>NotExistsTheProtaskException</strong>            		<br/>
  *	 协议供货， 所选的数据不存在有效的任务书<br/>												
  *  CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		<br/>
  *  Project:   srplatform                    					<br/>           
  *  Module ID: 协议供货     		<br/>
  *  Create Date：2009-12-15 下午11:51:29 by liangxj    					<br/>                                 
  *  Modified Date:  2009-12-15 下午11:51:29 by liangxj                   <br/>               
  *      
  *  @since 0.4
  *  @version: 0.5 
  */ 
public class NotExistsTheProtaskException extends IllegalDatabaseDataException {

	/** serialVersionUID<中文解释> */
	private static final long serialVersionUID = 1L;

	public NotExistsTheProtaskException(String msg) {
		super(msg);
	}

	public NotExistsTheProtaskException(String msg, Throwable t) {
		super(msg, t);
	}
	
}
