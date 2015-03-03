package com.gpcsoft.epp.webService.ueSystem.bulletin.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/**
 * 项目条件
 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
 * @since 2011-3-14 15:08
 */
@NodeMapping(tag = "factor")
public class FactorDTO{

	private String factorID;//报名条件记录号
	
	private String factorName;//报名条件
	
	private String factorRemark;//备注

	@NodeMapping(tag = "factorID")
	public String getFactorID() {
		return factorID;
	}

	public void setFactorID(String factorID) {
		this.factorID = factorID;
	}

	@NodeMapping(tag = "factorName")
	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	@NodeMapping(tag = "factorRemark")
	public String getFactorRemark() {
		return factorRemark;
	}

	public void setFactorRemark(String factorRemark) {
		this.factorRemark = factorRemark;
	}
}
