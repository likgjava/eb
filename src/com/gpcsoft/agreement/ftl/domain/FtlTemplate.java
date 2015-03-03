package com.gpcsoft.agreement.ftl.domain;

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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.agreement.ftl.enumeration.FtlTemplateEnum;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>FtlTemplate</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-12-1 下午07:25:44 by guoyr    					                            
  *  <br/>Modified Date:  2010-12-1 下午07:25:44 by guoyr   
  *  @gpcsoft.package packageDir="com.gpcsoft.agreement.ftl"
  *  @gpcsoft.page domain="ftl"  project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="模板"                              
  *  @since 0.5
  *  @version: 0.5 
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "FTL_TEMPLATE")
public class FtlTemplate implements GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "FTL_TEMPLATE_ID", unique = true, nullable = false, length = 50)
	private String objId;
	
	/**模板路径*/
	@Column(name = "FTL_PATH", length = 100)
	private String ftlPath;
	
	/**模板名称*/
	@Column(name = "FTL_NAME", length = 100)
	private String ftlName;
	
	/**模板附件名称*/
	@Column(name = "FILE_NAME", length = 100)
	private String fileName;
	
	/**模板类型*/
	@Column(name = "FTL_TYPE", length = 100)
	private String ftlType;
	
	/**模板类型*/
	@Transient
	private String ftlTypeCN;
	
	/**模板内容*/
	@Transient
	private String content;
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATOR_ID")
	@BatchSize(size = 15)
	private User createUser;

	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 7)
	private Date createTime;

	/** 修改人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="MODIFER_ID")
	@BatchSize(size = 15)
	private User updateUser;

	/** 修改时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_TIME", length = 7)
	private Date updateTime;

	public void setObjId(String objId){
		this.objId = objId;
	}
	
	public String getObjId() {
		return this.objId;
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

	public String getFtlPath() {
		return ftlPath;
	}

	public void setFtlPath(String ftlPath) {
		this.ftlPath = ftlPath;
	}

	public String getFtlName() {
		return ftlName;
	}

	public void setFtlName(String ftlName) {
		this.ftlName = ftlName;
	}

	public String getFtlType() {
		return ftlType;
	}

	public void setFtlType(String ftlType) {
		this.ftlType = ftlType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFtlTypeCN() {
		ftlTypeCN  = FtlTemplateEnum.getFtlTypeCN(ftlType);
		return ftlTypeCN;
	}

	public void setFtlTypeCN(String ftlTypeCN) {
		this.ftlTypeCN = ftlTypeCN;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


}