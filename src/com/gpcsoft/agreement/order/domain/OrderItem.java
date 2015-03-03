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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;

import com.gpcsoft.agreement.bargin.require.domain.RequireItem;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.goods.goods.domain.Goods;

/** 
  *  Comments: <strong>订单明细</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 协议供货--订单明细   		
  *  <br/>Create Date：2010-4-7 下午05:49:26 by sunl    					                            
  *  <br/>Modified Date:  2010-4-7 下午05:49:26 by sunl    
  *                                 
  *  @since 0.5
  *  @version: 0.5 
  *  
  *  @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.order"
  *  @gpcsoft.page domain="order" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="订单明细"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREEMENT_ORDER_ITEM")
public class OrderItem implements GpcBaseObject {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	@Id
	@Column(name = "ORDER_DTL_ID", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	private String objId;
	
	/** 订单 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="ORDER_ID")
	@BatchSize(size = 15)
	private Order order;
	
	/** 需求条目 add by yucy*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="REQUIRE_DTL_ID")
	@BatchSize(size = 15)
	private RequireItem requireItem;
	
	/** 供应商 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="SUPPLIER_ID")	 
	@BatchSize(size = 15)
	private OrgInfo supplier;
	
	/** 商品 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="GOODS_ID")	 
	@BatchSize(size = 15)
	private Goods goods;
	
	/** 描述 */
	@Length(max=200)  
	@Column(name = "DECR", length = 200)
	private String desr;
	
	/** 数量 */
	@Column(name = "GOODS_QTY", precision = 16, scale = 6)
	private BigDecimal goodsQty;
	
	/** 计量单位 */
	@Column(name = "GOODS_UNIT", length = 20)
	private String goodsUnit;
	
	/** 单价 */
	@Column(name = "GOODS_PRICE", precision = 16, scale = 6)
	private BigDecimal goodsPrice;
	
	/** 协议价 */
	@Column(name = "AGREE_PRICE", precision = 16, scale = 6)
	private BigDecimal agreePrice;
	
	/** 市场价 */
	@Column(name = "MARKET_PRICE", precision = 16, scale = 6)
	private BigDecimal marketPrice;
	
	/** 金额 */
	@Column(name = "GOODS_TOTAL", precision = 16, scale = 6)
	private BigDecimal goodsTotal;
	
	/** 备注 */
	@Length(max=200)  
	@Column(name = "MEMO", length = 200)
	private String memo;
	
	/** 采购商品选配 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN,CascadeType.SAVE_UPDATE})
	@JoinColumn(name = "ORDER_DTL_ID") 
	private Set<OrderGoodsOption> orderGoodsOptions = new HashSet<OrderGoodsOption>(0);
	
	/** 采购商品礼包 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN,CascadeType.SAVE_UPDATE})
	@JoinColumn(name = "ORDER_ITEM_ID") 
	private Set<OrderGoodsGift> orderGoodsGifts = new HashSet<OrderGoodsGift>(0);
	
	/** 零配件与订单条目关系 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN,CascadeType.SAVE_UPDATE})
	@JoinColumn(name = "ORDER_ITEM_ID") 
	private Set<OrderGoodsAccessories> orderGoodsAccessories = new HashSet<OrderGoodsAccessories>(0);
	
	/** 任务书条目与订单条目关系 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN,CascadeType.SAVE_UPDATE})
	@JoinColumn(name = "ORDER_DTL_ID") 
	private Set<OrderProtask> orderProtasks = new HashSet<OrderProtask>(0);
	
	/** 任务书条目数量*/
	@Transient
	private Integer orderProtaskCount;
	
	/**
	 * @gpcsoft.property title="主键"
	 */
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	/**
	 * @gpcsoft.property title="订单"
	 */
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public RequireItem getRequireItem() {
		return requireItem;
	}
	public void setRequireItem(RequireItem requireItem) {
		this.requireItem = requireItem;
	}
	/**
	 * @gpcsoft.property title="供应商"
	 */
	public OrgInfo getSupplier() {
		return supplier;
	}
	public void setSupplier(OrgInfo supplier) {
		this.supplier = supplier;
	}
	
	/**
	 * @gpcsoft.property title="商品"
	 */
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
	/**@gpcsoft.property title="描述"*/
	public String getDesr() {
		return desr;
	}
	public void setDesr(String desr) {
		this.desr = desr;
	}
	/**
	 * @gpcsoft.property title="数量"
	 */
	public BigDecimal getGoodsQty() {
		return goodsQty;
	}
	public void setGoodsQty(BigDecimal goodsQty) {
		this.goodsQty = goodsQty;
	}
	
	/**
	 * @gpcsoft.property title="单价"
	 */
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	/**
	 * @gpcsoft.property title="协议价"
	 */
	public BigDecimal getAgreePrice() {
		return agreePrice;
	}
	public void setAgreePrice(BigDecimal agreePrice) {
		this.agreePrice = agreePrice;
	}
	
	/**
	 * @gpcsoft.property title="市场价"
	 */
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	
	/**
	 * @gpcsoft.property title="计量单位"
	 */
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	
	/**
	 * @gpcsoft.property title="金额"
	 */
	public BigDecimal getGoodsTotal() {
		return goodsTotal;
	}
	public void setGoodsTotal(BigDecimal goodsTotal) {
		this.goodsTotal = goodsTotal;
	}
	
	/**
	 * @gpcsoft.property title="备注"
	 */
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * @gpcsoft.property title="采购商品选配"
	 */
	public Set<OrderGoodsOption> getOrderGoodsOptions() {
		return orderGoodsOptions;
	}
	public void setOrderGoodsOptions(Set<OrderGoodsOption> orderGoodsOptions) {
		this.orderGoodsOptions = orderGoodsOptions;
	}
	
	/** @gpcsoft.property title="采购商品礼包" */
	public Set<OrderGoodsGift> getOrderGoodsGifts() {
		return orderGoodsGifts;
	}
	public void setOrderGoodsGifts(Set<OrderGoodsGift> orderGoodsGifts) {
		this.orderGoodsGifts = orderGoodsGifts;
	}
	
	/**
	 * @gpcsoft.property title="订单条目和零配件关系"
	 */
	public Set<OrderGoodsAccessories> getOrderGoodsAccessories() {
		return orderGoodsAccessories;
	}
	public void setOrderGoodsAccessories(
			Set<OrderGoodsAccessories> orderGoodsAccessories) {
		this.orderGoodsAccessories = orderGoodsAccessories;
	}
	
	/**
	 * @gpcsoft.property title="任务书条目与订单条目关系"
	 */
	public Set<OrderProtask> getOrderProtasks() {
		return orderProtasks;
	}
	public void setOrderProtasks(Set<OrderProtask> orderProtasks) {
		this.orderProtasks = orderProtasks;
	}
	
	//任务书条目数量
	public Integer getOrderProtaskCount(){
		return this.orderProtaskCount;
	}

	public void setOrderProtaskCount(Integer orderProtaskCount) {
		this.orderProtaskCount = orderProtaskCount;
	}
	
	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setCreateTime(Date createTime) {
		// TODO Auto-generated method stub
		
	}
}