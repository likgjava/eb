/**
 * 
 */
package com.gpcsoft.epp.webService.ueSystem.bulletin.dto;

/**
 * @author liuk
 *
 */
public class PurchaseFileDTO {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	private String fileName;//采购文件名称
	
	private String downUrl;//下载地址
	
	private String filePath; //中间路径
	
	private String viewName; //采购文件实际名称
	
	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}
}
