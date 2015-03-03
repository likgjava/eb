package com.gpcsoft.epp.contract.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;


/**
 * 资金构成
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
@Table(name = "CONTRACT_FUNDS_CONSTITUTE")
public class FundsConstitute extends WorkFlowModel implements GpcBaseObject {


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FUNDS_CONSTITUTE_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;//主键

	@Column(name = "CONTRACT_NO")
	private String contractNo;//合同编号
	
	@Column(name = "BUDGET_FUNDS")
	private double budgetFunds;//预算内
	
	@Column(name = "OFF_BUDGET_FUNDS")
	private double offBudgetFunds;//预算外
	
	@Column(name = "SELF_FINANCING")
	private double selfFinancing;//自筹
	
	@Column(name = "TOTAL_VALUE")
	private double totalValue;//资金总额
	
	@Column(name = "NORMAL_INNER_FUNDS")
	private double normalInnerBudgets;//一般预算内资金金额
	
	@Column(name = "GOVERNMENT_FUNDS")
	private double governmentBudgets;//政府性基金

	@Column(name = "SOCIAL_SECURITY_FUNDS")
	private double socialSecurityBudgets;//社保基金金额
	
	@Column(name = "GOVERNMENT_CAPITAL_FUNDS")
	private double governmentCapitalBudgets;//国有资本经营预算金额

	@Column(name = "INTERCOURSE_FUNDS")
	private double intercourseBudgets;//往来资金金额

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

	public double getBudgetFunds() {
		return budgetFunds;
	}

	public void setBudgetFunds(double budgetFunds) {
		this.budgetFunds = budgetFunds;
	}

	public double getOffBudgetFunds() {
		return offBudgetFunds;
	}

	public void setOffBudgetFunds(double offBudgetFunds) {
		this.offBudgetFunds = offBudgetFunds;
	}

	public double getSelfFinancing() {
		return selfFinancing;
	}

	public void setSelfFinancing(double selfFinancing) {
		this.selfFinancing = selfFinancing;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	public double getNormalInnerBudgets() {
		return normalInnerBudgets;
	}

	public void setNormalInnerBudgets(double normalInnerBudgets) {
		this.normalInnerBudgets = normalInnerBudgets;
	}

	public double getGovernmentBudgets() {
		return governmentBudgets;
	}

	public void setGovernmentBudgets(double governmentBudgets) {
		this.governmentBudgets = governmentBudgets;
	}

	public double getSocialSecurityBudgets() {
		return socialSecurityBudgets;
	}

	public void setSocialSecurityBudgets(double socialSecurityBudgets) {
		this.socialSecurityBudgets = socialSecurityBudgets;
	}

	public double getGovernmentCapitalBudgets() {
		return governmentCapitalBudgets;
	}

	public void setGovernmentCapitalBudgets(double governmentCapitalBudgets) {
		this.governmentCapitalBudgets = governmentCapitalBudgets;
	}

	public double getIntercourseBudgets() {
		return intercourseBudgets;
	}

	public void setIntercourseBudgets(double intercourseBudgets) {
		this.intercourseBudgets = intercourseBudgets;
	}

	

}