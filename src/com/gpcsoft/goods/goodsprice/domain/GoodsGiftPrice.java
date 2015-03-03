package com.gpcsoft.goods.goodsprice.domain;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.goods.goods.domain.GoodsGift;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>商品礼包价格</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   agreement                    					          
 *  <br/>Module ID: 小额交易平台
 *  <br/>Create Date：2011-1-7 上午08:57:51 by likg    					                            
 *  <br/>Modified Date:  2011-1-7 上午08:57:51 by likg                                   
 *  <p>@since 0.5
 *   @version: 0.5 
 *   
 * @gpcsoft.package packageDir="com.gpcsoft.goods"
 * @gpcsoft.page domain="goods/goodsprice"
 * @hibernate.class table="GOODS_GIFT_PRICE"
 * @author Administrator
 * @version 1.0
 */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "GOODS_GIFT_PRICE")
public class GoodsGiftPrice implements GpcBaseObject,IPropertyCUserTime {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 主键 */
    @Id
    @Column(name = "GOODS_GIFT_PRICE_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /** 商品礼包 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODS_GIFT_ID")
	@BatchSize(size = 15)
	private GoodsGift goodsGift;
	
	/** 行情供应商 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name = "PRICE_SUPPLIER_ID")
	@BatchSize(size = 15)
	private GoodsPriceSupplier goodsPriceSupplier;
	
	/** 商品行情 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="GOODS_PRICE_ID")
    @BatchSize(size = 15)
	private GoodsPrice goodsPrice;
    
    /** 礼包价格 */
	@Column(name = "GIFT_PRICE")
	private BigDecimal giftPrice;
	
    /** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="CREATOR_ID") 
    @BatchSize(size = 15)
    private User createUser;
    
    /** 创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    /** 修改人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="MODIFIER_ID")
    @BatchSize(size = 15)
    private User updateUser;
    
    /** 修改时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_TIME")
    private Date updateTime;

	public Date getCreateTime() {
		return createTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public BigDecimal getGiftPrice() {
		return giftPrice;
	}

	public GoodsGift getGoodsGift() {
		return goodsGift;
	}

	public GoodsPrice getGoodsPrice() {
		return goodsPrice;
	}

	public GoodsPriceSupplier getGoodsPriceSupplier() {
		return goodsPriceSupplier;
	}

	public String getObjId() {
		return objId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public void setGiftPrice(BigDecimal giftPrice) {
		this.giftPrice = giftPrice;
	}

	public void setGoodsGift(GoodsGift goodsGift) {
		this.goodsGift = goodsGift;
	}

	public void setGoodsPrice(GoodsPrice goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public void setGoodsPriceSupplier(GoodsPriceSupplier goodsPriceSupplier) {
		this.goodsPriceSupplier = goodsPriceSupplier;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}
    
}
