package com.gpcsoft.epp.consign.domain;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 *  Comments: <strong>Consign</strong>委托协议表<br/>            		
 *	<br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 权限平台     		
 *  <br/>Create Date：2010-4-14 上午02:59:07 by yemx    					                            
 *  <br/>Modified Date:  2010-4-14 上午02:59:07 by yemx                                 
 *  @gpcsoft.package packageDir="com.gpcsoft.epp.consign"
 *  @gpcsoft.page domain="project" project="es/planform"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="委托协议"    
 *  @since 0.4
 *  @version: 0.5
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@OrderProperty(property="consCode", flag="true")
@Table(name = "ECP_CONSIGN")
public class Consign extends WorkFlowModel implements GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CONS_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;  //委托协议ID
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="PROJ_ID")
	private Project project; //所属项目
	
	@Column(name = "CONS_CODE", length = 50)
	private String consCode; // 委托编号
	
	@Column(name = "CONS_NAME", length = 100)
	private String consName; // 委托协议名称
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@BatchSize(size = 20)
	@JoinColumn(name="CONS_BUYER_ID")
	private OrgInfo consBuyer; //采购人

	@Column(name="CONS_BUYER_NAME",length=100)
	private String consBuyerName; //采购人名称

	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="CONS_AGENT_ID")
	private OrgInfo consAgent; //代理机构
	
	@Column(name="CONS_AGENT_NAME",length=100)
	private String consAgentName; //代理机构名称

	@Column(name="CONS_BUYER_LINKER",length=20)
	private String consBuyerLinker; //采购人联系人
	
	@Column(name="CONS_BUYER_TEL",length=50)
	private String consBuyerTel; //采购人联系电话
	
	@Column(name="CONS_AGENT_LINKER",length=20)
	private String consAgentLinker; //代理机构联系人
	
	@Column(name="CONS_AGENT_TEL",length=50)
	private String consAgentTel; //代理机构联系电话
	
	@Column(name="CONS_TIME")
	private Date consTime; //委托时间

	@Column(name="CONS_FINISHTIME")
	private Date consFinishTime; //拟完成时间

	@Column(name="CONS_OPINION",length=200)
	private String consOpinion; //意见

	@Column(name="CONS_CONTENTS",length=500)
	private String consContents; //委托协议内容
	
	@Column(name="CONS_CONTENTS_ATT")
	private String consContentsAtt; //委托协议附件
	
	@Column(name="CONS_OTHERATTACHMENT",length=36)
	private String consOtherattachment; //其他附件
	
	@Column(name="CONS_BUYERSIGNDATA",length=100)
	private String consBuyerSigndata; //委托方电子签名信息
	
	@Column(name="CONS_AGENTSIGNDATA",length=100)
	private String consAgentSigndata; //受托方电子签名信息
	
	@Column(name="CONS_REMARK",length=500)
	private String consRemark; //备注
	
	@Column(name="USE_STATUS",length=2)
	private String useStatus; //使用状态[00:临时;01:正式;02:作废]
	
	@Transient
    private String useStatusCN;
	
	@Column(name = "CONFIRM_STATUS")
    private String confirmStatus;//确认状态
    
    @Transient
    private String confirmStatusCN;
    
    @LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany//相当于inverse="false" 
	@JoinColumn(name = "CONS_ID") 
	@Cascade({CascadeType.DELETE_ORPHAN})
	private Set<ConsignTaskPlan> consignTaskPlans = new HashSet<ConsignTaskPlan>();
	
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
    
    //有效开始时间
    @Column(name = "EFFECTIVE_TIME_START")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveStartTime;
    
    
    //有效结束时间
    @Column(name = "EFFECTIVE_TIME_END")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveEndTime;
    
    //委托协议类型[00:项目委托 01:长期委托]
    @Column(name = "CONS_TYPE ",length=2)
    private String consType ;
    
    
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

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/**
	 * @gpcsoft.property title="所属项目"
	 */
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	/**
	 * @gpcsoft.property title="编号"
	 */
	public String getConsCode() {
		return consCode;
	}

	public void setConsCode(String consCode) {
		this.consCode = consCode;
	}

	/**
	 * @gpcsoft.query op="like"
	 * @gpcsoft.property title="名称"
	 */
	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	/**
	 * @gpcsoft.property title="委托协议内容"
	 */
	public String getConsContentsAtt() {
		return consContentsAtt;
	}

	public void setConsContentsAtt(String consContentsAtt) {
		this.consContentsAtt = consContentsAtt;
	}

	/**
	 * @gpcsoft.query
	 * @gpcsoft.property title="采购人"
	 */
	public OrgInfo getConsBuyer() {
		return consBuyer;
	}

	public void setConsBuyer(OrgInfo consBuyer) {
		this.consBuyer = consBuyer;
	}

	/**
	 * @gpcsoft.query
	 * @gpcsoft.property title="采购人名称"
	 */
	public String getConsBuyerName() {
		return consBuyerName;
	}

	public void setConsBuyerName(String consBuyerName) {
		this.consBuyerName = consBuyerName;
	}

	/**
	 * @gpcsoft.property title="代理机构"
	 */
	public OrgInfo getConsAgent() {
		return consAgent;
	}

	public void setConsAgent(OrgInfo consAgent) {
		this.consAgent = consAgent;
	}

	/**
	 * @gpcsoft.property title="代理机构名称"
	 */
	public String getConsAgentName() {
		return consAgentName;
	}

	public void setConsAgentName(String consAgentName) {
		this.consAgentName = consAgentName;
	}

	/**
	 * @gpcsoft.property title="采购人联系人"
	 */
	public String getConsBuyerLinker() {
		return consBuyerLinker;
	}

	public void setConsBuyerLinker(String consBuyerLinker) {
		this.consBuyerLinker = consBuyerLinker;
	}

	/**
	 * @gpcsoft.property title="采购人联系电话"
	 */
	public String getConsBuyerTel() {
		return consBuyerTel;
	}

	public void setConsBuyerTel(String consBuyerTel) {
		this.consBuyerTel = consBuyerTel;
	}

	/**
	 * @gpcsoft.property title="代理机构联系人"
	 */
	public String getConsAgentLinker() {
		return consAgentLinker;
	}

	public void setConsAgentLinker(String consAgentLinker) {
		this.consAgentLinker = consAgentLinker;
	}

	/**
	 * @gpcsoft.property title="代理机构联系电话"
	 */
	public String getConsAgentTel() {
		return consAgentTel;
	}

	public void setConsAgentTel(String consAgentTel) {
		this.consAgentTel = consAgentTel;
	}

	/**
	 * @gpcsoft.property title="委托时间"
	 */
	public Date getConsTime() {
		return consTime;
	}

	public void setConsTime(Date consTime) {
		this.consTime = consTime;
	}

	/**
	 * @gpcsoft.property title="拟完成时间"
	 */
	public Date getConsFinishTime() {
		return consFinishTime;
	}

	public void setConsFinishTime(Date consFinishTime) {
		this.consFinishTime = consFinishTime;
	}

	/**
	 * @gpcsoft.property title="意见"
	 */
	public String getConsOpinion() {
		return consOpinion;
	}

	public void setConsOpinion(String consOpinion) {
		this.consOpinion = consOpinion;
	}

	/**
	 * @gpcsoft.property title="委托协议内容"
	 */
	public String getConsContents() {
		return consContents;
	}

	public void setConsContents(String consContents) {
		this.consContents = consContents;
	}

	/**
	 * @gpcsoft.property title="其他附件"
	 */
	public String getConsOtherattachment() {
		return consOtherattachment;
	}

	public void setConsOtherattachment(String consOtherattachment) {
		this.consOtherattachment = consOtherattachment;
	}

	/**
	 * @gpcsoft.property title="采购人电子签名"
	 */
	public String getConsBuyerSigndata() {
		return consBuyerSigndata;
	}

	public void setConsBuyerSigndata(String consBuyerSigndata) {
		this.consBuyerSigndata = consBuyerSigndata;
	}

	/**
	 * @gpcsoft.property title="代理机构电子签名"
	 */
	public String getConsAgentSigndata() {
		return consAgentSigndata;
	}

	public void setConsAgentSigndata(String consAgentSigndata) {
		this.consAgentSigndata = consAgentSigndata;
	}

	/**
	 * @gpcsoft.property title="备注"
	 */
	public String getConsRemark() {
		return consRemark;
	}

	public void setConsRemark(String consRemark) {
		this.consRemark = consRemark;
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

	/**
	 * @gpcsoft.property title="确认状态"
	 */
	public String getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

	/**
	 * @gpcsoft.property title="确认状态"
	 */
	public String getConfirmStatusCN() {
		this.confirmStatusCN = CommonEnum.getConfirmCN(this.confirmStatus);
		return confirmStatusCN;
	}

	public void setConfirmStatusCN(String confirmStatusCN) {
		this.confirmStatusCN = confirmStatusCN;
	}

	/**
	 * @gpcsoft.property title="使用状态"
	 */
	public String getUseStatusCN() {
		this.useStatusCN = CommonEnum.getUseCN(this.useStatus);
		return useStatusCN;
	}

	public void setUseStatusCN(String useStatusCN) {
		this.useStatusCN = useStatusCN;
	}
	
	/**
	 * @gpcsoft.property title="有效期开始时间"
	 */
	public Date getEffectiveStartTime() {
		return effectiveStartTime;
	}

	public void setEffectiveStartTime(Date effectiveStartTime) {
		this.effectiveStartTime = effectiveStartTime;
	}
	
	/**
	 * @gpcsoft.property title="有效期结束时间"
	 */
	public Date getEffectiveEndTime() {
		return effectiveEndTime;
	}

	public void setEffectiveEndTime(Date effectiveEndTime) {
		this.effectiveEndTime = effectiveEndTime;
	}
	
	/**
	 * @gpcsoft.property title="委托协议类型"
	 */
	public String getConsType() {
		return consType;
	}

	public void setConsType(String consType) {
		this.consType = consType;
	}

	public Set<ConsignTaskPlan> getConsignTaskPlans() {
		return consignTaskPlans;
	}

	public void setConsignTaskPlans(Set<ConsignTaskPlan> consignTaskPlans) {
		this.consignTaskPlans = consignTaskPlans;
	}
}
