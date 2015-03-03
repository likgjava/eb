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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 *  Comments: <strong>Consignprotocol</strong>供应商确认记录表<br/>           		
 *	<br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 权限平台     		
 *  <br/>Create Date：2010-4-14 上午02:59:07 by yemx    					                            
 *  <br/>Modified Date:  2010-4-14 上午02:59:07 by yemx                                 
 *  @gpcsoft.package packageDir="com.gpcsoft.epp.noticemanage"
 *  @gpcsoft.page domain="noticemanage" project="es/planform"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="供应商确认记录"    
 *  @since 0.4
 *  @version: 0.5
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_NOTICE_AFFIRMRECORD")
@SuppressWarnings("serial")
public class NoticeAffirmRecord implements GpcBaseObject,IPropertyCUserTime, IPropertyUUserTime{

	@Id
	@Column(name ="NOTICE_A_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;  //供应商确认记录ID
	
	@OneToOne(optional=false)
    @JoinColumn(name="NOTICE_ID")
	private Notice notice; //通知书
	
	@Column(name = "NOTICE_A_AFFIRM_TIME")
	private Date noticeAAffirmTime; //供应商确认时间
	
	@Column(name = "AFFIRMRECORD", length = 50)
	private String affirmRecord; 
	
	@Column(name = "NOTICE_A_AFFIRM_NAME", length = 50)
	private String noticeAAffirmName; //确认人名称
	
	@Column(name = "NOTICE_A_REMARK", length = 500)
	private String noticeARemark; //备注
	
	@Column(name = "USE_STATUS")
	private String useStatus;//使用状态
	
	//创建人
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATE_USER", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User createUser;

    //创建时间
    @Column(name = "CREATE_TIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    //修改人
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="MODIFY_USER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User updateUser;
    
    //修改时间
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	/**
	 * @gpcsoft.property title="供应商确认时间"
	 */
	public Date getNoticeAAffirmTime() {
		return noticeAAffirmTime;
	}

	public void setNoticeAAffirmTime(Date noticeAAffirmTime) {
		this.noticeAAffirmTime = noticeAAffirmTime;
	}

	public String getAffirmRecord() {
		return affirmRecord;
	}

	public void setAffirmRecord(String affirmRecord) {
		this.affirmRecord = affirmRecord;
	}

	/**
	 * @gpcsoft.property title="确认人名称"
	 */
	public String getNoticeAAffirmName() {
		return noticeAAffirmName;
	}

	public void setNoticeAAffirmName(String noticeAAffirmName) {
		this.noticeAAffirmName = noticeAAffirmName;
	}

	/**
	 * @gpcsoft.property title="备注"
	 */
	public String getNoticeARemark() {
		return noticeARemark;
	}

	public void setNoticeARemark(String noticeARemark) {
		this.noticeARemark = noticeARemark;
	}
	
	/**
	 * @gpcsoft.property title="创建人"
	 */
	public User getCreateUser() {
		return createUser;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	/**
	 * @gpcsoft.property title="创建时间"
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @gpcsoft.property title="修改人"
	 */
	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}
	
	/**
	 * @gpcsoft.property title="修改时间"
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}


