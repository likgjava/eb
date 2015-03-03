package com.gpcsoft.agreement.bargin.project.domain;

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

import com.gpcsoft.agreement.bargin.project.enumeration.RecommendProjectEnum;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.epp.project.domain.Project;

/** 
 *  Comments: <strong>RecommendProject</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   agreement                    					          
 *  <br/>Module ID: 小额交易平台     		
 *  <br/>Create Date：2010-10-9 下午15:00:48 by likg
 *  <br/>Modified Date:  2010-10-9 下午15:00:48 by likg
 *  @gpcsoft.package packageDir="com.gpcsoft.agreement.bargin.project"
 *  @gpcsoft.page domain="bargin/project" project="agreement"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="推荐项目"  
 *  @since 0.1
 *  @version: 0.1 
 */ 

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "RECOMMEND_PROJECT")
public class RecommendProject implements GpcBaseObject {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 3717149308128882446L;

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
	
	/** 推荐项目图片 */
	@Column(name = "PICTURE", length = 50)
	private String picture;
	
	/** 审核状态：(00:待审核 01:通过 02:不通过) */
    @Column(name = "AUDIT_STATUS", length = 2)
    private String auditStatus;
    
    /** 审核状态展现形式 */
    @Transient
    private String auditStatusCN;
    
    /** 有效状态：（0:无效 1:有效） */
    @Column(name = "USE_STATUS", length = 1)
    private Boolean useStatus;
    
    /** 推荐项目 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="PROJECT_ID")
	@BatchSize(size = 15)
	private Project project;
	
	/** 创建时间 */
	@Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TIME")
	private Date createTime;
	
	/** 序号 */
	@Column(name = "SORT", length = 6)
	private Long sort;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getRecommender() {
		return recommender;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditStatusCN() {
		this.auditStatusCN = RecommendProjectEnum.getAuditStatusCN(this.auditStatus);
		return this.auditStatusCN;
	}

	public void setAuditStatusCN(String auditStatusCN) {
		this.auditStatusCN = auditStatusCN;
	}

	public Boolean getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Boolean useStatus) {
		this.useStatus = useStatus;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
}
