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
 * 合同支付信息
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
@Table(name = "CONTRACT_PAYMENT_INFO")
public class ContractPaymentInfo extends WorkFlowModel implements GpcBaseObject {

	@Id
	@Column(name = "CONTRACT_PAYMENT_INFO_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;//主键
	
	@Column(name = "CONTRACT_NO")
    private String contractNo;//合同号
	
	@Column(name = "PAYMENT_TIME")
    private Date paymentTime;//支付时间
	
	@Column(name = "PAYMENT_AMT")
    private double paymentAmt;//支付金额
    
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CONTRACT_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private Contract contract;//合同
    
    @Column(name = "PUR_DELEGATION")
    private String  taskPlanId;//任务书 主键
    
    @Column(name = "THE_TIMES")
    private int theTimes;//第n次付款

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

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public double getPaymentAmt() {
		return paymentAmt;
	}

	public void setPaymentAmt(double paymentAmt) {
		this.paymentAmt = paymentAmt;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	public String getTaskPlanId() {
		return taskPlanId;
	}

	public void setTaskPlanId(String taskPlanId) {
		this.taskPlanId = taskPlanId;
	}

	public int getTheTimes() {
		return theTimes;
	}

	public void setTheTimes(int theTimes) {
		this.theTimes = theTimes;
	}
	
	
	
   
}