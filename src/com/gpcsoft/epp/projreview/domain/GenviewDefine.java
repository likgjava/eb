
package com.gpcsoft.epp.projreview.domain;

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

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.epp.eval.domain.CongruousFactor;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;

/** 
 * @Description: 开标一览表规则
 * @version V1.0
 * @Create Date 2010-7-12 下午05:06:43 By liuke 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.projreview"
 * @gpcsoft.page domain="planform/projreview" project="es"
 * @gpcsoft.domain
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_GEN_VIEW_DEFINE")
public class GenviewDefine extends WorkFlowModel implements GpcBaseObject{

	@Id
	@Column(name="GEN_VIEW_DEFINE_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="TenderID")
	private Project project;						//关联包组				

	@Column(name="FACTOR_ID")
	private String  factorId;						//关联指标			

	@Column(name="FACTOR_NAME")
	private String factorName;                      //指标名称		
	
	@Column(name = "SHOW_NO")
	private BigDecimal showNo;						//展示顺序
	
	@Column(name = "REDUNDANCY1")
	private String redundancy1;						//指标类型(01：投标价 02：保证金 03：投标声明 04：工期)
	
	@Column(name = "REDUNDANCY2")
	private String redundancy2;						//是否选中（01：选中）
	
	@Column(name = "REDUNDANCY3")
	private String redundancy3;						//默认值
	
	//modify by liuke
	/**当前有效id*/
	@Column(name = "CURRENT_VALID_ID")
	private String currentId;
	/********************************getters and setters**********************************/
	
	
	
	public String getCurrentId() {
		return currentId;
	}

	public String getRedundancy1() {
		return redundancy1;
	}

	public void setRedundancy1(String redundancy1) {
		this.redundancy1 = redundancy1;
	}

	public String getRedundancy2() {
		return redundancy2;
	}

	public void setRedundancy2(String redundancy2) {
		this.redundancy2 = redundancy2;
	}

	public String getRedundancy3() {
		return redundancy3;
	}

	public void setRedundancy3(String redundancy3) {
		this.redundancy3 = redundancy3;
	}

	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}
	
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

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	public BigDecimal getShowNo() {
		return showNo;
	}

	public void setShowNo(BigDecimal showNo) {
		this.showNo = showNo;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#setCreateTime(java.util.Date)
	 */
	public void setCreateTime(Date date) {
	}
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#getCreateTime()
	 */
	public Date getCreateTime() {
		return null;
	}
	
}
