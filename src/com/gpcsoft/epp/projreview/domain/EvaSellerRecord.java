package com.gpcsoft.epp.projreview.domain;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;




/**
 * @author liuke
 * @since 0.5
 * @version: 0.6
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.projreview"
 * @gpcsoft.page domain="planform/projreview" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="开标记录"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_EVA_SELLER_RECORD")
public class EvaSellerRecord extends WorkFlowModel implements GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime{
	@Id
	@Column(name = "SELLER_REC_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@Column(name = "EVAL_ID",length = 36)
	private String evalId;//评审记录表主键
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="SUPPLIER_ID")//关联的外键	 
	@BatchSize(size = 15)
	private OrgInfo supplier;//供应商ID
	
	@Column(name = "SUPPLIER_NAME",length = 50)
	private String supplierName;//供应商名称
	
	@Column(name = "FACTOR_SCORE",length = 50)
	private String factorScore;//评审打分
	
	@Column(name = "FACTOR_OPINION",length = 500)
	private String factorOpinion;//评审意见
	
	@Column(name = "RECOMMEND",length = 2)
	private String recommend;//推荐[00:不推荐;01:推荐]
	
	@Column(name = "SUB_PROJ_ID",length = 36)
	private String subProjId;//项目ID[冗余]
	
	@Column(name = "SUB_PROJ_NAME",length = 100)
	private String subProjName;//项目名称[冗余]
	
	@Transient
	private String num = "0";//推荐次数
	
	@Transient
	private String isSelect ;//是否被确认
	
	@Transient
	private String bidId ;//投标主键
	
	@Transient
	private String expertName ;//评委姓名
	
	@Column(name = "SUB_PROJ_CODE",length = 100)
	private String subProjCode;//项目名称[冗余]
	
	@Column(name = "QUOTESUM")
	private BigDecimal quoteSum; //报价总金额
	
	@Column(name = "USE_STATUS")
	private Character useStatus;//使用状态
	
	@Transient
	private String evalLinkerName ;//评审人姓名
	
	/** 创建人 */
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
	@JoinColumn(
	name="MODIFY_USER", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
   	private User updateUser;
    
    //修改时间
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
     private Date updateTime;

 /********************************GET和SET方法**********************************************/
    public String getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}
    
    public String getEvalLinkerName() {
		return evalLinkerName;
	}

	public void setEvalLinkerName(String evalLinkerName) {
		this.evalLinkerName = evalLinkerName;
	}
    
    public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getBidId() {
		return bidId;
	}

	public void setBidId(String bidId) {
		this.bidId = bidId;
	}
	
	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	
	public String getEvalId() {
		return evalId;
	}

	public void setEvalId(String evalId) {
		this.evalId = evalId;
	}

	public OrgInfo getSupplier() {
		return supplier;
	}
    
	public void setSupplier(OrgInfo supplier) {
		this.supplier = supplier;
	}
	 /**
	 * @gpcsoft.property title="供应商名称"
	 */
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	/**
	 * @gpcsoft.property title="评审打分"
	 * @gpcsoft.validate class="required"
	 */
	public String getFactorScore() {
		return factorScore;
	}

	public void setFactorScore(String factorScore) {
		this.factorScore = factorScore;
	}
	 /**
	 * @gpcsoft.property title="评审意见"
	 * @gpcsoft.validate class="required"
	 */
	public String getFactorOpinion() {
		return factorOpinion;
	}

	public void setFactorOpinion(String factorOpinion) {
		this.factorOpinion = factorOpinion;
	}
	 /**
	 * @gpcsoft.property title="是否推荐"
	 * @gpcsoft.validate class="required"
	 */
	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getSubProjId() {
		return subProjId;
	}

	public void setSubProjId(String subProjId) {
		this.subProjId = subProjId;
	}

	public String getSubProjName() {
		return subProjName;
	}

	public void setSubProjName(String subProjName) {
		this.subProjName = subProjName;
	}

	public String getSubProjCode() {
		return subProjCode;
	}

	public void setSubProjCode(String subProjCode) {
		this.subProjCode = subProjCode;
	}

	public Character getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Character useStatus) {
		this.useStatus = useStatus;
	}
    
	 /**
	 * @gpcsoft.property title="报价总金额(元)"
	 */
	public BigDecimal getQuoteSum() {
		return quoteSum;
	}

	public void setQuoteSum(BigDecimal quoteSum) {
		this.quoteSum = quoteSum;
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
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

}
