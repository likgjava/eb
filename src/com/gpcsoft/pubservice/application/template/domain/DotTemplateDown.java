package com.gpcsoft.pubservice.application.template.domain;

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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.srplatform.auth.domain.User;


/** 
  *  Comments: <strong>范本下载记录</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   pubservice                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-20 下午03:12:40 by yucy    					                            
  *  <br/>Modified Date:  2010-7-20 下午03:12:40 by yucy                                   
  *	  @since 0.5
  *   @version: 0.5 
  *   
  *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.template"
  *  @gpcsoft.page domain="DotTemplateDown"
  *  @hibernate.class table="EPS_TEMPLATE_DOWN"
  *  @author Administrator
  *  @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_TEMPLATE_DOWN")
public class DotTemplateDown implements GpcBaseObject ,IPropertyCUserTime {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**主键*/
	@Id
	@Column(name = "TEMPLATE_DOWN_ID",length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;

	/**范本*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="TEMPLATE_ID")
	@BatchSize(size = 15)
	private DotTemplate dotTemplate;
	
	/**所属机构*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="ORG_ID")
	@BatchSize(size = 15)
	private OrgInfo org;
	
	/**创建人*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATE_USER")	 
	@BatchSize(size = 15)
	private User createUser;
	
	/**创建时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public DotTemplate getDotTemplate() {
		return dotTemplate;
	}

	public void setDotTemplate(DotTemplate dotTemplate) {
		this.dotTemplate = dotTemplate;
	}

	public OrgInfo getOrg() {
		return org;
	}

	public void setOrg(OrgInfo org) {
		this.org = org;
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
}