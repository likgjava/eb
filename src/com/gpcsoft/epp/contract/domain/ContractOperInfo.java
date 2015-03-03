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
 * 合同操作信息
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
@Table(name = "CONTRACT_OPER_INFO")
public class ContractOperInfo extends WorkFlowModel implements GpcBaseObject {

	private static final long serialVersionUID = 2L;
	
	@Id
	@Column(name = "CONTRACT_OPER_INFO_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;//主键
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CONTRACT_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Contract contractId;//合同ID
	
	@Column(name="OPERATING_PERSONNEL")//关联的外键	 
	private String operatingPersonnel;//操作人
	
	@Column(name = "OPER_DATE")
	private Date operDate;// 操作时间
	
	@Column(name = "OPER_STATUS")
	private String operStatus;//操作状态
	
	@Column(name = "OPINION")
	private String opinion;// 意见
	
	@Column(name = "TASK_ID")
	private String taskID;//任务ID
	
	@Column(name = "WORKFLOW_ID")
	private String workflowID;//工作流程ID
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getOperatingPersonnel() {
		return operatingPersonnel;
	}

	public void setOperatingPersonnel(String operatingPersonnel) {
		this.operatingPersonnel = operatingPersonnel;
	}

	public Date getOperDate() {
		return operDate;
	}

	public void setOperDate(Date operDate) {
		this.operDate = operDate;
	}

	public String getOperStatus() {
		return operStatus;
	}

	public void setOperStatus(String operStatus) {
		this.operStatus = operStatus;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public String getWorkflowID() {
		return workflowID;
	}

	public void setWorkflowID(String workflowID) {
		this.workflowID = workflowID;
	}

	public Contract getContractId() {
		return contractId;
	}

	public void setContractId(Contract contractId) {
		this.contractId = contractId;
	}
	
	
}