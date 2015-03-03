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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.agreement.bargin.project.domain.BargainTurn;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>议价记录</strong>            		
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
  *  @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.ebargain"
  *  @gpcsoft.page domain="ebargain" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="报价历史记录"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_RECORD_HISTORY")
public class BiddingRecordHistory implements GpcBaseObject{

	/** serialVersionUID */
	private static final long serialVersionUID = 3318375439626922493L;

	/**记录号*/
    @Id
    @Column(name = "RECORD_HISTORY_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
	/**报价记录*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="RECORD_DETAIL_ID") 
    @BatchSize(size = 15)
    private BiddingRecordDetail biddingRecordDetail;
    
	/**报价轮次*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="BARGAIN_TURN_ID") 
    @BatchSize(size = 15)
    private BargainTurn bargainTurn;
    
	/**议价时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BARGIN_TIME")
	private Date barginTime;
	
	/**金额*/
	@Column(name = "GOODS_TOTAL", precision = 16, scale = 6)
	private BigDecimal goodsTotal;
	
	/** 单价 */
	@Column(name = "GOODS_PRICE", precision = 16, scale = 6)
	private BigDecimal goodsPrice;
	
	/** 报价人 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CRE_USER_ID")
	private User createUser;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	public BiddingRecordDetail getBiddingRecordDetail() {
		return biddingRecordDetail;
	}

	public void setBiddingRecordDetail(BiddingRecordDetail biddingRecordDetail) {
		this.biddingRecordDetail = biddingRecordDetail;
	}

	public BargainTurn getBargainTurn() {
		return bargainTurn;
	}

	public void setBargainTurn(BargainTurn bargainTurn) {
		this.bargainTurn = bargainTurn;
	}

	public Date getBarginTime() {
		return barginTime;
	}

	public void setBarginTime(Date barginTime) {
		this.barginTime = barginTime;
	}

	public BigDecimal getGoodsTotal() {
		return goodsTotal;
	}

	public void setGoodsTotal(BigDecimal goodsTotal) {
		this.goodsTotal = goodsTotal;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return barginTime;
	}

	public void setCreateTime(Date barginTime) {
		this.barginTime = barginTime;
	}
}