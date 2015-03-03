package com.gpcsoft.agreement.cart.domain;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.agreement.order.domain.ProtaskItem;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>购物车条目</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 协议供货--购物车     		
  *  <br/>Create Date：2010-4-8 上午01:35:05 by yucy    					                            
  *  <br/>Modified Date:  2010-4-8 上午01:35:05 by yucy                                   
  *  
  *   @since 0.5
  *   @version: 0.5 
  *   
  *  @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.cart"
  *  @gpcsoft.page domain="cart" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="购物车条目"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREEMENT_CART_ITEM")
public class ShoppingCartItem implements GpcBaseObject, IPropertyCUserTime {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** createUser */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CRE_USER_ID")
	private User createUser;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRE_TIME")
	private Date createTime;

	/** 主键*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "CART_DTL_ID", unique = true, nullable = false, length = 50)
	private String objId;
	
	/** 购物车*/
    @BatchSize(size = 15)//批量抓取
	@ManyToOne(fetch = FetchType.LAZY, optional=true)
	@JoinColumn(name = "CART_ID")
	private ShoppingCart shoppingCart;
	
	/** 供应商*/	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPLIER_ID")
	private OrgInfo supplier;
	
	/** 商品*/	
	@JoinColumn(name = "GOODS_ID")
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @BatchSize(size = 15)//批量抓取
	private Goods goods;
	
	/** 任务书条目*/
	@JoinColumn(name = "TASK_ITEM_ID")
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @BatchSize(size = 15)//批量抓取
	private ProtaskItem protaskItem;
	
	/** 数量*/
	@Column(name = "GOODS_QTY", precision = 16, scale = 6)
	private BigDecimal goodsQty;
	
	/** 单价*/
	@Column(name = "GOODS_PRICE", precision = 16, scale = 6)
	private BigDecimal goodsPrice;
	
	/** 市场价*/
	@Column(name = "MARKET_PRICE", precision = 16, scale = 6)
	private BigDecimal marketPrice;
	
	/** 计量单位 */
	@Column(name = "GOODS_UNIT", length = 20)
	private String goodsUnit;
	
	/** 协议价*/
	@Column(name = "AGREE_PRICE", precision = 16, scale = 6)
	private BigDecimal agreePrice;
	
	/** 金额*/
	@Column(name = "GOODS_TOTAL", precision = 16, scale = 6)
	private BigDecimal goodsTotal;
	
	/** 备注*/
	@Column(name = "MEMO", length = 200)
	private String memo;
	
	/** 选配*/
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany//相当于inverse="false"   以ShoppingCartItem为主
	@JoinColumn(name = "CART_DTL_ID") 
	@Cascade({CascadeType.SAVE_UPDATE,CascadeType.DELETE_ORPHAN})
	private Set<ShoppingCartGoodsOption> cartGoodsOptions = new HashSet<ShoppingCartGoodsOption>();
	
	/** 采购商品礼包 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany//相当于inverse="false"   以ShoppingCartItem为主
	@JoinColumn(name = "CART_ITEM_ID") 
	@Cascade({CascadeType.SAVE_UPDATE,CascadeType.DELETE_ORPHAN})
	private Set<ShoppingCartGoodsGift> cartGoodsGifts = new HashSet<ShoppingCartGoodsGift>();
	
	/** 零配件 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany//相当于inverse="false"   以ShoppingCartItem为主
	@JoinColumn(name = "CART_ITEM_ID") 
	@Cascade({CascadeType.SAVE_UPDATE,CascadeType.DELETE_ORPHAN})
	private Set<ShoppingCartGoodsAccessories> cartGoodsAccessories = new HashSet<ShoppingCartGoodsAccessories>();

	public void setObjId(String objId){ 
		this.objId = objId;
	}
	
	public String getObjId() {
		return this.objId;
	}
	
	/** @gpcsoft.property title="购物车"  */
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	/** @gpcsoft.property title="供应商"  */
	public OrgInfo getSupplier() {
		return supplier;
	}

	public void setSupplier(OrgInfo supplier) {
		this.supplier = supplier;
	}

	/** @gpcsoft.property title="商品"  */
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	/** @gpcsoft.property title="数量"  */
	public BigDecimal getGoodsQty() {
		return goodsQty;
	}

	public void setGoodsQty(BigDecimal goodsQty) {
		this.goodsQty = goodsQty;
	}

	/** @gpcsoft.property title="单价"  */
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	/** @gpcsoft.property title="市场价"  */
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	
	/** @gpcsoft.property title="协议价"  */
	public BigDecimal getAgreePrice() {
		return agreePrice;
	}

	public void setAgreePrice(BigDecimal agreePrice) {
		this.agreePrice = agreePrice;
	}

	/** @gpcsoft.property title="金额"  */
	public BigDecimal getGoodsTotal() {
		return goodsTotal;
	}

	public void setGoodsTotal(BigDecimal goodsTotal) {
		this.goodsTotal = goodsTotal;
	}

	/** @gpcsoft.property title="备注"  */
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	/** @gpcsoft.property title="计量单位"  */
	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	
	/** @gpcsoft.property title="任务书条目"  */
	public ProtaskItem getProtaskItem() {
		return protaskItem;
	}

	public void setProtaskItem(ProtaskItem protaskItem) {
		this.protaskItem = protaskItem;
	}

	/**
	 * @gpcsoft.property title="cartGoodsOptions"
	 */
	public Set<ShoppingCartGoodsOption> getCartGoodsOptions() {
		return cartGoodsOptions;
	}

	/** cartGoodsOptions */
	public void setCartGoodsOptions(Set<ShoppingCartGoodsOption> cartGoodsOptions) {
		this.cartGoodsOptions = cartGoodsOptions;
	}

	/** @gpcsoft.property title="采购商品礼包" */
	public Set<ShoppingCartGoodsGift> getCartGoodsGifts() {
		return cartGoodsGifts;
	}

	public void setCartGoodsGifts(Set<ShoppingCartGoodsGift> cartGoodsGifts) {
		this.cartGoodsGifts = cartGoodsGifts;
	}

	/** @gpcsoft.property title="采购商品零配件" */
	public Set<ShoppingCartGoodsAccessories> getCartGoodsAccessories() {
		return cartGoodsAccessories;
	}

	public void setCartGoodsAccessories(
			Set<ShoppingCartGoodsAccessories> cartGoodsAccessories) {
		this.cartGoodsAccessories = cartGoodsAccessories;
	}

	/**
	 * @gpcsoft.property title="createUser"
	 */
	public User getCreateUser() {
		return createUser;
	}

	/** createUser */
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	/**
	 * @gpcsoft.property title="createTime"
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/** createTime */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
 
}