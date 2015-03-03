package com.gpcsoft.bizplatform.organization.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;


/** 
  *  Comments: <strong>评价指标分值</strong>            		
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
@Table(name = "ECP_PUB_EVALUATE_SCORE")
public class Score implements GpcBaseObject {

	/** serialVersionUID */
	private static final long serialVersionUID = 7512435374912400079L;

	/**主键*/
	@Id
	@Column(name = "SCORE_ID",length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
    
	/**评价*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="EVALUATE_ID")	 
	@BatchSize(size = 15)
	private Evaluate evaluate;

	/**指标Id*/
	@Column(name = "QUOTA_ID", length = 50)
	private String quotaId;
	
	/**比重*/
	@Column(name = "QUOTA_PROPORTION")
	private BigDecimal proportion;
	
	/**名称*/
	@Column(name = "QUOTA_NAME", length = 200)
	private String quotaName;
	
	/**分值*/
	@Column(name = "SCORE")
	private BigDecimal score;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Evaluate getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Evaluate evaluate) {
		this.evaluate = evaluate;
	}

	public String getQuotaId() {
		return quotaId;
	}

	public void setQuotaId(String quotaId) {
		this.quotaId = quotaId;
	}

	public BigDecimal getProportion() {
		return proportion;
	}

	public void setProportion(BigDecimal proportion) {
		this.proportion = proportion;
	}

	public String getQuotaName() {
		return quotaName;
	}

	public void setQuotaName(String quotaName) {
		this.quotaName = quotaName;
	}

	public BigDecimal getScore() {
		return score;
	}
	
	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date arg0) {
		
	}

}