package com.gpcsoft.epp.common.domain;

import java.util.Date;

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

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 * @Description: 指标 
 * @version V1.0
 * @Create Date 2010-8-10 上午10:18:18 By wanghz  
 * @gpcsoft.package packageDir="com.gpcsoft.epp.common"
 * @gpcsoft.page domain="common" project="es/planform"
 * @gpcsoft.domain
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_FACTOR_DE")
public class FactorDe implements GpcBaseObject{

	@Id
	@Column(name="FAC_DEFINE_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@Column(name="FACTOR_NAME")
	private String factorName;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="FACTYPE_DEFINE_ID")
	private FactortypeDe factortypeDe;
	
	@Column(name="FACTOR_CODE") //指标编号
	private String factorCode;
	
	@Column(name="IS_NEED") //是否必填
	private String isNeed;
	
	@Column(name="REMARK") //备注
	private String remark;
	
	@Column(name="FACTOR_SOURCE_KIND") //指标种类
	private String factorSourceKind;
	
	@Column(name="SHOW_NO") //排列序号
	private String showNo;
	
	@Column(name="SCORE") //分值
	private String score;
	
	@Column(name="WEIGHT") //权重
	private String weight;
	
	@Column(name="SCORE_MAX")
	private String maxScore;//最大得分
	
	@Column(name="SCORE_MIN")
	private String minScore;//最小得分
	
	@Column(name="ALONE_EVAL")
	private String aloneEval;			// 是否独立评分
	
	@Column(name="AFFECT_NEXT")
	private String affectNext;			// 是否影响下一分类评分
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATOR", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User creator;

    //创建时间
    @Column(name = "CREATE_TIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
	
	public String getFactorCode() {
		return factorCode;
	}


	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}


	public String getIsNeed() {
		return isNeed;
	}


	public void setIsNeed(String isNeed) {
		this.isNeed = isNeed;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getFactorSourceKind() {
		return factorSourceKind;
	}


	public void setFactorSourceKind(String factorSourceKind) {
		this.factorSourceKind = factorSourceKind;
	}


	public String getShowNo() {
		return showNo;
	}


	public void setShowNo(String showNo) {
		this.showNo = showNo;
	}


	public String getScore() {
		return score;
	}


	public void setScore(String score) {
		this.score = score;
	}


	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
	}


	public String getMaxScore() {
		return maxScore;
	}


	public void setMaxScore(String maxScore) {
		this.maxScore = maxScore;
	}


	public String getMinScore() {
		return minScore;
	}


	public void setMinScore(String minScore) {
		this.minScore = minScore;
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


	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#getCreateTime()
	 */
	public Date getCreateTime() {
		return null;
	}

	
	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#getObjId()
	 */
	public String getObjId() {
		return this.objId;
	}

	
	/* (non-Javadoc)
	 * @see com.gpcsoft.core.model.GpcBaseObject#setCreateTime(java.util.Date)
	 */
	public void setCreateTime(Date arg0) {
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
	 * @gpcsoft.property title="指标分类"
	 */
	public FactortypeDe getFactortypeDe() {
		return factortypeDe;
	}


	public void setFactortypeDe(FactortypeDe factortypeDe) {
		this.factortypeDe = factortypeDe;
	}


	public void setObjId(String objId) {
		this.objId = objId;
	}
}
