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
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;

/** 
 *  Comments: <strong>标段关联申报书条目</strong>            		
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

@Table(name = "ECP_TEND_M_TASK_P")
public class SubProjectMTaskPlanSub implements GpcBaseObject{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "TEND_M_TASK_P_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; //主键
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="TENDERID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Project tproject;//招标项目
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="SUB_TENDERID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Project project;//标段
	
	@Column(name = "QUANTITY", length = 16)
	private BigDecimal quantity;//数量
	
	@Transient
	private BigDecimal realQuantity; // 总共数量

	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="BUY_MAIN_BODY")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private OrgInfo buyMainBody;//采购主体
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="TASK_PLAN_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private TaskPlan taskPlan;//申报书
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="TASK_PLAN_SUB_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private TaskPlanSub taskPlanSub; //申报书条目
	
	@Column(name = "BUDGET_MONEY", length = 16)
	private BigDecimal money;//预算金额
	
	public Project getTproject() {
		return tproject;
	}

	public void setTproject(Project tproject) {
		this.tproject = tproject;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public OrgInfo getBuyMainBody() {
		return buyMainBody;
	}

	public void setBuyMainBody(OrgInfo buyMainBody) {
		this.buyMainBody = buyMainBody;
	}

	public TaskPlan getTaskPlan() {
		return taskPlan;
	}

	public void setTaskPlan(TaskPlan taskPlan) {
		this.taskPlan = taskPlan;
	}

	public TaskPlanSub getTaskPlanSub() {
		return taskPlanSub;
	}

	public void setTaskPlanSub(TaskPlanSub taskPlanSub) {
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
	
	public BigDecimal getRealQuantity() {
		return realQuantity;
	}

	public void setRealQuantity(BigDecimal realQuantity) {
		this.realQuantity = realQuantity;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	

}
