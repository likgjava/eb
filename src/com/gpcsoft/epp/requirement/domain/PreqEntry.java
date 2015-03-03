package com.gpcsoft.epp.requirement.domain;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.epp.consign.domain.Consign;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @author liuke
 * @since 0.5
 * @version: 0.6
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.requirement"
 * @gpcsoft.page domain="planform/requirement" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="采购需求条目"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PREQ_ENTRY")
public class PreqEntry implements GpcBaseObject{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PREQ_ENTRY_ID", nullable=false, length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="PREQ_ID") 		
	private PurRequirement purRequirement; //采购需求
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 15)
	@JoinColumn(name="BUYER_ID") 
	private OrgInfo buyer;//采购人
	
	
	//采购需求附件
	@OneToMany(cascade={CascadeType.REMOVE},mappedBy = "preEntry")
	@org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Set<PurReqAttachment> purReqAttachments = new HashSet<PurReqAttachment>();
	
	@Column(name = "ATTACH_REAL_ID", length = 36)
	private String attachRelaId; //需求条目附件ID
	
	@Column(name = "NAME", length = 50)
	private String name; //需求条目名称
	
	/** 委托书 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CONS_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Consign consign;  //委托
	
	/** 申报书 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="TASK_PLAN_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private TaskPlan taskPlan; 
	
	/** 申报书条目 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="TASK_PLAN_SUB_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private TaskPlanSub taskPlanSub; //申报书条目

	/** 采购品目 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="PURITEM_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private PurCategory purchase; //采购品目
	
	@Column(name = "PURITEM_CODE", length = 50)
	private String purchaseCode; //采购品目编号
	
	@Column(name = "PURITEM_NAME", length = 50)
	private String purchaseName; //采购品目名称
	
	@Column(name = "UNIT_PRICE", length = 16)
	private BigDecimal unitPrice; //单价
	
	@Column(name = "SPEC", length = 50)
	private String spec; // 规格
	
	@Column(name = "UNIT", length = 50)
	private String unit; // 计量单位
	
	@Column(name = "QUANTITY", length = 16)
	private BigDecimal quantity; // 数量
	
	@Transient
	private BigDecimal realQuantity; // 数量

	@Column(name = "TOTAL_PRICE", length = 16)
	private BigDecimal totalPrice; // 总价
	
	@Column(name = "NOTE", length = 500)
	private String note; // 条目信息描述
	
	@Column(name = "TECHNICAL", length = 500)
	private String technical; // 技术要求
	
	@Column(name = "PAYMENT_CLAUSE", length = 500)
	private String paymentClause; // 付款条件
	
	@Column(name = "DELIVERY_REQUIRE", length = 500)
	private String deliveryRequire; //交货要求
	
	@Column(name = "SERVICE_PROMISE", length = 500)
	private String servicePromise; //服务承诺
	
	@Column(name = "PREQ_ENTRY_QUALITY_ASSURANCE", length = 500)
	private String qualityAssurance; //质量保证
	
	@Column(name = "ACCEPT_STANDARD", length = 500)
	private String acceptStandard; //验收标准
	
	@Column(name = "WARRENTY_LEN", length = 50)
	private String warrentyLen; //保修期
	
	@Column(name = "REMARK", length = 500)
	private String remark; //备注
	
	@Column(name = "USE_STATUS")
	private Character useStatus;//使用状态
	
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
	name="MODIFY_USER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
   	private User updateUser;
    
    //修改时间
	@Column(name = "MODIFY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	
	/********************************GET和SET方法**********************************************/

	
	/**
	 * @gpcsoft.property title="采购品目"
	 * @gpcsoft.validate class="required"
	 */

	public PurCategory getPurchase() {
		return purchase;
	}

	public void setPurchase(PurCategory purchase) {
		this.purchase = purchase;
	}

	/**
	 * @gpcsoft.property title="采购品目编码"
	 */
	public String getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	/**
	 * @gpcsoft.property title="采购品目名称"
	 * @gpcsoft.validate class="required"
	 */
	public String getPurchaseName() {
		return purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}
	
	/**
	 * @gpcsoft.property title="单价"
	 * @gpcsoft.validate class="required"
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @gpcsoft.property title="技术要求"
	 */
	public String getTechnical() {
		return technical;
	}

	public void setTechnical(String technical) {
		this.technical = technical;
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

	public PurRequirement getPurRequirement() {
		return purRequirement;
	}

	public void setPurRequirement(PurRequirement purRequirement) {
		this.purRequirement = purRequirement;
	}

	public String getAttachRelaId() {
		return attachRelaId;
	}

	public void setAttachRelaId(String attachRelaId) {
		this.attachRelaId = attachRelaId;
	}
	/**
	 * @gpcsoft.property title="名称"
	 * @gpcsoft.validate class="required"
	 */
	public String getName() {
		return this.name;
	}

	public void setName(String Name) {
		this.name = Name;
	}

	/**
	 * @gpcsoft.property title="规格"
	 * @gpcsoft.validate class="required"
	 */
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	/**
	 * @gpcsoft.property title="计量单位"
	 * @gpcsoft.validate class="required"
	 */
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * @gpcsoft.property title="数量"
	 * @gpcsoft.validate class="required"
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	/**
	 * @gpcsoft.property title="总价"
	 * @gpcsoft.validate class="required"
	 */
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * @gpcsoft.property title="条目信息描述"
	 */
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @gpcsoft.property title="付款条件"
	 */
	public String getPaymentClause() {
		return paymentClause;
	}

	public void setPaymentClause(String paymentClause) {
		this.paymentClause = paymentClause;
	}
	/**
	 * @gpcsoft.property title="交货要求"
	 */
	public String getDeliveryRequire() {
		return deliveryRequire;
	}

	public void setDeliveryRequire(String deliveryRequire) {
		this.deliveryRequire = deliveryRequire;
	}
	/**
	 * @gpcsoft.property title="服务承诺"
	 */
	public String getServicePromise() {
		return servicePromise;
	}

	public void setServicePromise(String servicePromise) {
		this.servicePromise = servicePromise;
	}
	/**
	 * @gpcsoft.property title="质量保证"
	 */
	public String getQualityAssurance() {
		return qualityAssurance;
	}

	public void setQualityAssurance(String qualityAssurance) {
		this.qualityAssurance = qualityAssurance;
	}
	/**
	 * @gpcsoft.property title="验收标准"
	 */
	public String getAcceptStandard() {
		return acceptStandard;
	}

	public void setAcceptStandard(String acceptStandard) {
		this.acceptStandard = acceptStandard;
	}
	/**
	 * @gpcsoft.property title="保修期"
	 */
	public String getWarrentyLen() {
		return warrentyLen;
	}

	public void setWarrentyLen(String warrentyLen) {
		this.warrentyLen = warrentyLen;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	public String getObjId() {
		// TODO Auto-generated method stub
		return this.objId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Set<PurReqAttachment> getPurReqAttachments() {
		return purReqAttachments;
	}

	public void setPurReqAttachments(Set<PurReqAttachment> purReqAttachments) {
		this.purReqAttachments = purReqAttachments;
	}
	
	
	public void addPurReqAttachment(PurReqAttachment purReqAttachment)
	{
		this.getPurReqAttachments().add(purReqAttachment);
		purReqAttachment.setPreEntry(this);
	}
	
	public Character getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Character useStatus) {
		this.useStatus = useStatus;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
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

	/**
	 * @gpcsoft.property title="委托"
	 */
	public Consign getConsign() {
		return consign;
	}

	public void setConsign(Consign consign) {
		this.consign = consign;
	}

	/**
	 * @gpcsoft.property title="申报书"
	 */
	public TaskPlan getTaskPlan() {
		return taskPlan;
	}

	public void setTaskPlan(TaskPlan taskPlan) {
		this.taskPlan = taskPlan;
	}

	/**
	 * @gpcsoft.property title="申报书条目"
	 */
	public TaskPlanSub getTaskPlanSub() {
		return taskPlanSub;
	}

	public void setTaskPlanSub(TaskPlanSub taskPlanSub) {
		this.taskPlanSub = taskPlanSub;
	}

	public OrgInfo getBuyer() {
		return buyer;
	}

	public void setBuyer(OrgInfo buyer) {
		this.buyer = buyer;
	}

	public BigDecimal getRealQuantity() {
		return realQuantity;
	}

	public void setRealQuantity(BigDecimal realQuantity) {
		this.realQuantity = realQuantity;
	}
}
