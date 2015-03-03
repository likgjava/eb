package com.gpcsoft.epp.webService.ueSystem.orgInfo.business.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

@NodeMapping(tag="business")
public class DownBusinessDTO {
	
	private String webUrl;  //企业网址
	private String oemail;	//企业EMAIL
	private String mainProduct;   //主营产品
	private String tenderType;	//投标范围及类别资产
	private String ocapacity;  //企业产能
	private String introduction;    //企业简介
	private String achievement;  //公司业绩
	private String lastInput;	//近一年营业收入
	private String lastTwoInput;   //近两年营业收入
	private String lastThreeInput;	//近三年营业收入
	private String incomeTax;  //企业所得税
	private String profit;  //税后利润
	
	@NodeMapping(tag="webUrl")
	public String getWebUrl() {
		return webUrl;
	}
	
	@NodeMapping(tag="oemail")
	public String getOemail() {
		return oemail;
	}
	
	@NodeMapping(tag="mainProduct")
	public String getMainProduct() {
		return mainProduct;
	}
	
	@NodeMapping(tag="tenderType")
	public String getTenderType() {
		return tenderType;
	}
	
	@NodeMapping(tag="ocapacity")
	public String getOcapacity() {
		return ocapacity;
	}

	@NodeMapping(tag="introduction")
	public String getIntroduction() {
		return introduction;
	}
	
	@NodeMapping(tag="achievement")
	public String getAchievement() {
		return achievement;
	}
	
	@NodeMapping(tag="lastInput")
	public String getLastInput() {
		return lastInput;
	}
	
	@NodeMapping(tag="lastTwoInput")
	public String getLastTwoInput() {
		return lastTwoInput;
	}
	
	@NodeMapping(tag="lastThreeInput")
	public String getLastThreeInput() {
		return lastThreeInput;
	}
	
	@NodeMapping(tag="incomeTax")
	public String getIncomeTax() {
		return incomeTax;
	}
	
	@NodeMapping(tag="profit")
	public String getProfit() {
		return profit;
	}
	
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public void setOemail(String oemail) {
		this.oemail = oemail;
	}
	public void setMainProduct(String mainProduct) {
		this.mainProduct = mainProduct;
	}
	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}
	public void setOcapacity(String ocapacity) {
		this.ocapacity = ocapacity;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	public void setLastInput(String lastInput) {
		this.lastInput = lastInput;
	}
	public void setLastTwoInput(String lastTwoInput) {
		this.lastTwoInput = lastTwoInput;
	}
	public void setLastThreeInput(String lastThreeInput) {
		this.lastThreeInput = lastThreeInput;
	}
	public void setIncomeTax(String incomeTax) {
		this.incomeTax = incomeTax;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}        
	
	
}
