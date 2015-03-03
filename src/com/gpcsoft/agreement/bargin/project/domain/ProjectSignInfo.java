package com.gpcsoft.agreement.bargin.project.domain;

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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.srplatform.auth.domain.Attachment;
/**
 * 项目报名信息(扩展)
 * @gpcsoft.package packageDir="com.gpcsoft.agreement.bargin.project"
 * @gpcsoft.page domain="project"
 * @hibernate.class table="ECP_PROJECT_SIGN_INFO"
 * @author sunl
 * @version 1.0
 * @created 12-四月-2010 9:32:16
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)

@Table(name = "ECP_PROJECT_SIGN_INFO")
public class ProjectSignInfo implements GpcBaseObject{

	/** serialVersionUID */
	private static final long serialVersionUID = 5386333560427463156L;

	@Id
	@Column(name = "ECP_PROJECT_SIGN_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/**项目*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="PROJECT_ID")	 
	@BatchSize(size = 15)
	private Project project;

	/**企业资质*/
	@Column(name = "COMPANY_QUALIFICATION")
	private String companyQualification;
	
	/**企业产能*/
	@Column(name = "COMPANY_PRODUCTIVITY")
	private String companyProductivity;
	
	/**质量认证*/
	@Column(name = "QUALITY_STANDARD")
	private String qualityStandard;
	
	/**附加语*/
	@Column(name = "ADDITIONAL_MEMO")
	private String additionalMemo;
	
	/**注册资金*/
	@Column(name = "REGISTERED_CAPITAL")
	private String registeredCapital;
	
	/**附件*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="ASSESSMENT_FILE")	 
    @Cascade({CascadeType.DELETE_ORPHAN })
	@BatchSize(size = 15)
	private Attachment assessmentFile;

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

	public String getCompanyQualification() {
		return companyQualification;
	}

	public void setCompanyQualification(String companyQualification) {
		this.companyQualification = companyQualification;
	}

	public String getCompanyProductivity() {
		return companyProductivity;
	}

	public void setCompanyProductivity(String companyProductivity) {
		this.companyProductivity = companyProductivity;
	}

	public String getQualityStandard() {
		return qualityStandard;
	}

	public void setQualityStandard(String qualityStandard) {
		this.qualityStandard = qualityStandard;
	}

	public String getAdditionalMemo() {
		return additionalMemo;
	}

	public void setAdditionalMemo(String additionalMemo) {
		this.additionalMemo = additionalMemo;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date arg0) {
	}

	public Attachment getAssessmentFile() {
		return assessmentFile;
	}

	public void setAssessmentFile(Attachment assessmentFile) {
		this.assessmentFile = assessmentFile;
	}
}
