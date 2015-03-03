package com.gpcsoft.epp.qualifications.domain;

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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>QualificationsApp</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   es                    					          
 *  <br/>Module ID: 预审申请 		
 *  <br/>Create Date：2010-4-14 下午05:20:48 by guom    					                            
 *  <br/>Modified Date:  2010-4-14 下午05:20:48 by guom                                 
 *  @gpcsoft.package packageDir="com.gpcsoft.epp.qualifications"
 *  @gpcsoft.page domain="planform/qualifications" project="es"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="预审申请"  
 *  @since 0.1
 *  @version: 0.1 
 */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "	")
public class QualificationsApp implements GpcBaseObject ,IPropertyCUserTime{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PreAppID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@Column(name = "PreAppNo", length = 50)
	private String preAppNo; // 编号
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(
	name="TenderId")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Project project; // 招标项目
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(
	name="SupplierId")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private OrgInfo supplyer; // 供应商ID
	
	@Column(name = "LinkMan", length = 50)
	private String linkMan; // 供应商的联系人
	
	@Column(name = "LinkPhone", length = 50)
	private String linkPhone; // 供应商的联系人
	
	@Column(name = "Email", length = 50)
	private String email; // 供应商的联系人
	
	@Column(name = "Address", length = 50)
	private String address; // 供应商的联系人
	
	@Column(name = "ZipCode", length = 50)
	private String zipCode; // 供应商的联系人
	
	@Column(name = "AuditStatus", length = 2)
	private String auditStatus;//审核状态00：待审核；01：审核通过；02：审核不通过
	
	@Column(name = "RelStatus", length = 2)
	private String relStatus;// 发布状态 00：未发布；01：已发布；02：撤回
	
	@Transient
	private String relStatusCN;
	
	@Column(name = "RelDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date relDate;// 发布时间  发布状态改为“已发布”时取Sysdate
	
	/** 发布人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="RelUser", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User relUser;
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CreUser", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User createUser;

    //创建时间
    @Column(name = "CreDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Column(name = "UseStatus", length = 2)
	private String useStatus;//使用状态

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/**
	 * @gpcsoft.property title="编号"
	 */
	public String getPreAppNo() {
		return preAppNo;
	}

	public void setPreAppNo(String preAppNo) {
		this.preAppNo = preAppNo;
	}

	/**
	 * @gpcsoft.property title="招标项目"
	 */
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @gpcsoft.property title="供应商"
	 */
	public OrgInfo getSupplyer() {
		return supplyer;
	}

	public void setSupplyer(OrgInfo supplyer) {
		this.supplyer = supplyer;
	}

	/**
	 * @gpcsoft.property title="联系人名称"
	 */
	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	/**
	 * @gpcsoft.property title="联系人电话"
	 */
	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	/**
	 * @gpcsoft.property title="电子邮箱"
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @gpcsoft.property title="联系人地址"
	 */
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @gpcsoft.property title="邮编"
	 */
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @gpcsoft.property title="审核状态"
	 */
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * @gpcsoft.property title="发布状态"
	 */
	public String getRelStatus() {
		return relStatus;
	}

	public void setRelStatus(String relStatus) {
		this.relStatus = relStatus;
	}

	/**
	 * @gpcsoft.property title="发布时间"
	 */
	public Date getRelDate() {
		return relDate;
	}

	public void setRelDate(Date relDate) {
		this.relDate = relDate;
	}

	/**
	 * @gpcsoft.property title="发布人"
	 */
	public User getRelUser() {
		return relUser;
	}

	public void setRelUser(User relUser) {
		this.relUser = relUser;
	}

	/**
	 * @gpcsoft.property title="创建人"
	 */
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	/**
	 * @gpcsoft.property title="创建时间"
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @gpcsoft.property title="使用状态"
	 */
	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public User getUpdateUser() {
		return this.relUser;
	}

	public void setUpdateUser(User relUser) {
		this.relUser = relUser;
	}

	public Date getUpdateTime() {
		return this.relDate;
	}

	public void setUpdateTime(Date relDate) {
		this.relDate = relDate;
	}

	public String getRelStatusCN() {
		relStatusCN = CommonEnum.getPublicStatusCN(this.getRelStatus());
		return relStatusCN;
	}

	public void setRelStatusCN(String relStatusCN) {
		this.relStatusCN = relStatusCN;
	}
}
