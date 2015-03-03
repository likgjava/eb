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
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>投诉举报</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2010-xxxx   		
 *  <br/>Project:                       					          
 *  <br/>Module ID: 公共服务平台     		
 *  <br/>Create Date：2010-9-26 			                            
 *  <br/>Modified Date:  2010-9-26                          
 *   
 *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.message"
 *  @gpcsoft.page domain="message" project="pubservice/application"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="投诉举报" 
 */ 
@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_PUB_COMPLAIN")
public class Complain implements GpcBaseObject{
	
	/** 主键ID */
	@Id
    @Column(name = "CPL_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 标题 */
	@Column(name = "TITLE", length = 200)
	private String title;
	
	/** 内容 */
	@Column(name = "CONTENT", length = 4000)
	private String content;
	
	/** 附件 */
	@Column(name = "ATT_FILE", length = 50)
	private String attFile;
	
	/** 回复信箱 */
	@Column(name = "REPLY_EMAIL", length = 100)	
	private String replyEmail;	

	
	/** 投诉人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="complainant")  
	@BatchSize(size = 15)//批量抓取
	private User complainant;
	
	/** 投诉人姓名 */
	@Column(name = "complainant_name", length = 50)
	private String complainantName;
	
	/** 投诉时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "complain_time")
	private Date complainTime;
	
	/** 被投诉人 */
	@Column(name = "be_complain", length = 50)
	private String beComplain;
	
	/** 被投诉人姓名 */
	@Column(name = "be_complain_name", length = 50)
	private String beComplainName;
	
	/** 被投诉人公司 */
	@Column(name = "be_company_id", length = 50)
	private String beCompanyId;
	
	/** 被投诉人公司名称 */
	@Column(name = "be_company_name", length = 200)
	private String beCompanyName;
	
	/** 类型	 00:投诉 01:举报*/
	@Column(name = "type", length = 2)
	private String type;
	
	/** 是否处理  0:未处理 1:已处理 */
	@Column(name = "IS_DISPOSE")
	private Boolean isDispose;
	
	/** 处理结果  */
	@Column(name = "result", length = 4000)
	private String result;
	
	/** 处理时间	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DISPOSE_TIME")
	private Date disposeTime;
	
	/** 处理人 */	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="disposer")  
	@BatchSize(size = 15)//批量抓取
	private User disposer;
	
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_Time")
	private Date createTime;     
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAttFile() {
		return attFile;
	}

	public void setAttFile(String attFile) {
		this.attFile = attFile;
	}

	public String getReplyEmail() {
		return replyEmail;
	}

	public void setReplyEmail(String replyEmail) {
		this.replyEmail = replyEmail;
	}

	public User getComplainant() {
		return complainant;
	}

	public void setComplainant(User complainant) {
		this.complainant = complainant;
	}

	public String getComplainantName() {
		return complainantName;
	}

	public void setComplainantName(String complainantName) {
		this.complainantName = complainantName;
	}

	public Date getComplainTime() {
		return complainTime;
	}

	public void setComplainTime(Date complainTime) {
		this.complainTime = complainTime;
	}

	public String getBeComplain() {
		return beComplain;
	}

	public void setBeComplain(String beComplain) {
		this.beComplain = beComplain;
	}

	public String getBeComplainName() {
		return beComplainName;
	}

	public void setBeComplainName(String beComplainName) {
		this.beComplainName = beComplainName;
	}	

	public String getBeCompanyId() {
		return beCompanyId;
	}

	public void setBeCompanyId(String beCompanyId) {
		this.beCompanyId = beCompanyId;
	}

	public String getBeCompanyName() {
		return beCompanyName;
	}

	public void setBeCompanyName(String beCompanyName) {
		this.beCompanyName = beCompanyName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsDispose() {
		return isDispose;
	}

	public void setIsDispose(Boolean isDispose) {
		this.isDispose = isDispose;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getDisposeTime() {
		return disposeTime;
	}

	public void setDisposeTime(Date disposeTime) {
		this.disposeTime = disposeTime;
	}

	public User getDisposer() {
		return disposer;
	}

	public void setDisposer(User disposer) {
		this.disposer = disposer;
	}

	public Date getCreateTime() {		
		return createTime;
	}

	public String getObjId() {		
		return objId;
	}
	
	public void setObjId(String objId) {		
		this.objId = objId;
	}

	public void setCreateTime(Date arg0) {
		this.createTime = arg0;

	}

}
