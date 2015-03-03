package com.gpcsoft.epp.eval.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.tree.BaseTree;
import com.gpcsoft.core.tree.TreeProperty;

/** 
 * @Description: 符合性指标分类 
 *    
 * @version V1.0
 * @Create Date 2010-8-2 上午09:25:08 By wanghz   
 * @gpcsoft.package packageDir="com.gpcsoft.epp.eval"
 * @gpcsoft.page domain="eval" project="es/planform"
 * @gpcsoft.domain
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Cache(region="com.gpcsoft.srplatform.sysconfig.domain", usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ECP_CONGRUOUS_FACTOR_TYPE")
@TreeProperty(topIcon="tombs.gif", nodeIcon="book_titel.gif", title="指标类型", text="typeName")
public class CongruousFactorType implements GpcBaseObject, BaseTree {

	@Id
	@Column(name="CON_FAC_TYPE_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@Column(name="Tender_ID")
	private String projId;//项目主键
	
	@Column(name="PROJ_H_ID")
	private String projHId;//显示顺序
	
	@Column(name="IMPL_BID_ID")
	private String implBidId;
	
	@Column(name="IMPL_BID_H_ID")
	private String implBidHId;
	
	@Column(name="TYPE_CODE")
	private String typeCode;
	
	@Column(name="TYPE_NAME")
	private String typeName;
	
	@Column(name="SHOW_NO")
	private Long sort;
	
	@Column(name="REMARK")
	private String remark;
	
	@Column(name="CREATE_ORG_ID")
	private String createOrgId;

	@Column(name="CREATOR")
	private String creator;
	
	@Column(name="CREATOR_TIME")
	private Date createTime;
	
	@Column(name="CHANGE_ORG_ID")
	private String changeOrgId;
	
	@Column(name="CHANGER")
	private String changer;
	
	@Column(name="LAST_TIME")
	private Date lastTime;
	
	@Column(name = "IS_LEAF", columnDefinition = "default='1'")
	private Boolean isLeaf; // 是否叶子节点 1.是 0.否
	
	@Column(name="FACTOR_TYPE_KIND")
	private String factorTypeKind;
	
	@Column(name="AUDIT_METHOD")
	private String auditMethod;//// 审核类型：00:初审 01:复审
	
	
	@Column(name="AUDIT_TYPE")
	private String auditType;//// 审核类型：00:初审 01:复审
	
	@Column(name="ALONE_EVAL")
	private String aloneEval;			// 是否独立评分
	
	@Column(name="AFFECT_NEXT")
	private String affectNext;			// 是否影响下一分类评分
	
	@Column(name = "TREE_LEVEL")
	private Short level; // 树形层级
	
	@Column(name="WEIGHT_COEFFICIENT")
	private Integer weightCoefficient;// 权重
	
	 /** 父级节点 */
	@ManyToOne
	@JoinColumn(name="PARENT_ID") 
	private CongruousFactorType parent; 						
	
    /** 子节点 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@JoinColumn(name = "PARENT_ID",updatable=false) 
	private Set<CongruousFactorType> subCongruousFactorType = new HashSet<CongruousFactorType>(0);
	
	/** 指标 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@JoinColumn(name = "CON_FAC_TYPE_ID",updatable=false) 
	private Set<CongruousFactor> congruousFactorSet = new HashSet<CongruousFactor>(0);
	
	@Transient
	private String auditMethodCN;  //评标方法
	
	@Transient
	private List<CongruousFactor> factorList = new ArrayList<CongruousFactor>(0);  //指标集合
	
	@Transient
	private List<CongruousFactorType> factorTypeList = new ArrayList<CongruousFactorType>(0);  //指标分类集合

	@Transient//指标分类的级数
	private int factorTypeLevel;
	
	@Transient//指标分类在列表中的行位置
	private int rowNumber;

	/********************************getters and setters**********************************/

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getFactorTypeLevel() {
		return factorTypeLevel;
	}

	public void setFactorTypeLevel(int factorTypeLevel) {
		this.factorTypeLevel = factorTypeLevel;
	}

	public List<CongruousFactorType> getFactorTypeList() {
		return factorTypeList;
	}

	public void setFactorTypeList(List<CongruousFactorType> factorTypeList) {
		this.factorTypeList = factorTypeList;
	}

	public List<CongruousFactor> getFactorList() {
		return factorList;
	}

	public void setFactorList(List<CongruousFactor> factorList) {
		this.factorList = factorList;
	}
	
	public String getObjId() {
		return objId;
	}
	
	public String getAuditMethodCN() {
		//auditMethodCN=//ModelTypeenum.getModelTypeCN(this.getModelType());
		return auditMethodCN;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/**
	 * @gpcsoft.property title="项目"
	 */
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getProjHId() {
		return projHId;
	}

	public void setProjHId(String projHId) {
		this.projHId = projHId;
	}

	public String getImplBidId() {
		return implBidId;
	}

	public void setImplBidId(String implBidId) {
		this.implBidId = implBidId;
	}

	public String getImplBidHId() {
		return implBidHId;
	}

	public void setImplBidHId(String implBidHId) {
		this.implBidHId = implBidHId;
	}

	/**
	 * @gpcsoft.property title="分类编号"
	 */
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * @gpcsoft.property title="分类名称"
	 */
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	/**
	 * @gpcsoft.property title="备注"
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateOrgId() {
		return createOrgId;
	}

	public void setCreateOrgId(String createOrgId) {
		this.createOrgId = createOrgId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getChangeOrgId() {
		return changeOrgId;
	}

	public void setChangeOrgId(String changeOrgId) {
		this.changeOrgId = changeOrgId;
	}

	public String getChanger() {
		return changer;
	}

	public void setChanger(String changer) {
		this.changer = changer;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public CongruousFactorType getParent() {
		return parent;
	}

	public void setParent(CongruousFactorType parent) {
		this.parent = parent;
	}

	public Set<CongruousFactorType> getSubCongruousFactorType() {
		return subCongruousFactorType;
	}

	public void setSubCongruousFactorType(
			Set<CongruousFactorType> subCongruousFactorType) {
		this.subCongruousFactorType = subCongruousFactorType;
	}
	
	public String getFactorTypeKind() {
		return factorTypeKind;
	}

	public void setFactorTypeKind(String factorTypeKind) {
		this.factorTypeKind = factorTypeKind;
	}
	
	public String getAloneEval() {
		return aloneEval;
	}

	public void setAloneEval(String aloneEval) {
		this.aloneEval = aloneEval;
	}

	public String getAffectNext() {
		return affectNext;
	}

	public void setAffectNext(String affectNext) {
		this.affectNext = affectNext;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public Set<CongruousFactor> getCongruousFactorSet() {
		return congruousFactorSet;
	}

	public void setCongruousFactorSet(Set<CongruousFactor> congruousFactorSet) {
		this.congruousFactorSet = congruousFactorSet;
	}
	/**
	 * @gpcsoft.property title="权重"
	 */
	public Integer getWeightCoefficient() {
		return weightCoefficient;
	}

	public void setWeightCoefficient(Integer weightCoefficient) {
		this.weightCoefficient = weightCoefficient;
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#getCreateTime()
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#setCreateTime(java.util.Date)
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Set<CongruousFactorType> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public String getAuditMethod() {
		return auditMethod;
	}

	public void setAuditMethod(String auditMethod) {
		this.auditMethod = auditMethod;
	}

	@Override
	public boolean equals(Object obj) {
		CongruousFactorType c1 = (CongruousFactorType) obj;
		if(c1.getObjId().equals(this.getObjId())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.getObjId().hashCode();
	}
	
}
