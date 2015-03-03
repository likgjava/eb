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
 * @Description: 指标与包件中间表
 * @version V1.0
 * @Create Date 2010-8-6 上午09:31:00 By wanghz   
 * @gpcsoft.package packageDir="com.gpcsoft.epp.eval"
 * @gpcsoft.page domain="eval" project="es/planform"
 * @gpcsoft.domain
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_FACTYPE_M_FACTOR")
public class FactypeMfactor implements GpcBaseObject{

	@Id
	@Column(name="TYPE_M_FAC_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@ManyToOne(fetch=FetchType.EAGER,optional=true)
	@JoinColumn(name="CON_FAC_TYPE_ID")
	private CongruousFactorType factorType;
	
	@ManyToOne(fetch=FetchType.EAGER,optional=true)
	@JoinColumn(name="CON_FAC_ID")
	private CongruousFactor factor;
	
	@Column(name="subTenderId")
	private String projId;
	
	public CongruousFactorType getFactorType() {
		return factorType;
	}
	public void setFactorType(CongruousFactorType factorType) {
		this.factorType = factorType;
	}
	public CongruousFactor getFactor() {
		return factor;
	}
	public void setFactor(CongruousFactor factor) {
		this.factor = factor;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#getCreateTime()
	 */
	public Date getCreateTime() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#getObjId()
	 */
	public String getObjId() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#setCreateTime(java.util.Date)
	 */
	public void setCreateTime(Date arg0) {
	}
}
