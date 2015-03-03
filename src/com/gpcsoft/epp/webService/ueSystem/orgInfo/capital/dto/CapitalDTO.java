/**
 * 
 */
package com.gpcsoft.epp.webService.ueSystem.orgInfo.capital.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/**
 * @author liuke
 *
 */
@NodeMapping(tag="body")
public class CapitalDTO {
	private String registerfee;  //注册资金
	private String currentAsset;	      //流动资产
	private String totalAsset;      //总资产
	private String netAsset; //净资产
	private String liabilityRatio;  //资产负债率
	private String fixAsset;       //固定资产	
	
	@NodeMapping(tag="registerfee")
	public String getRegisterfee() {
		return registerfee;
	}
	public void setRegisterfee(String registerfee) {
		this.registerfee = registerfee;
	}
	@NodeMapping(tag="currentAsset")
	public String getCurrentAsset() {
		return currentAsset;
	}
	public void setCurrentAsset(String currentAsset) {
		this.currentAsset = currentAsset;
	}
	@NodeMapping(tag="totalAsset")
	public String getTotalAsset() {
		return totalAsset;
	}
	public void setTotalAsset(String totalAsset) {
		this.totalAsset = totalAsset;
	}
	@NodeMapping(tag="netAsset")
	public String getNetAsset() {
		return netAsset;
	}
	public void setNetAsset(String netAsset) {
		this.netAsset = netAsset;
	}
	@NodeMapping(tag="liabilityRatio")
	public String getLiabilityRatio() {
		return liabilityRatio;
	}
	public void setLiabilityRatio(String liabilityRatio) {
		this.liabilityRatio = liabilityRatio;
	}
	@NodeMapping(tag="fixAsset")
	public String getFixAsset() {
		return fixAsset;
	}
	public void setFixAsset(String fixAsset) {
		this.fixAsset = fixAsset;
	}
}
