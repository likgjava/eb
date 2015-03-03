package com.gpcsoft.smallscale.point.domain;

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
 * @gpcsoft.title value="交易"                            
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate=true,dynamicInsert=true)
@Table(name="eps_point_deal")
@OrderProperty(property="dealDate", flag="true")
public class Deal implements GpcBaseObject{

	private static final long serialVersionUID = 2331347467053800704L;

	@Id
	@Column(name = "DEAL_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/**
	 * 交易日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DEAL_DATE")
	private Date dealDate;
	
	/**
	 * 交易积分额度
	 */
	@Column(name="DEAL_NUMBER")
	private Long dealNumber;
	
	/**
	 * 谁赠送
	 */
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name = "FROM_USER")	 
	@BatchSize(size = 15)
	private User formUser;
	
	/**
	 * 赠送给谁
	 */
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name = "TO_USER")	
	@BatchSize(size = 15)
	private User toUser;
	
	/**
	 * 备注
	 */
	@Column(name="DEAL_MEMO",length=500)
	private String dealMemo;
	

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	public Long getDealNumber() {
		return dealNumber;
	}

	public void setDealNumber(Long dealNumber) {
		this.dealNumber = dealNumber;
	}

	public User getFormUser() {
		return formUser;
	}

	public void setFormUser(User formUser) {
		this.formUser = formUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public String getDealMemo() {
		return dealMemo;
	}

	public void setDealMemo(String dealMemo) {
		this.dealMemo = dealMemo;
	}

	public void setCreateTime(Date paramDate) {
	}

	public Date getCreateTime() {
		return null;
	}
}
