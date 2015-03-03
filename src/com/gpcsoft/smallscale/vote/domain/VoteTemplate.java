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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.smallscale.vote.enumeration.VoteEnum;
import com.gpcsoft.srplatform.auth.domain.User;
/** 
 *  Comments: <strong>投票模板</strong>        		
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
 * @hibernate.class table="VOTE_TEMPLATE"
 * @author Administrator
 * @version 1.0
 */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "VOTE_TEMPLATE")
@OrderProperty(flag="true", property="templateCode")
public class VoteTemplate implements GpcBaseObject,IPropertyCUserTime,IPropertyUUserTime {

	private static final long serialVersionUID = -507753528275364549L;
	
	/** ID */
	@Id
	@Column(name = "TEMPLATE_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 模板编号 投票编号*/
	@Column(name = "TEMPLATE_CODE")
	private String  templateCode;
	
	/** 模板名称 评选主题*/
	@Column(name = "TEMPLATE_NAME")
	private String templateName;
	
	/**  描述*/
	@Column(name = "TEMPLATE_COMMENT")
	private String templateComment;
	
	/**  开始时间*/
	@Temporal(TemporalType.DATE)
	@Column(name = "START_TIME")
	private Date startTime;
	
	/** 结束时间 */
	@Temporal(TemporalType.DATE)
	@Column(name = "END_TIME")
	private Date endTime;
	
	/** 评价专员 */
    @Column(name="APPRAISE_COMMISSIONER") 
	private String  appraiseCommissioner;
	
    /**  手机*/
    @Column(name = "COMMISSIONER_PHONE")
	private String commissionerPhone;
	
    /** 信箱 */
    @Column(name = "COMMISSIONER_EMAIL")
	private String commissionerEmail;
	
    /** 电话  */
    @Column(name = "COMMISSIONER_TEL")
	private String commissionerTel;
    
    /** 传真  */
    @Column(name = "COMMISSIONER_FAX")
	private String commissionerFax;
	
    /** 是否单一投票 (1:是,0：不是(当为0时为指标评选))*/
    @Column(name = "IS_SINGLE_VOTE")
	private Boolean isSingleVote;
    
    /** 是否单一投票别名*/
    @Transient
    private String isSingleVoteCN;
	
	/** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR_ID") 
    @BatchSize(size = 15)
	private User createUser;
	
    /** 创建时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	/**修改人  */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="MODIFIER_ID") 
    @BatchSize(size = 15)
	private User updateUser;
	
    /** 修改时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_TIME")
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
	 * @gpcsoft.property title="templateCode"
	 */
	public String getTemplateCode() {
		return templateCode;
	}

	/** templateCode */
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	/**
	 * @gpcsoft.property title="templateName"
	 */
	public String getTemplateName() {
		return templateName;
	}

	/** templateName */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * @gpcsoft.property title="templateComment"
	 */
	public String getTemplateComment() {
		return templateComment;
	}

	/** templateComment */
	public void setTemplateComment(String templateComment) {
		this.templateComment = templateComment;
	}

	/**
	 * @gpcsoft.property title="startTime"
	 */
	public Date getStartTime() {
		return startTime;
	}

	/** startTime */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @gpcsoft.property title="endTime"
	 */
	public Date getEndTime() {
		return endTime;
	}

	/** endTime */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @gpcsoft.property title="appraiseCommissioner"
	 */
	public String getAppraiseCommissioner() {
		return appraiseCommissioner;
	}

	/** appraiseCommissioner */
	public void setAppraiseCommissioner(String appraiseCommissioner) {
		this.appraiseCommissioner = appraiseCommissioner;
	}

	/**
	 * @gpcsoft.property title="commissionerPhone"
	 */
	public String getCommissionerPhone() {
		return commissionerPhone;
	}

	/** commissionerPhone */
	public void setCommissionerPhone(String commissionerPhone) {
		this.commissionerPhone = commissionerPhone;
	}

	/**
	 * @gpcsoft.property title="commissionerEmail"
	 */
	public String getCommissionerEmail() {
		return commissionerEmail;
	}

	/** commissionerEmail */
	public void setCommissionerEmail(String commissionerEmail) {
		this.commissionerEmail = commissionerEmail;
	}

	/**
	 * @gpcsoft.property title="commissionerTel"
	 */
	public String getCommissionerTel() {
		return commissionerTel;
	}

	/** commissionerTel */
	public void setCommissionerTel(String commissionerTel) {
		this.commissionerTel = commissionerTel;
	}

	/**
	 * @gpcsoft.property title="isSingleVote"
	 */
	public Boolean getIsSingleVote() {
		return isSingleVote;
	}

	/** isSingleVote */
	public void setIsSingleVote(Boolean isSingleVote) {
		this.isSingleVote = isSingleVote;
	}

	/** isSingleVoteCN */
	public void setIsSingleVoteCN(String isSingleVoteCN) {
		this.isSingleVoteCN = isSingleVoteCN;
	}

	/**
	 * @gpcsoft.property title="isSingleVoteCN"
	 */
	public String getIsSingleVoteCN() {
		isSingleVoteCN = this.isSingleVote == true?"1":"0";
		this.isSingleVoteCN = VoteEnum.getIsSingleVoteCN(isSingleVoteCN);
		return isSingleVoteCN;
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

	/** commissionerFax */
	public void setCommissionerFax(String commissionerFax) {
		this.commissionerFax = commissionerFax;
	}

	/**
	 * @gpcsoft.property title="commissionerFax"
	 */
	public String getCommissionerFax() {
		return commissionerFax;
	}
}
