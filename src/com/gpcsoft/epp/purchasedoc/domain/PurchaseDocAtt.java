package com.gpcsoft.epp.purchasedoc.domain;
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


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PROCFILE_ATT")
public class PurchaseDocAtt extends WorkFlowModel implements GpcBaseObject{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PROCFILE_A_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
    @ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="FileID") 
	private PurchaseDoc purchaseDoc;//对应招标文件ID  
    
    @Column(name = "TENDER_ID", length = 50)
	private String tenderId;//对应招标项目ID 	【冗余字段】
    
    @Column(name = "PACK_ID", length = 50)
	private String packId;//对应标段（包组）ID	
    
    @Column(name = "ATT_RA_ID", length = 50)
	private String attRaId;//关联附件ID 
    
    @Column(name = "FILE_TYPE", length = 2)
	private String fileType;//00:商务标；01：技术标[临时解决] 
    
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public PurchaseDoc getPurchaseDoc() {
		return purchaseDoc;
	}

	public void setPurchaseDoc(PurchaseDoc purchaseDoc) {
		this.purchaseDoc = purchaseDoc;
	}

	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	public String getPackId() {
		return packId;
	}

	public void setPackId(String packId) {
		this.packId = packId;
	}

	public String getAttRaId() {
		return attRaId;
	}

	public void setAttRaId(String attRaId) {
		this.attRaId = attRaId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
}
