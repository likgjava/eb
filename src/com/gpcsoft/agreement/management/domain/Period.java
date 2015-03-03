package com.gpcsoft.agreement.management.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;

/**
 * EpsAgreementPeriod entity. @author MyEclipse Persistence Tools
 */
/** 
  *  Comments: <strong>期间</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 协议供货-期间     		
  *  <br/>Create Date：2010-4-16 下午04:41:30 by yucy    					                            
  *  <br/>Modified Date:  2010-4-16 下午04:41:30 by yucy                                   
  *  
  *   @since 0.5
  *   @version: 0.5 
  *   
  *  @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.management"
  *  @gpcsoft.page domain="management" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="期间"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREEMENT_PERIOD")
public class Period implements GpcBaseObject{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "PERIOD_ID", unique = true, nullable = false, length = 50)
	private String objId;
	
	/**期间名称*/
	@Column(name = "PERIOD_NAME", length = 100)
	private String periodName;
	
	/**开始日期*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BEGIN_DATE", length = 7)	
	private Date beginDate;
	
	/**结束日期*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE", length = 7)	
	private Date endDate;
	
	/**备注*/
	@Column(name = "MEMO", length = 200)
	private String memo;

	public void setObjId(String objId){
		this.objId = objId;
	}
	
	public String getObjId() {
		return this.objId;
	}
	
	/** @gpcsoft.property title="期间名称"  */
	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
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

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCreateTime(Date createTime) {
		// TODO Auto-generated method stub
		
	}

	
}