package com.gpcsoft.epp.webService.ueSystem.orgInfo.legal.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

@NodeMapping(tag="body")
public class LegalDTO {
	
	private String orgCode;  //组织机构代码
	private String orgCodeFile;	      //组织机构代码证
	private String orgCodeAuthority;      //发证机关
	private String credibility; //银行信誉度
	private String credibilityFile;  //银行证明文件
	private String businessValDate;       //营业期限
	private String businessFile;       //营业执照
	
	@NodeMapping(tag="orgCode")
	public String getOrgCode() {
		return orgCode;
	}
	
	@NodeMapping(tag="orgCodeFile")
	public String getOrgCodeFile() {
		return orgCodeFile;
	}
	
	@NodeMapping(tag="orgCodeAuthority")
	public String getOrgCodeAuthority() {
		return orgCodeAuthority;
	}
	
	@NodeMapping(tag="credibility")
	public String getCredibility() {
		return credibility;
	}
	
	@NodeMapping(tag="credibilityFile")
	public String getCredibilityFile() {
		return credibilityFile;
	}
	
	@NodeMapping(tag="businessValDate")
	public String getBusinessValDate() {
		return businessValDate;
	}
	
	@NodeMapping(tag="businessFile")
	public String getBusinessFile() {
		return businessFile;
	}
	
	
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public void setOrgCodeFile(String orgCodeFile) {
		this.orgCodeFile = orgCodeFile;
	}
	public void setOrgCodeAuthority(String orgCodeAuthority) {
		this.orgCodeAuthority = orgCodeAuthority;
	}
	public void setCredibility(String credibility) {
		this.credibility = credibility;
	}
	public void setCredibilityFile(String credibilityFile) {
		this.credibilityFile = credibilityFile;
	}
	public void setBusinessValDate(String businessValDate) {
		this.businessValDate = businessValDate;
	}
	public void setBusinessFile(String businessFile) {
		this.businessFile = businessFile;
	}
	

	
	
}
