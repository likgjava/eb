package com.gpcsoft.bizplatform.base.purcatalog.domain;
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
import com.gpcsoft.srplatform.baseData.domain.District;

/**
 * 采购目录区域中间表
 * @gpcsoft.package packageDir="com.gpcsoft.eps.base.purcatalog"
 * @gpcsoft.page domain="base/purcatalog" project="base/purcatalog"
 * @hibernate.class table="ECP_BASE_CTLOG_DSTRCT"
 * @author Administrator
 * @version 1.0
 * @created 14-一月-2010 10:34:02
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_BASE_CTLOG_DSTRCT")
public class PurCatalogDistrict implements GpcBaseObject{

	/** serialVersionUID */
	private static final long serialVersionUID = -7002837979634758672L;

	/**主键*/
	@Id
	@Column(name = "ECP_BASE_CTLOG_DSTRCT_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/**目录*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CATALOG_ID", updatable = false) 
	@BatchSize(size = 15)
    private PurCatalog purCatalog;
	
	/**行政区域*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="DISTRICT_ID", updatable = false) 
	@BatchSize(size = 15)
    private District district;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public PurCatalog getPurCatalog() {
		return purCatalog;
	}

	public void setPurCatalog(PurCatalog purCatalog) {
		this.purCatalog = purCatalog;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date arg0) {
	}
}