package com.gpcsoft.epp.eval.domain;

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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.srplatform.auth.domain.Attachment;

/** 
 * @Description: 指标响应
 * @version V1.0
 * @Create Date 2010-8-4 下午03:44:32 By wanghz   
 * @gpcsoft.package packageDir="com.gpcsoft.epp.eval"
 * @gpcsoft.page domain="eval" project="es/planform"
 * @gpcsoft.domain
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_CONG_FACTOR_RESPONSE")
public class CongFactorResponse implements GpcBaseObject{
	
	@Id
	@Column(name="RES_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@Column(name="FACTOR_ID")
	private String factorId;
	
	@Column(name="FACTOR_NAME")
	private String factorName;
	
	@Column(name="RESP_VALUE")
	private String respValue;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="RESP_ATTR")
	private Attachment attr;

	@Transient
	private String packIds;// 不对应数据库字段
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	/**
	 * @gpcsoft.property title="指标响应值"
	 */
	public String getRespValue() {
		return respValue;
	}

	public void setRespValue(String respValue) {
		this.respValue = respValue;
	}

	/**
	 * @gpcsoft.property title="指标响应文件"
	 */
	public Attachment getAttr() {
		return attr;
	}

	public void setAttr(Attachment attr) {
		this.attr = attr;
	}
	public String getPackIds() {
		return packIds;
	}
	public void setPackIds(String packIds) {
		this.packIds = packIds;
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#getCreateTime()
	 */
	public Date getCreateTime() {
		return null;
	}

	
	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#setCreateTime(java.util.Date)
	 */
	public void setCreateTime(Date date) {
	}
}
