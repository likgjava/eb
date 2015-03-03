package com.gpcsoft.pubservice.webService.webService.gxoa.ws.dto;

import java.util.Date;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/** 
 *  Comments: <strong>Project</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Module ID: 项目    		
 *  @gpcsoft.package packageDir="com.gpcsoft.es.ext.bulletin"
 *  @gpcsoft.page domain="ext/bulletin" project="es"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="项目"  
 *  @since 0.1
 *  @version: 0.1 
 */ 
@NodeMapping(tag="projectInfo")
public class ProjectDTO{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private String projCode;//项目编号
	private String projName;//项目名称
	private String ebuyMethod; //招标方式（如公开，邀请）
	private String content; //招标内容
	private String buyerId; //委托单位（委托单位的机构代码）
	private String buyerLinkerName; //预算单位联系人
	private String buyerLinkerPhone; //预算单位联系电话
	private String categoryCode; //招标类型（采购品目，如货物类）
	private Date createDate; //立项日期
	private String department; //立项部门（部门编码，类似机构代码）
	private String totalBudget; //项目投资规模（单位：元）
	private Date entrustDate; //委托日期
	
	@NodeMapping(tag="projCode")
	public String getProjCode() {
		return projCode;
	}
	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}

	@NodeMapping(tag="projName")
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}

	@NodeMapping(tag="ebuyMethod")
	public String getEbuyMethod() {
		return ebuyMethod;
	}
	public void setEbuyMethod(String ebuyMethod) {
		this.ebuyMethod = ebuyMethod;
	}

	@NodeMapping(tag="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@NodeMapping(tag="buyerId")
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	@NodeMapping(tag="buyerLinkerName")
	public String getBuyerLinkerName() {
		return buyerLinkerName;
	}
	public void setBuyerLinkerName(String buyerLinkerName) {
		this.buyerLinkerName = buyerLinkerName;
	}

	@NodeMapping(tag="buyerLinkerPhone")
	public String getBuyerLinkerPhone() {
		return buyerLinkerPhone;
	}
	public void setBuyerLinkerPhone(String buyerLinkerPhone) {
		this.buyerLinkerPhone = buyerLinkerPhone;
	}

	@NodeMapping(tag="categoryCode")
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@NodeMapping(tag="createDate")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@NodeMapping(tag="department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	@NodeMapping(tag="totalBudget")
	public String getTotalBudget() {
		return totalBudget;
	}
	public void setTotalBudget(String totalBudget) {
		this.totalBudget = totalBudget;
	}

	@NodeMapping(tag="entrustDate")
	public Date getEntrustDate() {
		return entrustDate;
	}
	public void setEntrustDate(Date entrustDate) {
		this.entrustDate = entrustDate;
	}
}
