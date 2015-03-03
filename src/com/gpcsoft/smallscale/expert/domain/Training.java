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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;

/** 
  *  Comments: <strong>培训信息</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-11-25 下午03:33:47 by likg    					                            
  *  <br/>Modified Date:  2010-11-25 下午03:33:47 by likg                                   
  *  <p>@since 0.5
  *   
  * @gpcsoft.package packageDir="com.gpcsoft.smallscale"
  * @gpcsoft.page domain="expert"
  * @hibernate.class table="EXPERT_TRAINING"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EXPERT_TRAINING")
public class Training implements GpcBaseObject
{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** ID */
	@Id
	@Column(name = "TRAINING_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 专家 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="EXPERT_ID")
	@BatchSize(size = 15)
	private ExpertInfo expertInfo;
	
	/** 培训课程 */
	@Column(name = "TRAINING_COURSE", length = 100)
	private String trainingCourse;
	
	/** 培训机构 */
	@Column(name = "TRAINING_ORG", length = 100)
	private String trainingOrg;
	
	/** 开始时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BEGIN_DATE", length = 7)
	private Date beginDate;
	
	/** 结束时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE", length = 7)
	private Date endDate;
	
	/** 课程介绍 */
	@Column(name = "COURSE_MEMO", length = 2000)
	private String courseMemo;
	
	/** 证书附件 */
	@Column(name = "FILE_ID", length = 50)
	private String file;

	/** 审核状态 */
	@Column(name = "AUDIT_STATUS", length = 2)
	private String auditStatus;

	@Transient
	private String statusCN;

	public Date getBeginDate() {
		return beginDate;
	}

	public String getCourseMemo() {
		return courseMemo;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getEndDate() {
		return endDate;
	}

	public ExpertInfo getExpertInfo() {
			return expertInfo;
		}

	public String getFile() {
		return file;
	}

	public String getObjId() {
		return this.objId;
	}

	public String getStatusCN() {
		return statusCN;
	}

	public String getTrainingCourse() {
		return trainingCourse;
	}

	public String getTrainingOrg() {
		return trainingOrg;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public void setCourseMemo(String courseMemo) {
		this.courseMemo = courseMemo;
	}

	public void setCreateTime(Date paramDate) {
		// TODO Auto-generated method stub
		
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setExpertInfo(ExpertInfo expertInfo) {
		this.expertInfo = expertInfo;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	

	public void setStatusCN(String statusCN) {
		this.statusCN = statusCN;
	}

	public void setTrainingCourse(String trainingCourse) {
		this.trainingCourse = trainingCourse;
	}

	public void setTrainingOrg(String trainingOrg) {
		this.trainingOrg = trainingOrg;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

}
