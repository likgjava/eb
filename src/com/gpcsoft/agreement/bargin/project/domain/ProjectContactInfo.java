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
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.epp.project.domain.Project;
/**
 * 项目联系人信息(扩展)
 * @gpcsoft.package packageDir="com.gpcsoft.agreement.bargin.project"
 * @gpcsoft.page domain="project"
 * @hibernate.class table="ECP_PROJECT_CONTACT_INFO"
 * @author sunl
 * @version 1.0
 * @created 12-四月-2010 9:32:16
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)

@Table(name = "ECP_PROJECT_CONTACT_INFO")
public class ProjectContactInfo implements GpcBaseObject{

	/** serialVersionUID */
	private static final long serialVersionUID = 6370453599597599446L;

	@Id
	@Column(name = "ECP_PROJECT_CONTACT_INFO_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/**项目*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="PROJECT_ID")	 
	@BatchSize(size = 15)
	private Project project;

	/**联系人*/
	@Column(name = "LINKER")
	private String linker;
	
	/**移动电话*/
	@Column(name = "MOBILE_PHONE")
	private String mobilePhone;
	
	/**固定电话*/
	@Column(name = "FIXED_TELEPHONE")
	private String fixedTelephone;
	
	/**传真*/
	@Column(name = "FAX")
	private String fax;
	
	/**地址*/
	@Column(name = "ADDRESS")
	private String address;
	
	/**邮编*/
	@Column(name = "POST_CODE")
	private String postCode;

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

	public String getLinker() {
		return linker;
	}

	public void setLinker(String linker) {
		this.linker = linker;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getFixedTelephone() {
		return fixedTelephone;
	}

	public void setFixedTelephone(String fixedTelephone) {
		this.fixedTelephone = fixedTelephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date arg0) {
	}

	
}
