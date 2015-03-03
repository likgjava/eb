package com.gpcsoft.bizplatform.base.purcategory.domain;

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
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.tree.BaseTree;
import com.gpcsoft.core.tree.TreeProperty;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * 采购品目分类,国家财政部门颁发的采购品目分类
 * @gpcsoft.package packageDir="com.gpcsoft.eps.base.purcategory"
 * @gpcsoft.page domain="base/purcategory" project="base/purcategory"
 * @gpcsoft.domain
 * @author liujf
 * @version 1.0
 * @created 13-一月-2010 13:12:40
 */
@SuppressWarnings("unchecked")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "PURCATALOG_CATEGORY")
//注意TreeProperty这个注解必须
//分别代表：顶部图标、节点图标、树标题、树显示文本在domain字的private属性，  其中父节点属性默认是  parent, 如果改变需写 parent=xxx
@TreeProperty(topIcon="tombs.gif", nodeIcon="_treelogo.gif", title="采购品目", text="categoryName", parent="parent")
public class PurCategory implements GpcBaseObject, BaseTree,IPropertyCUserTime,IPropertyUUserTime {
	private static final long serialVersionUID = -3326458115347153438L;

	/** 主键 */
	@Id
	@Column(name = "ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
	private String objId;
    
    /**
     * 父分类
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @BatchSize(size = 15)
    @JoinColumn(name="PARENT_ID")       
    private PurCategory parent;
	
	/**
	 * 序号
	 */
	@Column(name = "category_Code", length = 50, nullable = false)
	private String categoryCode;
	
	/**
	 * 品目名称
	 */
	@Column(name = "category_Name", length = 100, nullable = false)
	private String categoryName;
	
	/**
	 * 备注
	 */
	@Column(name = "remarks", length = 300)
	private String remarks;
	
	/**
	 * 发布日期
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "release_Date")
	private Date releaseDate;
	
	/**
	 * 发布状态
	 */
	@Column(name = "release_Status", length = 50)
	private String releaseStatus;


	@Transient
	public String isHasChild;
    
    /**
     * 是否叶子结点
     */
    @Column(name = "PURCATEGORY_IS_LEAF", length = 5, nullable=false)
    private Boolean isLeaf;

    /**
     * 拼音缩写
     */
    @Column(name = "SHORT_SPELL_NAME", length = 50, nullable=true)
    private String shortSpellName;
    
    /**
	 * 排序
	 */
	@Column(name = "CATEGORY_SORT")
	private Long sort;
    
    /** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="create_User")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User createUser;
	
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_Date", length = 7)
	private Date createTime;
	
	/** 修改人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="update_User")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User updateUser;
	
	/** 修改时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_Date", length = 7)
	private Date updateTime;
	
	/** 下级品目 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "PARENT_ID") 
	@OrderBy("categoryCode asc")
	private Set<PurCategory> children = new HashSet<PurCategory>(0);
    
	public String getIsHasChild() {
        return isHasChild;
    }

    public void setIsHasChild(String isHasChild) {
        this.isHasChild = isHasChild;
    }

	public String getObjId() {
		return objId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getRemarks() {
		return remarks;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public String getReleaseStatus() {
		return releaseStatus;
	}


    public PurCategory getParent() {
		return parent;
	}

	public void setParent(PurCategory parent) {
		this.parent = parent;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
	
    public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Boolean getIsLeaf() {
        return this.isLeaf;
    }
	
	public String getShortSpellName() {
		return shortSpellName;
	}

	public void setShortSpellName(String shortSpellName) {
		this.shortSpellName = shortSpellName;
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

	public Set<PurCategory> getChildren() {
		return children;
	}

	public void setChildren(Set<PurCategory> children) {
		this.children = children;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
	
}