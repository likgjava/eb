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
 * @gpcsoft.title value="消费"                            
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate=true,dynamicInsert=true)
@Table(name="eps_point_consume")
@OrderProperty(property="consumeDate", flag="true")
public class Consume  implements GpcBaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5913071843519149188L;

	@Id
	@Column(name = "CONSUME_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name = "USER_ID")	 
	@BatchSize(size = 15)
	private User userId; 
	
	/**
	 * 消费类型 0 参与采购 1罚分, 2兑换礼品
	 */
	@Column(name = "CONSUME_TYPE",length=1)
	private String consumeType;
	
	@Transient
	private String consumeTypeCN;
	
	/**
	 * 消费日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CONSUME_DATE")
	private Date consumeDate;
	
	/**
	 * 消费额度
	 */
	@Column(name = "CONSUME_NUMBER")
	private Long consumeNumber;
	
	
	/**
	 * 使用来源
	 */
	@Column(name = "CONSUME_SOURCE",length=100)
	private String consumeSource;
	
	/**
	 * 所在项目
	 */
	@Column(name = "CONSUME_PROJECT_ID",length=50)
	private String consumeProjectId;
	
	/**
	 * 备注
	 */
	@Column(name = "CONSUME_MEMO",length=500)
	private String consumeMemo;

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

	public String getConsumeType() {
		return consumeType;
	}

	public void setConsumeType(String consumeType) {
		this.consumeType = consumeType;
	}

	public Date getConsumeDate() {
		return consumeDate;
	}

	public void setConsumeDate(Date consumeDate) {
		this.consumeDate = consumeDate;
	}

	public Long getConsumeNumber() {
		return consumeNumber;
	}

	public void setConsumeNumber(Long consumeNumber) {
		this.consumeNumber = consumeNumber;
	}

	public String getConsumeSource() {
		return consumeSource;
	}

	public void setConsumeSource(String consumeSource) {
		this.consumeSource = consumeSource;
	}

	public String getConsumeProjectId() {
		return consumeProjectId;
	}

	public void setConsumeProjectId(String consumeProjectId) {
		this.consumeProjectId = consumeProjectId;
	}

	public String getConsumeMemo() {
		return consumeMemo;
	}

	public void setConsumeMemo(String consumeMemo) {
		this.consumeMemo = consumeMemo;
	}

	public String getConsumeTypeCN() {
		this.consumeTypeCN = SmallscaleEnum.getConsumeTypeCN(this.consumeType);
		return consumeTypeCN;
	}

	public void setConsumeTypeCN(String consumeTypeCN) {
		this.consumeTypeCN = consumeTypeCN;
	}
	
	public void setCreateTime(Date createTime) {
	}

	public Date getCreateTime() {
		return null;
	}
}
