package com.gpcsoft.serve.hotel.domain;

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
import com.gpcsoft.core.model.OrderProperty;

/** 
  *  Comments: <strong>RecommendHotel</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   serve                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-12-9 上午09:56:36 by likg    					                            
  *  <br/>Modified Date:  2010-12-9 上午09:56:36 by likg                                   
  *  @gpcsoft.package packageDir="com.gpcsoft.serve.hotel"
  *  @gpcsoft.page domain="hotel" project="serve"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="推荐酒店"                              
  *  @since 0.5
  *  @version: 0.5 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "SERVE_RECOMMEND_HOTEL")
@OrderProperty(property="sort", flag="true")
public class RecommendHotel implements GpcBaseObject{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 主键ID */
	@Id
    @Column(name = "RECOMMEND_HOTEL_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 推荐的酒店 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="HOTEL_ID")	//关联的外键	 
	@BatchSize(size = 15)	//批量抓取
	private Hotel hotel;
	
	/** 推荐人的id */
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
		return auditStatusCN;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public String getObjId() {
		return this.objId;
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

	public Boolean getUseStatus() {
		return useStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public void setAuditStatusCN(String auditStatusCN) {
		this.auditStatusCN = auditStatusCN;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public void setObjId(String objId) {
		this.objId = objId;
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

	public void setUseStatus(Boolean useStatus) {
		this.useStatus = useStatus;
	}
}
