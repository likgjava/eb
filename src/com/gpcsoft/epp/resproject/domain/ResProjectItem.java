package com.gpcsoft.epp.resproject.domain;

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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>ResProjectItem</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2011-12-21 下午08:21:16 by yangx    					                            
 *  <br/>Modified Date:  2011-12-21 下午08:21:16 by yangx                                   
 *	  @since 3.5
 *   @version: 3.5 
 */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PROJECT_ITEM")
public class ResProjectItem extends WorkFlowModel implements GpcBaseObject{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PROJECT_ITEM_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;                 //主键                        
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="PROJECT_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private ResProject resProject;     //项目ID 
	
	@Column(name="ITEM_NAME")
	private String itemName;     //明细名称
	
	@Column(name="ITEM_CODE")
	private String itemCode;     //明细编码
	
	@Column(name="QUANTITY")
	private BigDecimal quantity;     //数量    
	
	@Column(name="UNIT")
	private String unit;         //单位  
	
	@Column(name="REMARK")
	private String remark;       //备注    
	
	@Column(name="TOTALPRICE")
	private BigDecimal totalprice;   //总价    
	
	@Column(name="UNITPRICE")
	private BigDecimal unitprice;    //单价    
	
	@Column(name="PURCHASE_MODEL")
	private String purchaseModel;//规格型号
	
	@Column(name="USE_STAUS")
	private String useStaus;     //使用状态
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATE_USER_ID", updatable = false)	 
	@BatchSize(size = 15)
	private User createUser;          //创建用户
	
	@Column(name = "CREATE_DATE")
	@Temporal(TemporalType.DATE)
	private Date createDate;            //创建时间
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="UPDATE_USER_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User updateUser;          //更新用户
	
	@Column(name = "UPDATE_DATE")
	@Temporal(TemporalType.DATE)
	private Date updateDate;            //更新时间
	
	@Column(name="EBUY_METHOD")
	private String ebuyMethod;//招标方式[公开招标,邀请招标]
	
	@Column(name="IS_EBUY")
	private String isEbuy;//是否招标[00:不招标,01招标]
	
	@Column(name="EBUY_STYLE")
	private String ebuyStyle;//招标组织形式(00:自行招标,01委托招标)
	
	@Column(name="CONTRAT_PRICE")
	private BigDecimal contractPrice;//合同估算价格
	
	@Column(name="AUDTI_STATUS")
	private String auditStatus;//审批状态
	
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public ResProject getResProject() {
		return resProject;
	}

	public void setResProject(ResProject resProject) {
		this.resProject = resProject;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

	public BigDecimal getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	public String getPurchaseModel() {
		return purchaseModel;
	}

	public void setPurchaseModel(String purchaseModel) {
		this.purchaseModel = purchaseModel;
	}

	public String getUseStaus() {
		return useStaus;
	}

	public void setUseStaus(String useStaus) {
		this.useStaus = useStaus;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getEbuyMethod() {
		return ebuyMethod;
	}

	public void setEbuyMethod(String ebuyMethod) {
		this.ebuyMethod = ebuyMethod;
	}

	public String getIsEbuy() {
		return isEbuy;
	}

	public void setIsEbuy(String isEbuy) {
		this.isEbuy = isEbuy;
	}

	public String getEbuyStyle() {
		return ebuyStyle;
	}

	public void setEbuyStyle(String ebuyStyle) {
		this.ebuyStyle = ebuyStyle;
	}

	public BigDecimal getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(BigDecimal contractPrice) {
		this.contractPrice = contractPrice;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

}
