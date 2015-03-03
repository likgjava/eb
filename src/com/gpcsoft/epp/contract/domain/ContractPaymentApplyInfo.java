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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;


/**
 * 合同支付申请信息
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
@Table(name = "CONTRACT_PAYMENT_APPLY_INFO")
public class ContractPaymentApplyInfo extends WorkFlowModel implements GpcBaseObject {
    
	@Id
	@Column(name = "CONTRACT_PAYMENT_APPLY_INFO_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;//主键
	
	
	@Column(name = "CONTRACT_NO")
	private String contractNo;//合同号
	
	
	@Column(name = "CONTRACT_AMT")
	private double contractAmt;//合同总金额
	
	
	@Column(name = "APPLY_DATE")
	private Date applyDate;//支付申请日期
	
	
	@Column(name = "THIS_TIME_PAYMENT_AMT")
	private double thisTimePaymentAmt;//本次支付申请金额
	
	
	@Column(name = "THE_TIMES")
	private int theTimes;//第n次付款
//	/**
//	 * 采购单位
//	 */
//	private Buyer buyer;//
	
	
	@Column(name = "PUR_FORM")
	private String purForm;//组织形式  枚举  用字典  政府集中采购/部门集中采购/分散采购
	
	
	@Column(name = "PUR_METHOD")
	private String purMethod;//采购方式       公开招标采购/邀请招标采购/竞争性谈判采购/询价采购/单一来源采购
	
	
	@Column(name = "PAYMENT_STATUS")
	private String paymentStatus;//支付状态      0支付申请已发送待审核 1审核未通过 2审核通过 3支付已完成
	
	
	@Column(name = "PAYMENT_BANK")
	private String paymentBank;//付款账户开户行
	
	
	@Column(name = "PAYMENT_ACCOUNT_NAME")
	private String paymentAccountName;//付款账户名
	
	
	@Column(name = "PAYMENT_ACCOUNT")
	private String paymentAccount;//付款账户
	
	
	@Column(name = "RECEIVE_ACCOUNT")
	private String receiveAccount;//收款账号
	
	
	@Column(name = "RECEIVE_BANK")
	private String receiveBank;// 收款开户行
	
	
	@Column(name = "RECEIVE_ACCOUNT_NAME")
	private String receiveAccountName;//收款账户名
	
	
	@Column(name = "REMARK")
	private String remark;//备注
	
	
	@Column(name = "CONTRACT_ID")
    private String contract;// 合同
    
	
	@Column(name = "HEAD_ACCT_YEAR")
    private String headAcctYear; //headAcctYear 与大平台联调时要用的字段


	public String getObjId() {
		return objId;
	}


	public void setObjId(String objId) {
		this.objId = objId;
	}


	public String getContractNo() {
		return contractNo;
	}


	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}


	public double getContractAmt() {
		return contractAmt;
	}


	public void setContractAmt(double contractAmt) {
		this.contractAmt = contractAmt;
	}


	public Date getApplyDate() {
		return applyDate;
	}


	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}


	public double getThisTimePaymentAmt() {
		return thisTimePaymentAmt;
	}


	public void setThisTimePaymentAmt(double thisTimePaymentAmt) {
		this.thisTimePaymentAmt = thisTimePaymentAmt;
	}


	public int getTheTimes() {
		return theTimes;
	}


	public void setTheTimes(int theTimes) {
		this.theTimes = theTimes;
	}


	public String getPurForm() {
		return purForm;
	}


	public void setPurForm(String purForm) {
		this.purForm = purForm;
	}


	public String getPurMethod() {
		return purMethod;
	}


	public void setPurMethod(String purMethod) {
		this.purMethod = purMethod;
	}


	public String getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	public String getPaymentBank() {
		return paymentBank;
	}


	public void setPaymentBank(String paymentBank) {
		this.paymentBank = paymentBank;
	}


	public String getPaymentAccountName() {
		return paymentAccountName;
	}


	public void setPaymentAccountName(String paymentAccountName) {
		this.paymentAccountName = paymentAccountName;
	}


	public String getPaymentAccount() {
		return paymentAccount;
	}


	public void setPaymentAccount(String paymentAccount) {
		this.paymentAccount = paymentAccount;
	}


	public String getReceiveAccount() {
		return receiveAccount;
	}


	public void setReceiveAccount(String receiveAccount) {
		this.receiveAccount = receiveAccount;
	}


	public String getReceiveBank() {
		return receiveBank;
	}


	public void setReceiveBank(String receiveBank) {
		this.receiveBank = receiveBank;
	}


	public String getReceiveAccountName() {
		return receiveAccountName;
	}


	public void setReceiveAccountName(String receiveAccountName) {
		this.receiveAccountName = receiveAccountName;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getContract() {
		return contract;
	}


	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getHeadAcctYear() {
		return headAcctYear;
	}


	public void setHeadAcctYear(String headAcctYear) {
		this.headAcctYear = headAcctYear;
	}
}