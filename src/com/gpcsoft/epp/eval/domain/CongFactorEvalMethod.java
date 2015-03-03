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
 * @Description: 符合性指标评审方法（注：今后与标书编评系统结合并细化） 
 *    
 * @version V1.0
 * @Create Date 2010-8-2 上午10:08:00 By wanghz
 * @gpcsoft.package packageDir="com.gpcsoft.epp.eval"
 * @gpcsoft.page domain="eval" project="es/planform"
 * @gpcsoft.domain
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_CONG_FACTOR_EVAL_METHOD")
public class CongFactorEvalMethod implements GpcBaseObject{

	@Id
	@Column(name="CON_EVAL_M_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@ManyToOne(fetch=FetchType.EAGER,optional=true)
	@JoinColumn(name="CON_FAC_ID")
	private CongruousFactor factor;
	
	@Column(name="REMARK")
	private String remark;

	@Column(name="VALUE_DATA_TYPE")
	private String valueDataType;
	
	/********************************getters and setters**********************************/
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public CongruousFactor getFactor() {
		return factor;
	}

	public void setFactor(CongruousFactor factor) {
		this.factor = factor;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getValueDataType() {
		return valueDataType;
	}

	public void setValueDataType(String valueDataType) {
		this.valueDataType = valueDataType;
	}

	/********************************Impl Methods**********************************/
	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#getCreateTime()
	 */
	public Date getCreateTime() {
		return null;
	}

	
	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#setCreateTime(java.util.Date)
	 */
	public void setCreateTime(Date arg0) {
	}
}
