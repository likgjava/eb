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
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.srplatform.auth.domain.User;


/** 
 *  Comments: <strong>参选对象</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   agreement                    					          
 *  <br/>Module ID: 公共服务平台     		
 *  <br/>Create Date：2011-1-14 下午02:26:41 by yangxb    					                            
 *  <br/>Modified Date:  2011-1-14 下午02:26:41 by yangxb                                   
 *<p>@since 0.5
 *   @version: 0.5 
 *   
 * @gpcsoft.package packageDir="com.gpcsoft.smallscale.vote"
 * @gpcsoft.page domain="vote" project="smallscale"
 * @hibernate.class table="VOTE_ASSESSED_THING"
 * @author Administrator
 * @version 1.0
 */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "VOTE_ASSESSED_THING")
@OrderProperty(flag = "true", property = "assessSort")
public class VoteAssessedThing implements GpcBaseObject,IPropertyCUserTime {

	private static final long serialVersionUID = -50735523275364549L;
	
	/** ID */
	@Id
	@Column(name = "ASSESSED_THING_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;	
	
	/** 投票模板 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="TEMPLATE_ID") 
    @BatchSize(size = 15)
	private VoteTemplate voteTemplate;
    
	/** 评选组 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="VOTE_UNIT_GROUP_ID") 
    @BatchSize(size = 15)
	private VoteUnitGroup voteUnitGroup;
    
    /**参选对象     */
    @Column(name="ATTENDER")
    private String attender;
    
    /**参选对象名称     */
    @Column(name="ATTENDER_NAME")
    private String attenderName;
    
	/** 是否推荐 (1：是,0：否)*/
	@Column(name = "IS_RECOMMENDED")
	private Boolean isRecommended;
	
    /** 参选单位排序 */
	@Column(name = "ASSESS_SORT")
	private Integer assessSort;
	
    /**图片*/
	@Column(name="PICTURE_ID")
    private String  picture;
	
    /**描述*/
	@Column(name="THING_COMMENT")
    private String  thingComment;
	
    /**使用状态（注：00.草稿,01.有效）*/
    @Column(name = "USE_STATUS", length = 2)
    private String useStatus;
    
    /**统计投票次数*/
    @Column(name = "VOTE_COUNT_NUM")
    private Integer voteCountNum;
    
    /**统计评论次数*/
    @Column(name = "VOTE_COMMENT_NUM")
    private Integer voteCommentNum;
    
	/**统计最终分值成绩 */
	@Column(name = "VOTE_FINAL_COUNT_GRADE", precision = 3, scale = 6)
	private BigDecimal voteFinalCountGrade;
	
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
	 * @gpcsoft.property title="voteTemplate"
	 */
	public VoteTemplate getVoteTemplate() {
		return voteTemplate;
	}

	/** voteTemplate */
	public void setVoteTemplate(VoteTemplate voteTemplate) {
		this.voteTemplate = voteTemplate;
	}

	/**
	 * @gpcsoft.property title="picture"
	 */
	public String getPicture() {
		return picture;
	}

	/** picture */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * @gpcsoft.property title="thingComment"
	 */
	public String getThingComment() {
		return thingComment;
	}

	/** thingComment */
	public void setThingComment(String thingComment) {
		this.thingComment = thingComment;
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

	/** voteUnitGroup */
	public void setVoteUnitGroup(VoteUnitGroup voteUnitGroup) {
		this.voteUnitGroup = voteUnitGroup;
	}

	/**
	 * @gpcsoft.property title="voteUnitGroup"
	 */
	public VoteUnitGroup getVoteUnitGroup() {
		return voteUnitGroup;
	}

	/** attender */
	public void setAttender(String attender) {
		this.attender = attender;
	}

	/**
	 * @gpcsoft.property title="attender"
	 */
	public String getAttender() {
		return attender;
	}

	/** attenderName */
	public void setAttenderName(String attenderName) {
		this.attenderName = attenderName;
	}

	/**
	 * @gpcsoft.property title="attenderName"
	 */
	public String getAttenderName() {
		return attenderName;
	}

	/** isRecommended */
	public void setIsRecommended(Boolean isRecommended) {
		this.isRecommended = isRecommended;
	}

	/**
	 * @gpcsoft.property title="isRecommended"
	 */
	public Boolean getIsRecommended() {
		return isRecommended;
	}

	/**
	 * @gpcsoft.property title="useStatus"
	 */
	public String getUseStatus() {
		return useStatus;
	}

	/** useStatus */
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	/**
	 * @gpcsoft.property title="voteCountNum"
	 */
	public Integer getVoteCountNum() {
		return voteCountNum;
	}

	/** voteCountNum */
	public void setVoteCountNum(Integer voteCountNum) {
		this.voteCountNum = voteCountNum;
	}

	/**
	 * @gpcsoft.property title="voteFinalCountGrade"
	 */
	public BigDecimal getVoteFinalCountGrade() {
		return voteFinalCountGrade;
	}

	/** voteFinalCountGrade */
	public void setVoteFinalCountGrade(BigDecimal voteFinalCountGrade) {
		this.voteFinalCountGrade = voteFinalCountGrade;
	}

	/** voteCommentNum */
	public void setVoteCommentNum(Integer voteCommentNum) {
		this.voteCommentNum = voteCommentNum;
	}

	/**
	 * @gpcsoft.property title="voteCommentNum"
	 */
	public Integer getVoteCommentNum() {
		return voteCommentNum;
	}

	/** assessSort */
	public void setAssessSort(Integer assessSort) {
		this.assessSort = assessSort;
	}

	/**
	 * @gpcsoft.property title="assessSort"
	 */
	public Integer getAssessSort() {
		return assessSort;
	}

}
