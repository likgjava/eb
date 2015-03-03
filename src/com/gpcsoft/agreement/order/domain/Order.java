package com.gpcsoft.agreement.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

import com.gpcsoft.agreement.order.enumeration.OrderEnum;
import com.gpcsoft.agreement.pub.domain.AgContract;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>订单</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 协议供货--订单     		
  *  <br/>Create Date：2010-4-7 下午04:00:21 by sunl    					                            
  *  <br/>Modified Date:  2010-4-7 下午04:00:21 by sunl                                   
  *   
  *   @since 0.5
  *   @version: 0.5 
  *   
  *   @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.order"
  *   @gpcsoft.page domain="order" project="agreement"
  *   @gpcsoft.domain
  *   @gpcsoft.title value="订单"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREEMENT_ORDER")
@OrderProperty(property="orderNo", flag="true")
public class Order implements GpcBaseObject , IPropertyCUserTime{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@Id
	@Column(name = "ORDER_ID", length = 36)
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	private String objId;
	
	/** 订单编号 */
	@NotNull
	@Column(name = "ORDER_NO", length = 15)
	private String orderNo;
	
	/** 合同 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CONTRACT_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private AgContract contract;
	
	/** 项目  add by yucy*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="PROJECT_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Project project;
	
	
	/** 采购人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="BUYER_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private OrgInfo buyer;
	
	/** 供应商 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="SUPPLIER_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private OrgInfo supplier;
	
	/**品目名称集合*/
	@Column(name = "CATEGORY_NAMES", length = 200)
	private String categoryNames;
	
	/** 总数量 */
	@Column(name = "GOODS_QTY", precision = 16, scale = 6)
	private BigDecimal goodsQty;
	
	/** 总金额 */
	@Column(name = "GOODS_TOTAL", precision = 16, scale = 6)
	private BigDecimal goodsTotal;
	
	/** 采购人确认时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "B_CONFIRM_DATE", length = 7)
	private Date buyerConfirmDate;
	
	/** 采购人确认人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="B_CONFIRM_USER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User buyerConfirmUser;
	
	/** 供应商确认时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "S_CONFIRM_DATE", length = 7)
	private Date supplierConfirmDate;
	
	/** 供应商确认人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="S_CONFIRM_USER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User supplierConfirmUser;
	
	/** 供应商确认状态 */
	@Column(name = "S_CONFIRM_STATUS", length = 15)
	private String supplierConfirmStatus;

	/** 采购人确认状态 */
	@Column(name = "B_CONFIRM_STATUS", length = 15)
	private String buyerConfirmStatus;
	
	/** 采购人确认意见 */
	@Column(name = "B_CONFIRM_OPTION")
	private String buyerOption;
	
	/** 供应商确认意见 */
	@Column(name = "S_CONFIRM_OPTION")
	private String supplierOption;
	
	/** 状态 */
	@Column(name = "USE_STATUS", length = 2)
	private String useStatus;
	
	/** 支付状态 */
	@Column(name = "PAY_STATUS", length = 2)
	private String payStatus;
	
	/**支付状态中文*/
	@Transient
	private String payStatusCN;
	
	/** 备注 */
	@Length(max=200)  
	@Column(name = "MEMO", length = 200)
	private String memo;
	
	/** 订单明细 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN,CascadeType.SAVE_UPDATE})
	@JoinColumn(name = "ORDER_ID") 
	private Set<OrderItem> orderItems = new HashSet<OrderItem>(0);
	
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRE_TIME", length = 7)
	private Date createTime;
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CRE_USER_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User createUser;
	
	/**
	 * @gpcsoft.property title="主键"
	 */
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}

	/**
	 * @gpcsoft.property title="订单编号"
	 */
	public String getOrderNo() {
		return this.orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @gpcsoft.property title="合同"
	 */
	public AgContract getContract() {
		return this.contract;
	}
	public void setContract(AgContract contract) {
		this.contract = contract;
	}

	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	/**
	 * @gpcsoft.property title="采购人"
	 */	
	public OrgInfo getBuyer() {
		return this.buyer;
	}
	public void setBuyer(OrgInfo buyer) {
		this.buyer = buyer;
	}

	/**
	 * @gpcsoft.property title="供应商"
	 */	
	public OrgInfo getSupplier() {
		return this.supplier;
	}
	public void setSupplier(OrgInfo supplier) {
		this.supplier = supplier;
	}

	/**
	 * @gpcsoft.property title="采购品目"
	 */	
	public String getCategoryNames() {
		return categoryNames;
	}
	public void setCategoryNames(String categoryNames) {
		this.categoryNames = categoryNames;
	}
	
	/**
	 * @gpcsoft.property title="总数量"
	 */	
	public BigDecimal getGoodsQty() {
		return this.goodsQty;
	}
	public void setGoodsQty(BigDecimal goodsQty) {
		this.goodsQty = goodsQty;
	}

	/**
	 * @gpcsoft.property title="总金额"
	 */	
	public BigDecimal getGoodsTotal() {
		return this.goodsTotal;
	}
	public void setGoodsTotal(BigDecimal goodsTotal) {
		this.goodsTotal = goodsTotal;
	}

	/**
	 * @gpcsoft.property title="采购人确认时间"
	 */	
	public Date getBuyerConfirmDate() {
		return buyerConfirmDate;
	}

	public void setBuyerConfirmDate(Date buyerConfirmDate) {
		this.buyerConfirmDate = buyerConfirmDate;
	}
	
	
	/**
	 * @gpcsoft.property title="采购人确认人"
	 */	
	public User getBuyerConfirmUser() {
		return this.buyerConfirmUser;
	}
	public void setBuyerConfirmUser(User buyerConfirmUser) {
		this.buyerConfirmUser = buyerConfirmUser;
	}
	
	/**
	 * @gpcsoft.property title="供应商确认时间"
	 */	
	public Date getSupplierConfirmDate() {
		return this.supplierConfirmDate;
	}
	public void setSupplierConfirmDate(Date supplierConfirmDate) {
		this.supplierConfirmDate = supplierConfirmDate;
	}

	/**
	 * @gpcsoft.property title="供应商确认人"
	 */	
	public User getSupplierConfirmUser() {
		return this.supplierConfirmUser;
	}
	public void setSupplierConfirmUser(User supplierConfirmUser) {
		this.supplierConfirmUser = supplierConfirmUser;
	}

	/**
	 * @gpcsoft.property title="状态"
	 */	
	public String getUseStatus() {
		return this.useStatus;
	}
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	
	/**@gpcsoft.property title="支付状态"*/	
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	public String getPayStatusCN() {
		this.payStatusCN = OrderEnum.getPayStatusCN(this.payStatus);
		return this.payStatusCN;
	}
	public void setPayStatusCN(String payStatusCN) {
		this.payStatusCN = payStatusCN;
	}
	/**
	 * @gpcsoft.property title="供应商确认状态"
	 */
	public String getSupplierConfirmStatus() {
		return supplierConfirmStatus;
	}
	public void setSupplierConfirmStatus(String supplierConfirmStatus) {
		this.supplierConfirmStatus = supplierConfirmStatus;
	}
	
	/**
	 * @gpcsoft.property title="采购人确认状态"
	 */
	public String getBuyerConfirmStatus() {
		return buyerConfirmStatus;
	}
	public void setBuyerConfirmStatus(String buyerConfirmStatus) {
		this.buyerConfirmStatus = buyerConfirmStatus;
	}
	
	public String getBuyerOption() {
		return buyerOption;
	}
	public void setBuyerOption(String buyerOption) {
		this.buyerOption = buyerOption;
	}
	public String getSupplierOption() {
		return supplierOption;
	}
	public void setSupplierOption(String supplierOption) {
		this.supplierOption = supplierOption;
	}
	/**
	 * @gpcsoft.property title="备注"
	 */	
	public String getMemo() {
		return this.memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @gpcsoft.property title="订单明细"
	 */	
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	/**
	 * @gpcsoft.property title="创建时间"
	 */	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * @gpcsoft.property title="创建人"
	 */	
	public User getCreateUser() {
		return createUser;
	}
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	

}