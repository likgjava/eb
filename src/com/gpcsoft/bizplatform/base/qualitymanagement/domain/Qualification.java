package com.gpcsoft.bizplatform.base.qualitymanagement.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.core.tree.BaseTree;
import com.gpcsoft.core.tree.TreeProperty;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>Qualification</strong>            		
 *	 <br/>	资质        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   bizplatform                    					          
 *  <br/>Module ID: 公共服务平台     		
 *  <br/>Create Date：2010-7-27 下午02:33:28 by guoyr    					                            
 *  <br/>Modified Date:  2010-7-27 下午02:33:28 by guoyr                                  
 *<p>@since 0.5
 *   @version: 0.5 
 *  
 *  
 * @gpcsoft.package packageDir="com.gpcsoft.bizplatform.base.qualitymanagement"
 * @gpcsoft.page domain="qualitymanagement" project="base"
 * @gpcsoft.domain
 * @gpcsoft.title value="资质"
 */ 

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE", discriminatorType = DiscriminatorType.STRING)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Cache(region = "com.gpcsoft.bizplatform.base.qualitymanagement.domain.Qualification", usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "BASE_QUALIFICATION")
@TreeProperty(topIcon="book_titel.gif", nodeIcon="book_titel.gif", title="资质", text="qualityName", tip="qualification",nodeIconExtend="icon")
@OrderProperty(property="sort", flag="false")
public class Qualification implements GpcBaseObject, BaseTree<Qualification>, IPropertyCUserTime ,IPropertyUUserTime{

	private static final long serialVersionUID = -1096034961431870495L;
	
	/** 主键 */
	@Id
	@Column(name = "QUALITY_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	/**
	 * 资质编号
	 */
	@NotNull
	@Length(max=50, min=2)  
	@Column(name = "QUALITY_CODE")
	private String qualityCode;
	
	/**
	 * 资质名称
	 */
	@NotNull
	@Length(max=100, min=2)  
	@Column(name = "QUALITY_NAME")
	private String qualityName;
	
	@Column(name = "SHORT_SPELL_NAME", length = 50)
	private String shortSpellName;//名称拼音缩写
	 
	/**
	 * 资质排序
	 */
	@Column(name = "SORT")
	private Integer sort;
	
	/**
	 * 状态（00临时,01正式）
	 */
	@Column(name = "USE_STATUS", length = 2)
	private String useStatus;
	
	/**
	 * 类型（00资质分类，01,资质定义，02,资质参数）
	 */
	@Column(name = "TYPE", insertable=false, updatable=false)
	private String type;

	/**
	 * 上级资质
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional=true)
	@BatchSize(size = 15)
	@JoinColumn(name = "PARENT_ID")
	private Qualification parent; 
	
	/**
	 * 是否叶子节点
	 */
	@Column(name = "IS_LEAF", length = 1)
	private Boolean isLeaf; 
	
	/**
	 * 是否显示
	 */
	@Column(name = "IS_DISPLAY", length = 1)
	private Boolean isDisplay; 
	
	/**
	 * 是否有子级
	 */
	@SuppressWarnings("unused")
	@Transient
	private Boolean isHasSub; 
	
	@Column(name = "TREE_PATH", length = 100)
	private String path; // 路径
	
	/**
	 * 创建人
	 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="CREATOR")
    @BatchSize(size = 15)
    private User createUser;
    
    /**
	 * 创建时间
	 */
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TIME")
    private Date createTime;
	
    /**
	 * 修改人
	 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="MODIFIER")
    @BatchSize(size = 15)
    private User updateUser;
	
    /**
	 * 修改时间
	 */
	@Column(name="MODIFY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.ALL})
	@JoinColumn(name = "PARENT_ID",updatable=false) 
	@OrderBy("sort asc")
	private Set<Qualification> subQualification = new HashSet<Qualification>(0);
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getQualityCode() {
		return qualityCode;
	}

	public void setQualityCode(String qualityCode) {
		this.qualityCode = qualityCode;
	}

	public String getQualityName() {
		return qualityName;
	}

	public void setQualityName(String qualityName) {
		this.qualityName = qualityName;
	}

	public String getShortSpellName() {
		return shortSpellName;
	}

	public void setShortSpellName(String shortSpellName) {
		this.shortSpellName = shortSpellName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public Qualification getParent() {
		return parent;
	}

	public void setParent(Qualification parent) {
		this.parent = parent;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
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

	public Set<Qualification> getSubQualification() {
		return subQualification;
	}

	public void setSubQualification(Set<Qualification> subQualification) {
		this.subQualification = subQualification;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Set<Qualification> getChildren() {
		return this.subQualification;
	}

	public Boolean getIsHasSub() {
		return isLeaf;
	}

	public void setIsHasSub(Boolean isHasSub) {
		this.isHasSub = isLeaf;
	}

	public Boolean getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Boolean isDisplay) {
		this.isDisplay = isDisplay;
	}
	
}