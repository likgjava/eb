package com.gpcsoft.smallscale.point.domain;

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

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.User;


/** 
 *  Comments: <strong>推广人</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2010-xxxx   		
 *  <br/>Project:                       					          
 *  <br/>Module ID: 公共服务平台     		
 *  <br/>Create Date：2010-9-26 			                            
 *  <br/>Modified Date:  2010-9-26                          
 *   
 *  @gpcsoft.package packageDir="com.gpcsoft.smallscale.point"
 *  @gpcsoft.page domain="point" 
 *  @gpcsoft.domain
 *  @gpcsoft.title value="推广人" 
 */ 
@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_POINT_PROMOTER")
public class Promoter implements GpcBaseObject {
	

	/** 主键ID */
	@Id
    @Column(name = "PROMOTER_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 录入类型（00 采购人记录 01推广人记录） */
	@Column(name = "RECORD_TYPE",length=2)
	private String recordType;
	
	
	/**处理状态 00 未处理 01已处理 */
	@Column(name = "DEAL_STATUS",length=2)
	private String dealStatus;
	
	/** 处理人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="dealuser_ID")  
	@BatchSize(size = 15)//批量抓取
	private User dealUser;
	
	/**推广人姓名 */
	@Column(name = "PROMOTER_NAME",length=50)
	private String promoterName;


	
	/**推广人身份证号码 */
	@Column(name = "PROMOTER_CID",length=50)
	private String promoterCID;
	
	/**推广单位名称 */
	@Column(name = "PROMOTER_UNIT_NAME",length=100)
	private String promoterUnitName;
	
	/**推广单位组织机构代码 */
	@Column(name = "PROMOTER_UNIT_CODE",length=50)
	private String promoterUnitCode;
	
	/**推广的单位联系人 */
	@Column(name = "promoted_Link_Name",length=50)
	private String promotedLinkName;
	
	/**推广的单位联系电话 */
	@Column(name = "promoted_Link_Tel",length=50)
	private String promotedLinkTel;
	
	/**推广的单位Email */
	@Column(name = "email",length=50)
	private String email;
	
	/**推广的单位采购意向 */
	@Column(name = "buy_dispose",length=200)
	private String buyDispose;
	
	/**验证码 */
	@Column(name = "validation_code",length=50)
	private String validationCode;
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="CREATOR_ID")  
	@BatchSize(size = 15)//批量抓取
	private User creator;


	
	/** 推广人 */	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="USER_ID")  
	@BatchSize(size = 15)//批量抓取
    private User promoter;
    
	/** 推广单位 */	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="ORG_ID")  
	@BatchSize(size = 15)//批量抓取
    private Company org;
    
    /** 推广日期 */
	@Temporal(TemporalType.DATE)
    @Column(name = "PROMOTER_DATE")
    private Date promoterDate;
	
	/** 推广单位联系人 */	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="P_USER_ID")  
	@BatchSize(size = 15)//批量抓取
    private User promoterBuyer;
	
	/** 备注 */
    @Column(name = "PROMOTER_MEMO",length=500)
	private String promoterMemo;
	
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_Time")
	private Date createTime;  



	public String getObjId() {		
		return objId;
	}	
	

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}



	public String getDealStatus() {
		return dealStatus;
	}


	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}

	public String getPromoterName() {
		return promoterName;
	}


	public void setPromoterName(String promoterName) {
		this.promoterName = promoterName;
	}

	public String getPromoterCID() {
		return promoterCID;
	}

	public void setPromoterCID(String promoterCID) {
		this.promoterCID = promoterCID;
	}

	public String getPromoterUnitName() {
		return promoterUnitName;
	}

	public void setPromoterUnitName(String promoterUnitName) {
		this.promoterUnitName = promoterUnitName;
	}



	public String getPromoterUnitCode() {
		return promoterUnitCode;
	}



	public void setPromoterUnitCode(String promoterUnitCode) {
		this.promoterUnitCode = promoterUnitCode;
	}



	public String getPromotedLinkName() {
		return promotedLinkName;
	}



	public void setPromotedLinkName(String promotedLinkName) {
		this.promotedLinkName = promotedLinkName;
	}



	public String getPromotedLinkTel() {
		return promotedLinkTel;
	}



	public void setPromotedLinkTel(String promotedLinkTel) {
		this.promotedLinkTel = promotedLinkTel;
	}



	public String getValidationCode() {
		return validationCode;
	}



	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}



	public User getCreator() {
		return creator;
	}



	public void setCreator(User creator) {
		this.creator = creator;
	}



	public User getPromoter() {
		return promoter;
	}


	public void setPromoter(User promoter) {
		this.promoter = promoter;
	}


	public Company getOrg() {
		return org;
	}


	public void setOrg(Company org) {
		this.org = org;
	}


	public Date getPromoterDate() {
		return promoterDate;
	}


	public void setPromoterDate(Date promoterDate) {
		this.promoterDate = promoterDate;
	}


	public User getPromoterBuyer() {
		return promoterBuyer;
	}


	public void setPromoterBuyer(User promoterBuyer) {
		this.promoterBuyer = promoterBuyer;
	}


	public String getPromoterMemo() {
		return promoterMemo;
	}


	public void setPromoterMemo(String promoterMemo) {
		this.promoterMemo = promoterMemo;
	}


	public void setObjId(String objId) {
		this.objId = objId;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;

	}
	
	public Date getCreateTime() {
		
		return this.createTime;
	}


	public User getDealUser() {
		return dealUser;
	}


	public void setDealUser(User dealUser) {
		this.dealUser = dealUser;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getBuyDispose() {
		return buyDispose;
	}


	public void setBuyDispose(String buyDispose) {
		this.buyDispose = buyDispose;
	}
	

}
