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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.model.GpcBaseObject;

/**
 * 采购目录明细
 * @gpcsoft.package packageDir="com.gpcsoft.bizplatform.base.purcatalog.domain"
 * @gpcsoft.page domain="base/purcatalog" project="base/purcatalog"
 * @hibernate.class table="ECP_BASE_CATALOG_DETAIL"
 * @author Administrator
 * @version 1.0
 * @created 14-一月-2010 10:34:02
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_BASE_CATALOG_DETAIL")
public class PurCatalogDetail implements GpcBaseObject {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -8225205648943674315L;

	/**主键*/
	@Id
	@Column(name = "CATALOG_DETAIL_ID", length = 36)
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
	
	/**单价*/
    @Column(name = "GOODSPRICE")
	private BigDecimal goodsPrice;
    
    /**年批量采购金额*/
    @Column(name = "YEAR_PROC_TOTAL")
    private BigDecimal yearTotal;

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

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public BigDecimal getYearTotal() {
		return yearTotal;
	}

	public void setYearTotal(BigDecimal yearTotal) {
		this.yearTotal = yearTotal;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date date) {
	}
	


}