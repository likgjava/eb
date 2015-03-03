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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;

/** 
  *  Comments: <strong>机构拥有社区</strong>            		
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
  * @hibernate.class table="ECP_BUSINESS_ORG_COMMUNITY"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_BUSINESS_ORG_COMMUNITY")
public class OrgCommunity implements GpcBaseObject {

	/** serialVersionUID */
	private static final long serialVersionUID = -6208781838200084774L;

	/** ID */
	@Id
	@Column(name = "ORG_COMMUNITY_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
    
    /** 所属机构 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="ORG_INFO_ID")
    @BatchSize(size = 15)
    private OrgInfo orgInfo;
    
    /** 社区 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="COMMUNITY_ID")
    @BatchSize(size = 15)
    private Community community;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public OrgInfo getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date arg0) {
		
	}
}
