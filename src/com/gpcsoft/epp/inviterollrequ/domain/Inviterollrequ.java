package com.gpcsoft.epp.inviterollrequ.domain;

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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;

/**                                
 *  @gpcsoft.package packageDir="com.gpcsoft.epp.inviterollrequ"
 *  @gpcsoft.page domain="planform/inviterollrequ" project="es"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="邀请名单申请单"    
 *  @since 0.4
 *  @version: 0.5
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_INVITEROLLREQU")
@SuppressWarnings("serial")
public class Inviterollrequ extends WorkFlowModel implements GpcBaseObject,IPropertyCUserTime, IPropertyUUserTime{
	@Id
	@Column(name ="INRQ_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;  //供应商确认记录ID
	
	
	/** 关联项目*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="PROJ_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private Project project;
	
	@Column(name = "PROJ_CODE", length = 100)
	private String projCode;//项目编号
	
	@Column(name = "PROJ_NAME", length = 50)
	private String projName;//项目名称
	
	@Column(name = "INRQ_KIND", length = 50)
	private String inrqKind;//邀请函种类
	
	@Transient
	private String inrqDKindCN;
	
	@Column(name = "VERSION")
	private BigDecimal version;//版本号
	
	@Column(name = "RECOM_DESC", length = 500)
	private String recomDesc;//名单推荐说明
	
	@Column(name = "AUDIT_STATUS", length = 2)
	private String auditStatus;//审核状态   00:待提交;01:待审核;02:审核通过;03:审核不通过
	
	@Transient
	private String auditStatusCN;
	
	@Column(name = "SELECT_TYPE", length = 50)
	private String selectType;//供应商选择种类（人工指定、随机抽取、范围抽取、任意<抽取+指定>）
	
	@ManyToOne(fetch=FetchType.EAGER,optional=true)
	@JoinColumn(name="ATTACH_REAL_ID")
	private Attachment attachment;					// 关联附件
	
	@Column(name = "USE_STATUS")
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
	
  //modify by liuke
	/**当前有效id*/
	@Column(name = "CURRENT_VALID_ID")
	private String currentId;


/********************************************************************************/
	public String getCurrentId() {
		return currentId;
	}

	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}
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

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	/**
	 * @gpcsoft.property title="项目编号"
	 */
	public String getProjCode() {
		return projCode;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}
	/**
	 * @gpcsoft.property title="项目名称"
	 */
	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getInrqKind() {
		return inrqKind;
	}

	public void setInrqKind(String inrqKind) {
		this.inrqKind = inrqKind;
	}

	public BigDecimal getVersion() {
		return version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

	public String getRecomDesc() {
		return recomDesc;
	}

	public void setRecomDesc(String recomDesc) {
		this.recomDesc = recomDesc;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
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

	public String getAuditStatusCN() {
		auditStatusCN = InrqAuditStatusEnum.getCN(this.getAuditStatus());
		return auditStatusCN;
	}

	public void setAuditStatusCN(String auditStatusCN) {
		this.auditStatusCN = auditStatusCN;
	}

	public String getInrqDKindCN() {
		inrqDKindCN = InrqKindEnum.getCN(this.getInrqKind());
		return inrqDKindCN;
	}

	public void setInrqDKindCN(String inrqDKindCN) {
		this.inrqDKindCN = inrqDKindCN;
	}
}
