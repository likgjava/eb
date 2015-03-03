package com.gpcsoft.epp.signuprecord.domain;

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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.epp.bid.domain.BailStatusCNEnum;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 *  Comments: <strong>SignUprecord</strong>供应商报名记录表<br/>            		
 *	<br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 权限平台     	
 *  <br/>Create Date：2010-4-14 上午02:59:07 by yemx    					                            
 *  <br/>Modified Date:  2010-4-14 上午02:59:07 by yemx                                 
 *  @gpcsoft.package packageDir="com.gpcsoft.epp.signuprecord"
 *  @gpcsoft.page domain="signuprecord" project="es/planform"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="供应商报名记录"    
 *  @since 0.4
 *  @version: 0.5
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_Tender_Apply_Rec")
@SuppressWarnings("serial")
public class SignUprecord extends WorkFlowModel implements GpcBaseObject,IPropertyCUserTime {

	@Id
	@Column(name = "ApplyID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;  //供应商报名记录ID
	
	@ManyToOne(fetch=FetchType.EAGER)
	@BatchSize(size = 20)
	@JoinColumn(name="TenderID")
	private Project project; //招标项目
	
	@ManyToOne(fetch=FetchType.EAGER)
	@BatchSize(size = 20)
	@JoinColumn(name="SupplyerID")
	private OrgInfo supplier; //投标人(供应商名称,代理机构名称)
	
	@Column(name = "supplyerName")
	private String supplierName; //投标人名称(供应商名称,代现机构名称)	

	@Column(name = "ApplyDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applyDate;//报名时间
    
	@Column(name = "Linker", length = 50)
	private String linker; //联系人

    @Column(name = "IDCard", length = 20)
	private String idCard; // 身份证号

    @Column(name = "Linker_Tel", length = 20)
	private String linkerTel; // 联系电话
    
    @Column(name = "Address", length = 100)
	private String address; // 联系地址
    
    @Column(name = "ZipCode", length = 10)
	private String zipCode; // 邮编
    
    @Column(name = "AuditStatus", length = 2)
	private String auditStatus; // 审核状态
  
	@Transient
    private String auditStatusCN; // 审核状态
	
	@Column(name = "DOCPRICE_PAYSTATUS")
	private String docpricePayStatus;//招标文件支付状态[1：已支付,0：未支付]
    
    @ManyToOne(fetch=FetchType.LAZY)
    @BatchSize(size = 20)
    @JoinColumn(name="AuditUser")
	private User auditUser; // 审核人
    
    @Column(name = "AuditDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auditDate;//审核时间
    
    @Column(name = "ApplyStatus", length = 2)
	private String applyStatus; // 报名状态
   
    @Column(name = "UnApplyReason", length = 100)
	private String unApplyReason; // 撤销原因
    
    @ManyToOne(fetch=FetchType.LAZY)
    @BatchSize(size = 20)
    @JoinColumn(name="UnApplyUser")
	private User unApplyUser; // 撤销人

    @Column(name = "UnApplyDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date unApplyDate;//撤销时间

    @Column(name = "Memo", length = 100)
	private String memo; // 备注

    @Transient
	private String opinion;//审核意见
	
	@Column(name = "ATTACH_REAL_ID")
	private String attachRelaId; //关联附件
	
	@Transient
	private String isBid;//是否投标
	
	//modify by liuke
	/**当前有效id*/
	@Column(name = "CURRENT_VALID_ID")
	private String currentId;
	
	@Transient
	private String bailStatus;//保证金缴纳状态
	
	@Transient
	private String bailStatusCN;//保证金缴纳状态中文显示名称
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CreUser", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User createUser;

    //创建时间
    @Column(name = "CreDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    /**扩展字段1*/
	@Column(name = "REDUNDANCY1")
	private String REDUNDANCY1;//冗余字段1
	
    public String getREDUNDANCY1() {
		return REDUNDANCY1;
	}

	public void setREDUNDANCY1(String redundancy1) {
		REDUNDANCY1 = redundancy1;
	}

	public String getBailStatusCN() {
    	bailStatusCN = BailStatusCNEnum.getCN(this.getBailStatus());
		return bailStatusCN;
	}

	public void setBailStatusCN(String bailStatusCN) {
		this.bailStatusCN = bailStatusCN;
	}

	public String getBailStatus() {
		return bailStatus;
	}

	public void setBailStatus(String bailStatus) {
		this.bailStatus = bailStatus;
	}

	public String getCurrentId() {
		return currentId;
	}

	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}

	public String getAttachRelaId() {
		return attachRelaId;
	}

	public void setAttachRelaId(String attachRelaId) {
		this.attachRelaId = attachRelaId;
	}


	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
    
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public OrgInfo getSupplier() {
		return supplier;
	}

	public void setSupplier(OrgInfo supplier) {
		this.supplier = supplier;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getLinker() {
		return linker;
	}

	public void setLinker(String linker) {
		this.linker = linker;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getLinkerTel() {
		return linkerTel;
	}

	public void setLinkerTel(String linkerTel) {
		this.linkerTel = linkerTel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public User getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(User auditUser) {
		this.auditUser = auditUser;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getUnApplyReason() {
		return unApplyReason;
	}

	public void setUnApplyReason(String unApplyReason) {
		this.unApplyReason = unApplyReason;
	}

	public User getUnApplyUser() {
		return unApplyUser;
	}

	public void setUnApplyUser(User unApplyUser) {
		this.unApplyUser = unApplyUser;
	}

	public Date getUnApplyDate() {
		return unApplyDate;
	}

	public void setUnApplyDate(Date unApplyDate) {
		this.unApplyDate = unApplyDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

    public String getAuditStatusCN() {
    	auditStatusCN = AuditStatusEnum.getCN(this.getAuditStatus());
		return auditStatusCN;
	}

	public void setAuditStatusCN(String auditStatusCN) {
		this.auditStatusCN = auditStatusCN;
	}

	public String getDocpricePayStatus() {
		return docpricePayStatus;
	}

	public void setDocpricePayStatus(String docpricePayStatus) {
		this.docpricePayStatus = docpricePayStatus;
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

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		this.applyDate = createTime;
	}

	public String getIsBid() {
		return isBid;
	}

	public void setIsBid(String isBid) {
		this.isBid = isBid;
	}
	
	@Override
	public boolean equals(Object obj) {
		SignUprecord signUprecord2 = (SignUprecord) obj;
		if(this.getSupplier().getObjId().equals(signUprecord2.getSupplier().getObjId())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getSupplier().getObjId().hashCode();
	}
	
}
