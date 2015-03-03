package com.gpcsoft.pubservice.application.service.domain;

import java.math.BigDecimal;
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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.pubservice.application.member.domain.MemberClass;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>服务组合</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   pubservice                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-8-9 下午01:55:58 by sunl   					                            
  *  <br/>Modified Date:  2010-8-9 下午01:55:58 by sunl   
  *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.service"
  *  @gpcsoft.page domain="Favorites" project="pubservice/application"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="服务组合"                              
  *  @since 0.5
  *  @version: 0.5 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "SERVICE_GROUP")
public class ServiceGroup implements GpcBaseObject ,IPropertyCUserTime{
    
	/** serialVersionUID */
	private static final long serialVersionUID = 2711365021142479938L;

	/**主键*/
    @Id
    @Column(name = "SERVICE_GROUP_ID")
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
	
	/**主服务*/
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="MAIN_SERVICE_ID")
    @BatchSize(size = 15)
    private ServiceBase mainService;
    
    /**搭配服务*/
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="APPEND_SERVICE_ID")
    @BatchSize(size = 15)
    private ServiceBase appendService;
	
    /** 会员级别 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="MEMBER_CLASS_ID")
	@BatchSize(size = 15)
	private MemberClass memberClass;
	
    /**折扣*/
    @Column(name = "DISCOUNT", length = 100)
    private BigDecimal discount;
    
    /**金额*/
    @Column(name = "AMOUNT", length = 100)
    private BigDecimal amount;
    
    /** 时长 */
	@Column(name = "DURATION", length = 10)
	private BigDecimal duration;
    
    /** 使用状态[00:临时;01:有效;02:无效] */
	@Column(name = "USE_STATUS", length = 2)
	private String useStatus;
    
	/** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR") 
    @BatchSize(size = 15)
    private User createUser;
	
	/**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATETIME")
    private Date createTime;

    public Date getCreateTime() {
		return createTime;
	}
    
    public User getCreateUser() {
		return createUser;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public ServiceBase getMainService() {
		return mainService;
	}

	public void setMainService(ServiceBase mainService) {
		this.mainService = mainService;
	}

	public ServiceBase getAppendService() {
		return appendService;
	}

	public void setAppendService(ServiceBase appendService) {
		this.appendService = appendService;
	}

	public MemberClass getMemberClass() {
		return memberClass;
	}

	public void setMemberClass(MemberClass memberClass) {
		this.memberClass = memberClass;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getDuration() {
		return duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}