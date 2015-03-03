package com.gpcsoft.bizplatform.suppliers.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.suppliers.enumeration.RecommendSupplierEnum;
import com.gpcsoft.core.model.GpcBaseObject;

/** 
 *  Comments: <strong>RecommendSupplier</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   bizplatform                    					          
 *  <br/>Module ID: 小额交易平台     		
 *  <br/>Create Date：2010-10-9 下午15:00:48 by likg
 *  <br/>Modified Date:  2010-10-9 下午15:00:48 by likg
 *  @gpcsoft.package packageDir="com.gpcsoft.bizplatform.suppliers"
 *  @gpcsoft.page domain="suppliers" project="bizplatform"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="推荐供应商"  
 *  @since 0.1
 *  @version: 0.1 
 */ 

@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "RECOMMEND_SUPPLIER")
public class RecommendSupplier implements GpcBaseObject 
{
	/** 主键ID */
	@Id
    @Column(name = "ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 推荐人 */
	@Column(name = "RECOMMENDER", length = 50)
	private String recommender;
	
	/** 推荐理由 */
	@Column(name = "REASON", length = 1000)
	private String reason;
	
	/** 审核状态：(00:待审核 01:通过 02:不通过) */
    @Column(name = "AUDIT_STATUS", length = 2)
    private String auditStatus;
    
    /** 审核状态展现形式 */
    @Transient
    private String auditStatusCN;
    
    /** 有效状态：（0:无效 1:有效） */
    @Column(name = "USE_STATUS", length = 1)
    private Boolean useStatus;
    
    /** 推荐的供应商的机构信息 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="ORGINFO")	//关联的外键	 
	@BatchSize(size = 15)	//批量抓取
	private OrgInfo orgInfo;
	
	/** 推荐的供应商 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="SUPPLIER")	//关联的外键	 
	@BatchSize(size = 15)	//批量抓取
	private Supplier supplier;
	
	/** 创建时间 */
	@Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TIME")
	private Date createTime;
	
	/** 序号 */
	@Column(name = "SORT", length = 6)
	private Long sort;
	
	public String getAuditStatus() {
		return auditStatus;
	}

	public String getAuditStatusCN() {
		this.auditStatusCN = RecommendSupplierEnum.getAuditStatusCN(this.auditStatus);
		return this.auditStatusCN;
	}

	public Date getCreateTime() 
	{
		return this.createTime;
	}

	public String getObjId() 
	{
		return this.objId;
	}

	public OrgInfo getOrgInfo() {
		return orgInfo;
	}

	public String getReason() {
		return reason;
	}

	public String getRecommender() {
		return recommender;
	}


	public Long getSort() {
		return sort;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public Boolean getUseStatus() {
		return useStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public void setAuditStatusCN(String auditStatusCN) {
		this.auditStatusCN = auditStatusCN;
	}

	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public void setUseStatus(Boolean useStatus) {
		this.useStatus = useStatus;
	}
}
