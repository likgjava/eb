package com.gpcsoft.epp.contract.domain;

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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;


/**
 * 资金明细单
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
@Table(name = "CONTRACT_FUND_DETAIL")
public class ContractFundDetail extends WorkFlowModel implements GpcBaseObject {
    

	@Id
	@Column(name = "CONTRACT_FUND_DETAIL_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")	
    private String objId;//主键

	
	@Column(name = "FUND_TYPE")
	private String fundType;//资金性质
	
	@Column(name = "PAY_TYPE")
	private String payType;//支付方式
	
	@Column(name = "IND_SOURCE")
	private String indSource;//预算来源 
	

	
	@Column(name = "AMT")
	private double amt;//总价
	
	@Column(name = "REMARK")
	private String remark;//备注
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "CONTRACT_PAYMENT_APPLY_INFO_ID")	
	@BatchSize(size = 15)	
	private ContractPaymentApplyInfo contractPaymentApplyInfo;//合同支付申请信息
	


	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getIndSource() {
		return indSource;
	}

	public void setIndSource(String indSource) {
		this.indSource = indSource;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ContractPaymentApplyInfo getContractPaymentApplyInfo() {
		return contractPaymentApplyInfo;
	}

	public void setContractPaymentApplyInfo(
			ContractPaymentApplyInfo contractPaymentApplyInfo) {
		this.contractPaymentApplyInfo = contractPaymentApplyInfo;
	}

	
	

}