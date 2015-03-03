package com.gpcsoft.goods.goodsclass.domain;

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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.core.tree.BaseTree;
import com.gpcsoft.core.tree.TreeProperty;
import com.gpcsoft.goods.goods.domain.GoodsModifier;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>商品分类</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-27 上午10:06:32 by yucy    					                            
  *  <br/>Modified Date:  2010-7-27 上午10:06:32 by yucy                                   
  *<p>@since 0.5
  *   @version: 0.5 
  *   
  * @gpcsoft.package packageDir="com.gpcsoft.goods.goodsclass"
  * @gpcsoft.page domain="goodsclass"
  * @hibernate.class table="GOODS_CLASS"
  * @author Administrator
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "GOODS_CLASS")
@TreeProperty(topIcon="tombs.gif", nodeIcon="_treelogo.gif", title="商品分类", text="goodsClassName", parent="parentClazz")
@OrderProperty(property="sort", flag="false")
public class GoodsClass implements GpcBaseObject, BaseTree<GoodsClass>,IPropertyCUserTime {

	/** serialVersionUID */
	private static final long serialVersionUID = 1794222123195539971L;

	@Id
    @Column(name = "GOODS_CLASS_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
    private String objId;
    
    /**父分类*/
    @ManyToOne(fetch=FetchType.LAZY)
    @BatchSize(size = 15)
    @JoinColumn(name="PARENT_CLASS_ID")
    private GoodsClass parentClazz;
    
    /**采购品目(中间表)*/
	@LazyCollection(value=LazyCollectionOption.EXTRA)
    @OneToMany
	@Cascade({CascadeType.SAVE_UPDATE,CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "GOODS_CLASS_ID") 
    private Set<GoodsClassCategory> goodsClassCategorySet = new HashSet<GoodsClassCategory>();
    
    /**分类编号*/
    @Column(name = "GOODS_CLASS_CODE", length = 100)
    private String goodsClassCode;
    
    /**分类名称 */
    @Column(name = "GOODS_CLASS_NAME", length = 100)
    private String goodsClassName;
    
    /**参数录入方式:分项录入、整体录入，两者任选*/
    @Column(name = "PARAM_INPUT_TYPE", length = 100)
    private String paramInputType;
    
    /**备注*/
    @Column(name = "REMARKS", length = 2000)
    private String remarks;

	/**创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATOR_ID", updatable = false)	 
	@BatchSize(size = 15)
    private User createUser;
    
	/**创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**修改人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="MODIFIER_ID", updatable = false) 
	@BatchSize(size = 15)
    private User modifier;

	/**修改时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_TIME")
    private Date modifyTime;
    
    /**拼音简码规则*/
    @Column(name = "SHORT_SPELL_NAME", length = 50)
    private String shortSpellName;
    
    /**排序字段*/
    @Column(name = "SORT")
    private Long sort;
    
    /**是否叶子结点*/
    @Column(name = "GOODS_CLASS_IS_LEAF", length = 5, nullable = false)
    private Boolean isLeaf;
    
    /**商品维护商*/
    @OneToMany(mappedBy = "goodsClass")
    @Cascade({CascadeType.ALL})
    private Set<GoodsModifier> goodsModifierSet = new HashSet<GoodsModifier>();
    
    /**下级节点*/
    @OneToMany(mappedBy="parentClazz" )
    @OrderBy(value = "goodsClassName asc")
    private Set<GoodsClass> children = new HashSet<GoodsClass>();

	public String getObjId() {
		return this.objId;
	}
	
	public void setObjId(String objId){
		this.objId = objId;
	}
	
	public GoodsClass getParentClazz() {
		return parentClazz;
	}

	public void setParentClazz(GoodsClass parentClazz) {
		this.parentClazz = parentClazz;
	}

	public Set<GoodsClassCategory> getGoodsClassCategorySet() {
		return goodsClassCategorySet;
	}

	public void setGoodsClassCategorySet(
			Set<GoodsClassCategory> goodsClassCategorySet) {
		this.goodsClassCategorySet = goodsClassCategorySet;
	}

    /**
     * @gpcsoft.property title="分类编号"
     */
	public String getGoodsClassCode() {
		return goodsClassCode;
	}

	public void setGoodsClassCode(String goodsClassCode) {
		this.goodsClassCode = goodsClassCode;
	}

    /** 
     * @gpcsoft.property title="分类名称"
     */
	public String getGoodsClassName() {
		return goodsClassName;
	}

	public void setGoodsClassName(String goodsClassName) {
		this.goodsClassName = goodsClassName;
	}

    /** 
     * @gpcsoft.property title="参数录入方式" 
     */
	public String getParamInputType() {
		return paramInputType;
	}

	public void setParamInputType(String paramInputType) {
		this.paramInputType = paramInputType;
	}
	
    /** 
     * @gpcsoft.property title="备注" 
     */
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getShortSpellName() {
		return shortSpellName;
	}

	public void setShortSpellName(String shortSpellName) {
		this.shortSpellName = shortSpellName;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public Set<GoodsModifier> getGoodsModifierSet() {
		return goodsModifierSet;
	}

	public void setGoodsModifierSet(Set<GoodsModifier> goodsModifierSet) {
		this.goodsModifierSet = goodsModifierSet;
	}

	public Set<GoodsClass> getChildren() {
		return children;
	}

	public void setChildren(Set<GoodsClass> children) {
		this.children = children;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Boolean getIsLeaf() {
		return this.isLeaf;
	}

	public Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public User getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}   
    
}