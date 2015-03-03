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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.agreement.bargin.project.enumeration.ProjectExtEnum;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.epp.project.domain.Project;
/**
 * 项目扩展信息
 * @gpcsoft.package packageDir="com.gpcsoft.agreement.bargin.project"
 * @gpcsoft.page domain="project"
 * @hibernate.class table="ECP_PROJECT_EXT_INFO"
 * @author sunl
 * @version 1.0
 * @created 12-四月-2010 9:32:16
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)

@Table(name = "ECP_PROJECT_PAY_INFO")
public class ProjectPayInfo implements GpcBaseObject{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -5599663495033371073L;

	@Id
	@Column(name = "ECP_PROJECT_EXT_PAY_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/**项目*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="PROJECT_ID")	 
	@BatchSize(size = 15)
	private Project project;

	/**交货时间*/
	@Column(name = "DELIVERY_DATE")
	private String deliveryDate;
	
	/**交货地点*/
	@Column(name = "DELIVERY_ADDRESS")
	private String deliveryAddress;
	
	/**交货方式*/
	@Column(name = "DELIVERY_TYPE")
	private String deliveryType;
	@Transient
	private String deliveryTypeCN;
	
	/**付款方式*/
	@Column(name = "PAY_TYPE")
	private String payType;
	@Transient
	private String payTypeCN;
	
	/**补充说明*/
	@Column(name = "SUPPLEMENT")
	private String supplement;

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

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getPayTypeCN() {
		this.payTypeCN = ProjectExtEnum.getPayTypeCN(this.payType);
		return payTypeCN;
	}

	public void setPayTypeCN(String payTypeCN) {
		this.payTypeCN = payTypeCN;
	}

	public String getDeliveryTypeCN() {
		this.deliveryTypeCN = ProjectExtEnum.getDeliveryCN(this.deliveryType);
		return deliveryTypeCN;
	}

	public void setDeliveryTypeCN(String deliveryTypeCN) {
		this.deliveryTypeCN = deliveryTypeCN;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getSupplement() {
		return supplement;
	}

	public void setSupplement(String supplement) {
		this.supplement = supplement;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date arg0) {
	}
	
}
