package com.gpcsoft.epp.bid.domain;

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
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @author shenjz
 * @since 0.5
 * @version: 0.6
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.bid"
 * @gpcsoft.page domain="planform/bid" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="保证金记录表"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ecp_bail_record")
public class BailRecord extends WorkFlowModel implements GpcBaseObject{
	@Id
	@Column(name = "BAIL_RECORD_ID", nullable=false, length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@OneToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="ApplyID",unique=true)//关联的外键	 
	@BatchSize(size = 50)
	private SignUprecord signUprecord; //供应商报名记录ID
	
	@Column(name = "BAIL")
	private BigDecimal ballMoney; //保证金[数额]
	
	@Column(name = "BAIL_PERCENT")
	private BigDecimal bailPercent; //保证金[百分比]
	
	@Column(name = "renderTime")
	private Date renderTime; //交纳时间
	
	@Column(name = "renderAtt", length = 50)
	private String renderAtt; //交纳证明【关联附件】
	
	@Column(name = "renderRemark", length = 50)
	private String renderRemark; //交纳备注
	
	@Column(name = "returnedTime")
	private Date returnedTime; //退回时间
	
	@Column(name = "returnedAtt ", length = 50)
	private String returnedAtt ; //退回证明
	
	@Column(name = "returnedRemark", length = 50)
	private String returnedRemark; //退回备注
	
	@Column(name = "bailStatus", length = 2)
	private String bailStatus; //保证金记录状态00：未收；01：已收；02：已退  ;03:已结转
	
	@Column(name = "BACK_BANK_ACCOUNT", length = 2)
	private String bankAccount; //退回银行账号
	
	@Column(name = "BACK_BANK_OPENNAME", length = 2)
	private String bankOpenName; //退回银行账号开户人姓名

	@Transient
	private String bailStatusCN;
	
	@Column (name = "TENDER_ID")
	private String projId;//招标项目ID/包组ID 
	
	@Column (name = "TENDER_NAME")
	private String projName;//项目/包组名称 
	
	@Column (name = "SUPPLYER_ID")
	private String supplyerId;//供应商ID 
	
	@Column (name = "SUPPLYER_NAME")
	private String supplyerName;//供应商名称 
	
	@Column(name = "CARRY_FORWARD")
	private Boolean carryForward;
	
	@Column(name = "LINKER")
	private String linker; //联系人
	
	@Column(name = "LINKER_TEL")
	private String linkerTel; //联系方式
	
	@Column(name = "PAY_TYPE", length = 2)
	private String payType; //缴纳方式	01:汇票,02:支票,03:电汇,04:现金,05:其他
	
	@Transient
	private String payTypeCN; //缴纳方式
	
	@Column(name = "INVOICE_FILE")
	private String invoiceFile;//发票附件
	
	@Column(name = "REMARK")
	private String remark;//备注
	
	@Column(name = "USE_STATUS")
	private String useStatus;//使用状态
	
	@Transient
	private String useStatusCN;
	
	@Transient 
	private String openAccount; //开户银行账号
	
	@Column(name = "AUDIT_STATUS")
	private String auditStatus;//审核状态
	
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
	@JoinColumn(name="MODIFY_USER", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User updateUser;
    
    	//修改时间
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
	
    @Transient
    private String opinion;//审核意见
    
	/********************************GET和SET方法**********************************************/
	public String getBailStatusCN() {
		this.bailStatusCN = BailStatusCNEnum.getCN(this.getBailStatus());
		return this.bailStatusCN;
	}

	public void setBailStatusCN(String bailStatusCN) {
		this.bailStatusCN = bailStatusCN;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankOpenName() {
		return bankOpenName;
	}

	public void setBankOpenName(String bankOpenName) {
		this.bankOpenName = bankOpenName;
	}
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	public SignUprecord getSignUprecord() {
		return signUprecord;
	}

	public void setSignUprecord(SignUprecord signUprecord) {
		this.signUprecord = signUprecord;
	}

	/**
	 * @gpcsoft.property title="保证金比例"
	 */
	public BigDecimal getBailPercent() {
		return bailPercent;
	}

	public void setBailPercent(BigDecimal bailPercent) {
		this.bailPercent = bailPercent;
	}

	/**
	 * @gpcsoft.property title="保证金"
	 */
	public BigDecimal getBallMoney() {
		return ballMoney;
	}

	public void setBallMoney(BigDecimal ballMoney) {
		this.ballMoney = ballMoney;
	}
	/**
	 * @gpcsoft.property title="交纳时间"
	 */
	public Date getRenderTime() {
		return renderTime;
	}

	public void setRenderTime(Date renderTime) {
		this.renderTime = renderTime;
	}
	
	/**
	 * @gpcsoft.property title="交纳证明"
	 */
	public String getRenderAtt() {
		return renderAtt;
	}

	public void setRenderAtt(String renderAtt) {
		this.renderAtt = renderAtt;
	}
	public String getRenderRemark() {
		return renderRemark;
	}
	
	public void setRenderRemark(String renderRemark) {
		this.renderRemark = renderRemark;
	}
	/**
	 * @gpcsoft.property title="退回时间"
	 */
	public Date getReturnedTime() {
		return returnedTime;
	}

	public void setReturnedTime(Date returnedTime) {
		this.returnedTime = returnedTime;
	}
	/**
	 * @gpcsoft.property title="退回证明"
	 */
	public String getReturnedAtt() {
		return returnedAtt;
	}

	public void setReturnedAtt(String returnedAtt) {
		this.returnedAtt = returnedAtt;
	}
	
	public String getReturnedRemark() {
		return returnedRemark;
	}

	public void setReturnedRemark(String returnedRemark) {
		this.returnedRemark = returnedRemark;
	}
	
	/**
	 * @gpcsoft.property title="状态"
	 */
	public String getBailStatus() {
		return bailStatus;
	}

	public void setBailStatus(String bailStatus) {
		this.bailStatus = bailStatus;
	}

	public String getLinker() {
		return linker;
	}

	public void setLinker(String linker) {
		this.linker = linker;
	}

	public String getLinkerTel() {
		return linkerTel;
	}

	public void setLinkerTel(String linkerTel) {
		this.linkerTel = linkerTel;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getInvoiceFile() {
		return invoiceFile;
	}

	public void setInvoiceFile(String invoiceFile) {
		this.invoiceFile = invoiceFile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
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

	public String getUseStatusCN() {
		useStatusCN = AuditStatusEnum.getCN(this.getUseStatus());
		return useStatusCN;
	}

	public void setUseStatusCN(String useStatusCN) {
		this.useStatusCN = useStatusCN;
	}
	
	public String getPayTypeCN() {
		payTypeCN = BailRecordTypeEnum.getCN(this.getPayType());
		return payTypeCN;
	}

	public void setPayTypeCN(String payTypeCN) {
		this.payTypeCN = payTypeCN;
	}

	public Boolean getCarryForward() {
		return carryForward;
	}

	public void setCarryForward(Boolean carryForward) {
		this.carryForward = carryForward;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getSupplyerId() {
		return supplyerId;
	}

	public void setSupplyerId(String supplyerId) {
		this.supplyerId = supplyerId;
	}

	public String getSupplyerName() {
		return supplyerName;
	}

	public void setSupplyerName(String supplyerName) {
		this.supplyerName = supplyerName;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getOpenAccount() {
		return openAccount;
	}

	public void setOpenAccount(String openAccount) {
		this.openAccount = openAccount;
	}
}
