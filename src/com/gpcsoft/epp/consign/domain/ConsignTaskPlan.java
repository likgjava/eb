package com.gpcsoft.epp.consign.domain;

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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;

/**
 *  Comments: <strong>Consign</strong>委托和申报书中间表<br/>            		
 *	<br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 权限平台     		
 *  <br/>Create Date：2010-4-14 上午02:59:07 by yemx    					                            
 *  <br/>Modified Date:  2010-4-14 上午02:59:07 by yemx                                 
 *  @gpcsoft.package packageDir="com.gpcsoft.epp.consign"
 *  @gpcsoft.page domain="project" project="es/planform"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="委托和申报书中间表"    
 *  @since 0.4
 *  @version: 0.5
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_CONS_TASK_PLAN")
public class ConsignTaskPlan implements GpcBaseObject{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CONS_TASK_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;  
	
	/** 委托协议 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CONS_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Consign consign; //委托
	
	/** 申报书 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="TASK_PLAN_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private TaskPlan taskPlan; //申报书
	
	/** 申报书明细 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="TASK_PLAN_SUB_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private TaskPlanSub taskPlanSub; //申报书明细
	
	

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
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
	 * @gpcsoft.property title="采购申报书"
	 */
	public TaskPlan getTaskPlan() {
		return taskPlan;
	}

	public void setTaskPlan(TaskPlan taskPlan) {
		this.taskPlan = taskPlan;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCreateTime(Date arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @gpcsoft.property title="采购申报书明细"
	 */
	public TaskPlanSub getTaskPlanSub() {
		return taskPlanSub;
	}

	public void setTaskPlanSub(TaskPlanSub taskPlanSub) {
		this.taskPlanSub = taskPlanSub;
	}
	
	
}
