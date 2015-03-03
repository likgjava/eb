package com.gpcsoft.agreement.management.domain;

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
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

/**
 * EpsAgreePurchaseGoods entity. @author MyEclipse Persistence Tools
 */
/** 
  *  Comments: <strong>协议商品</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 协议供货-协议单品     		
  *  <br/>Create Date：2010-4-16 下午03:28:20 by yucy    					                            
  *  <br/>Modified Date:  2010-4-16 下午03:28:20 by yucy                                   
  *  
  *   @since 0.5
  *   @version: 0.5 
  *   
  *  @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.management"
  *  @gpcsoft.page domain="management" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="协议单品"
  *   
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_PURCHASE_GOODS")
public class AgreementGoods implements GpcBaseObject {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "AGREEMENT_GOODS_ID", unique = true, nullable = false, length = 50)
	private String objId;

	/**一级协议ID*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AGREEMENT_ID")	
	private Agreement agreement;
	
	/**协议商品分类*/
	@ManyToOne(fetch = FetchType.LAZY ,optional=true)
	@JoinColumn(name = "AGREEMENT_CLASS_ID")	
	@BatchSize(size = 15)
	private AgreementGoodsclass agreementGoodsclass;
	
	/**二级协议ID*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SECOND_AGREEMENT_ID")	
	private AgreementSecond agreementSecond;
	
	/**协议类型*/
	@Column(name = "AGREEMENT_TYPE", length = 2)	
	private String agreementType;
	
	
	/**分类*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODSCLASS_ID")	
	private GoodsClass goodsClass;
	
	/**品牌*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRAND_ID")	
	private GoodsBrand brand;
	
	/**商品*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODS_ID")	
	private Goods goods;
	
	
	/**折扣率*/
	@Column(name = "DISCOUNT_RATIO", precision = 16, scale = 6)
	private BigDecimal discountRatio;
	
	/**协议价*/
	@Column(name = "AGREEMENT_PRICE", precision = 16, scale = 6)
	private BigDecimal agreementPrice;
	
	/**市场价*/
	@Column(name = "MARKET_PRICE", precision = 16, scale = 6)
	private BigDecimal marketPrice;
	
	/**备注*/
	@Column(name = "MEMO", length = 200)
	private String memo;

	public void setObjId(String objId){
		this.objId = objId;
	}
	public String getObjId() {
		return this.objId;
	}
	
	/** @gpcsoft.property title="一级协议ID"  */
	public Agreement getAgreement() {
		return agreement;
	}

	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}

	/** @gpcsoft.property title="协议商品分类"  */
	public AgreementGoodsclass getAgreementGoodsclass() {
		return agreementGoodsclass;
	}

	public void setAgreementGoodsclass(AgreementGoodsclass agreementGoodsclass) {
		this.agreementGoodsclass = agreementGoodsclass;
	}
	/** @gpcsoft.property title="二级协议ID"  */
	public AgreementSecond getAgreementSecond() {
		return agreementSecond;
	}

	public void setAgreementSecond(AgreementSecond agreementSecond) {
		this.agreementSecond = agreementSecond;
	}
	/** @gpcsoft.property title="协议类型"  */
	public String getAgreementType() {
		return agreementType;
	}

	public void setAgreementType(String agreementType) {
		this.agreementType = agreementType;
	}
	/** @gpcsoft.property title="分类"  */
	public GoodsClass getGoodsClass() {
		return goodsClass;
	}

	public void setGoodsClass(GoodsClass goodsClass) {
		this.goodsClass = goodsClass;
	}

	/** @gpcsoft.property title="品牌"  */
	public GoodsBrand getBrand() {
		return brand;
	}

	public void setBrand(GoodsBrand brand) {
		this.brand = brand;
	}
	/** @gpcsoft.property title="商品"  */
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	/** @gpcsoft.property title="折扣率"  */
	public BigDecimal getDiscountRatio() {
		return discountRatio;
	}

	public void setDiscountRatio(BigDecimal discountRatio) {
		this.discountRatio = discountRatio;
	}
	/** @gpcsoft.property title="协议价"  */
	public BigDecimal getAgreementPrice() {
		return agreementPrice;
	}

	public void setAgreementPrice(BigDecimal agreementPrice) {
		this.agreementPrice = agreementPrice;
	}
	/** @gpcsoft.property title="市场价"  */
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	/** @gpcsoft.property title="备注"  */
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setCreateTime(Date createTime) {
		// TODO Auto-generated method stub
		
	}

	
}