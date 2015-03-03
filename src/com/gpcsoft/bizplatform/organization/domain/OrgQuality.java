package com.gpcsoft.bizplatform.organization.domain;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationClass;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationDefine;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.VerifyObject;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * 资质信息
 * @gpcsoft.package packageDir="com.gpcsoft.bizplatform.organization.domain"
 * @gpcsoft.page domain="OrgQuality"
 * @hibernate.class table="ORG_QUALIFICATION" dynamic-insert="true" dynamic- update="true"
 * @author liqy
 * @version 1.0
 * @created 25-五月-2010 15:49:22
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ORG_QUALIFICATION")
public class OrgQuality implements GpcBaseObject,IPropertyCUserTime ,IPropertyUUserTime,VerifyObject{

	private static final long serialVersionUID = -2294768478839738442L;
	
    /** 主键 */
    @Id
    @Column(name = "QUALIFICATION_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")    
	private String objId;
    
	/**所属对象*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="BELONG_OBJECT_ID") 
	@BatchSize(size = 15)
	private OrgInfo org;
    
	/**审核状态*/
	@Column(name = "STATUS", length = 100)
	private String auditStatus;
	
	@Transient
	private String auditStatusCN;
	
	/**使用状态*/
	@Column(name = "USE_STATUS", length = 100)
	private String useStatus;
	
	/**资质类型*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="QUALITY_CLASS_ID") 
	@BatchSize(size = 15)
	private QualificationClass qualificationClass;
	
	/**资质定义*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="QUALITY_DEFINE_ID") 
	@BatchSize(size = 15)
	private QualificationDefine qualificationDefine;
	
	/**资质文件*/
    @Column(name = "QUALIFICATION_FILE")
	private String qualificationFile;
    
    /**资质详细信息*/
	@LazyCollection(value=LazyCollectionOption.EXTRA)
    @OneToMany
    @OrderBy("qualityParam")
	@Cascade({CascadeType.SAVE_UPDATE,CascadeType.DELETE_ORPHAN } )
	@JoinColumn(name = "QUALIFICATION_ID") 
    private Set<QualificationDetail> qualificationDetailSet = new HashSet<QualificationDetail>();
	
	/**创建时间*/
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TIME")
	private Date createTime;
    
	/**创建人*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR")  
    @BatchSize(size = 15)
	private User createUser;
    
	/**修改人*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="MODIFIER")  
    @BatchSize(size = 15)
	private User updateUser;
    
	/**修改时间*/
    @Temporal(TemporalType.DATE)
    @Column(name = "MODIFY_TIME")
	private Date updateTime;
    
    /**审核人*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="VERIFIER_ID") 
	@BatchSize(size = 15)
	private User verifyUser;
	
    /**审核时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VERIFY_TIME")
    private Date verifyTime;
    
    /**意见*/
    @Column(name = "OPINION")
    private String opinion;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public OrgInfo getOrg() {
		return org;
	}

	public void setOrg(OrgInfo org) {
		this.org = org;
	}

	public String getAuditStatus() {
		return this.auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public QualificationClass getQualificationClass() {
		return qualificationClass;
	}

	public void setQualificationClass(QualificationClass qualificationClass) {
		this.qualificationClass = qualificationClass;
	}
	
	public QualificationDefine getQualificationDefine() {
		return qualificationDefine;
	}

	public void setQualificationDefine(QualificationDefine qualificationDefine) {
		this.qualificationDefine = qualificationDefine;
	}

	public String getQualificationFile() {
		return qualificationFile;
	}

	public void setQualificationFile(String qualificationFile) {
		this.qualificationFile = qualificationFile;
	}

	public Set<QualificationDetail> getQualificationDetailSet() {
		return qualificationDetailSet;
	}

	public void setQualificationDetailSet(
			Set<QualificationDetail> qualificationDetailSet) {
		this.qualificationDetailSet = qualificationDetailSet;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
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
		this.auditStatusCN = OrganizationEnum.getAuditStatusCN(this.auditStatus);
		return this.auditStatusCN;
	}
	
	public void setAuditStatusCN(String auditStatusCN){
		this.auditStatusCN = auditStatusCN;
	}

	public User getVerifyUser() {
		return verifyUser;
	}

	public void setVerifyUser(User verifyUser) {
		this.verifyUser = verifyUser;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
}