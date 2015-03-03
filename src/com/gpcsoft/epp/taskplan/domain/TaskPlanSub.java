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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>TaskPlanSub</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   es                    					          
  *  <br/>Module ID: 采购计划     		
  *  <br/>Create Date：2010-4-14 下午02:36:59 by guom    					                            
  *  <br/>Modified Date:  2010-4-14 下午02:36:59 by guom                                 
  *      
  *  @since 0.1
  *  @version: 0.1 
  *  @gpcsoft.package packageDir="com.gpcsoft.epp.taskplan"
  *  @gpcsoft.page domain="planform/taskplan" project="es"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="采购计划明细"
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_TASK_PLAN_SUB")
public class TaskPlanSub extends WorkFlowModel implements GpcBaseObject , IPropertyCUserTime, IPropertyUUserTime{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "TASK_PLAN_SUB_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;//主键

	/** 采购品目 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="PURCHASE_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private PurCategory purchase;
	
	@Column(name = "PURCHASE_NAME")
	private String purchaseName;//采购品目名称
	
	@Column(name = "BUDGET_NAME", updatable = false)
	private String budgetName;//预算单位名称
	
	@Column(name = "LIST_STATUS")
	private String listStatus;//品目执行状态
	
	@Column(name = "PURCHASE_MODEL")
	private String purChasemodel;//产品型号
	
	@Column(name = "QUANTITY")
	private BigDecimal quantity;//数量
	
	@Transient
	private BigDecimal realQuantity; // 实际
	@Transient
	private BigDecimal realMoney; // 实际金额 add by wangcl

	@Column(name = "UNIT")
	private String unit;//计量单位
	
	@Column(name = "UNITPRICE")
	private BigDecimal unitPrice;//单价
	
	@Column(name = "TOTALPRICE")
	private BigDecimal totalPrice;//预算
	

	@Column(name = "REMARK")
	private String remark;//备注
	
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
	@JoinColumn(
	name="MODIFY_USER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User updateUser;
    
    //修改时间
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    
    
    @Column(name = "redundancy1")
	private String redundancy1;//冗余1
    
    @Column(name = "redundancy2")
	private String redundancy2;//冗余2
    
    @Column(name = "redundancy3")
	private String redundancy3;//冗余3
    
	/**分类*/
    @Transient
	private String goodsClass;
	
	/**已完成数量*/
	@Column(name = "FIN_GOOD_SQTY", precision = 16, scale = 6)
	private BigDecimal finGoodSqty;
	
	/**剩余数量*/
	@Transient
	private BigDecimal finQty;
	
	@Transient
	private BigDecimal finTotal;
	
	/**完成金额*/ 
	@Column(name = "FIN_GOODS_TOTAL", precision = 16, scale = 6)
	private BigDecimal finGoodTotal;
	
	
    /** 任务书明细 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "TASK_PLAN_SUB_ID",updatable=false) 
	private Set<TaskPlanMSub> taskPlanMSubs = new HashSet<TaskPlanMSub>(0);
	
	
    @Column(name = "PURCHASE_PLAN_ID")
	private String purchasePlanId;//采购计划Id
    
    @Column(name = "PURCHASE_DETAIL_ID")
    private String purchaseDetailId;//采购计划明细Id

    /********************************GET和SET方法**********************************************/
    	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	public String getListStatus() {
		return listStatus;
	}

	public void setListStatus(String listStatus) {
		this.listStatus = listStatus;
	}
	/**
	 * @gpcsoft.property title="产品型号"
	 * @gpcsoft.validate class="required"
	 */
	public String getPurChasemodel() {
		return purChasemodel;
	}

	public void setPurChasemodel(String purChasemodel) {
		this.purChasemodel = purChasemodel;
	}
	/**
	 * @gpcsoft.property title="采购数量"
	 * @gpcsoft.validate class="required"
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	/**
	 * @gpcsoft.property title="计量单位"
	 * @gpcsoft.validate class="required"
	 */
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * @gpcsoft.property title="单价"
	 * @gpcsoft.validate class="required"
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @gpcsoft.property title="预算总金额"
	 * @gpcsoft.validate class="required"
	 */
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	/**
	 * @gpcsoft.property title="采购品目名称"
	 * @gpcsoft.validate class="required"
	 */
	public String getPurchaseName() {
		return purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}

	/**
	 * @gpcsoft.property title="采购品目"
	 * @gpcsoft.validate class="required"
	 */
	public PurCategory getPurchase() {
		return purchase;
	}

	public void setPurchase(PurCategory purchase) {
		this.purchase = purchase;
	}

	/**
	 * @gpcsoft.property title="备注"
	 * @gpcsoft.validate class="required"
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Character getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Character useStatus) {
		this.useStatus = useStatus;
	}

	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	
	/**
	 * @gpcsoft.property title="预算单位"
	 */
	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}
	
	public BigDecimal getRealQuantity() {
		return realQuantity;
	}

	public void setRealQuantity(BigDecimal realQuantity) {
		this.realQuantity = realQuantity;
	}

	public BigDecimal getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(BigDecimal realMoney) {
		this.realMoney = realMoney;
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
	

	public String getGoodsClass() {
		return goodsClass;
	}

	public void setGoodsClass(String goodsClass) {
		this.goodsClass = goodsClass;
	}

	public BigDecimal getFinGoodSqty() {
		return finGoodSqty;
	}

	public void setFinGoodSqty(BigDecimal finGoodSqty) {
		this.finGoodSqty = finGoodSqty;
	}

	public BigDecimal getFinQty() {
		return finQty; 
	}

	public void setFinQty(BigDecimal finQty) {
		this.finQty = finQty;
	}

	public BigDecimal getFinTotal() {
		return finTotal;
	}

	public void setFinTotal(BigDecimal finTotal) {
		this.finTotal = finTotal;
	}

	public BigDecimal getFinGoodTotal() {
		return finGoodTotal;
	}

	public void setFinGoodTotal(BigDecimal finGoodTotal) {
		this.finGoodTotal = finGoodTotal;
	}

	

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Set<TaskPlanMSub> getTaskPlanMSubs() {
		return taskPlanMSubs;
	}

	public void setTaskPlanMSubs(Set<TaskPlanMSub> taskPlanMSubs) {
		this.taskPlanMSubs = taskPlanMSubs;
	}

	public String getPurchasePlanId() {
		return purchasePlanId;
	}

	public void setPurchasePlanId(String purchasePlanId) {
		this.purchasePlanId = purchasePlanId;
	}

	public String getPurchaseDetailId() {
		return purchaseDetailId;
	}

	public void setPurchaseDetailId(String purchaseDetailId) {
		this.purchaseDetailId = purchaseDetailId;
	}
	
}
