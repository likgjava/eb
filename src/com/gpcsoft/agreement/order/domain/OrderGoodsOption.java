package com.gpcsoft.agreement.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.goods.goods.domain.GoodsOptionalFitting;

/** 
  *  Comments: <strong>采购商品选配</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 协议供货-采购商品选配    		
  *  <br/>Create Date：2010-4-7 下午06:49:40 by sunl    					                            
  *  <br/>Modified Date:  2010-4-7 下午06:49:40 by sunl                           
  *          
  *  @since 0.5
  *  @version: 0.5 
  *  
  *  @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.order"
  *  @gpcsoft.page domain="order" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="采购商品选配"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_ORDER_GOODS_OPTION")
public class OrderGoodsOption implements GpcBaseObject {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@Id
	@Column(name = "ORDER_OPT_ID", length = 36)
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	private String objId;
	
	/** 订单明细 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_DTL_ID")
	@BatchSize(size = 15)
	private OrderItem orderItem;
	
	/** 商品库选配 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPTION_ID")
	@BatchSize(size = 15)
	private GoodsOptionalFitting optionalFitting;
	
	/** 计量单位 */
	@Column(name = "OPT_UNIT", length = 20)
	private String optUnit;
	
	/** 数量 */
	@Column(name = "OPT_QTY", precision = 16, scale = 6)
	private BigDecimal optQty;
	
	/** 选配价 */
	@Column(name = "OPT_PRICE", precision = 16, scale = 6)
	private BigDecimal optPrice;
	
	/** 选配协议价 */
	@Column(name = "OPT_AGREE_PRICE", precision = 16, scale = 6)
	private BigDecimal optAgreePrice;

	/** 市场价 */
//	@Column(name = "OPT_MARKET_PRICE", precision = 16, scale = 6)
	@Deprecated
	@Transient
	private BigDecimal optMarketPrice;
	
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
	 * @gpcsoft.property title="订单明细"
	 */
	public OrderItem getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
	
	/**
	 * @gpcsoft.property title="商品库选配"
	 */
	public GoodsOptionalFitting getOptionalFitting() {
		return optionalFitting;
	}
	public void setOptionalFitting(GoodsOptionalFitting optionalFitting) {
		this.optionalFitting = optionalFitting;
	}
	
	/**
	 * @gpcsoft.property title="选配价"
	 */
	public BigDecimal getOptPrice() {
		return optPrice;
	}

	public void setOptPrice(BigDecimal optPrice) {
		this.optPrice = optPrice;
	}
	
	/**
	 * @gpcsoft.property title="选配协议价"
	 */
	public BigDecimal getOptAgreePrice() {
		return optAgreePrice;
	}
	public void setOptAgreePrice(BigDecimal optAgreePrice) {
		this.optAgreePrice = optAgreePrice;
	}
	
	/** @gpcsoft.property title="计量单位"  */
	public String getOptUnit() {
		return optUnit;
	}
	public void setOptUnit(String optUnit) {
		this.optUnit = optUnit;
	}
	
	/** @gpcsoft.property title="数量"  */
	public BigDecimal getOptQty() {
		return optQty;
	}
	public void setOptQty(BigDecimal optQty) {
		this.optQty = optQty;
	}
	
	/** @gpcsoft.property title="市场价"  */
	public BigDecimal getOptMarketPrice() {
		return optMarketPrice;
	}
	public void setOptMarketPrice(BigDecimal optMarketPrice) {
		this.optMarketPrice = optMarketPrice;
	}
	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setCreateTime(Date createTime) {
		// TODO Auto-generated method stub
		
	}
	
	
}