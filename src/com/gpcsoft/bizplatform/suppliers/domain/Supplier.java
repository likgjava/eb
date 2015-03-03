package com.gpcsoft.bizplatform.suppliers.domain;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.VerifyObject;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * 供应商
 * @gpcsoft.package packageDir="com.gpcsoft.bizplatform.suppliers"
 * @gpcsoft.page domain="suppliers"
 * @hibernate.class table="SPL_SUPPLIER"
 * @author sunl
 * @version 1.0
 * @created 12-四月-2010 9:32:16
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "SPL_SUPPLIER")
public class Supplier implements GpcBaseObject,IPropertyCUserTime,IPropertyUUserTime ,VerifyObject{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 7917696452543147017L;
	
	/**
	 * 记录id
	 */
	@Id
	@Column(name = "SUPPLIER_ID",length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;

	/**
	 * 是否厂家
	 */
	@Column(name = "ISMANUFACTURER",length=100)
	private String isManufacturer;
	
	/**
	 * 法定代表人
	 */
	@Column(name = "CORPORATION",length=100)
	private String corporation;
	/**
	 * 企业简介（中）
	 */
	@Column(name = "DESC_CN",length=1000)
	private String descCn;
	/**
	 * 企业简介(英)
	 */
	@Column(name = "DESC_EN",length=1000)
	private String descEn;
	/**
	 * 开户帐号，银行账号
	 */
	@Column(name = "OPEN_ACCOUNT",length=100)
	private String openAccount;
	/**
	 * 开户帐号名称
	 */
	@Column(name = "OPEN_ACCOUNT_NMBR",length=100)
	private String openAccountNmbr;
	/**
	 * 开户银行名称
	 */
	@Column(name = "OPEN_BANK",length=100)
	private String openBank;
	/**
	 * 主营产品范围(中)
	 */
	@Column(name = "MAIN_PRODUCTS_CN",length=1000)
	private String mainProductsCn;
	/**
	 * 主营产品范围(英)
	 */
	@Column(name = "MAIN_PRODUCTS_EN",length=1000)
	private String mainProductsEn;

	/**
	 * 工商注册地址，住所
	 */
	@Column(name = "REG_ADDRESS",length=100)
	private String regAddress;
	/**
	 * 工商注册发证机关
	 */
	@Column(name = "REG_AUTH_ORG",length=100)
	private String regAuthOrg;
	/**
	 * 工商注册营业范围
	 */
	@Column(name = "REG_BUS_SCOPE",length=1000)
	private String regBusScope;
	
	@Transient
	private String regBusScopeName;
	
	/**
	 * 企业类型，公司类型
	 */
	@Column(name = "ENT_TYPE",length=100)
	private String entType;
	
	/**
	 * 工商注册号
	 */
	@Column(name = "REG_CODE",length=100)
	private String regCode;
	/**
	 * 工商注册日期
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "REG_DATE")
	private Date regDate;
	/**
	 * 工商注册有效期
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "REG_TO_DATE")
	private Date regToDate;
	/**
	 * 营业执照结束日期
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "TRADE_END_DATE")
	private Date tradeEndDate;
	/**
	 * 营业执照开始日期
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "TRADE_START_DATE")
	private Date tradeStartDate;
	/**
	 * 经营地址
	 */
	@Column(name = "UNIT_ADDRESS",length=100)
	private String unitAddress;
	/**
	 * 注册资金，注册资本
	 */
	@Column(name = "REG_CAPITAL")
	private Double regCapital;
	/**
	 * 实收资本
	 */
	@Column(name = "PAID_UP_CAPITAL")
	private Double paidUpCapital;
	/**
	 * 货币类型 01：元 02：美元 03：欧元 04：日元 05：港币 06：台币
	 */
	@Column(name = "REG_MONEY_TYPE",length=100)
	private String regMoneyType;
	/**
	 * 企业规模
	 */
	@Column(name = "UNIT_SCAPE",length=100)
	private String unitScape;
	/**
	 * 网址
	 */
	@Column(name = "WEB_URL",length=100)
	private String webUrl;
	/**
	 * 组织机构证颁发单位
	 */
	@Column(name = "ORG_UNIT_AWARD_UNIT",length=100)
	private String orgUnitAwardUnit;
	/**
	 * 组织机构证登记号
	 */
	@Column(name = "ORG_UNIT_REG_NMBR",length=100)
	private String orgUnitRegNmbr;
	/**
	 * 组织机构证开始日期
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "ORG_UNIT_START_DATE")
	private Date orgUnitStartDate;
	/**
	 * 组织机构证结束日期
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "ORG_UNIT_END_DATE")
	private Date orgUnitEndDate;
	/**
	 * 投标范围及类别
	 */
	@Column(name = "BID_FOR_RANGE", length=2000)
	private String bidForRange;
	
	@Transient
	private String bidForRangeName;
	
	@Transient
	private String bidForRangeCode;
	
	/**
	 * 企业性质
	 */
	@Column(name = "ENT_PRPT",length=100)
	private String entPrpt;
	
	@Transient
	private String entPrptCN;
	
	/**
	 * 国税登记编号
	 */
	@Column(name = "NAT_TAX_NMBR",length=100)
	private String natTaxNmbr;
	/**
	 * 税务登记证国税电脑编码
	 */
	@Column(name = "NAT_TAX_CMPT_NMBR",length=100)
	private String natTaxCmptNmbr;

	/**
	 * 地税登记编号
	 */
	@Column(name = "LAND_TAX_NMBR",length=100)
	private String landTaxNmbr;
	/**
	 * 税务登记证地税电脑编码
	 */
	@Column(name = "LAND_TAX_CMPT_NMBR",length=100)
	private String landTaxCmptNmbr;
	
	/**
	 * 审核状态，00.草稿 01.待审核 02.通过 03.不通过
	 */
	@Column(name = "AUDIT_STATUS",length=100)
	private String auditStatus;

	@Transient
	private String auditStatusCN;
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATOR") 
	@BatchSize(size = 15)
	private User createUser;
	
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 7)
	private Date createTime;
	
	/** 修改人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="MODIFIER")
	@BatchSize(size = 15)
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
	
	/** 机构 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="ORG_INFO_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private OrgInfo orgInfo;
	
	/**
	 * 评价总分
	 */
    @Formula("(select nvl(avg(e.summary_score),0) from ECP_PUB_EVALUATE e where e.ORGINFO_ID = ORG_INFO_ID)")
    private BigDecimal evalSum;

	public OrgInfo getOrgInfo() {
		return orgInfo;
	}
	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getIsManufacturer() {
		return isManufacturer;
	}
	public void setIsManufacturer(String isManufacturer) {
		this.isManufacturer = isManufacturer;
	}
	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getDescCn() {
		return descCn;
	}

	public void setDescCn(String descCn) {
		this.descCn = descCn;
	}

	public String getDescEn() {
		return descEn;
	}

	public void setDescEn(String descEn) {
		this.descEn = descEn;
	}

	public String getOpenAccount() {
		return openAccount;
	}

	public void setOpenAccount(String openAccount) {
		this.openAccount = openAccount;
	}

	public String getOpenAccountNmbr() {
		return openAccountNmbr;
	}

	public void setOpenAccountNmbr(String openAccountNmbr) {
		this.openAccountNmbr = openAccountNmbr;
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public String getMainProductsCn() {
		return mainProductsCn;
	}

	public void setMainProductsCn(String mainProductsCn) {
		this.mainProductsCn = mainProductsCn;
	}

	public String getMainProductsEn() {
		return mainProductsEn;
	}

	public void setMainProductsEn(String mainProductsEn) {
		this.mainProductsEn = mainProductsEn;
	}

	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	public String getRegAuthOrg() {
		return regAuthOrg;
	}

	public void setRegAuthOrg(String regAuthOrg) {
		this.regAuthOrg = regAuthOrg;
	}

	public String getRegBusScope() {
		return regBusScope;
	}

	public void setRegBusScope(String regBusScope) {
		this.regBusScope = regBusScope;
	}

	public String getEntType() {
		return entType;
	}

	public void setEntType(String entType) {
		this.entType = entType;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getRegToDate() {
		return regToDate;
	}

	public void setRegToDate(Date regToDate) {
		this.regToDate = regToDate;
	}

	public Date getTradeEndDate() {
		return tradeEndDate;
	}

	public void setTradeEndDate(Date tradeEndDate) {
		this.tradeEndDate = tradeEndDate;
	}

	public Date getTradeStartDate() {
		return tradeStartDate;
	}

	public void setTradeStartDate(Date tradeStartDate) {
		this.tradeStartDate = tradeStartDate;
	}

	public String getUnitAddress() {
		return unitAddress;
	}

	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}

	public Double getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(Double regCapital) {
		this.regCapital = regCapital;
	}

	public Double getPaidUpCapital() {
		return paidUpCapital;
	}

	public void setPaidUpCapital(Double paidUpCapital) {
		this.paidUpCapital = paidUpCapital;
	}

	public String getRegMoneyType() {
		return regMoneyType;
	}

	public void setRegMoneyType(String regMoneyType) {
		this.regMoneyType = regMoneyType;
	}

	public String getUnitScape() {
		return unitScape;
	}

	public void setUnitScape(String unitScape) {
		this.unitScape = unitScape;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getOrgUnitAwardUnit() {
		return orgUnitAwardUnit;
	}

	public void setOrgUnitAwardUnit(String orgUnitAwardUnit) {
		this.orgUnitAwardUnit = orgUnitAwardUnit;
	}

	public String getOrgUnitRegNmbr() {
		return orgUnitRegNmbr;
	}

	public void setOrgUnitRegNmbr(String orgUnitRegNmbr) {
		this.orgUnitRegNmbr = orgUnitRegNmbr;
	}

	public Date getOrgUnitStartDate() {
		return orgUnitStartDate;
	}

	public void setOrgUnitStartDate(Date orgUnitStartDate) {
		this.orgUnitStartDate = orgUnitStartDate;
	}

	public Date getOrgUnitEndDate() {
		return orgUnitEndDate;
	}

	public void setOrgUnitEndDate(Date orgUnitEndDate) {
		this.orgUnitEndDate = orgUnitEndDate;
	}

	public String getBidForRange() {
		return bidForRange;
	}

	public void setBidForRange(String bidForRange) {
		this.bidForRange = bidForRange;
	}

	public String getEntPrpt() {
		return entPrpt;
	}
	
	public void setEntPrpt(String entPrpt) {
		this.entPrpt = entPrpt;
	}
	
	public String getEntPrptCN() {
		this.entPrptCN = OrganizationEnum.getEntPrptCN(this.entPrpt);
		return this.entPrptCN;
	}
	
	public void setEntPrptCN(String entPrptCN) {
		this.entPrptCN = entPrptCN;
	}

	public String getNatTaxNmbr() {
		return natTaxNmbr;
	}

	public void setNatTaxNmbr(String natTaxNmbr) {
		this.natTaxNmbr = natTaxNmbr;
	}

	public String getNatTaxCmptNmbr() {
		return natTaxCmptNmbr;
	}

	public void setNatTaxCmptNmbr(String natTaxCmptNmbr) {
		this.natTaxCmptNmbr = natTaxCmptNmbr;
	}

	public String getLandTaxNmbr() {
		return landTaxNmbr;
	}

	public void setLandTaxNmbr(String landTaxNmbr) {
		this.landTaxNmbr = landTaxNmbr;
	}

	public String getLandTaxCmptNmbr() {
		return landTaxCmptNmbr;
	}

	public void setLandTaxCmptNmbr(String landTaxCmptNmbr) {
		this.landTaxCmptNmbr = landTaxCmptNmbr;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	public String getAuditStatusCN() {
		this.auditStatusCN = OrganizationEnum.getAuditStatusCN(this.auditStatus);
		return this.auditStatusCN;
	}
	
	public void setAuditStatusCN(String auditStatusCN) {
		this.auditStatusCN = auditStatusCN;
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
	
	public BigDecimal getEvalSum() {
		return evalSum;
	}

	public void setEvalSum(BigDecimal evalSum) {
		this.evalSum = evalSum;
	} 

	public String getRegBusScopeName() {
		if(this.regBusScope != null){
			String temp = this.regBusScope.replace("##||##", "&");
			String args[] = temp.split("&");
			if(args != null && args.length > 1){
				this.regBusScopeName = args[1]==null?"":args[1];
			}else{
				this.regBusScopeName = "";
			}
		}
		return regBusScopeName;
	}
	
	public void setRegBusScopeName(String regBusScopeName) {
		this.regBusScopeName = regBusScopeName;
	}
}