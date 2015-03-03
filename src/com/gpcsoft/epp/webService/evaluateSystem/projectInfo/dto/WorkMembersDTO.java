package com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/**
 * @author shenjz
 *
 */
@NodeMapping(tag="ecpWorkgMember")
public class WorkMembersDTO{
	private String workgmId;//专家ID
	private String subProjId;//项目/包组Id
	private String workgmName;//专家名称
	private String workgmDuty;//职称
	private String workgmSpeciality;//职业
	private String workgmLinkerPhone;//专家联系方式
	private String workgmOrgName;//所属单位
	private String isLeader;//是否组长[1：是；0：否]
	private String isAmount;//是否正选[1：是；0：否]
	private String idCard;//身份证号
	private String workgmType; // 成员类型：[00：采购人代表;01：专家;02:经办人；03：监察人员等]
	private String signinStatus; //签到状态[00:未签到;01：已签到]
	
	@NodeMapping(tag="workgmId")
	public String getWorkgmId() {
		return workgmId;
	}
	public void setWorkgmId(String workgmId) {
		this.workgmId = workgmId;
	}
	@NodeMapping(tag="subProjId")
	public String getSubProjId() {
		return subProjId;
	}
	public void setSubProjId(String subProjId) {
		this.subProjId = subProjId;
	}
	@NodeMapping(tag="workgmName")
	public String getWorkgmName() {
		return workgmName;
	}
	public void setWorkgmName(String workgmName) {
		this.workgmName = workgmName;
	}
	@NodeMapping(tag="workgmDuty")
	public String getWorkgmDuty() {
		return workgmDuty;
	}
	public void setWorkgmDuty(String workgmDuty) {
		this.workgmDuty = workgmDuty;
	}
	@NodeMapping(tag="workgmSpeciality")
	public String getWorkgmSpeciality() {
		return workgmSpeciality;
	}
	public void setWorkgmSpeciality(String workgmSpeciality) {
		this.workgmSpeciality = workgmSpeciality;
	}
	@NodeMapping(tag="workgmLinkerPhone")
	public String getWorkgmLinkerPhone() {
		return workgmLinkerPhone;
	}
	public void setWorkgmLinkerPhone(String workgmLinkerPhone) {
		this.workgmLinkerPhone = workgmLinkerPhone;
	}
	@NodeMapping(tag="workgmOrgName")
	public String getWorkgmOrgName() {
		return workgmOrgName;
	}
	public void setWorkgmOrgName(String workgmOrgName) {
		this.workgmOrgName = workgmOrgName;
	}
	@NodeMapping(tag="isLeader")
	public String getIsLeader() {
		return isLeader;
	}
	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}
	@NodeMapping(tag="isAmount")
	public String getIsAmount() {
		return isAmount;
	}
	public void setIsAmount(String isAmount) {
		this.isAmount = isAmount;
	}
	@NodeMapping(tag="idCard")
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	@NodeMapping(tag="workgmType")
	public String getWorkgmType() {
		return workgmType;
	}
	public void setWorkgmType(String workgmType) {
		this.workgmType = workgmType;
	}
	
	@NodeMapping(tag="signinStatus")
	public String getSigninStatus() {
		return signinStatus;
	}
	public void setSigninStatus(String signinStatus) {
		this.signinStatus = signinStatus;
	}
	
}
