package com.gpcsoft.epp.negotationrecord.domain;

import java.math.BigDecimal;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;
/**
 * @author liuke
 * @since 0.5
 * @version: 0.6
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.negotationrecord"
 * @gpcsoft.page domain="planform/negotationrecord" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="谈判记录"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_NEGOTIATION_RECORD")
public class NegotationRecord extends WorkFlowModel implements GpcBaseObject, IPropertyCUserTime, IPropertyUUserTime{
	@Id
	@Column(name = "NEG_RECORD_ID", nullable=false, length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="SUB_TENDERID") 		
	private Project subProject; //招标项目/包组ID
	
	@Column(name = "TENDERID", length = 50)
	private String tenderId; //招标项目ID[冗余]
	
	@Column(name = "NEG_R_ID", length = 50)
	private String negRId; //谈判轮次ID
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="SUPLIER_ID")//关联的外键	 
	@BatchSize(size = 15)
	private OrgInfo supplier; //供应商ID
	
	@Column(name = "SUPLIER_NAME", length = 50)
	private String supplierName; //供应商名称
	
	@Column(name = "NEG_RECORD_TOTAL")
	private BigDecimal recordTotal; //报价总金额
	
	@Column(name = "NEG_RECORD_MEMO", length = 4000)
	private String recordMemo; //报价备注
	
	@Column(name="NEG_RECORD_FILE",length=36)
	private String negRecordFile; //报价文件
	
    @Column(name = "NEG_RECORD_TIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordTime;   //报价时间
	
	//创建人
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATE_USER", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User createUser;

	//创建时间
    @Column(name = "CREATE_TIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

  //修改人
  	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(
	name="MODIFY_USER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
   	private User updateUser;
    
    //修改时间
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    
	/********************************GET和SET方法**********************************************/
    public Project getSubProject() {
		return subProject;
	}
	public void setSubProject(Project subProject) {
		this.subProject = subProject;
	}
	public String getTenderId() {
		return tenderId;
	}
	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}
	public String getNegRId() {
		return negRId;
	}
	public void setNegRId(String negRId) {
		this.negRId = negRId;
	}
	 /**
	 * @gpcsoft.property title="报价总金额"
	 */
	public BigDecimal getRecordTotal() {
		return recordTotal;
	}
	public void setRecordTotal(BigDecimal recordTotal) {
		this.recordTotal = recordTotal;
	}
	public String getRecordMemo() {
		return recordMemo;
	}
	public void setRecordMemo(String recordMemo) {
		this.recordMemo = recordMemo;
	}
	public String getNegRecordFile() {
		return negRecordFile;
	}
	public void setNegRecordFile(String negRecordFile) {
		this.negRecordFile = negRecordFile;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
    
   
    public OrgInfo getSupplier() {
		return supplier;
	}
	public void setSupplier(OrgInfo supplier) {
		this.supplier = supplier;
	}
	 /**
	 * @gpcsoft.property title="供应商名称"
	 */
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	/**
	 * @gpcsoft.property title="创建时间"
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getObjId() {
		return this.objId;
	}
    
	public User getCreateUser() {
		return createUser;
	}
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	public User getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}
	
	public Date getUpdateTime() {
		return this.modifyTime;
	}
	
	public void setUpdateTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
}
