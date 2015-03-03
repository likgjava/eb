package com.gpcsoft.smallscale.expert.domain;

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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.core.model.VerifyObject;
import com.gpcsoft.smallscale.expert.enumeration.ExpertEnum;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>专家申请</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   agreement                    					          
 *  <br/>Module ID: 小额交易平台     		
 *  <br/>Create Date：2011-1-6 上午10:13:04 by likg
 *  <br/>Modified Date:  2011-1-6 上午10:13:04 by likg
 *  <p>@since 0.5
 *   
 * @gpcsoft.package packageDir="com.gpcsoft.smallscale"
 * @gpcsoft.page domain="expert"
 * @hibernate.class table="EXPERT_INFO_APPLY"
 * @author Administrator
 * @version 1.0
 */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EXPERT_INFO_APPLY")
@OrderProperty(property="createTime", flag="false")
public class ExpertInfoApply  implements GpcBaseObject,IPropertyCUserTime,VerifyObject {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EXPERT_INFO_APPLY_ID",length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 专家 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="EXPERT_INFO_ID")
	@BatchSize(size = 15)
	private ExpertInfo expertInfo;
	
	/** 申请类型 */
	@Column(name = "APPLY_TYPE", length = 100)
	private String applyType;
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATOR")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User createUser;
	
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 7)
	private Date createTime;
	
	/** 申请人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="APPLIER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User applyUser;
	
	/** 申请时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "APPLY_TIME", length = 7)
	private Date applyTime;
	
	/** 审核状态[00:草稿;01:待审;02:通过,03:不通过] */
	@Column(name = "AUDIT_STATUS", length = 2)
	private String auditStatus;
	
	@Transient
	private String auditStatusCN;
	
	 /** 审核人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="VERIFIER_ID") 
	@BatchSize(size = 15)
	private User verifyUser;
	
    /** 审核时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VERIFY_TIME")
    private Date verifyTime;
    
    /** 意见 */
    @Column(name = "OPINION", length = 500)
    private String opinion;

	public Date getApplyTime() {
		return applyTime;
	}

	public String getApplyType() {
		return applyType;
	}

	public User getApplyUser() {
		return applyUser;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public String getAuditStatusCN() {
		this.auditStatusCN = ExpertEnum.getAuditStatusCN(this.auditStatus);
		return this.auditStatusCN;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public ExpertInfo getExpertInfo() {
		return expertInfo;
	}

	public String getObjId() {
		return objId;
	}

	public String getOpinion() {
		return opinion;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public User getVerifyUser() {
		return verifyUser;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public void setApplyUser(User applyUser) {
		this.applyUser = applyUser;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public void setAuditStatusCN(String auditStatusCN) {
		this.auditStatusCN = auditStatusCN;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public void setExpertInfo(ExpertInfo expertInfo) {
		this.expertInfo = expertInfo;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	public void setVerifyUser(User verifyUser) {
		this.verifyUser = verifyUser;
	}
}
