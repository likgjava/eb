package com.gpcsoft.epp.project.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.consign.domain.Consign;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.workgroup.domain.WorkGroup;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;
/** 
  *  Comments: <strong>Project</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   es                    					          
  *  <br/>Module ID: 项目表     		
  *  <br/>Create Date：2010-4-14 下午05:20:48 by guom    					                            
  *  <br/>Modified Date:  2010-4-14 下午05:20:48 by guom                                 
  *  @gpcsoft.package packageDir="com.gpcsoft.epp.project"
  *  @gpcsoft.page domain="planform/project" project="es"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="项目"  
  *  @since 0.1
  *  @version: 0.1 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_Tender_Project")
public class Project extends WorkFlowModel implements GpcBaseObject, IPropertyCUserTime{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "TenderID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; //主键
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="TASK_PLAN_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private TaskPlan taskPlan;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="AgreementID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Consign consign;//委托协议
	
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name="AgenciesID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private OrgInfo agencies;//代理机构	
	
	@Column(name = "BUYERS_ID")
	private String buyersId;//采购人ID[以“，”分隔]
	
	@Column(name = "BUYERS_NAME")
	private String buyersName;//采购人名称[以“，”分隔]
	
	@Column(name = "BUDGET_TOTAL_MONEY")
	private BigDecimal budgetTotalMoney;//预算总金额
	
	@Column(name = "PURCATEGORY_IDS")
	private String purCategoryIds;//对应品目Id[以“，”分隔]
	
	@Column(name = "PURCATEGORY_NAMES")
	private String purCategoryNames;//对应品目名称[以“，”分隔]

	@Column(name = "GOODS_CLASS_IDS")
	private String goodsClassIds;//对应商品分类Id[以“，”分隔]

	@Column(name = "GOODS_CLASS_NAMES")
	private String goodsClassNames;//对应商品分类名称[以“，”分隔]
	
	@Column(name = "TenderNo")
	private String projCode;//项目编号
	
	@Column(name = "TenderName")
	private String projName;//项目名称
	
	@Column(name = "TenderMethod", length = 2)
	private String ebuyMethod;//采购方式
	
	//服务费支付方式[业主单位支付、中标单位支付]
	@Transient
	private String serviceFeePayType;
	
	//服务费计算方式[固定金额、差额累计]
	@Transient
	private String serviceFeeCalculateType;
	
	//是否匿名投标
	@Transient
	private String  ruleAnonymous;
	
	//标书费购买记录
	@Transient
	private List list;

	//固定金额
	@Transient
	private BigDecimal fixAmount;
	
	@Transient
	private String ebuyMethodCN;
	
	@Column(name = "TENDERTYPE", length = 2)
	private String tenderType;//项目类型
	
	@Transient
	private String tenderTypeCN;
	
	@Column(name = "Summary", length = 500)
	private String projSummary;//项目摘要
	
	@Column(name = "MeetingRoom", length = 50)
	private String meetingRoomId;//标评室ID
	
	@Column(name = "Plan_Start_Date")
	@Temporal(TemporalType.DATE)
	private Date planStartDate;//计划开始时间

	@Column(name = "Plan_End_Date")
	@Temporal(TemporalType.DATE)
	private Date planEndDate;//计划结束时间
	
	@Column(name = "Start_Date")
	@Temporal(TemporalType.DATE)
	private Date startDate;//计划实际开始时间
	
	@Column(name = "End_Date")
	@Temporal(TemporalType.DATE)
	private Date endDate;//计划实际结束时间
	
	@Column(name = "Parent_ID", length = 50)
	private String parentId;//上级项目ID
	
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name="ManagerID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Employee manager;//项目负责人
	
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(
	name="MonitorID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Employee monitor;//项目监管人
	
	@Column(name = "ProcessStatus")
	private String projProcessStatus;//项目过程状态
	
	@Column(name = "tender_Process_Statuscn")
	private String projProcessStatusCN;//项目过程状态中文
	
	@Transient
	private String processPers;//项目进度百分比
	
	@Transient 
	private BigDecimal realCount; //分包总数量

	@Transient
	private BigDecimal realMoney;//分包总金额
	
	@Column(name = "implStatus", length = 2)
	private String projImplStatus;//项目实施状态[00：正常;01:暂停;02:终止;]
	
	@Transient
	private String projImplStatusCN;//项目实施状态
	
	@Column(name = "AuditStatus", length = 2)
	private String auditStatus;//项目审核状态

	@Column(name = "CONTENT")
	private String content;//招标内容

	@Column(name = "BAIL")
	private BigDecimal bail;//保证金金额
	
	@Column(name = "BAIL_PERCENT")
	private BigDecimal bailPercent; //保证金[百分比]

	@Column(name = "PUR_DOC_PRICE")
	private BigDecimal purDocPrice;//招标文件价格
	
	@Column(name="EVAL_METHOD")
	private String evalMethod;//评标方法
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CreUser", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User createUser;

    //创建时间
    @Column(name = "CreDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Column(name = "Template_ID", length = 50)
	private String templateId;//计划模版Id

	@Column(name = "UseStatus", length = 2)
	private String useStatus;//使用状态
	
	@Column(name = "TENDER_SYS_FLAG")
	private String tenderSysFlag;//系统标识
	
	@Column(name = "REDUNDANCY1")
	private String redundancy1;//冗余字段1
	
	@Column(name = "REDUNDANCY2")
	private String redundancy2;//冗余字段2
	
	@Column(name = "REDUNDANCY3")
	private String redundancy3;//冗余字段3
	
	@Transient
	private String useStatusCN;//使用状态
	
	//modify by xiaojf
	@LazyCollection(value=LazyCollectionOption.EXTRA)
    @OneToMany
    @BatchSize(size=10)
    @JoinColumn(name = "Parent_ID")
    @Cascade({CascadeType.ALL})
    private Set<Project> subProject;
	
	@Transient
	private String isDividePack;//是否分包[1:已经分包,2:没有分包,3:规则不分包]

	@Transient
	private String projectIsComplete;//检查项目完整性[1:完整,2:不完整]
	
	@Transient
	private String checkProjectIsSub;//检查项目完整性[1:完整,2:不完整]
	
	@Transient
	private ProjectCountView projectCountView;//招标项目统计视图
	
	@Column(name = "SIGN_UP_START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date signUpSTime;
	
	@Column(name = "SIGN_UP_END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date signUpETime;
	
	@Transient
	private Date tenderStartTime;
	
	@Transient	
	private Date tenderEndTime;
	
	@Column(name = "EVAL_START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date evalStartTime;
	
	@Column(name = "EVAL_END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date evalEndTime;
	
	@Transient
	private Date openBidStartDate;

	@Transient
	private String openBidAddr;
	
	@Transient
	private Date bailEndDate;//交纳保证金截止时间
	
	//modify by wangcl
	@Transient
	private WorkGroup workGroup;//临时保存某类工作组
	
	//modify by liuke
	/**当前有效id*/
	@Column(name = "CURRENT_VALID_ID")
	private String currentId;
	
	@Column(name="SYS_FLAG")
	private String sysFlag;//所属系统标识
	
	@Column(name="ZHAOBIAO_AREA")
	private String zhaobiaoArea;//建筑工程招标范围
	@Transient
	private BigDecimal quantity;
	
	@Transient
	private Bulletin bidEvaluationReport;  //评审报告
	
	@Transient
	private String isConfigBuyResult;   //是否已经定标
	
	@Transient
	private String winnerNames;   //中标供应商名称
	
	@Column(name = "TENDER_KEEP_ON_R")
	private String tenderRecord;//备案状态[00：未备案，01：已备案]
	
	@Column(name="CENTRAL_INVESTMENT_MONEY")
	private BigDecimal centralInvestmentMoney;//中央投标保证金（当预算类型为中央投资时,此字段有值。为非中央投资时，此字段值为空）
	
	@Column(name = "RESPROJECT_ID")
	private String resProjectId;//源项目ID
	
	/********************************GET和SET方法**********************************************/
	public String getCurrentId() {
		return currentId;
	}
	

	public String getEvalMethod() {
		return evalMethod;
	}


	public BigDecimal getBailPercent() {
		return bailPercent;
	}


	public void setBailPercent(BigDecimal bailPercent) {
		this.bailPercent = bailPercent;
	}


	public void setEvalMethod(String evalMethod) {
		this.evalMethod = evalMethod;
	}


	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}
	/**
	 * @gpcsoft.property title="采购方式"
	 */
	public String getEbuyMethodCN() {
		this.ebuyMethodCN = EbuyMethodEnum.getEBuyMethodCN(this.getEbuyMethod());
		return this.ebuyMethodCN;
	}
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public BigDecimal getFixAmount() {
		return fixAmount;
	}

	/**
	 * @gpcsoft.property title="固定金额"
	 */
	public void setFixAmount(BigDecimal fixAmount) {
		this.fixAmount = fixAmount;
	}
	
	/**
	 * @gpcsoft.property title="服务费支付方式"
	 */
	public String getServiceFeePayType() {
		return serviceFeePayType;
	}

	public void setServiceFeePayType(String serviceFeePayType) {
		this.serviceFeePayType = serviceFeePayType;
	}
	
	/**
	 * @gpcsoft.property title="服务费计算方式"
	 */
	public String getServiceFeeCalculateType() {
		return serviceFeeCalculateType;
	}

	public void setServiceFeeCalculateType(String serviceFeeCalculateType) {
		this.serviceFeeCalculateType = serviceFeeCalculateType;
	}

	/**
	 * @gpcsoft.property title="项目类型"
	 */
	public String getTenderType() {
		return tenderType;
	}

	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}
	
	
	/**
	 * @gpcsoft.property title="委托协议"
	 */
	public Consign getConsign() {
		return consign;
	}

	public void setConsign(Consign consign) {
		this.consign = consign;
	}

	/**
	 * @gpcsoft.property title="代理机构"
	 */
	public OrgInfo getAgencies() {
		return agencies;
	}

	public Date getSignUpSTime() {
		return signUpSTime;
	}

	public void setSignUpSTime(Date signUpSTime) {
		this.signUpSTime = signUpSTime;
	}

	public Date getSignUpETime() {
		return signUpETime;
	}

	public void setSignUpETime(Date signUpETime) {
		this.signUpETime = signUpETime;
	}

	public Date getTenderStartTime() {
		return tenderStartTime;
	}

	public void setTenderStartTime(Date tenderStartTime) {
		this.tenderStartTime = tenderStartTime;
	}

	public Date getTenderEndTime() {
		return tenderEndTime;
	}

	public void setTenderEndTime(Date tenderEndTime) {
		this.tenderEndTime = tenderEndTime;
	}

	public Date getEvalStartTime() {
		return evalStartTime;
	}

	public void setEvalStartTime(Date evalStartTime) {
		this.evalStartTime = evalStartTime;
	}

	public Date getEvalEndTime() {
		return evalEndTime;
	}

	public void setEvalEndTime(Date evalEndTime) {
		this.evalEndTime = evalEndTime;
	}

	public Date getOpenBidStartDate() {
		return openBidStartDate;
	}

	public void setOpenBidStartDate(Date openBidStartDate) {
		this.openBidStartDate = openBidStartDate;
	}

	public String getOpenBidAddr() {
		return openBidAddr;
	}

	public void setOpenBidAddr(String openBidAddr) {
		this.openBidAddr = openBidAddr;
	}

	public void setAgencies(OrgInfo agencies) {
		this.agencies = agencies;
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
	 * @gpcsoft.property title="项目名称"
	 */
	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	/**
	 * @gpcsoft.property title="采购方式"
	 */
	public String getEbuyMethod() {
		return ebuyMethod;
	}

	public void setEbuyMethod(String ebuyMethod) {
		this.ebuyMethod = ebuyMethod;
	}

	/**
	 * @gpcsoft.property title="项目摘要"
	 */
	public String getProjSummary() {
		return projSummary;
	}

	public void setProjSummary(String projSummary) {
		this.projSummary = projSummary;
	}
	/**
	 * @gpcsoft.property title="预订评标室"
	 */
	public String getMeetingRoomId() {
		return meetingRoomId;
	}

	public void setMeetingRoomId(String meetingRoomId) {
		this.meetingRoomId = meetingRoomId;
	}

	/**
	 * @gpcsoft.property title="计划开始时间"
	 */
	public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	/**
	 * @gpcsoft.property title="计划结束时间"
	 */
	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	/**
	 * @gpcsoft.property title="实际开始时间"
	 */
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @gpcsoft.property title="实际结束时间"
	 */
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @gpcsoft.property title="项目负责人"
	 */
	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	/**
	 * @gpcsoft.property title="项目监管人"
	 */
	public Employee getMonitor() {
		return monitor;
	}

	public void setMonitor(Employee monitor) {
		this.monitor = monitor;
	}

	/**
	 * @gpcsoft.property title="项目过程状态"
	 */
	public String getProjProcessStatus() {
		return projProcessStatus;
	}

	public void setProjProcessStatus(String projProcessStatus) {
		this.projProcessStatus = projProcessStatus;
	}

	/**
	 * @gpcsoft.property title="审核状态"
	 */
	public String getAuditStatus() {
		return auditStatus;//
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
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
	 * @gpcsoft.property title="计划模版"
	 */
	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
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

	
	public void setEbuyMethodCN(String ebuyMethodCN) {
		this.ebuyMethodCN = ebuyMethodCN;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Project other = (Project) obj;

        if (objId != null && other.objId != null) {
            return objId.equals(other.objId);
        }
        if (projCode != null && other.projCode != null) {
            return projCode.equals(other.projCode);
        }
        return true;
	}

	@Override
	public int hashCode() {
		int result; 
		result = (projCode==null?0:projCode.hashCode()); 
		result = 29*result + (projName==null?0:projName.hashCode()); 
		return result; 
	}

	public Set<Project> getSubProject() {
		return subProject;
	}

	public void setSubProject(Set<Project> subProject) {
		this.subProject = subProject;
	}
	
	
	public TaskPlan getTaskPlan() {
		return taskPlan;
	}

	public void setTaskPlan(TaskPlan taskPlan) {
		this.taskPlan = taskPlan;
	}

	
	/**
	 * @gpcsoft.property title="使用状态"
	 */
	public String getUseStatusCN() {
		this.useStatusCN = CommonEnum.getUseCN(this.getUseStatus());
		return this.useStatusCN;
	}

	public void setUseStatusCN(String useStatusCN) {
		this.useStatusCN = useStatusCN;
	}

	/**
	 * @gpcsoft.property title="项目过程状态"
	 */
	public String getProjImplStatus() {
		return projImplStatus;
	}

	public String getProjProcessStatusCN() {
		return this.projProcessStatusCN;
	}


	public void setProjProcessStatusCN(String projProcessStatusCN) {
		this.projProcessStatusCN = projProcessStatusCN;
	}


	public void setProjImplStatus(String projImplStatus) {
		this.projImplStatus = projImplStatus;
	}

	/**
	 * @gpcsoft.property title="项目过程状态"
	 */
	public String getProjImplStatusCN() {
		this.projImplStatusCN = ProjImplStatusEnum.getCN(this.getProjProcessStatus());
		return this.projImplStatusCN;
	}

	public void setProjImplStatusCN(String projImplStatusCN) {
		this.projImplStatusCN = projImplStatusCN;
	}

	/**
	 * @gpcsoft.property title="项目进度"
	 */
	public String getProcessPers() {
		return processPers;
	}

	public void setProcessPers(String processPers) {
		this.processPers = processPers;
	}

	public ProjectCountView getProjectCountView() {
		return projectCountView;
	}

	public void setProjectCountView(ProjectCountView projectCountView) {
		this.projectCountView = projectCountView;
	}


	public String getBuyersId() {
		return buyersId;
	}

	public void setBuyersId(String buyersId) {
		this.buyersId = buyersId;
	}

	public String getBuyersName() {
		return buyersName;
	}

	public void setBuyersName(String buyersName) {
		this.buyersName = buyersName;
	}

	public BigDecimal getBudgetTotalMoney() {
		return budgetTotalMoney;
	}

	public void setBudgetTotalMoney(BigDecimal budgetTotalMoney) {
		this.budgetTotalMoney = budgetTotalMoney;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getBail() {
		return bail;
	}

	public void setBail(BigDecimal bail) {
		this.bail = bail;
	}

	public BigDecimal getPurDocPrice() {
		return purDocPrice;
	}

	public void setPurDocPrice(BigDecimal purDocPrice) {
		this.purDocPrice = purDocPrice;
	}

	public String getProjectIsComplete() {
		return projectIsComplete;
	}

	public void setProjectIsComplete(String projectIsComplete) {
		this.projectIsComplete = projectIsComplete;
	}

	public String getCheckProjectIsSub() {
		return checkProjectIsSub;
	}

	public void setCheckProjectIsSub(String checkProjectIsSub) {
		this.checkProjectIsSub = checkProjectIsSub;
	}

	public String getIsDividePack() {
		return isDividePack;
	}

	public void setIsDividePack(String isDividePack) {
		this.isDividePack = isDividePack;
	}

	public String getPurCategoryIds() {
		return purCategoryIds;
	}

	public void setPurCategoryIds(String purCategoryIds) {
		this.purCategoryIds = purCategoryIds;
	}

	/**
	 * @gpcsoft.property title="品目"
	 */
	public String getPurCategoryNames() {
		return purCategoryNames;
	}

	public void setPurCategoryNames(String purCategoryNames) {
		this.purCategoryNames = purCategoryNames;
	}

	public String getGoodsClassIds() {
		return goodsClassIds;
	}

	public void setGoodsClassIds(String goodsClassIds) {
		this.goodsClassIds = goodsClassIds;
	}

	/**
	 * @gpcsoft.property title="商品分类"
	 */
	public String getGoodsClassNames() {
		return goodsClassNames;
	}

	public void setGoodsClassNames(String goodsClassNames) {
		this.goodsClassNames = goodsClassNames;
	}

	
	/**
	 * @gpcsoft.property title="项目类型"
	 */
	public String getTenderTypeCN() {
		this.tenderTypeCN = ProjectEnum.getCN(this.getTenderType());
		return this.tenderTypeCN;
	}

	public void setTenderTypeCN(String tenderTypeCN) {
		this.tenderTypeCN = tenderTypeCN;
	}

	public String getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}

	public String getTenderSysFlag() {
		return tenderSysFlag;
	}

	public void setTenderSysFlag(String tenderSysFlag) {
		this.tenderSysFlag = tenderSysFlag;
	}

	public String getRedundancy1() {
		return redundancy1;
	}

	public void setRedundancy1(String redundancy1) {
		this.redundancy1 = redundancy1;
	}

	public String getRedundancy2() {
		return redundancy2;
	}

	public void setRedundancy2(String redundancy2) {
		this.redundancy2 = redundancy2;
	}

	public String getRedundancy3() {
		return redundancy3;
	}

	public void setRedundancy3(String redundancy3) {
		this.redundancy3 = redundancy3;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public BigDecimal getRealCount() {
		return realCount;
	}

	public void setRealCount(BigDecimal realCount) {
		this.realCount = realCount;
	}

	public BigDecimal getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(BigDecimal realMoney) {
		this.realMoney = realMoney;
	}

	public Date getBailEndDate() {
		return bailEndDate;
	}

	public void setBailEndDate(Date bailEndDate) {
		this.bailEndDate = bailEndDate;
	}

	public WorkGroup getWorkGroup() {
		return workGroup;
	}

	public void setWorkGroup(WorkGroup workGroup) {
		this.workGroup = workGroup;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
	
	public Bulletin getBidEvaluationReport() {
		return bidEvaluationReport;
	}


	public void setBidEvaluationReport(Bulletin bidEvaluationReport) {
		this.bidEvaluationReport = bidEvaluationReport;
	}


	public String getIsConfigBuyResult() {
		return isConfigBuyResult;
	}


	public void setIsConfigBuyResult(String isConfigBuyResult) {
		this.isConfigBuyResult = isConfigBuyResult;
	}
	
	public String getWinnerNames() {
		return winnerNames;
	}

	public void setWinnerNames(String winnerNames) {
		this.winnerNames = winnerNames;
	}


	public String getRuleAnonymous() {
		return ruleAnonymous;
	}


	public void setRuleAnonymous(String ruleAnonymous) {
		this.ruleAnonymous = ruleAnonymous;
	}

	public String getTenderRecord() {
		return tenderRecord;
	}


	public void setTenderRecord(String tenderRecord) {
		this.tenderRecord = tenderRecord;
	}


	public BigDecimal getCentralInvestmentMoney() {
		return centralInvestmentMoney;
	}


	public void setCentralInvestmentMoney(BigDecimal centralInvestmentMoney) {
		this.centralInvestmentMoney = centralInvestmentMoney;
	}

	public String getResProjectId() {
		return resProjectId;
	}

	public void setResProjectId(String resProjectId) {
		this.resProjectId = resProjectId;
	}
		public String getZhaobiaoArea() {
		return zhaobiaoArea;
	}

	public void setZhaobiaoArea(String zhaobiaoArea) {
		this.zhaobiaoArea = zhaobiaoArea;
	}
}
