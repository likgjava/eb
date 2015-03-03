package com.gpcsoft.smallscale.pointmall.domain;

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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.tree.BaseTree;
import com.gpcsoft.core.tree.TreeProperty;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>礼品系列管理</strong>            		
 *  
 *  @gpcsoft.package packageDir="com.gpcsoft.smallscale.pointmall"
 *  @gpcsoft.page domain="pointmall" project="smallscale"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="礼品系列管理"
 */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "SM_GIFT_SERIES")
@TreeProperty(topIcon="tombs.gif", nodeIcon="_treelogo.gif", title="礼品系列", text="name", parent="parent")
public class GiftSeries implements GpcBaseObject, IPropertyCUserTime,BaseTree<GiftSeries> {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**主键ID */
	@Id
	@Column(name="SERIES_ID", length=50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name="paymentableGenerator",strategy="uuid.hex")
	private String objId;
	
	/**系列名称  */
	@Column(name="SERIES_NAME", length=100)
	private String name;
	
	/**系列编号  */
	@Column(name="SERIES_CODE", length=50)
	private String seriesCode;
	
	/**上级系列id  */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="PARENT_ID")  
    @BatchSize(size = 15)
	private GiftSeries parent;
	
	/**是否启用(1:启用,0：禁用)  */
	@Column(name="IS_USED")
	private boolean isUsed;
	
	/**是否叶子节点(1:是,0：否)  */
	@Column(name="IS_LEAF")
	private boolean isLeaf;
	
    /**排序号*/
    @Column(name = "SORT", length = 9)
	private Integer sort;
	
	 /**创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR_ID")  
    @BatchSize(size = 15)
    private User createUser;
	
	/**创建时间	 */
	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_TIME")
	private Date createTime;
	
    /**系列集合*/
	@LazyCollection(value=LazyCollectionOption.EXTRA)
    @OneToMany
    @OrderBy(value = "createTime asc")
	@JoinColumn(name = "PARENT_ID") 
    private Set<GiftSeries> children = new HashSet<GiftSeries>();

	/**
	 * gpcsoft.property title="记录号"
	 */
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/**
	 * gpcsoft.property title="系列名称"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * gpcsoft.property title="系列编码"
	 */
	public String getSeriesCode() {
		return seriesCode;
	}

	public void setSeriesCode(String seriesCode) {
		this.seriesCode = seriesCode;
	}

	/**
	 * gpcsoft.property title="上级系列"
	 */
	public GiftSeries getParent() {
		return parent;
	}

	public void setParent(GiftSeries parent) {
		this.parent = parent;
	}

	/**
	 * gpcsoft.property title="是否启用"
	 */
	public boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 * gpcsoft.property title="是否子节点"
	 */
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf =isLeaf;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	/** sort */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @gpcsoft.property title="sort"
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * gpcsoft.property title="创建人"
	 */
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	/**
	 * gpcsoft.property title="创建时间"
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Set<GiftSeries> getChildren() {
		return children;
	}
	
	public void setChildren(Set<GiftSeries> children) {
		this.children = children;
	}
}
