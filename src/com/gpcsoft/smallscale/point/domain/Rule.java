package com.gpcsoft.smallscale.point.domain;

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

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.smallscale.point.enumeration.SmallscaleEnum;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 * @gpcsoft.package packageDir="com.gpcsoft.smallscale.point"
 * @gpcsoft.page domain="point" project="smallscale" 
 * @gpcsoft.domain
 * @gpcsoft.title value="积分规则"                            
 */



@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "eps_point_rules")
public class Rule implements GpcBaseObject, IPropertyCUserTime,
		IPropertyUUserTime {

	/** serialVersionUID */
	private static final long serialVersionUID = -4857464434528655618L;
	
	/** 主键ID */
	@Id
    @Column(name = "RULE_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 来源 （枚举定义） */	
	@Column(name = "SOURCE_CODE",length=100)
	private String sourceCode;
    
    public final static String POINT_WAY_NUMBER="0";
    public final static String POINT_WAY_PERCENT="1";
    
    /**  积分方式 0 额度 1 百分比 */
    @Column(name = "POINT_WAY" ,length=1)
    private String pointWay;
    
    /**  积分额度 */
    @Column(name = "POINT_NUMBER" ,length=8)
    private Integer pointNumber;
    
    /**  百分比 */
    @Column(name = "POINT_PERCENT" ,length=8)
    private Integer pointPercent;
    
    /**  当前状态 1 有效 0无效 */
    @Column(name = "CURRENT_STATUS" ,length=1)
    private String currentStatus = "1";
    
    @Transient
    private String currentStatusCN ;
    

    
    /**  积分累加方式,1,加分，-1减分 */
    @Column(name = "POINT_SIGN" ,length=1)
    private Integer pointSign;
    
    /**  额度最小值 */
    @Column(name = "AMOUNT_MIN" ,length=12)
    private Integer amountMin;
    
    /**  额度最大值 */
    @Column(name = "AMOUNT_MAX" ,length=12)
    private Integer amountMax;
    
   
    /** 创建人 */	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="CREATOR")  
	@BatchSize(size = 15)//批量抓取
    private User createUser;
    
    /**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTime;
    
	 /** 修改人 */	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="MODIFIER")  
	@BatchSize(size = 15)//批量抓取
    private User updateUser;
    
    /**
	 * 修改时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MODIFY_TIME")
	private Date updateTime;
	
	/**
	 * 发布日期
	 */
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name="PUB_DATE")
//	private Date pubDate;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}	

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getPointWay() {
		return pointWay;
	}

	public void setPointWay(String pointWay) {
		this.pointWay = pointWay;
	}

	public Integer getPointNumber() {
		return pointNumber;
	}

	public void setPointNumber(Integer pointNumber) {
		this.pointNumber = pointNumber;
	}

	public Integer getPointPercent() {
		return pointPercent;
	}

	public void setPointPercent(Integer pointPercent) {
		this.pointPercent = pointPercent;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Integer getPointSign() {
		return pointSign;
	}

	public void setPointSign(Integer pointSign) {
		this.pointSign = pointSign;
	}

	public Integer getAmountMin() {
		return amountMin;
	}

	public void setAmountMin(Integer amountMin) {
		this.amountMin = amountMin;
	}

	public Integer getAmountMax() {
		return amountMax;
	}

	public void setAmountMax(Integer amountMax) {
		this.amountMax = amountMax;
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
	
	
	public String getCurrentStatusCN() {
		this.currentStatusCN = SmallscaleEnum.getAuditStatusCN(this.currentStatus);
		return currentStatusCN;
	}

	public void setCurrentStatusCN(String currentStatusCN) {
		this.currentStatusCN = currentStatusCN;
	}
	
	
}
