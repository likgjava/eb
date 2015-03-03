package com.gpcsoft.epp.webService.ueSystem.bulletin.dto;


public class SignUpResponeDTO{

	private String factorId;//响应指标id 
	
	private String applyFactorFile;//响应文件
	
	private String applyValue;//响应值

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getApplyFactorFile() {
		return applyFactorFile;
	}

	public void setApplyFactorFile(String applyFactorFile) {
		this.applyFactorFile = applyFactorFile;
	}

	public String getApplyValue() {
		return applyValue;
	}

	public void setApplyValue(String applyValue) {
		this.applyValue = applyValue;
	}
	
}
