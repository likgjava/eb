package com.gpcsoft.epp.worktask.domain;

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

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 * @Description: 完成任务
 * @version V1.0
 * @Create Date 2010-8-2 上午09:25:36 By wanghz   
 * @gpcsoft.package packageDir="com.gpcsoft.epp.worktask"
 * @gpcsoft.page domain="worktask" project="es/planform"
 * @gpcsoft.domain
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_COMPLETED_WORKTASK")
public class CompletedWorkTask implements GpcBaseObject,IPropertyCUserTime{

	@Id
	@Column(name="COMPED_WTASK_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="WORKTASK_ID")
	private WaitprocWorkTask workTask;
	
	@Column(name="WORKTASK_NAME")
	private String worktaskName;
	
	@Column(name="WORKTASK_TYPE")
	private String worktaskType;
	
	@Column(name="OPINION")
	private String opinion;
	
	@Column(name="PROC_TYPE")
	private String procType;
	
	@Column(name="VIEW_PROC_RESULTURL")
	private String viewProcResultUrl;

	@Column(name="BIZ_ID")
	private String bizId;
	
	@Column(name="PROCESS_RESULT")
	private String prcessResult;
	
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="CREATE_USER")
	private User createUser;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public WaitprocWorkTask getWorkTask() {
		return workTask;
	}

	public void setWorkTask(WaitprocWorkTask workTask) {
		this.workTask = workTask;
	}

	public String getWorktaskName() {
		return worktaskName;
	}

	public void setWorktaskName(String worktaskName) {
		this.worktaskName = worktaskName;
	}

	public String getWorktaskType() {
		return worktaskType;
	}

	public void setWorktaskType(String worktaskType) {
		this.worktaskType = worktaskType;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getProcType() {
		return procType;
	}

	public void setProcType(String procType) {
		this.procType = procType;
	}

	public String getViewProcResultUrl() {
		return viewProcResultUrl;
	}

	public void setViewProcResultUrl(String viewProcResultUrl) {
		this.viewProcResultUrl = viewProcResultUrl;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getPrcessResult() {
		return prcessResult;
	}

	public void setPrcessResult(String prcessResult) {
		this.prcessResult = prcessResult;
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
