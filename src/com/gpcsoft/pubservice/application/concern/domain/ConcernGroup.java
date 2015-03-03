package com.gpcsoft.pubservice.application.concern.domain;

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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>关注分组</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-11-2 下午01:02:33 by guoyr    					                            
  *  <br/>Modified Date:  2010-11-2 下午01:02:33 by guoyr   
  *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.concern"
  *  @gpcsoft.page domain="concerm" project="pubservice/application"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="关注分组"                              
  *  @since 0.5
  *  @version: 0.5 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PUB_CONCERN_GROUP")
@OrderProperty(property="sort", flag="true")
public class ConcernGroup implements GpcBaseObject {
    
	/** serialVersionUID */
	private static final long serialVersionUID = 2711365021142479938L;

	/**主键*/
    @Id
    @Column(name = "CONCERN_GROUP_ID")
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;    
    
    /**关注分组名称 */
    @Column(name = "GROUP_NAME")
    private String groupName;
    
    /**关注分组类型：01:供应商 02:采购人 */
    @Column(name = "GROUP_TYPE")
    private String groupType;
    
    /**所属人*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="BELONGS_USER") 
    @BatchSize(size = 15)
    private User belongsUser;
    
    /**所属机构（冗余）*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="BELONGS_ORG") 
    @BatchSize(size = 15)
    private OrgInfo belongsOrg;
    
    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    /**排序*/
    @Column(name = "SORT_NO")
    private Integer sort;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public User getBelongsUser() {
		return belongsUser;
	}

	public void setBelongsUser(User belongsUser) {
		this.belongsUser = belongsUser;
	}

	public OrgInfo getBelongsOrg() {
		return belongsOrg;
	}

	public void setBelongsOrg(OrgInfo belongsOrg) {
		this.belongsOrg = belongsOrg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}


	
}