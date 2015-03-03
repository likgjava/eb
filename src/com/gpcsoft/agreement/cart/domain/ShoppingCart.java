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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.srplatform.auth.domain.User;


/** 
  *  Comments: <strong>购物车</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 协议供货--购物车     		
  *  <br/>Create Date：2010-4-8 上午01:57:19 by yucy    					                            
  *  <br/>Modified Date:  2010-4-8 上午01:57:19 by yucy                                   
  *  
  *   @since 0.5
  *   @version: 0.5 
  *   
  *  @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.cart"
  *  @gpcsoft.page domain="cart" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="购物车"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREEMENT_CART")
public class ShoppingCart implements GpcBaseObject, IPropertyCUserTime{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** createUser */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CRE_USER_ID")
	private User createUser;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRE_TIME")
	private Date createTime;
	
	/**主键*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "CART_ID", unique = true, nullable = false, length = 50)
	private String objId;

	/**采购人*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUYER_ID")
	private OrgInfo buyer;

	/**编号*/
	@Column(name = "CART_NO", length = 15)
	private String cartNo;

	/**总数量*/
	@Column(name = "GOODS_QTY", precision = 16, scale = 6)
	private BigDecimal goodsQty;

	/**总金额*/
	@Column(name = "GOODS_TOTAL", precision = 16, scale = 6)
	private BigDecimal goodsTotal;
	
	/**购物车条目*/
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "CART_ID") 
	private Set<ShoppingCartItem> cartItems=new HashSet<ShoppingCartItem>();
	
	public void setObjId(String objId){
		this.objId = objId;
	}
	
	public String getObjId() {
		return this.objId;
	}

	/** @gpcsoft.property title="采购人"  */
	public OrgInfo getBuyer() {
		return buyer;
	}

	public void setBuyer(OrgInfo buyer) {
		this.buyer = buyer;
	}

	/** @gpcsoft.property title="编号"  */
	public String getCartNo() {
		return cartNo;
	}

	public void setCartNo(String cartNo) {
		this.cartNo = cartNo;
	}

	/** @gpcsoft.property title="总数量"  */
	public BigDecimal getGoodsQty() {
		return goodsQty;
	}

	public void setGoodsQty(BigDecimal goodsQty) {
		this.goodsQty = goodsQty;
	}

	/** @gpcsoft.property title="总金额"  */
	public BigDecimal getGoodsTotal() {
		return goodsTotal;
	}

	public void setGoodsTotal(BigDecimal goodsTotal) {
		this.goodsTotal = goodsTotal;
	}

	/**
	 * @gpcsoft.property title="cartItems"
	 */
	public Set<ShoppingCartItem> getCartItems() {
		return cartItems;
	}

	/** cartItems */
	public void setCartItems(Set<ShoppingCartItem> cartItems) {
		this.cartItems = cartItems;
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