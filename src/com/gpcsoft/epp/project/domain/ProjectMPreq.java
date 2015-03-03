package com.gpcsoft.epp.project.domain;

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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.epp.requirement.domain.PreqEntry;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>ProjectMPreq</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 执行平台    		
  *  <br/>Create Date：2010-6-24 上午10:54:59 by yangx    					                            
  *  <br/>Modified Date:  2010-6-24 上午10:54:59 by yangx                                   
  *  @gpcsoft.package packageDir="com.gpcsoft.epp.project"
  *  @gpcsoft.page domain="planform/project" project="es"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="标段与需求条目中间表"  
  *  @since 0.1
  *  @version: 0.1 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)

@Table(name = "ECP_PROJ_PREQ_ENTRY")
public class ProjectMPreq implements GpcBaseObject,IPropertyCUserTime, IPropertyUUserTime{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PROJ_M_PREQ_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; //主键
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="Ten_TenderId")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Project tproject;//招标项目

	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="TenderId")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Project project;//标段
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="PREQ_ENTRY_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private PreqEntry preqEntry;//采购需求条目
	
	@Column(name = "QUANTITY", length = 16)
	private BigDecimal quantity;//数量
	
	@Column(name = "TENDERNAME", length = 50)
	private String tenderName;//标段名称
	
	@Column(name = "TENDERNO", length = 50)
	private String tenderNo;//标段编号
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="BUYER_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private OrgInfo buyer;//采购人
	
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

	public PreqEntry getPreqEntry() {
		return preqEntry;
	}

	public void setPreqEntry(PreqEntry preqEntry) {
		this.preqEntry = preqEntry;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getTenderName() {
		return tenderName;
	}

	public void setTenderName(String tenderName) {
		this.tenderName = tenderName;
	}

	public String getTenderNo() {
		return tenderNo;
	}

	public void setTenderNo(String tenderNo) {
		this.tenderNo = tenderNo;
	}

	public OrgInfo getBuyer() {
		return buyer;
	}

	public void setBuyer(OrgInfo buyer) {
		this.buyer = buyer;
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

	public Project getTproject() {
		return tproject;
	}

	public void setTproject(Project tproject) {
		this.tproject = tproject;
	}
}