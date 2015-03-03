package com.gpcsoft.epp.bulletin.domain;

/** 
  *  Comments: <strong>对外发布公告的数据对象</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   es                    					          
  *  <br/>Module ID: 公告对外发送     		
  *  <br/>Create Date：2010-11-22 上午10:39:47 by liuy    					                            
  *  <br/>Modified Date:  2010-11-22 上午10:39:47 by liuy  
  */ 
public class PubLishBulModel {
	
	
	private Bulletin bulletin;
	
	
	/** 
	 * 公告发布状态[00：发布成功；01：发布失败]
	 */
	private String resType;
	
	/** 
	 * 公告发布信息[成攻或失败的相关信息存放]
	 */
	private String resNote;

	

	public Bulletin getBulletin() {
		return bulletin;
	}

	public void setBulletin(Bulletin bulletin) {
		this.bulletin = bulletin;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getResNote() {
		return resNote;
	}

	public void setResNote(String resNote) {
		this.resNote = resNote;
	}
	

}
