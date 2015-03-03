package com.gpcsoft.epp.projreview.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;

/**
 * @Description: 评审打分 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.projreview"
 * @gpcsoft.page domain="planform/projreview" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="评审打分"
 * @version V1.0
 * @Create Date 2010-10-9 下午02:20:11 By wanghz
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_EVA_FACTOR_RESULT")
public class EvalFactorResult extends WorkFlowModel implements GpcBaseObject{
	@Id
	@Column(name = "EVA_FAC_RE_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@Column(name = "SELLER_REC_ID",length = 36)
	private String sellerRecId;								// 评审记录ID
	
	@Column(name = "FACTOR_TYPE_ID",length = 36)
	private String factorTypeId;							// 指标分类ID
	
	@Column(name = "FACTOR_TYPE_NAME",length = 36)
	private String factorTypeName;							// 指标分类名称
	
	@Column(name = "FACTOR_ID",length = 36)
	private String factorId;								// 指标ID
	
	@Column(name = "FACTOR_NAME",length = 36)
	private String factorName;								// 指标名称
	
	@Column(name = "EVA_SCORE",length = 36)
	private String score;									// 评审打分
	
	@Column(name = "MEMO",length = 36)
	private String memo;									// 备注
	
	@Transient
	private String respValue;								// 指标响应值
	@Transient
	private String respAttrId;								// 指标响应文件ID
	@Transient
	private String respAttrName;							// 指标响应文件名称
	
	@Transient
	private String maxScore;
	
	public String getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(String maxScore) {
		this.maxScore = maxScore;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getSellerRecId() {
		return sellerRecId;
	}

	public void setSellerRecId(String sellerRecId) {
		this.sellerRecId = sellerRecId;
	}

	public String getFactorTypeId() {
		return factorTypeId;
	}

	public void setFactorTypeId(String factorTypeId) {
		this.factorTypeId = factorTypeId;
	}

	/**
	 * @gpcsoft.property title="指标分类名称"
	 */
	public String getFactorTypeName() {
		return factorTypeName;
	}

	public void setFactorTypeName(String factorTypeName) {
		this.factorTypeName = factorTypeName;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	/**
	 * @gpcsoft.property title="指标名称"
	 */
	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	/**
	 * @gpcsoft.property title="打分"
	 */
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	/**
	 * @gpcsoft.property title="备注"
	 */
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getRespValue() {
		return respValue;
	}

	public void setRespValue(String respValue) {
		this.respValue = respValue;
	}

	public String getRespAttrId() {
		return respAttrId;
	}

	public void setRespAttrId(String respAttrId) {
		this.respAttrId = respAttrId;
	}

	public String getRespAttrName() {
		return respAttrName;
	}

	public void setRespAttrName(String respAttrName) {
		this.respAttrName = respAttrName;
	}
}
