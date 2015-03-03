package com.gpcsoft.epp.taskplan.domain;

import java.math.BigDecimal;
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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;
/** 
  *  Comments: <strong>RecordForm</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   es                    					          
  *  <br/>Module ID: 备案书表     		
  *  <br/>Create Date：2010-4-14 下午05:20:48 by guom    					                            
  *  <br/>Modified Date:  2010-4-14 下午05:20:48 by guom                                 
  *  @gpcsoft.package packageDir="com.gpcsoft.epp.project"
  *  @gpcsoft.page domain="planform/Record" project="es"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="备案书"  
  *  @since 0.1
  *  @version: 0.1 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPP_RECORD_FORM")
public class RecordForm extends WorkFlowModel implements GpcBaseObject, IPropertyCUserTime, IPropertyUUserTime{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "REC_FORM_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; //主键

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="TENDERID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Project project;
	
	@Column(name = "REC_FORM_NO")
	private String recFormNo;//招管备案号
	
	@Column(name = "REC_FORM_SCALE")     
	private String recFormScale;//建设规模
	
	@Column(name = "REC_FORM_ADDR")   
	private String recFormAddr;//建设地点
	
	@Column(name = "REC_FORM_STRUCTURE_TYPE")  
	private String recFormStructureType;//结构类型
	
	@Column(name = "REC_FORM_ENGINEERING_NAME")
	private String engineeringName;//工程名称
	
	@Column(name = "REC_FORM_TENDERER_PROVE")  
	private String tendererProve;//招标人证明
	
	@Column(name = "REC_FORM_PROJ_APPROVAL")   
	private String projApproval;//立项证明
	
	@Column(name = "REC_FORM_PLAN_PERMIT") 
	private String planPermit;//建设工程规划许可证
	
	@Column(name = "REC_FORM_FUNDS_PROOF")  
	private String fundsProof;//资金落实证明
	
	@Column(name = "REC_FORM_APPROVE_UNIT_ATTA_ID") 
	private String approveUnit;//规划批准单位
	
	@Column(name = "REC_FORM_DESIGN_UNIT_ATTA_ID") 
	private String designUnit;//设计图审查批准文件号
	
	@Column(name = "REC_FORM_PRFR_PRCD_ATTA_ID") 
	private String performProcedure;//招标范围、招标方式和招标组织形式等应当履行核准手续的，已经核准（附核准文件）
	
	@Column(name = "REC_PROJ_MANAGER")      
	private String projManager;//项目经理
	
	@Column(name = "REC_RESERVE_PRICE")   
	private String reservePrice;//底价
	
	@Column(name = "REC_FORM_ORG_NAME")
	private String recFormOrgName;//建设单位名称
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="REC_FORM_ORG_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 1)//批量抓取
	private OrgInfo recFormOrgId;//建设单位id
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="REC_RESERVE_USER_ID")//关联的外键	 
	@BatchSize(size = 1)//批量抓取
	private User recReserveUserId;//经办人ID 
	
	@Column(name = "REC_RESERVE_USER_NAME")
	private String recReserveUserName;//经办人名称 
	
	@Column(name = "EBUY_METHOD")
	private String ebuyMethod;//招标方式 
	
	@Transient
	private String ebuyMethodCn;//招标方式 中文
	
	@Column(name = "REC_FORM_INV_ORG")
	private String recFormInvOrg;//勘察单位
	
	@Column(name = "REC_FORM_DESIGN_ORG")
	private String recFormDesignOrg;//设计单位
	
	@Transient
	private String bidSupplier; //中标供应商
	@Transient
	private BigDecimal bidQuote;//中标造价
	@Column(name = "REC_FORM_CONTENT")
	private String recFormContent;//招标范围 
	
	@Column(name = "REC_FORM_BUY_METHOD")
	private String recFormBuyMethod;//组织形式[00:自行组织招标；01:委托组织招标] 
	
	@Transient
	private String recFormBuyMethodCn;
	
	@Column(name = "ECP_FORM_BULLETIN_DATE")
	private Date ecpFormBulletinDate;//招标公告发布时间
	
	@Column(name = "ECP_FORM_TENDER_DOC_DATE")
	private Date ecpFormTenderDocDate;//招标文件发放时间 
	
	@Column(name = "ECP_FORM_BIDDING_S_DATE")
	private Date ecpFormBiddingSDate;//投标开始时间 
	
	@Column(name = "ECP_FORM_BIDDING_E_DATE")
	private Date ecpFormBiddingEDate;//投标结束时间 
	
	@Column(name = "ECP_FORM_NOTICE_DATE")
	private Date ecpFormNoticeDate;//投标结束时间 
	
	@Column(name = "ECP_FORM_CONTRACT_DATE")
	private Date ecpFormNoticDate;//合同签订时间 
	
	@OneToMany(mappedBy="recordForm", fetch=FetchType.LAZY)
	@Cascade({CascadeType.ALL})
	@JoinColumn(name = "REC_FORM_ID") 
	private Set<ProjMember> projMembers = new HashSet<ProjMember>();
	
	
    @ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="AGENTY_ID")
	private OrgInfo agency; //代理机构

	
	@Column(name = "PROJECT_NATURE")
	private String projNature;//项目性质(00:新建、01:扩建、02:改建) 
	
	@Column(name = "PROJECT_ATT")
	private String projProperty;//项目属性(00:基础设施、01:公用事业) 
	
	@Column(name = "MONEY_SOURCE")
	private String moneySource;//资金来源
	
	@Column(name = "INVESTENT")
	private BigDecimal investent;//投资金额
	
	@Column(name = "TOTAL_AREA_VAR")
	private String totalArea;//建筑总面积(m2) 
	
	@Column(name = "SINGLE_AREA_VAR")
	private String singleArea;//单位建筑面积(m2) ,定义为字符类型
	
	@Column(name = "PRO_NUMBER")
	private BigDecimal projNumber;//工程个数
	
	@Column(name = "NUM_LAYER_OVERGROUND")
	private BigDecimal layerOverRound;//层数地上 
	
	@Column(name = "NUM_LAYER_UNDERGROUND")
	private BigDecimal layerUnderRound;//层数地下
	
	@Column(name = "CORNICE")
	private BigDecimal cornice;//檐口高度(m)
	
	@Column(name = "SPAN")
	private BigDecimal span;//跨度(m)
	
	@Column(name = "FORM_ORG_LINKER")
	private String linkerName;//联系人名称
	
	@Column(name = "FORM_ORG_LINKER_TEL")
	private String linkerTel;//联系人电话
	
	@Column(name = "UNIT_PROPERTY")
	private String unitProperty;//	建设单位性质,招标人性质填“国有”、“民营”、“外商独资”、“中外合资”、“行政机关”、“事业单位”；
    
	@Transient
	private String unitPropertyCN;
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATE_USER_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User createUser;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
	private Date createTime;//创建时间
    //修改人
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="MODIFY_USER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User updateUser;
    
    //修改时间
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
	
    @Column(name = "USE_STATUS")
    private String useStatus;
    
    @Column(name = "AUDIT_STATUS")
    private String auditStatus;
    
	/**
	 * @gpcsoft.property title="项目"
	 * @gpcsoft.validate class="required"
	 */
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @gpcsoft.property title="招管备案号"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="50"
	 */
	public String getRecFormNo() {
		return recFormNo;
	}

	public void setRecFormNo(String recFormNo) {
		this.recFormNo = recFormNo;
	}

	/**
	 * @gpcsoft.property title="建设规模（面积、层次、工程估价）"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="50"
	 */
	public String getRecFormScale() {
		return recFormScale;
	}

	public void setRecFormScale(String recFormScale) {
		this.recFormScale = recFormScale;
	}

	/**
	 * @gpcsoft.property title="建设地点"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="500"
	 */
	public String getRecFormAddr() {
		return recFormAddr;
	}

	public void setRecFormAddr(String recFormAddr) {
		this.recFormAddr = recFormAddr;
	}

	/**
	 * @gpcsoft.property title="结构类型"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="2"
	 */
	public String getRecFormStructureType() {
		return recFormStructureType;
	}

	public void setRecFormStructureType(String recFormStructureType) {
		this.recFormStructureType = recFormStructureType;
	}

	/**
	 * @gpcsoft.property title="工程名称"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="500"
	 */
	public String getEngineeringName() {
		return engineeringName;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	/**
	 * @gpcsoft.property title="招标人证明"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="50"
	 */
	public String getTendererProve() {
		return tendererProve;
	}

	public void setTendererProve(String tendererProve) {
		this.tendererProve = tendererProve;
	}

	/**
	 * @gpcsoft.property title="立项批文"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="500"
	 */
	public String getProjApproval() {
		return projApproval;
	}

	public void setProjApproval(String projApproval) {
		this.projApproval = projApproval;
	}

	/**
	 * @gpcsoft.property title="建设工程规划许可证"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="500"
	 */
	public String getPlanPermit() {
		return planPermit;
	}

	public void setPlanPermit(String planPermit) {
		this.planPermit = planPermit;
	}

	/**
	 * @gpcsoft.property title="资金落实证明"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="50"
	 */
	public String getFundsProof() {
		return fundsProof;
	}

	public void setFundsProof(String fundsProof) {
		this.fundsProof = fundsProof;
	}

	/**
	 * @gpcsoft.property title="项目经理"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="50"
	 */
	public String getProjManager() {
		return projManager;
	}

	public void setProjManager(String projManager) {
		this.projManager = projManager;
	}

	/**
	 * @gpcsoft.property title="底价"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="50"
	 */
	public String getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(String reservePrice) {
		this.reservePrice = reservePrice;
	}

	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @gpcsoft.property title="建设单位"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="50"
	 */
	public String getRecFormOrgName() {
		return recFormOrgName;
	}

	public void setRecFormOrgName(String recFormOrgName) {
		this.recFormOrgName = recFormOrgName;
	}

	/**
	 * @gpcsoft.property title="建设单位ID"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="50"
	 */
	public OrgInfo getRecFormOrgId() {
		return recFormOrgId;
	}

	public void setRecFormOrgId(OrgInfo recFormOrgId) {
		this.recFormOrgId = recFormOrgId;
	}

	public String getBidSupplier() {
		return bidSupplier;
	}
	public void setBidSupplier(String bidSupplier) {
		this.bidSupplier = bidSupplier;
	}
	/**
	 * @gpcsoft.property title="经办人ID"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="50"
	 */
	public User getRecReserveUserId() {
		return recReserveUserId;
	}

	public void setRecReserveUserId(User recReserveUserId) {
		this.recReserveUserId = recReserveUserId;
	}

	/**
	 * @gpcsoft.property title="经办人"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="50"
	 */
	public String getRecReserveUserName() {
		return recReserveUserName;
	}

	public void setRecReserveUserName(String recReserveUserName) {
		this.recReserveUserName = recReserveUserName;
	}

	/**
	 * @gpcsoft.property title="招标方式"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="2"
	 */
	public String getEbuyMethod() {
		return ebuyMethod;
	}

	public void setEbuyMethod(String ebuyMethod) {
		this.ebuyMethod = ebuyMethod;
	}

	/**
	 * @gpcsoft.property title="勘察单位"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="200"
	 */
	public String getRecFormInvOrg() {
		return recFormInvOrg;
	}

	public void setRecFormInvOrg(String recFormInvOrg) {
		this.recFormInvOrg = recFormInvOrg;
	}

	/**
	 * @gpcsoft.property title="设计单位"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="50"
	 */
	public String getRecFormDesignOrg() {
		return recFormDesignOrg;
	}

	public void setRecFormDesignOrg(String recFormDesignOrg) {
		this.recFormDesignOrg = recFormDesignOrg;
	}

	/**
	 * @gpcsoft.property title="招标范围"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="500"
	 */
	public String getRecFormContent() {
		return recFormContent;
	}

	public void setRecFormContent(String recFormContent) {
		this.recFormContent = recFormContent;
	}

	/**
	 *  组织形式[00:自行组织招标；01:委托组织招标] 
	 * @gpcsoft.property title="招标组织"
	 * @gpcsoft.validate class="required"
	 * @gpcsoft.validate maxlength="2"
	 */
	public String getRecFormBuyMethod() {
		return recFormBuyMethod;
	}

	public void setRecFormBuyMethod(String recFormBuyMethod) {
		this.recFormBuyMethod = recFormBuyMethod;
	}

	/**
	 * @gpcsoft.property title="发布招标公告（投标邀请书）"
	 * @gpcsoft.validate class="required"
	 */
	public Date getEcpFormBulletinDate() {
		return ecpFormBulletinDate;
	}

	public void setEcpFormBulletinDate(Date ecpFormBulletinDate) {
		this.ecpFormBulletinDate = ecpFormBulletinDate;
	}

	/**
	 * @gpcsoft.property title="招标文件发放"
	 * @gpcsoft.validate class="required"
	 */
	public Date getEcpFormTenderDocDate() {
		return ecpFormTenderDocDate;
	}

	public void setEcpFormTenderDocDate(Date ecpFormTenderDocDate) {
		this.ecpFormTenderDocDate = ecpFormTenderDocDate;
	}

	/**
	 * @gpcsoft.property title="投标开始时间"
	 * @gpcsoft.validate class="required"
	 */
	public Date getEcpFormBiddingSDate() {
		return ecpFormBiddingSDate;
	}

	public void setEcpFormBiddingSDate(Date ecpFormBiddingSDate) {
		this.ecpFormBiddingSDate = ecpFormBiddingSDate;
	}

	/**
	 * @gpcsoft.property title="投标截止、开标"
	 * @gpcsoft.validate class="required"
	 */
	public Date getEcpFormBiddingEDate() {
		return ecpFormBiddingEDate;
	}

	public void setEcpFormBiddingEDate(Date ecpFormBiddingEDate) {
		this.ecpFormBiddingEDate = ecpFormBiddingEDate;
	}

	/**
	 * @gpcsoft.property title="中标通知书发出"
	 * @gpcsoft.validate class="required"
	 */
	public Date getEcpFormNoticeDate() {
		return ecpFormNoticeDate;
	}

	public void setEcpFormNoticeDate(Date ecpFormNoticeDate) {
		this.ecpFormNoticeDate = ecpFormNoticeDate;
	}

	/**
	 * @gpcsoft.property title="合同签订"
	 * @gpcsoft.validate class="required"
	 */
	public Date getEcpFormNoticDate() {
		return ecpFormNoticDate;
	}

	public void setEcpFormNoticDate(Date ecpFormNoticDate) {
		this.ecpFormNoticDate = ecpFormNoticDate;
	}

	public Set<ProjMember> getProjMembers() {
		return projMembers;
	}

	public void setProjMembers(Set<ProjMember> projMembers) {
		this.projMembers = projMembers;
	}

	public String getEbuyMethodCn() {
		this.ebuyMethodCn = EbuyMethodEnum.getEBuyMethodCN(this.getEbuyMethod());
		return this.ebuyMethodCn;
	}

	public void setEbuyMethodCn(String ebuyMethodCn) {
		this.ebuyMethodCn = ebuyMethodCn;
	}

	public String getRecFormBuyMethodCn() {
		this.recFormBuyMethodCn = RecordFormEnum.getRecFormBuyMethodCn(this.getRecFormBuyMethod());
		return this.recFormBuyMethodCn;
	}

	public void setRecFormBuyMethodCn(String recFormBuyMethodCn) {
		this.recFormBuyMethodCn = recFormBuyMethodCn;
	}

	public String getApproveUnit() {
		return approveUnit;
	}

	public void setApproveUnit(String approveUnit) {
		this.approveUnit = approveUnit;
	}

	public String getDesignUnit() {
		return designUnit;
	}

	public void setDesignUnit(String designUnit) {
		this.designUnit = designUnit;
	}

	public String getPerformProcedure() {
		return performProcedure;
	}

	public void setPerformProcedure(String performProcedure) {
		this.performProcedure = performProcedure;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @gpcsoft.property title="代理机构"
	 */
	public OrgInfo getAgency() {
		return agency;
	}

	public void setAgency(OrgInfo agency) {
		this.agency = agency;
	}
	public BigDecimal getBidQuote() {
		return bidQuote;
	}
	public void setBidQuote(BigDecimal bidQuote) {
		this.bidQuote = bidQuote;
	}

	public String getProjNature() {
		return projNature;
	}

	public void setProjNature(String projNature) {
		this.projNature = projNature;
	}

	public String getProjProperty() {
		return projProperty;
	}

	public void setProjProperty(String projProperty) {
		this.projProperty = projProperty;
	}

	public String getMoneySource() {
		return moneySource;
	}

	public void setMoneySource(String moneySource) {
		this.moneySource = moneySource;
	}

	public BigDecimal getInvestent() {
		return investent;
	}

	public void setInvestent(BigDecimal investent) {
		this.investent = investent;
	}

	public String getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(String totalArea) {
		this.totalArea = totalArea;
	}

	public String getSingleArea() {
		return singleArea;
	}

	public void setSingleArea(String singleArea) {
		this.singleArea = singleArea;
	}

	public BigDecimal getProjNumber() {
		return projNumber;
	}

	public void setProjNumber(BigDecimal projNumber) {
		this.projNumber = projNumber;
	}

	public BigDecimal getLayerOverRound() {
		return layerOverRound;
	}

	public void setLayerOverRound(BigDecimal layerOverRound) {
		this.layerOverRound = layerOverRound;
	}

	public BigDecimal getLayerUnderRound() {
		return layerUnderRound;
	}

	public void setLayerUnderRound(BigDecimal layerUnderRound) {
		this.layerUnderRound = layerUnderRound;
	}

	public BigDecimal getCornice() {
		return cornice;
	}

	public void setCornice(BigDecimal cornice) {
		this.cornice = cornice;
	}

	public BigDecimal getSpan() {
		return span;
	}

	public void setSpan(BigDecimal span) {
		this.span = span;
	}

	public String getLinkerName() {
		return linkerName;
	}

	public void setLinkerName(String linkerName) {
		this.linkerName = linkerName;
	}

	public String getLinkerTel() {
		return linkerTel;
	}

	public void setLinkerTel(String linkerTel) {
		this.linkerTel = linkerTel;
	}

	public String getUnitProperty() {
		return unitProperty;
	}

	public void setUnitProperty(String unitProperty) {
		this.unitProperty = unitProperty;
	}

	public String getUnitPropertyCN() {
		unitPropertyCN = UnitPropertyEnum.getUnitPropertyCN(this.getUnitProperty());
		return unitPropertyCN;
	}

	public void setUnitPropertyCN(String unitPropertyCN) {
		this.unitPropertyCN = unitPropertyCN;
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

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

}
