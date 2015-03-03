package com.gpcsoft.epp.webService.ueSystem.bulletin.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/** 
 *  Comments: <strong>Project</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Module ID: 公告    		
 *  @gpcsoft.package packageDir="com.gpcsoft.es.ext.bulletin"
 *  @gpcsoft.page domain="ext/bulletin" project="es"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="对外公告"  
 *  @since 0.1
 *  @version: 0.1 
 */ 
@NodeMapping(tag="bulletin")
public class BulletinDTO{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
 
	private String bulletinCode;//公告编号
	
	private String bulletinName;//公告名称
	
	private String projectNo; //关联项目编号
	 
	private String bulletinType; //公告类型
	
	private String relDate; //发布时间
	
	private String fromUrl; //系统来源
	
	private String purCategoryNos; //采购品目编号
	
	private String purAreaNos; //采购区域编号
	
	private String signupStartDate;//报名开始时间
	
	private String signupEndDate;//报名结束时间
	
	@NodeMapping(tag="bulletinCode" , notNull = true)
	public String getBulletinCode() {
		return bulletinCode;
	}

	public void setBulletinCode(String bulletinCode) {
		this.bulletinCode = bulletinCode;
	}

	@NodeMapping(tag="bulletinName")
	public String getBulletinName() {
		return bulletinName;
	}

	public void setBulletinName(String bulletinName) {
		this.bulletinName = bulletinName;
	}

	@NodeMapping(tag="prjCode")
	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@NodeMapping(tag="bulletinType")
	public String getBulletinType() {
		return bulletinType;
	}

	public void setBulletinType(String bulletinType) {
		this.bulletinType = bulletinType;
	}

	@NodeMapping(tag="releaseDate")
	public String getRelDate() {
		return relDate;
	}

	public void setRelDate(String relDate) {
		this.relDate = relDate;
	}

	@NodeMapping(tag="fromUrl")
	public String getFromUrl() {
		return fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	@NodeMapping(tag="purCategoryCodes")
	public String getPurCategoryNos() {
		return purCategoryNos;
	}

	public void setPurCategoryNos(String purCategoryNos) {
		this.purCategoryNos = purCategoryNos;
	}

	@NodeMapping(tag="purAreaCode")
	public String getPurAreaNos() {
		return purAreaNos;
	}

	public void setPurAreaNos(String purAreaNos) {
		this.purAreaNos = purAreaNos;
	}
	
	@NodeMapping(tag="signupStartDate")
	public String getSignupStartDate() {
		return signupStartDate;
	}

	public void setSignupStartDate(String signupStartDate) {
		this.signupStartDate = signupStartDate;
	}
	
	@NodeMapping(tag="signupEndDate")
	public String getSignupEndDate() {
		return signupEndDate;
	}

	public void setSignupEndDate(String signupEndDate) {
		this.signupEndDate = signupEndDate;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
