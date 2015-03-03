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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.VerifyObject;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>商品品牌</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-27 下午12:45:45 by yucy    					                            
  *  <br/>Modified Date:  2010-7-27 下午12:45:45 by yucy                                   
  *	 <p>@since 0.5
  *   @version: 0.5 
  *   
  * @gpcsoft.package packageDir="com.gpcsoft.goods.goods"
  * @gpcsoft.page domain="goods"
  * @hibernate.class table="GoodsBrand"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "GOODS_BRAND")
public class GoodsBrand  implements GpcBaseObject ,IPropertyCUserTime,IPropertyUUserTime,VerifyObject{
    
	/** serialVersionUID */
    private static final long serialVersionUID = -7246757478587677221L;

    /**记录号*/
    @Id
    @Column(name = "GOODS_BRAND_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**品牌编号*/
    @Column(name = "BRAND_CODE", length = 100)
    private String brandCode;
    
    /**品牌说明*/
    @Column(name = "BRAND_DESC", length = 100)
    private String brandDesc;
    
    /**品牌名称*/
    @Column(name = "BRAND_NAME", length = 100)
    private String brandName;
    
    /**品牌英文名称*/
    @Column(name = "ENGLISH_NAME", length = 100)
    private String englishName;

    /**拼音简写*/
    @Column(name = "SHORT_SPELL_NAME", length = 50)
    private String shortSpellName;
    
	/**当前有效id*/
	@Column(name = "CURRENT_VALID_ID", length = 50)
	private String currentId;
    
    /**品牌主标识*/
    @Column(name = "MAIN_LOGO_ID")
    private String mainLogo;
    
    /**售卖状态（注：启卖、禁卖）*/
    @Column(name = "SELL_STATUS", length = 100 ,columnDefinition="default='01'")
    private String sellStatus;
    
    /**审批状态：00.草稿（默认） 01.待审核 02.通过 03.不通过*/
    @Column(name = "AUDIT_STATUS", length = 100)
    private String auditStatus;
    
    /**有效状态（注：00.草稿,01.有效、02.报废）*/
    @Column(name = "MANAGER_STATUS", length = 100)
    private String useStatus;
    
    /**ECDEMIC 是否外地品牌*/
    @Column(name = "ECDEMIC", length = 5)
    private Boolean ecdemic;
    
    /**ECDEMIC 是否国外品牌*/
    @Column(name = "FOREIGNER", length = 5)
    private Boolean foreigner;
    
    /**类别名称*/
    @Column(name= "GOODS_CLASS_NAMES", length = 200)
    private String goodsClassNames;
    
	
	/**商品分类通过中间表映射*/
	@ManyToMany(cascade = {javax.persistence.CascadeType.MERGE,javax.persistence.CascadeType.REFRESH})    
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@JoinTable(name="GOODS_BRAND_TO_CLASS",joinColumns={@JoinColumn(name="GOODS_BRAND_ID")},inverseJoinColumns={@JoinColumn(name="GOODS_CLASS_ID")})
	@Cache(region="com.gpcsoft.goods.goodsclass.domain.GoodsClass", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<GoodsClass> goodsClasses = new HashSet<GoodsClass>();					//角色信息
	
    /**商品维护商*/
	@LazyCollection(value=LazyCollectionOption.EXTRA)
    @OneToMany
    @Cascade({CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "GOODS_BRAND_ID")
    private Set<GoodsModifier> goodsModifierSet = new HashSet<GoodsModifier>();
	
	/**所属机构id*/
	@Column(name= "BELONGS_ID")
	private String belongsId;

	/**所属机构名称*/
	@Column(name= "BELONGS_NAME")
	private String belongsName;

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
    @BatchSize(size = 15)
    private User updateUser;
    
    /**修改时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_TIME")
    private Date updateTime;
    
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
    
    /**生效时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VALID_TIME")
    private Date validTime;
    
    /**失效时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INVALID_TIME")
    private Date invalidTime;
    
    /**有效商品数量*/
    @Formula("(select nvl(count(o.GOODS_ID), 0) from GOODS o where o.GOODS_BRAND_ID=GOODS_BRAND_ID and o.SELL_STATUS='01' and o.MANGER_STATUS='01')")
    private BigDecimal goodsTotal;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getAuditStatusCN() {
		return GoodsEnum.getAuditStatusCN(this.getAuditStatus());
	}
	
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getShortSpellName() {
		return shortSpellName;
	}

	public void setShortSpellName(String shortSpellName) {
		this.shortSpellName = shortSpellName;
	}

	public String getMainLogo() {
		return mainLogo;
	}

	public void setMainLogo(String mainLogo) {
		this.mainLogo = mainLogo;
	}

	public String getSellStatusCN(){
		return GoodsEnum.getSellStatusCN(this.getSellStatus());
	}
	
	public String getSellStatus() {
		return sellStatus;
	}

	public void setSellStatus(String sellStatus) {
		this.sellStatus = sellStatus;
	}
	public String getUseStatusCN() {
		return GoodsEnum.getUseStatusCN(this.getUseStatus());
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getEcdemicCN(){
		return ecdemic?"是":"否";
	}
	
	public Boolean getEcdemic() {
		return ecdemic;
	}

	public void setEcdemic(Boolean ecdemic) {
		this.ecdemic = ecdemic;
	}

	public String getForeignerCN(){
		return foreigner?"是":"否";
	}
	
	public Boolean getForeigner() {
		return foreigner;
	}

	public void setForeigner(Boolean foreigner) {
		this.foreigner = foreigner;
	}
	
	public String getBelongsId() {
		return belongsId;
	}

	public void setBelongsId(String belongsId) {
		this.belongsId = belongsId;
	}

	public String getBelongsName() {
		return belongsName;
	}

	public void setBelongsName(String belongsName) {
		this.belongsName = belongsName;
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

	
	public Set<GoodsClass> getGoodsClasses() {
		return goodsClasses;
	}

	public void setGoodsClasses(Set<GoodsClass> goodsClasses) {
		this.goodsClasses = goodsClasses;
	}

	public Set<GoodsModifier> getGoodsModifierSet() {
		return this.goodsModifierSet;
	}

	public void setGoodsModifierSet(Set<GoodsModifier> goodsModifierSet) {
		this.goodsModifierSet = goodsModifierSet;
	}

	public String getCurrentId() {
		return currentId;
	}

	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}

	public String getGoodsClassNames() {
		return goodsClassNames;
	}

	public void setGoodsClassNames(String goodsClassNames) {
		this.goodsClassNames = goodsClassNames;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
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

	public BigDecimal getGoodsTotal() {
		return goodsTotal;
	}

	public void setGoodsTotal(BigDecimal goodsTotal) {
		this.goodsTotal = goodsTotal;
	}
	
	
}