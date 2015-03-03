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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.tree.BaseTree;
import com.gpcsoft.core.tree.TreeProperty;

/** 
  *  Comments: <strong>区间</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   协议供货-区间                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-4-16 下午04:32:06 by yucy    					                            
  *  <br/>Modified Date:  2010-4-16 下午04:32:06 by yucy                                   
  *  
  *   @since 0.5
  *   @version: 0.5 
  *   
  *  @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.management"
  *  @gpcsoft.page domain="management" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="区间"
  */ 
@SuppressWarnings("unchecked")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREEMENT_AREA")
@TreeProperty(topIcon="tombs.gif", nodeIcon="_treelogo.gif", title="协议区间", text="areaName")
public class Area implements GpcBaseObject , BaseTree{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "AREA_ID", unique = true, nullable = false, length = 50)
	private String objId;
	
	/**区间名称*/
	@Column(name = "AREA_NAME", length = 100)
	private String areaName;
	
	/**行政区域编码集合*/
	@Column(name = "AREA_CODES", length = 1000)
	private String areaCodes;
	
	/**行政区域名称集合*/
	@Column(name = "AREA_NAMES", length = 1000)
	private String areaNames;
	
	/**上级区域*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	private Area parent;
	
	/** 区间下子区间 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "PARENT_ID") 
	private Set<Area> areas = new HashSet<Area>();
	
	/** 排序字段 */
	@Column(name = "AREA_SORT")
	private Long sort;
	
	@Column(name = "AREA_IS_LEAF")
    private Boolean isLeaf;
	
	/**是否有效 0无效 1有效 2删除  */
	@Column(name = "AREA_IS_VALID")
    private Character isValid;
	
	/**备注*/
	@Column(name = "MEMO", length = 200)
	private String memo;

	public void setObjId(String objId){
		this.objId = objId;
	}
	
	public String getObjId() {
		return this.objId;
	}

	/** @gpcsoft.property title="区间名称"  */
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/** @gpcsoft.property title="行政区域编码集合" */
	public String getAreaCodes() {
		return areaCodes;
	}

	public void setAreaCodes(String areaCodes) {
		this.areaCodes = areaCodes;
	}
	/** @gpcsoft.property title="行政区域名称集合" */
	public String getAreaNames() {
		return areaNames;
	}

	public void setAreaNames(String areaNames) {
		this.areaNames = areaNames;
	}
	/** @gpcsoft.property title="上级区域" */
	public Area getParent() {
		return parent;
	}

	public void setParent(Area parent) {
		this.parent = parent;
	}
	/** @gpcsoft.property title="备注" */
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set<Area> getAreas() {
		return areas;
	}

	public void setAreas(Set<Area> areas) {
		this.areas = areas;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	public Boolean getIsLeaf() {
		return this.isLeaf;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCreateTime(Date createTime) {
		// TODO Auto-generated method stub
		
	}

	public Set getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	public Character getIsValid() {
		return isValid;
	}

	public void setIsValid(Character isValid) {
		this.isValid = isValid;
	}
}