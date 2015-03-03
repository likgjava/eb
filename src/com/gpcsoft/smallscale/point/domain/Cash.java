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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 * @gpcsoft.package packageDir="com.gpcsoft.smallscale.point"
 * @gpcsoft.page domain="point" project="smallscale" 
 * @gpcsoft.domain
 * @gpcsoft.title value="兑现"                            
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate=true,dynamicInsert=true)
@Table(name="eps_point_cash")
@OrderProperty(property="cashDate", flag="true")
public class Cash implements GpcBaseObject{
	private static final long serialVersionUID = -6398043235452110273L;

	@Id
	@Column(name = "CASH_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name = "USER_ID")	 
	@BatchSize(size = 15)
	private User userId; 
	
	/**
	 * 兑换日期
	 */
	@Column(name="CASH_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date cashDate;
	
	/**
	 * 兑换积分额度
	 */
	@Column(name="CASH_NUMBER",length=50)
	private Long cashNumber;
	
	/**
	 * 相关附件
	 */
	@Column(name="CASH_FILE",length=50)
	private String cashFile;
	
	/**
	 * 备注
	 */
	@Column(name="CASH_MEMO",length=50)
	private String cashMemo;
	
	/**
	 * 兑现金额
	 */
	@Column(name="CASH_MONEY")
	private BigDecimal cashMoney;
	
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

	public Date getCashDate() {
		return cashDate;
	}

	public void setCashDate(Date cashDate) {
		this.cashDate = cashDate;
	}

	public Long getCashNumber() {
		return cashNumber;
	}

	public void setCashNumber(Long cashNumber) {
		this.cashNumber = cashNumber;
	}

	public String getCashFile() {
		return cashFile;
	}

	public void setCashFile(String cashFile) {
		this.cashFile = cashFile;
	}

	public String getCashMemo() {
		return cashMemo;
	}

	public void setCashMemo(String cashMemo) {
		this.cashMemo = cashMemo;
	}

	public BigDecimal getCashMoney() {
		return cashMoney;
	}

	public void setCashMoney(BigDecimal cashMoney) {
		this.cashMoney = cashMoney;
	}

	public void setCreateTime(Date createTime) {
	}

	public Date getCreateTime() {
		return null;
	}
}
