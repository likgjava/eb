package com.gpcsoft.epp.project.domain;

import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>ProjectRule</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 执行平台    		
  *  <br/>Create Date：2010-11-10 下午04:55:45 by yangx    					                            
  *  <br/>Modified Date:  2010-11-10 下午04:55:45 by yangx                                   
  *  @gpcsoft.package packageDir="com.gpcsoft.epp.project"
  *  @gpcsoft.page domain="planform/project" project="es"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="项目规则" 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_TEND_RULE")
public class ProjectRule extends WorkFlowModel implements GpcBaseObject, IPropertyCUserTime{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "RULE_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; //主键
	
	@OneToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="TENDER_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Project project;//招标项目[包组]
	
	@Column(name = "RULE_SIGNUP_START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date signUpSTime;//报名开始时间

	@Column(name = "RULE_SIGNUP_END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date signUpETime;//报名结束时间

	@Column(name = "RULE_SUBMIT_START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date submitStTime;//投标开始时间
	
	@Column(name = "RULE_SUBMIT_END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date submitETime;//投标结束时间
	
	@Column(name = "RULE_OPEN_BID_START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date openBidSDate;//开标时间
	
	@Column(name = "RULE_EVAL_START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date evalSTime;//评标开始时间
	
	@Column(name = "RULE_EVAL_END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date evalETime;//评标结束时间
	
	@Column(name = "rule_bail_start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bailStartDate;//交纳保证金开始时间
	@Column(name = "rule_bail_end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bailEndDate;//交纳保证金截止时间
	@Column(name = "RULE_ISDIVIDEPACK", length = 2)
	private Boolean isDividePack;//是否分包[0:未分包;1:分包]
	
	@Column(name = "RULE_OPEN_BID_ADDR")
	private String openBidAddr;//开标地点
	
	@Column(name = "RULE_ROUND_NO_COUNT")
	private BigDecimal roundNoCount;//谈判轮次总数。[具体每轮的情况，存在轮次表中]
	
	@Column(name = "RULE_ON_LINE")
	private String onLine;//0:网下流程1:网上流程
	
	//服务费支付方式[业主单位支付、中标单位支付]
	@Column(name = "SERVICE_FEE_PAY_TYPE", length = 2)
	private String serviceFeePayType;
	
	@Transient
	private String serviceFeePayTypeCN;
	
	//服务费计算方式[固定金额、差额累计、折扣]
	@Column(name = "SERVICE_FEE_CALCULATE_TYPE", length = 2)
	private String serviceFeeCalculateType;
	
	@Transient
	private String serviceFeeCalculateTypeCN;
	
	//固定金额
	@Column(name = "FIX_AMOUNT", length = 2)
	private BigDecimal fixAmount;//固定金额
	
	//折扣
	@Column(name = "DIS_COUNT", length = 2)
	private BigDecimal disCount;//折扣
	


	//创建人
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATE_USER", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User createUser;

    //创建时间
   	@Column(name = "CREATE_TIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    //修改人
   	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="MODIFY_USER", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User updateUser;
    
	//修改时间
	@Column(name = "MODIFY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	@Transient
	private String evalPeriod;//评标天数  打印预览用 
	
	@Column(name = "RULE_PRICE_CONTROL")
	private BigDecimal priceControl;//add by yangx 控制价
	
	//是否匿名投标[0:不匿名;1:匿名投标] add by shenjz 
	@Column(name = "RULE_ANONYMOUS", length = 2)
	private String ruleAnonymous;
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}

	public Date getSignUpSTime() {
		return signUpSTime;
	}

	/**
	 * @gpcsoft.property title="服务费支付方式"
	 */
	public String getServiceFeePayTypeCN() {
		this.serviceFeePayTypeCN = EbuyMethodEnum.getServiceFeePayTypeCN(this.serviceFeePayType);
		return serviceFeePayTypeCN;
	}

	public void setServiceFeePayTypeCN(String serviceFeePayTypeCN) {
		this.serviceFeePayTypeCN = serviceFeePayTypeCN;
	}
	
	/**
	 * @gpcsoft.property title="服务费计算方式"
	 */
	public void setServiceFeeCalculateType(String serviceFeeCalculateType) {
		this.serviceFeeCalculateType = serviceFeeCalculateType;
	}

	public String getServiceFeeCalculateType() {
		return serviceFeeCalculateType;
	}
	
	public String getServiceFeeCalculateTypeCN() {
		this.serviceFeeCalculateTypeCN = EbuyMethodEnum.getServiceFeeCalculateTypeCN(this.serviceFeePayType);
		return serviceFeeCalculateTypeCN;
	}

	public void setServiceFeeCalculateTypeCN(String serviceFeeCalculateTypeCN) {
		this.serviceFeeCalculateTypeCN = serviceFeeCalculateTypeCN;
	}

	/**
	 * @gpcsoft.property title="服务费支付方式"
	 */
	public String getServiceFeePayType() {
		return serviceFeePayType;
	}

	public void setServiceFeePayType(String serviceFeePayType) {
		this.serviceFeePayType = serviceFeePayType;
	}

	public BigDecimal getFixAmount() {
		return fixAmount;
	}

	public void setFixAmount(BigDecimal fixAmount) {
		this.fixAmount = fixAmount;
	}

	public void setSignUpSTime(Date signUpSTime) {
		this.signUpSTime = signUpSTime;
	}

	public Date getSignUpETime() {
		return signUpETime;
	}

	public void setSignUpETime(Date signUpETime) {
		this.signUpETime = signUpETime;
	}

	public Date getSubmitStTime() {
		return submitStTime;
	}

	public void setSubmitStTime(Date submitStTime) {
		this.submitStTime = submitStTime;
	}

	public Date getSubmitETime() {
		return submitETime;
	}

	public void setSubmitETime(Date submitETime) {
		this.submitETime = submitETime;
	}

	public Date getOpenBidSDate() {
		return openBidSDate;
	}

	public void setOpenBidSDate(Date openBidSDate) {
		this.openBidSDate = openBidSDate;
	}

	public Date getEvalSTime() {
		return evalSTime;
	}

	public void setEvalSTime(Date evalSTime) {
		this.evalSTime = evalSTime;
	}

	public Date getEvalETime() {
		return evalETime;
	}

	public void setEvalETime(Date evalETime) {
		this.evalETime = evalETime;
	}

	public Boolean getIsDividePack() {
		return isDividePack;
	}

	public void setIsDividePack(Boolean isDividePack) {
		this.isDividePack = isDividePack;
	}

	public String getOpenBidAddr() {
		return openBidAddr;
	}

	public void setOpenBidAddr(String openBidAddr) {
		this.openBidAddr = openBidAddr;
	}

	public BigDecimal getRoundNoCount() {
		return roundNoCount;
	}

	public void setRoundNoCount(BigDecimal roundNoCount) {
		this.roundNoCount = roundNoCount;
	}

	public String getOnLine() {
		return onLine;
	}

	public void setOnLine(String onLine) {
		this.onLine = onLine;
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
	public String getEvalPeriod() {
		if(evalSTime!=null&&evalETime!=null){
			long time = Math.abs(evalETime.getTime() - evalSTime.getTime());
			 int period = (int) (time / (24 * 3600 * 1000));
			 return period+1+"";
		}
		else{
			return "";
		}
	}

	public void setEvalPeriod(String evalPeriod) {
		this.evalPeriod = evalPeriod;
	}

	public BigDecimal getPriceControl() {
		return priceControl;
	}

	public void setPriceControl(BigDecimal priceControl) {
		this.priceControl = priceControl;
	}
	
	public BigDecimal getDisCount() {
		return disCount;
	}

	public void setDisCount(BigDecimal disCount) {
		this.disCount = disCount;
	}

	public String getRuleAnonymous() {
		return ruleAnonymous;
	}

	public void setRuleAnonymous(String ruleAnonymous) {
		this.ruleAnonymous = ruleAnonymous;
	}
	
		public Date getBailStartDate() {
		return bailStartDate;
	}

	public void setBailStartDate(Date bailStartDate) {
		this.bailStartDate = bailStartDate;
	}

	public Date getBailEndDate() {
		return bailEndDate;
	}

	public void setBailEndDate(Date bailEndDate) {
		this.bailEndDate = bailEndDate;
	}
}
