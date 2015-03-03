package com.gpcsoft.smallscale.pay.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;


/** 
  *  Comments: <strong>支付记录</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2011-7-20 下午04:13:16 by sunlei    					                            
  *  <br/>Modified Date:  2011-7-20 下午04:13:16 by sunlei                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "PAY_RECORD")
public class PayRecord implements GpcBaseObject {

	/** serialVersionUID */
	private static final long serialVersionUID = -2981725632453332490L;

	/** ID */
	@Id
	@Column(name = "PAY_RECORD_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/**支付号*/
	@Column(name = "PAY_NO")
	private String payNo;
	
	/**支付金额*/
	@Column(name = "PAY_AMOUNT")
	private BigDecimal payAmount;
	
	/**支付时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PAY_TIME")
	private Date payTime;
	
	/**支付状态*/
	@Column(name = "PAY_STATUS")
	private String payStatus;
	
	@Transient
	private String payStatusCN;
	
	/**支付业务类型如订单、服务订阅、团购、标书费、保证金等*/
	@Column(name = "PAY_BUSINESS_TYPE")
	private String payBusinessType;
	
	@Transient
	private String payBusinessTypeCN;
	
	/**支付业务编号*/
	@Column(name = "PAY_BUSINESS_ID")
	private String payBusinessId;
	
	/**支付方式如工商银行*/
	@Column(name = "PAY_MODE")
	private String payMode;
	
	/**支付人姓名*/
	@Column(name = "PAY_PERSON_NAME")
	private String payPersonName;
	
	/**支付人ID*/
	@Column(name = "PAY_EMPLOYEE_ID")
	private String payEmpId;
	
	/**发票抬头*/
	@Column(name = "INVOICE_TITLE")
	private String invoiceTitle;
	
	/**发票名目*/
	@Column(name = "INVOICE_ITEMS")
	private String invoiceItems;
	
	/**邮寄地址*/
	@Column(name = "MAILING_ADDRESS")
	private String mailingAddress;
	
	/**支付机构名称*/
	@Formula("(select n.ORG_NAME from AUTH_ORG_EMPLOYEE e join AUTH_ORGNIZATION n on e.EMP_COMPANY_ID = n.ORG_ID where e.EMP_ID = PAY_EMPLOYEE_ID)")
	private String payOrgName;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	public String getPayStatusCN() {
		this.payStatusCN = PayRecordEnum.getPayStatusCN(this.payStatus);
		return this.payStatusCN;
	}

	public void setPayStatusCN(String payStatusCN) {
		this.payStatusCN = payStatusCN;
	}

	public String getPayBusinessType() {
		return payBusinessType;
	}

	public void setPayBusinessType(String payBusinessType) {
		this.payBusinessType = payBusinessType;
	}

	public String getPayBusinessTypeCN() {
		this.payBusinessTypeCN = PayRecordEnum.getPayBusinessTypeCN(this.payBusinessType);
		return this.payBusinessTypeCN;
	}

	public void setPayBusinessTypeCN(String payBusinessTypeCN) {
		this.payBusinessTypeCN = payBusinessTypeCN;
	}

	public String getPayBusinessId() {
		return payBusinessId;
	}

	public void setPayBusinessId(String payBusinessId) {
		this.payBusinessId = payBusinessId;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getPayPersonName() {
		return payPersonName;
	}

	public void setPayPersonName(String payPersonName) {
		this.payPersonName = payPersonName;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCreateTime(Date arg0) {
		// TODO Auto-generated method stub
	}

	public String getPayEmpId() {
		return payEmpId;
	}

	public void setPayEmpId(String payEmpId) {
		this.payEmpId = payEmpId;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(String invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getPayOrgName() {
		return payOrgName;
	}

	public void setPayOrgName(String payOrgName) {
		this.payOrgName = payOrgName;
	}
	
}
