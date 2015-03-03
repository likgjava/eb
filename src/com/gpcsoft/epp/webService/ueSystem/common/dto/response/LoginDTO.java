package com.gpcsoft.epp.webService.ueSystem.common.dto.response;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

@NodeMapping(tag="login")
public class LoginDTO {

	private HeaderDTO header = new HeaderDTO();
	
	private BodyDTO body = new BodyDTO();

	@NodeMapping(tag="header")
	public HeaderDTO getHeader() {
		return header;
	}

	public void setHeader(HeaderDTO header) {
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
