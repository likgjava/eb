package com.gpcsoft.epp.purchasedoc.domain;
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

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.project.domain.Project;
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
 * @gpcsoft.title value="采购文件"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_Tender_ProcFile")
public class PurchaseDoc extends WorkFlowModel implements GpcBaseObject,IPropertyCUserTime{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FileID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@Column(name = "FileNo", length = 50)
	private String fileNo;//编号
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="TenderID") 		
	private Project project; //采购项目
	
	@Column(name = "FileType", length = 2)
	private String fileType; //文件类型07：采购文件；08：澄清文件；09：询价文件
	
	@Column(name = "KeyWord", length = 100)
	private String keyWord; //关键字
	
	@Column(name = "Content", length = 1000)
	private String content; //摘要
	
	@Column(name = "FILEPRICE")
	private String filePrice;//文件价格(元)
	
	@Column(name = "BID_FILE", length = 50)
	private String attachRelaId; //关联附件
	
	@Column(name = "AuditStatus", length = 2)
	private String auditStatus;// 待采购人确认00,采购人确认通过01,监察局审核通过02,采购办审核通过03,被退回04
	
	@Transient
	private String auditStatusCN;//审核状态
	
	@Transient                  
	private String confirmStatus; //确认状态
	
	@Column(name = "RelStatus", length = 2)
	private String relStatus;// 发布状态 00：未发布；01：已发布；02：撤回
	
	@Column(name = "RelDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date relDate;// 发布时间  发布状态改为“已发布”时取Sysdate
	
	/** 发布人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="RelUser", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User relUser;
	
	@Transient
	private String opinion;//审核意见
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CreUser", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User createUser;

    //创建时间
    @Column(name = "CreDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Column(name = "UseStatus", length = 2)
	private String useStatus;//使用状态

	@Column(name = "PROOF_ID", length = 50)
	private String proofId;//论证小组ID
    
    @Column(name = "PROOF_OPINION", length = 4000)
	private String proofOpinion;//论证结论
    
    @Column(name = "PUR_FILEID", length = 50)
	private String purFileId;//澄清文件所关联的招标文件Id
    
    @Column(name = "MEMEO", length = 4000)
	private String memeo;//澄清文件内容
    
    @Column(name = "BID_BUSINESS_FILE", length = 50)
    private String bidBusinessFile;//商务标文件关联Id
    
    @Column(name = "BID_TECHNICAL_FILE", length = 50)
    private String bidTechnicalFile;//技术标文件关联Id
    

	//modify by liuke
    /**当前有效id*/
	@Column(name = "CURRENT_VALID_ID")
	private String currentId;
    
	@Transient
	private Bulletin bulletin;//审核意见
	/********************************GET和SET方法**********************************************/
	
	public Bulletin getBulletin() {
		return bulletin;
	}

	public void setBulletin(Bulletin bulletin) {
		this.bulletin = bulletin;
	}
	public String getCurrentId() {
		return currentId;
	}

	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}
	
	public String getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
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
	
    public String getProofId() {
		return proofId;
	}

	public void setProofId(String proofId) {
		this.proofId = proofId;
	}
   
	/**
	 * @gpcsoft.property title="论证结论"
	 */
	public String getProofOpinion() {
		return proofOpinion;
	}

	public void setProofOpinion(String proofOpinion) {
		this.proofOpinion = proofOpinion;
	}
	
	/**
	 * @gpcsoft.property title="编号"
	 */
	public String getFileNo() {
		return fileNo;
	}
	/**
	 * @gpcsoft.property title="文件价格(元)"
	 */
	public String getFilePrice() {
		return filePrice;
	}

	public void setFilePrice(String filePrice) {
		this.filePrice = filePrice;
	}
	
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	/**
	 * @gpcsoft.property title="项目"
	 */
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	/**
	 * @gpcsoft.property title="文件类型"
	 */
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * @gpcsoft.property title="关键字"
	 */
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	/**
	 * @gpcsoft.property title="摘要"
	 */
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @gpcsoft.property title="采购文件"
	 */
	public String getAttachRelaId() {
		return attachRelaId;
	}

	public void setAttachRelaId(String attachRelaId) {
		this.attachRelaId = attachRelaId;
	}

	/**
	 * @gpcsoft.property title="审核状态"
	 */
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * @gpcsoft.property title="发送状态"
	 */
	public String getRelStatus() {
		return relStatus;
	}

	public void setRelStatus(String relStatus) {
		this.relStatus = relStatus;
	}

	/**
	 * @gpcsoft.property title="发送时间"
	 */
	public Date getRelDate() {
		return relDate;
	}

	public void setRelDate(Date relDate) {
		this.relDate = relDate;
	}

	/**
	 * @gpcsoft.property title="发送人"
	 */
	public User getRelUser() {
		return relUser;
	}

	public void setRelUser(User relUser) {
		this.relUser = relUser;
	}

	/**
	 * @gpcsoft.property title="创建人"
	 */
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
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

	/**
	 * @gpcsoft.property title="使用状态"
	 */
	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	
	/**
	 * @gpcsoft.property title="审核状态"
	 */
	public String getAuditStatusCN() {
		this.auditStatusCN = PurchaseDocEnum.getCN(this.getAuditStatus());
		return this.auditStatusCN;
	}

	public void setAuditStatusCN(String auditStatusCN) {
		this.auditStatusCN = auditStatusCN;
	}
	
	public String getPurFileId() {
		return purFileId;
	}

	public void setPurFileId(String purFileId) {
		this.purFileId = purFileId;
	}
	/**
	 * @gpcsoft.property title="文件内容"
	 */
	public String getMemeo() {
		return memeo;
	}

	public void setMemeo(String memeo) {
		this.memeo = memeo;
	}
    public String getBidBusinessFile() {
		return bidBusinessFile;
	}

	public void setBidBusinessFile(String bidBusinessFile) {
		this.bidBusinessFile = bidBusinessFile;
	}

	public String getBidTechnicalFile() {
		return bidTechnicalFile;
	}

	public void setBidTechnicalFile(String bidTechnicalFile) {
		this.bidTechnicalFile = bidTechnicalFile;
	}
}
