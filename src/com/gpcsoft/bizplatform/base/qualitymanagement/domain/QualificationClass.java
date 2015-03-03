package com.gpcsoft.bizplatform.base.qualitymanagement.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.annotations.Formula;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

import com.gpcsoft.core.tree.TreeProperty;

/** 
 *  Comments: <strong>QualificationClass</strong>            		
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
 * @gpcsoft.title value="资质类型"
 */ 

@Entity
@DiscriminatorValue("00")
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@TreeProperty(topIcon="book_titel.gif", nodeIcon="book_titel.gif", title="资质分类", text="qualityName", tip="qualification",nodeIconExtend="icon")
public class QualificationClass  extends Qualification{

	private static final long serialVersionUID = -1096034961431870495L;
	
	
	/**
	 * 资质类型
	 */
	@NotNull
	@Length(max=100, min=2)  
	@Column(name = "CLASS_TYPE")
	private String classType;

	/**下级个数*/
	@Formula("(Select count(bq.quality_id) From BASE_QUALIFICATION bq Where bq.Type = '00' and bq.parent_id = quality_id)")
	private int subClazzCount;

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}
	
	public int getSubClazzCount() {
		return subClazzCount;
	}

	public void setSubClazzCount(int subClazzCount) {
		this.subClazzCount = subClazzCount;
	}

	@Override
	public Boolean getIsLeaf() {
		int count = 0;
		count = getSubClazzCount();
		if(count>0){
			return false;
		}else{
			return true;
		}
	}
}