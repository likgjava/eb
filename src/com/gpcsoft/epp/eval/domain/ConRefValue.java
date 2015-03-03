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
 * @Description: TODO 
 *    
 * @version V1.0
 * @Create Date 2010-8-2 上午10:00:04 By wanghz   
 * @gpcsoft.package packageDir="com.gpcsoft.epp.eval"
 * @gpcsoft.page domain="eval" project="es/planform"
 * @gpcsoft.domain
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_CON_REF_VALUE")
public class ConRefValue implements GpcBaseObject{
	
	@Id
	@Column(name="CON_REF_V_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@ManyToOne(fetch=FetchType.EAGER,optional=true)
	@JoinColumn(name="CON_FAC_ID")
	private CongruousFactor factor;
	
	@Column(name="VALUE_GROUP")
	private String valueGroup;
	
	@Column(name="REF_VALUE")
	private String refValue;
	
	@Column(name="SHOW_NO")
	private Integer showNO;

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

	public String getValueGroup() {
		return valueGroup;
	}

	public void setValueGroup(String valueGroup) {
		this.valueGroup = valueGroup;
	}

	public String getRefValue() {
		return refValue;
	}

	public void setRefValue(String refValue) {
		this.refValue = refValue;
	}

	public Integer getShowNO() {
		return showNO;
	}

	public void setShowNO(Integer showNO) {
		this.showNO = showNO;
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
