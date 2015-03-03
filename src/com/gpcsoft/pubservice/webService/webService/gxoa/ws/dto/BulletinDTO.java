package com.gpcsoft.pubservice.webService.webService.gxoa.ws.dto;

import java.util.Date;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/** 
 *  Comments: <strong>Project</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Module ID: 项目    		
 *  @gpcsoft.package packageDir="com.gpcsoft.es.ext.bulletin"
 *  @gpcsoft.page domain="ext/bulletin" project="es"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="项目"  
 *  @since 0.1
 *  @version: 0.1 
 */ 
@NodeMapping(tag="projectInfo")
public class BulletinDTO{
	
	private String projCode;//项目编号
	private String bulletinTitle;	//公告标题
	private String bulletinContent; //公告内容
	private Date signStartTime; //报名开始时间
	private Date signEndTime; //报名结束时间
	private Date bidStartTime; //投标开始时间
	private Date bidEndTime; //投标结束时间
	private Date openBidTime; //开标时间
	private String meetRoomAddress; //开标地点
	private String bulletinUrl;//招标公告地址
	
	@NodeMapping(tag="bulletinUrl")
	public String getBulletinUrl() {
		return bulletinUrl;
	}
	public void setBulletinUrl(String bulletinUrl) {
		this.bulletinUrl = bulletinUrl;
	}
	
	@NodeMapping(tag="projCode")
	public String getProjCode() {
		return projCode;
	}
	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}
	
	@NodeMapping(tag="bulletinTitle")
	public String getBulletinTitle() {
		return bulletinTitle;
	}
	public void setBulletinTitle(String bulletinTitle) {
		this.bulletinTitle = bulletinTitle;
	}
	
	@NodeMapping(tag="bulletinContent")
	public String getBulletinContent() {
		return bulletinContent;
	}
	public void setBulletinContent(String bulletinContent) {
		this.bulletinContent = bulletinContent;
	}
	
	@NodeMapping(tag="signStartTime")
	public Date getSignStartTime() {
		return signStartTime;
	}
	public void setSignStartTime(Date signStartTime) {
		this.signStartTime = signStartTime;
	}
	
	@NodeMapping(tag="signEndTime")
	public Date getSignEndTime() {
		return signEndTime;
	}
	public void setSignEndTime(Date signEndTime) {
		this.signEndTime = signEndTime;
	}
	
	@NodeMapping(tag="bidStartTime")
	public Date getBidStartTime() {
		return bidStartTime;
	}
	public void setBidStartTime(Date bidStartTime) {
		this.bidStartTime = bidStartTime;
	}
	
	@NodeMapping(tag="bidEndTime")
	public Date getBidEndTime() {
		return bidEndTime;
	}
	public void setBidEndTime(Date bidEndTime) {
		this.bidEndTime = bidEndTime;
	}
	
	@NodeMapping(tag="openBidTime")
	public Date getOpenBidTime() {
		return openBidTime;
	}
	public void setOpenBidTime(Date openBidTime) {
		this.openBidTime = openBidTime;
	}
	
	@NodeMapping(tag="meetRoomAddress")
	public String getMeetRoomAddress() {
		return meetRoomAddress;
	}
	public void setMeetRoomAddress(String meetRoomAddress) {
		this.meetRoomAddress = meetRoomAddress;
	}
}
