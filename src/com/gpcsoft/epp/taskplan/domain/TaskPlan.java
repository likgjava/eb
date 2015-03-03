package com.gpcsoft.epp.taskplan.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Department;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;


/** 
  *  Comments: <strong>TaskPlan</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   es                    					          
  *  <br/>Module ID: 采购计划     		
  *  <br/>Create Date：2010-4-14 下午12:52:38 by guom    					                            
  *  <br/>Modified Date:  2010-4-14 下午12:52:38 by guom                                 
  *      
  *  @since 0.1
  *  @version: 0.1 
  *  
  *  @gpcsoft.package packageDir="com.gpcsoft.epp.taskplan"
  *  @gpcsoft.page domain="planform/taskplan" project="es"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="采购计划"
  */


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@OrderProperty(property="taskCode", flag="true")
@Table(name = "ECP_TASK_PLAN")
public class TaskPlan extends WorkFlowModel implements GpcBaseObject, IPropertyCUserTime, IPropertyUUserTime{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	public static final String CONFIRM_STATUS_PASS = "06";//审核代理机构通过
	public static final String CONFIRM_STATUS_NO_PASS = "05";//审核代理机构不通过

	
	@Id
	@Column(name = "TASK_PLAN_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;//主键
	
	
	@Column(name = "TASK_PLAN_CODE")
	private String taskCode;//申报书编号
	
	@Column(name = "TASK_PLAN_NAME")
	private String taskName;//申报书名称
	
	@Column(name = "TASK_PLAN_TYPE")
	private String taskType;//申报书类型

	@Column(name = "BLOCK_CHECK_STATUS")
	private String block_check_status;//大宗审核状态

	@Transient
	private String taskTypeCN;

	@Column(name = "TASK_PLAN_ACCTYEAR")
	@Temporal(TemporalType.TIMESTAMP)
	private Date acctYear;//申报书年度
	
	@Column(name = "TASK_PLAN_APPLYDATE")
	@Temporal(TemporalType.DATE)
	private Date applyDate;//申请日期
	
	@Column(name = "TASK_PLAN_FINISHDATE")
	@Temporal(TemporalType.DATE)
	private Date finishDate;//期望完成时间
	
	@Column(name = "TASK_PLAN_AMT")
	private BigDecimal amt; //任务金额
	
	@Column(name = "TASK_PLAN_ORGTYPE")
	private String orgType;//组织形式
	
	@Column(name = "EBUY_METHOD")
	private String ebuyMethod;//采购方式
	
	@Transient
	private String ebuyMethodCN;
	
	@Column(name = "TASK_PLAN_IS_CHECK")
	private Boolean checkTaskPlan;//是否主管部门审核
	
	@Column(name = "TASK_PLAN_SENDTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendTime;//申报书发送时间
	
	@Column(name = "REMARK")
	private String remark;//备注
	
	@Column(name = "TASK_LAST_CONTRACTCODE")
	private String lastContractCode;//申报书原合同编号
	
	/** 业主单位项目 项目负责人*/
    @ManyToOne(fetch=FetchType.EAGER,optional=true)
	@JoinColumn(name="LEADER")
	private Employee leader;
	
    /** 业主单位项目负责人姓名[冗余]*/
    @Column(name = "LEADER_NAME")
	private String leaderName;
    
    @Column(name = "DRAW_TYPE")
	private String drawType;//代理机构抽取类型：00随机抽取  ，01单项选择
    
	@Column(name = "USE_STATUS")
	private String useStatus;//使用状态
	
	@Transient
    private String useStatusCN;
	
	@Column(name = "PROCESS_STATUS")
	private String processStatus;//进程状态:00 未开始,01 正在招标 02 已完成
	
	@Transient
    private String processStatusCN;
	
    @Column(name = "CONFIRM_STATUS")
    private String confirmStatus;//审核状态
    
    @Transient
    private String confirmStatusCN;
    
    @Column(name = "AUDIT_DETAIL")
    private String auditDetail; //审核资金明细状态
    
    @Transient
    private String auditDetailCN;
    
    /** 代理机构*/
    @ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="AGENTY_ID")
	private OrgInfo taskAgent; //代理机构
    
    @Column(name="AGENTY_NAME")
	private String taskAgentName; //代理机构名称
    
    @Column(name="AGENTY_LEADER")
	private String agentLeader; //代理机构项目负责人
    
    @Column(name="AGENTY_LEADER_TEL")
	private String agentLeaderTel; //代理机构联系电话

	/** 主管单位 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="DEPART_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Company department;
	
	@Column(name = "DEPART_NAME")
	private String departmentName;//主管单位名称
	
	@Column(name = "DEPARTMENT_LINKER")
	private String departmentLinker;//主管单位联系人
	
	@Column(name = "DEPARTMENT_LINKER_TEL")
	private String departmentLinkerTel;//主管单位联系电话
	
	
	/** 业务处室 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="GOVERNMENT_ID",updatable=false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Department government;
	
	@Column(name = "GOVERNMENT_NAME")
	private String governmentName;//业务处室名称

	@Column(name = "GOV_LINKER")
	private String govLinker;//业务处室联系人
	
	@Column(name = "GOV_LINKER_TEL")
	private String govLinkerTel;//业务处室联系电话
	
	
	/** 预算单位..  业主单位  招标人*/
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="BUDGET_ID" )//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private OrgInfo budget;
	
	@Column(name = "BUDGET_NAME")
	private String budgetName;//预算单位名称
	
	@Column(name = "BUDGET_LINKER")
	private String budgetLinker;//预算单位联系人
	
	@Column(name = "BUDGET_LINETEL")
	private String budgetLinkerTel;//预算单位联系电话
	
	/** 采购执行部门 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="TASK_PLAN_PUR_DEPARTID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Department purDepartment;
	
	@Column(name = "TASK_PLAN_PUR_DEPARTNAME")
	private String purDemartmentName;//采购执行部门名称
	
	/** 创建人 */
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
	@JoinColumn(name="MODIFY_USER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User updateUser;
    
    //修改时间
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    
    /**项目投资规模*/
	@Column(name = "TOTAL_SCALE")
	private String totalScale;
	
	/**建设规模*/
	@Column(name = "CONSTRUCT_SCALE")
	private String constructScale;
	
	/**项目总投资额*/
	@Column(name = "TOTAL_AMOUNT")
	private BigDecimal totalAmount;
	
	/**资金来源*/
	@Column(name = "MONEY_SOURCE")
	private String moneySrc;
	
	/**附件*/
	@Column(name = "ATTACH_RALAID")
	private String attachRelaId;
	
	@Transient
	private String moneySrcCN;
	
	@Transient
	private BigDecimal totalAmountW;//投资总资转为万元
	
	
	
	/**工程地点*/
	@Column(name = "PROJ_ADDRESS")
	private String prjAddress;
	
	/** 资金来源 **/
	@Column(name="REDUNDANCY1")
	private String redundancy1;
	
	/** 委托单位 **/
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="COMMISSIONED_UNIT_ID", updatable = false)//关联的外键
	@BatchSize(size = 15)//批量抓取
	private OrgInfo commissioner; 

	@Column(name = "COMMISSIONED_UNIT_NAME")
	private String commissionUnitName;
	
	@Column(name = "COMMISSIONED_UNIT_LINKER")
	private String commissionUnitLinker;

	@Column(name = "COMMISSIONED_UNIT_TEL")
	private String commissionedUnitTel;  
 
	
	
	
    /** 任务书明细 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "TASK_PLAN_ID",updatable=false) 
	private Set<TaskPlanMSub> taskPlanMSubs = new HashSet<TaskPlanMSub>(0);
	
	/** 资金明细 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "TASK_PLAN_ID",updatable=false) 
	private Set<TaskPlanMDetail> taskPlanMDetails = new HashSet<TaskPlanMDetail>(0);
    
	@Transient
	private String opinion;//审核意见
	
	/**
	 * @gpcsoft.property title="招标中心项目负责人"
	 */
	public String getAgentLeader() {
		return agentLeader;
	}

	public void setAgentLeader(String agentLeader) {
		this.agentLeader = agentLeader;
	}
	

	/**总数量*/
	@Column(name = "SUM_QTY", precision = 16, scale = 6)
	private BigDecimal sumQty;
	
	/**已完成的数量*/
	//@Formula("(Select Sum(i.fin_good_sqty) From ECP_TASK_PLAN_SUB i Where i.Task_Id = TASK_ID)")
	@Transient
	private BigDecimal finGoodSqty;
	
	/**剩余数量*/
	//@Formula("(SUM_QTY - (select nvl(sum(o.order_task_qty),0) from eps_agree_order_protask o left join eps_agreement_order_item i on i.order_dtl_id = o.order_dtl_id left join eps_agreement_order a on a.order_id = i.order_id left join eps_agreement_protask_item e on e.task_item_id = o.task_item_id where o.task_item_id = TASK_ID and a.use_status != '02') )")
	@Transient
	private BigDecimal finTotal;
	
	/**品目名称集合*/
	@Column(name = "CATEGORY_NAMES", length = 200)
	private String categoryNames;
	
    public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	public String getRedundancy1() {
		return redundancy1;
	}

	public void setRedundancy1(String redundancy1) {
		this.redundancy1 = redundancy1;
	}

	/**
	 * @gpcsoft.property title="项目投资规模"
	 */
	public String getTotalScale() {
		return totalScale;
	}

	public BigDecimal getTotalAmountW() {
		
		if(totalAmount==null)return new BigDecimal(0);
		String a=totalAmount.toString();
		String b="10000";
		
		totalAmountW= new BigDecimal(a).divide(new BigDecimal(b),6,RoundingMode.HALF_UP);
		
		String result=totalAmountW.toString();
		String pointEnd=result.substring(result.lastIndexOf(".")+1);
		char[] c=pointEnd.toCharArray();
		StringBuffer str=new StringBuffer();
		str.append(result.substring(0, result.indexOf(".")));
		int n=0;
		for(int i=c.length-1;i>0;i--){
			if(c[i]!='0'&&c[i]!='.'){
				n=i;
				break;
			} 
		}
		
		if(n==0&&c[0]=='0'){
		}else{
			str.append(".");
			 for(int i=0;i<=n;i++){
				str.append(c[i]);
			 }
		}
		totalAmountW=new BigDecimal(str.toString());
		return totalAmountW;
	}

	public void setTotalScale(String totalScale) {
		this.totalScale = totalScale;
	}

	/**
	 * @gpcsoft.property title="建设规模"
	 */
	public String getConstructScale() {
		return constructScale;
	}

	public void setConstructScale(String constructScale) {
		this.constructScale = constructScale;
	}
	
	/**
	 * @gpcsoft.property title="项目总投资额"
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @gpcsoft.property title="资金来源"
	 */
	public String getMoneySrc() {
		return moneySrc;
	}

	public void setMoneySrc(String moneySrc) {
		this.moneySrc = moneySrc;
	}

	
	public String getAttachRelaId() {
		return attachRelaId;
	}

	public void setAttachRelaId(String attachRelaId) {
		this.attachRelaId = attachRelaId;
	}

	/**
	 * @gpcsoft.property title="资金来源"
	 */
	public String getMoneySrcCN() {
		this.moneySrcCN = EbuyMethodEnum.getMoneyResourceCN(this.getMoneySrc());
		return moneySrcCN;
	}

	public void setMoneySrcCN(String moneySrcCN) {
		this.moneySrcCN = moneySrcCN;
	}
	
	/**
	 * @gpcsoft.property title="工程地点"
	 */
	public String getPrjAddress() {
		return prjAddress;
	}

	public void setPrjAddress(String prjAddress) {
		this.prjAddress = prjAddress;
	}

	//modify by liuke
	/**当前有效id*/
	@Column(name = "CURRENT_VALID_ID")
	private String currentId;
	/********************************GET和SET方法**********************************************/
	public String getCurrentId() {
		return currentId;
	}

	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/**
	 * @gpcsoft.property title="业主单位项目负责人"
	 * @gpcsoft.validate class="required"
	 */
	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	
	public String getDrawType() {
		return drawType;
	}

	public void setDrawType(String drawType) {
		this.drawType = drawType;
	}
	
	/**
	 * @gpcsoft.property title="编号"
	 * @gpcsoft.validate class="required"
	 */
	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	
	/**
	 * @gpcsoft.property title="名称"
	 * @gpcsoft.validate class="required"
	 */
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @gpcsoft.property title="申报书类型"
	 * @gpcsoft.validate class="required"
	 */
	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	/**
	 * @gpcsoft.property title="采购年度"
	 * @gpcsoft.validate class="required"
	 */
	public Date getAcctYear() {
		return acctYear;
	}

	public void setAcctYear(Date acctYear) {
		this.acctYear = acctYear;
	}
	
	/**
	 * @gpcsoft.property title="主管单位"
	 * @gpcsoft.validate class="required"
	 */
	public Company getDepartment() {
		return department;
	}

	public void setDepartment(Company department) {
		this.department = department;
	}

	/**
	 * @gpcsoft.property title="业务处室"
	 * @gpcsoft.validate class="required"
	 */
	public Department getGovernment() {
		return government;
	}

	public void setGovernment(Department government) {
		this.government = government;
	}

	/**
	 * @gpcsoft.property title="业务处室名称"
	 * @gpcsoft.validate class="required"
	 */
	public String getGovernmentName() {
		return governmentName;
	}

	public void setGovernmentName(String governmentName) {
		this.governmentName = governmentName;
	}

	/**
	 * @gpcsoft.property title="预算单位"
	 * @gpcsoft.validate class="required"
	 */
	public OrgInfo getBudget() {
		return budget;
	}

	public void setBudget(OrgInfo budget) {
		this.budget = budget;
	}

	/**
	 * @gpcsoft.property title="采购执行部门"
	 * @gpcsoft.validate class="required"
	 */
	public Department getPurDepartment() {
		return purDepartment;
	}

	public void setPurDepartment(Department purDepartment) {
		this.purDepartment = purDepartment;
	}

	/**
	 * @gpcsoft.property title="主管单位名称"
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @gpcsoft.property title="主管单位联系人"
	 */
	public String getDepartmentLinker() {
		return departmentLinker;
	}

	public void setDepartmentLinker(String departmentLinker) {
		this.departmentLinker = departmentLinker;
	}

	/**
	 * @gpcsoft.property title="主管单位联系电话"
	 */
	public String getDepartmentLinkerTel() {
		return departmentLinkerTel;
	}

	public void setDepartmentLinkerTel(String departmentLinkerTel) {
		this.departmentLinkerTel = departmentLinkerTel;
	}

	/**
	 * @gpcsoft.property title="业务处室联系人"
	 */
	public String getGovLinker() {
		return govLinker;
	}

	public void setGovLinker(String govLinker) {
		this.govLinker = govLinker;
	}

	/**
	 * @gpcsoft.property title="业务处室联系电话"
	 */
	public String getGovLinkerTel() {
		return govLinkerTel;
	}

	public void setGovLinkerTel(String govLinkerTel) {
		this.govLinkerTel = govLinkerTel;
	}

	/**
	 * @gpcsoft.property title="预算单位名称"
	 */
	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	/**
	 * @gpcsoft.property title="预算单位联系人"
	 */
	public String getBudgetLinker() {
		return budgetLinker;
	}

	public void setBudgetLinker(String budgetLinker) {
		this.budgetLinker = budgetLinker;
	}

	/**
	 * @gpcsoft.property title="预算单位联系电话"
	 */
	public String getBudgetLinkerTel() {
		return budgetLinkerTel;
	}

	public void setBudgetLinkerTel(String budgetLinkerTel) {
		this.budgetLinkerTel = budgetLinkerTel;
	}

	/**
	 * @gpcsoft.property title="申请日期"
	 */
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	/**
	 * @gpcsoft.property title="期望完成时间"
	 */
	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	/**
	 * @gpcsoft.property title="任务金额"
	 */
	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	/**
	 * @gpcsoft.property title="组织形式"
	 */
	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	/**
	 * @gpcsoft.property title="采购方式"
	 * @gpcsoft.validate class="required"
	 */
	public String getEbuyMethod() {
		return ebuyMethod;
	}
	
	/**
	 * @gpcsoft.property title="采购方式"
	 */
	public String getEbuyMethodCN() {
		this.ebuyMethodCN = EbuyMethodEnum.getEBuyMethodCN(this.getEbuyMethod());
		return this.ebuyMethodCN;
	}

	public void setEbuyMethodCN(String ebuyMethodCN) {
		this.ebuyMethodCN = ebuyMethodCN;
	}

	public void setEbuyMethod(String ebuyMethod) {
		this.ebuyMethod = ebuyMethod;
	}

	/**
	 * @gpcsoft.property title="采购执行部门名称"
	 */
	public String getPurDemartmentName() {
		return purDemartmentName;
	}

	public void setPurDemartmentName(String purDemartmentName) {
		this.purDemartmentName = purDemartmentName;
	}

	/**
	 * @gpcsoft.property title="代理机构联系电话"
	 */
	public String getAgentLeaderTel() {
		return agentLeaderTel;
	}

	public void setAgentLeaderTel(String agentLeaderTel) {
		this.agentLeaderTel = agentLeaderTel;
	}

	public Boolean getCheckTaskPlan() {
		return checkTaskPlan;
	}

	public void setCheckTaskPlan(Boolean checkTaskPlan) {
		this.checkTaskPlan = checkTaskPlan;
	}

	
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * @gpcsoft.property title="备注"
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	public String getLastContractCode() {
		return lastContractCode;
	}

	public void setLastContractCode(String lastContractCode) {
		this.lastContractCode = lastContractCode;
	}

	
	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	public User getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	
	public User getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @gpcsoft.property title="状态"
	 */
	public String getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

	/**
	 * @gpcsoft.property title="状态"
	 */
	public String getConfirmStatusCN() {
		confirmStatusCN = TaskPlanConfirmEnum.getCN(this.getConfirmStatus());
		return confirmStatusCN;
	}

	public void setConfirmStatusCN(String confirmStatusCN) {
		this.confirmStatusCN = confirmStatusCN;
	}

	public Set<TaskPlanMSub> getTaskPlanMSubs() {
		return taskPlanMSubs;
	}

	public void setTaskPlanMSubs(Set<TaskPlanMSub> taskPlanMSubs) {
		this.taskPlanMSubs = taskPlanMSubs;
	}

	public Set<TaskPlanMDetail> getTaskPlanMDetails() {
		return taskPlanMDetails;
	}

	public void setTaskPlanMDetails(Set<TaskPlanMDetail> taskPlanMDetails) {
		this.taskPlanMDetails = taskPlanMDetails;
	}

	public String getAuditDetail() {
		return auditDetail;
	}

	public void setAuditDetail(String auditDetail) {
		this.auditDetail = auditDetail;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getProcessStatusCN() {
		this.processStatusCN = TaskPlanConfirmEnum.getTaskplanProcessStatusCN(this.processStatus);
		return processStatusCN;
	}

	public void setProcessStatusCN(String processStatusCN) {
		this.processStatusCN = processStatusCN;
	}

	public String getUseStatusCN() {
		useStatusCN = CommonEnum.getUseCN(this.getUseStatus());
		return useStatusCN;
	}

	public void setUseStatusCN(String useStatusCN) {
		this.useStatusCN = useStatusCN;
	}

	public String getAuditDetailCN() {
		auditDetailCN = CommonEnum.getConfirmCN(this.getAuditDetail());
		return auditDetailCN;
	}

	public void setAuditDetailCN(String auditDetailCN) {
		this.auditDetailCN = auditDetailCN;
	}

	/**
	 * @gpcsoft.property title="代理机构"
	 */
	public OrgInfo getTaskAgent() {
		return taskAgent;
	}

	public void setTaskAgent(OrgInfo taskAgent) {
		this.taskAgent = taskAgent;
	}

	/**
	 * @gpcsoft.property title="代理机构名称"
	 */
	public String getTaskAgentName() {
		return taskAgentName;
	}

	public void setTaskAgentName(String taskAgentName) {
		this.taskAgentName = taskAgentName;
	}

	public String getTaskTypeCN() {
		taskTypeCN = TaskPlanTypeEnum.getTypeCN(this.getTaskType());
		return taskTypeCN;
	}

	public void setTaskTypeCN(String taskTypeCN) {
		this.taskTypeCN = taskTypeCN;
	}

	/**
	 * @gpcsoft.property title="负责人"
	 */
	public Employee getLeader() {
		return leader;
	}

	public void setLeader(Employee leader) {
		this.leader = leader;
	}
	
	public String getBlock_check_status() {
		return block_check_status;
	}

	public void setBlock_check_status(String block_check_status) {
		this.block_check_status = block_check_status;
	}

	public BigDecimal getSumQty() {
		return sumQty;
	}

	public void setSumQty(BigDecimal sumQty) {
		this.sumQty = sumQty;
	}

	public BigDecimal getFinGoodSqty() {
		return finGoodSqty;
	}

	public void setFinGoodSqty(BigDecimal finGoodSqty) {
		this.finGoodSqty = finGoodSqty;
	}

	public BigDecimal getFinTotal() {
		return finTotal;
	}

	public void setFinTotal(BigDecimal finTotal) {
		this.finTotal = finTotal;
	}

	public String getCategoryNames() {
		return categoryNames;
	}

	public void setCategoryNames(String categoryNames) {
		this.categoryNames = categoryNames;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static String getCONFIRM_STATUS_PASS() {
		return CONFIRM_STATUS_PASS;
	}

	public static String getCONFIRM_STATUS_NO_PASS() {
		return CONFIRM_STATUS_NO_PASS;
	}

	/**
	 * @gpcsoft.property title="委托单位"
	 */
	public OrgInfo getCommissioner() {
		return commissioner;
	}
	
	
	public void setCommissioner(OrgInfo commissioner) {
		this.commissioner = commissioner;
	}

	/**
	 * @gpcsoft.property title="委托单位名称"
	 */
	public String getCommissionUnitName() {
		return commissionUnitName;
	}

	public void setCommissionUnitName(String commissionUnitName) {
		this.commissionUnitName = commissionUnitName;
	}

	/**
	 * @gpcsoft.property title="委托单位联系人"
	 */
	public String getCommissionUnitLinker() {
		return commissionUnitLinker;
	}

	public void setCommissionUnitLinker(String commissionUnitLinker) {
		this.commissionUnitLinker = commissionUnitLinker;
	}

	/**
	 * @gpcsoft.property title="委托单位联系人"
	 */
	public String getCommissionedUnitTel() {
		return commissionedUnitTel;
	}

	public void setCommissionedUnitTel(String commissionedUnitTel) {
		this.commissionedUnitTel = commissionedUnitTel;
	}
	
	
}
