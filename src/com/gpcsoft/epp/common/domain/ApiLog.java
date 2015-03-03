package com.gpcsoft.epp.common.domain;

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

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>ApiLog</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 执行平台    		
  *  <br/>Create Date：2010-9-13 下午02:06:48 by yangx    					                            
  *  <br/>Modified Date:  2010-9-13 下午02:06:48 by yangx                                   
 *  @gpcsoft.package packageDir="com.gpcsoft.epp.common"
 *  @gpcsoft.page domain="planform/common" project="es"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="日志记录"    
 *  @since 0.4
 *  @version: 0.5
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_LOG_RECORD")
public class ApiLog extends WorkFlowModel implements GpcBaseObject, IPropertyCUserTime {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LOG_RECORD_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;  //主键ID

	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="TenderID") 
	private Project project; //所属项目
	
	@Column(name = "BIZ_ID")
	private String bizId; // 业务ID

	@Column(name = "API_TYPE")
	private String apiType; // 交换类型[公告:00;待办任务:01;短消息:02]

	@Transient
	private String apiTypeCN;
	
	@Column(name = "TARGET")
	private String target; // 发布的目标地编号[金采网,财政网等等]

	@Column(name = "TARGET_NAME")
	private String targetName; // 发布的目标地名称

	@Column(name = "DOCOUNT")
	private BigDecimal doCount; // 操作次数

	@Column(name = "STATUS")
	private String status; //是否发布成功[成功:"true";失败:"false";异常:"exception"(当多次或其他方式多次处理后仍不能操作成功的,设置为异常)]
	
	@Transient
	private String statusCN;
	
	@Column(name = "LOG")
	private String log; // 日志信息,记录数据交换过程中的操作日志
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CreUser", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User createUser;

    //创建时间
    @Column(name = "CreDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getApiType() {
		return apiType;
	}

	public void setApiType(String apiType) {
		this.apiType = apiType;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
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

	public BigDecimal getDoCount() {
		return doCount;
	}

	public void setDoCount(BigDecimal doCount) {
		this.doCount = doCount;
	}

	public String getApiTypeCN() {
		apiTypeCN = ApiLogTypeEnum.getCN(this.getApiType());
		return apiTypeCN;
	}

	public void setApiTypeCN(String apiTypeCN) {
		this.apiTypeCN = apiTypeCN;
	}

	public String getStatusCN() {
		statusCN = ApiLogStatusEnum.getCN(this.getStatus());
		return statusCN;
	}

	public void setStatusCN(String statusCN) {
		this.statusCN = statusCN;
	}
}
