package com.gpcsoft.epp.project.domain;

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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;

/** 
 *  Comments: <strong>招标项目关联申报书条目</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   agreement                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-6-30 上午09:46:25 by yangx    					                            
 *  <br/>Modified Date:  2010-6-30 上午09:46:25 by yangx                                   
  *  @gpcsoft.package packageDir="com.gpcsoft.epp.project"
  *  @gpcsoft.page domain="planform/project" project="es"
  *  @gpcsoft.domain
  *  @gpcsoft.title value=""  
  *  @since 0.1
  *  @version: 0.1 
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)

@Table(name = "ECP_TEND_M_TASK")
public class ProjectMTaskPlan implements GpcBaseObject{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "TEND_M_TASK_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; //主键
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="TENDERID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Project tproject;//招标项目
	
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="BUY_MAIN_BODY")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private OrgInfo buyMainBody;//采购主体
	
	@Column(name = "TASK_PLAN_SUB_ID")
	private String taskPlanSub; //申报书条目
	
	@Column(name = "PURCHASE_ID")
	private String purchaseId;//品目ID
	
	@Column(name = "PURCHASE_NAME")
	private String purchaseName;//品目名称
	
	@Column(name = "FROM_TYPE")
	private String fromType;//00:采购申报书 	01:建筑类登记表 	02:产权交易登记表	03:土地交易登记表
	
	@Column(name = "BUDGET_MONEY")
	private BigDecimal budgetMoney;//预算金额
	
	@Column(name = "BUDGET_FROM")
	private String budgetFrom;//预算来源
	
	@Column(name = "QUANTITY")
	private BigDecimal quantity;//数量
	
	@Column(name = "IS_EFFECTIVE")
	private String isEffective;//是否有效[0:无效;1:有效;]
	
	@Column(name = "REMARK")
	private String remark;//备注
	
	@Transient
	private String taskcode;
	
	
	@Transient
	private String projectId;
	
	@Transient
	private String taskName;
	
	@Transient
	private String taskplanId;
	
	@Transient
	private String taskPlanSubId;
	
	@Transient
	private String applyDate;

	public String getTaskPlanSubId() {
		return taskPlanSubId;
	}

	public void setTaskPlanSubId(String taskPlanSubId) {
		this.taskPlanSubId = taskPlanSubId;
	}

	public String getTaskplanId() {
		return taskplanId;
	}

	public void setTaskplanId(String taskplanId) {
		this.taskplanId = taskplanId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getTaskcode() {
		return taskcode;
	}

	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}


	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public Project getTproject() {
		return tproject;
	}

	public void setTproject(Project tproject) {
		this.tproject = tproject;
	}

	/**
	 * @gpcsoft.property title="采购单位"
	 */
	public OrgInfo getBuyMainBody() {
		return buyMainBody;
	}

	public void setBuyMainBody(OrgInfo buyMainBody) {
		this.buyMainBody = buyMainBody;
	}

	public String getTaskPlanSub() {
		return taskPlanSub;
	}

	public void setTaskPlanSub(String taskPlanSub) {
		this.taskPlanSub = taskPlanSub;
	}

	public Date getCreateTime() {
		return null;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setCreateTime(Date arg0) {
		
	}

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchaseName() {
		return purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}

	public String getBudgetFrom() {
		return budgetFrom;
	}

	public void setBudgetFrom(String budgetFrom) {
		this.budgetFrom = budgetFrom;
	}

	public String getIsEffective() {
		return isEffective;
	}

	public void setIsEffective(String isEffective) {
		this.isEffective = isEffective;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public BigDecimal getBudgetMoney() {
		return budgetMoney;
	}

	public void setBudgetMoney(BigDecimal budgetMoney) {
		this.budgetMoney = budgetMoney;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
}
