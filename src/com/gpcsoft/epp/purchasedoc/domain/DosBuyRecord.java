package com.gpcsoft.epp.purchasedoc.domain;
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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @author liuke
 * @since 0.5
 * @version: 0.6
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.purchasedoc"
 * @gpcsoft.page domain="planform/purchasedoc" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="采购文件购买记录"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_DOC_BUY_RECORD")
public class DosBuyRecord extends WorkFlowModel implements GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime
{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	public static final String STATUS_PAID = "01";//支付
	public static final String STATUS_APPLY ="00";//申请
	@Id
	@Column(name = "DOC_R_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@Column(name = "FILEID", length = 36)
	private String fileId; //采购文件ID

	@Column(name = "SUPPLIER_ID", length = 36)
	private String supplierId; //供应商ID
	
	@Column(name = "SUPPLIER_NAME", length = 50)
	private String supplierName; //供应商名称
	
	@Column(name = "ATTACH_REAL_ID", length = 50)
	private String docBuyFile; //购买凭证
	
	@Column(name = "DOC_BUY_STATUS", length = 2)
	private String docBuyStatus; //供应商购买采购文件状态[00：购买申请，01：支付]
	
	@Transient
	private String docBuyStatusCN;//购买采购文件状态
	
	@Column(name = "USE_STATUS")
	private String useStatus;//使用状态
	
	@Transient
	private String useStatusCN;

	/** 创建人 */
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
	name="MODIFY_USER", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User updateUser;
    
    	//修改时间
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    
    //缴费时间
    @Column(name = "RENDERTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date renderTime;
    
	@Column(name = "AMOUNT")
	private BigDecimal amount;//缴费金额

	@Column(name = "INVOICE_FILE", length = 50)
	private String invoiceFile; //发票[存放附件ID]
	
	@Column(name = "PAY_TYPE", length = 2)
	private String payType; //缴纳方式	01:汇票,02:支票,03:电汇,04:现金,05:其他

	@Transient
	private String payTypeCN; //缴纳方式
	
	@Column(name = "REMARK", length = 2000)
	private String remark; //备注
	
	@Column(name = "TENDERID", length = 50)
	private String tenderId; //招标项目ID/包组ID
	
	@Formula("(SELECT p.TenderName FROM ECP_Tender_Project p where p.TenderID=TENDERID and TENDERID is not null)")
	private String tenderName;//招标项目名称/包组名称
	
	@Column(name = "IS_SUBPROJECT", length = 1)
	private Boolean isSubProject; //是否按包组缴纳[0:不是；1：是]
	
	@Column(name = "LINKER")
	private String linker; //联系人
	
	@Column(name = "LINKER_TEL")
	private String linkerTel; //联系方式
	
	@Column(name = "AUDIT_STATUS")
	private String auditStatus; //审核状态
	
	@Transient
	private String opinion;
	
	/********************************GET和SET方法**********************************************/
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	/**
	 * @gpcsoft.property title="购买凭证"
	 */
	public void setDocBuyFile(String docBuyFile) {
		this.docBuyFile = docBuyFile;
	}
	
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	
	/**
	 * @gpcsoft.property title="供应商Id"
	 * @gpcsoft.validate class="required"
	 */
	public String getSupplierId() {
		return supplierId;
	}

	public String getDocBuyFile() {
		return docBuyFile;
	}
	/**
	 * @gpcsoft.property title="供应商名称"
	 * @gpcsoft.validate class="required"
	 */
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * @gpcsoft.property title="购买状态"
	 * @gpcsoft.validate class="required"
	 */
	public String getDocBuyStatus() {
		return docBuyStatus;
	}

	public void setDocBuyStatus(String docBuyStatus) {
		this.docBuyStatus = docBuyStatus;
	}
	/**
	 * @gpcsoft.property title="购买状态"
	 * @gpcsoft.validate class="required"
	 */
	public String getDocBuyStatusCN() {
		this.docBuyStatusCN = DosBuyTypeEnum.getCN(this.getDocBuyStatus());
		return this.docBuyStatusCN;
	}

	public void setDocBuyStatusCN(String docBuyStatusCN) {
		this.docBuyStatusCN = docBuyStatusCN;
	}
	
	public String getObjId() {
		// TODO Auto-generated method stub
		return this.objId;
	}
	
	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
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
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getRenderTime() {
		return renderTime;
	}

	public void setRenderTime(Date renderTime) {
		this.renderTime = renderTime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getInvoiceFile() {
		return invoiceFile;
	}

	public void setInvoiceFile(String invoiceFile) {
		this.invoiceFile = invoiceFile;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	public Boolean getIsSubProject() {
		return isSubProject;
	}

	public void setIsSubProject(Boolean isSubProject) {
		this.isSubProject = isSubProject;
	}

	public String getPayTypeCN() {
		payTypeCN = DosBuyTypeEnum.getCN(this.getPayType());
		return payTypeCN;
	}

	public void setPayTypeCN(String payTypeCN) {
		this.payTypeCN = payTypeCN;
	}

	public String getTenderName() {
		return tenderName;
	}

	public void setTenderName(String tenderName) {
		this.tenderName = tenderName;
	}

	public String getLinker() {
		return linker;
	}

	public void setLinker(String linker) {
		this.linker = linker;
	}

	public String getLinkerTel() {
		return linkerTel;
	}

	public void setLinkerTel(String linkerTel) {
		this.linkerTel = linkerTel;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getUseStatusCN() {
		useStatusCN = AuditStatusEnum.getCN(this.getUseStatus());
		return useStatusCN;
	}

	public void setUseStatusCN(String useStatusCN) {
		this.useStatusCN = useStatusCN;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
}