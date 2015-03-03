package com.gpcsoft.pubservice.application.advertisement.domain;

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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.core.model.VerifyObject;
import com.gpcsoft.pubservice.application.advertisement.enumeration.AdvertisingMessage;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  *  Comments: <strong>广告订阅</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2011-3-21 下午02:13:05 by zhaojf    					                            
  *  <br/>Modified Date:  2011-3-21 下午02:13:05 by zhaojf                                   
  *<p>@since 0.5
  *   @version: 0.5
  *   
  *   @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.advertisement"
  *   @gpcsoft.page domain="advertisement" project="pubservice/application"
  *   @gpcsoft.domain
  *   @gpcsoft.title value="广告订阅"
  *   
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_PUB_ADVERTISING_SUBSCRIBE")
@OrderProperty(property="createTime",flag="true")
public class AdvertisingSubscribe implements GpcBaseObject,IPropertyCUserTime,IPropertyUUserTime,VerifyObject{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**主键ID */
	@Id
	@Column(name="ADVER_SUBSCRIBE_ID", length=50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name="paymentableGenerator",strategy="uuid.hex")
	private String objId;
	
    /**广告位Id*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="ADVER_POSITION_ID") 
    @BatchSize(size = 15)
    private AdvertisingPosition advertisingPosition;
	
    /**投放机构Id*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="ORGINFO_ID") 
    @BatchSize(size = 15)
    private OrgInfo orgInfo;
	
	/**投放机构名称 */
	@Column(name="ORGINFO_ORGNAME")
	private String orgName;

	/**广告链接	 */
	@Column(name="ADVER_LINK")
	private String adverLink;
	
	/**广告文件	 */
	@Column(name="ADVER_FILE")
	private String adverFile;
	
    /**开始时间*/
    @Temporal(TemporalType.DATE)
    @Column(name = "START_TIME")
    private Date startTime;
    
    /**结束时间*/
    @Temporal(TemporalType.DATE)
    @Column(name = "END_TIME")
    private Date endTime;
    
	/**总资费  */
	@Column(name="TOTAL_OUTLAY", precision = 16, scale = 4)
    private BigDecimal totalOutlay;
	
    /**审核状态：00.草稿（默认） 01.新建待审核 02.审核通过 03.审核不通过*/
    @Column(name = "AUDIT_STATUS", length = 2)
    private String auditStatus;
    
    /**使用状态（注：00.草稿,01.有效、02.报废）*/
    @Column(name = "USE_STATUS", length = 2)
    private String useStatus;
	
	 /**创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="CREATOR")  
    @BatchSize(size = 15)
    private User createUser;
	
	/**创建时间	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	/**修改人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
	@JoinColumn(name="MODIFIER")//关联的外键  
	@BatchSize(size = 15)//批量抓取
	private User updateUser;
	 
	/**修改时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_TIME")
	private Date updateTime;	
	
	/**审核人*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="AUDITOR") 
	@BatchSize(size = 15)
	private User verifyUser;
	
	/**审核时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AUDIT_TIME")
	private Date verifyTime;
	 
	/**意见*/
	@Column(name = "AUDIT_OPTION")
	private String opinion;
	
    /**排序号*/
    @Column(name = "AD_SORT", length = 9)
	private Long sort;

	/**审核状态别名 */
	@Transient
	private String auditStatusCN;
	
	/**使用状态别名 */
	@Transient
	private String useStatusCN;
	
	/** auditStatusCN */
	public void setAuditStatusCN(String auditStatusCN) {
		this.auditStatusCN = auditStatusCN;
	}

	/**
	 * @gpcsoft.property title="auditStatusCN"
	 */
	public String getAuditStatusCN() {
		this.auditStatusCN = AdvertisingMessage.getAuditStatusCN(this.auditStatus);
		return this.auditStatusCN;
	}

	/** useStatusCN */
	public void setUseStatusCN(String useStatusCN) {
		this.useStatusCN = useStatusCN;
	}

	/**
	 * @gpcsoft.property title="useStatusCN"
	 */
	public String getUseStatusCN() {
		this.useStatusCN = AdvertisingMessage.getUseStatusCN(this.useStatus);
		return this.useStatusCN;
	}

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
	 * @gpcsoft.property title="advertisingPosition"
	 */
	public AdvertisingPosition getAdvertisingPosition() {
		return advertisingPosition;
	}

	/** advertisingPosition */
	public void setAdvertisingPosition(AdvertisingPosition advertisingPosition) {
		this.advertisingPosition = advertisingPosition;
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
	 * @gpcsoft.property title="orgName"
	 */
	public String getOrgName() {
		return orgName;
	}

	/** orgName */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @gpcsoft.property title="adverLink"
	 */
	public String getAdverLink() {
		return adverLink;
	}

	/** adverLink */
	public void setAdverLink(String adverLink) {
		this.adverLink = adverLink;
	}

	/**
	 * @gpcsoft.property title="adverFile"
	 */
	public String getAdverFile() {
		return adverFile;
	}

	/** adverFile */
	public void setAdverFile(String adverFile) {
		this.adverFile = adverFile;
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
	 * @gpcsoft.property title="totalOutlay"
	 */
	public BigDecimal getTotalOutlay() {
		return totalOutlay;
	}

	/** totalOutlay */
	public void setTotalOutlay(BigDecimal totalOutlay) {
		this.totalOutlay = totalOutlay;
	}

	/**
	 * @gpcsoft.property title="auditStatus"
	 */
	public String getAuditStatus() {
		return auditStatus;
	}

	/** auditStatus */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * @gpcsoft.property title="useStatus"
	 */
	public String getUseStatus() {
		return useStatus;
	}

	/** useStatus */
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
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

	/**
	 * @gpcsoft.property title="verifyUser"
	 */
	public User getVerifyUser() {
		return verifyUser;
	}

	/** verifyUser */
	public void setVerifyUser(User verifyUser) {
		this.verifyUser = verifyUser;
	}

	/**
	 * @gpcsoft.property title="verifyTime"
	 */
	public Date getVerifyTime() {
		return verifyTime;
	}

	/** verifyTime */
	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	/**
	 * @gpcsoft.property title="opinion"
	 */
	public String getOpinion() {
		return opinion;
	}

	/** opinion */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	/** sort */
	public void setSort(Long sort) {
		this.sort = sort;
	}

	/**
	 * @gpcsoft.property title="sort"
	 */
	public Long getSort() {
		return sort;
	}
}
