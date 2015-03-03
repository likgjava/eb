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
import com.gpcsoft.goods.goods.domain.GoodsGift;

/** 
 *  Comments: <strong>采购商品礼包</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   agreement                    					          
 *  <br/>Module ID: 小额交易平台
 *  <br/>Create Date：2011-1-17 上午08:57:51 by likg    					                            
 *  <br/>Modified Date:  2011-1-17 上午08:57:51 by likg                                   
 *  <p>@since 0.5
 *   @version: 0.5 
 *   
 * @gpcsoft.package packageDir="com.gpcsoft.agreement"
 * @gpcsoft.page domain="cart"
 * @hibernate.class table="EPS_AGREE_CART_GOODS_GIFT"
 * @author Administrator
 * @version 1.0
 */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_CART_GOODS_GIFT")
public class ShoppingCartGoodsGift implements GpcBaseObject{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Column(name = "CART_GOODS_GIFT_ID", unique = true, nullable = false, length = 50)
	private String objId;
	
	/** 购物车条目 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CART_ITEM_ID")
	private ShoppingCartItem shoppingCartItem;
	
	/** 商品礼包 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODS_GIFT_ID")
	private GoodsGift goodsGift;
	
	/** 礼包价格 */
	@Column(name = "GIFT_PRICE")
	private BigDecimal giftPrice;

	public Date getCreateTime() {
		return null;
	}

	/** @gpcsoft.property title="礼包价格" */
	public BigDecimal getGiftPrice() {
		return giftPrice;
	}

	/** @gpcsoft.property title="商品礼包" */
	public GoodsGift getGoodsGift() {
		return goodsGift;
	}

	/** @gpcsoft.property title="主键" */
	public String getObjId() {
		return objId;
	}

	/** @gpcsoft.property title="购物车条目" */
	public ShoppingCartItem getShoppingCartItem() {
		return shoppingCartItem;
	}

	public void setCreateTime(Date arg0) {
		
	}

	public void setGiftPrice(BigDecimal giftPrice) {
		this.giftPrice = giftPrice;
	}

	public void setGoodsGift(GoodsGift goodsGift) {
		this.goodsGift = goodsGift;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setShoppingCartItem(ShoppingCartItem shoppingCartItem) {
		this.shoppingCartItem = shoppingCartItem;
	}
	
}
