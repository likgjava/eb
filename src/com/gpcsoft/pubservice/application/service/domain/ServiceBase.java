package com.gpcsoft.pubservice.application.service.domain;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.pubservice.application.service.enumeration.ServiceEnum;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>服务</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   pubservice                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-8-9 下午01:55:58 by sunl   					                            
  *  <br/>Modified Date:  2010-8-9 下午01:55:58 by sunl   
  *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.service"
  *  @gpcsoft.page domain="Favorites" project="pubservice/application"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="服务"                              
  *  @since 0.5
  *  @version: 0.5 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "SERVICE_BASE")
public class ServiceBase implements GpcBaseObject ,IPropertyCUserTime{
    
	/** serialVersionUID */
	private static final long serialVersionUID = 2711365021142479938L;

	/**主键*/
    @Id
    @Column(name = "SERVICE_ID")
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**
	 * 服务名称
	 */
	@Column(name = "SERVICE_NAME", length = 100)
	private String serviceName;
	
	/**
	 * 服务图片
	 */
	@Column(name = "SERVICE_PIC", length = 100)
	private String servicePic;
	
	/**
	 * 服务描述
	 */
	@Column(name = "SERVICE_DESC", length = 100)
	private String serviceDesc;
	
	/**
	 * 服务外部链接
	 */
	@Column(name = "SERVICE_LINK", length = 100)
	private String serviceLink;
	
	/**
	 * 服务协议外部链接
	 */
	@Column(name = "SERVICE_AGREEMENT_LINK", length = 100)
	private String serviceAgreementLink;
	
	/**单价*/
    @Column(name = "SERVICE_PRICE", length = 100)
    private BigDecimal servicePrice;
    
    /**
	 * 金额单位，枚举【01:年， 02:月， 03:期】
	 */
	@Column(name = "SERVICE_UNIT", length = 100)
	private String serviceUnit;
	
	@Transient
	private String serviceUnitCN;
	
	/**
	 * 前置服务
	 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="SERVICE_PREPOSITION")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private ServiceBase servicePreposition;

	/**
	 * 是否可单独购买
	 */
	@Column(name = "IS_SINGLE_PURCHASE", length = 100)
	private String isSinglePurchase;

	/**
	 * 是否推荐服务
	 */
	@Column(name = "IS_RECOMMENDATION", length = 100)
	private String isRecommendation;
	
	/** 使用状态[00:临时;01:有效;02:无效] */
	@Column(name = "USE_STATUS", length = 2)
	private String useStatus;
	
	/** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR") 
    @BatchSize(size = 15)
    private User createUser;
	
	/**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATETIME")
    private Date createTime;

    public Date getCreateTime() {
		return createTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public String getIsRecommendation() {
		return isRecommendation;
	}
    
    public String getIsSinglePurchase() {
		return isSinglePurchase;
	}

	public String getObjId() {
		return objId;
	}

	public String getServiceAgreementLink() {
		return serviceAgreementLink;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public String getServiceLink() {
		return serviceLink;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getServicePic() {
		return servicePic;
	}

	public ServiceBase getServicePreposition() {
		return servicePreposition;
	}

	public BigDecimal getServicePrice() {
		return servicePrice;
	}

	public String getServiceUnit() {
		return serviceUnit;
	}

	public String getServiceUnitCN() {
		this.serviceUnitCN = ServiceEnum.getServiceUnitCN(this.serviceUnit);
		return serviceUnitCN;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public void setIsRecommendation(String isRecommendation) {
		this.isRecommendation = isRecommendation;
	}

	public void setIsSinglePurchase(String isSinglePurchase) {
		this.isSinglePurchase = isSinglePurchase;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setServiceAgreementLink(String serviceAgreementLink) {
		this.serviceAgreementLink = serviceAgreementLink;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public void setServiceLink(String serviceLink) {
		this.serviceLink = serviceLink;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setServicePic(String servicePic) {
		this.servicePic = servicePic;
	}

	public void setServicePreposition(ServiceBase servicePreposition) {
		this.servicePreposition = servicePreposition;
	}

	public void setServicePrice(BigDecimal servicePrice) {
		this.servicePrice = servicePrice;
	}

	public void setServiceUnit(String serviceUnit) {
		this.serviceUnit = serviceUnit;
	}

	public void setServiceUnitCN(String serviceUnitCN) {
		this.serviceUnitCN = serviceUnitCN;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
}