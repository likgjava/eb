
package com.gpcsoft.epp.projreview.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.Attachment;

/** 
 * @Description: 开标一览表
 * @version V1.0
 * @Create Date 2010-7-12 下午05:06:43 By wanghz   
 * @gpcsoft.package packageDir="com.gpcsoft.epp.projreview"
 * @gpcsoft.page domain="planform/projreview" project="es"
 * @gpcsoft.domain
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_OPENBID_GENERALVIEW")
public class OpenbidGeneralview extends WorkFlowModel implements GpcBaseObject{

	@Id
	@Column(name="GEN_VIEW_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="BID_ID")
	private Bid bid;								// 投标记录ID
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="OPEN_B_R_ID")
	private OpenBidRecord openBidRecord;			// 开标记录
	
	@Column(name="BID_QUOTESUM")
	private Float bidQuotesum;						// 投标报价
	
	@ManyToOne(fetch=FetchType.EAGER,optional=true)
	@JoinColumn(name="ATTACH_REAL_ID")
	private Attachment attachment;					// 关联附件
	
	@Column(name="SUPPLIER_ID")
	private String supplierId;						// 供应商Id
	
	@Column(name="SUPPLIER_NAME")
	private String supplierName;					// 供应商名称
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="SUB_PROJ_ID")
	private Project subProj;						// 关联包组				
	
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.ALL})
	@JoinColumn(name = "GEN_VIEW_ID") 
	private Set<OpenBidGeneralVitem> openBidGeneralVitemSet = new HashSet<OpenBidGeneralVitem>(0);
	
	@Transient
	private List<OpenBidGeneralVitem>openBidGeneralVitemlist ;    //开标一览表明细信息

	@Transient
	private String attachRelaId;                    //投标文件
	
	@Transient
	private String projectId;                       //项目主键
	
	@Transient
	private String subProjectId;                    //包组主键
	
	@Transient
	private Float  bidQuotesumTotal;                      //报价总和
	
	/********************************getters and setters**********************************/		
	public Float getBidQuotesumTotal() {
		return bidQuotesumTotal;
	}

	public void setBidQuotesumTotal(Float bidQuotesumTotal) {
		this.bidQuotesumTotal = bidQuotesumTotal;
	}
	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getSubProjectId() {
		return subProjectId;
	}

	public void setSubProjectId(String subProjectId) {
		this.subProjectId = subProjectId;
	}
	
	public List<OpenBidGeneralVitem> getOpenBidGeneralVitemlist() {
		return openBidGeneralVitemlist;
	}

	public void setOpenBidGeneralVitemlist(
			List<OpenBidGeneralVitem> openBidGeneralVitemlist) {
		this.openBidGeneralVitemlist = openBidGeneralVitemlist;
	}
	
	public String getAttachRelaId() {
		return attachRelaId;
	}

	public void setAttachRelaId(String attachRelaId) {
		this.attachRelaId = attachRelaId;
	}
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/**
	 * @gpcsoft.property title="投标记录"
	 */
	public Bid getBid() {
		return bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}
	
	/**
	 * @gpcsoft.property title="开标记录"
	 */
	public OpenBidRecord getOpenBidRecord() {
		return openBidRecord;
	}

	public void setOpenBidRecord(OpenBidRecord openBidRecord) {
		this.openBidRecord = openBidRecord;
	}

	/**
	 * @gpcsoft.property title="投标报价"
	 */
	public Float getBidQuotesum() {
		return bidQuotesum;
	}

	public void setBidQuotesum(Float bidQuotesum) {
		this.bidQuotesum = bidQuotesum;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	/**
	 * @gpcsoft.property title="附件"
	 */
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
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
	 * @gpcsoft.property title="所属标段"
	 */
	public Project getSubProj() {
		return subProj;
	}

	public void setSubProj(Project subProj) {
		this.subProj = subProj;
	}

	
	public void setCreateTime(Date date) {
	}
	

	public Date getCreateTime() {
		return null;
	}
	
	public Set<OpenBidGeneralVitem> getOpenBidGeneralVitemSet() {
		return openBidGeneralVitemSet;
	}

	public void setOpenBidGeneralVitemSet(
			Set<OpenBidGeneralVitem> openBidGeneralVitemSet) {
		this.openBidGeneralVitemSet = openBidGeneralVitemSet;
	}
}
