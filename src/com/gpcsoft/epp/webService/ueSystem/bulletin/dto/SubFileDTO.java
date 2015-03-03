/**
 * 
 */
package com.gpcsoft.epp.webService.ueSystem.bulletin.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/**
 * @author liuk
 *
 */
@NodeMapping(tag="subFile")
public class SubFileDTO {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	private String docId;//文件名称
	
	private String type; //文件类型
	
	private String hashCode; //文件MD5值
	
	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}
}
