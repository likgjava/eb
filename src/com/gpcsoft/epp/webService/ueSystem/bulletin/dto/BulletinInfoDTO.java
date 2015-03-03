package com.gpcsoft.epp.webService.ueSystem.bulletin.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/**
 * 公告详情
 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
 * @since 2011-3-14 15:08
 */
@NodeMapping(tag="bulletin")
public class BulletinInfoDTO {
	private String bulletinCode;//公告编号
	
	private String bulletinName;//公告名称
	
	private String prjNo;//关联项目编号
	
	private String prjName;//关联项目名称
	
	private String prjType;//关联项目类型
	
	private String purType;//关联项目采购方式
	
	private String packNo;//关联包编号
	
	private String packName;//关联包名称
	
	private String bulletinType;//公告类型
	
	private String content;//公告内容
	
	private String signupStartDate;//报名开始时间
	
	private String signupEndDate;//报名结束时间
	
	private String submitStartDate;//投标开始时间
	
	private String submitEndDate;//投标结束时间
	
	private String relDate;//发布时间
	
	private String bulletinFile;//公告文件
	
	private String fromUrl;//系统来源
	
	private String purCategoryNos;//采购品目编号
	
	private String purCategoryNames;//采购品目名称
	
	private String purAreaNos;//采购区域编号
	
	private String purAreaNames;//采购区域
	
	private String handler;//经办人
	
	private String budget;//项目预算
	
	private String prjStatus;//项目阶段
	
	private String applyStatus;//报名状态
	
	private String tenderStatus;//报名状态
	
	private String linker;//联系人
	
	private String linkTel;//联系电话
	
	private String linkAddress;//联系地址
	
	private String linkId;//在线沟通号
	
	private String prjSummary;//采购摘要
	
	private String applyUrl;//报名地址
	
	private String downUrl;//下载采购文件地址
	
	private String tenderUrl;//投标地址
	
	private String tenderFileUrl;//投标文件地址
	
	private String loginServiceUrl;//登录地址
	
	private String commonServerUrl ; //公共服务地址
	
	private String applyType ; //报名方式
	
	private String purchaseOrgName;
	
	private String angentName;
	
	private String projSummary;
	
	private String tenderType;//投标方式（anonymous匿名投标 open非匿名投标）
	
	private String isPack;//是否分包
	
	private String openBidUrl;//开标地址
	
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
	public String getPrjNo() {
		return prjNo;
	}

	public void setPrjNo(String prjNo) {
		this.prjNo = prjNo;
	}

	@NodeMapping(tag="prjName")
	public String getPrjName() {
		return prjName;
	}

	public void setPrjName(String prjName) {
		this.prjName = prjName;
	}

	@NodeMapping(tag="packNo")
	public String getPackNo() {
		return packNo;
	}

	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}

	@NodeMapping(tag="packName")
	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	@NodeMapping(tag="bulletinType")
	public String getBulletinType() {
		return bulletinType;
	}

	public void setBulletinType(String bulletinType) {
		this.bulletinType = bulletinType;
	}

	@NodeMapping(tag="content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	@NodeMapping(tag="submitStartDate")
	public String getSubmitStartDate() {
		return submitStartDate;
	}

	public void setSubmitStartDate(String submitStartDate) {
		this.submitStartDate = submitStartDate;
	}

	@NodeMapping(tag="submitEndDate")
	public String getSubmitEndDate() {
		return submitEndDate;
	}

	public void setSubmitEndDate(String submitEndDate) {
		this.submitEndDate = submitEndDate;
	}

	@NodeMapping(tag="relDate")
	public String getRelDate() {
		return relDate;
	}

	public void setRelDate(String relDate) {
		this.relDate = relDate;
	}

	@NodeMapping(tag="bulletinFile")
	public String getBulletinFile() {
		return bulletinFile;
	}

	public void setBulletinFile(String bulletinFile) {
		this.bulletinFile = bulletinFile;
	}

	@NodeMapping(tag="fromUrl")
	public String getFromUrl() {
		return fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	@NodeMapping(tag="purCategoryNos")
	public String getPurCategoryNos() {
		return purCategoryNos;
	}

	public void setPurCategoryNos(String purCategoryNos) {
		this.purCategoryNos = purCategoryNos;
	}

	@NodeMapping(tag="purCategoryNames")
	public String getPurCategoryNames() {
		return purCategoryNames;
	}

	public void setPurCategoryNames(String purCategoryNames) {
		this.purCategoryNames = purCategoryNames;
	}

	@NodeMapping(tag="purAreaNos")
	public String getPurAreaNos() {
		return purAreaNos;
	}

	public void setPurAreaNos(String purAreaNos) {
		this.purAreaNos = purAreaNos;
	}

	@NodeMapping(tag="purAreaNames")
	public String getPurAreaNames() {
		return purAreaNames;
	}

	public void setPurAreaNames(String purAreaNames) {
		this.purAreaNames = purAreaNames;
	}

	@NodeMapping(tag="handler")
	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	@NodeMapping(tag="budget")
	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	@NodeMapping(tag="prjStatus")
	public String getPrjStatus() {
		return prjStatus;
	}

	public void setPrjStatus(String prjStatus) {
		this.prjStatus = prjStatus;
	}

	@NodeMapping(tag="applyStatus")
	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	@NodeMapping(tag="linker")
	public String getLinker() {
		return linker;
	}

	public void setLinker(String linker) {
		this.linker = linker;
	}

	@NodeMapping(tag="linkTel")
	public String getLinkTel() {
		return linkTel;
	}

	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}

	@NodeMapping(tag="linkAddress")
	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	@NodeMapping(tag="linkId")
	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	@NodeMapping(tag="prjSummary")
	public String getPrjSummary() {
		return prjSummary;
	}

	public void setPrjSummary(String prjSummary) {
		this.prjSummary = prjSummary;
	}

	@NodeMapping(tag="applyUrl")
	public String getApplyUrl() {
		return applyUrl;
	}

	public void setApplyUrl(String applyUrl) {
		this.applyUrl = applyUrl;
	}

	@NodeMapping(tag="tenderStatus")
	public String getTenderStatus() {
		return tenderStatus;
	}

	public void setTenderStatus(String tenderStatus) {
		this.tenderStatus = tenderStatus;
	}
	
	@NodeMapping(tag="downUrl")
	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	@NodeMapping(tag="tenderUrl")
	public String getTenderUrl() {
		return tenderUrl;
	}

	public void setTenderUrl(String tenderUrl) {
		this.tenderUrl = tenderUrl;
	}

	@NodeMapping(tag="tenderFileUrl")
	public String getTenderFileUrl() {
		return tenderFileUrl;
	}

	public void setTenderFileUrl(String tenderFileUrl) {
		this.tenderFileUrl = tenderFileUrl;
	}
	
	@NodeMapping(tag="loginServiceUrl")
	public String getLoginServiceUrl() {
		return loginServiceUrl;
	}

	public void setLoginServiceUrl(String loginServiceUrl) {
		this.loginServiceUrl = loginServiceUrl;
	}
	
	@NodeMapping(tag="prjType")
	public String getPrjType() {
		return prjType;
	}

	public void setPrjType(String prjType) {
		this.prjType = prjType;
	}
	
	@NodeMapping(tag="purType")
	public String getPurType() {
		return purType;
	}

	public void setPurType(String purType) {
		this.purType = purType;
	}
	
	@NodeMapping(tag="commonServerUrl")
	public String getCommonServerUrl() {
		return commonServerUrl;
	}

	public void setCommonServerUrl(String commonServerUrl) {
		this.commonServerUrl = commonServerUrl;
	}
	
	@NodeMapping(tag="applyType")
	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	
	@NodeMapping(tag="purchaseOrgName")
	public String getPurchaseOrgName() {
		return purchaseOrgName;
	}

	public void setPurchaseOrgName(String purchaseOrgName) {
		this.purchaseOrgName = purchaseOrgName;
	}
	
	@NodeMapping(tag="angentName")
	public String getAngentName() {
		return angentName;
	}

	public void setAngentName(String angentName) {
		this.angentName = angentName;
	}
	
	@NodeMapping(tag="projSummary")
	public String getProjSummary() {
		return projSummary;
	}

	public void setProjSummary(String projSummary) {
		this.projSummary = projSummary;
	}
	
	@NodeMapping(tag="tenderType")
	public String getTenderType() {
		return tenderType;
	}

	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}

	@NodeMapping(tag="isPack")
	public String getIsPack() {
		return isPack;
	}

	public void setIsPack(String isPack) {
		this.isPack = isPack;
	}

	@NodeMapping(tag="openBidUrl")
	public String getOpenBidUrl() {
		return openBidUrl;
	}

	public void setOpenBidUrl(String openBidUrl) {
		this.openBidUrl = openBidUrl;
	}
}
