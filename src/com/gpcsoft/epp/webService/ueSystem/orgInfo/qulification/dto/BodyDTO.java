package com.gpcsoft.epp.webService.ueSystem.orgInfo.qulification.dto;

import java.util.List;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

@NodeMapping(tag="body")
public class BodyDTO {

	private List<QualificationDTO> qualificationList;

	@NodeMapping(tag="qualificationlist")
	public List<QualificationDTO> getQualificationList() {
		return qualificationList;
	}

	public void setQualificationList(List<QualificationDTO> qualificationList) {
		this.qualificationList = qualificationList;
	}
}
