package com.gpcsoft.epp.contract.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;


/**
 * 供方
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
@Table(name = "CONTRACT_SUPPLIER")
public class ContractSupplier extends WorkFlowModel implements GpcBaseObject {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CONTRACT_SUPPLIER_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;//主键
	
	@Column(name = "CONTRACT_NO")
	private String contractNo;//合同编号
	
	@Column(name = "SUPPLIER_NAME")
	private String supplierName;//供方名称

	@Column(name = "TELEPHONE")
	private String supplierTel;//电话

	@Column(name = "FAX")
	private String fax;//传真
	
	@Column(name = "SUPPLIER")
	private String supplier;//传真

	@Column(name = "ADDRESS")
	private String address;//地址

	@Column(name = "DEPOSIT_BANK")
	private String depositBank;//开户银行

	@Column(name = "ACCOUNT_NO")
	private String accountNo;//银行帐号

	@Column(name = "OPEN_ACCOUNT_NAME")
    private String openAccountName;//银行帐号名称(户名)

	@Column(name = "SIGNED_TIME")
	@Temporal(TemporalType.DATE)
    private Date supplierSignedTime;//合同签定时间
 
	@Column(name = "CORPORATE")
    private String supplierCorporate;//法人代表

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

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierTel() {
		return supplierTel;
	}

	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepositBank() {
		return depositBank;
	}

	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getOpenAccountName() {
		return openAccountName;
	}

	public void setOpenAccountName(String openAccountName) {
		this.openAccountName = openAccountName;
	}

	public Date getSupplierSignedTime() {
		return supplierSignedTime;
	}

	public void setSupplierSignedTime(Date supplierSignedTime) {
		this.supplierSignedTime = supplierSignedTime;
	}

	public String getSupplierCorporate() {
		return supplierCorporate;
	}

	public void setSupplierCorporate(String supplierCorporate) {
		this.supplierCorporate = supplierCorporate;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
}