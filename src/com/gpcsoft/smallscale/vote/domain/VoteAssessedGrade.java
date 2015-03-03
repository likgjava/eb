package com.gpcsoft.smallscale.vote.domain;

import java.math.BigDecimal;
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
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  *  Comments: <strong>投票指标分值表</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2011-2-24 下午01:50:18 by zhaojf    					                            
  *  <br/>Modified Date:  2011-2-24 下午01:50:18 by zhaojf                                   
  *<p>@since 0.5
  *   @version: 0.5
  *   
  *   @gpcsoft.package packageDir="com.gpcsoft.smallscale.vote"
  *   @gpcsoft.page domain="vote" project="smallscale"
  *   @hibernate.class table="VOTE_ASSESSED_GRADE"
  *   @author Administrator
  *   @version 1.0
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "VOTE_ASSESSED_GRADE")
public class VoteAssessedGrade implements GpcBaseObject ,IPropertyCUserTime{

	/** serialVersionUID */
	private static final long serialVersionUID = 5208134676862294291L;

	/** ID */
	@Id
	@Column(name = "VOTE_ASSESSED_GRADE_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 投票评选表 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="ASSESSED_ID") 
    @BatchSize(size = 15)
	private VoteAssessed voteAssessed;
    
	/** 主题指标 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="POINTER_ID") 
    @BatchSize(size = 15)
	private VotePointer votePointer;
    
	/** 评分分值 */
	@Column(name = "VOTE_GRADE", precision = 3, scale = 6)
	private BigDecimal grade;
	
	/** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR_ID") 
    @BatchSize(size = 15)
	private User createUser;
	
    /** 创建时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;

	/**
	 * @gpcsoft.property title="objId"
	 */
	public String getObjId() {
		return objId;
	}

	/** objId */
	public void setObjId(String objId) {
		this.objId = objId;
	}

	/**
	 * @gpcsoft.property title="voteAssessed"
	 */
	public VoteAssessed getVoteAssessed() {
		return voteAssessed;
	}

	/** voteAssessed */
	public void setVoteAssessed(VoteAssessed voteAssessed) {
		this.voteAssessed = voteAssessed;
	}

	/**
	 * @gpcsoft.property title="votePointer"
	 */
	public VotePointer getVotePointer() {
		return votePointer;
	}

	/** votePointer */
	public void setVotePointer(VotePointer votePointer) {
		this.votePointer = votePointer;
	}

	/**
	 * @gpcsoft.property title="grade"
	 */
	public BigDecimal getGrade() {
		return grade;
	}

	/** grade */
	public void setGrade(BigDecimal grade) {
		this.grade = grade;
	}

	/**
	 * @gpcsoft.property title="createUser"
	 */
	public User getCreateUser() {
		return createUser;
	}

	/** createUser */
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	/**
	 * @gpcsoft.property title="createTime"
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/** createTime */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
