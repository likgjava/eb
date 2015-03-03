package com.gpcsoft.smallscale.pointmall.domain;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Where;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.smallscale.point.enumeration.SmallscaleEnum;
import com.gpcsoft.srplatform.auth.domain.User;
/** 
  *  Comments: <strong>Gift</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2011-1-7 上午09:38:49 by yucy    					                            
  *  <br/>Modified Date:  2011-1-7 上午09:38:49 by yucy                                   
  *	<p>@since 0.5
  * @version: 0.5 
  * @gpcsoft.package packageDir="com.gpcsoft.smallscale"
  * @gpcsoft.page domain="pointmall"
  * @hibernate.class table="Gift"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "SM_GIFT")
public class Gift implements GpcBaseObject ,IPropertyCUserTime{
    
    /** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**记录号*/
    @Id
    @Column(name = "GIFT_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**礼物编号*/
    @Column(name = "GIFT_CODE")
    private String giftCode;
    
    /**所属系列*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="SERIES_ID") 
    @BatchSize(size = 15)
    private GiftSeries giftSeries;
    
    /**商品*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="GOODS_ID") 
    @BatchSize(size = 15)
    private Goods goods;
    
    /**礼物名称*/
    @Column(name = "GIFT_NAME")
    private String giftName;
    
    /**礼品图片*/
	@Column(name="PICTURE_ID")	 
    private String  picture;
    
    /**礼品供货商*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="GIFT_SUPPLIER_ID") 
    @BatchSize(size = 15)
    private GiftSupplier giftSupplier;

    /**礼品类型 实物00 虚拟01*/
    @Column(name = "GIFT_TYPE")
    private String giftType;
    
    @Transient
    private String giftTypeCN;
    
	/**兑换总数*/
	@Column(name = "EXCHANGE_COUNT")
	private Integer exchangeCount;
    
    /**开始时间*/
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIME")
    private Date startTime;
	
    /**结束时间*/
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_TIME")
    private Date endTime;
    
    /**礼品描述*/
    @Column(name = "GIFT_COMMENT")
    private String giftComment;
    
    /** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR_ID") 
    @BatchSize(size = 15)
    private User createUser;

    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    /**是否启用*/
    @Column(name = "IS_USED")
    private Boolean isUsed;
    
    @Transient
    private String isUsedCN;
    
    /**是否推荐*/
    @Column(name = "IS_RECOMMENDED")
    private Boolean isRecommended;
    
    @Transient
    private String isRecommendedCN;
    
    /**规则集合*/
	@LazyCollection(value=LazyCollectionOption.EXTRA)
    @OneToMany
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "GIFT_ID") 
    @OrderBy(clause = "CREATE_TIME asc")
	@Where(clause="IS_USED='1'")
    private Set<GiftExchangeRule> giftExchangeRuleSet = new HashSet<GiftExchangeRule>();
	
	/**虚拟礼品兑换人数*/
	@Formula("( select count(vr.virtual_record_id) from SM_VIRTUAL_GIFT_RECORD vr where vr.gift_id = GIFT_ID and vr.deal_status = '03'  )")
	private Integer virtualRecordQty;
	
	/**实体礼品兑换人数*/
	@Formula("( select count(sr.real_record_id) from SM_REAL_GIFT_RECORD sr where sr.gift_id = GIFT_ID and sr.deal_status = '03' )")
	private Integer realRecordQty;
	
	/**评价人数*/
	@Formula("( select count(c.comment_id) from SM_GIFT_COMMENT c where c.gift_id = GIFT_ID ) ")
	private Integer totalEvalQty;
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getGiftCode() {
		return giftCode;
	}

	public void setGiftCode(String giftCode) {
		this.giftCode = giftCode;
	}
	
	public GiftSeries getGiftSeries() {
		return giftSeries;
	}

	public void setGiftSeries(GiftSeries giftSeries) {
		this.giftSeries = giftSeries;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public GiftSupplier getGiftSupplier() {
		return giftSupplier;
	}

	public void setGiftSupplier(GiftSupplier giftSupplier) {
		this.giftSupplier = giftSupplier;
	}

	public String getGiftType() {
		return giftType;
	}

	public void setGiftType(String giftType) {
		this.giftType = giftType;
	}

	public Integer getExchangeCount() {
		return exchangeCount;
	}

	public void setExchangeCount(Integer exchangeCount) {
		this.exchangeCount = exchangeCount;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getGiftComment() {
		return giftComment;
	}

	public void setGiftComment(String giftComment) {
		this.giftComment = giftComment;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}
	
	public String getIsUsedCN() {
		this.isUsedCN = SmallscaleEnum.getGiftBooleanCN(this.isUsed?"1":"0");
		return this.isUsedCN;
	}
	
	public void setIsUsedCN(String isUsedCN) {
		this.isUsedCN = isUsedCN;
	}
	
	public String getGiftTypeCN() {
		this.giftTypeCN = SmallscaleEnum.getGiftTypeCN(this.giftType);
		return this.giftTypeCN;
	}

	public void setGiftTypeCN(String giftTypeCN) {
		this.giftTypeCN = giftTypeCN;
	}

	public Boolean getIsRecommended() {
		return isRecommended;
	}

	public void setIsRecommended(Boolean isRecommended) {
		this.isRecommended = isRecommended;
	}

	public String getIsRecommendedCN() {
		this.isRecommendedCN = SmallscaleEnum.getGiftBooleanCN(this.isRecommended?"1":"0");
		return this.isRecommendedCN;
	}

	public void setIsRecommendedCN(String isRecommendedCN) {
		this.isRecommendedCN = isRecommendedCN;
	}

	public Set<GiftExchangeRule> getGiftExchangeRuleSet() {
		return giftExchangeRuleSet;
	}

	public void setGiftExchangeRuleSet(Set<GiftExchangeRule> giftExchangeRuleSet) {
		this.giftExchangeRuleSet = giftExchangeRuleSet;
	}

	public Integer getTotalEvalQty() {
		return totalEvalQty;
	}

	public void setTotalEvalQty(Integer totalEvalQty) {
		this.totalEvalQty = totalEvalQty;
	}

	public Integer getVirtualRecordQty() {
		return virtualRecordQty;
	}

	public void setVirtualRecordQty(Integer virtualRecordQty) {
		this.virtualRecordQty = virtualRecordQty;
	}

	public Integer getRealRecordQty() {
		return realRecordQty;
	}

	public void setRealRecordQty(Integer realRecordQty) {
		this.realRecordQty = realRecordQty;
	}
}