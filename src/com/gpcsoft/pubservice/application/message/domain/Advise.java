package com.gpcsoft.pubservice.application.message.domain;

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
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>建议意见</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2010-xxxx   		
 *  <br/>Project:                       					          
 *  <br/>Module ID: 公共服务平台     		
 *  <br/>Create Date：2010-10-26 			                            
 *  <br/>Modified Date:  2010-10-26                          
 *   
 *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.message"
 *  @gpcsoft.page domain="message" project="pubservice/application"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="建议意见" 
 */ 
@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_PUB_ADVISE")
public class Advise implements GpcBaseObject,IPropertyCUserTime,IPropertyUUserTime {
	
	/** 主键ID */
	@Id
    @Column(name = "ADV_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 内容 */
	@Column(name = "content", length = 4000)
	private String content;
	
	/** 建议意见人 */	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="adviser")  
	@BatchSize(size = 15)//批量抓取
	private User createUser;
	
	/** 联系电话 */
	@Column(name = "CONTACT_TEL", length = 4000)
	private String contactTel;//
	
	/** 联系邮件 */
	@Column(name = "CONTACT_EMAIL", length = 4000)
	private String contactEmail;//
	
	/** 回复内容 */
	@Column(name = "REPLY_CONTENT", length = 4000)
	private String replyContent;//
	
	/** 回复人 */	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="REPPIER")  
	@BatchSize(size = 15)//批量抓取
	private User repplier;//
	
	/** 是否回复 */
	@Column(name = "IS_REPLY")
	private String replyStatus;
	
		
	/** 建议时间	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ADVISE_TIME")
	private Date createTime;
	
	/** 回复时间	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REPLY_TIME")
	private Date replyTime;
	
	/** 修改人 */	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="update_user")  
	@BatchSize(size = 15)//批量抓取
	private User updateUser;
	
	/** 修改时间	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	
	public Date getCreateTime() {		
		return this.createTime;
	}

	public String getObjId() {		
		return objId;
	}

	public void setCreateTime(Date arg0) {
		this.createTime = arg0;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public User getCreateUser() {
		
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
		
	}	

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public User getRepplier() {
		return repplier;
	}

	public void setRepplier(User repplier) {
		this.repplier = repplier;
	}

	

	public String getReplyStatus() {
		return replyStatus;
	}

	public void setReplyStatus(String replyStatus) {
		this.replyStatus = replyStatus;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	

}
