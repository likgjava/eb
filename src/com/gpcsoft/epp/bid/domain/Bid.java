package com.gpcsoft.epp.bid.domain;

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
 * @gpcsoft.package packageDir="com.gpcsoft.epp.bid"
 * @gpcsoft.page domain="planform/bid" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="供应商投标"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_BID")
public class Bid extends WorkFlowModel implements GpcBaseObject, IPropertyCUserTime, IPropertyUUserTime{
	@Id
	@Column(name = "BID_ID", nullable=false, length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	//创建人
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="SUPPLIER_ID")//关联的外键	 
	@BatchSize(size = 15)
	private OrgInfo supplier; //供应商ID
	
	@Column(name = "BID_NO")
	private String bidNo; //投标识别码
	
	@Column(name = "SUPPLIER_NAME", length = 50)
	private String supplierName; //供应商名称
	
	@Column(name="BID_FILE",length=36)
	private String attachRelaId; //投标文件
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="PROJ_ID") 		
	private Project project; //采购项目
	
	@Column(name = "PROJ_NAME", length = 500)
	private String projName; //采购项目名称
	
	@Column(name = "PROJ_CODE", length = 100)
	private String projCode; //采购项目编号
	
	@Column(name = "BID_QUOTESUM")
	private BigDecimal bidQuoteSum; //报价总金额
	
	@Column(name = "BID_BRAND", length = 50)
	private String bidBrand; //投标品牌
	
	@Column(name = "BID_STATEMENT", length = 500)
	private String bidStatement; //投标声明
	
	@Column(name = "BID_LINKER", length = 50)
	private String bidLinker; //联系人
	
	@Column(name = "BID_LINKER_IDCARD", length = 50)
	private String bidLinkerIdCard; //联系人身份证
	
	@Column(name = "BID_REMARK", length = 200)
	private String bidRemark; //备注
	
	@Column(name = "BID_SUBMIT_TYPE", length = 50)
	private String bidSubmitType; //投标方式（注：网上投标、网下投标&lt;代理机构录入报名情况;）
	
	
	@Column(name = "USE_STATUS" ,length = 2)
	private String useStatus;//使用状态
	
	@Column(name = "PROJ_MANAGER_NAMES", length = 50)
	private String projManagerName; //项目经理名称
	
	@Column(name = "PROJ_MANAGER_IDS", length = 50)
	private String projManagerId; //项目经理ID
	
	@Transient
	private String isOpenBid;//项目是否开标
	
	
	
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
    
    /*@LazyCollection(value=LazyCollectionOption.FALSE)
    @OneToMany
    @JoinColumn(name = "BID_ID") 
    @Cascade({CascadeType.ALL})
    private Set<BidPackage> bidPackageSet = new HashSet<BidPackage>();
	*/
	
    //modify by liuke
	/**当前有效id*/
	@Column(name = "CURRENT_VALID_ID")
	private String currentId;
    
	/********************************GET和SET方法**********************************************/
	public String getCurrentId() {
		return currentId;
	}
	public String getBidNo() {
		return bidNo;
	}
	public void setBidNo(String bidNo) {
		this.bidNo = bidNo;
	}
	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}
	
	public String getIsOpenBid() {
		return isOpenBid;
	}
	public void setIsOpenBid(String isOpenBid) {
		this.isOpenBid = isOpenBid;
	}
    
    /**
	 * @gpcsoft.property title="供应商ID"
	 */
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
	 * @gpcsoft.property title="项目名称"
	 */
	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}
	 /**
	 * @gpcsoft.property title="项目编号"
	 */
	public String getProjCode() {
		return projCode;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}
	 /**
	 * @gpcsoft.property title="报价总金额(元)"
	 * @gpcsoft.validate class="number"
	 */
	public BigDecimal getBidQuoteSum() {
		return bidQuoteSum;
	}

	public void setBidQuoteSum(BigDecimal bidQuoteSum) {
		this.bidQuoteSum = bidQuoteSum;
	}
	/**
	 * @gpcsoft.property title="投标品牌"
	 */
	public String getBidBrand() {
		return bidBrand;
	}

	public void setBidBrand(String bidBrand) {
		this.bidBrand = bidBrand;
	}
	/**
	 * @gpcsoft.property title="投标声明"
	 */
	public String getBidStatement() {
		return bidStatement;
	}

	public void setBidStatement(String bidStatement) {
		this.bidStatement = bidStatement;
	}
	/**
	 * @gpcsoft.property title="联系人"
	 */
	public String getBidLinker() {
		return bidLinker;
	}

	public void setBidLinker(String bidLinker) {
		this.bidLinker = bidLinker;
	}
	/**
	 * @gpcsoft.property title="联系人身份证"
	 */
	public String getBidLinkerIdCard() {
		return bidLinkerIdCard;
	}

	public void setBidLinkerIdCard(String bidLinkerIdCard) {
		this.bidLinkerIdCard = bidLinkerIdCard;
	}
	/**
	 * @gpcsoft.property title="备注"
	 */
	public String getBidRemark() {
		return bidRemark;
	}

	public void setBidRemark(String bidRemark) {
		this.bidRemark = bidRemark;
	}
	/**
	 * @gpcsoft.property title="投标方式"
	 */
	public String getBidSubmitType() {
		return bidSubmitType;
	}

	public void setBidSubmitType(String bidSubmitType) {
		this.bidSubmitType = bidSubmitType;
	}
	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
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
	/*public Set<BidPackage> getBidPackageSet() {
		return bidPackageSet;
	}
	public void setBidPackageSet(Set<BidPackage> bidPackageSet) {
		this.bidPackageSet = bidPackageSet;
	}*/
	
	public Date getUpdateTime() {
		return this.modifyTime;
	}
	
	/**
	 * @gpcsoft.property title="投标文件"
	 */
	public String getAttachRelaId() {
		return attachRelaId;
	}
	public void setAttachRelaId(String attachRelaId) {
		this.attachRelaId = attachRelaId;
	}
	public void setUpdateTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	
	public String getProjManagerName() {
		return projManagerName;
	}
	public void setProjManagerName(String projManagerName) {
		this.projManagerName = projManagerName;
	}
	public String getProjManagerId() {
		return projManagerId;
	}
	public void setProjManagerId(String projManagerId) {
		this.projManagerId = projManagerId;
	}
}
