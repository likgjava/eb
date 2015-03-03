package com.gpcsoft.epp.webService.ueSystem.common.dto.response;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

@NodeMapping(tag="header")
public class HeaderDTO {

	private String operTag = "Y";//默认操作成功
	
	private String operDesc;

	@NodeMapping(tag="operTag")
	public String getOperTag() {
		return operTag;
	}

	public void setOperTag(String operTag) {
		this.operTag = operTag;
	}

	@NodeMapping(tag="operDesc")
	public String getOperDesc() {
		return operDesc;
	}

	public void setOperDesc(String operDesc) {
		this.operDesc = operDesc;
	}
}
