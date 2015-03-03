
package com.gpcsoft.epp.projreview.domain;

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
import com.gpcsoft.epp.eval.domain.CongruousFactor;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;

/** 
 * @Description: 开标一览表明细
 * @version V1.0
 * @Create Date 2010-7-12 下午05:06:43 By liuke 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.projreview"
 * @gpcsoft.page domain="planform/projreview" project="es"
 * @gpcsoft.domain
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_OPENBID_GENERAL_V_ITEM")
public class OpenBidGeneralVitem extends WorkFlowModel implements GpcBaseObject{
	@Id
	@Column(name="GEN_VIEW_ITEM_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="GEN_VIEW_DEFINE_ID")
	private GenviewDefine genviewDefine;			//开标一览表规则			

	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="GEN_VIEW_ID")
	private OpenbidGeneralview openbidGeneralview;	//开标一览表
	
	@Column(name="FACTOR_ID")
	private String  factorId;						//关联指标			

	@Column(name="FACTOR_NAME")
	private String factorName;                      //指标名称		
	
	@Column(name="RESP_VALUE")
	private String respValue;				        //响应值
		
	@Transient
	private String genviewDefineId;                 //开标一览表规则主键
	
	@Transient
	private String supplierId;	                    //供应商主键
	

	/********************************getters and setters**********************************/
	public String getGenviewDefineId() {
		return genviewDefineId;
	}

	public void setGenviewDefineId(String genviewDefineId) {
		this.genviewDefineId = genviewDefineId;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}
	
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public GenviewDefine getGenviewDefine() {
		return genviewDefine;
	}

	public void setGenviewDefine(GenviewDefine genviewDefine) {
		this.genviewDefine = genviewDefine;
	}

	public OpenbidGeneralview getOpenbidGeneralview() {
		return openbidGeneralview;
	}

	public void setOpenbidGeneralview(OpenbidGeneralview openbidGeneralview) {
		this.openbidGeneralview = openbidGeneralview;
	}

	public String getRespValue() {
		return respValue;
	}

	public void setRespValue(String respValue) {
		this.respValue = respValue;
	}

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	public void setCreateTime(Date date) {
	}
	
	
	public Date getCreateTime() {
		return null;
	}
	
}
