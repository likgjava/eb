package com.gpcsoft.bizplatform.organization.domain;

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
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.srplatform.auth.domain.User;


/** 
  *  Comments: <strong>违法记录</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   bizplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2011-7-12 下午03:12:40 by yucy    					                            
  *  <br/>Modified Date:  2011-7-12 下午03:12:40 by yucy                                   
  *	  @since 0.5
  *   @version: 0.5 
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PUB_ILLEGAL_RECORD")
public class IllegalRec implements GpcBaseObject ,IPropertyCUserTime{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**主键*/
	@Id
	@Column(name = "ILLEGAL_RECORD_ID",length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
    
	/**违法机构id*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="ORGINFO_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private OrgInfo org;
	
	/**违规标题*/
	@Column(name = "TITLE")
	private String title;

	/**违规详情*/
	@Column(name = "REASON")
	private String reason;
	
	/**是否显示*/
	@Column(name = "IS_SHOW")
	private Boolean isShow;
	
	/**创建时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;
	
    /** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR_ID") 
    @BatchSize(size = 15)
    private User createUser;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public OrgInfo getOrg() {
		return org;
	}

	public void setOrg(OrgInfo org) {
		this.org = org;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
}