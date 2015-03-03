package com.gpcsoft.bizplatform.organization.domain;

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

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.VerifyObject;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>成功案例</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   bizplatform                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-26 上午11:22:33 by guoyr    					                            
  *  <br/>Modified Date:  2010-7-26 上午11:22:33 by guoyr   
  *  @gpcsoft.package packageDir="com.gpcsoft.bizplatform.successcase"
  *  @gpcsoft.page domain="successcase" project="bizplatform"
  *  @gpcsoft.domain
  *  @since 0.5
  *  @version: 0.5 
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PUB_SUCCESS_CASE")
public class SuccessCase implements GpcBaseObject,VerifyObject {

	/** serialVersionUID */
	private static final long serialVersionUID = 7512435374912400079L;

	/**主键*/
	@Id
	@Column(name = "CASE_ID",length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
    
	/**所属机构*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="ORGINFO_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private OrgInfo orgInfo;

	/**项目名称*/
	@Column(name = "PROJECT_NAME", length = 100)
	private String projectName;
	
	/**开始时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_DATE")
	private Date startTime;
	
	/**结束时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE")
	private Date endTime;
	
	/**采购品目*/
	@Column(name = "CATEGORY_IDS", length = 500)
	private String categoryIds;
	
	/**采购品目名称*/
	@Column(name = "CATEGORY_NAMES", length = 1000)
	private String categoryNames;
	
	/**案例描述*/
	@Column(name = "CASE_DESCRIPTION", length = 4000)
	private String description;
	
	/**状态 默认01（00:临时--01:正式--02:作废）**/
	@Column(name = "USE_STATUS")
	private String useStatus;
	
	/**审核状态 （00:草稿--01:待审--02:通过--03:不通过）**/
	@Column(name = "AUDIT_STATUS")
	private String auditStatus;
	
	/** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="CREATOR")
    @BatchSize(size = 15)
    private User creator;
    
    /** 创建时间 */
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    /**审核人*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="VERIFIER_ID") 
	@BatchSize(size = 15)
	private User verifyUser;
	
    /**审核时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VERIFY_TIME")
    private Date verifyTime;
    
    /**意见*/
    @Column(name = "OPINION")
    private String opinion;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/**
	* @gpcsoft.property title="所属机构"
	*/
	public OrgInfo getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}

	/**
	* @gpcsoft.property title="项目名称"
	*/
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	* @gpcsoft.property title="开始时间"
	*/
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	* @gpcsoft.property title="结束时间"
	*/
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	* @gpcsoft.property title="采购内容"
	*/
	public String getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}

	/**
	* @gpcsoft.property title="采购内容"
	*/
	public String getCategoryNames() {
		return categoryNames;
	}

	public void setCategoryNames(String categoryNames) {
		this.categoryNames = categoryNames;
	}

	/**
	* @gpcsoft.property title="案例描述"
	*/
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	* @gpcsoft.property title="状态"
	*/
	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	/**
	* @gpcsoft.property title="审核状态"
	*/
	public String getAuditStatus() {
		return auditStatus;
	}
	
	public String getAuditStatusCN() {
		return OrganizationEnum.getAuditStatusCN(this.getAuditStatus());
	}
	

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	* @gpcsoft.property title="创建人"
	*/
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	/**
	* @gpcsoft.property title="创建时间"
	*/
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getVerifyUser() {
		return verifyUser;
	}

	public void setVerifyUser(User verifyUser) {
		this.verifyUser = verifyUser;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
}