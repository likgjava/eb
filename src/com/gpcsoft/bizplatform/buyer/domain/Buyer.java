package com.gpcsoft.bizplatform.buyer.domain;
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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.base.industry.domain.Industry;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.VerifyObject;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.baseData.domain.District;

/**
 * 采购人
 * @gpcsoft.package packageDir="com.gpcsoft.eps.buyer"
 * @gpcsoft.page domain="buyer"
 * @hibernate.class table="BUY_BUYER"
 * @author admin
 * @version 1.0
 * @created 08-三月-2010 10:54:31
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name="BUY_BUYER")
public class Buyer implements GpcBaseObject,IPropertyCUserTime,IPropertyUUserTime ,VerifyObject{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录id
	 */
	@Id
	@Column(name = "BUYER_ID",length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/**
	 * 上级采购单位
	 */
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@Cascade({CascadeType.REFRESH})//级联类型
	@JoinColumn(name="PARENT_UNIT_ID")//关联的外键  
	@BatchSize(size = 15)//批量抓取
	private OrgInfo parentUnit;
	
	/**
	 * 预算编码
	 */
	@Column(name = "BUDGET_CODE",length = 100)
	private String budgetCode;
	
	/**
	 * 行政部门
	 */
	@Column(name = "EXEC_DEPT", length = 100)
	private String execDept;
	
	@Transient
	private String execDeptCN;
	
	/**
	 * 传真
	 */
	@Column(name = "FAX", length = 50)
	private String fax;
	
	/**
	 * 资金归口处室，01：行政政法 02：教科文 03：经济建设 04：农业 05：社会保障 06：企业 07：金融 08：其他
	 */
	@Column(name = "FUND_DEPT", length = 100)
	private String fundDept;
	
	@Transient
	private String fundDeptCN;
	
	/**
	 * 采购主体：暂时不用
	 */
	@Column(name = "PUR_SBJCT", length = 100)
	private String purSbjct;
	
	@Transient
	private String purSbjctCN;

	/**
	 * 机构简介
	 */
	@Column(name = "UNIT_INTRODUCTION", length = 1000)
	private String unitIntroduction;
	
	/**
	 * 单位性质 01：国家机关 02：事业单位 03：团体组织 04：企业单位
	 */
	@Column(name = "UNIT_TYPE", length = 100)
	private String unitType;
	
	@Transient
	private String unitTypeCN;
	
	/**
	 * 所属行业-关联字典表
	 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="INDUSTRY_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Industry belongIndustry;
	
	/**
	 * 主管部门 01:财政 02:建设 03:发改委 04:经贸委 05:监察 06:统计
	 */
	@Column(name = "CMPT_DEP_TYPE", length = 60)
	private String cmptDepType;
	
	@Transient 
	private String cmptDepTypeCN;/*主管部门*/
	
	
	/**
	 * 所属区域
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@BatchSize(size = 100)
	@JoinColumn(name = "DISTRICT_ID")
	private District belongsDistricts;

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
    
    /**
	 * 交易总额
	 */
    @Column(name = "DEAL_TOTAL")
    private BigDecimal dealTotal;//同步此数据是否太繁琐？ by yucy
    
    //成交总额
//    @Formula("(select nvl(sum(o.GOODS_TOTAL), 0) from EPS_AGREEMENT_ORDER o ,EPS_PUB_CONTRACT c where c.CONTRACT_ID = o.CONTRACT_ID and c.BUYER_ID = ORG_INFO_ID)")
//    private BigDecimal dealTotal;
    
    /**
	 * 成交时间
	 */
    @Column(name = "DEAL_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dealTime;

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

	public Industry getBelongIndustry() {
		return belongIndustry;
	}

	public void setBelongIndustry(Industry belongIndustry) {
		this.belongIndustry = belongIndustry;
	}
	
	public OrgInfo getParentUnit() {
		return parentUnit;
	}

	public void setParentUnit(OrgInfo parentUnit) {
		this.parentUnit = parentUnit;
	}

	public String getBudgetCode() {
		return budgetCode;
	}

	public void setBudgetCode(String budgetCode) {
		this.budgetCode = budgetCode;
	}

	public String getExecDept() {
		return execDept;
	}

	public void setExecDept(String execDept) {
		this.execDept = execDept;
	}

	public String getExecDeptCN() {
		this.execDeptCN = OrganizationEnum.getExecDeptCN(this.execDept);
		return this.execDeptCN;
	}
	
	public void setExecDeptCN(String execDeptCN) {
		this.execDeptCN = execDeptCN;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFundDept() {
		return fundDept;
	}

	public void setFundDept(String fundDept) {
		this.fundDept = fundDept;
	}

	public String getPurSbjct() {
		return purSbjct;
	}

	public void setPurSbjct(String purSbjct) {
		this.purSbjct = purSbjct;
	}

	public String getFundDeptCN() {
		this.fundDeptCN = OrganizationEnum.getFundDeptCN(this.fundDept);
		return this.fundDeptCN;
	}
	public void setFundDeptCN(String fundDeptCN) {
		this.fundDeptCN = fundDeptCN;
	}
	
	public String getUnitTypeCN() {
		this.unitTypeCN = OrganizationEnum.getBuyerUnitTypeCN(this.unitType);
		return this.unitTypeCN;
	}
	public void setUnitTypeCN(String unitTypeCN) {
		this.unitTypeCN = unitTypeCN;
	}
	
	public String getPurSbjctCN() {
		this.purSbjctCN = OrganizationEnum.getPurSbjctCN(this.purSbjct);
		return this.purSbjctCN;
	}
	
	public void setPurSbjctCN(String purSbjctCN) {
		this.purSbjctCN = purSbjctCN;
	}

	public String getUnitIntroduction() {
		return unitIntroduction;
	}

	public void setUnitIntroduction(String unitIntroduction) {
		this.unitIntroduction = unitIntroduction;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getCmptDepType() {
		return cmptDepType;
	}

	public void setCmptDepType(String cmptDepType) {
		this.cmptDepType = cmptDepType;
	}

	public String getCmptDepTypeCN() {
		this.cmptDepTypeCN = OrganizationEnum.getCmptDepTypeCN(this.cmptDepType);
		return this.cmptDepTypeCN;
	}

	public void setCmptDepTypeCN(String cmptDepTypeCN) {
		this.cmptDepTypeCN = cmptDepTypeCN;
	}

	public District getBelongsDistricts() {
		return belongsDistricts;
	}

	public void setBelongsDistricts(District belongsDistricts) {
		this.belongsDistricts = belongsDistricts;
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

	public BigDecimal getEvalSum() {
		return evalSum;
	}
	public void setEvalSum(BigDecimal evalSum) {
		this.evalSum = evalSum;
	}
	public BigDecimal getDealTotal() {
		return dealTotal;
	}
	public void setDealTotal(BigDecimal dealTotal) {
		this.dealTotal = dealTotal;
	}
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	
	
	
}