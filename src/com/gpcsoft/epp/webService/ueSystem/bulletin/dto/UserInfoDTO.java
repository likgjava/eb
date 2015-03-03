/**
 * 
 */
package com.gpcsoft.epp.webService.ueSystem.bulletin.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/**
 * @author liuk
 *
 */
@NodeMapping(tag="userInfo")
public class UserInfoDTO {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	private String userCode;//用户名
	
	private String orgCode;//组织机构代码
	
	private String orgName;//机构名称
	
	private String userName;//姓名
	
	private String userId; //用户ID
	
	private String transactionCertNo ; //建筑工程交易证号
	
	private String legalDelegate ; //企业法人代表
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTransactionCertNo() {
		return transactionCertNo;
	}

	public void setTransactionCertNo(String transactionCertNo) {
		this.transactionCertNo = transactionCertNo;
	}

	public String getLegalDelegate() {
		return legalDelegate;
	}

	public void setLegalDelegate(String legalDelegate) {
		this.legalDelegate = legalDelegate;
	}
}
