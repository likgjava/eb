package com.gpcsoft.smallscale.business.domain;

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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.smallscale.business.enumeration.BusinessMemberEnum;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>商圈会员</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-11-25 下午02:55:59 by yucy    					                            
  *  <br/>Modified Date:  2010-11-25 下午02:55:59 by yucy                                   
  *  <p>@since 0.5
  *   
  * @gpcsoft.package packageDir="com.gpcsoft.smallscale.business"
  * @gpcsoft.page domain="business"
  * @hibernate.class table="ecp_business_member"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ecp_business_member")
public class BusinessMember implements GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime{

	/** serialVersionUID */
	private static final long serialVersionUID = -2981725632453332490L;

	/** ID */
	@Id
	@Column(name = "BUSINESSMEMBER_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 机构 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="ORG_INFO_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private OrgInfo orgInfo;
	
	/**开始日期*/
	@Temporal(TemporalType.DATE)
	@Column(name = "BEGAIN_DATE")
	private Date begainDate;
	
	/**结束日期*/
	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE")
	private Date endDate;
	
	/**时长*/
	@Column(name = "TIME_TYPE")
	private String timeType;
	
	@Transient
	private String timeTypeCN;
	
	/** 审核状态[00:草稿;01:待审;02:通过,03:不通过] */
	@Column(name = "AUDIT_STATUS", length = 2)
	private String auditStatus;
	
	@Transient
	private String auditStatusCN;
	
	/** 使用状态[00:临时;01:有效;02:无效] */
	@Column(name = "USE_STATUS", length = 2)
	private String useStatus;
	
	@Transient
	private String useStatusCN;
    
	/** 是否启用 */
	@Column(name = "ISOFF")
	private String isOff;/*禁用启用，2.禁用 1.启用。默认启用*/
	
	/** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATE_USER") 
    @BatchSize(size = 15)
    private User createUser;

    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    /** 修改人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="UPDATE_USER")
    @BatchSize(size = 15)
    private User updateUser;
    
    /**修改时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public OrgInfo getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}

	public Date getBegainDate() {
		return begainDate;
	}

	public void setBegainDate(Date begainDate) {
		this.begainDate = begainDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getTimeTypeCN() {
		this.timeTypeCN = BusinessMemberEnum.getTimeTypeCN(this.timeType);
		return timeTypeCN;
	}

	public void setTimeTypeCN(String timeTypeCN) {
		this.timeTypeCN = timeTypeCN;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditStatusCN() {
		this.auditStatusCN = OrganizationEnum.getAuditStatusCN(this.auditStatus);
		return this.auditStatusCN;
	}

	public void setAuditStatusCN(String auditStatusCN) {
		this.auditStatusCN = auditStatusCN;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getUseStatusCN() {
		this.useStatusCN = OrganizationEnum.getUseStatusCN(this.useStatus);
		return useStatusCN;
	}


	public void setUseStatusCN(String useStatusCN) {
		this.useStatusCN = useStatusCN;
	}

	public String getIsOff() {
		return isOff;
	}

	public void setIsOff(String isOff) {
		this.isOff = isOff;
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

	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
