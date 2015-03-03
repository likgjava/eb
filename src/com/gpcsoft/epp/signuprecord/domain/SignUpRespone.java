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
import com.gpcsoft.srplatform.auth.domain.Attachment;

/**
 * @author liuke
 * @since 0.5
 * @version: 0.6
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.signuprecord"
 * @gpcsoft.page domain="planform/signuprecord" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="报名响应"
 */



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_SIGNUPRESPONE")
@SuppressWarnings("serial")
public class SignUpRespone implements GpcBaseObject{
	@Id
	@Column(name = "SIGNUP_FAC_RESPON_ID", nullable=false, length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@ManyToOne(fetch=FetchType.EAGER)
	@BatchSize(size = 20)
	@JoinColumn(name="SIGNUP_FAC_ID")
	private SignUpCondFactor signUpCondFactor; //报名指标
	
	
	//投标文件
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name="ATTACH_REAL_ID")//关联的外键	 
	@BatchSize(size = 15)
	private Attachment signUpResponeFile;//报名响应文件
	


	@Column(name = "RESPONSEVALUE", length = 500)
	private String responseValue; //报名响应
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public SignUpCondFactor getSignUpCondFactor() {
		return signUpCondFactor;
	}

	public void setSignUpCondFactor(SignUpCondFactor signUpCondFactor) {
		this.signUpCondFactor = signUpCondFactor;
	}

	/**
	 * @gpcsoft.property title="报名响应"
	 */
	public String getResponseValue() {
		return responseValue;
	}

	public void setResponseValue(String responseValue) {
		this.responseValue = responseValue;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCreateTime(Date arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public Attachment getSignUpResponeFile() {
		return signUpResponeFile;
	}

	public void setSignUpResponeFile(Attachment signUpResponeFile) {
		this.signUpResponeFile = signUpResponeFile;
	}
	
	
}
