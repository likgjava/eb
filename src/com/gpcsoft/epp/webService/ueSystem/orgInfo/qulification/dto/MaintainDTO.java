package com.gpcsoft.epp.webService.ueSystem.orgInfo.qulification.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/**
 * 资质主体
 * @author zhouzhanghe
 * @since 2011.3.28 09:30
 *
 */
@NodeMapping(tag="maintain")
public class MaintainDTO {

	private String header;//头
	
	private BodyDTO body;//体

	@NodeMapping(tag="header")
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	@NodeMapping(tag="body")
	public BodyDTO getBody() {
		return body;
	}

	public void setBody(BodyDTO body) {
		this.body = body;
	}
}
