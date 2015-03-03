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
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * 合同验收信息
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
@Table(name = "CONTRACT_ACCEPTANCE")
public class ContractAcceptance extends WorkFlowModel implements GpcBaseObject {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CONTRACT_ACCEPTANCE_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;//主键
	
	@JoinColumn(name = "CONTRACT_ID")
	@BatchSize(size = 15)
    private Contract contract;//合同信息
    
	@Column(name = "ACCEPTANCE_DATE")
	@Temporal(TemporalType.DATE)
	private Date acceptanceDate;// 验收日期

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ACCEPTANCE_REPORT")
	@BatchSize(size = 15)
	private Attachment acceptanceReport;//验收报告(附件)
	
	@Column(name = "ACCEPTANCE_OPINION")
	private String acceptanceOpinion;//验收意见
	
	@Column(name = "ACCEPTANCE_PRINCIPAL")
	private String acceptancePrincipal;//验收负责人
	
	@Column(name = "INVOICE")
	private String invoice;//发票等附件
	
	@Column(name = "ACCEPTANCE_DESC")
	private String acceptanceDesc;//验收说明
	
	@Column(name = "ACCEPTANCE_RESULT")
	private String acceptanceResult;//验收结果 通过、不通过
	
	@Column(name = "ACCEPTANCE_APPLY_DATE")
	private Date acceptanceApplyDate;//验收申请日期
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ACCEPTANCE_APPLY_USER")
	@BatchSize(size = 15)
	private User acceptanceApplyUser;//验收申请人
	
	@Column(name = "ACCEPTANCE_APPLY_INFO")
	private String acceptanceApplyInfo;//验收申请资料
	
	@Column(name = "ACCEPTANCE_APPLY_DESC")
	private String acceptanceApplyDesc;//验收申请说明
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "CHECK_ACCEPT_USER")
	@BatchSize(size = 15)
	private User checkAndAcceptUser;//验收、评价人
	
	@Column(name = "STATUS")
	private String status;//合同验收状态
	
	@Column(name = "ACCEPTANCE_LINK_MAN")
	private String acceptanceLinkMan;//验收申请联系人
	
	@Column(name = "ACCEPTANCE_LINK_TEL")
	private String acceptanceLinkTel;//验收申请联系人电话
	
	@Column(name = "CONTRACT_PAYMENT_APPLY_INFO_ID")
	private String contractPaymentApplyInfoId;//合同支付申请id
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "CONTRACT_PAYEMNT_TIMES_ID")
	@BatchSize(size = 15)
	private ContractPaymentTimes contractPaymentTimes;//合同支付批次
	
	@Column(name = "THIS_TIMES")
	private int theTimes;//支付第几批

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Date getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public Attachment getAcceptanceReport() {
		return acceptanceReport;
	}

	public void setAcceptanceReport(Attachment acceptanceReport) {
		this.acceptanceReport = acceptanceReport;
	}

	public String getAcceptanceOpinion() {
		return acceptanceOpinion;
	}

	public void setAcceptanceOpinion(String acceptanceOpinion) {
		this.acceptanceOpinion = acceptanceOpinion;
	}

	public String getAcceptancePrincipal() {
		return acceptancePrincipal;
	}

	public void setAcceptancePrincipal(String acceptancePrincipal) {
		this.acceptancePrincipal = acceptancePrincipal;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getAcceptanceDesc() {
		return acceptanceDesc;
	}

	public void setAcceptanceDesc(String acceptanceDesc) {
		this.acceptanceDesc = acceptanceDesc;
	}

	public String getAcceptanceResult() {
		return acceptanceResult;
	}

	public void setAcceptanceResult(String acceptanceResult) {
		this.acceptanceResult = acceptanceResult;
	}

	public Date getAcceptanceApplyDate() {
		return acceptanceApplyDate;
	}

	public void setAcceptanceApplyDate(Date acceptanceApplyDate) {
		this.acceptanceApplyDate = acceptanceApplyDate;
	}

	public User getAcceptanceApplyUser() {
		return acceptanceApplyUser;
	}

	public void setAcceptanceApplyUser(User acceptanceApplyUser) {
		this.acceptanceApplyUser = acceptanceApplyUser;
	}

	public String getAcceptanceApplyInfo() {
		return acceptanceApplyInfo;
	}

	public void setAcceptanceApplyInfo(String acceptanceApplyInfo) {
		this.acceptanceApplyInfo = acceptanceApplyInfo;
	}

	public String getAcceptanceApplyDesc() {
		return acceptanceApplyDesc;
	}

	public void setAcceptanceApplyDesc(String acceptanceApplyDesc) {
		this.acceptanceApplyDesc = acceptanceApplyDesc;
	}

	public User getCheckAndAcceptUser() {
		return checkAndAcceptUser;
	}

	public void setCheckAndAcceptUser(User checkAndAcceptUser) {
		this.checkAndAcceptUser = checkAndAcceptUser;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAcceptanceLinkMan() {
		return acceptanceLinkMan;
	}

	public void setAcceptanceLinkMan(String acceptanceLinkMan) {
		this.acceptanceLinkMan = acceptanceLinkMan;
	}

	public String getAcceptanceLinkTel() {
		return acceptanceLinkTel;
	}

	public void setAcceptanceLinkTel(String acceptanceLinkTel) {
		this.acceptanceLinkTel = acceptanceLinkTel;
	}

	public String getContractPaymentApplyInfoId() {
		return contractPaymentApplyInfoId;
	}

	public void setContractPaymentApplyInfoId(String contractPaymentApplyInfoId) {
		this.contractPaymentApplyInfoId = contractPaymentApplyInfoId;
	}

	public ContractPaymentTimes getContractPaymentTimes() {
		return contractPaymentTimes;
	}

	public void setContractPaymentTimes(ContractPaymentTimes contractPaymentTimes) {
		this.contractPaymentTimes = contractPaymentTimes;
	}

	public int getTheTimes() {
		return theTimes;
	}

	public void setTheTimes(int theTimes) {
		this.theTimes = theTimes;
	}
	
  
	
}