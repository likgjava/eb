package com.gpcsoft.pubservice.application.message.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.pubservice.application.message.enumeration.EnumMessage;

/**  
 *  Comments: <strong>留言</strong>
 *  
 *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.message"
 *  @gpcsoft.page domain="message" project="pubservice/application"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="留言" 
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_PUB_NOTE")
public class Note implements GpcBaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**主键ID */
	@Id
	@Column(name="NOTE_ID", length=50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name="paymentableGenerator",strategy="uuid.hex")
	private String objId;
	
	/**内容 */
	@Column(name="CONTENT")
	private String content;
	
	/**附件 (表情图标)*/
	@Column(name="ATT_FILE")
	private String attFile;
	
	/**接受人 */
	@Column(name="RECEIVER")
	private String receiver;
	
	/**接受人姓名 */
	@Column(name="RECEIVER_NAME")
	private String receiverName;
	
	/**相关商品 */
	@Column(name="GOODS_ID")
	private String goodsId;
	
	/**类型  (00：普通留言   01：客服咨询) */
	@Column(name="TYPE")
	private String type;
	
	/**留言人 */
	@Column(name="LEAVER")
	private String leaver;
	
	/**留言人姓名 */
	@Column(name="LEAVER_NAME")
	private String leaverName;
	
	/**回复内容 */
	@Column(name="REPLY_CONTENT")
	private String replyContent;
	
	/**回复时间 */
	@Temporal(TemporalType.DATE)
	@Column(name="REPLY_TIME")
	private Date replyTime;
	
	/**是否回复(0:没有回复   1：已经回复) */
	@Column(name="IS_REPLY")
	private Boolean isReply;
	
	/**是否匿名(0:不匿名   1：匿名) */
	@Column(name="IS_ANNOY")
	private Boolean isAnnoy;
	
	/**是否阅读(0:未阅读   1：已阅读) */
	@Column(name="IS_READ")
	private Boolean isRead;
	
	/**留言时间 */
	@Temporal(TemporalType.DATE)
	@Column(name="LEAVER_TIME",length=7)
	private Date createTime;
	
	/*回复状态别名*/
	@Transient
	private String isReplyCN;
	
	/*阅读状态别名*/
	@Transient
	private String isReadCN;
	
	public String getIsReplyCN() {
		this.isReplyCN = EnumMessage.getReplyCN(this.isReply?"1":"0");
		return isReplyCN;
	}

	public void setIsReplyCN(String isReplyCN) {
		this.isReplyCN = isReplyCN;
	}

	public String getIsReadCN() {
		this.isReadCN = EnumMessage.getReadCN(this.isRead?"1":"0");
		return isReadCN;
	}

	public void setIsReadCN(String isReadCN) {
		this.isReadCN = isReadCN;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLeaver() {
		return leaver;
	}

	public void setLeaver(String leaver) {
		this.leaver = leaver;
	}

	public String getLeaverName() {
		return leaverName;
	}

	public void setLeaverName(String leaverName) {
		this.leaverName = leaverName;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public Boolean getIsReply() {
		return isReply;
	}

	public void setIsReply(Boolean isReply) {
		this.isReply = isReply;
	}

	public Boolean getIsAnnoy() {
		return isAnnoy;
	}

	public void setIsAnnoy(Boolean isAnnoy) {
		this.isAnnoy = isAnnoy;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public String getAttFile() {
		return attFile;
	}

	public void setAttFile(String attFile) {
		this.attFile = attFile;
	}

}
