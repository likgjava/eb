package com.gpcsoft.smallscale.groupbuying.domain;

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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>团购供应商</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   smallscale                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2011-6-21 上午08:49:53 by likg    					                            
  *  <br/>Modified Date:  2011-6-21 上午08:49:53 by likg                                   
  *  <p>@since 0.5
  *
  * @gpcsoft.package packageDir="com.gpcsoft.smallscale.groupbuying"
  * @gpcsoft.page domain="GroupSupplier"
  * @hibernate.class table="EPS_GROUP_SUPPLIER"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_GROUP_SUPPLIER")
public class GroupSupplier implements GpcBaseObject ,IPropertyCUserTime {

	/** serialVersionUID */
	private static final long serialVersionUID = 621937758909271748L;

	/**记录号*/
    @Id
    @Column(name = "GROUP_BUYER_ID", length=50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**所属机构*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="ORG_INFO_ID")
    @BatchSize(size=15)
    private OrgInfo orgInfo;
    
    /**所属团购*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="GROUP_BUYING_ID")
    @BatchSize(size=15)
    private GroupBuying groupBuying;
    
    /**卖价*/
    @Column(name="ASK_PRICE")
    private BigDecimal askPrice;
    
    /**是否参与供货*/
    @Column(name="IS_DEAL")
    private Boolean isDeal;
    
    /**创建人*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="CREATOR_ID")
    @BatchSize(size = 15)
    private User createUser;
    
    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /** @gpcsoft.property title="记录号" */
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/** @gpcsoft.property title="所属机构" */
	public OrgInfo getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}

	/** @gpcsoft.property title="所属团购" */
	public GroupBuying getGroupBuying() {
		return groupBuying;
	}

	public void setGroupBuying(GroupBuying groupBuying) {
		this.groupBuying = groupBuying;
	}

	/** @gpcsoft.property title="卖价" */
	public BigDecimal getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(BigDecimal askPrice) {
		this.askPrice = askPrice;
	}

	/** @gpcsoft.property title="是否参与供货" */
	public Boolean getIsDeal() {
		return isDeal;
	}

	public void setIsDeal(Boolean isDeal) {
		this.isDeal = isDeal;
	}

	/** @gpcsoft.property title="创建人" */
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	/** @gpcsoft.property title="创建时间" */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}
