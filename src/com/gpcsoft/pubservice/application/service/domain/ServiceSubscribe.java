package com.gpcsoft.pubservice.application.service.domain;

import java.math.BigDecimal;
import java.util.Calendar;
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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.agreement.order.enumeration.OrderEnum;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>服务订阅</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   pubservice                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-8-9 下午01:55:58 by sunl   					                            
  *  <br/>Modified Date:  2010-8-9 下午01:55:58 by sunl   
  *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.service"
  *  @gpcsoft.page domain="Favorites" project="pubservice/application"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="服务订阅"                              
  *  @since 0.5
  *  @version: 0.5 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "SERVICE_SUBSCRIBE")
public class ServiceSubscribe implements GpcBaseObject ,IPropertyCUserTime{
    
	/** serialVersionUID */
	private static final long serialVersionUID = 2711365021142479938L;

	/**主键*/
    @Id
    @Column(name = "SERVICE_SUBSCRIBE_ID")
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**订阅机构*/
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="ORG_INFO_ID")
    @BatchSize(size = 15)
    private OrgInfo orgInfo;
    
    /**订阅服务*/
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="SERVICE_ID")
    @BatchSize(size = 15)
    private ServiceBase serviceBase;
    
    /**缴费金额*/
    @Column(name = "PAY_AMOUNT", length = 100)
    private BigDecimal payAmount;
    
    /**开始时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIME")
    private Date startTime;
    
    /**结束时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_TIME")
    private Date endTime;
    
    @Transient
    private String duration;

    /** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR") 
    @BatchSize(size = 15)
    private User createUser;
    
    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATETIME")
    private Date createTime;
    
    /**审核人*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="AUDIT_USER") 
	@BatchSize(size = 15)
	private User auditor;
	
    /**审核时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AUDIT_TIME")
    private Date auditTime;

    /** 审核状态[00:草稿;01:待审;02:通过,03:不通过] */
	@Column(name = "AUDIT_STATUS", length = 2)
	private String auditStatus;
	
	/** 支付状态 */
	@Column(name = "PAY_STATUS", length = 2)
	private String payStatus;
	
	/**支付状态中文*/
	@Transient
	private String payStatusCN;
	
	/** 备注 */
	@Column(name = "REMARK", length = 1000)
	private String remark;
	
	@Transient
	private String auditStatusCN;
	
	/**@gpcsoft.property title="支付状态"*/	
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	public String getPayStatusCN() {
		this.payStatusCN = OrderEnum.getPayStatusCN(this.payStatus);
		return this.payStatusCN;
	}
	public void setPayStatusCN(String payStatusCN) {
		this.payStatusCN = payStatusCN;
	}
	
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

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public ServiceBase getServiceBase() {
		return serviceBase;
	}

	public void setServiceBase(ServiceBase serviceBase) {
		this.serviceBase = serviceBase;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public User getAuditor() {
		return auditor;
	}

	public void setAuditor(User auditor) {
		this.auditor = auditor;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
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

	public String getDuration() {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		if(null != this.startTime && null != this.endTime) {
			start.setTime(startTime);
			end.setTime(endTime);
			//如果年相等，则返回相差的月数，否则返回相差的年数
			if(end.get(Calendar.YEAR) - start.get(Calendar.YEAR) == 0) {
				this.duration = String.valueOf(end.get(Calendar.MONTH)- start.get(Calendar.MONTH));
			} else {
				this.duration = String.valueOf(end.get(Calendar.YEAR) - start.get(Calendar.YEAR));
			}
		}
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public String getAuditStatusCN() {
		this.auditStatusCN = OrganizationEnum.getAuditStatusCN(this.auditStatus);
		return this.auditStatusCN;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}