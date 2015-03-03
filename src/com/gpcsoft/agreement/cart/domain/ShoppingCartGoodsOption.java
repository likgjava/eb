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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.goods.goods.domain.GoodsOptionalFitting;


/** 
  *  Comments: <strong>采购商品选配</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 协议供货——购物车     		
  *  <br/>Create Date：2010-4-8 上午01:20:14 by yucy    					                            
  *  <br/>Modified Date:  2010-4-8 上午01:20:14 by yucy                                   
  *  
  *   @since 0.5
  *   @version: 0.5 
  *   
  *  @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.cart"
  *  @gpcsoft.page domain="cart" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="采购商品选配"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_CART_GOODS_OPTION")
public class ShoppingCartGoodsOption implements GpcBaseObject {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**主键*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "CART_OPT_ID", unique = true, nullable = false, length = 50)
	private String objId;
	
	/**购物车条目*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CART_DTL_ID")
	private ShoppingCartItem shoppingCartItem;
	
	/**商品库选配*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPTION_ID")
	private GoodsOptionalFitting option;
	
	/**单价*/
	@Column(name = "OPT_PRICE", precision = 16, scale = 6)
	private BigDecimal optPrice;
	
	/**数量*/
	@Column(name = "OPT_QTY", precision = 16, scale = 6)
	private BigDecimal optQty;
	
	/** 计量单位 */
//	@Column(name = "OPT_UNIT", length = 20)
	@Transient
	@Deprecated
	private String optUnit;
	
	/**市场价*/
//	@Column(name = "OPT_MARKET_PRICE", precision = 16, scale = 6)
	@Deprecated
	@Transient
	private BigDecimal optMarketPrice;
	
	/**选配协议价*/
	@Column(name = "OPT_AGREE_PRICE", precision = 16, scale = 6)
	private BigDecimal optAgreePrice;

	public void setObjId(String objId){
		this.objId = objId;
	}
	
	public String getObjId() {
		return this.objId;
	}

	/** @gpcsoft.property title="购物车条目"  */
	public ShoppingCartItem getShoppingCartItem() {
		return shoppingCartItem;
	}

	public void setShoppingCartItem(ShoppingCartItem shoppingCartItem) {
		this.shoppingCartItem = shoppingCartItem;
	}
	
	/** @gpcsoft.property title="商品库选配"  */
	public GoodsOptionalFitting getOption() {
		return option;
	}

	public void setOption(GoodsOptionalFitting option) {
		this.option = option;
	}

	/** @gpcsoft.property title="协议价"  */
	public BigDecimal getOptAgreePrice() {
		return optAgreePrice;
	}

	public void setOptAgreePrice(BigDecimal optAgreePrice) {
		this.optAgreePrice = optAgreePrice;
	}

	/** @gpcsoft.property title="选配价"  */
	public BigDecimal getOptPrice() {
		return optPrice;
	}

	public void setOptPrice(BigDecimal optPrice) {
		this.optPrice = optPrice;
	}

	/** @gpcsoft.property title="市场价"  */
	public BigDecimal getOptMarketPrice() {
		return optMarketPrice;
	}

	public void setOptMarketPrice(BigDecimal optMarketPrice) {
		this.optMarketPrice = optMarketPrice;
	}

	/** @gpcsoft.property title="数量"  */
	public BigDecimal getOptQty() {
		return optQty;
	}

	public void setOptQty(BigDecimal optQty) {
		this.optQty = optQty;
	}

	/** @gpcsoft.property title="计量单位"  */
	public String getOptUnit() {
		return optUnit;
	}

	public void setOptUnit(String optUnit) {
		this.optUnit = optUnit;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCreateTime(Date createTime) {
		// TODO Auto-generated method stub
		
	}

}