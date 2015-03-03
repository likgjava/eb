package com.gpcsoft.agreement.ftl.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.agreement.ftl.enumeration.FtlTemplateEnum;
import com.gpcsoft.core.model.GpcBaseObject;

/** 
  *  Comments: <strong>FtlTemplateProperty</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-12-1 下午07:25:44 by guoyr    					                            
  *  <br/>Modified Date:  2010-12-1 下午07:25:44 by guoyr   
  *  @gpcsoft.package packageDir="com.gpcsoft.agreement.ftl"
  *  @gpcsoft.page domain="ftl"  project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="模板属性"                              
  *  @since 0.5
  *  @version: 0.5 
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "FTL_TEMPLATE_PROPERTY")
public class FtlTemplateProperty implements GpcBaseObject {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "FTL_TEMPLATE_PROPERTY_ID", unique = true, nullable = false, length = 50)
	private String objId;
	
	/**属名名称*/
	@Column(name = "PROPERTY_TITLE", length = 100)
	private String propertyTitle;
	
	/**属性值*/
	@Column(name = "PROPERTY_VALUE", length = 1000)
	private String propertyValue;
	
	/**排序*/
	@Column(name = "SORT")
	private Long sort;
	
	/**模板类型*/
	@Column(name = "FTL_TYPE", length = 2)
	private String ftlType;
	
	/**模板类型*/
	@Transient
	private String ftlTypeCN;
	
	

	public void setObjId(String objId){
		this.objId = objId;
	}
	
	public String getObjId() {
		return this.objId;
	}

	public String getFtlTypeCN() {
		ftlTypeCN  = FtlTemplateEnum.getFtlTypeCN(ftlType);
		return ftlTypeCN;
	}

	public void setFtlTypeCN(String ftlTypeCN) {
		this.ftlTypeCN = ftlTypeCN;
	}

	public String getPropertyTitle() {
		return propertyTitle;
	}

	public void setPropertyTitle(String propertyTitle) {
		this.propertyTitle = propertyTitle;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}


	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public String getFtlType() {
		return ftlType;
	}

	public void setFtlType(String ftlType) {
		this.ftlType = ftlType;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date arg0) {
		
	}

}