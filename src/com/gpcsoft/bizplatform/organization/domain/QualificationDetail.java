package com.gpcsoft.bizplatform.organization.domain;
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

import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationParam;
import com.gpcsoft.core.model.GpcBaseObject;

/**
 * 资质信息详细
 * @gpcsoft.package packageDir="com.gpcsoft.bizplatform.organization.domain"
 * @gpcsoft.page domain="QualificationDetail"
 * @hibernate.class table="ORG_QUALIFICATION_DETAIL"
 * @author liqy
 * @version 1.0
 * @created 25-五月-2010 15:58:26
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ORG_QUALIFICATION_DETAIL")
public class QualificationDetail implements GpcBaseObject {

    /** serialVersionUID */
	private static final long serialVersionUID = -6968650004371384451L;

	@Id
    @Column(name = "QUALIFICATION_DETAIL_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
    
	/**资质主对象*/
    @ManyToOne(fetch=FetchType.LAZY , optional=true)
	@JoinColumn(name="QUALIFICATION_ID")
	@BatchSize(size = 15)
	private OrgQuality orgQuality;
    
	/**参数值*/
    @Column(name = "PARAM_VALUE", length = 100)
	private String paramValue;
    
	/**资质参数*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="QUALITY_PARAM_ID")  
	@BatchSize(size = 15)
	private QualificationParam qualityParam;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public OrgQuality getOrgQuality() {
		return orgQuality;
	}

	public void setOrgQuality(OrgQuality orgQuality) {
		this.orgQuality = orgQuality;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public QualificationParam getQualityParam() {
		return qualityParam;
	}

	public void setQualityParam(QualificationParam qualityParam) {
		this.qualityParam = qualityParam;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date arg0) {
		
	}

}