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
 *  Comments: <strong>Project</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   es                    					          
 *  <br/>Module ID: 项目表     		
 *  <br/>Create Date：2010-4-14 下午05:20:48 by guom    					                            
 *  <br/>Modified Date:  2010-4-14 下午05:20:48 by guom                                 
 *  @gpcsoft.package packageDir="com.gpcsoft.epp.contract"
 *  @gpcsoft.page domain="planform/contract" project="es"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="项目"  
 *  @since 0.1
 *  @version: 0.1 
 */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "CONTRACT_CLAUSE_DETAIL")
public class ClauseDetail extends WorkFlowModel implements GpcBaseObject {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CLAUSE_DETAIL_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@Column(name = "CONTRACT_NO")
	private String contractNo;//合同编号
	
	@Column(name = "SEQ")
	private String seq;//明细顺序
	
	@Column(name = "DETAIL_PROPERTY")
	private String detailProperty;//明细属性
	
	@Column(name = "DETAIL_VALUE")
	private String detailValue;//明细值
	
	@Column(name = "VALUE_TYPE")
	private String valueType;//值类型
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "CONTRACT_CLAUSE_ID")
	@BatchSize(size = 15)
	private ContractClause clauseId;//条款ID

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

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getDetailProperty() {
		return detailProperty;
	}

	public void setDetailProperty(String detailProperty) {
		this.detailProperty = detailProperty;
	}

	public String getDetailValue() {
		return detailValue;
	}

	public void setDetailValue(String detailValue) {
		this.detailValue = detailValue;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public ContractClause getClauseId() {
		return clauseId;
	}

	public void setClauseId(ContractClause clauseId) {
		this.clauseId = clauseId;
	}
	
	
}