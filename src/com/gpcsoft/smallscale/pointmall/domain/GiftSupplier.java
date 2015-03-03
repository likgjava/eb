package com.gpcsoft.smallscale.pointmall.domain;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.smallscale.point.enumeration.SmallscaleEnum;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>礼品供货商管理</strong>            		
 *  
 *  @gpcsoft.package packageDir="com.gpcsoft.smallscale.pointmall"
 *  @gpcsoft.page domain="pointmall" project="smallscale"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="礼品供货商管理"
 */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "SM_GIFT_SUPPLIER")
public class GiftSupplier implements GpcBaseObject, IPropertyCUserTime {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**主键ID */
	@Id
	@Column(name="GIFT_SUPPLIER_ID", length=50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name="paymentableGenerator",strategy="uuid.hex")
	private String objId;
	
	/**供货商Id  */
	@Column(name="SUPPLIER_ID", length=50)
	private String supplierId;
	
	/**供货商名称  */
	@Column(name="SUPPLIER_NAME", length=50)
	private String supplierName;
	
    /**开始时间*/
    @Temporal(TemporalType.DATE)
    @Column(name = "START_TIME")
    private Date startTime;
    
    /**结束时间*/
    @Temporal(TemporalType.DATE)
    @Column(name = "END_TIME")
    private Date endTime;
	
	/**是否启用(1:启用,0：禁用)  */
	@Column(name="IS_USED")
	private Boolean isUsed;
	
	/**是否启用别名  */
    @Transient
    private String isUsedCN;
	
	 /**创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="CREATOR_ID")  
    @BatchSize(size = 15)
    private User createUser;
	
	/**创建时间	 */
	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_TIME")
	private Date createTime;

	/**
	 * gpcsoft.property title="记录号"
	 */
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/**
	 * gpcsoft.property title="供货商记录号"
	 */
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * gpcsoft.property title="供货商名称"
	 */
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * gpcsoft.property title="开始时间"
	 */
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * gpcsoft.property title="结束时间"
	 */
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/** isUsed */
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 * @gpcsoft.property title="是否启用"
	 */
	public Boolean getIsUsed() {
		return isUsed;
	}

	/** isUsedCN */
	public void setIsUsedCN(String isUsedCN) {
		this.isUsedCN = isUsedCN;
	}

	/**
	 * @gpcsoft.property title="是否启用别名"
	 */
	public String getIsUsedCN() {
		isUsedCN = this.isUsed == true?"1":"0";
		this.isUsedCN = SmallscaleEnum.getGiftBooleanCN(isUsedCN);
		return isUsedCN;
	}

	/**
	 * gpcsoft.property title="创建人"
	 */
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	/**
	 * gpcsoft.property title="创建时间"
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
