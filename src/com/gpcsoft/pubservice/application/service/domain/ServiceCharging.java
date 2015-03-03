package com.gpcsoft.pubservice.application.service.domain;

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
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.pubservice.application.member.domain.MemberClass;
import com.gpcsoft.pubservice.application.service.enumeration.ServiceEnum;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>服务计费</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2011-3-24 上午09:05:37 by likg    					                            
  *  <br/>Modified Date:  2011-3-24 上午09:05:37 by likg                                   
  *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.service"
  *  @gpcsoft.page domain="ServiceCharging" project="pubservice/application"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="服务计费"                              
  *  @since 0.5
  *  @version: 0.5 
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "SERVICE_CHARGING")
@OrderProperty(property="createTime", flag="false")
public class ServiceCharging implements GpcBaseObject ,IPropertyCUserTime {
    
	/** serialVersionUID */
	private static final long serialVersionUID = -7769834834823387475L;

	/**主键*/
    @Id
    @Column(name = "SERVICE_CHARGING_ID")
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /** 会员级别 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="MEMBER_CLASS_ID")
	@BatchSize(size = 15)
	private MemberClass memberClass;
	
	/** 服务 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="SERVICE_ID")
	@BatchSize(size = 15)
	private ServiceBase serviceBase;
	
	/** 时长 */
	@Column(name = "DURATION")
	private BigDecimal duration;
	
	/** 金额 */
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	
	/** 折扣率 */
	@Column(name = "DISCOUNT")
	private BigDecimal discount;
	
	/** 使用状态[00:临时;01:有效;02:无效] */
	@Column(name = "USE_STATUS", length = 2)
	private String useStatus;
	
	@Transient
	private String useStatusCN;
	
	/** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR") 
    @BatchSize(size = 15)
    private User createUser;
	
	/** 创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATETIME")
    private Date createTime;

	public BigDecimal getAmount() {
		return amount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public BigDecimal getDuration() {
		return duration;
	}

	public MemberClass getMemberClass() {
		return memberClass;
	}

	public String getObjId() {
		return objId;
	}

	public ServiceBase getServiceBase() {
		return serviceBase;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public String getUseStatusCN() {
		this.useStatusCN = ServiceEnum.getUseStatusCN(this.useStatus);
		return useStatusCN;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public void setMemberClass(MemberClass memberClass) {
		this.memberClass = memberClass;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setServiceBase(ServiceBase serviceBase) {
		this.serviceBase = serviceBase;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public void setUseStatusCN(String useStatusCN) {
		this.useStatusCN = useStatusCN;
	}
	
}