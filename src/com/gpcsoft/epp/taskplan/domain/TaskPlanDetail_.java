package com.gpcsoft.epp.taskplan.domain;

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

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>TaskPlanDetail</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   es                    					          
  *  <br/>Module ID: 采购计划     		
  *  <br/>Create Date：2010-4-14 下午03:45:11 by guom    					                            
  *  <br/>Modified Date:  2010-4-14 下午03:45:11 by guom                                 
  *  @gpcsoft.package packageDir="com.gpcsoft.epp.taskplan"
  *  @gpcsoft.page domain="planform/taskplan" project="es"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="采购计划资金明细"   
  *  @since 0.1
  *  @version: 0.1 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)

@Table(name = "ECP_TASK_PLAN_DETAIL_")
public class TaskPlanDetail_ extends WorkFlowModel implements GpcBaseObject , IPropertyCUserTime, IPropertyUUserTime{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "TASK_PLAN_DETAIL_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;//主键
	
	@Column(name = "BUDGET_NAME", updatable = false)
	private String budgetName;//预算单位名称
	
	@Column(name = "APPROVAL_NUMBER")
	private String approvalNumber; //批准文号

	@Column(name = "FUNDTYPE", length = 50)
	private String fundType;//资金性质
	
	@Transient
    private String fundTypeCN;
	
	@Column(name = "INDSOURCE", length = 50)
	private String indSource;//预算来源
	
	@Transient
    private String indSourceCN;
	
	@Column(name = "PAYTYPE", length = 50)
	private String payType;//支付方式
	
	@Column(name = "BDGMANADIVISION", length = 50)
	private String bdgManadivision;//指标归口处室
	
	@Column(name = "EXPECONORMI", length = 50)
	private String exPeconormic;//支出经济分类
	
	@Column(name = "EXPFUNC", length = 50)
	private String expFunc;//支出功能分类
	
	@Column(name = "QUANTITY", length = 50)
	private String quantity;//资金金额
	
	@Column(name = "REMARK", length = 50)
	private String remark;//备注
	
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
	@JoinColumn(name="MODIFY_USER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User updateUser;
    
    //修改时间
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
	
	/********************************GET和SET方法**********************************************/
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	/**
	 * @gpcsoft.property title="资金性质"
	 */
	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}
	/**
	 * @gpcsoft.property title="预算来源"
	 */
	public String getIndSource() {
		return indSource;
	}

	public void setIndSource(String indSource) {
		this.indSource = indSource;
	}
	/**
	 * @gpcsoft.property title="支付方式"
	 */
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * @gpcsoft.property title="指标归口处室"
	 */
	public String getBdgManadivision() {
		return bdgManadivision;
	}

	public void setBdgManadivision(String bdgManadivision) {
		this.bdgManadivision = bdgManadivision;
	}
	/**
	 * @gpcsoft.property title="支出经济分类"
	 */
	public String getExPeconormic() {
		return exPeconormic;
	}

	public void setExPeconormic(String exPeconormic) {
		this.exPeconormic = exPeconormic;
	}
	/**
	 * @gpcsoft.property title="支出功能分类"
	 */
	public String getExpFunc() {
		return expFunc;
	}

	public void setExpFunc(String expFunc) {
		this.expFunc = expFunc;
	}
	
	/**
	 * @gpcsoft.property title="资金金额(元)"
	 */
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
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
	 * @gpcsoft.property title="备注"
	 */

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * @gpcsoft.property title="预算单位"
	 */
	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	/**
	 * @gpcsoft.property title="批准文号"
	 */
	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	public String getFundTypeCN() {
		fundTypeCN = TaskPlanFundTypeEnum.getFundTypeCN(this.getFundType());
		return fundTypeCN;
	}

	public void setFundTypeCN(String fundTypeCN) {
		this.fundTypeCN = fundTypeCN;
	}

	public String getIndSourceCN() {
		indSourceCN = TaskPlanIndSourceEnum.getIndSourceCN(this.getIndSource());
		return indSourceCN;
	}

	public void setIndSourceCN(String indSourceCN) {
		this.indSourceCN = indSourceCN;
	}
	
	
}
