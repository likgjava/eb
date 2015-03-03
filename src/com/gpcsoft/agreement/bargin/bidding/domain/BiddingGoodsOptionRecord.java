package com.gpcsoft.agreement.bargin.bidding.domain;

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

import com.gpcsoft.agreement.bargin.require.domain.RequireGoodsOpt;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.goods.goods.domain.GoodsOptionalFitting;

/** 
  *  Comments: <strong>需求商品选配记录</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 协议供货--议价   		
  *  <br/>Create Date：2010-4-7 下午03:50:06 by yucy    					                            
  *  <br/>Modified Date:  2010-4-7 下午03:50:06 by yucy     
  *                               
  *   @since 0.5
  *   @version: 0.5 
  *   
  *  @gpcsoft.package packageDir="com.gpcsoft.agreement.bargin.barginrecord.domain"
  *  @gpcsoft.page domain="barginrecord" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="需求商品选配记录"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_BARGAIN_RECORD_OPT")
public class BiddingGoodsOptionRecord implements GpcBaseObject {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 2724051270320248021L;

	/**记录号*/
    @Id
    @Column(name = "BARGAIN_RECORD_OPT_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
	
	/**议价商品明细*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="REQUIRE_OPT_ID") 
    @BatchSize(size = 15)
	private RequireGoodsOpt requireGoodsOpt;
    
	/**议价记录明细*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="BARGAIN_RECORD_DTL_ID") 
    @BatchSize(size = 15)
	private BiddingRecordItem bargainRecordItem;
    
    /**选配*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="OPTION_ID") 
    @BatchSize(size = 15)
    private GoodsOptionalFitting optionalFitting;
    
	/**数量*/
	@Column(name = "OPT_QTY", precision = 16, scale = 6)
	private BigDecimal optQty;
	
	/**选配价*/
	@Column(name = "OPT_PRICE", precision = 16, scale = 6)
	private BigDecimal optPrice;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public RequireGoodsOpt getRequireGoodsOpt() {
		return requireGoodsOpt;
	}

	public void setRequireGoodsOpt(RequireGoodsOpt requireGoodsOpt) {
		this.requireGoodsOpt = requireGoodsOpt;
	}

	public BiddingRecordItem getBargainRecordItem() {
		return bargainRecordItem;
	}

	public void setBargainRecordItem(BiddingRecordItem bargainRecordItem) {
		this.bargainRecordItem = bargainRecordItem;
	}

	public GoodsOptionalFitting getOptionalFitting() {
		return optionalFitting;
	}

	public void setOptionalFitting(GoodsOptionalFitting optionalFitting) {
		this.optionalFitting = optionalFitting;
	}

	public BigDecimal getOptQty() {
		return optQty;
	}

	public void setOptQty(BigDecimal optQty) {
		this.optQty = optQty;
	}

	public BigDecimal getOptPrice() {
		return optPrice;
	}

	public void setOptPrice(BigDecimal optPrice) {
		this.optPrice = optPrice;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date arg0) {
	}
}