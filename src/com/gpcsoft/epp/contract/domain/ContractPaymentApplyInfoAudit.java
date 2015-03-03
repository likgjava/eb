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
import com.gpcsoft.srplatform.auth.domain.User;


/**
 * 合同支付申请审批信息
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
@Table(name = "CONTRACT_PAYMENT_APPLY_AUDIT")
public class ContractPaymentApplyInfoAudit extends WorkFlowModel implements GpcBaseObject {

	
	@Id
	@Column(name = "PAYMENT_APPLY_AUDIT_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;//主键
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="AUDIT_USER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User auditUser;// 审批人
	
	@Column(name = "OPINION")
	private String opinion;//审批意见
	
	
	@Column(name = "AUDIT_TIME")
	private Date auditTime;//审批时间
	
	@Column(name = "AUDIT_STATUS")
	private String auditStatus;//审批状态 同意 或 不同意
	
	@Column(name = "WF_INST_PROCESS_ID")
	private String wfInstProcessId;//工作流关联ID
	
	@Column(name = "WF_INST_WORK_ITEM_ID")
	private String wfInstWorkItemId;//工作项关联ID
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CONTRACT_PAYMENT_APPLY_INFO_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private ContractPaymentApplyInfo contractPaymentApplyInfo;//合同支付申请信息

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public User getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(User auditUser) {
		this.auditUser = auditUser;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getWfInstProcessId() {
		return wfInstProcessId;
	}

	public void setWfInstProcessId(String wfInstProcessId) {
		this.wfInstProcessId = wfInstProcessId;
	}

	public String getWfInstWorkItemId() {
		return wfInstWorkItemId;
	}

	public void setWfInstWorkItemId(String wfInstWorkItemId) {
		this.wfInstWorkItemId = wfInstWorkItemId;
	}

	public ContractPaymentApplyInfo getContractPaymentApplyInfo() {
		return contractPaymentApplyInfo;
	}

	public void setContractPaymentApplyInfo(
			ContractPaymentApplyInfo contractPaymentApplyInfo) {
		this.contractPaymentApplyInfo = contractPaymentApplyInfo;
	}
	
	


}