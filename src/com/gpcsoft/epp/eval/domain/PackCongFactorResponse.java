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

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;

/** 
 * @Description: 指标响应与包件中间表
 * @version V1.0
 * @Create Date 2010-8-4 下午03:38:49 By wanghz   
 * @gpcsoft.package packageDir="com.gpcsoft.epp.eval"
 * @gpcsoft.page domain="eval" project="es/planform"
 * @gpcsoft.domain
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PACK_CONG_FACTOR_RESPONSE")
public class PackCongFactorResponse implements GpcBaseObject{
	
	@Id
	@Column(name="CONF_RES_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;

	@Column(name="BID_P_ID")
	private String bidPackageId;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="RES_ID")
	private CongFactorResponse congFactorResponse;
	
	@Column(name="RES_TYPE")
	private String resType;

	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getBidPackageId() {
		return bidPackageId;
	}

	public void setBidPackageId(String bidPackageId) {
		this.bidPackageId = bidPackageId;
	}

	public CongFactorResponse getCongFactorResponse() {
		return congFactorResponse;
	}

	public void setCongFactorResponse(CongFactorResponse congFactorResponse) {
		this.congFactorResponse = congFactorResponse;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
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
