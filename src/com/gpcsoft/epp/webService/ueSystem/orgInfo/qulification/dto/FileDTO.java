package com.gpcsoft.epp.webService.ueSystem.orgInfo.qulification.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/**
 * 资质文件
 * @author zhouzhanghe
 * @since 2011.3.28 09:30
 */
@NodeMapping(tag="file")
public class FileDTO {
	
	private String id;

	private String name;

	@NodeMapping(tag="id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NodeMapping(tag="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
