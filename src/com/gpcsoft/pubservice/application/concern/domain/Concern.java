package com.gpcsoft.pubservice.application.concern.domain;

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
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>关注</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-11-2 下午01:02:33 by guoyr    					                            
  *  <br/>Modified Date:  2010-11-2 下午01:02:33 by guoyr   
  *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.concern"
  *  @gpcsoft.page domain="concerm" project="pubservice/application"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="关注"                              
  *  @since 0.5
  *  @version: 0.5 
  */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PUB_CONCERN")
public class Concern implements GpcBaseObject ,IPropertyCUserTime{
    
	/** serialVersionUID */
	private static final long serialVersionUID = 2711365021142479938L;

	/**主键*/
    @Id
    @Column(name = "CONCERN_ID")
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**所属分组*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CONCERN_GROUP") 
    @BatchSize(size = 15)
    private ConcernGroup concernGroup;
    
    /**所属名单类型：01:我的客户 02:黑名单 03：潜在客户*/
    @Column(name = "LIST_TYPE")
    private String listType;
    
    /**客户人*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="ORGINFO") 
    @BatchSize(size = 15)
    private OrgInfo orgInfo;
    
    /**客户管理员*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="USER_ID") 
    @BatchSize(size = 15)
    private User user;
    
    /**与客户的成交量(笔)*/
    @Formula("(select count(o.order_id) from EPS_AGREEMENT_ORDER o where o.USE_STATUS = '01' and o.S_CONFIRM_STATUS = '01' and o.B_CONFIRM_STATUS = '01' and (o.SUPPLIER_ID = ORGINFO and o.BUYER_ID = (select g.BELONGS_ORG from ECP_PUB_CONCERN_GROUP g where g.CONCERN_GROUP_ID = CONCERN_GROUP)) or (o.buyer_id = ORGINFO and o.supplier_id = (select g.BELONGS_ORG from ECP_PUB_CONCERN_GROUP g where g.CONCERN_GROUP_ID = CONCERN_GROUP)))")
    private Integer bargainSumNum;
    
    /**与客户成交的总金额*/
    @Formula("(select sum(o.goods_total) from EPS_AGREEMENT_ORDER o where o.USE_STATUS = '01' and o.S_CONFIRM_STATUS = '01' and o.B_CONFIRM_STATUS = '01' and (o.SUPPLIER_ID = ORGINFO and o.BUYER_ID = (select g.BELONGS_ORG from ECP_PUB_CONCERN_GROUP g where g.CONCERN_GROUP_ID = CONCERN_GROUP)) or (o.buyer_id = ORGINFO and o.supplier_id = (select g.BELONGS_ORG from ECP_PUB_CONCERN_GROUP g where g.CONCERN_GROUP_ID = CONCERN_GROUP)))")
    private BigDecimal bargainSumMoney;
    
    /**与客户最近的交易时间*/
    @Formula("(select max(o.s_confirm_date) from EPS_AGREEMENT_ORDER o where o.USE_STATUS = '01' and o.S_CONFIRM_STATUS = '01' and o.B_CONFIRM_STATUS = '01' and (o.SUPPLIER_ID = ORGINFO and o.BUYER_ID = (select g.BELONGS_ORG from ECP_PUB_CONCERN_GROUP g where g.CONCERN_GROUP_ID = CONCERN_GROUP)) or (o.buyer_id = ORGINFO and o.supplier_id = (select g.BELONGS_ORG from ECP_PUB_CONCERN_GROUP g where g.CONCERN_GROUP_ID = CONCERN_GROUP)))")
    private Date bargainLastlyDate;
    
    /**所属人*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="BELONGS_USER") 
    @BatchSize(size = 15)
    private User belongsUser;

    /** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR_ID") 
    @BatchSize(size = 15)
    private User createUser;
    
    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}


	public ConcernGroup getConcernGroup() {
		return concernGroup;
	}

	public void setConcernGroup(ConcernGroup concernGroup) {
		this.concernGroup = concernGroup;
	}
	
	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public OrgInfo getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @gpcsoft.property title="bargainSumNum"
	 */
	public Integer getBargainSumNum() {
		return bargainSumNum;
	}

	/** bargainSumNum */
	public void setBargainSumNum(Integer bargainSumNum) {
		this.bargainSumNum = bargainSumNum;
	}

	/**
	 * @gpcsoft.property title="bargainSumMoney"
	 */
	public BigDecimal getBargainSumMoney() {
		return bargainSumMoney;
	}

	/** bargainSumMoney */
	public void setBargainSumMoney(BigDecimal bargainSumMoney) {
		this.bargainSumMoney = bargainSumMoney;
	}

	/** bargainLastlyDate */
	public void setBargainLastlyDate(Date bargainLastlyDate) {
		this.bargainLastlyDate = bargainLastlyDate;
	}

	/**
	 * @gpcsoft.property title="bargainLastlyDate"
	 */
	public Date getBargainLastlyDate() {
		return bargainLastlyDate;
	}

	/** belongsUser */
	public void setBelongsUser(User belongsUser) {
		this.belongsUser = belongsUser;
	}

	/**
	 * @gpcsoft.property title="belongsUser"
	 */
	public User getBelongsUser() {
		return belongsUser;
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
	
}