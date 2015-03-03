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
import com.gpcsoft.smallscale.expert.domain.ExpertInfo;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  *  Comments: <strong>评审专家</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2011-5-9 上午09:07:43 by zhaojf    					                            
  *  <br/>Modified Date:  2011-5-9 上午09:07:43 by zhaojf                                   
  *<p>@since 0.5
  *   @version: 0.5
  *   
  *  @gpcsoft.package packageDir="com.gpcsoft.smallscale.vote"
  *  @gpcsoft.page domain="vote" project="smallscale"
  *  @hibernate.class table="VOTE_ASSESSED_EXPERT"
  *  @author Administrator
  *  @version 1.0
 */

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "VOTE_ASSESSED_EXPERT")
@OrderProperty(flag = "true", property = "expertSort")
public class VoteAssesseExpert implements GpcBaseObject ,IPropertyCUserTime{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** ID */
	@Id
	@Column(name = "VOTE_ASSESSED_EXPERT_ID", length = 50)
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
    
    /**评选组名称     */
    @Column(name="VOTE_UNIT_GROUP_NAME")
    private String voteUnitGroupName;
    
	/** 评审专家 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="EXPERT_ID") 
    @BatchSize(size = 15)
	private ExpertInfo expertInfo;
    
    /**专家名称     */
    @Column(name="EXPERT_NAME")
    private String expertName;
    
    /**专家图像*/
	@Column(name="EXPERT_PIC")
    private String  expertPic;
	
	 /**专家简介备注*/
	@Column(name="EXPERT_COMMENT")
	private String expertComment;
	
	/** 比例系数 */
	@Column(name = "PROPORTION_FACTOR", precision = 3, scale = 6)
	private BigDecimal proportionFactor;
	
    /** 专家排序 */
	@Column(name = "EXPERT_SORT")
	private Integer expertSort;
	
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
	 * @gpcsoft.property title="voteUnitGroup"
	 */
	public VoteUnitGroup getVoteUnitGroup() {
		return voteUnitGroup;
	}

	/** voteUnitGroup */
	public void setVoteUnitGroup(VoteUnitGroup voteUnitGroup) {
		this.voteUnitGroup = voteUnitGroup;
	}

	/**
	 * @gpcsoft.property title="expertInfo"
	 */
	public ExpertInfo getExpertInfo() {
		return expertInfo;
	}

	/** expertInfo */
	public void setExpertInfo(ExpertInfo expertInfo) {
		this.expertInfo = expertInfo;
	}

	/**
	 * @gpcsoft.property title="expertName"
	 */
	public String getExpertName() {
		return expertName;
	}

	/** expertName */
	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}

	/**
	 * @gpcsoft.property title="expertPic"
	 */
	public String getExpertPic() {
		return expertPic;
	}

	/** expertPic */
	public void setExpertPic(String expertPic) {
		this.expertPic = expertPic;
	}

	/**
	 * @gpcsoft.property title="proportionFactor"
	 */
	public BigDecimal getProportionFactor() {
		return proportionFactor;
	}

	/** proportionFactor */
	public void setProportionFactor(BigDecimal proportionFactor) {
		this.proportionFactor = proportionFactor;
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

	/**
	 * @gpcsoft.property title="expertComment"
	 */
	public String getExpertComment() {
		return expertComment;
	}

	/** expertComment */
	public void setExpertComment(String expertComment) {
		this.expertComment = expertComment;
	}

	/**
	 * @gpcsoft.property title="voteUnitGroupName"
	 */
	public String getVoteUnitGroupName() {
		return voteUnitGroupName;
	}

	/** voteUnitGroupName */
	public void setVoteUnitGroupName(String voteUnitGroupName) {
		this.voteUnitGroupName = voteUnitGroupName;
	}

	/** expertSort */
	public void setExpertSort(Integer expertSort) {
		this.expertSort = expertSort;
	}

	/**
	 * @gpcsoft.property title="expertSort"
	 */
	public Integer getExpertSort() {
		return expertSort;
	}



}
