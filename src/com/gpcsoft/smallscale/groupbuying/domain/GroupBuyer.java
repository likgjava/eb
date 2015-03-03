package com.gpcsoft.smallscale.groupbuying.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.smallscale.groupbuying.enumeration.GroupBuyingEnum;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.baseData.domain.District;

/** 
  *  Comments: <strong>团购采购人</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   smallscale                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2011-6-21 上午08:49:53 by likg    					                            
  *  <br/>Modified Date:  2011-6-21 上午08:49:53 by likg                                   
  *  <p>@since 0.5
  *
  * @gpcsoft.package packageDir="com.gpcsoft.smallscale.groupbuying"
  * @gpcsoft.page domain="GroupBuyer"
  * @hibernate.class table="EPS_GROUP_BUYER"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate=true, dynamicInsert=true)
@Table(name="EPS_GROUP_BUYER")
public class GroupBuyer implements GpcBaseObject ,IPropertyCUserTime {

	/** serialVersionUID */
	private static final long serialVersionUID = 2918186780375709580L;

	/**记录号*/
    @Id
    @Column(name="GROUP_BUYER_ID", length=50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**所属机构*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="ORG_INFO_ID")
    @BatchSize(size=15)
    private OrgInfo orgInfo;
    
    /**所属团购*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="GROUP_BUYING_ID")
    @BatchSize(size=15)
    private GroupBuying groupBuying;
    
    /**购买数量*/
    @Column(name="AMOUNT")
    private BigDecimal amount;
    
    /**收货人姓名*/
    @Column(name="RECEIVE_NAME", length=30)
    private String receiveName;
    
    /**手机号码*/
    @Column(name="MOBILE_PHONE", length=30)
    private String mobilePhone;
    
    /**固定电话*/
    @Column(name="FIXED_PHONE", length=30)
    private String fixedPhone;
    
    /**电子邮箱*/
    @Column(name="EMAIL", length=30)
    private String email;
    
    /**省份*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="PROVINCE")
	@BatchSize(size = 15)
    private District province;
    
    /**详细地址*/
    @Column(name="ADDRESS", length=200)
    private String address;
    
    /**邮政编码*/
    @Column(name="POST_CODE", length=6)
    private String postCode;
    
    /**支付方式[00:货到付款，01:网银，02:支付宝]*/
    @Column(name="PAYMENT_METHOD", length=2)
    private String paymentMethod;
    
    @Transient
    private String paymentMethodCN;
    
    /**支付状态*/
    @Column(name="PAY_STATUS", length=2)
    private String payStatus;
    
    @Transient
    private String payStatusCN;
    
    /**配送方式[00:快递，01:平邮，02:EMS]*/
    @Column(name="SHIPPING_METHOD", length=2)
    private String shippingMethod;
    
    @Transient
    private String shippingMethodCN;
    
    /**创建人*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="CREATOR_ID")
    @BatchSize(size = 15)
    private User createUser;
    
    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

	/** @gpcsoft.property title="记录号" */
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/** @gpcsoft.property title="所属机构" */
	public OrgInfo getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}

	/** @gpcsoft.property title="团购" */
	public GroupBuying getGroupBuying() {
		return groupBuying;
	}

	public void setGroupBuying(GroupBuying groupBuying) {
		this.groupBuying = groupBuying;
	}

	/** @gpcsoft.property title="购买数量" */
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/** @gpcsoft.property title="收货人姓名" */
	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	/** @gpcsoft.property title="移动电话" */
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/** @gpcsoft.property title="固定电话" */
	public String getFixedPhone() {
		return fixedPhone;
	}

	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}

	/** @gpcsoft.property title="电子邮箱" */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/** @gpcsoft.property title="省份" */
	public District getProvince() {
		return province;
	}

	public void setProvince(District province) {
		this.province = province;
	}

	/** @gpcsoft.property title="详细地址" */
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/** @gpcsoft.property title="邮政编码" */
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/** @gpcsoft.property title="支付方式" */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethodCN() {
		this.paymentMethodCN = GroupBuyingEnum.getPaymentMethodCN(this.paymentMethod);
		return paymentMethodCN;
	}

	public void setPaymentMethodCN(String paymentMethodCN) {
		this.paymentMethodCN = paymentMethodCN;
	}
	
	/** @gpcsoft.property title="支付状态" */
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	public String getPayStatusCN() {
		this.payStatusCN = GroupBuyingEnum.getPayStatusCN(this.payStatus);
		return this.payStatusCN;
	}

	public void setPayStatusCN(String payStatusCN) {
		this.payStatusCN = payStatusCN;
	}

	/** @gpcsoft.property title="配送方式" */
	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public String getShippingMethodCN() {
		this.shippingMethodCN = GroupBuyingEnum.getShippingMethodCN(this.shippingMethod);
		return shippingMethodCN;
	}

	public void setShippingMethodCN(String shippingMethodCN) {
		this.shippingMethodCN = shippingMethodCN;
	}

	/** @gpcsoft.property title="创建人" */
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	/** @gpcsoft.property title="创建时间" */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}
