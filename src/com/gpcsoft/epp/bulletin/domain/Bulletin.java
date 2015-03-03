package com.gpcsoft.epp.bulletin.domain;

import java.util.Date;

import javax.persistence.CascadeType;
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
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 *  Comments: <strong>Consignprotocol</strong>公告表<br/>            		
 *	<br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 权限平台     	
 *  <br/>Create Date：2010-4-14 上午02:59:07 by yemx    					                            
 *  <br/>Modified Date:  2010-4-14 上午02:59:07 by yemx                                 
 *  @gpcsoft.package packageDir="com.gpcsoft.epp.bulletin"
 *  @gpcsoft.page domain="bulletin" project="es/planform"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="公告"    
 *  @since 0.4
 *  @version: 0.5
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_Base_Bulletin")
public class Bulletin extends WorkFlowModel implements GpcBaseObject, IPropertyCUserTime {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Bulletin_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;  //公告ID
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="TenderID") 
	private Project project; //所属项目
	
	@Column(name = "Bulletin_No", length = 50)
	private String bulletinNo; // 编号
	
	@Column(name="Bulletin_Type",length=2)
	private String bullType; //公告类型 
	
	@Transient
	private String bullTypeCN; //公告类型 

	@Column(name = "Title", length = 80)
	private String bullTitle; // 公告标题
	
	@ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	@JoinColumn(name="CONTENT")//关联的外键
	private Attachment content; //公告内容[关联附件ID]
	
	@Transient
	private String bullContents;//公告内容[显示]
	
	@Column(name = "SignUp_Start_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date signUpSTime;//报名开始时间
	
	@Column(name = "SignUp_End_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date signUpETime;//报名结束时间
	
	
	@Column(name = "Submit_Start_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tenderStartTime;//投标开始时间
	
	@Column(name = "Submit_End_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tenderEndTime;//投标结束时间
	
	@Column(name = "AuditStatus", length = 2)
	private String auditStatus;//审核状态00：待审核；01：审核通过；02：审核不通过
	
	@Column(name = "RelStatus", length = 2)
	private String relStatus;// 发布状态 00：未发布；01：已发布；02：撤回
	
	@Transient
	private String relStatusCN;// 发布状态 00：未发布；01：已发布；02：撤回
	
	@Column(name = "RelDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date relDate;// 发布时间  发布状态改为“已发布”时取Sysdate
	
	@Column(name = "PURCATEGORY_NAMES")
	private String purcategoryNames;// 品目名称[冗余，以'，'分隔]
		
	@Column(name = "PURCATEGORY_IDS")
	private String purcategoryIds;// 品目ID[冗余，以'，'分隔]
	
	/** 发布人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="RelUser", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private Employee relUser;
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CreUser", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User createUser;

    //创建时间
    @Column(name = "CreDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Column(name = "UseStatus", length = 2)
	private String useStatus;//使用状态
    
    @Transient
	private String opinion;//审核意见
    
   
	@Transient
    private Date openBidTime;     //开标时间
    
    @Transient
    private Date evalStartTime;    //评标开始时间
    
  //modify by liuke
	/**当前有效id*/
	@Column(name = "CURRENT_VALID_ID")
	private String currentId;
    
    public String getCurrentId() {
		return currentId;
	}

	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}
	
	/**
	 * @gpcsoft.property title="发布状态"
	 */
	public String getRelStatusCN() {
		this.relStatusCN  = BulletinTypeEnum.getRelStatusCN(this.relStatus);
		return relStatusCN;
	}

	public void setRelStatusCN(String relStatusCN) {
		this.relStatusCN = relStatusCN;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

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

	/**
	 * @gpcsoft.property title="编号"
	 */
	public String getBulletinNo() {
		return bulletinNo;
	}

	public void setBulletinNo(String bulletinNo) {
		this.bulletinNo = bulletinNo;
	}

	/**
	 * @gpcsoft.property title="公告类型"
	 */
	public String getBullType() {
		return bullType;
	}

	public void setBullType(String bullType) {
		this.bullType = bullType;
	}

	/**
	 * @gpcsoft.property title="公告标题"
	 */
	public String getBullTitle() {
		return bullTitle;
	}

	public void setBullTitle(String bullTitle) {
		this.bullTitle = bullTitle;
	}

	/**
	 * @gpcsoft.property title="报名开始时间"
	 */
	public Date getSignUpSTime() {
		return signUpSTime;
	}

	public void setSignUpSTime(Date signUpSTime) {
		this.signUpSTime = signUpSTime;
	}

	/**
	 * @gpcsoft.property title="公告内容"
	 */
	public Attachment getContent() {
		return content;
	}

	public void setContent(Attachment content) {
		this.content = content;
	}
	
	/**
	 * @gpcsoft.property title="投标开始时间"
	 */
	public Date getTenderStartTime() {
		return tenderStartTime;
	}

	public void setTenderStartTime(Date tenderStartTime) {
		this.tenderStartTime = tenderStartTime;
	}

	/**
	 * @gpcsoft.property title="投标结束时间"
	 */
	public Date getTenderEndTime() {
		return tenderEndTime;
	}

	public void setTenderEndTime(Date tenderEndTime) {
		this.tenderEndTime = tenderEndTime;
	}

	/**
	 * @gpcsoft.property title="审核状态"
	 */
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * @gpcsoft.property title="发布状态"
	 */
	public String getRelStatus() {
		return relStatus;
	}

	
	public void setRelStatus(String relStatus) {
		this.relStatus = relStatus;
	}

	/**
	 * @gpcsoft.property title="发布时间"
	 */
	public Date getRelDate() {
		return relDate;
	}

	public void setRelDate(Date relDate) {
		this.relDate = relDate;
	}

	/**
	 * @gpcsoft.property title="发布人"
	 */
	public Employee getRelUser() {
		return relUser;
	}

	public void setRelUser(Employee relUser) {
		this.relUser = relUser;
	}

	/**
	 * @gpcsoft.property title="创建人"
	 */
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	/**
	 * @gpcsoft.property title="创建时间"
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @gpcsoft.property title="使用状态"
	 */
	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	/**
	 * @gpcsoft.property title="报名结束时间"
	 */
	public Date getSignUpETime() {
		return signUpETime;
	}

	public void setSignUpETime(Date signUpETime) {
		this.signUpETime = signUpETime;
	}

	/**
	 * @gpcsoft.property title="内容"
	 */
	public String getBullContents() {
		return bullContents;
	}

	public void setBullContents(String bullContents) {
		this.bullContents = bullContents;
	}

	/**
	 * @gpcsoft.property title="公告类型"
	 */
	public String getBullTypeCN() {
		this.bullTypeCN = BulletinTypeEnum.getCN(this.getBullType());
		return this.bullTypeCN;
	}
	
	public void setBullTypeCN(String bullTypeCN) {
		this.bullTypeCN = bullTypeCN;
	}
	
	public Date getOpenBidTime() {
		return openBidTime;
	}

	public void setOpenBidTime(Date openBidTime) {
		this.openBidTime = openBidTime;
	}

	/**
	 * @gpcsoft.property title="开标时间"
	 */
	public Date getEvalStartTime() {
		return evalStartTime;
	}
	
	/**
	 * @gpcsoft.property title="评标开始时间"
	 */
	public void setEvalStartTime(Date evalStartTime) {
		this.evalStartTime = evalStartTime;
	}

	public String getPurcategoryNames() {
		return purcategoryNames;
	}

	public void setPurcategoryNames(String purcategoryNames) {
		this.purcategoryNames = purcategoryNames;
	}

	public String getPurcategoryIds() {
		return purcategoryIds;
	}

	public void setPurcategoryIds(String purcategoryIds) {
		this.purcategoryIds = purcategoryIds;
	}
}
