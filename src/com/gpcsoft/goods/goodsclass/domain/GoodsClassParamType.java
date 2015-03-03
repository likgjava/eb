package com.gpcsoft.goods.goodsclass.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.tree.BaseTree;
import com.gpcsoft.core.tree.TreeProperty;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>参数类型配置</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-27 上午11:36:11 by yucy    					                            
  *  <br/>Modified Date:  2010-7-27 上午11:36:11 by yucy                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="PARAM_TYPE", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("01")
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Cache(region = "com.gpcsoft.goods.goodsclass.domain.GoodsClassParamType", usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "GOODS_CLASS_PARAM")
@TreeProperty(topIcon="book_titel.gif", nodeIcon="book_titel.gif", title="商品分类参数", text="name", tip="goodsclassparamType",nodeIconExtend="icon")
public class GoodsClassParamType implements GpcBaseObject ,IPropertyCUserTime,BaseTree<GoodsClassParamType> {
    /** serialVersionUID */
    private static final long serialVersionUID = 5903283622314542764L;

    @Id
    @Column(name = "GOODS_CLASS_PARAM_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**参数分类编号*/
    @Column(name = "PARAM_CODE", length = 100)
    private String paramCode;
    
    /**参数分类名称*/
    @Column(name = "PARAM_NAME", length = 100)
    private String paramName;
    
    /** 类型: 01.基类 02.分类参数*/
	@Column(name = "PARAM_TYPE", insertable=false, updatable=false)
	private String paramType; 
    
    /**参数分类说明*/
    @Column(name = "PARAM_DESC", length = 300)
    private String paramDesc;
    
    /**排序号*/
    @Column(name = "SORT", length = 9)
    private Integer sort;
    
    /**商品分类*/
    @ManyToOne(fetch=FetchType.LAZY)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="GOODS_CLASS_ID")
    @BatchSize(size = 15)
    private GoodsClass goodsClass;

    /**父级分类*/
    @ManyToOne(fetch = FetchType.LAZY)
    @BatchSize(size = 15)
    @JoinColumn(name = "PARAM_PARENT_ID")
    private GoodsClassParamType parent;
    
    /**是否叶子节点*/
    @Column(name = "PARAM_IS_LEAF", length = 1)
    private Boolean isLeaf;
    
    /**创建人*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATOR_ID", updatable = false)	 
	@BatchSize(size = 15)
    private User createUser;
    
	/**创建时间*/
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
    
    @Column(name = "TREE_PATH", length = 100)
	private String path; // 路径

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getParamDesc() {
		return paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public GoodsClass getGoodsClass() {
		return goodsClass;
	}

	public void setGoodsClass(GoodsClass goodsClass) {
		this.goodsClass = goodsClass;
	}

	public GoodsClassParamType getParent() {
		return parent;
	}

	public void setParent(GoodsClassParamType parent) {
		this.parent = parent;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Set<GoodsClassParamType> getChildren() {
		return null;
	}
    
	
	
}