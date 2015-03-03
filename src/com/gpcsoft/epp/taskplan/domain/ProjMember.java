package com.gpcsoft.epp.taskplan.domain;

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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;

/** 
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   epp                    					          
 *  <br/>Module ID: 建设工程施工招标组织机构人员登记表     		
 *  @gpcsoft.package packageDir="com.gpcsoft.epp.taskplan"
 *  @gpcsoft.page domain="taskplan/Record" project="epp"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="建设工程施工招标组织机构人员登记表"  
 *  @since 0.1
 *  @version: 0.1 
 */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPP_PROJ_MEMBER")
public class ProjMember  extends WorkFlowModel implements GpcBaseObject{

	@Id				 
	@Column(name = "PROJ_MEMBER_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")	
	private String objId;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="REC_FORM_ID")//关联的外键	 
	@BatchSize(size = 1)//批量抓取
	private RecordForm recordForm;//备案表 
	
	@Column(name = "PROJ_MEMBER_NAME")
	private String projMemberName;//人员名称
	
	@Column(name = "PROJ_MEMBER_DUTY")
	private String projMemberDudy;//职称
	
	@Column(name = "PROJ_MEMBER_TYPE")
	private String projMemeberType;//人员类型[00:一般人员;01:项目负责人;02:技术负责人;03:建经负责人;] 
	
	@Transient
	private String projMemeberTypeCn;
	
	@Column(name = "PROJ_MEMBER_ISLEADER")
	private String projMemberIsLeader;//是否负责人 
	
	@Column(name = "PROJ_MEMBER_POSITION")
	private String projMemberPosition;//职务
	
	@Column(name = "PROJ_MEMBER_EDUCATION")
	private String projMemberEducation;//学历 
	
	@Column(name = "PROJ_MEMBER_PRO")
	private String projMemberPro;//专业
	
	@Column(name = "PROJ_MEMBER_LIFE")
	private String projMemberLife;//从事专业年限
	
	@Column(name = "PROJ_MEMBER_RESULTS")
	private String projMemberResults;//主要业绩 
	
	@Column(name = "PROJ_MEMBER_REMARK")
	private String projMemberRemark;//备注 
	
	@Column(name = "PROJ_MEMBER_COMPANY")
	private String projMemberCompany;//工作单位

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public RecordForm getRecordForm() {
		return recordForm;
	}

	public void setRecordForm(RecordForm recordForm) {
		this.recordForm = recordForm;
	}

	/**
	 * @gpcsoft.property title="姓名"
	 * @gpcsoft.validate class="required"
	 */
	public String getProjMemberName() {
		return projMemberName;
	}

	public void setProjMemberName(String projMemberName) {
		this.projMemberName = projMemberName;
	}

	/**
	 * @gpcsoft.property title="职称"
	 * @gpcsoft.validate class="required"
	 */
	public String getProjMemberDudy() {
		return projMemberDudy;
	}

	public void setProjMemberDudy(String projMemberDudy) {
		this.projMemberDudy = projMemberDudy;
	}

	/**
	 * @gpcsoft.property title="人员类型"
	 * @gpcsoft.validate class="required"
	 */
	public String getProjMemeberType() {
		return projMemeberType;
	}

	public void setProjMemeberType(String projMemeberType) {
		this.projMemeberType = projMemeberType;
	}

	public String getProjMemberIsLeader() {
		return projMemberIsLeader;
	}

	public void setProjMemberIsLeader(String projMemberIsLeader) {
		this.projMemberIsLeader = projMemberIsLeader;
	}

	/**
	 * @gpcsoft.property title="职务"
	 * @gpcsoft.validate class="required"
	 */
	public String getProjMemberPosition() {
		return projMemberPosition;
	}

	public void setProjMemberPosition(String projMemberPosition) {
		this.projMemberPosition = projMemberPosition;
	}

	/**
	 * @gpcsoft.property title="学历"
	 * @gpcsoft.validate class="required"
	 */
	public String getProjMemberEducation() {
		return projMemberEducation;
	}

	public void setProjMemberEducation(String projMemberEducation) {
		this.projMemberEducation = projMemberEducation;
	}

	/**
	 * @gpcsoft.property title="专业"
	 * @gpcsoft.validate class="required"
	 */
	public String getProjMemberPro() {
		return projMemberPro;
	}

	public void setProjMemberPro(String projMemberPro) {
		this.projMemberPro = projMemberPro;
	}

	/**
	 * @gpcsoft.property title="从事专业年限"
	 * @gpcsoft.validate class="required"
	 */
	public String getProjMemberLife() {
		return projMemberLife;
	}

	public void setProjMemberLife(String projMemberLife) {
		this.projMemberLife = projMemberLife;
	}

	/**
	 * @gpcsoft.property title="主要业绩"
	 * @gpcsoft.validate class="required"
	 */
	public String getProjMemberResults() {
		return projMemberResults;
	}

	public void setProjMemberResults(String projMemberResults) {
		this.projMemberResults = projMemberResults;
	}

	/**
	 * @gpcsoft.property title="备注"
	 * @gpcsoft.validate class="required"
	 */
	public String getProjMemberRemark() {
		return projMemberRemark;
	}

	public void setProjMemberRemark(String projMemberRemark) {
		this.projMemberRemark = projMemberRemark;
	}

	/**
	 * @gpcsoft.property title="工作单位"
	 * @gpcsoft.validate class="required"
	 */
	public String getProjMemberCompany() {
		return projMemberCompany;
	}

	public void setProjMemberCompany(String projMemberCompany) {
		this.projMemberCompany = projMemberCompany;
	}

	public String getProjMemeberTypeCn() {
		this.projMemeberTypeCn = ProjMemberEnum.getProjMemeberTypeCn(this.getProjMemeberType());
		return this.projMemeberTypeCn;
	}

	public void setProjMemeberTypeCn(String projMemeberTypeCn) {
		this.projMemeberTypeCn = projMemeberTypeCn;
	}
}
