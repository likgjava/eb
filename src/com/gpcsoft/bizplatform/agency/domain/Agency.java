package com.gpcsoft.bizplatform.agency.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.VerifyObject;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * 代理机构
 * @gpcsoft.package packageDir="com.gpcsoft.bizplatform.agency"
 * @gpcsoft.page domain="agency"
 * @hibernate.class table="AGENCY_AGENT"
 * @author xiaoyong
 * @version 1.0
 * @created 16-三月-2010 13:55:15
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "AGENCY_AGENT")
public class Agency implements GpcBaseObject,IPropertyCUserTime,IPropertyUUserTime ,VerifyObject{

	/** serialVersionUID */
    private static final long serialVersionUID = 4300282297503759581L;
    
    /**
     * 记录号
     */
    @Id
    @Column(name = "AGENT_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
	/**
	 * 主管单位，上级主管部门
	 */
    @ManyToOne(fetch=FetchType.LAZY)
    @Cascade({CascadeType.REFRESH})//级联类型
    @JoinColumn(name="UNIT_IN_CHARGE_ID")//关联的外键  
    @BatchSize(size = 15)//批量抓取
	private OrgInfo unitInCharge;
    
	/**
	 * 行政级别：1.省、2.市、3.县、4.区
	 */
    @Column(name = "ADMIN_GRD", length = 100)
	private String adminGrd;
    
    @Transient
    private String adminGrdCN;
	/**
	 * 近三年经营代理情况
	 */
    @Column(name = "AGENCY_BUSS_CNDT", length = 1000)
	private String agencyBussCndt;
	/**
	 * 代理机构类型： 01:政府集中采购中心 02:政府部门采购中心 03:社会中介（招标公司） 04:企业专职采购部门
	 */
    @Column(name = "AGENT_TYPE", length = 100)
	private String agentType;
    
    @Transient 
	private String agentTypeCN;
	
	/**
	 * 评标地址
	 */
    @Column(name = "BID_PRPS_EVLT_ADDR", length = 100)
	private String bidPrpsEvltAddr;
	/**
	 * 营业范围（兼营）
	 */
    @Column(name = "CNCR_BUSS_SCP", length = 1000)
	private String cncrBussScp;
    /**
	 * 流动资产
	 */
    @Column(name = "CRNT_AST")
	private Double crntAst;
	/**
	 * 经济性质
	 */
    @Column(name = "ECNM_NATURE", length = 100)
	private String ecnmNature;
	/**
	 * 固定资产年折旧额
	 */
    @Column(name = "FIXED_AST_DPRC_YR")
	private Double fixedAstDprcYr;
	/**
	 * 高级职称技术人员数
	 */
    @Column(name = "HIGH_TITLE_TCHST_NMBR", length = 9)
	private Integer highTitleTchstNmbr;
	/**
	 * 营业范围（主营）
	 */
    @Column(name = "MIAN_BUSS_SCP", length = 1000)
	private String mianBussScp;
	/**
	 * 中级职称技术人员数
	 */
    @Column(name = "MIDDLE_TITLE_TCHST_NMBR", length = 9)
	private Integer middleTitleTchstNmbr;

	/**
	 * 开户帐号
	 */
	@Column(name = "OPEN_ACCOUNT", length = 100)
	private String openAccount;
	/**
	 * 开户帐号名称
	 */
	@Column(name = "OPEN_ACCOUNT_NAME", length = 100)
	private String openAccountName;
	/**
	 * 开户银行
	 */
	@Column(name = "OPEN_BANK", length = 100)
	private String openBank;
	/**
	 * 从业人员总数
	 */
	@Column(name = "PRCT_TOTAL_NMBR", length = 9)
	private Integer prctTotalNmbr;
	/**
	 * 采购代理:01.集中采购、02.招标代理
	 */
	@Column(name = "PUR_AGENT", length = 100)
	private String purAgent;
	/**
	 * 工商注册地址
	 */
	@Column(name = "REG_ADDRESS", length = 100)
	private String regAddress;
	/**
	 * 工商注册发证机关
	 */
	@Column(name = "REG_AUTH_ORG", length = 100)
	private String regAuthOrg;
	/**
	 * 工商注册营业范围
	 */
	@Column(name = "REG_BUS_SCOPE", length = 100)
	private String regBusScope;
	/**
	 * 注册资金
	 */
	@Column(name = "REG_CAPITAL")
	private Double regCapital;
	/**
	 * 工商注册号
	 */
	@Column(name = "REG_CODE", length = 100)
	private String regCode;
	/**
	 * 工商注册法人
	 */
	@Column(name = "REG_COPORATE", length = 100)
	private String regCoporate;
	/**
	 * 工商注册日期
	 */
    @Temporal(TemporalType.DATE) 
	@Column(name = "REG_DATE")
	private Date regDate;
	/**
	 * 工商注册执照
	 */
	@Cascade({CascadeType.REFRESH})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REG_FILE_ID")
	private Attachment regFile;
	/**
	 * 工商注册有效期
	 */
    @Temporal(TemporalType.DATE)
	@Column(name = "REG_TO_DATE")
	private Date regToDate;
	/**
	 * 是否ISO认证
	 */
	@Column(name = "REG_ISO", length = 100)
	private String regIso;
	
	@Transient 
	private String regISOCN; /*是否ISO认证*/
	
	/**
	 * 注册执业人员数
	 */
	@Column(name = "REG_PRCTS_NMBR", length = 9)
	private Integer regPrctsNmbr;
    /**
	 * 负责总额
	 */
	@Column(name = "TOTAL_CHARGE")
	private Double totalCharge;
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
	 * 近三年承担过的招标代理项目
	 */
	@Column(name = "UNDTK_BID_PROJ", length = 1000)
	private String undtkBidProj;
	/**
	 * 企业简介
	 */
	@Column(name = "UNIT_DESC", length = 100)
	private String unitDesc;
		
	/**
	 * 资产总额
	 */
    @Column(name = "TOTAL_ASSETS")
	private Double totalAssets;
	/**
	 * 企业规模
	 */
	@Column(name = "UNIT_SCAPE", length = 100)
	private String unitScape;
	/**
	 * 企业类型 01：国家机关 02：事业单位 03：团体组织
	 */
	@Column(name = "UNIT_TYPE", length = 100)
	private String unitType;
	
	@Transient 
	private String unitTypeCN; /*企业类型*/
	/**
	 * 网址
	 */
	@Column(name = "WEB_URL", length = 100)
	private String webUrl;
	/**
	 * 备注
	 */
	@Column(name = "REMARKS", length = 2000)
	private String remarks;
	
	/**
	 * 00:草稿 01：待审核 02：审核通过 03：审核不通过
	 */
	@Column(name = "AUDIT_STATUS", length = 100)
	private String auditStatus;
	
	@Transient
	private String auditStatusCN;
	
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
	public String getAdminGrd() {
		return adminGrd;
	}
	public void setAdminGrd(String adminGrd) {
		this.adminGrd = adminGrd;
	}
	public String getAgencyBussCndt() {
		return agencyBussCndt;
	}
	public void setAgencyBussCndt(String agencyBussCndt) {
		this.agencyBussCndt = agencyBussCndt;
	}
	public String getAgentType() {
		return agentType;
	}
	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}
	public String getAgentTypeCN() {
		this.agentTypeCN = OrganizationEnum.getAgentTypeCN(this.agentType);
		return this.agentTypeCN;
	}
	public void setAgentTypeCN(String agentTypeCN) {
		this.agentTypeCN = agentTypeCN;
	}
	public String getBidPrpsEvltAddr() {
		return bidPrpsEvltAddr;
	}
	public void setBidPrpsEvltAddr(String bidPrpsEvltAddr) {
		this.bidPrpsEvltAddr = bidPrpsEvltAddr;
	}
	public String getCncrBussScp() {
		return cncrBussScp;
	}
	public void setCncrBussScp(String cncrBussScp) {
		this.cncrBussScp = cncrBussScp;
	}
	public Double getCrntAst() {
		return crntAst;
	}
	public void setCrntAst(Double crntAst) {
		this.crntAst = crntAst;
	}
	public String getEcnmNature() {
		return ecnmNature;
	}
	public void setEcnmNature(String ecnmNature) {
		this.ecnmNature = ecnmNature;
	}
	public Double getFixedAstDprcYr() {
		return fixedAstDprcYr;
	}
	public void setFixedAstDprcYr(Double fixedAstDprcYr) {
		this.fixedAstDprcYr = fixedAstDprcYr;
	}
	public Integer getHighTitleTchstNmbr() {
		return highTitleTchstNmbr;
	}
	public void setHighTitleTchstNmbr(Integer highTitleTchstNmbr) {
		this.highTitleTchstNmbr = highTitleTchstNmbr;
	}
	public String getMianBussScp() {
		return mianBussScp;
	}
	public void setMianBussScp(String mianBussScp) {
		this.mianBussScp = mianBussScp;
	}
	public Integer getMiddleTitleTchstNmbr() {
		return middleTitleTchstNmbr;
	}
	public void setMiddleTitleTchstNmbr(Integer middleTitleTchstNmbr) {
		this.middleTitleTchstNmbr = middleTitleTchstNmbr;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getOpenAccount() {
		return openAccount;
	}
	public void setOpenAccount(String openAccount) {
		this.openAccount = openAccount;
	}
	public String getOpenAccountName() {
		return openAccountName;
	}
	public void setOpenAccountName(String openAccountName) {
		this.openAccountName = openAccountName;
	}
	public String getOpenBank() {
		return openBank;
	}
	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	public Integer getPrctTotalNmbr() {
		return prctTotalNmbr;
	}
	public void setPrctTotalNmbr(Integer prctTotalNmbr) {
		this.prctTotalNmbr = prctTotalNmbr;
	}
	public String getPurAgent() {
		return purAgent;
	}
	public void setPurAgent(String purAgent) {
		this.purAgent = purAgent;
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
	public Double getRegCapital() {
		return regCapital;
	}
	public void setRegCapital(Double regCapital) {
		this.regCapital = regCapital;
	}
	public String getRegCode() {
		return regCode;
	}
	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	public String getRegCoporate() {
		return regCoporate;
	}
	public void setRegCoporate(String regCoporate) {
		this.regCoporate = regCoporate;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Attachment getRegFile() {
		return regFile;
	}
	public void setRegFile(Attachment regFile) {
		this.regFile = regFile;
	}
	public String getRegIso() {
		return regIso;
	}
	public void setRegIso(String regIso) {
		this.regIso = regIso;
	}
	public Integer getRegPrctsNmbr() {
		return regPrctsNmbr;
	}
	public void setRegPrctsNmbr(Integer regPrctsNmbr) {
		this.regPrctsNmbr = regPrctsNmbr;
	}
	public Date getRegToDate() {
		return regToDate;
	}
	public void setRegToDate(Date regToDate) {
		this.regToDate = regToDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Double getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(Double totalCharge) {
		this.totalCharge = totalCharge;
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
	public String getUndtkBidProj() {
		return undtkBidProj;
	}
	public void setUndtkBidProj(String undtkBidProj) {
		this.undtkBidProj = undtkBidProj;
	}
	public String getUnitDesc() {
		return unitDesc;
	}
	public void setUnitDesc(String unitDesc) {
		this.unitDesc = unitDesc;
	}
	public OrgInfo getUnitInCharge() {
		return unitInCharge;
	}
	public void setUnitInCharge(OrgInfo unitInCharge) {
		this.unitInCharge = unitInCharge;
	}

	public String getUnitScape() {
		return unitScape;
	}
	public void setUnitScape(String unitScape) {
		this.unitScape = unitScape;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	
	public String getRegISOCN() {
		return this.regISOCN;
	}
	
	public void setRegISOCN(String regISOCN) {
		this.regISOCN = regISOCN;
	}
	
	public String getUnitTypeCN() {
		this.unitTypeCN = OrganizationEnum.getUnitTypeCN(this.unitType);
		return this.unitTypeCN ;
	}
	
	public void setUnitTypeCN(String unitTypeCN) {
		this.unitTypeCN = unitTypeCN;
	}

	public String getAdminGrdCN() {
		this.adminGrdCN = OrganizationEnum.getAdminGrdCN(this.adminGrd);
		return this.adminGrdCN;
	}
	
	public void setAdminGrdCN(String adminGrdCN) {
		this.adminGrdCN = adminGrdCN;
	}
	
	public Double getTotalAssets() {
		return totalAssets;
	}
	
	public void setTotalAssets(Double totalAssets) {
		this.totalAssets = totalAssets;
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

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public User getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
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
	public BigDecimal getEvalSum() {
		return evalSum;
	}
	public void setEvalSum(BigDecimal evalSum) {
		this.evalSum = evalSum;
	}
	
	
}