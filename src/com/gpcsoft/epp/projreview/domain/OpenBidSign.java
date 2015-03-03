package com.gpcsoft.epp.projreview.domain;

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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @author zhouzhanghe
 * @since 0.5
 * @version: 0.6
 * @gpcsoft.package packageDir="com.gpcsoft.epp.projreview"
 * @gpcsoft.page domain="planform/projreview" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="开标签到表"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_OPENBID_SIGN")
public class OpenBidSign implements GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime{

	@Id
	@Column(name = "SIGN_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@Column(name = "TENDER_ID",length = 50)
	private String tenderId; //招标项目ID/标段ID 
	
	//创建人
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="SUPPLER_ID")//关联的外键	 
	@BatchSize(size = 15)
	private OrgInfo supplierId; //签到投标供应商ID
	
	@Column(name = "SUPPLER_NAME",length = 50)
	private String supplierName; //投标供应商名称
	
	@Column(name = "SUPPLER_CODE",length = 50)
	private String supplierCode; //投标供应商编号
	
	@Column(name = "SIGN_STR",length = 50)
	private String signStr; //签名数据 
	
	@Column(name = "SIGN_CERT",length = 50)
	private String signCert; //签名证书
	
	@Column(name = "SIGN_NO",length = 50)
	private String signNo; //证书编号（如KEY的唯一编号等） 
	
	@Column(name = "SIGN_CERT_TYPE",length = 50)
	private String signCertType; //签到证书类型
	
	@Column(name = "SIGN_CERT_EMP_NAME",length = 50)
	private String signCertEmpName; //签到人名称
	
	@Column(name = "SIGN_CERT_EMP_TEL",length = 50)
	private String signCertEmpTel; //签到人联系电话 
	
	@Column(name = "SIGN_TYPE",length = 50)
	private String signType; //签到类型 
	
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

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	public OrgInfo getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(OrgInfo supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSignCertType() {
		return signCertType;
	}

	public void setSignCertType(String signCertType) {
		this.signCertType = signCertType;
	}

	public String getSignCertEmpName() {
		return signCertEmpName;
	}

	public void setSignCertEmpName(String signCertEmpName) {
		this.signCertEmpName = signCertEmpName;
	}

	public String getSignCertEmpTel() {
		return signCertEmpTel;
	}

	public void setSignCertEmpTel(String signCertEmpTel) {
		this.signCertEmpTel = signCertEmpTel;
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

	public String getSignStr() {
		return signStr;
	}

	public void setSignStr(String signStr) {
		this.signStr = signStr;
	}

	public String getSignCert() {
		return signCert;
	}

	public void setSignCert(String signCert) {
		this.signCert = signCert;
	}

	public String getSignNo() {
		return signNo;
	}

	public void setSignNo(String signNo) {
		this.signNo = signNo;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}
}
