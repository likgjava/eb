package com.gpcsoft.epp.taskplan.domain;

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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>TaskPlanDetail</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   es                    					          
  *  <br/>Module ID: 采购计划     		
  *  <br/>Create Date：2010-4-14 下午03:45:11 by guom    					                            
  *  <br/>Modified Date:  2010-4-14 下午03:45:11 by guom                                 
  *  @gpcsoft.package packageDir="com.gpcsoft.epp.taskplan"
  *  @gpcsoft.page domain="planform/taskplan" project="es"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="采购资金明细"   
  *  @since 0.1
  *  @version: 0.1 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)

@Table(name = "ECP_TASK_PLAN_DETAIL")
public class TaskPlanDetail extends WorkFlowModel implements GpcBaseObject , IPropertyCUserTime, IPropertyUUserTime{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "TASK_PLAN_DETAIL_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;//主键
	
	@Column(name = "BUDGET_NAME", updatable = false)
	private String budgetName;//预算单位名称
	
	@Column(name = "APPROVAL_NUMBER")
	private String approvalNumber; //批准文号
	
	@Column(name = "SUPERIOR_APPROPRIATION")
	private String superiorApp;//上级拨款
	
	
	@Column(name = "LOCAL_APPROPRIATION")
	private String localApp;//本级财政
	
	@Column(name = "OWNER_APPROPRIATION")
	private String ownerApp;//自筹
	
	@Column(name = "OTHER_APPROPRIATION")
	private String otherApp;//其他资金
	
	@Column(name = "QUANTITY")
	private String quantity;//总金额
	
	@Column(name = "USE_STATUS")
	private Character useStatus;//使用状态

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
	
	/********************************GET和SET方法**********************************************/
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}
	
	/**
	 * @gpcsoft.property title="批准文号"
	 */
	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	/**
	 * @gpcsoft.property title="上级拨款"
	 */
	public String getSuperiorApp() {
		return superiorApp;
	}

	public void setSuperiorApp(String superiorApp) {
		this.superiorApp = superiorApp;
	}

	/**
	 * @gpcsoft.property title="本级财政"
	 */
	public String getLocalApp() {
		return localApp;
	}

	public void setLocalApp(String localApp) {
		this.localApp = localApp;
	}

	/**
	 * @gpcsoft.property title="自筹资金"
	 */
	public String getOwnerApp() {
		return ownerApp;
	}

	public void setOwnerApp(String ownerApp) {
		this.ownerApp = ownerApp;
	}

	/**
	 * @gpcsoft.property title="其他资金"
	 */
	public String getOtherApp() {
		return otherApp;
	}

	public void setOtherApp(String otherApp) {
		this.otherApp = otherApp;
	}

	public Character getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Character useStatus) {
		this.useStatus = useStatus;
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	
	/**
	 * @gpcsoft.property title="总资金"
	 */
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
}
