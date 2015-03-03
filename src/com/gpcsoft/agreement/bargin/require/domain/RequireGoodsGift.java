package com.gpcsoft.agreement.bargin.require.domain;

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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.goods.goods.domain.GoodsGift;

/** 
 *  Comments: <strong>需求商品礼包</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   agreement                    					          
 *  <br/>Module ID: 协议供货   		
 *  <br/>Create Date：2011-1-24 上午10:13:06 by likg    					                            
 *  <br/>Modified Date:  2011-1-24 上午10:13:06 by likg            
 *                          
 *  @since 0.5
 *  @version: 0.5 
 *  
 *  @gpcsoft.package packageDir="com.gpcsoft.agreement.bargin.require.domain"
 *  @gpcsoft.page domain="require" project="agreement"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="需求商品礼包"
 */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_REQUIRE_GOODS_GIFT")
public class RequireGoodsGift implements GpcBaseObject {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Column(name = "REQUIRE_GOODS_GIFT_ID", unique = true, nullable = false, length = 50)
	private String objId;
	
	/** 需求条目 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="REQUIRE_ITEM_ID") 
    @BatchSize(size = 15)
    private RequireItem requireItem;
	
	/** 商品礼包 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODS_GIFT_ID")
	private GoodsGift goodsGift;
	
	/** 礼包价格 */
	@Column(name = "GIFT_PRICE")
	private BigDecimal giftPrice;

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

	/** @gpcsoft.property title="需求条目" */
	public RequireItem getRequireItem() {
		return requireItem;
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

	public void setRequireItem(RequireItem requireItem) {
		this.requireItem = requireItem;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date arg0) {
	}
	
}
