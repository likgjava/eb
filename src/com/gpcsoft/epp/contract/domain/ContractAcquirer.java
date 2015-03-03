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
 *  需方
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
@Table(name = "CONTRACT_ACQUIRER")
public class ContractAcquirer extends WorkFlowModel implements GpcBaseObject {
	
	@Id
	@Column(name = "CONTRACT_ACQUIRER_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;//主键
	
	@Column(name = "CONTRACT_NO")
	private String contractNo;//合同编号
	
	@Column(name = "ACQUIRER")
	private String acquirer;//买方名称
	
	@Column(name = "TELEPHONE")
	private String telephone;//电话
	
	@Column(name = "FAX")
	private String fax;//传真
	
	@Column(name = "ADDRESS")
	private String address;//地址
	
	@Column(name = "SIGNED_TIME")
	@Temporal(TemporalType.DATE)
	private Date acquirerSignedTime;//合同签定时间
	
	@Column(name = "CORPORATE")
	private String acquirerCorporate;//法人代表

	
	@Column(name = "BUYER")
	private String buyer;//采购人ID
	
	
	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

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

	public String getAcquirer() {
		return acquirer;
	}

	public void setAcquirer(String acquirer) {
		this.acquirer = acquirer;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public Date getAcquirerSignedTime() {
		return acquirerSignedTime;
	}

	public void setAcquirerSignedTime(Date acquirerSignedTime) {
		this.acquirerSignedTime = acquirerSignedTime;
	}

	public String getAcquirerCorporate() {
		return acquirerCorporate;
	}

	public void setAcquirerCorporate(String acquirerCorporate) {
		this.acquirerCorporate = acquirerCorporate;
	}

	
}