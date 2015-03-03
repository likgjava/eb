package com.gpcsoft.pubservice.application.template.domain;

import java.math.BigDecimal;
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
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.pubservice.application.template.enumeration.DotTemplateEnum;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>范本对象</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   pubservice                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-20 下午03:12:40 by yucy    					                            
  *  <br/>Modified Date:  2010-7-20 下午03:12:40 by yucy                                   
  *	  @since 0.5
  *
  *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.template"
  *  @gpcsoft.page domain="DotTemplate"
  *  @hibernate.class table="EPS_TEMPLATE"
  *  @author Administrator
  *  @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_TEMPLATE")
public class DotTemplate implements GpcBaseObject ,IPropertyCUserTime {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**主键*/
	@Id
	@Column(name = "TEMPLATE_ID",length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/**范本名称*/
	@Column(name = "TEMPLATE_NAME")
	private String templateName;
	
	/**区域id*/
	@Column(name = "DISTRICT_CODE")
	private String districtCode;
	
	/**区域名称*/
	@Column(name = "DISTRICT_NAME")
	private String districtName;
	
	/**所属机构*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="ORG_ID")
	@BatchSize(size = 15)
	private OrgInfo org;

	/**品目编号*/
	@Column(name = "CATEGORY_CODE")
	private String categoryCode;
	
	/**品目名称*/
	@Column(name = "CATEGORY_NAME")
	private String categoryName;
	
	/**范本类型*/
	@Column(name = "TEMPLATE_TYPE")
	private String templateType;
	
	@Transient
	private String templateTypeCN;
	
	/**范本文件*/
	@Column(name = "TEMPLATE_FILE")
	private String templateFile;
	
	/**是否共享*/
	@Column(name = "IS_SHARE")
	private Boolean isShare;
	
	/**范本简要描述*/
	@Column(name = "TEMPLATE_DESC")
	private String templateDesc;
	
	 /**有效状态：[临时:00,有效:01,无效:02]*/
    @Column(name = "USE_STATUS", length=2)
    private String useStatus;
	
	/**创建人*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATE_USER")	 
	@BatchSize(size = 15)
	private User createUser;
	
	/**创建时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	/**下载次数*/
	@Formula("(select nvl(count(d.TEMPLATE_DOWN_ID),0) from EPS_TEMPLATE_DOWN d where d.TEMPLATE_ID = TEMPLATE_ID)")
    private BigDecimal downNum;
	
	/**收藏次数*/
	@Formula("(select nvl(count(f.TEMPLATE_FAVORITE_ID),0) from EPS_TEMPLATE_FAVORITE f where f.TEMPLATE_ID = TEMPLATE_ID)")
	private BigDecimal favoriteNum;
	
	/**使用次数*/
	@Formula("(select nvl(count(u.TEMPLATE_USED_ID),0) from EPS_TEMPLATE_USED u where u.TEMPLATE_ID = TEMPLATE_ID)")
	private BigDecimal usedNum;

	/** @gpcsoft.property title="主键" */
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/** @gpcsoft.property title="范本名称" */
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/** @gpcsoft.property title="区域code" */
	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	/** @gpcsoft.property title="区域名称" */
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	/** @gpcsoft.property title="所属机构" */
	public OrgInfo getOrg() {
		return org;
	}

	public void setOrg(OrgInfo org) {
		this.org = org;
	}

	/** @gpcsoft.property title="品目编号" */
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	/** @gpcsoft.property title="品目名称" */
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/** @gpcsoft.property title="范本类型" */
	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	
	public String getTemplateTypeCN() {
		this.templateTypeCN = DotTemplateEnum.getTemplateTypeCN(this.templateType);
		return templateTypeCN;
	}

	public void setTemplateTypeCN(String templateTypeCN) {
		this.templateTypeCN = templateTypeCN;
	}

	/** @gpcsoft.property title="范本文件" */
	public String getTemplateFile() {
		return templateFile;
	}

	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}

	/** @gpcsoft.property title="是否共享" */
	public Boolean getIsShare() {
		return isShare;
	}

	public void setIsShare(Boolean isShare) {
		this.isShare = isShare;
	}
	
	/** @gpcsoft.property title="简要描述" */
	public String getTemplateDesc() {
		return templateDesc;
	}

	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}

	/** @gpcsoft.property title="使用状态" */
	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	/** @gpcsoft.property title="创建人" */
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	/** @gpcsoft.property title="创建时间" */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** @gpcsoft.property title="下载次数" */
	public BigDecimal getDownNum() {
		return downNum;
	}

	public void setDownNum(BigDecimal downNum) {
		this.downNum = downNum;
	}

	/** @gpcsoft.property title="收藏次数" */
	public BigDecimal getFavoriteNum() {
		return favoriteNum;
	}

	public void setFavoriteNum(BigDecimal favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

	/** @gpcsoft.property title="使用次数" */
	public BigDecimal getUsedNum() {
		return usedNum;
	}

	public void setUsedNum(BigDecimal usedNum) {
		this.usedNum = usedNum;
	}
	
}