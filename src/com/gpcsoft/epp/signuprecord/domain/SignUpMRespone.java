package com.gpcsoft.epp.signuprecord.domain;

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
 * @author liuke
 * @since 0.5
 * @version: 0.6
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.signuprecord"
 * @gpcsoft.page domain="planform/signuprecord" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="报名指标"
 */



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_SIGNUP_M_RESPON")
@SuppressWarnings("serial")
public class SignUpMRespone implements GpcBaseObject{
	
	@Id
	@Column(name = "SIGNUP_M_RESPON_ID", nullable=false, length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@ManyToOne(fetch=FetchType.EAGER)
	@BatchSize(size = 20)
	@JoinColumn(name="SIGNUP_FAC_RESPON_ID")
	private SignUpRespone signUpRespone; //报名响应
	
	@ManyToOne(fetch=FetchType.EAGER)
	@BatchSize(size = 20)
	@JoinColumn(name="APPLYID")
	private SignUprecord signUprecord; //供应商报名记录
	
	@ManyToOne(fetch=FetchType.EAGER)
	@BatchSize(size = 20)
	@JoinColumn(name="SUBTENDERID")
	private Project project; //包组
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public SignUpRespone getSignUpRespone() {
		return signUpRespone;
	}

	public void setSignUpRespone(SignUpRespone signUpRespone) {
		this.signUpRespone = signUpRespone;
	}

	public SignUprecord getSignUprecord() {
		return signUprecord;
	}

	public void setSignUprecord(SignUprecord signUprecord) {
		this.signUprecord = signUprecord;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCreateTime(Date arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
