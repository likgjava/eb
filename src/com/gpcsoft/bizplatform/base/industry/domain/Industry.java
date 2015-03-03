package com.gpcsoft.bizplatform.base.industry.domain;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.core.tree.BaseTree;
import com.gpcsoft.core.tree.TreeProperty;


/**
 * Comments: <strong>District</strong>行业分类 <br/>
 * 继承<code>BaseTree</code> <br/>
 * <br/>
 * Project: srplatform <br/>
 * Module ID: <br/>
 * 
 * Create Date：2009-10-23 <br/>
 * Modified By：liangxj <br/>
 * CopyRright (c)2008-xxxx: 珠海政采软件技术有限公司 <br/>
 * Modified Date: 2009-10-23 修改为注解的方式
 * 
 * @author liangxj
 * @since 0.4
 * @version: 0.5
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.bizplatform.base.industry"
 * @gpcsoft.page domain="industry"
 * @gpcsoft.domain
 * @gpcsoft.title value="行业分类"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "BASE_INDUSTRY")
@TreeProperty(topIcon="tombs.gif", nodeIcon="book_titel.gif", title="行业分类", text="name")
@OrderProperty(property="sort", flag="false")
public class Industry implements GpcBaseObject, BaseTree<Industry> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "INDUSTRY_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键

	@Column(name = "INDUSTRY_CODE", length = 40)
	private String code; // 行政区域代码

	@Column(name = "INDUSTRY_NAME", length = 20)
	private String name; // 地区名称

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INDUSTRY_PARENT_ID")
	private Industry parent; // 上级行政区域
	
	@Column(name = "IND_LEVEL")
	private Short level; // 层级
	
	/**
	 * 排序
	 */
	@Column(name = "INDUSTRY_SORT_NO")
	private Long sort;
	
	@Column(name="CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	/**
     * 拼音缩写
     */
    @Column(name = "SHORT_SPELL_NAME", length = 50, nullable=true)
    private String shortSpellName;
    
    /**
     * 是否叶子结点
     */
    @Column(name = "INDUSTRY_IS_LEAF", length = 5, nullable=false)
    private Boolean isLeaf;
	
	/** 下级行业 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "INDUSTRY_PARENT_ID") 
	@OrderBy("sort asc")
	private Set<Industry> children = new HashSet<Industry>(0);
	
	/********************************GET和SET方法**********************************************/
	/**
	 * @gpcsoft.property title="创建时间"
	 */
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 实现目录树接口BaseTree
	 */
	public String getTreeName() {
		return "行业分类";
	}

	public String getTreeParent() {
		return "parent";
	}

	public String getTreeText() {
		return "name";
	}

	public String getTreeObjId() {
		return this.objId;
	}

	/******************************** GET和SET方法 **********************************************/

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
 
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** parent */
	public void setParent(Industry parent) {
		this.parent = parent;
	}
	
	public Industry getParent() {
		return parent;
	}
	
	
	public Short getLevel() {
		return level;
	}
	public void setLevel(Short level) {
		this.level = level;
	}
	
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	
	public Set<Industry> getChildren() {
		return children;
	}
	public void setChildren(Set<Industry> children) {
		this.children = children;
	}
	
	public String getShortSpellName() {
		return shortSpellName;
	}
	public void setShortSpellName(String shortSpellName) {
		this.shortSpellName = shortSpellName;
	}
	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public Boolean getIsLeaf() {
		return isLeaf;
	}
	
	

}