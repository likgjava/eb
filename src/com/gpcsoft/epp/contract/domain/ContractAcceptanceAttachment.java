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
import com.gpcsoft.srplatform.auth.domain.Attachment;


/**
 * 合同验收信息所包含的附件
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
@Table(name = "CONTRACT_ACCEPTANCE_ATTACHMENT")
public class ContractAcceptanceAttachment extends WorkFlowModel implements GpcBaseObject {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CONTRACT_ACCP_ATTA_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "CONTRACT_ACCEPTANCE_ID")
	@BatchSize(size = 15)
	private ContractAcceptance contractAcceptance;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ATTACHMENT_ID")
	@BatchSize(size = 15)
	private Attachment attachment;
	
	@Column(name = "CONTRACT_ACCP_ATTA_STATUS")
	private String attaStatus;
	
	@Column(name = "CONTRACT_ACCP_ATTA_TYPE")
	private String attaType;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public ContractAcceptance getContractAcceptance() {
		return contractAcceptance;
	}

	public void setContractAcceptance(ContractAcceptance contractAcceptance) {
		this.contractAcceptance = contractAcceptance;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public String getAttaStatus() {
		return attaStatus;
	}

	public void setAttaStatus(String attaStatus) {
		this.attaStatus = attaStatus;
	}

	public String getAttaType() {
		return attaType;
	}

	public void setAttaType(String attaType) {
		this.attaType = attaType;
	}
	
	
	
}
