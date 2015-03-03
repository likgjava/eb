package com.gpcsoft.epp.project.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.gpcsoft.core.model.GpcBaseObject;

/** 
  *  Comments: <strong>招标项目统计视图</strong>      
  *        		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-7-30 下午03:55:45 by liuy    					                            
  *  <br/>Modified Date:  2010-7-30 下午03:55:45 by liuy  
  *  
  *  @gpcsoft.package packageDir="com.gpcsoft.epp.project"
  *  @gpcsoft.page domain="planform/project" project="es"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="招标项目统计视图"  
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity
@Table(name = "ECP_V_PROJECT_COUNT")
public class ProjectCountView implements GpcBaseObject{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "projId", length = 36)
	private String objId; //主键
	
	@Column(name = "subProjectC")
	private BigDecimal subProjectC = new BigDecimal("0"); //分包数目
	
	@Column(name = "signUpC")
	private BigDecimal signUpC = new BigDecimal("0");; //参与供应商数目

	@Column(name = "signUpValidC")
	private BigDecimal signUpValidC = new BigDecimal("0");; //有效参与供应商数目
	
	@Column(name = "bidC")
	private BigDecimal bidC = new BigDecimal("0");; //参与投标供应商数目
	
	@Column(name = "bidValidC")
	private BigDecimal bidValidC = new BigDecimal("0");; //有效参与投标供应商数目
	
	@Column(name = "winnerC")
	private BigDecimal winnerC = new BigDecimal("0");; //中标供应商数目

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public BigDecimal getSubProjectC() {
		return this.subProjectC;
	}

	public void setSubProjectC(BigDecimal subProjectC) {
		this.subProjectC = subProjectC;
	}

	public BigDecimal getSignUpC() {
		return this.signUpC;
	}

	public void setSignUpC(BigDecimal signUpC) {
		this.signUpC = signUpC;
	}

	public BigDecimal getSignUpValidC() {
		return this.signUpValidC;
	}

	public void setSignUpValidC(BigDecimal signUpValidC) {
		this.signUpValidC = signUpValidC;
	}

	public BigDecimal getBidC() {
		return this.bidC;
	}

	public void setBidC(BigDecimal bidC) {
		this.bidC = bidC;
	}

	public BigDecimal getBidValidC() {
		return this.bidValidC;
	}

	public void setBidValidC(BigDecimal bidValidC) {
		this.bidValidC = bidValidC;
	}

	public BigDecimal getWinnerC() {
		return this.winnerC;
	}

	public void setWinnerC(BigDecimal winnerC) {
		this.winnerC = winnerC;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCreateTime(Date arg0) {
		// TODO Auto-generated method stub
		
	}
}
