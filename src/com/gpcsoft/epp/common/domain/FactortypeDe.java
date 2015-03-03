package com.gpcsoft.epp.common.domain;

import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.core.tree.BaseTree;
import com.gpcsoft.core.tree.TreeProperty;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 * @Description: 指标分类 
 * @version V1.0
 * @Create Date 2010-8-10 上午10:01:24 By wanghz   
 * @gpcsoft.package packageDir="com.gpcsoft.epp.common"
 * @gpcsoft.page domain="common" project="es/planform"
 * @gpcsoft.domain
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_FACTORTYPE_DE")
@OrderProperty(property="sort", flag="false")
@TreeProperty(topIcon="tombs.gif", nodeIcon="book_titel.gif", title="指标分类", text="factorTypeName")
public class FactortypeDe implements GpcBaseObject, BaseTree<FactortypeDe> {
	
	@Id
	@Column(name="FACTORTYPE_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="PURCHASE_CATEGORY_ID")
	private PurCategory purchaseCategory;
	
	@Column(name="SHOW_NO")
	private Long sort;
	
	@Column(name = "IS_LEAF", columnDefinition = "default='1'")
	private Boolean isLeaf; // 是否叶子节点 1.是 0.否
	
 
	
	 /** 父级节点 */
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="PARENT_ID") 
	private FactortypeDe parent; 						
	
	@Column(name="TYPE_CODE") //分类编号
	private String typeCode;
	
	@Column(name="TYPE_Name") //分类名称
	private String typeName;
	
	@Column(name="FACTOR_TYPE_KIND") //指标种类
	private String factorTypeKind;
	
	@Column(name="ALONE_EVAL")
	private String aloneEval;			// 是否独立评分
	
	@Column(name="AFFECT_NEXT")
	private String affectNext;			// 是否影响下一分类评分
	
	@Column(name="REMARK") //备注
	private String remark;
	
	@Column(name="TREE_LEVEL") //层级
	private String treeLevel;

	@Column(name="WEIGHT_COEFFICIENT") //权重
	private String weightCoefficient;
	
	@Column(name="SCORE") //分值
	private String score;

	@Column(name="AUDIT_TYPE") //审核类型00：初审  01：复审
	private String auditType;
	
	@Column(name="AUDIT_METHOD") //审核方法
	private String auditMethod;
	
	@Column(name="IS_DEFAULT_SYS") //审核方法
	private String isDefaultSys;
 
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATOR", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User creator;

    //创建时间
    @Column(name = "CREATOR_TIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creatorTime;

	public String getIsDefaultSys() {
		return isDefaultSys;
	}

	public void setIsDefaultSys(String isDefaultSys) {
		this.isDefaultSys = isDefaultSys;
	}

	public String getFactorTypeKind() {
		return factorTypeKind;
	}

	public void setFactorTypeKind(String factorTypeKind) {
		this.factorTypeKind = factorTypeKind;
	}

	public String getAuditMethod() {
		return auditMethod;
	}

	public void setAuditMethod(String auditMethod) {
		this.auditMethod = auditMethod;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getCreatorTime() {
		return creatorTime;
	}

	public void setCreatorTime(Date creatorTime) {
		this.creatorTime = creatorTime;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(String treeLevel) {
		this.treeLevel = treeLevel;
	}

	public String getWeightCoefficient() {
		return weightCoefficient;
	}

	public void setWeightCoefficient(String weightCoefficient) {
		this.weightCoefficient = weightCoefficient;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/**
	 * @gpcsoft.property title="所属品目"
	 */
	public PurCategory getPurchaseCategory() {
		return purchaseCategory;
	}

	public void setPurchaseCategory(PurCategory purchaseCategory) {
		this.purchaseCategory = purchaseCategory;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	 
	public FactortypeDe getParent() {
		return parent;
	}

	public void setParent(FactortypeDe parent) {
		this.parent = parent;
	}

	public Set<FactortypeDe> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getCreateTime() {
		return creatorTime;
	}

	public void setCreateTime(Date creatorTime) {
		this.creatorTime=creatorTime;
	}
	
}
