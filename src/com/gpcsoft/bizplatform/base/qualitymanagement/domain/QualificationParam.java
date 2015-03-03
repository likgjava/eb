package com.gpcsoft.bizplatform.base.qualitymanagement.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.gpcsoft.core.tree.TreeProperty;

/** 
 *  Comments: <strong>QualificationParam</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   bizplatform                    					          
 *  <br/>Module ID: 公共服务平台     		
 *  <br/>Create Date：2010-7-27 下午02:57:53 by guoyr    					                            
 *  <br/>Modified Date:  2010-7-27 下午02:57:53 by guoyr                                  
 *   @since 0.5
 *   @version: 0.5 
 *  
 * @gpcsoft.package packageDir="com.gpcsoft.bizplatform.base.qualitymanagement"
 * @gpcsoft.page domain="qualitymanagement" project="base"
 * @gpcsoft.domain
 * @gpcsoft.title value="资质参数"
 */ 

@Entity
@DiscriminatorValue("02")
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@TreeProperty(topIcon="book_titel.gif", nodeIcon="tombs_mag.gif", title="资质参数", text="name", tip="qualification",nodeIconExtend="icon")
public class QualificationParam  extends Qualification{

	private static final long serialVersionUID = -1096034961431870495L;
	
	/**
	 * 参数类型
	 */
	@Column(name = "PARAM_TYPE")
	private String paramType;
	
	/**
	 * 参数单位
	 */
	@Column(name = "UNIT")
	private String unit;

	/**
	 * 是否必填
	 */
	@Column(name = "IS_REQUIRED")
	private Boolean isRequired;
	
	/**
	 * 级别（级别之间用","隔开，每个级别的级别名称和级别值用#隔开）
	 */
	@Column(name = "LEVEL_DATA")
	private String level;

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public Boolean getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	

	
}