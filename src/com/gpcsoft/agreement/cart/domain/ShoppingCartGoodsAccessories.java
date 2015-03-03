package com.gpcsoft.agreement.cart.domain;

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
 *  Comments: <strong>零配件_购物车条目</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   agreement                    					          
 *  <br/>Module ID: 小额交易平台
 *  <br/>Create Date：2011-1-17 上午08:57:51 by sunl    					                            
 *  <br/>Modified Date:  2011-1-17 上午08:57:51 by sunl                                   
 *  <p>@since 0.5
 *   @version: 0.5 
 *   
 * @gpcsoft.package packageDir="com.gpcsoft.agreement.cart"
 * @gpcsoft.page domain="cart"
 * @hibernate.class table="EPS_AGREE_CART_GOODS_ACCESS"
 * @author Administrator
 * @version 1.0
 */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_CART_GOODS_ACCESS")
public class ShoppingCartGoodsAccessories implements GpcBaseObject{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Column(name = "GOODS_ACCESS_CART_ITEM_ID", unique = true, nullable = false, length = 50)
	private String objId;
	
	/** 购物车条目 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CART_ITEM_ID")
	private ShoppingCartItem shoppingCartItem;
	
	/** 零配件 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODS_ACCESS_ID")
	private GoodsAccessories goodsAccess;
	
	/** 零配件价格 */
	@Column(name = "ACCESS_PRICE")
	private BigDecimal accessPrice;

	/** @gpcsoft.property title="编号" */
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/** @gpcsoft.property title="购物车条目" */
	public ShoppingCartItem getShoppingCartItem() {
		return shoppingCartItem;
	}

	public void setShoppingCartItem(ShoppingCartItem shoppingCartItem) {
		this.shoppingCartItem = shoppingCartItem;
	}

	/** @gpcsoft.property title="零配件" */
	public GoodsAccessories getGoodsAccess() {
		return goodsAccess;
	}

	public void setGoodsAccess(GoodsAccessories goodsAccess) {
		this.goodsAccess = goodsAccess;
	}

	/** @gpcsoft.property title="零配件价格" */
	public BigDecimal getAccessPrice() {
		return accessPrice;
	}

	public void setAccessPrice(BigDecimal accessPrice) {
		this.accessPrice = accessPrice;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setCreateTime(Date arg0) {
		// TODO Auto-generated method stub
	}
}
