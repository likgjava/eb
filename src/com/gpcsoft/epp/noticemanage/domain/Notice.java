package com.gpcsoft.epp.noticemanage.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 *  Comments: <strong>Consignprotocol</strong>通知书管理表<br/>           		
 *	<br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 权限平台     		
 *  <br/>Create Date：2010-4-14 上午02:59:07 by yemx    					                            
 *  <br/>Modified Date:  2010-4-14 上午02:59:07 by yemx                                 
 *  @gpcsoft.package packageDir="com.gpcsoft.epp.noticemanage"
 *  @gpcsoft.page domain="noticemanage" project="es/planform"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="通知书"    
 *  @since 0.4
 *  @version: 0.5
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_Base_Notice")
@SuppressWarnings("serial")
public class Notice  extends WorkFlowModel  implements GpcBaseObject,IPropertyCUserTime{

	@Id
	@Column(name = "NOTICE_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;  //通知书ID
	
	@Column(name="Notice_Type",length=50)
	private String noticeType; //通知书种类[成交通知书，结果通知书]
	
	@Transient
	private String noticeTypeCn;
	
	/** 关联项目*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="TenderID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private Project project;
	
	//创建时间
    @Column(name = "Cre_Date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
	
	/** 发送单位 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="Send_Org_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private OrgInfo sendOrg;
	
	/** 发送人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="Send_User_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User sendUser;
	
	@Column(name = "Send_Status", length = 2)
	private String sendStatus;//发送状态

	//发送时间
    @Column(name = "Send_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;
    
    /** 接收单位 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="Recvice_Org_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private OrgInfo receiveOrg;
    
	/** 接收人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="Recvice_User_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User receiveUser;
	
	
	@Column(name = "Recvice_Status", length = 2)
	private String receiveStatus;//接收状态 00:未接收,01已接收
	
	//发送时间
    @Column(name = "Recvice_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiveDate;

    @Column(name = "Title", length = 50)
	private String noticeTitle; //通知书标题
	
    /** 内容 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="Content", updatable = true)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Attachment noticeContent; //通知书内容[关联附件ID]
	
	@Transient
	private String contents;//内容[显示]
	
	@Transient
	private String tempStatus;//临时状态：判断是否能继续起草
	
	@Column(name = "Ret_Content", length = 1000)
	private String retContent; //回执内容
	
	@Column(name = "USE_STATUS", length = 2)
	private String useStatus;//使用状态

	//modify by liuke
	/**当前有效id*/
	@Column(name = "CURRENT_VALID_ID")
	private String currentId;
	
	public String getCurrentId() {
		return currentId;
	}

	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public String getObjId() {
		return this.objId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getCreateUser() {
		return this.sendUser;
	}

	public void setCreateUser(User sendUser) {
		this.sendUser = sendUser;
	}

	/**
	 * @gpcsoft.property title="类型"
	 */
	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public OrgInfo getSendOrg() {
		return sendOrg;
	}

	public void setSendOrg(OrgInfo sendOrg) {
		this.sendOrg = sendOrg;
	}

	public User getSendUser() {
		return sendUser;
	}

	public void setSendUser(User sendUser) {
		this.sendUser = sendUser;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	/**
	 * @gpcsoft.property title="接收单位"
	 */
	public OrgInfo getReceiveOrg() {
		return receiveOrg;
	}

	public void setReceiveOrg(OrgInfo receiveOrg) {
		this.receiveOrg = receiveOrg;
	}

	public User getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(User receiveUser) {
		this.receiveUser = receiveUser;
	}

	public String getReceiveStatus() {
		return receiveStatus;
	}

	public void setReceiveStatus(String receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	/**
	 * @gpcsoft.property title="标题"
	 */
	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public Attachment getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(Attachment noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRetContent() {
		return retContent;
	}

	public void setRetContent(String retContent) {
		this.retContent = retContent;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getNoticeTypeCn() {
		return NoticeTypeEnum.getResult(noticeType);
	}

	public void setNoticeTypeCn(String noticeTypeCn) {
		this.noticeTypeCn = noticeTypeCn;
	}

	public String getTempStatus() {
		return tempStatus;
	}

	public void setTempStatus(String tempStatus) {
		this.tempStatus = tempStatus;
	}
	
}
