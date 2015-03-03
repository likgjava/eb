package com.gpcsoft.pubservice.webService.webService.gxoa.ws.dto;

import java.util.List;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/** 
 *  Comments: <strong>Project</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Module ID: 项目    		
 *  @gpcsoft.package packageDir="com.gpcsoft.es.ext.bulletin"
 *  @gpcsoft.page domain="ext/bulletin" project="es"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="项目"  
 *  @since 0.1
 *  @version: 0.1 
 */ 
@NodeMapping(tag="projectInfo")
public class PurchaseFileDTO{
	
	private String projCode;//项目编号
	private String isPack; //是否分包
	private String sellPackType; //文件售卖方式
	private String filePrice; //文件价格（单位：元）
	private String bailPrice; //保证金价格（单位：元）
	private String purchaseDocUrl;//招标文件地址
	private List<PurchaseFileDTO> packs;//包件信息
	
	@NodeMapping(tag="projCode")
	public String getProjCode() {
		return projCode;
	}
	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}
	
	@NodeMapping(tag="isPack")
	public String getIsPack() {
		return isPack;
	}
	public void setIsPack(String isPack) {
		this.isPack = isPack;
	}

	@NodeMapping(tag="sellPackType")
	public String getSellPackType() {
		return sellPackType;
	}
	public void setSellPackType(String sellPackType) {
		this.sellPackType = sellPackType;
	}

	@NodeMapping(tag="filePrice")
	public String getFilePrice() {
		return filePrice;
	}
	public void setFilePrice(String filePrice) {
		this.filePrice = filePrice;
	}

	@NodeMapping(tag="bailPrice")
	public String getBailPrice() {
		return bailPrice;
	}
	public void setBailPrice(String bailPrice) {
		this.bailPrice = bailPrice;
	}
	
	@NodeMapping(tag="purchaseDocUrl")
	public String getPurchaseDocUrl() {
		return purchaseDocUrl;
	}
	public void setPurchaseDocUrl(String purchaseDocUrl) {
		this.purchaseDocUrl = purchaseDocUrl;
	}
	
	public List<PurchaseFileDTO> getPacks() {
		return packs;
	}
	public void setPacks(List<PurchaseFileDTO> packs) {
		this.packs = packs;
	}
}
