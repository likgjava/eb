package com.gpcsoft.epp.contract.domain;

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
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;

/**
 * 支付批次信息
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.contract"
 * @gpcsoft.page domain="planform/contract"
 * @hibernate.class table="CONTRACT" dynamic-insert="true" dynamic-update="true"
 * @author Administrator
 * @version 1.0
 * @created 13-六月-2009 19:56:37
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "CONTRACT_PAYMENT_TIMES")
public class ContractPaymentTimes extends WorkFlowModel implements GpcBaseObject {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CONTRACT_PAYMENT_TIMES_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;//主键
	
	@Column(name = "BATCH")
	private int batch;//支付批次
	
	@Column(name = "RATIO")
	private double ratio;//支付比例
	
	@Column(name = "AMT")
	private double amt;//付款金额
	
	@Column(name = "PAY_TIME")
	@Temporal(TemporalType.DATE)
	private Date payTime;//付款时间
	
	@Column(name = "AMT_REMARK")
	private String amtRemark;//资金明细备注
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "CONTRACT_ID")
	@BatchSize(size = 15)
	private Contract contract;//合同
	
	@Column(name = "IS_UPLOAD_ACCEPTANCE")
	private String isUploadAcceptance;//是否上传验收材料

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public int getBatch() {
		return batch;
	}

	public void setBatch(int batch) {
		this.batch = batch;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getAmtRemark() {
		return amtRemark;
	}

	public void setAmtRemark(String amtRemark) {
		this.amtRemark = amtRemark;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getIsUploadAcceptance() {
		return isUploadAcceptance;
	}

	public void setIsUploadAcceptance(String isUploadAcceptance) {
		this.isUploadAcceptance = isUploadAcceptance;
	}
	
  

}