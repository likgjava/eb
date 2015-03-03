package com.gpcsoft.agreement.bargin.bidding.domain;
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
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.agreement.bargin.require.domain.RequireItem;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.goods.goods.domain.Goods;

/** 
 *  Comments: <strong>议价明细记录</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   agreement                    					          
 *  <br/>Module ID: 协议供货--议价   		
 *  <br/>Create Date：2010-4-7 下午03:50:06 by yucy    					                            
 *  <br/>Modified Date:  2010-4-7 下午03:50:06 by yucy           
 *                          
 *  @since 0.5
 *  @version: 0.5 
 *  
 *  @gpcsoft.package packageDir="com.gpcsoft.agreement.bargin.barginrecord.domain"
 *  @gpcsoft.page domain="barginrecord" project="agreement"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="议价明细记录"
 */ 

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_BARGAIN_RECORD_ITEM")

public class BiddingRecordItem implements GpcBaseObject {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 3570158841706716664L;

	/**记录号*/
    @Id
    @Column(name = "BARGAIN_RECORD_DTL_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
	
	/**议价记录*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="BARGAIN_RECORD_ID") 
    @BatchSize(size = 15)
    private BiddingRecord biddingRecord;
	
	/**需求条目*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="REQUIRE_DTL_ID") 
    @BatchSize(size = 15)
    private RequireItem requireItem;
    
    /**商品*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="GOODS_ID") 
    @BatchSize(size = 15)
    private Goods goods;
    
	/** 单价 */
	@Column(name = "GOODS_PRICE", precision = 16, scale = 6)
	private BigDecimal goodsPrice;
	
	/**金额*/
	@Column(name = "GOODS_TOTAL", precision = 16, scale = 6)
	private BigDecimal goodsTotal;
	
	/**服务内容*/
	@Column(name = "SERVICE_CONTENT")
	private String serviceContent;
	
	/**备注*/
	@Column(name = "MEMO", length = 3000)
	private String remark;
	
    /**商品选配记录*/
	@LazyCollection(value=LazyCollectionOption.EXTRA)
    @OneToMany
	@JoinColumn(name = "BARGAIN_RECORD_DTL_ID")
    private Set<BiddingGoodsOptionRecord> biddingGoodsOptionRecordSet = new HashSet<BiddingGoodsOptionRecord>();

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public BiddingRecord getBiddingRecord() {
		return biddingRecord;
	}

	public void setBiddingRecord(BiddingRecord biddingRecord) {
		this.biddingRecord = biddingRecord;
	}

	public RequireItem getRequireItem() {
		return requireItem;
	}

	public void setRequireItem(RequireItem requireItem) {
		this.requireItem = requireItem;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public BigDecimal getGoodsTotal() {
		return goodsTotal;
	}

	public void setGoodsTotal(BigDecimal goodsTotal) {
		this.goodsTotal = goodsTotal;
	}

	public String getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Set<BiddingGoodsOptionRecord> getBiddingGoodsOptionRecordSet() {
		return biddingGoodsOptionRecordSet;
	}

	public void setBiddingGoodsOptionRecordSet(
			Set<BiddingGoodsOptionRecord> biddingGoodsOptionRecordSet) {
		this.biddingGoodsOptionRecordSet = biddingGoodsOptionRecordSet;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date arg0) {
		
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	
}