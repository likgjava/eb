package com.gpcsoft.agreement.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

/** 
  *  Comments: <strong>任务单条目</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 订单-任务单条目    		
  *  <br/>Create Date：2010-4-8 上午03:00:01 by yucy    					                            
  *  <br/>Modified Date:  2010-4-8 上午03:00:01 by yucy                                   
  *  
  *   @since 0.5
  *   @version: 0.5 
  *   
  *   @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.order"
  *   @gpcsoft.page domain="order" project="agreement"
  *   @gpcsoft.domain
  *   @gpcsoft.title value="任务单条目" 
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREEMENT_PROTASK_ITEM")
public class ProtaskItem implements GpcBaseObject {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**主键*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "TASK_ITEM_ID", unique = true, nullable = false, length = 50)
	private String objId;

	/**任务书*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TASK_ID")
	private Procurementtask procurementtask;

	/**品目*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PUR_CATEGORY_ID")
	private PurCategory purCategory;
	
	/**分类*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODS_CLASS_ID")
	private GoodsClass goodsClass;
	
	/**预算金额*/
	@Column(name = "GOODS_TOTAL", precision = 16, scale = 6)
	private BigDecimal goodsTotal;
	
	/**已完成数量*/
	@Column(name = "FIN_GOOD_SQTY", precision = 16, scale = 6)
	private BigDecimal finGoodSqty;
	
	/**剩余数量*/
	@Formula("(GOODS_QTY-(select nvl(sum(o.order_task_qty),0) from eps_agree_order_protask o join eps_agreement_order_item i on i.order_dtl_id = o.order_dtl_id join eps_agreement_order a on a.order_id = i.order_id where o.task_item_id = TASK_ITEM_ID and a.use_status != '02'))")
	private BigDecimal finQty;
	
	/**剩余金额*/
	@Formula("(GOODS_TOTAL-(select nvl(sum(o.ORDER_TASK_TOTAL),0) from eps_agree_order_protask o join eps_agreement_order_item i on i.order_dtl_id = o.order_dtl_id join eps_agreement_order a on a.order_id = i.order_id where o.task_item_id = TASK_ITEM_ID and a.use_status != '02'))")
	private BigDecimal finTotal;

	/**备注*/
	@Column(name = "MEMO", length = 200)
	private String memo;

	/**数量*/
	@Column(name = "GOODS_QTY", precision = 16, scale = 6)
	private BigDecimal goodsQty;
	
	/**单价*/
	@Column(name = "GOODS_PRICE", precision = 16, scale = 6)
	private BigDecimal goodsPrice;
	
	/**完成金额*/
	@Column(name = "FIN_GOODS_TOTAL", precision = 16, scale = 6)
	private BigDecimal finGoodTotal;
	
	/** 任务书条目与订单条目关系 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "Task_Item_ID") 
	private Set<OrderProtask> orderProtask = new HashSet<OrderProtask>(0);


	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}

	/** @gpcsoft.property title="任务书"*/
	public Procurementtask getProcurementtask() {
		return procurementtask;
	}

	public void setProcurementtask(Procurementtask procurementtask) {
		this.procurementtask = procurementtask;
	}	
	
	/** @gpcsoft.property title="分类"*/
	public GoodsClass getGoodsClass() {
		return goodsClass;
	}
	public void setGoodsClass(GoodsClass goodsClass) {
		this.goodsClass = goodsClass;
	}
	
	/** @gpcsoft.property title="品目" */
	public PurCategory getPurCategory() {
		return purCategory;
	}
	public void setPurCategory(PurCategory purCategory) {
		this.purCategory = purCategory;
	}
	
	/** @gpcsoft.property title="预算金额" */
	public BigDecimal getGoodsTotal() {
		return goodsTotal;
	}
	
	public void setGoodsTotal(BigDecimal goodsTotal) {
		this.goodsTotal = goodsTotal;
	}	
	
	/** @gpcsoft.property title="已完成数量" */
	public BigDecimal getFinGoodSqty() {
		return finGoodSqty;
	}
	public void setFinGoodSqty(BigDecimal finGoodSqty) {
		this.finGoodSqty = finGoodSqty;
	}	
	
	/** @gpcsoft.property title="备注" */
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}	
	
	/** @gpcsoft.property title="数量" */
	public BigDecimal getGoodsQty() {
		return goodsQty;
	}
	public void setGoodsQty(BigDecimal goodsQty) {
		this.goodsQty = goodsQty;
	}	
	
	/** @gpcsoft.property title="单价" */
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	/** @gpcsoft.property title="完成金额" */
	public BigDecimal getFinGoodTotal() {
		return finGoodTotal;
	}
	public void setFinGoodTotal(BigDecimal finGoodTotal) {
		this.finGoodTotal = finGoodTotal;
	}
	
	/**@gpcsoft.property title="剩余金额"*/
	public BigDecimal getFinTotal() {
		return finTotal;
	}
	public void setFinTotal(BigDecimal finTotal) {
		this.finTotal = finTotal;
	}
	
	/**@gpcsoft.property title="剩余数量"*/
	public BigDecimal getFinQty() {
		return finQty;
	}
	public void setFinQty(BigDecimal finQty) {
		this.finQty = finQty;
	}	
	
	/**
	 * @gpcsoft.property title="任务书条目与订单条目关系"
	 */
	public Set<OrderProtask> getOrderProtask() {
		return orderProtask;
	}
	public void setOrderProtask(Set<OrderProtask> orderProtask) {
		this.orderProtask = orderProtask;
	}
	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setCreateTime(Date createTime) {
		// TODO Auto-generated method stub
		
	}
	
}