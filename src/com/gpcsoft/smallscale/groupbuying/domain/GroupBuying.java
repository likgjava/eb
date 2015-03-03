package com.gpcsoft.smallscale.groupbuying.domain;

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
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>团购</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   smallscale                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2011-6-21 上午08:49:53 by likg    					                            
  *  <br/>Modified Date:  2011-6-21 上午08:49:53 by likg                                   
  *  <p>@since 0.5
  *
  * @gpcsoft.package packageDir="com.gpcsoft.smallscale.groupbuying"
  * @gpcsoft.page domain="GroupBuying"
  * @hibernate.class table="EPS_GROUP_BUYING"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate=true, dynamicInsert=true)
@Table(name="EPS_GROUP_BUYING")
public class GroupBuying implements GpcBaseObject ,IPropertyCUserTime {

	/** serialVersionUID */
	private static final long serialVersionUID = 3521054749787980523L;

	/**记录号*/
    @Id
    @Column(name = "GROUP_BUYING_ID", length=50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
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
    @BatchSize(size=15)
    private GoodsClass goodsClass;
    
    /**名称*/
    @Column(name="NAME", length=100)
    private String name;
    
    /**图片*/
    @Column(name="PICTURE", length=50)
    private String picture;
    
	/**市场价*/
    @Column(name = "MARKET_PRICE")
    private BigDecimal marketPrice;
    
	/**团购价*/
    @Column(name = "GROUP_PRICE")
    private BigDecimal groupPrice;
    
    /**折扣*/
    @Column(name = "DISCOUNT")
    private BigDecimal discount;
    
    /**开始时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIME")
    private Date startTime;
    
    /**结束时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_TIME")
    private Date endTime;
    
    /**最低团购数*/
    @Column(name = "MIN_NUMBER")
    private BigDecimal minNumber;
    
    /**最大团购数*/
    @Column(name = "MAX_NUMBER")
    private BigDecimal maxNumber;
    
	/**当前团购数*/
    @Column(name = "CURRENT_NUMBER")
    private BigDecimal currentNumber;
    
    /**达到最低团购数时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MEET_NUMBER_TIME")
    private Date meetNumberTime;
    
    /**当前状态*/
    @Column(name="CURRENT_STATUS", length=2)
    private String currentStatus;
    
    /**是否团购成功*/
    @Column(name="IS_SUCCESS", length=1)
    private Boolean isSuccess;
    
    /**描述*/
    @Column(name="GROUP_DESC", length=1000)
    private String desc;
    
    /**有效状态：[临时:00,有效:01,无效:02]*/
    @Column(name = "USE_STATUS", length=2)
    private String useStatus;
    
    /**创建人*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="CREATOR_ID")  
    @BatchSize(size = 15)
    private User createUser;
    
    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

	/** @gpcsoft.property title="记录号" */
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/** @gpcsoft.property title="商品" */
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	/** @gpcsoft.property title="商品分类" */
	public GoodsClass getGoodsClass() {
		return goodsClass;
	}

	public void setGoodsClass(GoodsClass goodsClass) {
		this.goodsClass = goodsClass;
	}

	/** @gpcsoft.property title="名称" */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** @gpcsoft.property title="图片" */
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	/** @gpcsoft.property title="市场价" */
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	/** @gpcsoft.property title="团购价" */
	public BigDecimal getGroupPrice() {
		return groupPrice;
	}

	public void setGroupPrice(BigDecimal groupPrice) {
		this.groupPrice = groupPrice;
	}

	/** @gpcsoft.property title="折扣" */
	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	/** @gpcsoft.property title="开始时间" */
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/** @gpcsoft.property title="结束时间" */
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/** @gpcsoft.property title="最低团购数" */
	public BigDecimal getMinNumber() {
		return minNumber;
	}

	public void setMinNumber(BigDecimal minNumber) {
		this.minNumber = minNumber;
	}
	
	/** @gpcsoft.property title="最大团购数" */
	public BigDecimal getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(BigDecimal maxNumber) {
		this.maxNumber = maxNumber;
	}

	/** @gpcsoft.property title="当前团购数" */
	public BigDecimal getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(BigDecimal currentNumber) {
		this.currentNumber = currentNumber;
	}

	/** @gpcsoft.property title="达到最低团购数时间" */
	public Date getMeetNumberTime() {
		return meetNumberTime;
	}

	public void setMeetNumberTime(Date meetNumberTime) {
		this.meetNumberTime = meetNumberTime;
	}

	/** @gpcsoft.property title="当前状态" */
	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	/** @gpcsoft.property title="是否团购成功" */
	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/** @gpcsoft.property title="描述" */
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/** @gpcsoft.property title="使用状态" */
	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	/** @gpcsoft.property title="创建人" */
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	/** @gpcsoft.property title="创建时间" */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}
