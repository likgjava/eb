package com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/**
 * @author shenjz
 *
 */
@NodeMapping(tag="ecpTenderApplyRec")
public class ApplyDTO{
	
	private String applyId;//报名记录表ID
	private String tenderId;//项目主键
	private String supplyerId;//供应商主键 
	private String supplyerName;//供应商名称
	private String orgCode;    //供应商编码
	
	private String applyDate;//报名时间
	private String linker;//联系人
	private String idCard;//联系人身份证号
	private String linkerTel;//联系电话
	private String address;//联系地址
	private String zipCode;//邮政编码
	private String memo;//描述
	private String tenderTime;//投标时间
	private String applyUrl;//供应商投标文件地址
	private String projManager;//项目经理
	private String bailRecord;//是否缴纳保证金
	private String bailRecordAmount;//保证金金额
	
	@NodeMapping(tag="applyId")
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	@NodeMapping(tag="tenderId")
	public String getTenderId() {
		return tenderId;
	}
	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}
	@NodeMapping(tag="supplyerId")
	public String getSupplyerId() {
		return supplyerId;
	}
	public void setSupplyerId(String supplyerId) {
		this.supplyerId = supplyerId;
	}
	@NodeMapping(tag="supplyerName")
	public String getSupplyerName() {
		return supplyerName;
	}
	public void setSupplyerName(String supplyerName) {
		this.supplyerName = supplyerName;
	}
	@NodeMapping(tag="applyDate")
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	@NodeMapping(tag="linker")
	public String getLinker() {
		return linker;
	}
	public void setLinker(String linker) {
		this.linker = linker;
	}
	@NodeMapping(tag="idCard")
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	@NodeMapping(tag="linkerTel")
	public String getLinkerTel() {
		return linkerTel;
	}
	public void setLinkerTel(String linkerTel) {
		this.linkerTel = linkerTel;
	}
	@NodeMapping(tag="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@NodeMapping(tag="zipCode")
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@NodeMapping(tag="memo")
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@NodeMapping(tag="tenderTime")
	public String getTenderTime() {
		return tenderTime;
	}
	public void setTenderTime(String tenderTime) {
		this.tenderTime = tenderTime;
	}
	@NodeMapping(tag="applyUrl")
	public String getApplyUrl() {
		return applyUrl;
	}
	public void setApplyUrl(String applyUrl) {
		this.applyUrl = applyUrl;
	}
	
	@NodeMapping(tag="projManager")
	public String getProjManager() {
		return projManager;
	}
	public void setProjManager(String projManager) {
		this.projManager = projManager;
	}
	
	@NodeMapping(tag="bailRecord")
	public String getBailRecord() {
		return bailRecord;
	}
	public void setBailRecord(String bailRecord) {
		this.bailRecord = bailRecord;
	}
	@NodeMapping(tag="orgCode")
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	@NodeMapping(tag="bailRecordAmount")
	public String getBailRecordAmount() {
		return bailRecordAmount;
	}
	public void setBailRecordAmount(String bailRecordAmount) {
		this.bailRecordAmount = bailRecordAmount;
	}
	
}
