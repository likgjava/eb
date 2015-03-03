package com.gpcsoft.goods.goods.domain;
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
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParam;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>商品参数</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-27 下午02:23:13 by yucy    					                            
  *  <br/>Modified Date:  2010-7-27 下午02:23:13 by yucy                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "GOODS_PARAM")
public class GoodsParam implements GpcBaseObject ,IPropertyCUserTime{

    /** serialVersionUID */
	private static final long serialVersionUID = 4988938569240832681L;

	/**记录号*/
    @Id
    @Column(name = "GOODS_PARAM_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**商品分类参数*/
    @ManyToOne(fetch=FetchType.LAZY)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="GOODS_CLASS_PARAM_ID")
    @BatchSize(size = 15)
    public GoodsClassParam goodsClassParam;
    
    /**商品*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="GOODS_ID")
    @BatchSize(size = 15)
    private Goods goods;
    
    /**参数名称*/
    @Column(name = "PARAM_NAME", length = 100)
    private String paramName;
    
    /**显示序号*/
    @Column(name = "SORT_NO", length = 9)
    private Integer sortNo;
    
    /**分类名称*/
    @Column(name = "TYPE_NAME", length = 100)
    private String typeName;
    
    /**参数值*/
    @Column(name = "PARAM_VALUE", length = 100)
    private String paramValue;
    
    /**是否商品自定义属性*/
    @Column(name = "IS_CUSTOM", length = 5)
    private Boolean isCustom;
    
    /**参数值计量单位*/
    @Column(name = "PARAM_UNIT", length = 100)
    private String paramUnit;

    /**创建人*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})//级联类型
    @JoinColumn(name="CREATOR_ID")//关联的外键  
    @BatchSize(size = 15)//批量抓取
    private User createUser;
    
    /**创建时间*/
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**修改人*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="MODIFIER_ID")
    @BatchSize(size = 15)
    private User modifier;

    /**修改时间*/
    @Temporal(TemporalType.DATE)
    @Column(name = "MODIFY_TIME")
    private Date modifyTime;

    /**可选配件*/
    @LazyCollection(value=LazyCollectionOption.EXTRA)
    @OneToMany()
    @JoinColumn(name = "GOODS_PARAM_ID", updatable = false) 
    @Cascade({CascadeType.DELETE_ORPHAN,CascadeType.SAVE_UPDATE})
    public Set<GoodsOptionalFitting> goodsOptionalFittingSet = new HashSet<GoodsOptionalFitting>();

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public Boolean getIsCustom() {
		return isCustom;
	}

	public void setIsCustom(Boolean isCustom) {
		this.isCustom = isCustom;
	}

	public String getParamUnit() {
		return paramUnit;
	}

	public void setParamUnit(String paramUnit) {
		this.paramUnit = paramUnit;
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

	public User getModifier() {
		return modifier;
	}

	public void setModifier(User modifier) {
		this.modifier = modifier;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public GoodsClassParam getGoodsClassParam() {
		return goodsClassParam;
	}

	public void setGoodsClassParam(GoodsClassParam goodsClassParam) {
		this.goodsClassParam = goodsClassParam;
	}

	public Set<GoodsOptionalFitting> getGoodsOptionalFittingSet() {
		return goodsOptionalFittingSet;
	}

	public void setGoodsOptionalFittingSet(
			Set<GoodsOptionalFitting> goodsOptionalFittingSet) {
		this.goodsOptionalFittingSet = goodsOptionalFittingSet;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
    
    
    
}