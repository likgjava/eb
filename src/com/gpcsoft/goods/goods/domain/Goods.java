package com.gpcsoft.goods.goods.domain;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.VerifyObject;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>商品</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-27 上午11:50:28 by yucy    					                            
  *  <br/>Modified Date:  2010-7-27 上午11:50:28 by yucy                                   
  *  <p>@since 0.5
  *   
  * @gpcsoft.package packageDir="com.gpcsoft.goods.goods"
  * @gpcsoft.page domain="goods"
  * @hibernate.class table="GOODS"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "GOODS")
public class Goods implements GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime, VerifyObject{
    /** serialVersionUID */
    private static final long serialVersionUID = 4300282297503759581L;

    /**商品Id*/
    @Id
    @Column(name = "GOODS_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**商品名称*/
    @Column(name = "PRODUCT_NAME", length = 100)
    private String productName;
    
    /**拼音简码*/
    @Column(name = "SHORT_SPELL_NAME", length = 50)
    private String shortSpellName;
    
    /**商品型号*/
    @Column(name = "PRODUCT_CODE", length = 100)
    private String productCode;
    
    /**商品编号，生成规则：商品分类编号+年（4位）+6位流水号*/
    @Column(name = "GOODS_CODE", length = 100)   
    private String goodsCode;
    
    /**商品分类*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="GOODS_CLASS_ID")
    @BatchSize(size = 15)
    private GoodsClass goodsClass;
    
    /**商品品牌*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="GOODS_BRAND_ID")  
    @BatchSize(size = 15)
    private GoodsBrand goodsBrand;
    
    /**商品品目*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="PUR_CATEGORY_ID")
    @BatchSize(size = 15)
    private PurCategory purCategory;
    
    /**功能描述*/
    @Column(name = "FUNCTION_INTRO", length = 100, columnDefinition = "CLOB")
    private String functionIntro;
    
    /**是否自定义商品*/
    @Column(name = "IS_CUSTOM", columnDefinition ="default=0", length = 5)
    private Boolean isCustom;
    
    @Transient
    private String isCustomCN;
    
    /**计量单位，从数据字典列表选择，存入name，不存code*/
    @Column(name = "MEASURE_UNIT", length = 100)
    private String measureUnit;
    
    /**参考价*/
    @Column(name = "REFER_PRICE", length = 100)
    private BigDecimal referPrice;
    
    /**产地*/
    @Column(name = "MADE_IN", length = 100)
    private String madeIn;
    
    /**生产厂家：制造商*/
    @Column(name = "FACTORY", length = 100)
    private String factory;
    
    /**是否零配件*/
    @Column(name = "IS_ACCESSORY",columnDefinition ="default=0",  length = 5)
    private Boolean isAccessory;
    
    @Transient
    private String isAccessoryCN;
    
    /**节能产品编号*/
    @Column(name = "ENERGY_SAVING_PRODUCT_NO", length = 100)
    private String energySavingProductNo;
    
	/**当前有效id*/
	@Column(name = "CURRENT_VALID_ID", length = 50)
	private String currentId;
	
    /**EnvironmentLabel 环境标志产品   是否"进入《环境标志产品政府采购清单》"：*/
    @Column(name = "ENVIRONMENT_LABEL", length = 100)
    private String environmentLabel;
    
    /**如果进入《含有密码技术的信息产品政府采购清单》，则录入编号，否则为空*/
    @Column(name = "CRYPTOGRAPHY_TECH_CODE", length = 100)
    private String cryptographyTechCode;
    
    /**自主创新认定编号*/
    @Column(name = "CREATION_CODE", length = 100)
    private String creationCode;
    
    /**是否可单独出售*/
    @Column(name = "SOLE_TO_SELL", columnDefinition ="default=0", length = 5)
    private Boolean soleToSell;
    
    @Transient
    private String soleToSellCN;
    
    /**是否特供*/
    @Column(name = "SPECIAL",columnDefinition ="default=0",  length = 5)
    private Boolean special;
    
    @Transient
    private String specialCN;
    
    /** 录入方式:分项录入、整体录入，两者任选 */
    @Column(name = "PARAM_INPUT_TYPE", length='5')
    private String paramInputType;
    
    /**备注*/
    @Column(name = "REMARK", length = 2000)
    private String remark;
    
    /**审核状态：00.草稿（默认） 01.待审核 02.通过 03.不通过*/
    @Column(name = "AUDIT_STATUS", length = 100)
    private String auditStatus;
    
    @Transient
	private String auditStatusCN;
    
    /**售卖状态：01.启卖 02.禁卖*/
    @Column(name = "SELL_STATUS", length = 100)
    private String sellStatus;
    
    /**有效状态：[临时:00,有效:01,报废:02]*/
    @Column(name = "MANGER_STATUS", length = 100)
    private String useStatus;
    
    @Transient
	private String useStatusCN;
    
    @Transient
	private String sellStatusCN;
    
    /**产品发布时间*/
    @Temporal(TemporalType.DATE)
    @Column(name = "PRODUCT_DATE_ISSUED")
    private Date productDateIssued;
    
    /**外部信息链接*/
    @Column(name = "EXTERNAL_INFOR_LINK", length = 100)
    private String externalInforLink;
    
    /**商品图片*/
	@Column(name="PICTURE_ID")	 
    private String  picture;
    
    /**附加图片*/
    @Column(name = "ADDITION_PICTURE")
    private String  additionPicture;
    
    
    /**规格说明（详细配置），文字形式描述的总体规格说明，详细质量指标的规格说明可以放到配置参数中描述。*/
    @Column(name = "SPEC", length = 3000)
    private String spec;

    /** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="CREATOR_ID")  
    @BatchSize(size = 15)
    private User createUser;
    
    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /** 修改人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="MODIFIER_ID")  
    @BatchSize(size = 15)//批量抓取
    private User updateUser;

    /**修改时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_TIME")
    private Date updateTime;
    
    /**生效时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VALID_TIME")
    private Date validTime;
    
    /**失效时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INVALID_TIME")
    private Date invalidTime;
    
    /**商品参数*/
    @LazyCollection(value=LazyCollectionOption.EXTRA)
    @JoinColumn(name = "GOODS_ID") 
    @OneToMany
    @Cascade({CascadeType.ALL})
    @OrderBy("sortNo asc")
    private Set<GoodsParam> goodsParamSet = new HashSet<GoodsParam>();
    
    /**审核人*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="VERIFIER_ID") 
	@BatchSize(size = 15)
	private User verifyUser;
	
    /**审核时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VERIFY_TIME")
    private Date verifyTime;
    
    /**意见*/
    @Column(name = "OPINION")
    private String opinion;
    
    /**
	 * 评价总分
	 */
    @Formula("(select nvl(avg(e.GOODS_EVALUATE_LEVEL),0) from GOODS_EVALUATE e where e.GOODS_ID = GOODS_ID)")
    private BigDecimal evalSum;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getFunctionIntro() {
		return functionIntro;
	}

	public void setFunctionIntro(String functionIntro) {
		this.functionIntro = functionIntro;
	}

	public Boolean getIsCustom() {
		return isCustom;
	}

	public void setIsCustom(Boolean isCustom) {
		this.isCustom = isCustom;
	}

	public String getIsCustomCN() {
		if(this.isCustom == null){
			this.isCustomCN = "";
		}else{
			this.isCustomCN = GoodsEnum.getGoodsBooleanCN("isCustom", this.isCustom==true?"1":"0");
		}
		return this.isCustomCN;
	}

	public void setIsCustomCN(String isCustomCN) {
		this.isCustomCN = isCustomCN;
	}

	public String getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}

	public BigDecimal getReferPrice() {
		return referPrice;
	}

	public void setReferPrice(BigDecimal referPrice) {
		this.referPrice = referPrice;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getAdditionPicture() {
		return additionPicture;
	}

	public void setAdditionPicture(String additionPicture) {
		this.additionPicture = additionPicture;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public GoodsClass getGoodsClass() {
		return goodsClass;
	}

	public void setGoodsClass(GoodsClass goodsClass) {
		this.goodsClass = goodsClass;
	}

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public Boolean getIsAccessory() {
		return isAccessory;
	}

	public void setIsAccessory(Boolean isAccessory) {
		this.isAccessory = isAccessory;
	}

	public String getEnergySavingProductNo() {
		return energySavingProductNo;
	}

	public void setEnergySavingProductNo(String energySavingProductNo) {
		this.energySavingProductNo = energySavingProductNo;
	}

	public String getEnvironmentLabel() {
		return environmentLabel;
	}

	public void setEnvironmentLabel(String environmentLabel) {
		this.environmentLabel = environmentLabel;
	}

	public String getCryptographyTechCode() {
		return cryptographyTechCode;
	}

	public void setCryptographyTechCode(String cryptographyTechCode) {
		this.cryptographyTechCode = cryptographyTechCode;
	}

	public String getCreationCode() {
		return creationCode;
	}

	public void setCreationCode(String creationCode) {
		this.creationCode = creationCode;
	}

	public Boolean getSoleToSell() {
		return soleToSell;
	}

	public void setSoleToSell(Boolean soleToSell) {
		this.soleToSell = soleToSell;
	}

	public Boolean getSpecial() {
		return special;
	}

	public void setSpecial(Boolean special) {
		this.special = special;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	public String getAuditStatusCN() {
		this.auditStatusCN = GoodsEnum.getAuditStatusCN(this.auditStatus);
		return this.auditStatusCN;
	}

	public void setAuditStatusCN(String auditStatusCN) {
		this.auditStatusCN = auditStatusCN;
	}
	
	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getUseStatusCN() {
		this.useStatusCN = GoodsEnum.getUseStatusCN(this.useStatus);
		return useStatusCN;
	}

	public void setUseStatusCN(String useStatusCN) {
		this.useStatusCN = useStatusCN;
	}

	public String getSellStatusCN() {
		this.sellStatusCN = GoodsEnum.getSellStatusCN(this.sellStatus);
		return sellStatusCN;
	}

	public void setSellStatusCN(String sellStatusCN) {
		this.sellStatusCN = sellStatusCN;
	}

	public String getSellStatus() {
		return sellStatus;
	}

	public void setSellStatus(String sellStatus) {
		this.sellStatus = sellStatus;
	}

	public Date getProductDateIssued() {
		return productDateIssued;
	}

	public void setProductDateIssued(Date productDateIssued) {
		this.productDateIssued = productDateIssued;
	}

	public String getExternalInforLink() {
		return externalInforLink;
	}

	public void setExternalInforLink(String externalInforLink) {
		this.externalInforLink = externalInforLink;
	}

	public String getShortSpellName() {
		return shortSpellName;
	}

	public void setShortSpellName(String shortSpellName) {
		this.shortSpellName = shortSpellName;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getParamInputType() {
		return paramInputType;
	}

	public void setParamInputType(String paramInputType) {
		this.paramInputType = paramInputType;
	}

	public GoodsBrand getGoodsBrand() {
		return goodsBrand;
	}

	public void setGoodsBrand(GoodsBrand goodsBrand) {
		this.goodsBrand = goodsBrand;
	}

	public PurCategory getPurCategory() {
		return purCategory;
	}

	public void setPurCategory(PurCategory purCategory) {
		this.purCategory = purCategory;
	}

	public Set<GoodsParam> getGoodsParamSet() {
		return goodsParamSet;
	}

	public void setGoodsParamSet(Set<GoodsParam> goodsParamSet) {
		this.goodsParamSet = goodsParamSet;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public String getCurrentId() {
		return currentId;
	}

	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}

	public String getIsAccessoryCN() {
		if(this.isAccessory == null){
			this.isAccessoryCN = "";
		}else{
			this.isAccessoryCN = GoodsEnum.getGoodsBooleanCN("isAccessory", this.isAccessory==true?"1":"0");
		}
		return isAccessoryCN;
	}

	public void setIsAccessoryCN(String isAccessoryCN) {
		this.isAccessoryCN = isAccessoryCN;
	}

	public String getSoleToSellCN() {
		if(this.soleToSell == null){
			this.soleToSellCN = "";
		}else{
			this.soleToSellCN = GoodsEnum.getGoodsBooleanCN("soleToSell", this.soleToSell==true?"1":"0");
		}
		return soleToSellCN;
	}

	public void setSoleToSellCN(String soleToSellCN) {
		this.soleToSellCN = soleToSellCN;
	}

	public String getSpecialCN() {
		if(this.special == null){
			this.specialCN = "";
		}else{
			this.specialCN = GoodsEnum.getGoodsBooleanCN("special", this.special==true?"1":"0");
		}
		return specialCN;
	}

	public void setSpecialCN(String specialCN) {
		this.specialCN = specialCN;
	}

	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	public BigDecimal getEvalSum() {
		return evalSum;
	}

	public void setEvalSum(BigDecimal evalSum) {
		this.evalSum = evalSum;
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
}