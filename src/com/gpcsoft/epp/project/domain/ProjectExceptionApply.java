package com.gpcsoft.epp.project.domain;

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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>ProjectPuase</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:                    					          
  *  <br/>Module ID: 执行平台    		
  *  <br/>Create Date：2010-10-11 下午01:34:32 by yangx    					                            
  *  <br/>Modified Date:  2010-10-11 下午01:34:32 by yangx                                   
  *	  @since 0.5
  *   @version: 0.5 
  * @gpcsoft.package packageDir="com.gpcsoft.epp.project"
  * @gpcsoft.page domain="planform/project" project="es"
  * @gpcsoft.domain
  * @gpcsoft.title value="异常申请"
  */ 
@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_FAILPROJ_REQ")
public class ProjectExceptionApply extends WorkFlowModel implements GpcBaseObject, IPropertyCUserTime, IPropertyUUserTime{

	@Id
	@Column(name = "FAIL_REQ_ID", nullable=false, length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="PROJ_ID") 		
	private Project project; //招标项目
	
	@Column(name = "FAIL_EBUYMETHOD", length = 2)
	private String failEbuyMethod;//采购方式
	
	@Column(name = "AGENTY_ID")
	private String agentyId; //代理机构Id

	@Column(name = "AGENTY_NAME")
	private String agentyName; //代理机构名称
	
	@Column(name = "FAIL_KIND")
	private String failKind; //失败种类
	
	@Column(name = "FAIL_REASONTYPE")
	private String failPeasontype; //失败原因类型
	
	@Column(name = "REASON_DESC")
	private String reasonDesc; //原因说明
	
	@Column(name = "ADVICE_PROCWAY")
	private String adviceProcway; //建议处理方式01：重新招标 ，02：变更采购方式，03：终止项目
	
	@Transient
	private String adviceProcwayCN;
	
	@Column(name = "ADVICE_DESC")
	private String adviceDesc; //建议说明
	
	@Column(name = "FORMER_EBUYMETHOD")
	private String formerEbuyMethod; //原采购方式
	
	@Column(name = "NEW_EBUYMETHOD")
	private String newEbuyMethod; //新采购方式
	
	@Transient
	private String newEbuyMethodCN;

	@Column(name = "RE_CREATE_PROJ")
	private String reCreateProj; //是否重新立项
	
	@Column(name = "NEW_EVALUATION_METHOD")
	private String newEvaluationMethod; //新评审方法
	
	@Column(name = "PROPOSER_TYPE")
	private String proposerType; //申请人类型
	
	@Column(name = "AUDIT_STATUS")
	private String auditStatus; //审核状态
	
	@Transient
	private String auditStatusCN;
	
	@Column(name = "USE_STATUS", length = 2)
	private String useStatus;//使用状态
	
	//创建人
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATE_USER", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User createUser;

	//创建时间
    @Column(name = "CREATE_TIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    //修改人
  	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="MODIFY_USER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
   	private User updateUser;
    
    //修改时间
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

	@Transient
	private String opinion;//审核意见
	
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

	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getFailEbuyMethod() {
		return failEbuyMethod;
	}

	public void setFailEbuyMethod(String failEbuyMethod) {
		this.failEbuyMethod = failEbuyMethod;
	}

	public String getAgentyId() {
		return agentyId;
	}

	public void setAgentyId(String agentyId) {
		this.agentyId = agentyId;
	}

	public String getAgentyName() {
		return agentyName;
	}

	public void setAgentyName(String agentyName) {
		this.agentyName = agentyName;
	}

	public String getFailKind() {
		return failKind;
	}

	public void setFailKind(String failKind) {
		this.failKind = failKind;
	}

	public String getFailPeasontype() {
		return failPeasontype;
	}

	public void setFailPeasontype(String failPeasontype) {
		this.failPeasontype = failPeasontype;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	public String getAdviceProcway() {
		return adviceProcway;
	}

	public void setAdviceProcway(String adviceProcway) {
		this.adviceProcway = adviceProcway;
	}

	public String getAdviceDesc() {
		return adviceDesc;
	}

	public void setAdviceDesc(String adviceDesc) {
		this.adviceDesc = adviceDesc;
	}

	public String getFormerEbuyMethod() {
		return formerEbuyMethod;
	}

	public void setFormerEbuyMethod(String formerEbuyMethod) {
		this.formerEbuyMethod = formerEbuyMethod;
	}

	public String getNewEbuyMethod() {
		return newEbuyMethod;
	}

	public void setNewEbuyMethod(String newEbuyMethod) {
		this.newEbuyMethod = newEbuyMethod;
	}

	public String getNewEvaluationMethod() {
		return newEvaluationMethod;
	}

	public void setNewEvaluationMethod(String newEvaluationMethod) {
		this.newEvaluationMethod = newEvaluationMethod;
	}

	public String getProposerType() {
		return proposerType;
	}

	public void setProposerType(String proposerType) {
		this.proposerType = proposerType;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getReCreateProj() {
		return reCreateProj;
	}

	public void setReCreateProj(String reCreateProj) {
		this.reCreateProj = reCreateProj;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getAdviceProcwayCN() {
		this.adviceProcwayCN = ProjExceptionApplyEnum.getCN(this.getAdviceProcway());
		return adviceProcwayCN;
	}

	public void setAdviceProcwayCN(String adviceProcwayCN) {
		this.adviceProcwayCN = adviceProcwayCN;
	}

	public String getNewEbuyMethodCN() {
		this.newEbuyMethodCN = EbuyMethodEnum.getEBuyMethodCN(this.getNewEbuyMethod());
		return newEbuyMethodCN;
	}

	public void setNewEbuyMethodCN(String newEbuyMethodCN) {
		this.newEbuyMethodCN = newEbuyMethodCN;
	}

	public String getAuditStatusCN() {
		this.auditStatusCN = AuditStatusEnum.getCN(this.getAuditStatus());
		return auditStatusCN;
	}

	public void setAuditStatusCN(String auditStatusCN) {
		this.auditStatusCN = auditStatusCN;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
}
