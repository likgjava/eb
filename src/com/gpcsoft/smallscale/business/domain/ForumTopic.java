package com.gpcsoft.smallscale.business.domain;

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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>商圈社区主题</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2010-xxxx   		
 *  <br/>Project:                       					          
 *  <br/>Module ID:    		
 *  <br/>Create Date：2011-1-27 			                            
 *  <br/>Modified Date:  2011-1-27                         
 *   
 *  @gpcsoft.package packageDir="com.gpcsoft.smallscale.business"
 *  @gpcsoft.page domain="business" 
 *  @gpcsoft.domain
 *  @gpcsoft.title value="商圈社区主题" 
 */ 
@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_FORUM_TOPIC")
public class ForumTopic implements GpcBaseObject, IPropertyCUserTime,
		IPropertyUUserTime {
	
	/** 主键ID */
	@Id
    @Column(name = "TOPIC_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 发表机构 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="ORG_INFO_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private OrgInfo orginfo;
	
	/** 所属社区 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="COMMUNITY_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Community community;
	
	/** 标题 */
	@Column(name = "title", length = 100)
	private String title;
	
	/** 内容 */
	@Column(name = "content", length = 4000)
	private String content;
	
	/** 附件 */
	@Column(name = "attachment", length = 100)
	private String attachment;
	
	/** 是否显示 */
	@Column(name = "is_show", length = 1)
	private Boolean isShow;
	
	/** 是否置顶 */
	@Column(name = "is_top", length = 1)
	private Boolean isTop;
	
	/** 是否精华 */
	@Column(name = "is_elite", length = 1)
	private Boolean isElite;
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="creator_ID")  
	@BatchSize(size = 15)//批量抓取
	private User createUser;
	
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_Time")
	private Date createTime;	
	
	/** 修改人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="modify_id")  
	@BatchSize(size = 15)//批量抓取
	private User updateUser;
	
	/** 修改时间 */
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_time")
	private Date updateTime;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public OrgInfo getOrginfo() {
		return orginfo;
	}

	public void setOrginfo(OrgInfo orginfo) {
		this.orginfo = orginfo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	public Boolean getIsTop() {
		return isTop;
	}

	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}

	/** isElite */
	public void setIsElite(Boolean isElite) {
		this.isElite = isElite;
	}

	/**
	 * @gpcsoft.property title="isElite"
	 */
	public Boolean getIsElite() {
		return isElite;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}
}
