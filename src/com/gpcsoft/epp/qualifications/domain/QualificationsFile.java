package com.gpcsoft.epp.qualifications.domain;

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
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;


/** 
 *  Comments: <strong>QualificationsFile</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   es                    					          
 *  <br/>Module ID: 资格预审文件	
 *  <br/>Create Date：2011-11-6 14:18 by shengn    		
 *  			                            
 *  @gpcsoft.package packageDir="com.gpcsoft.epp.qualifications"
 *  @gpcsoft.page domain="planform/qualifications" project="es"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="资格预审文件"  
 *  @since 0.1
 *  @version: 0.1 
 */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_QUALIFICATIONS_FILE")
public class QualificationsFile extends WorkFlowModel implements GpcBaseObject{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PREREVIEWID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@Column(name = "PREREVIEWNO", length = 50)
	private String preReviewNo; // 预审编号
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(
	name="TenderId")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Project project; // 招标项目
	
	@Column(name = "KEYWORD", length = 50)
	private String keyWord; // 关键字
	
	@Column(name = "CONTENT", length = 1000)
	private String content; //摘要
	
	@Column(name = "QUA_FILE", length = 50)
	private String quaFile; //预审文件
	
	@Column(name = "AuditStatus", length = 2)
	private String auditStatus;//审核状态00：待审核；01：审核通过；02：审核不通过
	
	@Column(name = "RelStatus", length = 2)
	private String relStatus;// 发布状态00：未发布；01：已发布；02：撤回
	
	@Transient
	private String relStatusCN;
	
	@Column(name = "RELDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date relDate;// 发布时间  发布状态改为“已发布”时取Sysdate
	
	/** 发布人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="RELUSER", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User relUser;
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREUSER", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User createUser;

    //创建时间
    @Column(name = "CREDATE", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Column(name = "USESTATUS", length = 2)
	private String useStatus;//使用状态

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getPreReviewNo() {
		return preReviewNo;
	}

	public void setPreReviewNo(String preReviewNo) {
		this.preReviewNo = preReviewNo;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getQuaFile() {
		return quaFile;
	}

	public void setQuaFile(String quaFile) {
		this.quaFile = quaFile;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getRelStatus() {
		return relStatus;
	}

	public void setRelStatus(String relStatus) {
		this.relStatus = relStatus;
	}

	public String getRelStatusCN() {
		return relStatusCN;
	}

	public void setRelStatusCN(String relStatusCN) {
		this.relStatusCN = relStatusCN;
	}

	public Date getRelDate() {
		return relDate;
	}

	public void setRelDate(Date relDate) {
		this.relDate = relDate;
	}

	public User getRelUser() {
		return relUser;
	}

	public void setRelUser(User relUser) {
		this.relUser = relUser;
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

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
    
    
}
