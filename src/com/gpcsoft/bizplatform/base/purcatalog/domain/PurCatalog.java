package com.gpcsoft.bizplatform.base.purcatalog.domain;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.VerifyObject;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * 采购目录
 * @gpcsoft.package packageDir="com.gpcsoft.eps.base.purcatalog"
 * @gpcsoft.page domain="base/purcatalog" project="base/purcatalog"
 * @hibernate.class table="ECP_BASE_CATALOG"
 * @author Administrator
 * @version 1.0
 * @created 14-一月-2010 10:34:02
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_BASE_CATALOG")
public class PurCatalog   implements GpcBaseObject, IPropertyCUserTime ,VerifyObject{
	
	private static final long serialVersionUID = -6431848821219215831L;

	/**主键*/
	@Id
	@Column(name = "CATALOG_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
    /**目录区域中间表*/
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@Cascade({CascadeType.SAVE_UPDATE,CascadeType.DELETE_ORPHAN})
    @OneToMany
	@JoinColumn(name = "CATALOG_ID")
    private Set<PurCatalogDistrict> purCatalogDistrictSet = new HashSet<PurCatalogDistrict>();
	
    /**使用区域编码*/
    @Column(name = "AREA_CODE")
    private String areaCode;
    
    /**区域名称*/
    @Column(name = "AREA_NAME")
	private String areaName;
    
    /**年度*/
    @Column(name = "YEAR")
    private Integer year;
    
    /**标题*/
    @Column(name = "TITLE",length=100)
    private String title;
    
    /**有关说明及要求*/
    @Column(name = "CONTENT")
    private String content;
    
    /**审核状态*/
    @Column(name = "AUDITSTATUS")
    private String auditStatus;
    
    /**发布状态*/
    @Column(name = "PUBSTATUS")
    private String publishStatus;
    
    /**生效时间 */
    @Temporal(TemporalType.DATE)
    @Column(name = "RELDATE")
    private Date relDate;  
    
    /**发布时间 */
    @Temporal(TemporalType.DATE)
    @Column(name = "PUBDATE")
    private Date pubDate;    
    
    /**发布人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="RELUSER", updatable = false) 
	@BatchSize(size = 15)
    private User relUser;
	
	/**创建时间*/
    @Temporal(TemporalType.DATE)
    @Column(name = "CREDATE")
	private Date createTime;
    
    /**创建人*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREUSER", updatable = false) 
	@BatchSize(size = 15)
	private User createUser;
	
    /**审核人*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="VERIFIER_ID") 
	@BatchSize(size = 15)
	private User verifyUser;
	
    /**审核时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VERIFY_TIME")
    private Date verifyTime;
    
    /**意见*/
    @Column(name = "OPINION")
    private String opinion;
	
	/**使用状态00：临时；01：正式；02：作废*/
    @Column(name = "USESTATUS")
	private String useStatus;
    
    /**目录明细(中间表)*/
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.SAVE_UPDATE,CascadeType.DELETE_ORPHAN})
    @JoinColumn(name = "CATALOG_ID") 
    private Set<PurCatalogDetail> purCatalogDetailSet = new HashSet<PurCatalogDetail>();
	
    /**采购方式目录明细(中间表)*/
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.SAVE_UPDATE,CascadeType.DELETE_ORPHAN})
    @JoinColumn(name = "CATALOG_ID") 
    private Set<PurCatalogProcType> purCatalogProcTypeSet = new HashSet<PurCatalogProcType>();

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	public Set<PurCatalogDistrict> getPurCatalogDistrictSet() {
		return purCatalogDistrictSet;
	}

	public void setPurCatalogDistrictSet(
			Set<PurCatalogDistrict> purCatalogDistrictSet) {
		this.purCatalogDistrictSet = purCatalogDistrictSet;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuditStatusCN() {
		return OrganizationEnum.getAuditStatusCN(this.getAuditStatus());
	}
	
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	public Date getRelDate() {
		return relDate;
	}

	public void setRelDate(Date relDate) {
		this.relDate = relDate;
	}
	
	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public User getRelUser() {
		return relUser;
	}

	public void setRelUser(User relUser) {
		this.relUser = relUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public String getUseStatus() {
		return useStatus;
	}
	
	public String getPublishStatus() {
		return publishStatus;
	}

	public String getPublishStatusCN() {
		return OrganizationEnum.getPubStatusCN(this.getPublishStatus());
	}
	
	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}

	public String getUseStatusCN(){
		return OrganizationEnum.getUseStatusCN(this.getUseStatus());
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public Set<PurCatalogDetail> getPurCatalogDetailSet() {
		return purCatalogDetailSet;
	}

	public void setPurCatalogDetailSet(Set<PurCatalogDetail> purCatalogDetailSet) {
		this.purCatalogDetailSet = purCatalogDetailSet;
	}

	public Set<PurCatalogProcType> getPurCatalogProcTypeSet() {
		return purCatalogProcTypeSet;
	}

	public void setPurCatalogProcTypeSet(Set<PurCatalogProcType> purCatalogProcTypeSet) {
		this.purCatalogProcTypeSet = purCatalogProcTypeSet;
	}

	public User getVerifyUser() {
		return verifyUser;
	}

	public void setVerifyUser(User verifyUser) {
		this.verifyUser = verifyUser;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
}