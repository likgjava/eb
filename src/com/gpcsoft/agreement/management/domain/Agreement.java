package com.gpcsoft.agreement.management.domain;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>协议</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 协议供货-协议     		
  *  <br/>Create Date：2010-4-16 下午03:16:35 by yucy    					                            
  *  <br/>Modified Date:  2010-4-16 下午03:16:35 by yucy                                   
  *  
  *   @since 0.5
  *   @version: 0.5 
  *   
  *  @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.management"
  *  @gpcsoft.page domain="management" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="协议"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_PURCHASE_AGREEMENT")
public class Agreement implements GpcBaseObject {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "AGREEMENT_ID", unique = true, nullable = false, length = 50)
	private String objId;
	
	/**协议区间id*/
	@Column(name = "AREA_IDS")
	private String areaIds;
	
	/**协议区间name*/
	@Column(name = "AREA_NAMES")
	private String areaNames;
	
	/**协议期间*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERIOD_ID")
	private Period period;
	
	/**协议编号*/
	@Column(name = "AGREEMENT_NO", length = 50)
	private String agreementNo;
	
	/**协议名称*/
	@Column(name="AGREEMENT_NAME", length = 100)
	private String name;
	
	/**协议甲方*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_ID")
	private OrgInfo org;
	
	/**授权供货商*/
	@Formula("(Select count(Distinct ps.Supplyer_Id) From Eps_Agree_Purchase_Agreement a, Eps_Agreement_Purchase_Second ps Where ps.Agreement_Id = a.Agreement_Id And a.Agreement_Id = AGREEMENT_ID)")
	private Integer authorizeSupplier;
	
	/**授权分类*/
	@Formula("(Select Count(Distinct Pg.Goodsclass_Id || Pg.Brand_Id)From Eps_Agree_Purchase_Agreement a, Eps_Agree_Purchase_Goodsclass Pg, Eps_Agreement_Purchase_Second Cd Where Pg.Second_Agreement_Id = Cd.Second_Agreement_Id And Cd.Agreement_Id = a.Agreement_Id And a.Agreement_Id = AGREEMENT_ID)")
	private Integer authorizeClass;
	
	/**分类数*/
	@Formula("(Select Count(Distinct Pg.Goodsclass_Id || Pg.Brand_Id) From Eps_Agree_Purchase_Agreement a, Eps_Agree_Purchase_Goodsclass Pg Where Pg.Agreement_Id = a.Agreement_Id and a.Agreement_Id = AGREEMENT_ID)")
	private Integer unAuthorizeClass;
	
	/**授权商品*/
	@Formula("(Select Count(Distinct Pg.Goods_Id) From Eps_Agree_Purchase_Goods Pg, Eps_Agree_Purchase_Agreement a, Eps_Agreement_Purchase_Second Cd Where Pg.Second_Agreement_Id = Cd.Second_Agreement_Id And Cd.Agreement_Id = a.Agreement_Id And a.Agreement_Id = AGREEMENT_ID)")
	private Integer authorizeGoods;
	
	/**商品数*/
	@Formula("(Select count(Distinct pg.Goods_Id) From Eps_Agree_Purchase_Goods pg, Eps_Agree_Purchase_Agreement a Where a.Agreement_Id = pg.Agreement_Id And a.Agreement_Id = AGREEMENT_ID)")
	private Integer unAuthorizeGoods;
	
	
	/**经销商*/	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPLYER_ID")
	private OrgInfo supplier;
	

	/**协议文件*/
	@Column(name = "AGREEMENT_FILE", length = 50)
	private String agreementFile;
	
	/**创建时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRE_TIME", length = 7)
	private Date creTime;
	
	/**创建人*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CRE_USER_ID")
	private User creUser;
	
	/**备注*/
	@Column(name = "MEMO", length = 200)
	private String memo;
	
	/**状态*/
	@Column(name = "USE_STATUS", length = 2)
	private String useStatus;
	
	/**修改时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_TIME", length = 7)
	private Date modifyTime;
	
	/** 协议下协议商品分类 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "AGREEMENT_ID") 
	private Set<AgreementGoodsclass> agreementGoodsclass = new HashSet<AgreementGoodsclass>();
	
	/** 协议下协议协议单品 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "AGREEMENT_ID") 
	private Set<AgreementGoods> agreementGoods = new HashSet<AgreementGoods>();
	
	/** 协议下二级协议 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "AGREEMENT_ID") 
	private Set<AgreementSecond> agreementSecond = new HashSet<AgreementSecond>();
	
	public void setObjId(String objId){
		this.objId = objId;
	}
	
	public String getObjId() {
		return this.objId;
	}

	public String getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(String areaIds) {
		this.areaIds = areaIds;
	}

	/** @gpcsoft.property title="协议区间"  */
	public String getAreaNames() {
		return areaNames;
	}

	public void setAreaNames(String areaNames) {
		this.areaNames = areaNames;
	}
	
	/** @gpcsoft.property title="协议期间"  */
	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}
	/** @gpcsoft.property title="协议编号"  */
	public String getAgreementNo() {
		return agreementNo;
	}

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	
	/** @gpcsoft.property title="协议名称"  */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** @gpcsoft.property title="协议甲方"  */
	public OrgInfo getOrg() {
		return org;
	}

	public void setOrg(OrgInfo org) {
		this.org = org;
	}
	/** @gpcsoft.property title="经销商"  */
	public OrgInfo getSupplier() {
		return supplier;
	}

	public void setSupplier(OrgInfo supplier) {
		this.supplier = supplier;
	}
	/** @gpcsoft.property title="协议文件"  */
	public String getAgreementFile() {
		return agreementFile;
	}

	public void setAgreementFile(String agreementFile) {
		this.agreementFile = agreementFile;
	}
	/** @gpcsoft.property title="创建时间"  */
	public Date getCreTime() {
		return creTime;
	}

	public void setCreTime(Date creTime) {
		this.creTime = creTime;
	}
	/** @gpcsoft.property title="创建人"  */
	public User getCreUser() {
		return creUser;
	}

	public void setCreUser(User creUser) {
		this.creUser = creUser;
	}
	/** @gpcsoft.property title="备注"  */
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	/** @gpcsoft.property title="状态"  */
	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	/** @gpcsoft.property title="修改时间"  */
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getCreateTime() {
		return this.creTime;
	}

	public void setCreateTime(Date createTime) {
		this.creTime = createTime;
	}

	
	public Integer getAuthorizeSupplier() {
		return authorizeSupplier;
	}

	public void setAuthorizeSupplier(Integer authorizeSupplier) {
		this.authorizeSupplier = authorizeSupplier;
	}

	public Integer getAuthorizeClass() {
		return authorizeClass;
	}

	public void setAuthorizeClass(Integer authorizeClass) {
		this.authorizeClass = authorizeClass;
	}

	public Integer getUnAuthorizeClass() {
		return unAuthorizeClass;
	}

	public void setUnAuthorizeClass(Integer unAuthorizeClass) {
		this.unAuthorizeClass = unAuthorizeClass;
	}

	public Integer getAuthorizeGoods() {
		return authorizeGoods;
	}

	public void setAuthorizeGoods(Integer authorizeGoods) {
		this.authorizeGoods = authorizeGoods;
	}

	public Integer getUnAuthorizeGoods() {
		return unAuthorizeGoods;
	}

	public void setUnAuthorizeGoods(Integer unAuthorizeGoods) {
		this.unAuthorizeGoods = unAuthorizeGoods;
	}

	public Set<AgreementGoodsclass> getAgreementGoodsclass() {
		return agreementGoodsclass;
	}

	public void setAgreementGoodsclass(Set<AgreementGoodsclass> agreementGoodsclass) {
		this.agreementGoodsclass = agreementGoodsclass;
	}

	public Set<AgreementGoods> getAgreementGoods() {
		return agreementGoods;
	}

	public void setAgreementGoods(Set<AgreementGoods> agreementGoods) {
		this.agreementGoods = agreementGoods;
	}

	public Set<AgreementSecond> getAgreementSecond() {
		return agreementSecond;
	}

	public void setAgreementSecond(Set<AgreementSecond> agreementSecond) {
		this.agreementSecond = agreementSecond;
	}

	
	
	
}