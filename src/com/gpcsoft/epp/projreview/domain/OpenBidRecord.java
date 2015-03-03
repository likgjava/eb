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
@Table(name = "ECP_OPENBID_RECORD")
public class OpenBidRecord implements GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime{
	@Id
	@Column(name = "OPEN_B_R_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@Column(name = "OPEN_B_ID",length = 36)
	private String openBId;//开标ID
	
	@Column(name = "SUB_PROJ_ID",length = 50)
	private String subProjId;//关联包组ID
	
	@Column(name = "SUPPLIER_NAME")
	private String sellerName;//对应参与供应商名称
	
	//供应商
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="SUPPLIER_ID")//关联的外键	 
	@BatchSize(size = 15)
	private OrgInfo supplier;//对应参与供应商ID
	
	@Column(name = "BID_ID",length = 36)
	private String bidId;//投标ID
	
	@Column(name = "BID_RECORD",length = 50)
	private String bidRecord;//投标ID
	
	@Column(name = "OPEN_B_R_STAUTS",length = 2)
	private String openBRStatus;//开启状态[未开标00，开标成功01，开标失败02]
	
	@Column(name = "OPEN_B_R_STAUTS_MEMO")
	private String openBRStatusMemo;//开标描述 
	
	@Column(name = "QUOTESUM")
	private BigDecimal quoteSum; //报价总金额
	
	@Column(name = "PROJ_MANAGER")
	private String projManager;		// 项目经理
	
	@Column(name = "USE_STATUS")
	private Character useStatus;//使用状态
	
	@Column(name = "OPEN_R_IS_EFFECTIVE")
	private String openRisEffective;//00：未知；01：有效； 02废标
	
	@Column(name = "EFFECTIVE_MEMO")
	private String effectiveMemo;//废标说明
	
	@Column(name = "CONFIRM_STATUS")
	private String confirmStatus;//开标确认状态[默认为空；00:确认为NO;01:确认为YES]
	
	@Column(name = "CONFIRM_MEMO")
	private String confirmMemo;//确认意见
	
	@Column(name = "CONFIRM_SIGN")
	private String confirmSign;//确认签名
	
	@Column(name = "CONFIRM_PERSON")
	private String confirmPerson;//确认签名
	
	@Column(name = "OPEN_B_LINKER")
	private String openBLinker;//联系人
	
	@Column(name = "OPEN_B_LINKER_TEL")
	private String openBLinkerTel;//联系电话
	
	@Transient
	private String isSelect ;//是否被确认
	
	
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
    public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
    
	public String getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}
	
	public String getSubProjId() {
		return subProjId;
	}

	public void setSubProjId(String subProjId) {
		this.subProjId = subProjId;
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
	
	public String getOpenBId() {
		return openBId;
	}

	public void setOpenBId(String openBId) {
		this.openBId = openBId;
	}
	 /**
	 * @gpcsoft.property title="供应商名称"
	 */
	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public OrgInfo getSupplier() {
		return supplier;
	}

	public void setSupplier(OrgInfo supplier) {
		this.supplier = supplier;
	}

	public String getBidId() {
		return bidId;
	}

	public void setBidId(String bidId) {
		this.bidId = bidId;
	}

	public String getBidRecord() {
		return bidRecord;
	}

	public void setBidRecord(String bidRecord) {
		this.bidRecord = bidRecord;
	}

	public String getOpenBRStatus() {
		return openBRStatus;
	}

	public void setOpenBRStatus(String openBRStatus) {
		this.openBRStatus = openBRStatus;
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

	
	/**
	 * @gpcsoft.property title="项目经理"
	 */
	public String getProjManager() {
		return projManager;
	}

	public void setProjManager(String projManager) {
		this.projManager = projManager;
	}

	public String getOpenRisEffective() {
		return openRisEffective;
	}

	public void setOpenRisEffective(String openRisEffective) {
		this.openRisEffective = openRisEffective;
	}

	public String getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

	public String getConfirmMemo() {
		return confirmMemo;
	}

	public void setConfirmMemo(String confirmMemo) {
		this.confirmMemo = confirmMemo;
	}

	public String getConfirmSign() {
		return confirmSign;
	}

	public void setConfirmSign(String confirmSign) {
		this.confirmSign = confirmSign;
	}

	public String getConfirmPerson() {
		return confirmPerson;
	}

	public void setConfirmPerson(String confirmPerson) {
		this.confirmPerson = confirmPerson;
	}

	public String getOpenBRStatusMemo() {
		return openBRStatusMemo;
	}

	public void setOpenBRStatusMemo(String openBRStatusMemo) {
		this.openBRStatusMemo = openBRStatusMemo;
	}

	public String getEffectiveMemo() {
		return effectiveMemo;
	}

	public void setEffectiveMemo(String effectiveMemo) {
		this.effectiveMemo = effectiveMemo;
	}

	public String getOpenBLinker() {
		return openBLinker;
	}

	public void setOpenBLinker(String openBLinker) {
		this.openBLinker = openBLinker;
	}

	public String getOpenBLinkerTel() {
		return openBLinkerTel;
	}

	public void setOpenBLinkerTel(String openBLinkerTel) {
		this.openBLinkerTel = openBLinkerTel;
	}
}
