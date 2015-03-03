package com.gpcsoft.bizplatform.base.purcatalog.domain;
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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.srplatform.workPlan.domain.PlanTaskEnum;

/**
 * 采购方式
 * @gpcsoft.package packageDir="com.gpcsoft.bizplatform.base.purcatalog.domain"
 * @gpcsoft.page domain="com.gpcsoft.bizplatform.base.purcatalog.domain"
 * @hibernate.class table="ECP_BASE_CATALOG_PROCTYPE"
 * @author Administrator
 * @version 1.0
 * @created 14-一月-2010 10:34:02
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_BASE_CATALOG_PROCTYPE")
public class PurCatalogProcType implements GpcBaseObject {

	/** serialVersionUID */
	private static final long serialVersionUID = -8098033576938246887L;

	/**主键*/
	@Id
	@Column(name = "CATALOG_PROCTYPE_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
    
    /**采购目录 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CATALOG_ID", updatable = false) 
	@BatchSize(size = 15)
    private PurCatalog purCatalog;
	
	/**采购品目*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CATEGORY_ID", updatable = false) 
	@BatchSize(size = 15)
    private PurCategory purCategory;
	
	/**采购方式*/
    @Column(name = "PROCTYPE")
	private String procType;
    
    @Transient
    private String procTypeCN;
    
    /**金额*/
    @Column(name = "PROC_TOTAL")
    private BigDecimal procTotal;

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
	public PurCategory getPurCategory() {
		return purCategory;
	}
	public void setPurCategory(PurCategory purCategory) {
		this.purCategory = purCategory;
	}
	
	public String getProcType() {
		return procType;
	}
	public void setProcType(String procType) {
		this.procType = procType;
	}
	
	public String getProcTypeCN() {
		this.procTypeCN = PlanTaskEnum.getBuyMethodCN(this.getProcType());
		return this.procTypeCN;
	}
	
	public BigDecimal getProcTotal() {
		return procTotal;
	}
	public void setProcTotal(BigDecimal procTotal) {
		this.procTotal = procTotal;
	}
	public Date getCreateTime() {
		return null;
	}
	public void setCreateTime(Date date) {
	}
}