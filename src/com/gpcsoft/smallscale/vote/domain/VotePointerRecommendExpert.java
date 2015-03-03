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
import com.gpcsoft.srplatform.auth.domain.User;
/** 
 *  Comments: <strong>专家推荐投票模板指标</strong>            		
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
 * @hibernate.class table="VOTE_POINTER_RECOMMEND_EXPERT"
 * @author Administrator
 * @version 1.0
 */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "VOTE_POINTER_RECOMMEND_EXPERT")
public class VotePointerRecommendExpert implements GpcBaseObject,IPropertyCUserTime {

	private static final long serialVersionUID = -507355664827512354L;
	
	
	/** ID */
	@Id
	@Column(name = "POINTER_RECOMMEND_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 投票模板 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="TEMPLATE_ID") 
    @BatchSize(size = 15)
	private VoteTemplate voteTemplate;
	
	/** 指标名称 */
	@Column(name = "POINTER_NAME")
	private String pointerName;

	/** 比例系数 */
	@Column(name = "POINTER_FACTOR", precision = 3, scale = 6)
	private BigDecimal pointerFactor;	
	
	/** 专家 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="EXPERT_ID") 
    @BatchSize(size = 15)
	private User expert;
	
	/** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR_ID") 
    @BatchSize(size = 15)
	private User createUser;
	
    /** 创建时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;

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
	 * @gpcsoft.property title="pointerName"
	 */
	public String getPointerName() {
		return pointerName;
	}

	/** pointerName */
	public void setPointerName(String pointerName) {
		this.pointerName = pointerName;
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
	 * @gpcsoft.property title="expert"
	 */
	public User getExpert() {
		return expert;
	}

	/** expert */
	public void setExpert(User expert) {
		this.expert = expert;
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
}
