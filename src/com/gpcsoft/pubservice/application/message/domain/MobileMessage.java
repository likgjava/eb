package com.gpcsoft.pubservice.application.message.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.OrderProperty;

/** 
 *  Comments: <strong>手机短信</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:                       					          
 *  <br/>Module ID: 公共服务平台     		
 *  <br/>Create Date：2010-9-26 上午11:50:28 by yucy    					                            
 *  <br/>Modified Date:  2010-9-26 上午11:50:28 by yucy                               
 *   
 *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.message"
 *  @gpcsoft.page domain="message" project="pubservice/application"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="手机短信" 
 */ 
@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@OrderProperty(property="createTime", flag="true")
@Table(name = "EPS_PUB_MOBILE_MESSAGE")
public class MobileMessage implements GpcBaseObject
{
	/** 主键ID */
	@Id
    @Column(name = "MB_MSG_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 电话 */
	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;
	
	/** 内容 */
	@Column(name = "CONTENT")
	private String content;
	
	/** 发送人 */
	@Column(name = "SENDER", length = 50)
	private String sender;
	
	/** 发送人姓名 */
	@Column(name = "SENDER_NAME", length = 50)
	private String senderName;
	
	/** 发送时间 */
	@Temporal(TemporalType.DATE)
    @Column(name = "SEND_TIME")
	private Date sendTime;
	
	/** 接收人 */
	@Column(name = "RECEIVER", length = 50)
	private String receiver;
	
	/** 接收人姓名 */
	@Column(name = "RECEIVER_NAME", length = 50)
	private String receiverName;
	
	/** 创建人 */
	@Column(name = "CREUSER", length = 50)
	private String creUserId;
	
	/** 创建人名称 */
	@Column(name = "CREUSERNAME", length = 50)
	private String creUserName;
	
	/** 创建时间 */
	@Temporal(TemporalType.DATE)
    @Column(name = "CREDATE")
	private Date createTime;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	public String getCreUserId() {
		return creUserId;
	}

	public void setCreUserId(String creUserId) {
		this.creUserId = creUserId;
	}

	public String getCreUserName() {
		return creUserName;
	}

	public void setCreUserName(String creUserName) {
		this.creUserName = creUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
