package com.gpcsoft.smallscale.point.domain;

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
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.smallscale.point.enumeration.SmallscaleEnum;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 * @gpcsoft.package packageDir="com.gpcsoft.smallscale.point"
 * @gpcsoft.page domain="point" project="smallscale" 
 * @gpcsoft.domain
 * @gpcsoft.title value="积分"                            
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate=true,dynamicInsert=true)
@Table(name="eps_point_exchange")
@OrderProperty(property="obtainDate", flag="true")
public class Exchange implements GpcBaseObject {
	private static final long serialVersionUID = -6685648278498037336L;

	@Id
	@Column(name = "EXCHANGE_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name = "USER_ID")	 
	@BatchSize(size = 15)
	private User userId; 
	
	/**
	 * 获取方式   0 购买积分 1 交易积分 3 推荐采购人 4 交换积分 5礼券积分
	 */
	@Column(name="EXCHANGE_TYPE",length=1)
	private String exchangeType;
	
	@Transient
	private String exchangeTypeCN;
	
	/**
	 * 获得日期
	 */
	@Column(name="OBTAIN_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date obtainDate;
	
	/**
	 * 积分来源
	 */
	@Column(name="OBTAIN_SOURCE",length=50)
	private String obtainSource;
	
	/**
	 * 积分额度
	 */
	@Column(name="EXCHANGE_NUMBER",length=8)
	private Long exchangeNumber;
	
	/**
	 * 积分状态 0-无效 1-有效
	 */
	@Column(name="CURRENT_STATUS",length=1)
	private String currentStatus;
	
	@Transient
	private String currentStatusCN;
	
	/**
	 * 备注
	 */
	@Column(name="EXCHANGE_MEMO",length=500)
	private String exchangeMemo;
	
	/**
	 * 有限日期
	 */
	@Column(name="VAL_DATE")
	private Date valDate;
	
	/**
	 * 购买金额
	 */
	@Column(name="EXCHANGE_MONEY")
	private BigDecimal exchangeMoney;
	

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public String getExchangeType() {
		return exchangeType;
	}

	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}

	public Date getObtainDate() {
		return obtainDate;
	}

	public void setObtainDate(Date obtainDate) {
		this.obtainDate = obtainDate;
	}

	public String getObtainSource() {
		return obtainSource;
	}

	public void setObtainSource(String obtainSource) {
		this.obtainSource = obtainSource;
	}

	public Long getExchangeNumber() {
		return exchangeNumber;
	}

	public void setExchangeNumber(Long exchangeNumber) {
		this.exchangeNumber = exchangeNumber;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getExchangeMemo() {
		return exchangeMemo;
	}

	public void setExchangeMemo(String exchangeMemo) {
		this.exchangeMemo = exchangeMemo;
	}

	public Date getValDate() {
		return valDate;
	}

	public void setValDate(Date valDate) {
		this.valDate = valDate;
	}

	public BigDecimal getExchangeMoney() {
		return exchangeMoney;
	}

	public void setExchangeMoney(BigDecimal exchangeMoney) {
		this.exchangeMoney = exchangeMoney;
	}

	public String getCurrentStatusCN() {
		this.currentStatusCN = SmallscaleEnum.getAuditStatusCN(this.currentStatus);
		return currentStatusCN;
	}

	public void setCurrentStatusCN(String currentStatusCN) {
		this.currentStatusCN = currentStatusCN;
	}

	public String getExchangeTypeCN() {
		this.exchangeTypeCN = SmallscaleEnum.getExchangeCN(this.exchangeType);
		return exchangeTypeCN;
	}

	public void setExchangeTypeCN(String exchangeTypeCN) {
		this.exchangeTypeCN = exchangeTypeCN;
	}

	public void setCreateTime(Date createTime) {
	}

	public Date getCreateTime() {
		return null;
	}
	
}
