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
 *  Comments: <strong>站内消息</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:                       					          
 *  <br/>Module ID: 公共服务平台     		
 *  <br/>Create Date：2010-9-26 上午11:50:28 by likg    					                            
 *  <br/>Modified Date:  2010-9-26 上午11:50:28 by likg                               
 *   
 *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.message"
 *  @gpcsoft.page domain="message" project="pubservice/application"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="站内消息" 
 */ 
@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@OrderProperty(property="createTime", flag="true")
@Table(name = "EPS_PUB_MESSAGE")
public class Message implements GpcBaseObject
{
	/** 主键ID */
	@Id
    @Column(name = "MSG_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 标题 */
	@Column(name = "TITLE")
	private String title;
	
	/** 内容 */
	@Column(name = "CONTENT")
	private String content;
	
	/** 消息类型 (00:系统消息， 01:用户消息) */
	@Column(name = "TYPE", length = 2)
	private String type;
	
	/** 发送人 */
	@Column(name = "SENDER", length = 50)
	private String sender;
	
	/** 发送人姓名 */
	@Column(name = "SENDER_NAME", length = 50)
	private String senderName;
	
	/** 发送时间 */
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SEND_TIME")
	private Date sendTime;
	
	/** 预定发送时间 */
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "RES_SEND_TIME")
	private Date resSendTime;
	
	/** 接收人 */
	@Column(name = "RECEIVER", length = 50)
	private String receiver;
	
	/** 接收人姓名 */
	@Column(name = "RECEIVER_NAME", length = 50)
	private String receiverName;
	
	/** 阅读时间 */
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "READER")
	private Date reader;
	
	/** 是否阅读 (0:未阅读， 1:已阅读)*/
	@Column(name = "IS_READ")
	private Boolean isRead;
	
	/** 是否保存发件箱 (0:不保存， 1:保存)*/
	@Column(name = "IS_SAVE")
	private Boolean isSave;
	
	/** 是否已经提示 (0:未提示， 1:已提示)*/
	@Column(name = "IS_TIP")
	private Boolean isTip;
	
	/** 创建人 */
	@Column(name = "CREUSER", length = 50)
	private String creUser;
	
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREDATE")
	private Date createTime;
	
	public Message() {
		super();
	}

	public Message(Message message) {
		this.title = message.getTitle();
		this.content = message.getContent();
		this.type = message.getType();
		this.sender = message.getSender();
		this.senderName = message.getSenderName();
		this.sendTime = message.getSendTime();
		this.resSendTime = message.getResSendTime();
		this.receiver = message.getReceiver();
		this.receiverName = message.getReceiverName();
		this.reader = message.getReader();
		this.isRead = message.getIsRead();
		this.isSave = message.getIsSave();
		this.isTip = message.getIsTip();
		this.creUser = message.getCreUser();
		this.createTime = message.getCreateTime();
	}

	public String getContent() {
		return content;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public String getCreUser() {
		return creUser;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public Boolean getIsSave() {
		return isSave;
	}

	public Boolean getIsTip() {
		return isTip;
	}

	public String getObjId() {
		return this.objId;
	}

	public Date getReader() {
		return reader;
	}

	public String getReceiver() {
		return receiver;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public Date getResSendTime() {
		return resSendTime;
	}

	public String getSender() {
		return sender;
	}

	public String getSenderName() {
		return senderName;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreUser(String creUser) {
		this.creUser = creUser;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public void setIsSave(Boolean isSave) {
		this.isSave = isSave;
	}

	public void setIsTip(Boolean isTip) {
		this.isTip = isTip;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setReader(Date reader) {
		this.reader = reader;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public void setResSendTime(Date resSendTime) {
		this.resSendTime = resSendTime;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(String type) {
		this.type = type;
	}

}
