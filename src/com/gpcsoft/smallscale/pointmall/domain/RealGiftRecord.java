package com.gpcsoft.smallscale.pointmall.domain;

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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.smallscale.point.enumeration.SmallscaleEnum;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>实物礼品兑换记录</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2010-xxxx   		
 *  <br/>Project:                       					          
 *  <br/>Module ID:    		
 *  <br/>Create Date：2011-1-7 			                            
 *  <br/>Modified Date:  2011-1-7                          
 *   
 *  @gpcsoft.package packageDir="com.gpcsoft.smallscale.pointmall"
 *  @gpcsoft.page domain="pointmall" 
 *  @gpcsoft.domain
 *  @gpcsoft.title value="礼品兑换记录" 
 */ 
@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "SM_REAL_GIFT_RECORD")
public class RealGiftRecord implements GpcBaseObject, IPropertyCUserTime {
	
	/** 主键ID */
	@Id
    @Column(name = "REAL_RECORD_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 礼品 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="GIFT_ID")  
	@BatchSize(size = 15)
	private Gift gift;
	
	/** 数量 */
    @Column(name = "count",length=8)
	private Integer giftCount;  
	
	/** 兑换规则 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="ERULE_ID")  
	@BatchSize(size = 15)
	private GiftExchangeRule erule; 
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="CREATOR_ID")  
	@BatchSize(size = 15)
	private User createUser;
	
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_Time")
	private Date createTime;
	
	/** 备注 */
    @Column(name = "GIFT_COMMENT",length=4000)
	private String giftComment;
    
    /** 收货人姓名 */
    @Column(name = "RECEIVER_NAME",length=50)
	private String receiverName;
    
    /** 收货人联系电话 */
    @Column(name = "RECEIVER_TEL",length=50)
	private String receiverTel;
    
    /** 收货地址 */
    @Column(name = "RECEIVER_ADDRESS",length=200)
	private String receiverAddress;
    
    /** 收货邮编 */
    @Column(name = "RECEIVER_POST",length=50)
	private String receiverPost;		
		
    
	/** 处理状态00 未处理 01已处理 */
    @Column(name = "deal_status",length=2)
	private String dealStatus;
	
	/** 处理人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="DEAL_USER")  
	@BatchSize(size = 15)
	private User  dealUser;
	
	/** 处理时间 */
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DEAL_TIME")
	private Date dealTime;
	
	/** 快递方式 */
    @Column(name = "FPOST_TYPE",length=50)
	private String fpostType;
    
    /** 快递方式 */
    @Transient
	private String fpostTypeCN;
	
	/** 快递号 */
    @Column(name = "FPOST_NUMBER",length=50)
	private String fpostNumber;
	
	/** 收货时间 */
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "RECEIVE_TIME")
	private Date receiveTime;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Gift getGift() {
		return gift;
	}

	public void setGift(Gift gift) {
		this.gift = gift;
	}

	

	public Integer getGiftCount() {
		return giftCount;
	}

	public void setGiftCount(Integer giftCount) {
		this.giftCount = giftCount;
	}

	public GiftExchangeRule getErule() {
		return erule;
	}

	public void setErule(GiftExchangeRule erule) {
		this.erule = erule;
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

	public String getGiftComment() {
		return giftComment;
	}

	public void setGiftComment(String giftComment) {
		this.giftComment = giftComment;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverTel() {
		return receiverTel;
	}

	public void setReceiverTel(String receiverTel) {
		this.receiverTel = receiverTel;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverPost() {
		return receiverPost;
	}

	public void setReceiverPost(String receiverPost) {
		this.receiverPost = receiverPost;
	}

	public String getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}

	public User getDealUser() {
		return dealUser;
	}

	public void setDealUser(User dealUser) {
		this.dealUser = dealUser;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getFpostType() {
		return fpostType;
	}

	public void setFpostType(String fpostType) {
		this.fpostType = fpostType;
	}

	public String getFpostNumber() {
		return fpostNumber;
	}

	public void setFpostNumber(String fpostNumber) {
		this.fpostNumber = fpostNumber;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getFpostTypeCN() {
		if(this.fpostType == null){
			fpostTypeCN = "";
		}
		else{
			fpostTypeCN = SmallscaleEnum.getGiftPostTypeCN(fpostType);
		}
		return fpostTypeCN;
	}

	public void setFpostTypeCN(String fpostTypeCN) {
		this.fpostTypeCN = fpostTypeCN;
	}



}
