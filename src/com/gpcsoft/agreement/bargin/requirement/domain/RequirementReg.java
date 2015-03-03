package com.gpcsoft.agreement.bargin.requirement.domain;
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

import com.gpcsoft.agreement.bargin.requirement.enumeration.RequirementEnum;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>采购需求报名</strong>            		
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
  * @gpcsoft.page domain="RequirementReg"
  * @hibernate.class table="EPS_PURCHASE_REQUIREMENT_REG"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_PURCHASE_REQUIREMENT_REG")
public class RequirementReg  implements GpcBaseObject ,IPropertyCUserTime{
    
	/** serialVersionUID */
	private static final long serialVersionUID = -1329564438831908029L;

	/**记录号*/
    @Id
    @Column(name = "EPS_PURCHASE_REQ_REG_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /** 报名机构 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="ORG_INFO_ID") 
    @BatchSize(size = 15)
    private OrgInfo regOrg;
    
    /** 采购需求 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="PURCHASE_REQUIRE_ID") 
    @BatchSize(size = 15)
    private Requirement requirement;
    
    /** 报名附件*/
    @Column(name = "REGIST_FILE")
    private String file;
    
    /** 联系人*/
    @Column(name = "LINKMEN")
    private String linkMen;
    
    /** 联系电话*/
    @Column(name = "LINKTEL")
    private String linkTel;

    /** 电子邮件*/
    @Column(name = "EMAIL")
    private String email;
    
    /** 传真*/
    @Column(name = "FAX")
    private String fax;
    
    /** 是否符合采购人需求*/
    @Column(name = "IS_QUALIFIED")
    private Boolean isQualified;
    
    /** 是否推荐供应商*/
    @Column(name = "IS_RECOMMEND")
    private Boolean isRecommend;
    
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
    
    /** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATE_USER") 
    @BatchSize(size = 15)
    private User createUser;
    
    /** 创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    /** 身份证 */
    @Column(name = "IDCARD")
    private String idCard;
    
    /** 地址 */
    @Column(name = "ADDRESS")
    private String address;

    /** 邮编 */
    @Column(name = "ZIPCODE")
    private String zipCode;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public OrgInfo getRegOrg() {
		return regOrg;
	}

	public void setRegOrg(OrgInfo regOrg) {
		this.regOrg = regOrg;
	}

	public Requirement getRequirement() {
		return requirement;
	}

	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Boolean getIsQualified() {
		return isQualified;
	}

	public void setIsQualified(Boolean isQualified) {
		this.isQualified = isQualified;
	}

	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
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

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}