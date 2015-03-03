package com.gpcsoft.smallscale.expert.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
import org.hibernate.annotations.Where;

import com.gpcsoft.bizplatform.base.industry.domain.Industry;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.core.model.VerifyObject;
import com.gpcsoft.smallscale.expert.enumeration.ExpertEnum;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.baseData.domain.District;

/** 
  *  Comments: <strong>专家基本信息</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-11-25 下午02:22:13 by likg    					                            
  *  <br/>Modified Date:  2010-11-25 下午02:22:13 by likg                                   
  *  <p>@since 0.5
  *   
  * @gpcsoft.package packageDir="com.gpcsoft.smallscale"
  * @gpcsoft.page domain="expert"
  * @hibernate.class table="EXPERT_INFO"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EXPERT_INFO")
@OrderProperty(property="createTime", flag="false")
public class ExpertInfo implements GpcBaseObject, IPropertyCUserTime, IPropertyUUserTime, VerifyObject 
{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** ID */
	@Id
	@Column(name = "EXPERT_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;

	/** 专家对应系统用户帐号 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.ALL})
    @JoinColumn(name = "USER_ID")
	private User user;

	/** 专家姓名【冗余字段，对应user.emp.name】 */
	@Column(name = "NAME")
	private String name;
	
	/** 是否退休 (0:未退休， 1:已退休)*/
	@Column(name = "IS_RETIRED")
	private Boolean isRetired;
	
	/** 所属行业 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="BELONG_INDUSTRY")
	@BatchSize(size = 15)
	private Industry belongIndustry;

	/** 所属地区 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="DISTRICT_ID")
	@BatchSize(size = 15)
	private District district;
	
	/** 评审区域 */
	@Column(name = "APP_DISTRICT_VALUE")
	private String appDistrictValue;
	
	/** 评审区域名称 */
	@Transient
	private String appDistinctName;
	
	/** 评审品目 */
	@Column(name = "APP_CATEGORY_VALUE")
	private String appCategoryValue;
	
	/** 评审品目名称 */
	@Transient
	private String appCategoryName;

	/** 政治面貌,枚举【01:党员， 02:团员， 03:群众】 */
	@Column(name = "POLITICAL_LANDSCAPE")
	private String politicalLandscape;

	@Transient
	private String politicalLandscapeCN;
	
	/** 国家职业资格等级,枚举【01:国家职业资格一级，02:国家职业资格二级，03:国家职业资格三级，04:国家职业资格四级，05:国家职业资格五级，06:无资格证书】 */
	@Column(name = "PROFESSION_QUALIFICATION_LEVEL")
	private String professionQualificationLevel;
	
	@Transient
	private String professionQualificationLevelCN;

	/** 出生年月 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BIRTHDAY", length = 7)
	private Date birthday;

	/** 照片 */
	@Column(name = "PHOTO_ID")
	private String photo;

	/** 从事特长年限 */
	@Column(name = "SPECIFY_YEAR", length = 2)
	private BigDecimal specifyYear;

	/** 特长描述 */
	@Column(name = "TECHNICAL_EXCELLENCE", length = 2000)
	private String technicalExcellence;

	/** 经验描述 */
	@Column(name = "TENDER_EXPERIENCE", length = 2000)
	private String tenderExperience;

	/** 荣誉证书，多附件 */
	@Column(name = "HONOR_FILE", length = 50)
	private String honorFile;

	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATOR_ID")
	@BatchSize(size = 15)
	private User createUser;

	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 7)
	private Date createTime;

	/** 修改人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="MODIFER_ID")
	@BatchSize(size = 15)
	private User updateUser;

	/** 修改时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_TIME", length = 7)
	private Date updateTime;
	
	/** 生效时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VALID_DATE", length = 7)
	private Date validTime;
	
	/** 是否为咨询员【0:不是 1:是】 */
	@Column(name = "IS_CONSULTANT", length = 1)
	private String isConsultant;
	
	/** 是否为评审员【0:不是 1:是】 */
	@Column(name = "IS_REVIEWERS", length = 1)
	private String isReviewers;
	
	/** ******************专家扩展信息开始*************************/
	/** 专家教育经历 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@JoinColumn(name = "EXPERT_ID")
	@Where(clause="AUDIT_STATUS='02'")
	private Set<Education> educations = new HashSet<Education>();
	
	/** 培训信息 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@JoinColumn(name = "EXPERT_ID")
	@Where(clause="AUDIT_STATUS='02'")
	private Set<Training> trainings = new HashSet<Training>();
	
	/** 任职经历 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@JoinColumn(name = "EXPERT_ID")
	@Where(clause="AUDIT_STATUS='02'")
	private Set<Experience> experiences = new HashSet<Experience>();
	
	/** 职称管理 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
    @OneToMany
	@JoinColumn(name = "EXPERT_ID")
	@Where(clause="AUDIT_STATUS='02'")
	private Set<Certificate> certificates = new HashSet<Certificate>();
	/** ******************专家扩展信息结束*************************/

	/** ******************状态信息开始*************************/
	/** 使用状态[00:临时; 01:有效; 02:无效] */
	@Column(name = "USE_STATUS", length = 2)
	private String useStatus;
	
	@Transient
	private String useStatusCN;
	
	/** 审核状态[00:草稿; 01:待审; 02:通过, 03:不通过] */
	@Column(name = "AUDIT_STATUS", length = 2)
	private String auditStatus;
	
	@Transient
	private String auditStatusCN;
	
	/** 启用状态[1:启用; 2:禁用] */
	@Column(name = "IS_OFF", length = 1)
	private String isOff;
	/** ******************状态信息结束*************************/
	    
	@Transient
	private String isOffCN;
	public String getAppCategoryName() {
		if(this.appCategoryValue != null){
			String temp = this.appCategoryValue.replace("##||##", "&");
			String args[] = temp.split("&");
			if(args != null && args.length > 1){
				this.appCategoryName = args[1]==null?"":args[1];
			}else{
				this.appCategoryName = "";
			}
		}
		return appCategoryName;
	}

	public String getAppCategoryValue() {
		return appCategoryValue;
	}

	public String getAppDistinctName() {
		if(this.appCategoryValue != null){
			String temp = this.appDistrictValue.replace("##||##", "&");
			String args[] = temp.split("&");
			if(args != null && args.length > 1){
				this.appDistinctName = args[1]==null?"":args[1];
			}else{
				this.appDistinctName = "";
			}
		}
		return appDistinctName;
	}

	public String getAppDistrictValue() {
		return appDistrictValue;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public String getAuditStatusCN() {
		this.auditStatusCN = ExpertEnum.getAuditStatusCN(this.auditStatus);
		return this.auditStatusCN;
	}

	public Industry getBelongIndustry() {
		return belongIndustry;
	}

	public Date getBirthday() {
		return birthday;
	}

	public Set<Certificate> getCertificates() {
		return certificates;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public District getDistrict() {
		return district;
	}

	public Set<Education> getEducations() {
		return educations;
	}

	public Set<Experience> getExperiences() {
		return experiences;
	}

	public String getHonorFile() {
		return honorFile;
	}

	public String getIsConsultant() {
		return isConsultant;
	}

	public String getIsOff() {
		return isOff;
	}

	public String getIsOffCN() {
		this.isOffCN = ExpertEnum.getIsOffCN(this.isOff);
		return isOffCN;
	}

	public Boolean getIsRetired() {
		return isRetired;
	}

	public String getIsReviewers() {
		return isReviewers;
	}

	public String getName() {
		return name;
	}

	public String getObjId() {
		return objId;
	}

	public String getOpinion() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPhoto() {
		return photo;
	}

	public String getPoliticalLandscape() {
		return politicalLandscape;
	}

	public String getPoliticalLandscapeCN() {
		this.politicalLandscapeCN = ExpertEnum.getPoliticalLandscapeCN(this.politicalLandscape);
		return politicalLandscapeCN;
	}

	public String getProfessionQualificationLevel() {
		return professionQualificationLevel;
	}

	public String getProfessionQualificationLevelCN() {
		this.professionQualificationLevelCN = ExpertEnum.getProfessionQualificationLevelCN(this.professionQualificationLevel);
		return professionQualificationLevelCN;
	}

	public BigDecimal getSpecifyYear() {
		return specifyYear;
	}

	public String getTechnicalExcellence() {
		return technicalExcellence;
	}

	public String getTenderExperience() {
		return tenderExperience;
	}

	public Set<Training> getTrainings() {
		return trainings;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public User getUser() {
		return user;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public String getUseStatusCN() {
		this.useStatusCN = ExpertEnum.getUseStatusCN(this.useStatus);
		return useStatusCN;
	}

	public Date getValidTime() {
		return validTime;
	}

	public void setAppCategoryName(String appCategoryName) {
		this.appCategoryName = appCategoryName;
	}

	public void setAppCategoryValue(String appCategoryValue) {
		this.appCategoryValue = appCategoryValue;
	}

	public void setAppDistinctName(String appDistinctName) {
		this.appDistinctName = appDistinctName;
	}

	public void setAppDistrictValue(String appDistrictValue) {
		this.appDistrictValue = appDistrictValue;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public void setAuditStatusCN(String auditStatusCN) {
		this.auditStatusCN = auditStatusCN;
	}

	public void setBelongIndustry(Industry belongIndustry) {
		this.belongIndustry = belongIndustry;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setCertificates(Set<Certificate> certificates) {
		this.certificates = certificates;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public void setEducations(Set<Education> educations) {
		this.educations = educations;
	}

	public void setExperiences(Set<Experience> experiences) {
		this.experiences = experiences;
	}

	public void setHonorFile(String honorFile) {
		this.honorFile = honorFile;
	}

	public void setIsConsultant(String isConsultant) {
		this.isConsultant = isConsultant;
	}

	public void setIsOff(String isOff) {
		this.isOff = isOff;
	}

	public void setIsOffCN(String isOffCN) {
		this.isOffCN = isOffCN;
	}

	public void setIsRetired(Boolean isRetired) {
		this.isRetired = isRetired;
	}

	public void setIsReviewers(String isReviewers) {
		this.isReviewers = isReviewers;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setPoliticalLandscape(String politicalLandscape) {
		this.politicalLandscape = politicalLandscape;
	}

	public void setPoliticalLandscapeCN(String politicalLandscapeCN) {
		this.politicalLandscapeCN = politicalLandscapeCN;
	}

	public void setProfessionQualificationLevel(String professionQualificationLevel) {
		this.professionQualificationLevel = professionQualificationLevel;
	}

	public void setProfessionQualificationLevelCN(
			String professionQualificationLevelCN) {
		this.professionQualificationLevelCN = professionQualificationLevelCN;
	}

	public void setSpecifyYear(BigDecimal specifyYear) {
		this.specifyYear = specifyYear;
	}

	public void setTechnicalExcellence(String technicalExcellence) {
		this.technicalExcellence = technicalExcellence;
	}

	public void setTenderExperience(String tenderExperience) {
		this.tenderExperience = tenderExperience;
	}

	public void setTrainings(Set<Training> trainings) {
		this.trainings = trainings;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public void setUseStatusCN(String useStatusCN) {
		this.useStatusCN = useStatusCN;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	public void setVerifyTime(Date paramDate) {
		
	}

	public void setVerifyUser(User paramUser) {
		
	}

	
}
