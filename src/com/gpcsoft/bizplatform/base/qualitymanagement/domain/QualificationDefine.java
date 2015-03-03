package com.gpcsoft.bizplatform.base.qualitymanagement.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.gpcsoft.core.tree.TreeProperty;

/** 
 *  Comments: <strong>QualificationDefine</strong>            		
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
 * @gpcsoft.title value="资质定义"
 */ 

@Entity
@DiscriminatorValue("01")
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@TreeProperty(topIcon="book_titel.gif", nodeIcon="tombs_mag.gif", title="资质定义", text="name", tip="qualification",nodeIconExtend="icon")
public class QualificationDefine  extends Qualification{

	private static final long serialVersionUID = -1096034961431870495L;
	
	

	
}