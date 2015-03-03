package com.gpcsoft.bizplatform.organization.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;

import com.gpcsoft.bizplatform.base.industry.domain.Industry;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.core.model.VerifyObject;
import com.gpcsoft.pubservice.application.service.domain.MemberClassEnum;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * 机构信息
 * @gpcsoft.package packageDir="com.gpcsoft.eps.organization"
 * @gpcsoft.page domain="organization"
 * @hibernate.class table="ORG_INFO"
 * @author Administrator
 * @version 1.0
 * @created 08-三月-2010 10:41:18
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ORG_INFO")
@OrderProperty(property="createTime", flag="true")
public class OrgInfo implements GpcBaseObject,IPropertyCUserTime,IPropertyUUserTime,VerifyObject {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name = "ORG_INFO_ID",length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/**
	 * 机构编码
	 */
	@Column(name = "ORG_CODE", length = 100)
	private String orgCode;
	
	/**
	 * 机构名称
	 */
	@Column(name = "ORG_NAME", length = 100)
	private String orgName;
	

	/*
	 * 机构唯一标识
	 */
	@Column(name = "UNIQUE_CODE", length = 100)
	private String uniqueCode;
	
	
	/**
	 * 当前有效id
	 */
	@Column(name = "CURRENT_VALID_ID", length = 50)
	private String currentId;
	
	/**
	 * 联系人(机构管理员）
	 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.ALL})
    @JoinColumn(name = "USER_ID")
	private User user;
	
	/**
	 * 行政区域
	 */
	@Column(name = "DISTRICT_VALUE", length = 200)
	private String districtValue;
	
	/**
	 * 行政区域名称
	 */
	@Transient
	private String distinctName;
	
	/**
	 * 临时字段（是否变更）
	 */
	@Transient
	private String isModify;
	
	/**
	 * districtId(处理town的提交)
	 */
	@Transient
	private String districtId;
	
	/**
	 * 机构地址，为了区分employee的地址
	 */
	@Transient
	private String orgAddress;
	
	/**
	 * 供应商扩展信息状态
	 */
	@Formula("(Select t.audit_status From SPL_SUPPLIER t Where t.supplier_id = supplier_id)")
	private String supplierAuditStatus;
	
	/**
	 * 采购人扩展信息状态
	 */
	@Formula("(Select t.audit_status From BUY_BUYER t Where t.buyer_id = buyer_id)")
	private String buyerAuditStatus;
	
	/**
	 * 代理机构扩展信息状态
	 */
	@Formula("(Select t.audit_status From AGENCY_AGENT t Where t.agent_id = agency_id)")
	private String agencyAuditStatus;
	
	/** ******************扩展机构信息 *************************/
	
	/**
	 * 机构基本信息
	 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.ALL})
    @JoinColumn(name = "COMPANY_ID")
	private Company company ;
	
	/**
	 * 代理机构id
	 */
	@Column(name = "AGENCY_ID")
	private String agencyId;
	
	/**
	 * 采购人id
	 */
	@Column(name = "BUYER_ID")
	private String buyerId;
	
	/**
	 * 供应商id
	 */
	@Column(name = "SUPPLIER_ID")
	private String supplierId;
	
	/**
	 * 监管机构id
	 */
	@Column(name = "SUPERVISION_ID")
	private String supervisionId;
	
	/**
	 * 主管部门id
	 */
	@Column(name = "CMPT_DEP_ID")
	private String cmptDepId;
	
	
	/** ******************状态信息 *************************/
	
	/** 使用状态[00:临时;01:有效;02:无效] */
	@Column(name = "USE_STATUS", length = 2)
	private String useStatus;
	
	@Transient
	private String useStatusCN;
	
	/** 审核状态[00:草稿;01:待审;02:通过,03:不通过] */
	@Column(name = "AUDIT_STATUS", length = 2)
	private String auditStatus;
	
	@Transient
	private String auditStatusCN;
	
	/** 审核类型[00:注册审核,01:变更审核,02:申请审核] */
	@Column(name = "AUDIT_TYPE", length = 2)
	private String auditType;
	
	@Transient
	private String auditTypeCN;
	
	/** 生效时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VALID_DATE", length = 7)
	private Date validTime;
	
	/** 失效时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INVALID_DATE", length = 7)
	private Date invalidTime;
	
	@Column(name = "IS_OFF", length = 50)
	private String isOff; /*禁用启用，2.禁用 1.启用。默认启用*/
	    
	@Transient
	private String isOffCN;/*禁用启用*/
	
	@Transient
	private String orgType;//机构类别
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATOR")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User createUser;
	
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 7)
	private Date createTime;
	
	/** 修改人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="MODIFIER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User updateUser;
	
	/** 修改时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_TIME", length = 7)
	private Date updateTime;
	
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
	
	/** 机构logo */
	@Column(name="ORG_LOGO")	 
	private String logo;
	
	/**机构资质*/
	@LazyCollection(value=LazyCollectionOption.EXTRA)
    @OneToMany
	@JoinColumn(name = "BELONG_OBJECT_ID")
	@Where(clause="USE_STATUS='01'")
	@OrderBy("qualificationClass asc")
    private Set<OrgQuality> qualitys = new HashSet<OrgQuality>();
	
	/**成功案例*/
	@LazyCollection(value=LazyCollectionOption.EXTRA)
    @OneToMany
	@JoinColumn(name = "ORGINFO_ID")
	@Where(clause="USE_STATUS='01'")
	private Set<SuccessCase> successCases = new HashSet<SuccessCase>();
	
	/**评价*/
	@LazyCollection(value=LazyCollectionOption.EXTRA)
    @OneToMany
	@JoinColumn(name = "ORGINFO_ID")
	private Set<Evaluate> evaluates = new HashSet<Evaluate>();
	
	
	/************************新注册功能，字段加入orgInfo中*************************/
	/**
	 * 所属行业-关联字典表
	 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="BELONGINDUSTRY")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Industry belongIndustry;

	/**
	 * 企业性质[大中型国营:01,集体:02,民营:03,福利:04,有限责任:05,合资:06,合作:07,独资:08,股份公司:09,上市公司:10,国有控股:11,团体组织:12]
	 */
	@Column(name = "ENT_PRPT",length=100)
	private String entPrpt;
	@Transient
	private String entPrptCN;
	
	/**
	 * 开业日期
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "BEGAINDATE")
	private Date begainDate;
	
	/**
	 * 企业规模
	 */
	@Column(name = "UNIT_SCAPE",length=100)
	private String unitScape;
	@Transient
	private String unitScapeCN;
	
	/**
	 * 企业网址
	 */
	@Column(name = "WEB_URL",length=100)
	private String webUrl;
	
	/**
	 * 企业简介
	 */
	@Column(name = "DESC_CN",length=1000)
	private String descCn;
	
	/**
	 * 主营产品
	 */
	@Column(name = "MAIN_PRODUCTS",length=1000)
	private String mainProducts;
	
	/**
	 * 经营范围
	 */
	@Column(name = "BID_FOR_RANGE", length=2000)
	private String bidForRange;
	
	@Transient
	private String bidForRangeName;
	
	@Transient
	private String bidForRangeCode;
	
	/**
	 * 是否厂家
	 */
	@Column(name = "ISMANUFACTURER",length=100)
	private String isManufacturer;

	/**
	 * 企业产能
	 */
	@Column(name = "ENTCAPACITY", length=2000)
	private String entCapacity;


	/**
	 * 是否会员
	 */
	@Column(name = "MEMBER_CLASS_ID")
	private String memberClassId;      //会员级别 00：非会员 01：会员
	
	@Transient
	private String memberClassIdCN;
	/************************新注册功能，字段加入orgInfo中**************************/
	public String getObjId() {
		return objId;
	}

	
	
	public String getMemberClassIdCN() {
		this.memberClassIdCN = MemberClassEnum.getMemberClassCN(this.memberClassId);
		return memberClassIdCN;
	}


	public void setMemberClassIdCN(String memberClassIdCN) {
		this.memberClassIdCN = memberClassIdCN;
	}

	public String getSupplierAuditStatus() {
		return supplierAuditStatus;
	}


	public void setSupplierAuditStatus(String supplierAuditStatus) {
		this.supplierAuditStatus = supplierAuditStatus;
	}


	public String getBuyerAuditStatus() {
		return buyerAuditStatus;
	}

	public String getMemberClassId() {
		return memberClassId;
	}


	public void setMemberClassId(String memberClassId) {
		this.memberClassId = memberClassId;
	}
	public void setBuyerAuditStatus(String buyerAuditStatus) {
		this.buyerAuditStatus = buyerAuditStatus;
	}


	public String getAgencyAuditStatus() {
		return agencyAuditStatus;
	}


	public void setAgencyAuditStatus(String agencyAuditStatus) {
		this.agencyAuditStatus = agencyAuditStatus;
	}


	public void setObjId(String objId) {
		this.objId = objId;
	}


	public String getCurrentId() {
		return currentId;
	}


	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}


	public String getOrgCode() {
		return orgCode;
	}

	public String getDistrictValue() {
		return districtValue;
	}


	public void setDistrictValue(String districtValue) {
		this.districtValue = districtValue;
	}


	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getOrgAddress() {
		return orgAddress;
	}


	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public String getAgencyId() {
		return agencyId;
	}


	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}


	public String getBuyerId() {
		return buyerId;
	}


	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}


	public String getSupplierId() {
		return supplierId;
	}


	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}


	public String getSupervisionId() {
		return supervisionId;
	}


	public void setSupervisionId(String supervisionId) {
		this.supervisionId = supervisionId;
	}


	public String getCmptDepId() {
		return cmptDepId;
	}


	public void setCmptDepId(String cmptDepId) {
		this.cmptDepId = cmptDepId;
	}


	public String getUseStatus() {
		return useStatus;
	}


	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}


	public String getUseStatusCN() {
		this.useStatusCN = OrganizationEnum.getUseStatusCN(this.useStatus);
		return useStatusCN;
	}


	public void setUseStatusCN(String useStatusCN) {
		this.useStatusCN = useStatusCN;
	}


	public String getAuditStatus() {
		return auditStatus;
	}


	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	
	public String getAuditType() {
		return auditType;
	}


	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}


	public String getAuditTypeCN() {
		this.auditTypeCN = OrganizationEnum.getAuditTypeCN(this.auditType);
		return this.auditTypeCN;
	}


	public void setAuditTypeCN(String auditTypeCN) {
		this.auditTypeCN = auditTypeCN;
	}

	public String getAuditStatusCN() {
		this.auditStatusCN = OrganizationEnum.getAuditStatusCN(this.auditStatus);
		return this.auditStatusCN;
	}

	public void setAuditStatusCN(String auditStatusCN) {
		this.auditStatusCN = auditStatusCN;
	}


	public Date getValidTime() {
		return validTime;
	}


	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}


	public Date getInvalidTime() {
		return invalidTime;
	}


	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}


	public String getIsOff() {
		return isOff;
	}


	public void setIsOff(String isOff) {
		this.isOff = isOff;
	}


	public String getIsOffCN() {
		this.isOffCN = OrganizationEnum.getIsOffCN(this.isOff);
		return isOffCN;
	}


	public void setIsOffCN(String isOffCN) {
		this.isOffCN = isOffCN;
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


	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
	public String getIsModify() {
		return isModify;
	}


	public void setIsModify(String isModify) {
		this.isModify = isModify;
	}

	public String getDistinctName() {
		if(this.districtValue != null){
			String temp = this.districtValue.replace("##||##", "&");
			String args[] = temp.split("&");
			if(args != null && args.length > 1){
				this.distinctName = args[1]==null?"":args[1];
			}else{
				this.distinctName = "";
			}
		}
		return distinctName;
	}

	public void setDistinctName(String distinctName) {
		this.distinctName = distinctName;
	}
	
	public String getBidForRangeCode() {
		if(this.bidForRange != null){
			String temp = this.bidForRange.replace("##||##", "&");
			String args[] = temp.split("&");
			if(args != null && args.length > 1){
				this.bidForRangeCode = args[0]==null?"":args[0];
			}else{
				this.bidForRangeCode = "";
			}
		}
		return bidForRangeCode;
	}
	public void setBidForRangeCode(String bidForRangeCode) {
		this.bidForRangeCode = bidForRangeCode;
	}
	
	public String getBidForRangeName() {
		if(this.bidForRange != null){
			String temp = this.bidForRange.replace("##||##", "&");
			String args[] = temp.split("&");
			if(args != null && args.length > 1){
				this.bidForRangeName = args[1]==null?"":args[1];
			}else{
				this.bidForRangeName = "";
			}
		}
		return bidForRangeName;
	}
	public void setBidForRangeName(String bidForRangeName) {
		this.bidForRangeName = bidForRangeName;
	}

	public String getOrgType() {
		return this.orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}


	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}


	
	public String getUniqueCode() {
		return uniqueCode;
	}



	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	
	
	public Set<OrgQuality> getQualitys() {
		return qualitys;
	}


	public void setQualitys(Set<OrgQuality> qualitys) {
		this.qualitys = qualitys;
	}


	public Set<SuccessCase> getSuccessCases() {
		return successCases;
	}


	public void setSuccessCases(Set<SuccessCase> successCases) {
		this.successCases = successCases;
	}


	public Set<Evaluate> getEvaluates() {
		return evaluates;
	}


	public void setEvaluates(Set<Evaluate> evaluates) {
		this.evaluates = evaluates;
	}


	public Industry getBelongIndustry() {
		return belongIndustry;
	}


	public void setBelongIndustry(Industry belongIndustry) {
		this.belongIndustry = belongIndustry;
	}


	public String getEntPrpt() {
		return entPrpt;
	}


	public void setEntPrpt(String entPrpt) {
		this.entPrpt = entPrpt;
	}


	public String getEntPrptCN() {
		this.entPrptCN = OrganizationEnum.getEXEntPrptCN(this.entPrpt);
		return entPrptCN;
	}


	public void setEntPrptCN(String entPrptCN) {
		this.entPrptCN = entPrptCN;
	}


	public Date getBegainDate() {
		return begainDate;
	}


	public void setBegainDate(Date begainDate) {
		this.begainDate = begainDate;
	}


	public String getUnitScape() {
		return unitScape;
	}


	public void setUnitScape(String unitScape) {
		this.unitScape = unitScape;
	}


	public String getUnitScapeCN() {
		this.unitScapeCN = OrganizationEnum.getEXUnitScapeCN(this.unitScape);
		return unitScapeCN;
	}


	public void setUnitScapeCN(String unitScapeCN) {
		
		this.unitScapeCN = unitScapeCN;
	}


	public String getWebUrl() {
		return webUrl;
	}


	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}


	public String getDescCn() {
		return descCn;
	}


	public void setDescCn(String descCn) {
		this.descCn = descCn;
	}


	public String getMainProducts() {
		return mainProducts;
	}


	public void setMainProducts(String mainProducts) {
		this.mainProducts = mainProducts;
	}


	public String getBidForRange() {
		return bidForRange;
	}


	public void setBidForRange(String bidForRange) {
		this.bidForRange = bidForRange;
	}


	public String getIsManufacturer() {
		return isManufacturer;
	}


	public void setIsManufacturer(String isManufacturer) {
		this.isManufacturer = isManufacturer;
	}


	public String getEntCapacity() {
		return entCapacity;
	}


	public void setEntCapacity(String entCapacity) {
		this.entCapacity = entCapacity;
	} 
	
	
	
	
	
}