package com.gpcsoft.epp.taskplan.domain;

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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;

/** 
  *  Comments: <strong>申报书与条目中间表</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-6-3 下午08:13:05 by Administrator    					                            
  *  <br/>Modified Date:  2010-6-3 下午08:13:05 by Administrator  
  *  
  *  
  *  @gpcsoft.package packageDir="com.gpcsoft.epp.taskplan"
  *  @gpcsoft.page domain="planform/taskplan" project="es"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="采购计划"
  */ 



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)

@Table(name = "ECP_TASK_SUB")
public class TaskPlanMSub implements GpcBaseObject{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "TASK_M_SUB_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;//主键
	
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name="TASK_PLAN_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private TaskPlan taskPlan;

	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name="TASK_PLAN_SUB_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	@Cascade({CascadeType.DELETE_ORPHAN})
	private TaskPlanSub taskPlanSub;
	
	@Column(name="STATUS")
	private String status;
	
	@Transient
	private BigDecimal quantity; //数量
	
	@Transient
	private BigDecimal money; //金额
	
	@Transient
	private Boolean  isUsed; //金额
	
	@Transient
	private Boolean   isSum; //是否汇总（数据交换时使用）
	
	public Boolean getIsSum() {
		return isSum;
	}

	public void setIsSum(Boolean isSum) {
		this.isSum = isSum;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	/**
	 * @gpcsoft.property title="申报书"
	 * @gpcsoft.validate class="required"
	 */
	public TaskPlan getTaskPlan() {
		return taskPlan;
	}

	public void setTaskPlan(TaskPlan taskPlan) {
		this.taskPlan = taskPlan;
	}

	/**
	 * @gpcsoft.property title="申报书条目"
	 * @gpcsoft.validate class="required"
	 */
	public TaskPlanSub getTaskPlanSub() {
		return taskPlanSub;
	}

	public void setTaskPlanSub(TaskPlanSub taskPlanSub) {
		this.taskPlanSub = taskPlanSub;
	}

	/**
	 * @gpcsoft.property title="状态"
	 * @gpcsoft.validate class="required"
	 */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getObjId() {
		return this.objId;
	}

	public void setCreateTime(Date arg0) {
		// TODO Auto-generated method stub
		
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	

}
