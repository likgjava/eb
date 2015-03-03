package com.gpcsoft.epp.oppugnrequisition.domain;

import java.util.Date;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 *  Comments: <strong>Consignprotocol</strong>质疑申请书表<br/>            		
 *	<br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 权限平台     		
 *  <br/>Create Date：2010-4-14 上午02:59:07 by yemx    					                            
 *  <br/>Modified Date:  2010-4-14 上午02:59:07 by yemx                                 
 *  @gpcsoft.package packageDir="com.gpcsoft.epp.oppugnrequisition"
 *  @gpcsoft.page domain="oppugnrequisition" project="es/planform"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="质疑"    
 *  @since 0.4
 *  @version: 0.5
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_OPPUGNREQUISITION")
@SuppressWarnings("serial")
@OrderProperty(property="createTime", flag="true")
public class OppugnRequisition implements GpcBaseObject,IPropertyCUserTime, IPropertyUUserTime {

	@Id
	@Column(name = "OPPU_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;  //主键
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="PROJ_ID")
	private Project project; //关联项目
	
//	@OneToOne(mappedBy="oppugnRequisition",fetch = FetchType.LAZY) 
//	private OppugnReply oppugnReply; //质疑回复
	
	@Column(name = "SUB_PROJ_ID", length = 36)
	private String subProjId; // 质疑包组ID
	
	@Column(name = "OPPU_REPLYER", length = 36)
	private String oppuReplyer; // 质疑受理人
	
	@Column(name = "OPPU_TYPE", length = 50)
	private String oppuType; // 质疑类型（注：采购文件质疑、采购过程质疑、初定采购结果质疑、其它）
	
	@Transient
	private String oppuTypeCN;
	
	@Column(name="OPPU_TARGETTYPE",length=50)
	private String consBuyerName; //质疑对象（注：采购人、代理机构、两者）
	
	@Transient
	private String consBuyerNameCN;

	@Column(name="OPPU_CONTENT",length=500)
	private String oppuContent; //质疑内容
	
	@Column(name="ATTACH_RELA_ID",length=36)
	private String attachRelaId; //关联附件

	@Column(name="OPPU_REMARK",length=500)
	private String oppuRemark; //备注
	
	@Column(name="OPPU_LINKER",length=50)
	private String oppuLinker; //联系人
	
	@Column(name="OPPU_LINKER_TEL",length=50)
	private String oppuLinkerTel; //联系人电话
	
	@Column(name="OPPU_LINKER_FAX",length=50)
	private String oppuLinkerFax; //联系人传真
	
	@Column(name="OPPU_LINKER_EMAIL",length=50)
	private String oppuLinkerEmail; //联系人电子邮件地址

	@Column(name="OPPU_LINKER_ADDRESS",length=100)
	private String oppuLinkerAddress; //联系地址
	
	@Column(name="USE_STATUS",length=2)
	private String useStatus; //使用状态[00:临时;01:正式;02:作废]

	@Column(name="OPPU_STATUS")
	private String oppuStatus; //答复状态[00:未答复;01:已答复;]
	
	@OneToMany(mappedBy = "oppugnRequisition",fetch=FetchType.LAZY)
	@LazyCollection(value=LazyCollectionOption.EXTRA)
    @Cascade({CascadeType.ALL})
    @OrderBy(value = "createTime desc")
    private Set<OppugnReply> replys;//答复集合

	/** 回复的质疑数目 */
    @Formula("(select count(t.oppu_id) from ECP_OPPUGNREPLY t where t.oppu_id = OPPU_ID)")
    private Integer replyCount;
	
	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}
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
	@JoinColumn(name="MODIFY_USER", updatable = false)//关联的外键	 
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getSubProjId() {
		return subProjId;
	}

	public void setSubProjId(String subProjId) {
		this.subProjId = subProjId;
	}

	/**
	 * @gpcsoft.property title="质疑受理人"
	 */
	public String getOppuReplyer() {
		return oppuReplyer;
	}

	public void setOppuReplyer(String oppuReplyer) {
		this.oppuReplyer = oppuReplyer;
	}

	/**
	 * @gpcsoft.query
	 * @gpcsoft.property title="质疑类型"
	 */
	public String getOppuType() {
		return oppuType;
	}

	public void setOppuType(String oppuType) {
		this.oppuType = oppuType;
	}

	/**
	 * @gpcsoft.query
	 * @gpcsoft.property title="质疑对象"
	 */
	public String getConsBuyerName() {
		return consBuyerName;
	}

	public void setConsBuyerName(String consBuyerName) {
		this.consBuyerName = consBuyerName;
	}

	/**
	 * @gpcsoft.property title="质疑内容"
	 */
	public String getOppuContent() {
		return oppuContent;
	}

	public void setOppuContent(String oppuContent) {
		this.oppuContent = oppuContent;
	}

	/**
	 * @gpcsoft.property title="关联附件"
	 */
	public String getAttachRelaId() {
		return attachRelaId;
	}

	public void setAttachRelaId(String attachRelaId) {
		this.attachRelaId = attachRelaId;
	}

	/**
	 * @gpcsoft.property title="备注"
	 */
	public String getOppuRemark() {
		return oppuRemark;
	}

	public void setOppuRemark(String oppuRemark) {
		this.oppuRemark = oppuRemark;
	}

	/**
	 * @gpcsoft.property title="联系人"
	 */
	public String getOppuLinker() {
		return oppuLinker;
	}

	public void setOppuLinker(String oppuLinker) {
		this.oppuLinker = oppuLinker;
	}

	/**
	 * @gpcsoft.property title="联系人电话"
	 */
	public String getOppuLinkerTel() {
		return oppuLinkerTel;
	}

	public void setOppuLinkerTel(String oppuLinkerTel) {
		this.oppuLinkerTel = oppuLinkerTel;
	}

	/**
	 * @gpcsoft.property title="联系人传真"
	 */
	public String getOppuLinkerFax() {
		return oppuLinkerFax;
	}

	public void setOppuLinkerFax(String oppuLinkerFax) {
		this.oppuLinkerFax = oppuLinkerFax;
	}

	/**
	 * @gpcsoft.property title="联系人电子邮件地址"
	 */
	public String getOppuLinkerEmail() {
		return oppuLinkerEmail;
	}

	public void setOppuLinkerEmail(String oppuLinkerEmail) {
		this.oppuLinkerEmail = oppuLinkerEmail;
	}

	/**
	 * @gpcsoft.property title="联系人地址"
	 */
	public String getOppuLinkerAddress() {
		return oppuLinkerAddress;
	}

	public void setConsFinishTime(String oppuLinkerAddress) {
		this.oppuLinkerAddress = oppuLinkerAddress;
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
	 * @gpcsoft.property title="修改人"
	 */
	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * @gpcsoft.property title="修改时间"
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

//	public OppugnReply getOppugnReply() {
//		return oppugnReply;
//	}
//
//	public void setOppugnReply(OppugnReply oppugnReply) {
//		this.oppugnReply = oppugnReply;
//	}

	public void setOppuLinkerAddress(String oppuLinkerAddress) {
		this.oppuLinkerAddress = oppuLinkerAddress;
	}
	
	public String getOppuTypeCN() {
		this.oppuTypeCN = OppugnTargetTypeEnum.getOppuTypeCN(this.getOppuType());
		return oppuTypeCN;
	}
	public void setOppuTypeCN(String oppuTypeCN) {
		this.oppuTypeCN = oppuTypeCN;
	}

	public String getConsBuyerNameCN() {
		this.consBuyerNameCN = OppugnTargetTypeEnum.getConsBuyerNameCN(this.getConsBuyerName());
		return consBuyerNameCN;
	}
	public void setConsBuyerNameCN(String consBuyerNameCN) {
		this.consBuyerNameCN = consBuyerNameCN;
	}
	
	public String getOppuStatus() {
		return oppuStatus;
	}

	public void setOppuStatus(String oppuStatus) {
		this.oppuStatus = oppuStatus;
	}

	public Set<OppugnReply> getReplys() {
		return replys;
	}

	public void setReplys(Set<OppugnReply> replys) {
		this.replys = replys;
	}
	
}
