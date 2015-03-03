package com.gpcsoft.pubservice.application.member.domain;

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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>会员</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2011-3-22 下午08:31:45 by likg    					                            
  *  <br/>Modified Date:  2011-3-22 下午08:31:45 by likg                                   
  *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.service"
  *  @gpcsoft.page domain="Member" project="pubservice/application"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="会员"                              
  *  @since 0.5
  *  @version: 0.5 
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "MEMBER")
@OrderProperty(property="createTime", flag="false")
public class Member implements GpcBaseObject ,IPropertyCUserTime{
    
	/** serialVersionUID */
	private static final long serialVersionUID = -5386333956836871590L;

	/** 主键 */
    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /** 机构 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="ORG_INFO_ID")
	@BatchSize(size = 15)
	private OrgInfo orgInfo;
    
    /** 加入会员时长 */
	@Column(name = "MEMBER_DURATION", length = 10)
	private BigDecimal memberDuration;
	
	/** 已缴纳金额 */
	@Column(name = "PAY_AMOUNT")
	private BigDecimal payAmount;
    
    /** 会员级别 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="MEMBER_CLASS_ID")
	@BatchSize(size = 15)
	private MemberClass memberClass;
	
	/** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR") 
    @BatchSize(size = 15)
    private User createUser;
	
	/** 创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATETIME")
    private Date createTime;
    
    /** 修改人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="MODIFIER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User updateUser;
	
	/** 修改时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_TIME", length = 7)
	private Date updateTime;

	public Date getCreateTime() {
		return createTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public MemberClass getMemberClass() {
		return memberClass;
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

	public BigDecimal getMemberDuration() {
		return memberDuration;
	}

	public String getObjId() {
		return objId;
	}

	public OrgInfo getOrgInfo() {
		return orgInfo;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public void setMemberClass(MemberClass memberClass) {
		this.memberClass = memberClass;
	}

	public void setMemberDuration(BigDecimal memberDuration) {
		this.memberDuration = memberDuration;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

}