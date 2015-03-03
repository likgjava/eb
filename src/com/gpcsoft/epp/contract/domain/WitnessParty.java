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
 * 见证方
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
@Table(name = "CONTRACT_WITNESS_PARTY")
public class WitnessParty extends WorkFlowModel implements GpcBaseObject {


	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "WITNESS_PARTY_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;//主键
	
	@Column(name = "CONTRACT_NO")
	private String contractNo;//合同编号
	
	@Column(name = "AGENT_NAME")
	private String agentName;//见证方名称
	
	@Column(name = "TEL")
	private String tel;//电话
	
	@Column(name = "FAX")
	private String fax;//传真
	
	@Column(name = "ADDRESS")
	private String address;//地址
	
	@Column(name = "CORPORATE")
	private String agentCorporate;//法人代表

	@Column(name = "CONTACT")
	private String contact;//联系人
	
	@Column(name = "SIGNED_TIME")
	@Temporal(TemporalType.DATE)
	private Date signedTime;//合同签定时间
	
	@Column(name = "AGENT")
	private String agent;

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

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public String getAgentCorporate() {
		return agentCorporate;
	}

	public void setAgentCorporate(String agentCorporate) {
		this.agentCorporate = agentCorporate;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getSignedTime() {
		return signedTime;
	}

	public void setSignedTime(Date signedTime) {
		this.signedTime = signedTime;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

  

}