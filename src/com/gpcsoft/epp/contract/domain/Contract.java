package com.gpcsoft.epp.contract.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * 采购合同
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
@Table(name = "CONTRACT")
public class Contract extends WorkFlowModel implements GpcBaseObject,IPropertyCUserTime{


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CONTRACT_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;// 主键

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@org.hibernate.annotations.Cascade(value=CascadeType.ALL)
	@JoinColumn(name = "CONTRACT_ACQUIRER_ID")
	@BatchSize(size = 15)
	private ContractAcquirer partyA;// 甲方买方

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@org.hibernate.annotations.Cascade(value=CascadeType.ALL)
	@JoinColumn(name = "CONTRACT_SUPPLIER_ID")
	@BatchSize(size = 15)
	private ContractSupplier secondParty;// 乙方卖方

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@org.hibernate.annotations.Cascade(value=CascadeType.ALL)
	@JoinColumn(name = "FUNDS_CONSTITUTE_ID")
	@BatchSize(size = 15)
	private FundsConstitute fundsConstitute; //资金构成
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@org.hibernate.annotations.Cascade(value=CascadeType.ALL)
	@JoinColumn(name = "WITNESS_PARTY")
	@BatchSize(size = 15)
	private WitnessParty witenessParty;// 见证方
	
	@Column(name = "ORDER_ID")
	private String orderId;//订单ID
	
	@Column(name = "CONTRACT_METHOD")
	private String contractMethod;//合同签订方式
	
	@Column(name = "CONTRACT_TASKPLAN")
	private String contracttTaskplan;//任务书执行情况
	
	@Column(name = "PROJECT_ID")
	private String projectId;//项目ID
	
	@Column(name = "SUB_PROJECT_ID")
	private String subProjectId;//包组ID
	
	@Column(name = "TaskPlanSub_IDS")
	private String taskPlanSubIds;//申报书条目ID(以','分隔多个组合的)
	
	@Column(name = "CONTRACT_TEXT")
	private String attachRelaId;// 合同书文本 附件

	@Column(name = "CONTRACT_ENCLOSURE")
	private String contractEn;// 合同附件

	@Column(name = "CONTRACT_NAME")
	private String contractName;// 合同名称
	
	@Column(name = "CONTRACT_NO")
	private String contractNo;// 合同编号
	
	@Column(name = "CONTRACT_STATUS")
	private String contractStatus;// 合同状态

	@Column(name = "BUYER")
	private String cuyerId;// 采购人ID

	@Column(name = "SUPPLIER")
	private String cupplierId;// 供应商Id
	
	@Column(name = "AGENCY")
	private String agencyId;// 代理机构Id
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CreUser", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User createUser;
	
	@Column(name = "CONTRACT_BEGIN_TIME")
	@Temporal(TemporalType.DATE)
	private Date createTime;// 合同开始时间

	@Column(name = "CONTRACT_SIGNED_TIME")
	@Temporal(TemporalType.DATE)
	private Date contractSignTime;// 合同签订时间

	@Column(name = "CONTRACT_END_TIME")
	@Temporal(TemporalType.DATE)
	private Date contractEndTime;// 合同结束时间

	@Column(name = "CAUTION_MONEY")
	private Double contractMoney;// 履约保证金

	@Column(name = "QUALITY_MONEY")
	private Double qualityMoney;// 质保金

	@Column(name = "PAYMENT_TYPE")
	private String paymentType;// 支付方式

	@Column(name = "CONTRACT_TYPE")
	private String contractType;// 合同类型：1 系统自动产生的合同2 手工录入合同
	
	@Column(name = "RECEIVE_TIME")
	@Temporal(TemporalType.DATE)
	private Date receiveTme;// 交货时间
	
	@Column(name = "RECEIVE_ADDRESS")
	private String receiveAddr;// 交货地点
	
	@Column(name = "CONTRACT_DESC")
	private String contractDesc;// 合同描述
	
	@Column(name = "REMARK")
	private String remark;// 备注
	
	@Column(name = "IS_CHANGED")
	private String isChanged;// 是否变更合同
	
	@Column(name = "OLD_CONTRACT")
	private String oldContractno;// 旧合同编号
	
	@Column(name = "PAY_TIMES")
	private int payTimes;// 支付次数
	
	@Column(name = "TOTAL_AMT")
	private double totalMoeny;//合同总额
	
	@Column(name = "CAUTION_MONEY_REPAY_DATE")
	@Temporal(TemporalType.DATE)
	private Date repayDate;//退保日期
	
	@Column(name = "PURCHASE_METHOD")
	private String purchaseMethod;//采购方式
	
	@Column(name = "CAUTION_MONEY_STATUS")
	private String payStatus;// 履约保证金的缴交状况，枚举类型：未缴，已交、申请退回，已退
	
	@Column(name = "CAUTION_MONEY_REPAY_REALY_DATE")
	@Temporal(TemporalType.DATE)
	private Date paybackDate;//保证金实际退回日期
	
	@Column(name = "LAST_PRINT_TIME")
	@Temporal(TemporalType.DATE)
	private Date lastPrintDate;//最后打印时间，只有项目经办人打印合同时，才记录这个时间，其它人打印的，不用记录。
	
	@Column(name = "SUBMIT_TIME")
	@Temporal(TemporalType.DATE)
	private Date submitDate;//提交备案时间，只有在提交采购处备案时，才需要记录这个时间。
	
	@Column(name = "CONTRACT_DELIVERY")
	private int deliver;//交货期限
	
	@Column(name = "PAY_CONDTION")
	private String payCondition;// 付款条件
	
	@Column(name = "TOTAL_COPIES")
	private int totalCopies;//合同份数
	
	@Column(name = "CAUTION_MONEY_RETURN_LIMITED")
	private int returnLimeitTime;//退保期限
	
	@Column(name = "PURDELEGATION_ID")
	private String taskplanSubID;//合同关联的申报书条目ID
	
	@Column(name = "CONTRACT_SAVE_TIME")
	@Temporal(TemporalType.DATE)
	private Date contractSaveTime;//经办人起草合同时间
	
	@Column(name = "CONTRACT_TO_BACKUP_TIME")
	@Temporal(TemporalType.DATE)
	private Date contractBackUpTime;//经办人提交备案时间
	
	@Column(name = "STATUS")
	private String status;//合同验收状态
	
	@Column(name = "ACCEPTANCE_DATE")
	@Temporal(TemporalType.DATE)
	private Date acceptTime;//验收日期
	
	@Column(name = "CREATOR")
	private String createUserId;//合同起草人id
	
	@Transient
	private String contractStatusCN;// 合同状态
	
	@LazyCollection(value=LazyCollectionOption.FALSE)
    @OneToMany
    @BatchSize(size=10)
    @JoinColumn(name = "CONTRACT_ID") 
    @Cascade({CascadeType.ALL})
	private List<ContractPaymentApplyInfo> contractPaymentApplyInfo;
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public ContractAcquirer getPartyA() {
		return partyA;
	}

	public void setPartyA(ContractAcquirer partyA) {
		this.partyA = partyA;
	}

	public ContractSupplier getSecondParty() {
		return secondParty;
	}

	public void setSecondParty(ContractSupplier secondParty) {
		this.secondParty = secondParty;
	}


	public FundsConstitute getFundsConstitute() {
		return fundsConstitute;
	}

	public void setFundsConstitute(FundsConstitute fundsConstitute) {
		this.fundsConstitute = fundsConstitute;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	public String getSubProjectId() {
		return subProjectId;
	}

	public void setSubProjectId(String subProjectId) {
		this.subProjectId = subProjectId;
	}
	
	public String getTaskPlanSubIds() {
		return taskPlanSubIds;
	}

	public void setTaskPlanSubIds(String taskPlanSubIds) {
		this.taskPlanSubIds = taskPlanSubIds;
	}

	public WitnessParty getWitenessParty() {
		return witenessParty;
	}

	public void setWitenessParty(WitnessParty witenessParty) {
		this.witenessParty = witenessParty;
	}
	
	public String getAttachRelaId() {
		return attachRelaId;
	}

	public void setAttachRelaId(String attachRelaId) {
		this.attachRelaId = attachRelaId;
	}

	public String getContractEn() {
		return contractEn;
	}

	public void setContractEn(String contractEn) {
		this.contractEn = contractEn;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public String getCuyerId() {
		return cuyerId;
	}

	public void setCuyerId(String cuyerId) {
		this.cuyerId = cuyerId;
	}

	public String getCupplierId() {
		return cupplierId;
	}

	public void setCupplierId(String cupplierId) {
		this.cupplierId = cupplierId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getContractSignTime() {
		return contractSignTime;
	}

	public void setContractSignTime(Date contractSignTime) {
		this.contractSignTime = contractSignTime;
	}

	public Date getContractEndTime() {
		return contractEndTime;
	}

	public void setContractEndTime(Date contractEndTime) {
		this.contractEndTime = contractEndTime;
	}

	public Double getContractMoney() {
		return contractMoney;
	}

	public void setContractMoney(Double contractMoney) {
		this.contractMoney = contractMoney;
	}

	public Double getQualityMoney() {
		return qualityMoney;
	}

	public void setQualityMoney(Double qualityMoney) {
		this.qualityMoney = qualityMoney;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public Date getReceiveTme() {
		return receiveTme;
	}

	public void setReceiveTme(Date receiveTme) {
		this.receiveTme = receiveTme;
	}

	public String getReceiveAddr() {
		return receiveAddr;
	}

	public void setReceiveAddr(String receiveAddr) {
		this.receiveAddr = receiveAddr;
	}

	public String getContractDesc() {
		return contractDesc;
	}

	public void setContractDesc(String contractDesc) {
		this.contractDesc = contractDesc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsChanged() {
		return isChanged;
	}

	public void setIsChanged(String isChanged) {
		this.isChanged = isChanged;
	}

	public String getOldContractno() {
		return oldContractno;
	}

	public void setOldContractno(String oldContractno) {
		this.oldContractno = oldContractno;
	}

	public int getPayTimes() {
		return payTimes;
	}

	public void setPayTimes(int payTimes) {
		this.payTimes = payTimes;
	}

	public double getTotalMoeny() {
		return totalMoeny;
	}

	public void setTotalMoeny(double totalMoeny) {
		this.totalMoeny = totalMoeny;
	}

	public Date getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}

	public String getPurchaseMethod() {
		return purchaseMethod;
	}

	public void setPurchaseMethod(String purchaseMethod) {
		this.purchaseMethod = purchaseMethod;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public Date getPaybackDate() {
		return paybackDate;
	}

	public void setPaybackDate(Date paybackDate) {
		this.paybackDate = paybackDate;
	}

	public Date getLastPrintDate() {
		return lastPrintDate;
	}

	public void setLastPrintDate(Date lastPrintDate) {
		this.lastPrintDate = lastPrintDate;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public int getDeliver() {
		return deliver;
	}

	public void setDeliver(int deliver) {
		this.deliver = deliver;
	}

	public String getPayCondition() {
		return payCondition;
	}

	public void setPayCondition(String payCondition) {
		this.payCondition = payCondition;
	}

	public int getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}

	public int getReturnLimeitTime() {
		return returnLimeitTime;
	}

	public void setReturnLimeitTime(int returnLimeitTime) {
		this.returnLimeitTime = returnLimeitTime;
	}

	public String getTaskplanSubID() {
		return taskplanSubID;
	}

	public void setTaskplanSubID(String taskplanSubID) {
		this.taskplanSubID = taskplanSubID;
	}

	public Date getContractSaveTime() {
		return contractSaveTime;
	}

	public void setContractSaveTime(Date contractSaveTime) {
		this.contractSaveTime = contractSaveTime;
	}

	public Date getContractBackUpTime() {
		return contractBackUpTime;
	}

	public void setContractBackUpTime(Date contractBackUpTime) {
		this.contractBackUpTime = contractBackUpTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractMethod() {
		return contractMethod;
	}

	public void setContractMethod(String contractMethod) {
		this.contractMethod = contractMethod;
	}

	public String getContracttTaskplan() {
		return contracttTaskplan;
	}

	public void setContracttTaskplan(String contracttTaskplan) {
		this.contracttTaskplan = contracttTaskplan;
	}

	public String getContractStatusCN() {
		this.contractStatusCN = ContractConfirmEnum.getCN(this.getContractStatus());
		return contractStatusCN;
	}

	public void setContractStatusCN(String contractStatusCN) {
		this.contractStatusCN = contractStatusCN;
	}
	
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public List<ContractPaymentApplyInfo> getContractPaymentApplyInfo() {
		return contractPaymentApplyInfo;
	}

	public void setContractPaymentApplyInfo(
			List<ContractPaymentApplyInfo> contractPaymentApplyInfo) {
		this.contractPaymentApplyInfo = contractPaymentApplyInfo;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	
	
}