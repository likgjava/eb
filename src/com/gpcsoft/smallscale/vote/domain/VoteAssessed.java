package com.gpcsoft.smallscale.vote.domain;

import java.math.BigDecimal;
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

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>投票记录表</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2011-1-14 下午02:26:41 by yangxb    					                            
  *  <br/>Modified Date:  2011-1-14 下午02:26:41 by yangxb                                   
  *<p>@since 0.5
  *   @version: 0.5 
  *   
  * @gpcsoft.package packageDir="com.gpcsoft.smallscale.vote"
  * @gpcsoft.page domain="vote" project="smallscale"
  * @hibernate.class table="VOTE_ASSESSED"
  * @author Administrator
  * @version 1.0
  */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "VOTE_ASSESSED")
@OrderProperty(flag = "true", property = "createTime")
public class VoteAssessed implements GpcBaseObject ,IPropertyCUserTime{

	private static final long serialVersionUID = -5073556641231264549L;
	
	/** ID */
	@Id
	@Column(name = "ASSESSED_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 投票模板 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="TEMPLATE_ID") 
    @BatchSize(size = 15)
	private VoteTemplate voteTemplate;
    
	/** 参选对象(被投对象) */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="ASSESSED_THING_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private VoteAssessedThing voteAssessedThing;
    
	/** 比例系数 */
	@Column(name = "POINTER_FACTOR", precision = 3, scale = 6)
	private BigDecimal pointerFactor;
	
	/** 投票人名称 */
	@Column(name = "USER_NAME")
	private String userName;
	
	/** 是否匿名(0:不匿名   1：匿名) */
	@Column(name = "IS_ANONYMITY")
	private Boolean isAnonymity;
	
	/** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="USER_ID") 
    @BatchSize(size = 15)
	private User createUser;
	
    /** 创建时间 投票时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VOTE_TIME")
	private Date createTime;
	
	/** 备注*/
	@Column(name = "MEMO",length=500)
	private String memo;
	
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
	 * @gpcsoft.property title="pointerFactor"
	 */
	public BigDecimal getPointerFactor() {
		return pointerFactor;
	}

	/** pointerFactor */
	public void setPointerFactor(BigDecimal pointerFactor) {
		this.pointerFactor = pointerFactor;
	}

	/**
	 * @gpcsoft.property title="userName"
	 */
	public String getUserName() {
		return userName;
	}

	/** userName */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @gpcsoft.property title="isAnonymity"
	 */
	public Boolean getIsAnonymity() {
		return isAnonymity;
	}

	/** isAnonymity */
	public void setIsAnonymity(Boolean isAnonymity) {
		this.isAnonymity = isAnonymity;
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
	 * @gpcsoft.property title="memo"
	 */
	public String getMemo() {
		return memo;
	}

	/** memo */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/** voteAssessedThing */
	public void setVoteAssessedThing(VoteAssessedThing voteAssessedThing) {
		this.voteAssessedThing = voteAssessedThing;
	}

	/**
	 * @gpcsoft.property title="voteAssessedThing"
	 */
	public VoteAssessedThing getVoteAssessedThing() {
		return voteAssessedThing;
	}
 }
