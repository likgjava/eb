package com.gpcsoft.epp.webService.ueSystem.orgInfo.qulification.dto;

import java.util.List;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/**
 * 资质Dto
 * @author zhouzhanghe
 * @since 2011.3.28 09:29
 */
@NodeMapping(tag="qualification")
public class QualificationDTO {

	private String name;//资质名称
	
	private String code;//资质编号
	
	private String qualificationCode;//资质定义编号
	
	private List<FileDTO> fileList;//资质文件
	
	private List<AttributeDTO> attributeList;//资质参数

	@NodeMapping(tag="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NodeMapping(tag="fileList")
	public List<FileDTO> getFileList() {
		return fileList;
	}

	@NodeMapping(tag="code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@NodeMapping(tag="qualificationCode")
	public String getQualificationCode() {
		return qualificationCode;
	}

	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}

	public void setFileList(List<FileDTO> fileList) {
		this.fileList = fileList;
	}

	@NodeMapping(tag="attributeList")
	public List<AttributeDTO> getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(List<AttributeDTO> attributeList) {
		this.attributeList = attributeList;
	} 
}
