/**
 * 
 */
package com.gpcsoft.epp.webService.ueSystem.orgInfo.business.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/**
 * @author liuke
 *
 */
@NodeMapping(tag="body")
public class BusinessDTO {

	private String webUrl;  //企业网址
	private String oEmail;	      //企业EMAIL
	private String mainProduct;      //主营产品
	private String tenderType; //投标范围及类别
	private String ocapacity;  //企业产能
	private String introduction;       //企业简介
	private String achievement;  //公司业绩
	private String lastInput;	 //近一年营业收入
	private String lastTwoInput;   //近两年营业收入
	private String lastThreeInput;  //近三年营业收入
	private String incomeTax;       //企业所得税
	private String profit;          //税后利润
	
	@NodeMapping(tag="webUrl")
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	@NodeMapping(tag="oEmail")
	public String getOEmail() {
		return oEmail;
	}
	public void setOEmail(String email) {
		oEmail = email;
	}
	@NodeMapping(tag="mainProduct")
	public String getMainProduct() {
		return mainProduct;
	}
	public void setMainProduct(String mainProduct) {
		this.mainProduct = mainProduct;
	}
	@NodeMapping(tag="tenderType")
	public String getTenderType() {
		return tenderType;
	}
	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}
	@NodeMapping(tag="ocapacity")
	public String getOcapacity() {
		return ocapacity;
	}
	public void setOcapacity(String ocapacity) {
		this.ocapacity = ocapacity;
	}
	@NodeMapping(tag="introduction")
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	@NodeMapping(tag="achievement")
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	@NodeMapping(tag="lastInput")
	public String getLastInput() {
		return lastInput;
	}
	public void setLastInput(String lastInput) {
		this.lastInput = lastInput;
	}
	@NodeMapping(tag="lastTwoInput")
	public String getLastTwoInput() {
		return lastTwoInput;
	}
	public void setLastTwoInput(String lastTwoInput) {
		this.lastTwoInput = lastTwoInput;
	}
	@NodeMapping(tag="lastThreeInput")
	public String getLastThreeInput() {
		return lastThreeInput;
	}
	public void setLastThreeInput(String lastThreeInput) {
		this.lastThreeInput = lastThreeInput;
	}
	@NodeMapping(tag="incomeTax")
	public String getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(String incomeTax) {
		this.incomeTax = incomeTax;
	}
	@NodeMapping(tag="profit")
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
}
