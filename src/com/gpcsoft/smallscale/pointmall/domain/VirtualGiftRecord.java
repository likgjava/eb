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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>虚拟礼品兑换记录</strong>            		
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
 *  @gpcsoft.title value="虚拟礼品兑换记录" 
 */ 
@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "SM_VIRTUAL_GIFT_RECORD")
public class VirtualGiftRecord implements GpcBaseObject, IPropertyCUserTime {
	
	/** 主键ID */
	@Id
    @Column(name = "VIRTUAL_RECORD_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	

	/** 礼品 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="GIFT_ID")  
	@BatchSize(size = 15)
	private Gift gift;
	
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
	
	/** 接受邮件 */
    @Column(name = "RECEIVE_EMAIL",length=50)
	private String receiveEmail;
	
	/** 卡号 */
    @Column(name = "CARD_CODE",length=50)
	private String cardCode;
    
    /** 密码 */
    @Column(name = "PASSWORD",length=50)
	private String password;

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

	public String getReceiveEmail() {
		return receiveEmail;
	}

	public void setReceiveEmail(String receiveEmail) {
		this.receiveEmail = receiveEmail;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

}
