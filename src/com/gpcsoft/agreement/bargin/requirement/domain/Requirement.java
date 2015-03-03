package com.gpcsoft.agreement.bargin.requirement.domain;
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
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.agreement.bargin.requirement.enumeration.RequirementEnum;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>采购需求</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-27 下午12:45:45 by yucy    					                            
  *  <br/>Modified Date:  2010-7-27 下午12:45:45 by yucy                                   
  *	 <p>@since 0.5
  *   @version: 0.5 
  *   
  * @gpcsoft.package packageDir="com.gpcsoft.bizplatform.requirement"
  * @gpcsoft.page domain="Requirement"
  * @hibernate.class table="EPS_PURCHASE_REQUIREMENT"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_PURCHASE_REQUIREMENT")
public class Requirement  implements GpcBaseObject ,IPropertyCUserTime,IPropertyUUserTime{
    
	/** serialVersionUID */
	private static final long serialVersionUID = -6569433862038158037L;

	/**记录号*/
    @Id
    @Column(name = "PURCHASE_REQUIRE_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /** 发布机构 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="ORG_INFO_ID") 
    @BatchSize(size = 15)
    private OrgInfo pubOrg;
    
    /** 采购品目 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CATEGORY_ID") 
    @BatchSize(size = 15)
    private PurCategory category;
    
    /** 标题*/
    @Column(name = "PURCHASE_TITLE")
    private String title;
    
    /** 采购数量*/
    @Column(name = "PURCHASE_NUMBER")
    private BigDecimal purchaseQty;
    
    /** 采购预算*/
    @Column(name = "PURCHASE_BUDGET")
    private BigDecimal purchaseBudget;

    /** 供货区域Id*/
    @Column(name = "DISTRICT_ID")
    private String districtId;
    
    /** 供货区域名称*/
    @Column(name = "DISTRICT_NAMES")
    private String districtNames;
    
    /** 具体需求描述*/
    @Column(name = "DESCRIPTION")
    private String discription;
    
    /** 相关图片*/
    @Column(name = "PICTURE")
    private String picture;
    
    /**结束时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ENDTIME")
    private Date endTime;
    
    /** 电子邮件*/
    @Column(name = "EMAIL")
    private String email;
    
    /** 联系电话*/
    @Column(name = "LINKTEL")
    private String linkTel;
    
    /** 联系人*/
    @Column(name = "LINKMEN")
    private String linkMen;
    
    /** 审批状态：00.草稿（默认） 01.待审核 02.通过 03.不通过*/
    @Column(name = "AUDIT_STATUS")
    private String auditStatus;
    
    @Transient
    private String auditStatusCN;
    
    /** 审核人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="AUDIT_USER") 
    @BatchSize(size = 15)
    private User auditUser;
    
    /** 审核时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AUDIT_TIME")
    private Date auditTime;
    
    /** 发布状态00 未发布 01 已发布*/
    @Column(name = "PUBLISH_STATUS")
    private String pubStatus;
    
    /** 发布时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PUBLISH_TIME")
    private Date pubTime;
    
    /** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATE_USER") 
    @BatchSize(size = 15)
    private User createUser;
    
    /** 创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    /** 修改人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="MODIFY_USER") 
    @BatchSize(size = 15)
    private User updateUser;
    
    /** 修改时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_TIME")
    private Date updateTime;
    
    /** 项目ID*/
    @Column(name = "PROJECT_ID")
    private String projectId;
    
    /** 是否匿名*/
    @Column(name = "IS_ANONYMOUS")
    private Boolean isAnonymous;

    /** 待审核的报名信息*/
	@Formula("( select nvl(count(t.eps_purchase_req_reg_id), 0 ) from eps_purchase_requirement_reg t where  t.audit_status = '01' and t.purchase_require_id = PURCHASE_REQUIRE_ID  )")
	private String toAuditCount;
	
    /** 已审核的报名信息*/
	@Formula("( select nvl(count(t.eps_purchase_req_reg_id), 0 ) from eps_purchase_requirement_reg t where  t.audit_status = '02' and t.purchase_require_id = PURCHASE_REQUIRE_ID  )")
	private String auditedCount;
    
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public OrgInfo getPubOrg() {
		return pubOrg;
	}

	public void setPubOrg(OrgInfo pubOrg) {
		this.pubOrg = pubOrg;
	}

	public PurCategory getCategory() {
		return category;
	}

	public void setCategory(PurCategory category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPurchaseQty() {
		return purchaseQty;
	}

	public void setPurchaseQty(BigDecimal purchaseQty) {
		this.purchaseQty = purchaseQty;
	}

	public BigDecimal getPurchaseBudget() {
		return purchaseBudget;
	}

	public void setPurchaseBudget(BigDecimal purchaseBudget) {
		this.purchaseBudget = purchaseBudget;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictNames() {
		return districtNames;
	}

	public void setDistrictNames(String districtNames) {
		this.districtNames = districtNames;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLinkTel() {
		return linkTel;
	}

	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}

	public String getLinkMen() {
		return linkMen;
	}

	public void setLinkMen(String linkMen) {
		this.linkMen = linkMen;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditStatusCN() {
		this.auditStatusCN = RequirementEnum.getAuditStatusCN(this.auditStatus);
		return this.auditStatusCN;
	}

	public void setAuditStatusCN(String auditStatusCN) {
		this.auditStatusCN = auditStatusCN;
	}
	
	public User getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(User auditUser) {
		this.auditUser = auditUser;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getPubStatus() {
		return pubStatus;
	}

	public void setPubStatus(String pubStatus) {
		this.pubStatus = pubStatus;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
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

	public String getToAuditCount() {
		return toAuditCount;
	}

	public void setToAuditCount(String toAuditCount) {
		this.toAuditCount = toAuditCount;
	}

	public String getAuditedCount() {
		return auditedCount;
	}

	public void setAuditedCount(String auditedCount) {
		this.auditedCount = auditedCount;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Boolean getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(Boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}
}