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

/** 
 * @Description: 待办任务接受者信息
 * @version V1.0
 * @Create Date 2010-8-2 上午09:25:36 By wanghz   
 * @gpcsoft.package packageDir="com.gpcsoft.epp.worktask"
 * @gpcsoft.page domain="worktask" project="es/planform"
 * @gpcsoft.domain
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_WORKTASK_RECEIVE")
public class WorktaskReceive implements GpcBaseObject{

	
	@Id
	@Column(name="WORKTASK_REC_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@Column(name="RECEIVE_ID")
	private String receiveId;							// 接收人ID
	
	@Column(name="WORKTASK_RE_TYPE")
	private String worktaskReType;						// 任务接受者类型[接受者为所有、机构、组织、角色或用户]
	
	@Column(name="RECEIVE_NAME")
	private String receiveNam;							// 接收者名称[人名，机构名或角色名]
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="WORKTASK_ID")
	private WaitprocWorkTask workTask;					

	public String getObjId() {
		return objId;
	}

	public String getReceiveId() {
		return receiveId;
	}

	public String getWorktaskReType() {
		return worktaskReType;
	}

	public String getReceiveNam() {
		return receiveNam;
	}

	public WaitprocWorkTask getWorkTask() {
		return workTask;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

	public void setWorktaskReType(String worktaskReType) {
		this.worktaskReType = worktaskReType;
	}

	public void setReceiveNam(String receiveNam) {
		this.receiveNam = receiveNam;
	}

	public void setWorkTask(WaitprocWorkTask workTask) {
		this.workTask = workTask;
	}

	
	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#getCreateTime()
	 */
	public Date getCreateTime() {
		return null;
	}

	
	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#setCreateTime(java.util.Date)
	 */
	public void setCreateTime(Date arg0) {
	}
	
	
	
}
