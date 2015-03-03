package com.gpcsoft.goods.goodsprice.domain;

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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.VerifyObject;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.baseData.domain.District;

/** 
  *  Comments: <strong>行情</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 协议供货-行情     		
  *  <br/>Create Date：2010-4-16 下午03:16:35 by yucy    					                            
  *  <br/>Modified Date:  2010-4-16 下午03:16:35 by yucy                                   
  *  
  *   @since 0.5
  *   @version: 0.5 
  *   
  *  @gpcsoft.package packageDir="com.gpcsoft.goods.goodsprice"
  *  @gpcsoft.page domain="goods" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="行情"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "GOODS_PRICE")
public class GoodsPrice implements GpcBaseObject,IPropertyCUserTime,IPropertyUUserTime,VerifyObject {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 8336001968067353987L;

    /**记录号*/
    @Id
    @Column(name = "GOODS_PRICE_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
	
	/**行情供应商*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRICE_SUPPLIER_ID")
	private GoodsPriceSupplier goodsPriceSupplier;
	
    /**市场价*/
    @Column(name = "MRKT_UNIT_PRICE" )
    private BigDecimal unitPrice;
	
	/**折扣率*/
    @Column(name = "DSCU_RATE" )
    private BigDecimal dscuRate;
    
	/**协议价*/
    @Column(name = "PRTC_PRICE" )
    private BigDecimal prtcPrice;
    
    /**会员价*/
    @Column(name = "MEMBER_PRICE" )
    private BigDecimal memberPrice;

    /**生效日期*/
    @Temporal(TemporalType.DATE)
    @Column(name = "EFCT_DATE")
    private Date efctDate;
    
    /**失效日期*/
    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    private Date endDate;
    
	/**所属区域*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISTRICT_ID")
	private District district;
	
	/**备注*/
    @Column(name = "REMARKS" )
	private String remarks;
    
	/** 协议下协议商品分类 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.SAVE_UPDATE,CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "GOODS_PRICE_ID") 
	private Set<GoodsOptFitPrice> goodsOptFitPriceSet = new HashSet<GoodsOptFitPrice>();
    
	/**当前有效id*/
	@Column(name = "CURRENT_ID", length = 50)
	private String currentId;
    
    /**审批状态：00.草稿（默认） 01.待审核 02.通过 03.不通过*/
    @Column(name = "AUDIT_STAUTS", length = 100)
    private String auditStatus;
    
    /**有效状态（注：00.草稿,01.有效、02.报废）*/
    @Column(name = "USE_STATUS", length = 100)
    private String useStatus;

    /** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="CREATOR") 
    @BatchSize(size = 15)
    private User createUser;
    
    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    /** 修改人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="MODIFIER")
    @BatchSize(size = 15)
    private User updateUser;
    
    /**修改时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_TIME")
    private Date updateTime;
    
    /**审核人*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="AUDITOR") 
	@BatchSize(size = 15)
	private User verifyUser;
	
    /**审核时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AUDIT_TIME")
    private Date verifyTime;
    
    /**意见*/
    @Column(name = "AUDIT_OPINION")
    private String opinion;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public GoodsPriceSupplier getGoodsPriceSupplier() {
		return goodsPriceSupplier;
	}

	public void setGoodsPriceSupplier(GoodsPriceSupplier goodsPriceSupplier) {
		this.goodsPriceSupplier = goodsPriceSupplier;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getDscuRate() {
		return dscuRate;
	}

	public void setDscuRate(BigDecimal dscuRate) {
		this.dscuRate = dscuRate;
	}
	
	public BigDecimal getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(BigDecimal memberPrice) {
		this.memberPrice = memberPrice;
	}

	public BigDecimal getPrtcPrice() {
		return prtcPrice;
	}

	public void setPrtcPrice(BigDecimal prtcPrice) {
		this.prtcPrice = prtcPrice;
	}

	public Date getEfctDate() {
		return efctDate;
	}

	public void setEfctDate(Date efctDate) {
		this.efctDate = efctDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCurrentId() {
		return currentId;
	}

	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public User getVerifyUser() {
		return verifyUser;
	}

	public void setVerifyUser(User verifyUser) {
		this.verifyUser = verifyUser;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Set<GoodsOptFitPrice> getGoodsOptFitPriceSet() {
		return goodsOptFitPriceSet;
	}

	public void setGoodsOptFitPriceSet(Set<GoodsOptFitPrice> goodsOptFitPriceSet) {
		this.goodsOptFitPriceSet = goodsOptFitPriceSet;
	}
	
}