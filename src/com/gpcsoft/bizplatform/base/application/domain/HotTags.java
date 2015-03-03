package com.gpcsoft.bizplatform.base.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>热门标签</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   pubservice                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-8-9 下午01:55:58 by yucy   					                            
  *  <br/>Modified Date:  2010-8-9 下午01:55:58 by yucy   
  *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.favorites.domain.HotTags"
  *  @gpcsoft.page domain="HotTags" project="pubservice/application"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="热门标签"                              
  *  @since 0.5
  *  @version: 0.5 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PUB_TAGS")
public class HotTags implements GpcBaseObject ,IPropertyCUserTime{
    
	/** serialVersionUID */
	private static final long serialVersionUID = -5327399572473736416L;

	/**记录号*/
    @Id
    @Column(name = "TAGS_ID")
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**标签对象类型[01:商品, 02:供应商, 03:代理机构, 04:行情区域, 05:商品分类, 06:采购品目] */
    @Column(name = "TAGS_OBJECT_TYPE")
    private String tagsType;
    
    /**标签对象Id */
    @Column(name = "TAGS_OBJECT_ID")
    private String tagsId;
    
    /**收藏对象名称 */
    @Column(name = "TAGS_OBJECT_NAME")
    private String tagsName;
    
    /**收藏对象描述 */
    @Column(name = "TAGS_OBJECT_DSCR")
    private String tagsDscr;

    /** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR_ID") 
    @BatchSize(size = 15)
    private User createUser;
    
    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	public String getTagsId() {
		return tagsId;
	}

	public void setTagsId(String tagsId) {
		this.tagsId = tagsId;
	}

	public String getTagsType() {
		return tagsType;
	}

	public void setTagsType(String tagsType) {
		this.tagsType = tagsType;
	}

	public String getTagsName() {
		return tagsName;
	}

	public void setTagsName(String tagsName) {
		this.tagsName = tagsName;
	}
	
	public String getTagsDscr() {
		return tagsDscr;
	}

	public void setTagsDscr(String tagsDscr) {
		this.tagsDscr = tagsDscr;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}