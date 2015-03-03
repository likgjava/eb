package com.gpcsoft.epp.webService.ueSystem.bulletin.dto;


/**
 * 报名DTO
 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
 * @since 2011-3-15 15:08
 */
public class ApplyProjectDTO{

	private String projCode;//项目编号
	
	private String orgCode;//供应商编码
	
	private String orgName;//供应商名称
	
	private String applyDate;//报名日期
	
	private String linkerMan;//联系人
	
	private String linkTel;//联系电话
	
	private String linkerIdCard;//身份证号码
	
	private String linkAddress;//联系地址
	
	private String zipCode;//邮编
	
	private String applyFile;//报名文件
	
	private String memo;//备注

	public String getProjCode() {
		return projCode;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getLinkerMan() {
		return linkerMan;
	}

	public void setLinkerMan(String linkerMan) {
		this.linkerMan = linkerMan;
	}

	public String getLinkTel() {
		return linkTel;
	}

	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}

	public String getLinkerIdCard() {
		return linkerIdCard;
	}

	public void setLinkerIdCard(String linkerIdCard) {
		this.linkerIdCard = linkerIdCard;
	}

	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getApplyFile() {
		return applyFile;
	}

	public void setApplyFile(String applyFile) {
		this.applyFile = applyFile;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
