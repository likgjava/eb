package com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto;

import java.util.List;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

@NodeMapping(tag="ecpTenderProject")
public class ProjectDTO{
	private String tenderiId;//项目/包Id
	private String tenderNo;//项目编号
	private String tenderName;//项目名称
	private String tenderContent;//招标内容
	private String tenderSummary;//摘要
	private String agenciesId;//代理机构ID
	private String agenciesName;//代理机构名称
	private String tenderType;//项目分类[00:非大宗;01:大宗]
	private String projectType;//工程类型[1=施工2=监理3=资格预审]
	private String tenderBudgetTotalMoney;//预算总金额
	private String purcategoryIds;//品目ID
	private String purcategoryNames;//品目	
	private String tenderMethod;//采购方式
	private String goodsclassIds;//商品分类id
	private String goodsclassNames; //商品分类
	private String tenderUrl;//招标文件地址
	private String buyerIds;
	private String buyerNames;//招标人名称
	private String openBidTime;//开标时间
	private String openBidAddr;//开标地点
	private String evalStartTime;//评标开始时间
	private String evalEndTime;//评标结束时间
	private String evlRoom;//评标室	
	private String projManager; //项目经办人
	private String prjMonitor;//项目监管
	private List<ProjectDTO> subProjectList;//包组信息
	
	@NodeMapping(tag="tenderiId")
	public String getTenderiId() {
		return tenderiId;
	}
	public void setTenderiId(String tenderiId) {
		this.tenderiId = tenderiId;
	}
	@NodeMapping(tag="tenderNo")
	public String getTenderNo() {
		return tenderNo;
	}
	public void setTenderNo(String tenderNo) {
		this.tenderNo = tenderNo;
	}
	
	@NodeMapping(tag="tenderName")
	public String getTenderName() {
		return tenderName;
	}
	public void setTenderName(String tenderName) {
		this.tenderName = tenderName;
	}
	
	@NodeMapping(tag="tenderContent")
	public String getTenderContent() {
		return tenderContent;
	}
	public void setTenderContent(String tenderContent) {
		this.tenderContent = tenderContent;
	}
	@NodeMapping(tag="tenderSummary")
	public String getTenderSummary() {
		return tenderSummary;
	}
	public void setTenderSummary(String tenderSummary) {
		this.tenderSummary = tenderSummary;
	}
	@NodeMapping(tag="agenciesId")
	public String getAgenciesId() {
		return agenciesId;
	}
	public void setAgenciesId(String agenciesId) {
		this.agenciesId = agenciesId;
	}
	@NodeMapping(tag="tenderType")
	public String getTenderType() {
		return tenderType;
	}
	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}
	@NodeMapping(tag="tenderBudgetTotalMoney")
	public String getTenderBudgetTotalMoney() {
		return tenderBudgetTotalMoney;
	}
	public void setTenderBudgetTotalMoney(String tenderBudgetTotalMoney) {
		this.tenderBudgetTotalMoney = tenderBudgetTotalMoney;
	}
	@NodeMapping(tag="purcategoryIds")
	public String getPurcategoryIds() {
		return purcategoryIds;
	}
	public void setPurcategoryIds(String purcategoryIds) {
		this.purcategoryIds = purcategoryIds;
	}
	@NodeMapping(tag="purcategoryNames")
	public String getPurcategoryNames() {
		return purcategoryNames;
	}
	public void setPurcategoryNames(String purcategoryNames) {
		this.purcategoryNames = purcategoryNames;
	}
	@NodeMapping(tag="goodsclassIds")
	public String getGoodsclassIds() {
		return goodsclassIds;
	}
	public void setGoodsclassIds(String goodsclassIds) {
		this.goodsclassIds = goodsclassIds;
	}
	@NodeMapping(tag="goodsclassNames")
	public String getGoodsclassNames() {
		return goodsclassNames;
	}
	public void setGoodsclassNames(String goodsclassNames) {
		this.goodsclassNames = goodsclassNames;
	}
	@NodeMapping(tag="tenderUrl")
	public String getTenderUrl() {
		return tenderUrl;
	}
	public void setTenderUrl(String tenderUrl) {
		this.tenderUrl = tenderUrl;
	}
	
	public List<ProjectDTO> getSubProjectList() {
		return subProjectList;
	}
	public void setSubProjectList(List<ProjectDTO> subProjectList) {
		this.subProjectList = subProjectList;
	}
	
	@NodeMapping(tag="tenderMethod")
	public String getTenderMethod() {
		return tenderMethod;
	}
	public void setTenderMethod(String tenderMethod) {
		this.tenderMethod = tenderMethod;
	}
	
	@NodeMapping(tag="buyerIds")
	public String getBuyerIds() {
		return buyerIds;
	}
	public void setBuyerIds(String buyerIds) {
		this.buyerIds = buyerIds;
	}
	
	@NodeMapping(tag="buyerNames")
	public String getBuyerNames() {
		return buyerNames;
	}
	public void setBuyerNames(String buyerNames) {
		this.buyerNames = buyerNames;
	}
	
	@NodeMapping(tag="openBidTime")
	public String getOpenBidTime() {
		return openBidTime;
	}
	public void setOpenBidTime(String openBidTime) {
		this.openBidTime = openBidTime;
	}
	
	@NodeMapping(tag="openBidAddr")
	public String getOpenBidAddr() {
		return openBidAddr;
	}
	public void setOpenBidAddr(String openBidAddr) {
		this.openBidAddr = openBidAddr;
	}
	
	@NodeMapping(tag="evalStartTime")
	public String getEvalStartTime() {
		return evalStartTime;
	}
	public void setEvalStartTime(String evalStartTime) {
		this.evalStartTime = evalStartTime;
	}
	
	@NodeMapping(tag="evalEndTime")
	public String getEvalEndTime() {
		return evalEndTime;
	}
	public void setEvalEndTime(String evalEndTime) {
		this.evalEndTime = evalEndTime;
	}
	@NodeMapping(tag="agenciesName")
	public String getAgenciesName() {
		return agenciesName;
	}
	public void setAgenciesName(String agenciesName) {
		this.agenciesName = agenciesName;
	}
	@NodeMapping(tag="projManager")
	public String getProjManager() {
		return projManager;
	}
	public void setProjManager(String projManager) {
		this.projManager = projManager;
	}
	
	@NodeMapping(tag="projectType")
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	@NodeMapping(tag="evlRoom")
	public String getEvlRoom() {
		return evlRoom;
	}
	public void setEvlRoom(String evlRoom) {
		this.evlRoom = evlRoom;
	}
	
	@NodeMapping(tag="prjMonitor")
	public String getPrjMonitor() {
		return prjMonitor;
	}
	public void setPrjMonitor(String prjMonitor) {
		this.prjMonitor = prjMonitor;
	}
	
	
}
