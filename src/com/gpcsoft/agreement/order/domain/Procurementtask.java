package com.gpcsoft.agreement.order.domain;

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
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>任务单</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 订单-任务单     		
  *  <br/>Create Date：2010-4-8 上午02:34:25 by yucy    					                            
  *  <br/>Modified Date:  2010-4-8 上午02:34:25 by yucy                                   
  *  
  *   @since 0.5
  *   @version: 0.5 
  *   
  *   @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.order"
  *   @gpcsoft.page domain="order" project="agreement"
  *   @gpcsoft.domain
  *   @gpcsoft.title value="任务单" 
  */ 
@Entity
@Table(name = "EPS_AGREEMENT_PROCUREMENTTASK")
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class Procurementtask implements GpcBaseObject ,IPropertyCUserTime{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**主键*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "TASK_ID", unique = true, nullable = false, length = 50)
	private String objId;
	
	/**编号*/
	@Column(name = "TASK_NO", length = 15)
	private String taskNo;
	
	/**采购人*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="BUYER_ID")
	@BatchSize(size = 15)
	private OrgInfo buyer;
	
	/**时期类型*/	
	@Column(name = "PERIOD_TYPE")
	private String periodType;

	/**时期值*/
	@Column(name = "PERIOD_VALUE")
	private String periodValue;
	
	/**总数量*/
	@Column(name = "SUM_QTY", precision = 16, scale = 6)
	private BigDecimal sumQty;
	
	/**已完成的数量*/
	@Formula("(Select Sum(i.fin_good_sqty) From EPS_AGREEMENT_PROTASK_ITEM i Where i.Task_Id = TASK_ID)")
	private BigDecimal finGoodSqty;
	
	/**总预算金额*/
	@Column(name = "SUM_TOTAL", precision = 16, scale = 6)
	private BigDecimal sumTotal;
	
	/**剩余数量*/
	@Formula("(SUM_QTY - (select nvl(sum(o.order_task_qty),0) from eps_agree_order_protask o join eps_agreement_order_item i on i.order_dtl_id = o.order_dtl_id join eps_agreement_order a on a.order_id = i.order_id join eps_agreement_protask_item e on e.task_item_id = o.task_item_id where e.task_id = TASK_ID and a.use_status != '02') )")
	private BigDecimal finTotal;
	
	/**品目名称集合*/
	@Column(name = "CATEGORY_NAMES", length = 200)
	private String categoryNames;
	
	/**备注*/
	@Column(name = "MEMO", length = 200)
	private String memo;
	
	/**使用状态*/
	@Column(name = "USE_STATUS", length = 2)
	private String useStatus;
	
	/**任务单条目*/
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "TASK_ID") 
	private Set<ProtaskItem> protaskItems = new HashSet<ProtaskItem>();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRE_TIME", length = 7)
	private Date createTime;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CRE_USER_ID")
	@BatchSize(size = 15)
	private User createUser;

	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	/** @gpcsoft.property title="编号" */
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}	
	
	/** @gpcsoft.property title="采购人" */
	public OrgInfo getBuyer() {
		return buyer;
	}
	public void setBuyer(OrgInfo buyer) {
		this.buyer = buyer;
	}
	
	/** @gpcsoft.property title="时期类型" */
	public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	
	/** @gpcsoft.property title="时期值" */
	public String getPeriodValue() {
		return periodValue;
	}
	public void setPeriodValue(String periodValue) {
		this.periodValue = periodValue;
	}
	
	/** @gpcsoft.property title="总数量" */
	public BigDecimal getSumQty() {
		return sumQty;

	}
	public void setSumQty(BigDecimal sumQty) {
		this.sumQty = sumQty;
	}
	
	/** @gpcsoft.property title = "已完成数量"*/
	public BigDecimal getFinGoodSqty() {
		return finGoodSqty;
	}
	public void setFinGoodSqty(BigDecimal finGoodSqty) {
		this.finGoodSqty = finGoodSqty;
	}
	
	/** @gpcsoft.property title="总预算金额" */
	public BigDecimal getSumTotal() {
		return sumTotal;
	}
	public void setSumTotal(BigDecimal sumTotal) {
		this.sumTotal = sumTotal;
	}
	
	/**@gpcsoft.property title="已完成预算"*/
	public BigDecimal getFinTotal() {
		return finTotal;
	}
	public void setFinTotal(BigDecimal finTotal) {
		this.finTotal = finTotal;
	}
	
	/** @gpcsoft.property title="品目名称集合" */
	public String getCategoryNames() {
		return categoryNames;
	}
	public void setCategoryNames(String categoryNames) {
		this.categoryNames = categoryNames;
	}

	/** @gpcsoft.property title="备注" */
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/** @gpcsoft.property title="使用状态" */
	public String getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	
	/** @gpcsoft.property title="任务单条目" */
	public Set<ProtaskItem> getProtaskItems() {
		return protaskItems;
	}
	public void setProtaskItems(Set<ProtaskItem> protaskItems) {
		this.protaskItems = protaskItems;
	}
	
	/** @gpcsoft.property title="创建时间" */
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** @gpcsoft.property title="创建人" */
	public User getCreateUser() {
		return createUser;
	}
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	
}