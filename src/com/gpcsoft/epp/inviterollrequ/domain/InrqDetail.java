package com.gpcsoft.epp.inviterollrequ.domain;

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
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;

/**                                
 *  @gpcsoft.package packageDir="com.gpcsoft.epp.inviterollrequ"
 *  @gpcsoft.page domain="planform/inviterollrequ" project="es"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="邀请名单"    
 *  @since 0.4
 *  @version: 0.5
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_INRQ_DETAIL")
@SuppressWarnings("serial")
public class InrqDetail implements GpcBaseObject,IPropertyCUserTime, IPropertyUUserTime{
	@Id
	@Column(name ="INRQ_D_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;  //供应商确认记录ID
		
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="INRQ_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private Inviterollrequ inviterollrequ;  //邀请名单申请单
	
	/** 关联包组*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="SUB_PROJ_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private Project subProject;
	
	@Column(name = "SUB_PROJ_CODE", length = 100)
	private String subProjCode;//包组编号
	
	@Column(name = "SUB_PROJ_NAME", length = 50)
	private String subProjName;//包组名称
	
	@ManyToOne(fetch=FetchType.EAGER)
	@BatchSize(size = 20)
	@JoinColumn(name="SUPPLIER_ID")
	private OrgInfo supplier; //供应商
	
	@Column(name = "SUPPLIER_NAME")
	private String supplierName; //供应商名称	
	
	@Column(name = "INRQ_D_RECEM_RESON", length = 500)
	private String inrqDRecemReson;//推荐理由
	
	@Column(name = "INRQ_D_KIND", length = 50)
	private String inrqDKind;//邀请函种类
	
	@Transient
	private String inrqDKindCN;
	
	 /** 内容 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="INRQ_D_CONTENT")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Attachment inrqDContent; //邀请函件内容[关联附件ID]
	
	@Transient
	private String contents;//内容[显示]
	
	@Column(name = "USE_STATUS")
	private String useStatus;//使用状态
		
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
	@JoinColumn(name="MODIFY_USER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User updateUser;
    
    //修改时间
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
	
    /********************************************************************************/    
    public Attachment getInrqDContent() {
		return inrqDContent;
	}

	public void setInrqDContent(Attachment inrqDContent) {
		this.inrqDContent = inrqDContent;
	}

    
    public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
    
    public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Inviterollrequ getInviterollrequ() {
		return inviterollrequ;
	}

	public void setInviterollrequ(Inviterollrequ inviterollrequ) {
		this.inviterollrequ = inviterollrequ;
	}

	public Project getSubProject() {
		return subProject;
	}

	public void setSubProject(Project subProject) {
		this.subProject = subProject;
	}

	public String getSubProjCode() {
		return subProjCode;
	}

	public void setSubProjCode(String subProjCode) {
		this.subProjCode = subProjCode;
	}

	public String getSubProjName() {
		return subProjName;
	}

	public void setSubProjName(String subProjName) {
		this.subProjName = subProjName;
	}

	public OrgInfo getSupplier() {
		return supplier;
	}

	public void setSupplier(OrgInfo supplier) {
		this.supplier = supplier;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getInrqDRecemReson() {
		return inrqDRecemReson;
	}

	public void setInrqDRecemReson(String inrqDRecemReson) {
		this.inrqDRecemReson = inrqDRecemReson;
	}

	public String getInrqDKind() {
		return inrqDKind;
	}

	public void setInrqDKind(String inrqDKind) {
		this.inrqDKind = inrqDKind;
	}



	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
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

	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getInrqDKindCN() {
		inrqDKindCN = InrqKindEnum.getCN(this.getInrqDKind());
		return inrqDKindCN;
	}

	public void setInrqDKindCN(String inrqDKindCN) {
		this.inrqDKindCN = inrqDKindCN;
	}
}
