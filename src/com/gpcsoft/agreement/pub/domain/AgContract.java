package com.gpcsoft.agreement.pub.domain;

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
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

import com.gpcsoft.agreement.order.domain.Order;
import com.gpcsoft.agreement.pub.enumeration.PubEnum;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.core.utils.MoneyUtil;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>Contract</strong>            		
  *	 <br/> 合同		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 协议供货--合同     		
  *  <br/>Create Date：2010-4-7 下午03:52:50 by liangxj    					                            
  *  <br/>Modified Date:  2010-4-7 下午03:52:50 by liangxj                                   
  *
  *   @since 0.5
  *   @version: 0.5 
  *   
  *   @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.pub"
  *   @gpcsoft.page domain="pub" project="agreement"
  *   @gpcsoft.domain
  *   @gpcsoft.title value="合同"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_PUB_CONTRACT")
@OrderProperty(property="contractNo", flag="true")
public class AgContract implements GpcBaseObject ,IPropertyCUserTime {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "CONTRACT_ID", unique = true, nullable = false, length = 50)
	private String objId;
	
	/** 合同名称 */
	@NotNull
	@Length(max=100, min=6)
	@Column(name = "CONTRACT_NAME", length = 100)
	private String contractName;
	
	/** 合同编号 */
	@NotNull
	@Length(max=100, min=6)
	@Column(name = "CONTRACT_NO", length = 50)
	private String contractNo;
	
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
	
	/** 合同签订时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CONTRACT_SIGNED_TIME", length = 7)
	private Date contractSignedTime;

	/** 合同生效时间 */
	@Temporal(TemporalType.DATE)
	@Column(name = "CONTRACT_BEGIN_TIME", length = 7)
	private Date contractBeginTime;
	
	/** 合同失效时间 */
	@Temporal(TemporalType.DATE)
	@Column(name = "CONTRACT_END_TIME", length = 7)
	private Date contractEndTime;
	
	/** 合同类型 1:项目采购合同，2:协议采购合同 */
	@Column(name = "CONTRACT_TYPE", precision = 2, scale = 0)
	private String contractType;
	
	/** 交货日期 */
	@Column(name = "DELIVERY_TIME", length = 100)
	private String deliveryTime;
	
	/** 交货地点 */
	@Column(name = "DELIVERY_PLACE", length = 500)
	private String deliveryPlace;
	
	/** 备注 */
	@Length(max=3000)
	@Column(name = "MEMO", length = 3000)
	private String memo;
	
	/** 合同状态 00:草稿 01:正式 02:作废 */
	@Column(name = "USE_STATUS", length = 2)
	private String useStatus;
	
	/** 合同文件 */
	@Column(name = "CONTRACT_FILE", length = 50)
	private String contractFile;
	
	/** 采购人确认状态 00:未确认 01:确认 02:退回 */
	@Column(name = "B_CONFIRM_STATUS", length = 2)
	private String buyerConfirmStatus;
	
	/** 采购人确认意见 */
	@Column(name = "B_CONFIRM_OPTION")
	private String buyerOption;
	
	/** 供应商确认意见 */
	@Column(name = "S_CONFIRM_OPTION")
	private String supplierOption;
	
	@Transient
	private String buyerConfirmStatusCN;
	
	/** 采购人确认时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "B_CONFIRM_DATE", length = 7)
	private Date buyerConfirmDate;
	
	/** 采购人确认人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="B_CONFIRM_USER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User buyerConfirmUser;
	
	/** 供货商确认状态 00:未确认 01:确认 02:退回 */
	@Column(name = "S_CONFIRM_STATUS", length = 2)
	private String supplierConfirmStatus;
	
	@Transient
	private String supplierConfirmStatusCN;
	
	
	/** 供应商确认时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "S_CONFIRM_DATE", length = 7)
	private Date supplierConfirmDate;
	
	/** 供应商确认人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="S_CONFIRM_USER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User supplierConfirmUser;
	
	/** 合同总额 */
	@Formula("(select sum(o.GOODS_TOTAL) from EPS_AGREEMENT_ORDER o where o.CONTRACT_ID = CONTRACT_ID)")
	private BigDecimal total;
	
	@Transient
	private String totalCN;
	
	@Transient
	private String totalFormat;
	
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRE_TIME", length = 7)
	private Date createTime;
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CRE_USER_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User createUser;
	
	/** 订单 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany(mappedBy = "contract")
	@JoinColumn(name = "CONTRACT_ID") 
	private Set<Order> orders = new HashSet<Order>(0);

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/**
	 * @gpcsoft.property title="合同名称"
	 */
	public String getContractName() {
		return contractName;
	}
	/** 合同名称 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	/**
	 * @gpcsoft.property title="合同编号"
	 */
	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/**
	 * @gpcsoft.property title="采购人"
	 */
	public OrgInfo getBuyer() {
		return buyer;
	}

	public void setBuyer(OrgInfo buyer) {
		this.buyer = buyer;
	}

	/**
	 * @gpcsoft.property title="供应商"
	 */
	public OrgInfo getSupplier() {
		return supplier;
	}

	public void setSupplier(OrgInfo supplier) {
		this.supplier = supplier;
	}

	/**
	 * @gpcsoft.property title="合同签订时间"
	 */
	public Date getContractSignedTime() {
		return contractSignedTime;
	}

	public void setContractSignedTime(Date contractSignedTime) {
		this.contractSignedTime = contractSignedTime;
	}

	/**
	 * @gpcsoft.property title="合同开始时间"
	 */
	public Date getContractBeginTime() {
		return contractBeginTime;
	}

	public void setContractBeginTime(Date contractBeginTime) {
		this.contractBeginTime = contractBeginTime;
	}

	/**
	 * @gpcsoft.property title="合同结束时间"
	 */
	public Date getContractEndTime() {
		return contractEndTime;
	}

	public void setContractEndTime(Date contractEndTime) {
		this.contractEndTime = contractEndTime;
	}

	/**
	 * @gpcsoft.property title="合同类型"
	 */
	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	/**
	 * @gpcsoft.property title="备注"
	 */
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @gpcsoft.property title="合同状态"
	 */
	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	/**
	 * @gpcsoft.property title="合同文件"
	 */
	public String getContractFile() {
		return contractFile;
	}

	public void setContractFile(String contractFile) {
		this.contractFile = contractFile;
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

	/**
	 * @gpcsoft.property title="供货商确认状态"
	 */

	public void setSupplierConfirmStatus(String supplierConfirmStatus) {
		this.supplierConfirmStatus = supplierConfirmStatus;
	}

	public void setBuyerConfirmDate(Date buyerConfirmDate) {
		this.buyerConfirmDate = buyerConfirmDate;
	}

	/**
	 * @gpcsoft.property title="采购人确认时间"
	 */	
	public Date getBuyerConfirmDate() {
		return buyerConfirmDate;
	}

	public String getSupplierConfirmStatus() {
		return supplierConfirmStatus;
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
	 * @gpcsoft.property title="合同总额"
	 */
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * @gpcsoft.property title="交货时间"
	 */	
	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	/**
	 * @gpcsoft.property title="交货地点"
	 */	
	public String getDeliveryPlace() {
		return deliveryPlace;
	}

	public void setDeliveryPlace(String deliveryPlace) {
		this.deliveryPlace = deliveryPlace;
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

	/**
	 * @gpcsoft.property title="订单"
	 */	
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public String getBuyerConfirmStatusCN() {
		if(this.buyerConfirmStatus != null){
			buyerConfirmStatusCN = PubEnum.getConfirmStatusCN(this.buyerConfirmStatus);
		}
		else{
			buyerConfirmStatusCN =  "";
		}
		
		return buyerConfirmStatusCN;
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

	public void setBuyerConfirmStatusCN(String buyerConfirmStatusCN) {
		this.buyerConfirmStatusCN = buyerConfirmStatusCN;
	}

	public String getSupplierConfirmStatusCN() {
		if(this.supplierConfirmStatus != null){
			supplierConfirmStatusCN =  PubEnum.getConfirmStatusCN(this.supplierConfirmStatus);
		}
		else {
			 supplierConfirmStatusCN = "";
		}		
		return supplierConfirmStatusCN;
	}

	public void setSupplierConfirmStatusCN(String supplierConfirmStatusCN) {
		this.supplierConfirmStatusCN = supplierConfirmStatusCN;
	}
	
	public String getTotalCN(){
		totalCN = total!=null?MoneyUtil.amountToChinese(this.getTotal().doubleValue()):null;
		return totalCN;
	}
	
	public void setTotalCN(String totalCN){
		this.totalCN =totalCN;
	}
	
	public String getTotalFormat(){
		totalFormat = total!=null?MoneyUtil.getDecimalFormat(this.getTotal().doubleValue()):null;
		return totalFormat;
	}
	
	public void setTotalFormat(String totalFormat){
		this.totalFormat =totalFormat;
	}
}