package com.gpcsoft.smallscale.expert.domain;

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

/** 
  *  Comments: <strong>职称管理</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-11-25 下午02:55:59 by likg    					                            
  *  <br/>Modified Date:  2010-11-25 下午02:55:59 by likg                                   
  *  <p>@since 0.5
  *   
  * @gpcsoft.package packageDir="com.gpcsoft.smallscale"
  * @gpcsoft.page domain="expert"
  * @hibernate.class table="EXPERT_CERTIFICATE"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EXPERT_CERTIFICATE")
public class Certificate implements GpcBaseObject
{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** ID */
	@Id
	@Column(name = "CERTIFICATE_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 专家 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="EXPERT_ID")
	@BatchSize(size = 15)
	private ExpertInfo expertInfo;
	
	/** 职称名称 */
	@Column(name = "TITLE_NAME", length = 50)
	private String titleName;
	
	/** 证书编号 */
	@Column(name = "CERTIFICATE_NO", length = 50)
	private String certificateNo;
	
	/** 颁发机构 */
	@Column(name = "ISSUE_UNIT", length = 100)
	private String issueUnit;
	
	/** 获得证书时间  */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACQUIRE_TIME", length = 7)
	private Date acquireTime;
	
	/** 证书有效时间  */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VAL_DATE", length = 7)
	private Date valDate;
	
	/** 证书附件 */
	@Column(name = "FILE_ID", length = 50)
	private String file;
	
	/** 审核状态 */
	@Column(name = "AUDIT_STATUS", length = 2)
	private String auditStatus;
	
	public Date getAcquireTime() {
		return acquireTime;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public ExpertInfo getExpertInfo() {
		return expertInfo;
	}

	public String getFile() {
		return file;
	}

	public String getIssueUnit() {
		return issueUnit;
	}

	public String getObjId() {
		return this.objId;
	}


	public String getTitleName() {
		return titleName;
	}

	public Date getValDate() {
		return valDate;
	}

	public void setAcquireTime(Date acquireTime) {
		this.acquireTime = acquireTime;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public void setCreateTime(Date paramDate) {
		// TODO Auto-generated method stub
		
	}

	public void setExpertInfo(ExpertInfo expertInfo) {
		this.expertInfo = expertInfo;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setIssueUnit(String issueUnit) {
		this.issueUnit = issueUnit;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}


	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public void setValDate(Date valDate) {
		this.valDate = valDate;
	}

}
