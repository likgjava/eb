package com.gpcsoft.epp.eval.domain;

import java.math.BigDecimal;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;

/** 
 * @Description: 指标集
 *    
 * @version V1.0
 * @Create Date 2010-8-2 上午09:25:36 By wanghz   
 * @gpcsoft.package packageDir="com.gpcsoft.epp.eval"
 * @gpcsoft.page domain="eval" project="es/planform"
 * @gpcsoft.domain
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_CONGRUOUS_FACTOR")
public class CongruousFactor extends WorkFlowModel implements GpcBaseObject{
	
	@Id
	@Column(name="CON_FAC_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@ManyToOne(fetch=FetchType.EAGER,optional=true)
	@JoinColumn(name="CON_FAC_TYPE_ID")
	private CongruousFactorType factorType;//指标类型
	
	@Column(name="FACTOR_NAME")
	private String factorName;//name 指标的名称
	
	@Column(name="FACTOR_CODE")
	private String factorCode;//code表示指标的编码
	
	@Column(name="AUDIT_TYPE")
	private String auditType;//auditType 00表示初审 01表示复审 02 表示价格评审;
	
	@Column(name="SCORE_MAX")
	private String maxScore;//最大得分
	
	@Column(name="SCORE_MIN")
	private String minScore;//最小得分
	
	@Column(name="AUDIT_METHOD")
	private String auditMethod;//表示编辑类型 0复选框 1编辑框
	
	
	@Column(name="WEIGHT")
	private Integer weightCoefficient;// 权重
	
	@Column(name="ALONE_EVAL")
	private String aloneEval;			// 是否独立评分
	
	@Column(name="AFFECT_NEXT")
	private String affectNext;			// 是否影响下一分类评分
	
	@Column(name="PROJ_NAMES")
	private String projName;// 冗余多个包件名称
	
	@Column(name="PROJ_IDS")
	private String projIds;// 冗余多个包件名称
	
	@Column(name="PARAM_ITEM_ID")
	private String paramItemId;
	
	@Column(name="SHOW_NO")
	private Integer showNo;
	
	@Column(name="REMARK")
	private String remark;
	
	@Column(name="CHANGE_ORG_ID")
	private String changeOrgId;
	
	@Column(name="CHANGER")
	private String changer;
	
	@Column(name="LAST_TIME")
	private Date lastTime;
	
	@Column(name="FACTOR_SOURCE_KIND")
	private String factorSourceKind;
	
	@Column(name="IS_NEED")
	private String isNeed;  // 是否必填项, 01 是,00否
	
	@Column(name="SCORE")
	private Double score;// 分值
	
	// 指标与包件中间表
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.ALL})
	@JoinColumn(name = "CON_FAC_ID") 
	private Set<FactypeMfactor> factypeMfactorSet = new HashSet<FactypeMfactor>(0);
	
	/********************************getters and setters**********************************/
	public String getProjIds() {
		return projIds;
	}

	public void setProjIds(String projIds) {
		this.projIds = projIds;
	}
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public CongruousFactorType getFactorType() {
		return factorType;
	}

	public void setFactorType(CongruousFactorType factorType) {
		this.factorType = factorType;
	}

	/**
	 * @gpcsoft.property title="指标名称"
	 */
	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}
	/**
	 * @gpcsoft.property title="适用项目/包组"
	 */
	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getParamItemId() {
		return paramItemId;
	}

	public void setParamItemId(String paramItemId) {
		this.paramItemId = paramItemId;
	}

	/**
	 * @gpcsoft.property title="序号"
	 */
	public Integer getShowNo() {
		return showNo;
	}

	public void setShowNo(Integer showNo) {
		this.showNo = showNo;
	}

	/**
	 * @gpcsoft.property title="评审标准：</br>（在专家进行评审打分时可以查阅）"
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getFactorSourceKind() {
		return factorSourceKind;
	}

	public void setFactorSourceKind(String factorSourceKind) {
		this.factorSourceKind = factorSourceKind;
	}

	/**
	 * @gpcsoft.property title="投标时是否必须响应"
	 */
	public String getIsNeed() {
		return isNeed;
	}

	public void setIsNeed(String isNeed) {
		this.isNeed = isNeed;
	}
	public Set<FactypeMfactor> getFactypeMfactorSet() {
		return factypeMfactorSet;
	}

	public void setFactypeMfactorSet(Set<FactypeMfactor> factypeMfactorSet) {
		this.factypeMfactorSet = factypeMfactorSet;
	}

	/**
	 * @gpcsoft.property title="分值"
	 */
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getFactorCode() {
		return factorCode;
	}

	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	
	/**
	 * @gpcsoft.property title="最大值"
	 */
	public String getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(String maxScore) {
		this.maxScore = maxScore;
	}
	
	/**
	 * @gpcsoft.property title="最小值"
	 */
	public String getMinScore() {
		return minScore;
	}

	public void setMinScore(String minScore) {
		this.minScore = minScore;
	}

	/**
	 * @gpcsoft.property title="审核方式"
	 */
	public String getAuditMethod() {
		return auditMethod;
	}

	public void setAuditMethod(String auditMethod) {
		this.auditMethod = auditMethod;
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

	
	/**
	 * @gpcsoft.property title="是否独立评分"
	 */
	public String getAloneEval() {
		return aloneEval;
	}

	public void setAloneEval(String aloneEval) {
		this.aloneEval = aloneEval;
	}

	/**
	 * @gpcsoft.property title="是否影响下一分类的评分"
	 */
	public String getAffectNext() {
		return affectNext;
	}

	public void setAffectNext(String affectNext) {
		this.affectNext = affectNext;
	}

	/********************************Impl Methods**********************************/
	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#getCreateTime()
	 */
	public Date getCreateTime() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#setCreateTime(java.util.Date)
	 */
	public void setCreateTime(Date arg0) {
	}
	
	public String getIsNeedCn(){
		if("01".equals(isNeed)){
			return "是";
		}
		if("01".equals(isNeed)){
			return "否";
		}
		return "";
	}
}
