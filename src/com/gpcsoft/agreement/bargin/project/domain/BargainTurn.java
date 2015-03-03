package com.gpcsoft.agreement.bargin.project.domain;

import java.math.BigDecimal;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.epp.project.domain.Project;
/**
 * 竞价轮次
 * @gpcsoft.package packageDir="com.gpcsoft.agreement.bargin.project"
 * @gpcsoft.page domain="project"
 * @hibernate.class table="EPS_AGREEMENT_BARGAIN_TURN"
 * @author sunl
 * @version 1.0
 * @created 12-四月-2010 9:32:16
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)

@Table(name = "EPS_AGREEMENT_BARGAIN_TURN")
public class BargainTurn implements GpcBaseObject{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BARGAIN_TURN_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; //主键
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(
	name="PROJECT_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Project project;

	@Column(name = "TRUN_NO")
	private Integer turnNo;//轮次号
	
	@Column(name = "START_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;//开始时间
	
	@Column(name = "END_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;//结束时间
	
	/** 降幅类型[01:百分比;02:元] */
	@Column(name = "TOTAL_CUT_TYPE", length = 2)
	private String totalCutType;
	
	@Column(name = "TOTAL_CUT")
	private BigDecimal totalCut;//降价幅度

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getTotalCutType() {
		return totalCutType;
	}

	public void setTotalCutType(String totalCutType) {
		this.totalCutType = totalCutType;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Integer getTurnNo() {
		return turnNo;
	}

	public void setTurnNo(Integer turnNo) {
		this.turnNo = turnNo;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getTotalCut() {
		return totalCut;
	}

	public void setTotalCut(BigDecimal totalCut) {
		this.totalCut = totalCut;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCreateTime(Date arg0) {
		// TODO Auto-generated method stub
		
	}
}
