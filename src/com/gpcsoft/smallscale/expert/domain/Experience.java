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
  *  Comments: <strong>任职经历</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-11-25 下午03:27:23 by likg    					                            
  *  <br/>Modified Date:  2010-11-25 下午03:27:23 by likg                                   
  *  <p>@since 0.5
  *   
  * @gpcsoft.package packageDir="com.gpcsoft.smallscale"
  * @gpcsoft.page domain="expert"
  * @hibernate.class table="EXPERT_EXPERIENCE"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EXPERT_EXPERIENCE")
public class Experience implements GpcBaseObject
{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** ID */
	@Id
	@Column(name = "EXPERIENCE_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 专家 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="EXPERT_ID")
	@BatchSize(size = 15)
	private ExpertInfo expertInfo;
	
	/** 工作单位 */
	@Column(name = "ORG_NAME", length = 100)
	private String orgName;
	
	/** 职业 */
	@Column(name = "SPECIALTY", length = 100)
	private String specialty;
	
	/** 职务 */
	@Column(name = "DUTY", length = 100)
	private String duty;
	
	/** 开始时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_TIME", length = 7)
	private Date startTime;
	
	/** 结束时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_TIME", length = 7)
	private Date endTime;
	
	/** 成就描述  */
	@Column(name = "ACHIEVEMENT", length = 2000)
	private String achievement;
	
	/** 审核状态 */
	@Column(name = "AUDIT_STATUS", length = 2)
	private String auditStatus;
	

	public String getAchievement() {
		return achievement;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDuty() {
		return duty;
	}

	public Date getEndTime() {
		return endTime;
	}

	public ExpertInfo getExpertInfo() {
		return expertInfo;
	}

	public String getObjId() {
		return this.objId;
	}

	public String getOrgName() {
		return orgName;
	}

	public String getSpecialty() {
		return specialty;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public void setCreateTime(Date paramDate) {
		// TODO Auto-generated method stub
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setExpertInfo(ExpertInfo expertInfo) {
		this.expertInfo = expertInfo;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}


}
