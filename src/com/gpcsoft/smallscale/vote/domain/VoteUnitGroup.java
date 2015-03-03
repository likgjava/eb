package com.gpcsoft.smallscale.vote.domain;

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
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.smallscale.vote.enumeration.VoteEnum;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  *  Comments: <strong>参选对象分组</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2011-4-25 上午10:18:28 by zhaojf    					                            
  *  <br/>Modified Date:  2011-4-25 上午10:18:28 by zhaojf                                   
  *<p>@since 0.5
  *   @version: 0.5
  *  
  *  @gpcsoft.package packageDir="com.gpcsoft.smallscale.vote"
  *  @gpcsoft.page domain="vote" project="smallscale"
  *  @hibernate.class table="VOTE_UNIT_GROUP"
  *  @author Administrator
  *  @version 1.0
 */

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "VOTE_UNIT_GROUP")
public class VoteUnitGroup implements GpcBaseObject ,IPropertyCUserTime{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** ID */
	@Id
	@Column(name = "VOTE_GROUP_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 投票模板 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="TEMPLATE_ID") 
    @BatchSize(size = 15)
	private VoteTemplate voteTemplate;
    
    /**评选组类型(00：企业；01：品牌；02：服务；03：商品)*/
	@Column(name = "VOTE_GROUP_TYPE")
    private String groupType;
	
	/**评选组类型别名 */
	@Transient
	private String groupTypeCN;
    
	/** 分组名称 */
	@Column(name = "VOTE_GROUP_NAME")
	private String groupName;
	
	/** 备注*/
	@Column(name = "MEMO",length=500)
	private String memo;
	
	/** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR_ID") 
    @BatchSize(size = 15)
	private User createUser;
	
    /** 创建时间 投票时间*/
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
	 * @gpcsoft.property title="groupName"
	 */
	public String getGroupName() {
		return groupName;
	}

	/** groupName */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @gpcsoft.property title="memo"
	 */
	public String getMemo() {
		return memo;
	}

	/** memo */
	public void setMemo(String memo) {
		this.memo = memo;
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

	/** groupType */
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	/**
	 * @gpcsoft.property title="groupType"
	 */
	public String getGroupType() {
		return groupType;
	}

	/** groupTypeCN */
	public void setGroupTypeCN(String groupTypeCN) {
		this.groupTypeCN = groupTypeCN;
	}

	/**
	 * @gpcsoft.property title="groupTypeCN"
	 */
	public String getGroupTypeCN() {
		this.groupTypeCN = VoteEnum.getGroupTypeCN(this.groupType);
		return groupTypeCN;
	}
 }
