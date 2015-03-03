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

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.goods.goods.domain.GoodsAccessories;

/** 
 *  Comments: <strong>零配件和订单条目关系</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   agreement                    					          
 *  <br/>Module ID: 小额交易平台
 *  <br/>Create Date：2011-1-19 上午08:57:51 by likg    					                            
 *  <br/>Modified Date:  2011-1-19 上午08:57:51 by likg                                   
 *  <p>@since 0.5
 *   @version: 0.5 
 *   
 * @gpcsoft.package packageDir="com.gpcsoft.agreement.order"
 * @gpcsoft.page domain="order"
 * @hibernate.class table="EPS_AGREE_ORDER_GOODS_ACCESS"
 * @author Administrator
 * @version 1.0
 */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_ORDER_GOODS_ACCESS")
public class OrderGoodsAccessories implements GpcBaseObject{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Column(name = "ORDER_GOODS_ACCESS_ID", unique = true, nullable = false, length = 50)
	private String objId;
	
	/** 订单条目 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ITEM_ID")
	private OrderItem orderItem;
	
	/** 零配件 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODS_ACCESSORIES_ID")
	private GoodsAccessories goodsAccess;
	
	/** 零配件价格 */
	@Column(name = "ACCESS_PRICE")
	private BigDecimal accessPrice;

	/** @gpcsoft.property title="零配件价格" */
	public BigDecimal getAccessPrice() {
		return accessPrice;
	}

	/** @gpcsoft.property title="商品零配件" */
	public GoodsAccessories getGoodsAccess() {
		return goodsAccess;
	}

	/** @gpcsoft.property title="主键" */
	public String getObjId() {
		return objId;
	}

	/** @gpcsoft.property title="订单条目" */
	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setAccessPrice(BigDecimal accessPrice) {
		this.accessPrice = accessPrice;
	}

	public void setGoodsAccess(GoodsAccessories goodsAccess) {
		this.goodsAccess = goodsAccess;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCreateTime(Date arg0) {
		// TODO Auto-generated method stub
	}
}
