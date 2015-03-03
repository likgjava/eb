package com.gpcsoft.agreement.bargin.require.domain;
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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

/** 
 *  Comments: <strong>需求条目</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   agreement                    					          
 *  <br/>Module ID: 协议供货   		
 *  <br/>Create Date：2010-4-7 下午03:50:06 by yucy    					                            
 *  <br/>Modified Date:  2010-4-7 下午03:50:06 by yucy           
 *                          
 *  @since 0.5
 *  @version: 0.5 
 *  
 *  @gpcsoft.package packageDir="com.gpcsoft.agreement.bargin.require.domain"
 *  @gpcsoft.page domain="ebargain" project="agreement"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="需求条目"
 */ 

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREEMENT_REQUIRE_ITEM")
public class RequireItem implements GpcBaseObject {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -3922965995626701072L;

	/**记录号*/
    @Id
    @Column(name = "REQUIRE_DTL_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
	
    /**项目*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="PROJECT_ID") 
    @BatchSize(size = 15)
    private Project project;
    
    /**商品*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="GOODS_ID") 
    @BatchSize(size = 15)
    private Goods goods;
    
    /**商品分类*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="GOODS_CLASS_ID") 
    @BatchSize(size = 15)
    private GoodsClass goodsClass;
    
    /**商品品目*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="PUR_CATEGORY_ID")
    @BatchSize(size = 15)
    private PurCategory purCategory;
    
    /**数量*/
	@Column(name = "GOODS_QTY", precision = 16, scale = 6)
    private BigDecimal goodsQty;

	/**计量单位*/
	@Column(name = "GOODS_UNIT")
	private String goodsUnit;
	
	/**协议价*/
	@Column(name = "AGREE_PRICE", precision = 16, scale = 6)
    private BigDecimal agreePrice;
	
	/**市场价*/
	@Column(name = "MARKET_PRICE", precision = 16, scale = 6)
    private BigDecimal marketPrice;
	
	/**单价*/
	@Column(name = "GOODS_PRICE", precision = 16, scale = 6)
    private BigDecimal goodsPrice;

	/**金额*/
	@Column(name = "GOODS_TOTAL", precision = 16, scale = 6)
    private BigDecimal goodsTotal;
	
	/**商品描述*/
	@Column(name = "GOODS_DESCR")
	private String descr;

	/**备注*/
	@Column(name = "MEMO")
	private String remark;
	
	 /**需求商品选配*/
    @LazyCollection(value=LazyCollectionOption.EXTRA)
    @JoinColumn(name = "REQUIRE_DTL_ID") 
    @OneToMany
    @Cascade({CascadeType.ALL})
    private Set<RequireGoodsOpt> requireGoodsOpt = new HashSet<RequireGoodsOpt>();
    
    /** 需求商品礼包 */
    @LazyCollection(value=LazyCollectionOption.EXTRA)
    @JoinColumn(name = "REQUIRE_ITEM_ID") 
    @OneToMany
    @Cascade({CascadeType.ALL})
    private Set<RequireGoodsGift> requireGoodsGifts = new HashSet<RequireGoodsGift>();

    /**需求商品零配件*/
    @LazyCollection(value=LazyCollectionOption.EXTRA)
    @JoinColumn(name = "REQUIRE_DTL_ID") 
    @OneToMany
    @Cascade({CascadeType.ALL})
    private Set<RequireGoodsAccessories> requireGoodsAccess = new HashSet<RequireGoodsAccessories>();

	public Set<RequireGoodsAccessories> getRequireGoodsAccess() {
		return requireGoodsAccess;
	}

	public void setRequireGoodsAccess(
			Set<RequireGoodsAccessories> requireGoodsAccess) {
		this.requireGoodsAccess = requireGoodsAccess;
	}
	
	public BigDecimal getAgreePrice() {
		return agreePrice;
	}

	public Date getCreateTime() {
		return null;
	}

	public String getDescr() {
		return descr;
	}

	public Goods getGoods() {
		return goods;
	}

	public GoodsClass getGoodsClass() {
		return goodsClass;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public BigDecimal getGoodsQty() {
		return goodsQty;
	}

	public BigDecimal getGoodsTotal() {
		return goodsTotal;
	}
	
	public String getGoodsUnit() {
		return goodsUnit;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public String getObjId() {
		return objId;
	}

	public Project getProject() {
		return project;
	}

	public PurCategory getPurCategory() {
		return purCategory;
	}

	public String getRemark() {
		return remark;
	}

	/** @gpcsoft.property title="需求商品礼包" */
	public Set<RequireGoodsGift> getRequireGoodsGifts() {
		return requireGoodsGifts;
	}

	/** @gpcsoft.property title="需求商品选配" */
	public Set<RequireGoodsOpt> getRequireGoodsOpt() {
		return requireGoodsOpt;
	}

	public void setAgreePrice(BigDecimal agreePrice) {
		this.agreePrice = agreePrice;
	}

	public void setCreateTime(Date arg0) {
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public void setGoodsClass(GoodsClass goodsClass) {
		this.goodsClass = goodsClass;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public void setGoodsQty(BigDecimal goodsQty) {
		this.goodsQty = goodsQty;
	}

	public void setGoodsTotal(BigDecimal goodsTotal) {
		this.goodsTotal = goodsTotal;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setPurCategory(PurCategory purCategory) {
		this.purCategory = purCategory;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setRequireGoodsGifts(Set<RequireGoodsGift> requireGoodsGifts) {
		this.requireGoodsGifts = requireGoodsGifts;
	}

	public void setRequireGoodsOpt(Set<RequireGoodsOpt> requireGoodsOpt) {
		this.requireGoodsOpt = requireGoodsOpt;
	}
	
}