package com.gpcsoft.epp.bid.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
/**
 * @Description: 投标条目
 * @gpcsoft.package packageDir="com.gpcsoft.epp.bid"
 * @gpcsoft.page domain="planform/bid" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="投标条目"
 * @version V1.0
 * @Create Date 2010-10-8 上午09:31:01 By wanghz
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_BID_ITEMS")
public class BidItems implements GpcBaseObject{
	@Id
	@Column(name = "BID_ITEMS_ID", nullable=false, length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@Column(name = "TASK_PLAN_SUB_ID", length = 50)
	private String taskPlansubId;								// 申报书明细ID[为了跟踪预算]
	
	@Column(name = "PURCHASE_ID", length = 50)
	private String purchaseId;									// 品目Id
	
	@Column(name = "PURCHASE_NAME", length = 50)
	private String purchaseName;								// 品目名称
	
	@Column(name = "BID_NUMBER")
	private BigDecimal number;									// 参投数量
	
	@Column(name = "PROJ_MANAGER")
	private String projManager;									// 项目经理
	
	@Column(name = "BID_QUOTESUM")
	private BigDecimal quotesum;								// 参与投标的明细总金额 
	
	@Column(name = "BID_PRICE")
	private BigDecimal price;									// 投标单价[计算出/手动填写]
	
	@Column(name = "BUDGET_ID", length = 50)
	private String budgetId;									// 预算单位ID
	
	@Column(name = "BUDGET_NAME", length = 50)
	private String budgetName;									// 预算代为名称
	
	@Column(name = "BID_MEMO", length = 50)
	private String bidMemo;										// 对于条目的响应描述[整体响应]
	
	@Column(name = "MEASURE_UNIT", length = 50)
	private String measureUnit;									// 计量单位
	
	@Column(name = "BID_P_ID", length = 50)
	private String bidPackId;									// 投标包件Id

	@Transient
	private String packId;										// 项目/包件 Id
	
	@Transient
	private String packName;									//包件名	
	
	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getTaskPlansubId() {
		return taskPlansubId;
	}

	public void setTaskPlansubId(String taskPlansubId) {
		this.taskPlansubId = taskPlansubId;
	}

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	/**
	 * @gpcsoft.property title="品目名称"
	 */
	public String getPurchaseName() {
		return purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}
	/**
	 * @gpcsoft.property title="参投数量"
	 */
	public BigDecimal getNumber() {
		return number;
	}

	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	/**
	 * @gpcsoft.property title="总金额"
	 */
	public BigDecimal getQuotesum() {
		return quotesum;
	}

	public void setQuotesum(BigDecimal quotesum) {
		this.quotesum = quotesum;
	}

	/**
	 * @gpcsoft.property title="投标单价"
	 */
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(String budgetId) {
		this.budgetId = budgetId;
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

	/**
	 * @gpcsoft.property title="响应描述"
	 */
	public String getBidMemo() {
		return bidMemo;
	}

	public void setBidMemo(String bidMemo) {
		this.bidMemo = bidMemo;
	}

	/**
	 * @gpcsoft.property title="计量单位"
	 */
	public String getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}

	
	/**
	 * @gpcsoft.property title="项目经理"
	 */
	public String getProjManager() {
		return projManager;
	}

	public void setProjManager(String projManager) {
		this.projManager = projManager;
	}

	public String getBidPackId() {
		return bidPackId;
	}

	public void setBidPackId(String bidPackId) {
		this.bidPackId = bidPackId;
	}
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#getCreateTime()
	 */
	public Date getCreateTime() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#setCreateTime(java.util.Date)
	 */
	public void setCreateTime(Date arg0) {
	}

	public String getPackId() {
		return packId;
	}

	public void setPackId(String packId) {
		this.packId = packId;
	}
}
