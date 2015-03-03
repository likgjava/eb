package com.gpcsoft.agreement.management.domain;

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

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;

/** 
  *  Comments: <strong>二级协议(供货商协议)</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-4-16 下午04:21:17 by yucy    					                            
  *  <br/>Modified Date:  2010-4-16 下午04:21:17 by yucy                                   
  *  
  *   @since 0.5
  *   @version: 0.5 
  *   
  *  @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.management"
  *  @gpcsoft.page domain="management" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="二级协议(供货商协议)"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREEMENT_PURCHASE_SECOND")
public class AgreementSecond implements GpcBaseObject {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "SECOND_AGREEMENT_ID", unique = true, nullable = false, length = 50)
	private String objId;
	
	/**名称*/
	@Column(name = "NAME", length = 50)
	private String name;
	
	/**协议*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AGREEMENT_ID")	
	private Agreement agreement;
	
	/**供货商*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPLYER_ID")
	private OrgInfo supplyer;

	/**协议区间*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AREA_ID")	
	private Area area ; 
	
	/**开始时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BEGIN_DATE", length = 7)	
	private Date beginDate;
	
	/**结束时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE", length = 7)	
	private Date endDate;
	
	/**备注*/
	@Column(name = "MEMO", length = 200)
	private String memo;
	
	/**内容*/
	@Column(name = "CONTENT", length = 50)
	private String content;

	public void setObjId(String objId){
		this.objId = objId;
	}
	
	public String getObjId() {
		return this.objId;
	}
	
	/** @gpcsoft.property title="一级协议"  */
	public Agreement getAgreement() {
		return agreement;
	}

	
	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}
	/** @gpcsoft.property title="供货商"  */
	public OrgInfo getSupplyer() {
		return supplyer;
	}

	public void setSupplyer(OrgInfo supplyer) {
		this.supplyer = supplyer;
	}
	/** @gpcsoft.property title="开始时间"  */
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	/** @gpcsoft.property title="结束时间"  */
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/** @gpcsoft.property title="备注"  */
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/** @gpcsoft.property title="名称"*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/** @gpcsoft.property title="协议区间"*/
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	/** @gpcsoft.property title="协议内容"*/
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCreateTime(Date createTime) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}