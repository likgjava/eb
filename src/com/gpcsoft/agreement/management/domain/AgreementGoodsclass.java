package com.gpcsoft.agreement.management.domain;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
/** 
  *  Comments: <strong>协议分类</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 协议供货-协议单品    		
  *  <br/>Create Date：2010-4-16 下午03:43:13 by yucy    					                            
  *  <br/>Modified Date:  2010-4-16 下午03:43:13 by yucy                                   
  *  
  *   @since 0.5
  *   @version: 0.5 
  *   
  *  @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.management"
  *  @gpcsoft.page domain="management" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="协议分类"
  *   
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_PURCHASE_GOODSCLASS")
public class AgreementGoodsclass implements GpcBaseObject{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "AGREEMENT_CLASS_ID", unique = true, nullable = false, length = 50)
	private String objId;

	/**协议*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AGREEMENT_ID")	
	private Agreement agreement;
	
	/**二级协议*/
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
	
	/**折扣率*/
	@Column(name = "DISCOUNT_RATIO", precision = 16, scale = 6)
	private BigDecimal discountRatio;
	
	/**备注*/
	@Column(name = "MEMO", length = 200)
	private String memo;
	
	/** 协议分类下协议商品 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "AGREEMENT_CLASS_ID") 
	private Set<AgreementGoods> agreementGoods = new HashSet<AgreementGoods>();
	
	/**商品库中此商品类别下是否有新增的商品*/
    @Transient
	private String haveNew;
	
	
	public void setObjId(String objId){
		this.objId = objId;
	}
	
	public String getObjId() {
		return this.objId;
	}

	/** @gpcsoft.property title="协议"  */
	public Agreement getAgreement() {
		return agreement;
	}

	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}
	/** @gpcsoft.property title="二级协议"  */
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
	/** @gpcsoft.property title="折扣率"  */
	public BigDecimal getDiscountRatio() {
		return discountRatio;
	}

	public void setDiscountRatio(BigDecimal discountRatio) {
		this.discountRatio = discountRatio;
	}
	/** @gpcsoft.property title="备注"  */
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	/** @gpcsoft.property title="协议分类下协议商品" */
	public Set<AgreementGoods> getAgreementGoods() {
		return agreementGoods;
	}

	public void setAgreementGoods(Set<AgreementGoods> agreementGoods) {
		this.agreementGoods = agreementGoods;
	}
	
	public String getHaveNew() {
		return haveNew;
	}

	public void setHaveNew(String haveNew) {
		this.haveNew = haveNew;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCreateTime(Date createTime) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}