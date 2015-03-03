package com.gpcsoft.pubservice.application.member.domain;

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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.pubservice.application.service.enumeration.ServiceEnum;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>会员级别</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2011-3-22 下午08:31:45 by likg    					                            
  *  <br/>Modified Date:  2011-3-22 下午08:31:45 by likg                                   
  *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.service"
  *  @gpcsoft.page domain="MemberClass" project="pubservice/application"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="会员级别"                              
  *  @since 0.5
  *  @version: 0.5 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "MEMBER_CLASS")
public class MemberClass implements GpcBaseObject ,IPropertyCUserTime{
    
	/** serialVersionUID */
	private static final long serialVersionUID = -1768801036307888090L;

	/** 主键 */
    @Id
    @Column(name = "MEMBER_CLASS_ID")
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /** 会员级别，枚举【0:普通会员，1:一级， 2:二级， 3:三级】 */
	@Column(name = "MEMBER_CLASS_NUM", length = 1)
	private BigDecimal memberClassNum;
	
	@Transient
	private String memberClassNumCN;
    
    /** 级别名称 */
	@Column(name = "MEMBER_CLASS_NAME", length = 100)
	private String memberClassName;
	
	/** 级别图片 */
	@Column(name = "MEMBER_CLASS_PIC", length = 50)
	private String memberClassPic;
	
	/** 级别描述 */
	@Column(name = "MEMBER_CLASS_DESC", length = 2000)
	private String memberClassDesc;
	
	/** 入会最小时长（月） */
	@Column(name = "MIN_AGE", length = 10)
	private BigDecimal minAge;
	
	/** 入会最大时长（月） */
	@Column(name = "MAX_AGE", length = 10)
	private BigDecimal maxAge;
	
	/** 缴费最小金额 */
	@Column(name = "MIN_FEE", length = 10)
	private BigDecimal minFee;
	
	/** 缴费最大金额 */
	@Column(name = "MAX_FEE", length = 10)
	private BigDecimal maxFee;
	
	/** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR") 
    @BatchSize(size = 15)
    private User createUser;
	
	/** 创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATETIME")
    private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public BigDecimal getMaxAge() {
		return maxAge;
	}

	public BigDecimal getMaxFee() {
		return maxFee;
	}

	public String getMemberClassDesc() {
		return memberClassDesc;
	}

	public String getMemberClassName() {
		return memberClassName;
	}

	public BigDecimal getMemberClassNum() {
		return memberClassNum;
	}

	public String getMemberClassNumCN() {
		this.memberClassNumCN = ServiceEnum.getMemberClassNumCN(this.memberClassNum.toString());
		return memberClassNumCN;
	}

	public String getMemberClassPic() {
		return memberClassPic;
	}

	public BigDecimal getMinAge() {
		return minAge;
	}

	public BigDecimal getMinFee() {
		return minFee;
	}

	public String getObjId() {
		return objId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public void setMaxAge(BigDecimal maxAge) {
		this.maxAge = maxAge;
	}

	public void setMaxFee(BigDecimal maxFee) {
		this.maxFee = maxFee;
	}

	public void setMemberClassDesc(String memberClassDesc) {
		this.memberClassDesc = memberClassDesc;
	}

	public void setMemberClassName(String memberClassName) {
		this.memberClassName = memberClassName;
	}

	public void setMemberClassNum(BigDecimal memberClassNum) {
		this.memberClassNum = memberClassNum;
	}

	public void setMemberClassNumCN(String memberClassNumCN) {
		this.memberClassNumCN = memberClassNumCN;
	}

	public void setMemberClassPic(String memberClassPic) {
		this.memberClassPic = memberClassPic;
	}

	public void setMinAge(BigDecimal minAge) {
		this.minAge = minAge;
	}

	public void setMinFee(BigDecimal minFee) {
		this.minFee = minFee;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
    
}