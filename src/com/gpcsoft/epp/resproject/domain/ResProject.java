package com.gpcsoft.epp.resproject.domain;

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
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.taskplan.domain.RecordForm;
import com.gpcsoft.epp.taskplan.domain.RecordFormEnum;
import com.gpcsoft.epp.taskplan.domain.UnitPropertyEnum;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>ResProject</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Module ID: 执行平台    		
  *  <br/>Create Date：2011-12-21 下午08:21:16 by yangx    					                            
  *  <br/>Modified Date:  2011-12-21 下午08:21:16 by yangx                                   
  *	  @since 3.5
  *   @version: 3.5 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PROJECT")
public class ResProject extends WorkFlowModel implements GpcBaseObject, IPropertyCUserTime, IPropertyUUserTime{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private static final String ISVOTING_NO = "00";
	private static final String ISVOTING_YES = "01";

	@Id
	@Column(name = "PROJECT_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;                 //主键                        
	
	@Column(name="PROJECT_NO",length=50)
	private String projectNo;             //项目编码       
	
	@Column(name="PROJECT_NAME",length=40)
	private String projectName;           //项目名称
	
	@Column(name="PROJECT_TYPE",length=2)
	private String projectType;           //项目属性(00:基础设施、01:公用事业) 
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="Parent_ID", updatable = false)	 
	@BatchSize(size = 15)
	private ResProject parent;              //父ID
	
	@Column(name="USE_STATUS",length=2)
	private String useStatus;             //使用状态[00:临时,01:正式,02作废]
	
	@Column(name="PROJECT_STATUS",length=2)
	private String projectStatus;         //项目的状态00:未开始 01 正在进行 ,02 结束
	
	@Column(name = "PLAN_START_DATE")
	@Temporal(TemporalType.DATE)
	private Date planStartDate;         //计划开时间
	
	@Column(name = "PLAN_END_DATE")
	@Temporal(TemporalType.DATE)
	private Date planEndDate;           //计划结束时间 
	
	@Column(name="REMARK")
	private String remark;                //备注
	
	@Column(name="AMT")
	private BigDecimal amt;               //金额
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATE_USER_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User createUser;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
	private Date createTime;//创建时间
    //修改人
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="UPDATE_USER_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User updateUser;
    
    //修改时间
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date updateTime;
	
	@Column(name="TASK_PLAN_ID")
	private String taskPlanId;            //申报书ID[冗余]如果有多个使用,逗号分割 
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="AGENTY_ID")
	private OrgInfo agenty;              //代理机构
	
	@Column(name="AGENTY_NAME")
	private String agentyName;            //代理机构名称 
	
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name="AGENTY_LEADER_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Employee agentyLeader;//代理机构项目负责人(代理机构联系人)
	
	@Column(name="AGENTY_LINKER")
	private String agentyLinker;          //代理机构负责人名称
	
	@Column(name="AGENTY_LINKER_TEL")
	private String agentyLinkerTel;       //代理机构负责人联系电话
	
	@Column(name="AGENTY_LINKER_FAX")
	private String agentyLinkerFax;       //代理机构传真 
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="BUDGET_ID" )
	@BatchSize(size = 15)
	private OrgInfo budget;              //业主单位
	
	@Column(name="BUDGET_NAME")
	private String budgetName;            //业主单位名称
	
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name="BUDGET_LEARDER_ID")	 
	@BatchSize(size = 15)
	private Employee budgetLeader;       //业主单位项目负责人(招标人联系人)
	
	@Column(name="BUDGET_LINKER")
	private String budgetLinker;          //业主单位联系人

	@Column(name="BUDGET_LINETEL")
	private String budgetLinkerTel;         //业主单位联系电话

	@Column(name="BUDGET_FAX")
	private String budgetFax;             //业主单位传真

	@Column(name="CONSTRUCT_SCALE")
	private String constructScale;        //建设规模

	@Column(name="ATTACH_RALAID")
	private String attachRalaid;          //附件

	@Column(name="PROJECT_ADDRESS")
	private String projectAddress;        //工程地点 
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="COMMISSIONED_UNIT_ID", updatable = false)
	@BatchSize(size = 15)
	private OrgInfo commissionedUnit;      //委托单位

	@Column(name="COMMISSIONED_UNIT_NAME")
	private String commissionedUnitName;  //委托单位名称

	@Column(name="COMMISSIONED_UNIT_LINKER")
	private String commissionedUnitLinker;//委托单位联系人 

	@Column(name="COMMISSIONED_UNIT_TEL")
	private String commissionedUnitTel;   //委托单位联系电话
	
	/** 主管单位 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="DEPART_ID", updatable = false)
	@BatchSize(size = 15)
	private Company department;
	
	@Column(name = "DEPART_NAME")
	private String departmentName;//主管单位名称
	
	@Column(name = "DEPARTMENT_LINKER")
	private String departmentLinker;//主管单位联系人
	
	@Column(name = "DEPARTMENT_LINKER_TEL")
	private String departmentLinkerTel;//主管单位联系电话
	
	@Column(name="MONEY_MODE")
	private String moneyModel;       //资金使用方式[00:直接投资,01:资金注入,02:投资补贴,03:贷款贴息,04:转贷]有多种使用,分隔
	
	@Column(name="IS_LEAF")
	private String isLeaf;
	
	@Column(name="MONEY_SOURCE")
	private String moneySource;//资金来源
	
	@Column(name = "PLAN_APPROVAL")   
	private String projApproval;//计划批文
	
	@Column(name="PLANNING_PERMIT")
	private String planPermit;//规划许可证
	
	@Column(name="SINGLE_AREA_VAR")
	private String singleArea;//单个建筑面积(m2)平方米 
	
	@Column(name="TOTAL_AREA_VAR")
	private String totalArea;//建筑总面积(m2) 
	
	@Column(name="PRO_NUMBER")
	private BigDecimal proNumber;//工程个数
	
	@Column(name="STRUCT_STYLE")
	private String structStyle;//结构形式
	
	@Column(name="NUM_LAYER_OVERGROUND")
	private BigDecimal layerOverGround;//层数地上 
	
	@Column(name="NUM_LAYER_UNDERGROUND")
	private BigDecimal layerUnderGround;//层数地下
	
	@Column(name="CORNICE")
	private BigDecimal cornice;//檐口高度(m) 
	
	@Column(name="SPAN")
	private BigDecimal span;//跨度(m) 
	
	@Column(name="BID_INVI_STYLE")
	private String bidInviStyle;//招标形式(00:自主招标 01,委托招标) 
	
	@Transient
	private String bidInviStyleCN;
	
	
	
	@Column(name="EBUY_METHOD")
	private String ebuyMethod;//招标方式
	
	@Transient
	private String ebuyMethodCn;//招标方式 中文
	
	@Column(name="PROJECT_NATURE")
	private String projNature;//项目性质(00:新建、01:扩建、02:改建)
	
	@Transient
	private String projNatureCN;
	
	
    @ManyToOne(fetch=FetchType.EAGER,optional=true)
	@JoinColumn(name="REC_FORM_ID")
	private RecordForm recordForm;//备案号ID

    @Column(name="IS_VOTING")
    private String isVoting;//是否比选
	
    @Column(name = "UNIT_PROPERTY")
	private String unitProperty;//	建设单位性质,招标人性质填“国有”、“民营”、“外商独资”、“中外合资”、“行政机关”、“事业单位”；

	@Transient
	private String unitPropertyCN;
    
	@Column(name="AUDIT_STATUS")
	private String auditStatus;
	
	@Column(name="CONTRAT_PRICE")
	private BigDecimal contratPrice;
	
	@Column(name="LAND_NO")
	private String landNo;//土地证号
	
	@Column(name="ENG_PROJECT_TYPE")
	private String engProjType;//建设工程分类[00:房屋建筑工程,01:市政公用设施]

	@Transient
	private String engProjTypeCN;  //项目类型
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public ResProject getParent() {
		return parent;
	}

	public void setParent(ResProject parent) {
		this.parent = parent;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
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
	

	public String getTaskPlanId() {
		return taskPlanId;
	}

	public void setTaskPlanId(String taskPlanId) {
		this.taskPlanId = taskPlanId;
	}

	public OrgInfo getAgenty() {
		return agenty;
	}

	public void setAgenty(OrgInfo agenty) {
		this.agenty = agenty;
	}

	public String getAgentyName() {
		return agentyName;
	}

	public void setAgentyName(String agentyName) {
		this.agentyName = agentyName;
	}

	public Employee getAgentyLeader() {
		return agentyLeader;
	}

	public void setAgentyLeader(Employee agentyLeader) {
		this.agentyLeader = agentyLeader;
	}

	public String getAgentyLinker() {
		return agentyLinker;
	}

	public void setAgentyLinker(String agentyLinker) {
		this.agentyLinker = agentyLinker;
	}

	public String getAgentyLinkerTel() {
		return agentyLinkerTel;
	}

	public void setAgentyLinkerTel(String agentyLinkerTel) {
		this.agentyLinkerTel = agentyLinkerTel;
	}

	public String getAgentyLinkerFax() {
		return agentyLinkerFax;
	}

	public void setAgentyLinkerFax(String agentyLinkerFax) {
		this.agentyLinkerFax = agentyLinkerFax;
	}

	public OrgInfo getBudget() {
		return budget;
	}

	public void setBudget(OrgInfo budget) {
		this.budget = budget;
	}

	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	public Employee getBudgetLeader() {
		return budgetLeader;
	}

	public void setBudgetLeader(Employee budgetLeader) {
		this.budgetLeader = budgetLeader;
	}

	public String getBudgetLinker() {
		return budgetLinker;
	}

	public void setBudgetLinker(String budgetLinker) {
		this.budgetLinker = budgetLinker;
	}

	public String getBudgetLinkerTel() {
		return budgetLinkerTel;
	}

	public void setBudgetLinkerTel(String budgetLinkerTel) {
		this.budgetLinkerTel = budgetLinkerTel;
	}

	public String getBudgetFax() {
		return budgetFax;
	}

	public void setBudgetFax(String budgetFax) {
		this.budgetFax = budgetFax;
	}

	public String getConstructScale() {
		return constructScale;
	}

	public void setConstructScale(String constructScale) {
		this.constructScale = constructScale;
	}

	public String getAttachRalaid() {
		return attachRalaid;
	}

	public void setAttachRalaid(String attachRalaid) {
		this.attachRalaid = attachRalaid;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public OrgInfo getCommissionedUnit() {
		return commissionedUnit;
	}

	public void setCommissionedUnit(OrgInfo commissionedUnit) {
		this.commissionedUnit = commissionedUnit;
	}

	public String getCommissionedUnitName() {
		return commissionedUnitName;
	}

	public void setCommissionedUnitName(String commissionedUnitName) {
		this.commissionedUnitName = commissionedUnitName;
	}

	public String getCommissionedUnitLinker() {
		return commissionedUnitLinker;
	}

	public void setCommissionedUnitLinker(String commissionedUnitLinker) {
		this.commissionedUnitLinker = commissionedUnitLinker;
	}

	public String getCommissionedUnitTel() {
		return commissionedUnitTel;
	}

	public void setCommissionedUnitTel(String commissionedUnitTel) {
		this.commissionedUnitTel = commissionedUnitTel;
	}

	public Company getDepartment() {
		return department;
	}

	public void setDepartment(Company department) {
		this.department = department;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentLinker() {
		return departmentLinker;
	}

	public void setDepartmentLinker(String departmentLinker) {
		this.departmentLinker = departmentLinker;
	}

	public String getDepartmentLinkerTel() {
		return departmentLinkerTel;
	}

	public void setDepartmentLinkerTel(String departmentLinkerTel) {
		this.departmentLinkerTel = departmentLinkerTel;
	}

	public String getMoneyModel() {
		return moneyModel;
	}

	public void setMoneyModel(String moneyModel) {
		this.moneyModel = moneyModel;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getMoneySource() {
		return moneySource;
	}

	public void setMoneySource(String moneySource) {
		this.moneySource = moneySource;
	}

	public String getPlanPermit() {
		return planPermit;
	}

	public void setPlanPermit(String planPermit) {
		this.planPermit = planPermit;
	}

	public String getSingleArea() {
		return singleArea;
	}

	public void setSingleArea(String singleArea) {
		this.singleArea = singleArea;
	}

	public String getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(String totalArea) {
		this.totalArea = totalArea;
	}

	public BigDecimal getProNumber() {
		return proNumber;
	}

	public void setProNumber(BigDecimal proNumber) {
		this.proNumber = proNumber;
	}

	public String getStructStyle() {
		return structStyle;
	}

	public void setStructStyle(String structStyle) {
		this.structStyle = structStyle;
	}

	public BigDecimal getLayerOverGround() {
		return layerOverGround;
	}

	public void setLayerOverGround(BigDecimal layerOverGround) {
		this.layerOverGround = layerOverGround;
	}

	public BigDecimal getLayerUnderGround() {
		return layerUnderGround;
	}

	public void setLayerUnderGround(BigDecimal layerUnderGround) {
		this.layerUnderGround = layerUnderGround;
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

	public String getBidInviStyle() {
		return bidInviStyle;
	}

	public void setBidInviStyle(String bidInviStyle) {
		this.bidInviStyle = bidInviStyle;
	}

	public String getEbuyMethod() {
		return ebuyMethod;
	}

	public void setEbuyMethod(String ebuyMethod) {
		this.ebuyMethod = ebuyMethod;
	}

	public String getProjNature() {
		return projNature;
	}

	public void setProjNature(String projNature) {
		this.projNature = projNature;
	}

	public RecordForm getRecordForm() {
		return recordForm;
	}

	public void setRecordForm(RecordForm recordForm) {
		this.recordForm = recordForm;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIsVoting() {
		return isVoting;
	}

	public void setIsVoting(String isVoting) {
		this.isVoting = isVoting;
	}

	public String getProjApproval() {
		return projApproval;
	}

	public void setProjApproval(String projApproval) {
		this.projApproval = projApproval;
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

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public BigDecimal getContratPrice() {
		return contratPrice;
	}

	public void setContratPrice(BigDecimal contratPrice) {
		this.contratPrice = contratPrice;
	}

	public String getLandNo() {
		return landNo;
	}

	public void setLandNo(String landNo) {
		this.landNo = landNo;
	}

	public String getEngProjType() {
		return engProjType;
	}

	public void setEngProjType(String engProjType) {
		this.engProjType = engProjType;
	}

	public String getEngProjTypeCN() {
		engProjTypeCN = EngProjectTypeEnum.getEngProjectTypeCn(this.getEngProjType());
		return engProjTypeCN;
	}

	public void setEngProjTypeCN(String engProjTypeCN) {
		this.engProjTypeCN = engProjTypeCN;
	}

	public String getProjNatureCN() {
		projNatureCN=EngProjectTypeEnum.getProjectNatureCn(this.getProjNature());
		return projNatureCN;
	}

	public void setProjNatureCN(String projNatureCN) {
		this.projNatureCN = projNatureCN;
	}

	public String getBidInviStyleCN() {
		bidInviStyleCN=RecordFormEnum.getRecFormBuyMethodCn(this.getBidInviStyle());
		return bidInviStyleCN;
	}

	public void setBidInviStyleCN(String bidInviStyleCN) {
		this.bidInviStyleCN = bidInviStyleCN;
	}
	
	public String getEbuyMethodCn() {
		this.ebuyMethodCn = EbuyMethodEnum.getEBuyMethodCN(this.getEbuyMethod());
		return this.ebuyMethodCn;
	}

	public void setEbuyMethodCn(String ebuyMethodCn) {
		this.ebuyMethodCn = ebuyMethodCn;
	}

	
}
