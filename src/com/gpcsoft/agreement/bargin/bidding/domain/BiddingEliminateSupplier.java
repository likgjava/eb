package com.gpcsoft.agreement.bargin.bidding.domain;
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

import com.gpcsoft.agreement.bargin.project.domain.BargainTurn;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>议价剔除供应商</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   agreement                    					          
 *  <br/>Module ID: 协议供货  		
 *  <br/>Create Date：2010-4-7 下午03:50:06 by yucy    					                            
 *  <br/>Modified Date:  2010-4-7 下午03:50:06 by yucy           
 *                          
 *  @since 0.5
 *  @version: 0.5 
 *  
 *  @gpcsoft.package packageDir="com.gpcsoft.agreement.bargin.barginrecord.domain"
 *  @gpcsoft.page domain="barginrecord" project="agreement"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="议价剔除供应商"
 */ 

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_ELIMINATE_SUPPLYER")
public class BiddingEliminateSupplier implements GpcBaseObject{

	/** serialVersionUID */
	private static final long serialVersionUID = -1708884779222908904L;

	/**记录号*/
    @Id
    @Column(name = "ELIMINATE_SUPPLYER_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**项目*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="PROJECT_ID") 
    @BatchSize(size = 15)
    private Project project;
    
    /**轮次*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="BARGAIN_TURN_ID") 
    @BatchSize(size = 15)
    private BargainTurn barginTurn;
    
    /**轮次号*/
    @Column(name = "ELIMINATE_REASON", length = 50)
    private String eliminateReason;
    
    /**开始时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ELIMINATE_TIME")
    private Date eliminateTime;
    
    /**剔除人*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="ELIMINATER_ID") 
    @BatchSize(size = 15)
    private User eliminateUser;
    
    /**供应商*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="SUPPLIER_ID") 
    @BatchSize(size = 15)
    private OrgInfo supplier;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public BargainTurn getBarginTurn() {
		return barginTurn;
	}

	public void setBarginTurn(BargainTurn barginTurn) {
		this.barginTurn = barginTurn;
	}

	public String getEliminateReason() {
		return eliminateReason;
	}

	public void setEliminateReason(String eliminateReason) {
		this.eliminateReason = eliminateReason;
	}

	public Date getEliminateTime() {
		return eliminateTime;
	}

	public void setEliminateTime(Date eliminateTime) {
		this.eliminateTime = eliminateTime;
	}

	public User getEliminateUser() {
		return eliminateUser;
	}

	public void setEliminateUser(User eliminateUser) {
		this.eliminateUser = eliminateUser;
	}

	public OrgInfo getSupplier() {
		return supplier;
	}

	public void setSupplier(OrgInfo supplier) {
		this.supplier = supplier;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date arg0) {
		
	}


    
}