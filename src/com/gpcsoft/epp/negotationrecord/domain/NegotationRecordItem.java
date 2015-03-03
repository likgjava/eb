package com.gpcsoft.epp.negotationrecord.domain;

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
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.requirement.domain.PreqEntry;
import com.gpcsoft.srplatform.auth.domain.User;
/**
 * @author liuke
 * @since 0.5
 * @version: 0.6
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.negotationrecord"
 * @gpcsoft.page domain="planform/negotationrecord" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="谈判记录"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_NEGOTIATION_RECORD_ITEM")
public class NegotationRecordItem implements GpcBaseObject, IPropertyCUserTime, IPropertyUUserTime{
	@Id
	@Column(name = "NEG_REC_ITEM_ID", nullable=false, length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="NEG_RECORD_ID") 		
	private NegotationRecord negotationRecord; //谈判记录
	
	@Column(name = "NEG_REC_ITEM_TOTAL")
	private BigDecimal negRecItemTotal; //金额
	
	@Column(name = "NEG_REC_ITEM_MEMO", length = 4000)
	private String negRecItemMemo; //备注
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="PREQ_ENTRY_ID") 		
	private PreqEntry preqEntry; //需求条目
	
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
	@JoinColumn(
	name="MODIFY_USER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
   	private User updateUser;
    
    //修改时间
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    
	/********************************GET和SET方法**********************************************/
	/**
	 * @gpcsoft.property title="创建时间"
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getObjId() {
		return this.objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
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
		return this.modifyTime;
	}
	
	public void setUpdateTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	public NegotationRecord getNegotationRecord() {
		return negotationRecord;
	}
	public void setNegotationRecord(NegotationRecord negotationRecord) {
		this.negotationRecord = negotationRecord;
	}
	public BigDecimal getNegRecItemTotal() {
		return negRecItemTotal;
	}
	public void setNegRecItemTotal(BigDecimal negRecItemTotal) {
		this.negRecItemTotal = negRecItemTotal;
	}
	public String getNegRecItemMemo() {
		return negRecItemMemo;
	}
	public void setNegRecItemMemo(String negRecItemMemo) {
		this.negRecItemMemo = negRecItemMemo;
	}
	public PreqEntry getPreqEntry() {
		return preqEntry;
	}
	public void setPreqEntry(PreqEntry preqEntry) {
		this.preqEntry = preqEntry;
	}
}
