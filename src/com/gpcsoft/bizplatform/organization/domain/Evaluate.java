package com.gpcsoft.bizplatform.organization.domain;

import java.math.BigDecimal;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.srplatform.auth.domain.User;


/** 
  *  Comments: <strong>评价对象</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   bizplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-7-20 下午03:12:40 by yucy    					                            
  *  <br/>Modified Date:  2010-7-20 下午03:12:40 by yucy                                   
  *	  @since 0.5
  *   @version: 0.5 
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PUB_EVALUATE")
public class Evaluate implements GpcBaseObject {

    /** serialVersionUID */
	private static final long serialVersionUID = 2103499020775965775L;

	/**主键*/
	@Id
	@Column(name = "EVALUATE_ID",length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
    
	/**被评价机构id*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="ORGINFO_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private OrgInfo org;

	/**项目id*/
	@Column(name = "PROJECT_ID", length = 50)
	private String project;
	
	/**项目名称*/
	@Column(name = "PROJECT_NAME", length = 200)
	private String projectName;
	
	/**评价人*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="RATED_ID")	 
	@BatchSize(size = 15)
	private User rater;
	
	/**评价机构*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="RATED_ORG")	 
	@BatchSize(size = 15)
	private OrgInfo rateOrg;
	
	/**评价时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EVAL_TIME")
	private Date evalTime;
	
	/**评价级别*/
	@Column(name = "EVAL_LEVAL")
	private Character leval;
	
	/**评价描述*/
	@Column(name = "EVAL_REMARK", length = 3000)
	private String remark;
	
	/**总分*/
	@Column(name = "SUMMARY_SCORE")
	private BigDecimal summaryScore;
	
	/**是否匿名*/
	@Column(name = "IS_ANONYMOUS")
	private Character isAonymous;
	
	/** 指标得分 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name = "EVALUATE_ID") 
	private Set<Score> scores = new HashSet<Score>();
	
	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	public String getObjId() {
		return objId;
	}
	
	public OrgInfo getOrg() {
		return org;
	}

	public void setOrg(OrgInfo org) {
		this.org = org;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public User getRater() {
		return rater;
	}

	public void setRater(User rater) {
		this.rater = rater;
	}

	public OrgInfo getRateOrg() {
		return rateOrg;
	}

	public void setRateOrg(OrgInfo rateOrg) {
		this.rateOrg = rateOrg;
	}

	public Date getEvalTime() {
		return evalTime;
	}

	public void setEvalTime(Date evalTime) {
		this.evalTime = evalTime;
	}

	public Character getLeval() {
		return leval;
	}

	public void setLeval(Character leval) {
		this.leval = leval;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getSummaryScore() {
		return summaryScore;
	}

	public void setSummaryScore(BigDecimal summaryScore) {
		this.summaryScore = summaryScore;
	}

	public Character getIsAonymous() {
		return isAonymous;
	}

	public void setIsAonymous(Character isAonymous) {
		this.isAonymous = isAonymous;
	}

	public Date getCreateTime() {
		return evalTime;
	}

	public void setCreateTime(Date evalTime) {
		this.evalTime = evalTime;
	}

	public Set<Score> getScores() {
		return scores;
	}

	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}
	
}