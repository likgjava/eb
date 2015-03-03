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
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>社区</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-11-25 下午02:55:59 by yucy    					                            
  *  <br/>Modified Date:  2010-11-25 下午02:55:59 by yucy                                   
  *  <p>@since 0.5
  *   
  * @gpcsoft.package packageDir="com.gpcsoft.smallscale"
  * @gpcsoft.page domain="business"
  * @hibernate.class table="ECP_BUSINESS_COMMUNITY"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_BUSINESS_COMMUNITY")
public class Community implements GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime{

	/** serialVersionUID */
	private static final long serialVersionUID = -2981725632453332490L;

	/** ID */
	@Id
	@Column(name = "COMMUNITY_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 社区名称 */
	@Column(name = "COMMUNITY_NAME")
	private String communityName;
	
	/** 投标品目 */
	@Column(name = "TENDER_CATEGORYS")
	private String tenderCategorys;
	
	/** 是否显示 */
	@Column(name = "IS_DISPLAY")
	private Boolean isDisplay;
	
	/** 备注 */
	@Column(name = "MEMO")
	private String memo;
	
	/**是否特色*/
	@Column(name = "ISSPECIAL")
	private Boolean isSpecial;
	
	/**是否推荐*/
	@Column(name = "ISRECOMMENDED")
	private Boolean isRecommended;
	
	 /** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR_ID") 
    @BatchSize(size = 15)
    private User createUser;

    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    /** 修改人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="MODIFIER_ID")
    @BatchSize(size = 15)
    private User updateUser;
    
    /**修改时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_TIME")
    private Date updateTime;
    
    /**加一个社区群id*/
    @Column(name = "GROUP_ID")
    private String groupId;
    
    /**社区图片*/
    @Column(name = "PICTURE")
    private String picture;
    
    /** 所属机构 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="ORG_INFO_ID")
    @BatchSize(size = 15)
    private OrgInfo orgInfo;
    
	/**是否公开*/
	@Column(name = "IS_PUBLIC")
	private Boolean isPublic;
    
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getTenderCategorys() {
		return tenderCategorys;
	}

	public void setTenderCategorys(String tenderCategorys) {
		this.tenderCategorys = tenderCategorys;
	}

	public Boolean getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Boolean isDisplay) {
		this.isDisplay = isDisplay;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Boolean getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(Boolean isSpecial) {
		this.isSpecial = isSpecial;
	}

	public Boolean getIsRecommended() {
		return isRecommended;
	}

	public void setIsRecommended(Boolean isRecommended) {
		this.isRecommended = isRecommended;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public OrgInfo getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
}
