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
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 * @Description: 待办工作任务
 * @version V1.0
 * @Create Date 2010-8-2 上午09:25:36 By wanghz   
 * @gpcsoft.package packageDir="com.gpcsoft.epp.worktask"
 * @gpcsoft.page domain="worktask" project="es/planform"
 * @gpcsoft.domain
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_WAITPROCWORKTASK")
public class WaitprocWorkTask implements GpcBaseObject{

	@Id
	@Column(name="WORKTASK_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@Column(name="WORKTASK_NAME")
	private String worktaskName;						// 任务名称
	
	@Column(name="WORKTASK_TYPE")
	private String worktaskType;						// 任务类别
	
	@Column(name="BIZ_ID")
	private String bizId;								// 流程ID
	
	@Column(name="WORKTASK_STATUS")
	private String worktaskStatus;						// 任务状态
	
	@Column(name="PARENT_BIZ_ID")
	private String parentBizId;							// 上级任务流程ID
	
	@Column(name="EARLY_WARNING_DAYS")
	private Integer earlyWarningDays;					// 预警时间(天)
	
	@Column(name="HASTEN_DAYS")
	private Integer hastenDays;							// 催办时间(天)
	
	@Column(name="OVERDUE_DAYS")
	private Integer overdueDays;						// 迟到时间(天)
	
	@Column(name="PRIORITY_TYPE")
	private String priorityType;						// 任务优先级
	
	@Column(name="USE_STATUS")
	private String useStatus;							// 使用状态
	
	@Column(name="CREATE_TIME")
	private Date createTime;							// 创建时间
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="CREATE_USER")
	private User createUser;							// 创建人
	
	@Column(name="PROC_VIEW_URL")
	private String procViewURL;
	
	@Column(name="PROC_KIND")
	private String procKind;							// 任务处理种类
	
	public String getObjId() {
		return objId;
	}

	/**
	 * @gpcsoft.property title="计划任务名称"
	 */
	public String getWorktaskName() {
		return worktaskName;
	}

	/**
	 * @gpcsoft.property title="任务类别"
	 */
	public String getWorktaskType() {
		return worktaskType;
	}

	/**
	 * @gpcsoft.property title="任务流程ID"
	 */
	public String getBizId() {
		return bizId;
	}

	/**
	 * @gpcsoft.property title="任务状态"
	 */
	public String getWorktaskStatus() {
		return worktaskStatus;
	}

	/**
	 * @gpcsoft.property title="上级任务流程ID"
	 */
	public String getParentBizId() {
		return parentBizId;
	}

	/**
	 * @gpcsoft.property title="预警时间(天)"
	 */
	public Integer getEarlyWarningDays() {
		return earlyWarningDays;
	}

	/**
	 * @gpcsoft.property title="催办时间(天)"
	 */
	public Integer getHastenDays() {
		return hastenDays;
	}

	/**
	 * @gpcsoft.property title="延期时间(天)"
	 */
	public Integer getOverdueDays() {
		return overdueDays;
	}

	/**
	 * @gpcsoft.property title="任务优先级"
	 */
	public String getPriorityType() {
		return priorityType;
	}

	/**
	 * @gpcsoft.property title="使用状态"
	 */
	public String getUseStatus() {
		return useStatus;
	}

	/**
	 * @gpcsoft.property title="创建时间"
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @gpcsoft.property title="创建人"
	 */
	public User getCreateUser() {
		return createUser;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setWorktaskName(String worktaskName) {
		this.worktaskName = worktaskName;
	}

	public void setWorktaskType(String worktaskType) {
		this.worktaskType = worktaskType;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public void setWorktaskStatus(String worktaskStatus) {
		this.worktaskStatus = worktaskStatus;
	}

	public void setParentBizId(String parentBizId) {
		this.parentBizId = parentBizId;
	}

	public void setEarlyWarningDays(Integer earlyWarningDays) {
		this.earlyWarningDays = earlyWarningDays;
	}

	public void setHastenDays(Integer hastenDays) {
		this.hastenDays = hastenDays;
	}

	public void setOverdueDays(Integer overdueDays) {
		this.overdueDays = overdueDays;
	}

	public void setPriorityType(String priorityType) {
		this.priorityType = priorityType;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public String getProcViewURL() {
		return procViewURL;
	}

	public void setProcViewURL(String procViewURL) {
		this.procViewURL = procViewURL;
	}

	public String getProcKind() {
		return procKind;
	}

	public void setProcKind(String procKind) {
		this.procKind = procKind;
	}
	
}