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
import com.gpcsoft.goods.goods.domain.GoodsOptionalFitting;

/** 
 *  Comments: <strong>需求商品选配</strong>            		
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
 *  @gpcsoft.page domain="require" project="agreement"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="需求商品选配"
 */ 

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_REQUIRE_GOODS_OPTION")
public class RequireGoodsOpt implements GpcBaseObject {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1249379273192449573L;

	/**记录号*/
    @Id
    @Column(name = "REQUIRE_OPT_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
	
    /**选配*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="OPTION_ID") 
    @BatchSize(size = 15)
    private GoodsOptionalFitting optionalFitting;
    
    /**需求明细*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="REQUIRE_DTL_ID") 
    @BatchSize(size = 15)
    private RequireItem requireItem;
    
    /**数量*/
	@Column(name = "OPT_QTY", precision = 16, scale = 6)
    private BigDecimal optQty;

	/**计量单位*/
	@Column(name = "OPT_UNIT")
	private String optUnit;
	
	/**选配价*/
	@Column(name = "OPT_PRICE", precision = 16, scale = 6)
    private BigDecimal optPrice;
	
	/**选配市场价*/
	@Column(name = "OPT_MARKET_PRICE", precision = 16, scale = 6)
    private BigDecimal optMarketPrice;
	
	/**选配协议价*/
	@Column(name = "OPT_AGREE_PRICE", precision = 16, scale = 6)
    private BigDecimal optAgreePrice;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public GoodsOptionalFitting getOptionalFitting() {
		return optionalFitting;
	}

	public void setOptionalFitting(GoodsOptionalFitting optionalFitting) {
		this.optionalFitting = optionalFitting;
	}

	public RequireItem getRequireItem() {
		return requireItem;
	}

	public void setRequireItem(RequireItem requireItem) {
		this.requireItem = requireItem;
	}

	public BigDecimal getOptQty() {
		return optQty;
	}

	public void setOptQty(BigDecimal optQty) {
		this.optQty = optQty;
	}

	public String getOptUnit() {
		return optUnit;
	}

	public void setOptUnit(String optUnit) {
		this.optUnit = optUnit;
	}

	public BigDecimal getOptPrice() {
		return optPrice;
	}

	public void setOptPrice(BigDecimal optPrice) {
		this.optPrice = optPrice;
	}

	public BigDecimal getOptMarketPrice() {
		return optMarketPrice;
	}

	public void setOptMarketPrice(BigDecimal optMarketPrice) {
		this.optMarketPrice = optMarketPrice;
	}

	public BigDecimal getOptAgreePrice() {
		return optAgreePrice;
	}

	public void setOptAgreePrice(BigDecimal optAgreePrice) {
		this.optAgreePrice = optAgreePrice;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date arg0) {
	}
	
}