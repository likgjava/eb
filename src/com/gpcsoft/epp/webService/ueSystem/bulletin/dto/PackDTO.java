package com.gpcsoft.epp.webService.ueSystem.bulletin.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/**
 * 包组信息
 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
 * @since 2011-3-14 15:08
 */
@NodeMapping(tag = "pack")
public class PackDTO{

	private String packCode;//包件编码
	
	private String packName;//包件名称
	
	private String startSignInDateTime;//开始报名时间
	
	private String endSignInDateTime;//结束报名时间
	
	private String createDateTime;//创建时间
	
	private String beginSubmitBidDateTime;//开始投标时间
	
	private String endSubmitBidDateTime;//结束投标时间
	
	private String packStatus;//招标阶段
	
	private String applyStatus;//报名状态
	
	private String tenderStatus;//投标状态
	
	private String packSummary;//采购摘要

	@NodeMapping(tag = "packCode")
	public String getPackCode() {
		return packCode;
	}

	public void setPackCode(String packCode) {
		this.packCode = packCode;
	}

	@NodeMapping(tag = "packName")
	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	@NodeMapping(tag = "startSignInDateTime")
	public String getStartSignInDateTime() {
		return startSignInDateTime;
	}

	public void setStartSignInDateTime(String startSignInDateTime) {
		this.startSignInDateTime = startSignInDateTime;
	}

	@NodeMapping(tag = "endSignInDateTime")
	public String getEndSignInDateTime() {
		return endSignInDateTime;
	}

	public void setEndSignInDateTime(String endSignInDateTime) {
		this.endSignInDateTime = endSignInDateTime;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	@NodeMapping(tag = "beginSubmitBidDateTime")
	public String getBeginSubmitBidDateTime() {
		return beginSubmitBidDateTime;
	}

	public void setBeginSubmitBidDateTime(String beginSubmitBidDateTime) {
		this.beginSubmitBidDateTime = beginSubmitBidDateTime;
	}

	@NodeMapping(tag = "endSubmitBidDateTime")
	public String getEndSubmitBidDateTime() {
		return endSubmitBidDateTime;
	}

	public void setEndSubmitBidDateTime(String endSubmitBidDateTime) {
		this.endSubmitBidDateTime = endSubmitBidDateTime;
	}

	@NodeMapping(tag = "packStatus")
	public String getPackStatus() {
		return packStatus;
	}

	public void setPackStatus(String packStatus) {
		this.packStatus = packStatus;
	}

	@NodeMapping(tag = "applyStatus")
	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	@NodeMapping(tag = "tenderStatus")
	public String getTenderStatus() {
		return tenderStatus;
	}

	public void setTenderStatus(String tenderStatus) {
		this.tenderStatus = tenderStatus;
	}

	@NodeMapping(tag = "packSummary")
	public String getPackSummary() {
		return packSummary;
	}

	public void setPackSummary(String packSummary) {
		this.packSummary = packSummary;
	}
}
