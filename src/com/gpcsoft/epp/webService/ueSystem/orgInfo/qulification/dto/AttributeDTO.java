package com.gpcsoft.epp.webService.ueSystem.orgInfo.qulification.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

@NodeMapping(tag="attribute")
public class AttributeDTO {

	private String code;
	
	private String value;

	@NodeMapping(tag="code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@NodeMapping(tag="value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
