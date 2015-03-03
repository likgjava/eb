package com.gpcsoft.epp.contract.domain;

import java.io.Serializable;

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

import com.gpcsoft.core.model.BaseObject;


/**
 * 合同条款
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
@Table(name = "CONTRACT_CLAUSE")
public class ContractClause extends BaseObject implements Serializable {

	@Id
	@Column(name = "CONTRACT_CLAUSE_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;//主键
	
	@Column(name = "PARENT_ID")
	private String parentId;//父条款主键
	
	@Column(name = "CONTRACT_NO")
	private String contractNo;//合同编号
	
	@Column(name = "CLAUSE_TYPE")
	private String clauseType;//条款类型
	
	@Column(name = "CLAUSE_PROPERTY")
	private String clauseProperty;//条款属性
	
	@Column(name = "CLAUSE_CONTENT")
	private String clauseContent;//条款内容
	
	@Column(name = "CLAUSE_NAME")
	private String clauseName;//条款名称
	
	@Column(name = "CLAUSE_ACCEPT_CRITERIA")
	private String clauseAcceptCriteria;//条款验收标准
	
	@Column(name = "CLAUSE_PERFORMANCE")
	private String clausePerformance;//条款履约情况 
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "CONTRACT_ID")
	// 关联的外键
	@BatchSize(size = 15)
	private Contract contract;//明细列表

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getClauseType() {
		return clauseType;
	}

	public void setClauseType(String clauseType) {
		this.clauseType = clauseType;
	}

	public String getClauseProperty() {
		return clauseProperty;
	}

	public void setClauseProperty(String clauseProperty) {
		this.clauseProperty = clauseProperty;
	}

	public String getClauseContent() {
		return clauseContent;
	}

	public void setClauseContent(String clauseContent) {
		this.clauseContent = clauseContent;
	}

	public String getClauseName() {
		return clauseName;
	}

	public void setClauseName(String clauseName) {
		this.clauseName = clauseName;
	}

	public String getClauseAcceptCriteria() {
		return clauseAcceptCriteria;
	}

	public void setClauseAcceptCriteria(String clauseAcceptCriteria) {
		this.clauseAcceptCriteria = clauseAcceptCriteria;
	}

	public String getClausePerformance() {
		return clausePerformance;
	}

	public void setClausePerformance(String clausePerformance) {
		this.clausePerformance = clausePerformance;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}



}