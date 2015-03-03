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
import com.gpcsoft.srplatform.baseData.domain.Dictionary;

/** 
  *  Comments: <strong>专家教育经历</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-11-25 下午03:20:28 by likg    					                            
  *  <br/>Modified Date:  2010-11-25 下午03:20:28 by likg                                   
  *<p>@since 0.5
  *   
  * @gpcsoft.package packageDir="com.gpcsoft.smallscale"
  * @gpcsoft.page domain="expert"
  * @hibernate.class table="EXPERT_EDUCATION"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EXPERT_EDUCATION")
public class Education implements GpcBaseObject
{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** ID */
	@Id
	@Column(name = "EXPERT_EDUCATION_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 专家 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="EXPERT_ID")
	@BatchSize(size = 15)
	private ExpertInfo expertInfo;
	
	/** 毕业院校  */
	@Column(name = "GRADUATE_SCHOOL", length = 100)
	private String graduateSchool;
	
	/** 入学时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENROLL_DATE", length = 7)
	private Date enrollDate;
	
	/** 毕业时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GRADUATE_DATE", length = 7)
	private Date graduateDate;
	
	/** 所学专业 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="SPECIALITY")
	@BatchSize(size = 15)
	private Dictionary speciality;
	
	/** 学历 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="DEGREE")
	@BatchSize(size = 15)
	private Dictionary degree;
	
	/** 证明文件 */
	@Column(name = "FILE_ID", length = 50)
	private String file;
	
	/** 审核状态 */
	@Column(name = "AUDIT_STATUS", length = 2)
	private String auditStatus;
	

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}


	public Date getEnrollDate() {
		return enrollDate;
	}

	public ExpertInfo getExpertInfo() {
		return expertInfo;
	}

	public String getFile() {
		return file;
	}

	public Date getGraduateDate() {
		return graduateDate;
	}

	public String getGraduateSchool() {
		return graduateSchool;
	}

	public String getObjId() {
		return this.objId;
	}


	public void setCreateTime(Date paramDate) {
		// TODO Auto-generated method stub
	}


	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public void setExpertInfo(ExpertInfo expertInfo) {
		this.expertInfo = expertInfo;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setGraduateDate(Date graduateDate) {
		this.graduateDate = graduateDate;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
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


	public Dictionary getSpeciality() {
		return speciality;
	}


	public void setSpeciality(Dictionary speciality) {
		this.speciality = speciality;
	}


	public Dictionary getDegree() {
		return degree;
	}


	public void setDegree(Dictionary degree) {
		this.degree = degree;
	}

	
}
