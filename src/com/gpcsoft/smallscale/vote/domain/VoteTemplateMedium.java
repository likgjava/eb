package com.gpcsoft.smallscale.vote.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * 
  *  Comments: <strong>评选主题合作媒体</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2011-5-11 下午04:10:47 by zhaojf    					                            
  *  <br/>Modified Date:  2011-5-11 下午04:10:47 by zhaojf                                   
  *<p>@since 0.5
  *   @version: 0.5
  * @gpcsoft.package packageDir="com.gpcsoft.smallscale.vote"
  * @gpcsoft.page domain="vote" project="smallscale"
  * @hibernate.class table="VOTE_TEMPLATE_MEDIUM"
  * @author Administrator
  * @version 1.0
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "VOTE_TEMPLATE_MEDIUM")
@OrderProperty(flag="true", property="mediumSort")
public class VoteTemplateMedium implements GpcBaseObject ,IPropertyCUserTime ,IPropertyUUserTime{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** ID */
	@Id
	@Column(name = "VOTE_TEMPLATE_MEDIUM_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 投票模板 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="VOTE_TEMPLATE_ID") 
    @BatchSize(size = 15)
	private VoteTemplate voteTemplate;
    
	/** 媒体 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="ORG_ID") 
    @BatchSize(size = 15)
	private OrgInfo orgInfo;
    
    /** 媒体排序 */
	@Column(name = "MEDIUM_SORT")
	private Integer mediumSort;
	
	/**创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATER_ID")  
    @BatchSize(size = 15)
    private User createUser;
	
	/**创建时间	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	/**修改人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="UPDATER_ID")//关联的外键  
	@BatchSize(size = 15)//批量抓取
	private User updateUser;
	 
	/**修改时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME")
	private Date updateTime;

	/**
	 * @gpcsoft.property title="objId"
	 */
	public String getObjId() {
		return objId;
	}

	/** objId */
	public void setObjId(String objId) {
		this.objId = objId;
	}

	/**
	 * @gpcsoft.property title="voteTemplate"
	 */
	public VoteTemplate getVoteTemplate() {
		return voteTemplate;
	}

	/** voteTemplate */
	public void setVoteTemplate(VoteTemplate voteTemplate) {
		this.voteTemplate = voteTemplate;
	}

	/**
	 * @gpcsoft.property title="orgInfo"
	 */
	public OrgInfo getOrgInfo() {
		return orgInfo;
	}

	/** orgInfo */
	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}

	/**
	 * @gpcsoft.property title="mediumSort"
	 */
	public Integer getMediumSort() {
		return mediumSort;
	}

	/** mediumSort */
	public void setMediumSort(Integer mediumSort) {
		this.mediumSort = mediumSort;
	}

	/**
	 * @gpcsoft.property title="createUser"
	 */
	public User getCreateUser() {
		return createUser;
	}

	/** createUser */
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	/**
	 * @gpcsoft.property title="createTime"
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/** createTime */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @gpcsoft.property title="updateUser"
	 */
	public User getUpdateUser() {
		return updateUser;
	}

	/** updateUser */
	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * @gpcsoft.property title="updateTime"
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/** updateTime */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

 }
