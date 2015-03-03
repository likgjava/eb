package com.gpcsoft.agreement.bargin.bidding.domain;
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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.agreement.bargin.project.domain.BargainTurn;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.BaseObject;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>BargainChat</strong>            		
  *	 <br/>	聊天记录Chat		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-4-28 下午04:00:24 by wangsw    					                            
  *  <br/>Modified Date:  2010-4-28 下午04:00:24 by wangsw                                   
  *<p>@since 0.5
  *   @version: 0.5 
  *   
  *  @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.ebargain"
  *  @gpcsoft.page domain="ebargain" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="议价聊天记录"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREEMENT_BARGAIN_CHAT")
public class BiddingChat extends BaseObject implements GpcBaseObject, IPropertyCUserTime{

	
	/** serialVersionUID */
	private static final long serialVersionUID = 573633953336126018L;

	/** 对象ID */
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "BARGAIN_CHAT_ID", unique = true, nullable = false, length = 50)
	private String objId;
	
	/**
	 * 报价记录id
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BARGAIN_RECORD_ID")
	private BiddingRecord biddingRecord;
	
    /**项目*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="PROJECT_ID") 
    @BatchSize(size = 15)
    private Project project;
    
    /**报价轮次*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="BARGAIN_TURN_ID") 
    @BatchSize(size = 15)
    private BargainTurn bargainTurn;
	
	/** 发言内容 */
	@Column(name = "CONTENT")
	private String content;

	/** 发言机构 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SAY_ORGINFO_ID")
	private OrgInfo sayOrgInfo;
	
	/** 发言人 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CRE_USER_ID")
	private User createUser;
	
	/** 发言时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRE_TIME")
	private Date createTime;
	
	/** 接收机构 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECEIVE_ORGINFO_ID")
	private OrgInfo receiveOrgInfo;
	
	/** 接收人 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECEIVE_USER_ID")
	private User receiveUser;
	
	/** 读取状态 */
	@Column(name = "READ_STATUS")
	private Boolean readStatus;
	
	/** 是否私聊 1私聊   0公聊 */
	@Column(name = "IS_PRIVATE", length = 1, columnDefinition = "default '0'")
	private Character isPrivate;
	
	/** 附件 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ATTACHMENT_ID")
	private Attachment attachment;

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

	public Boolean getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Boolean readStatus) {
		this.readStatus = readStatus;
	}

	/**
	 * @gpcsoft.property title="content"
	 */
	public String getContent() {
		return content;
	}

	/** content */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @gpcsoft.property title="sayOrgInfo"
	 */
	public OrgInfo getSayOrgInfo() {
		return sayOrgInfo;
	}

	/** sayOrgInfo */
	public void setSayOrgInfo(OrgInfo sayOrgInfo) {
		this.sayOrgInfo = sayOrgInfo;
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
	 * @gpcsoft.property title="receiveOrgInfo"
	 */
	public OrgInfo getReceiveOrgInfo() {
		return receiveOrgInfo;
	}

	/** receiveOrgInfo */
	public void setReceiveOrgInfo(OrgInfo receiveOrgInfo) {
		this.receiveOrgInfo = receiveOrgInfo;
	}

	/**
	 * @gpcsoft.property title="receiveUser"
	 */
	public User getReceiveUser() {
		return receiveUser;
	}

	/** receiveUser */
	public void setReceiveUser(User receiveUser) {
		this.receiveUser = receiveUser;
	}

	/**
	 * @gpcsoft.property title="attachment"
	 */
	public Attachment getAttachment() {
		return attachment;
	}

	/** attachment */
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	/**
	 * @gpcsoft.property title="isPrivate"
	 */
	public Character getIsPrivate() {
		return isPrivate;
	}

	/** isPrivate */
	public void setIsPrivate(Character isPrivate) {
		this.isPrivate = isPrivate;
	}

	public BiddingRecord getBiddingRecord() {
		return biddingRecord;
	}

	public void setBiddingRecord(BiddingRecord biddingRecord) {
		this.biddingRecord = biddingRecord;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public BargainTurn getBargainTurn() {
		return bargainTurn;
	}

	public void setBargainTurn(BargainTurn bargainTurn) {
		this.bargainTurn = bargainTurn;
	}
}