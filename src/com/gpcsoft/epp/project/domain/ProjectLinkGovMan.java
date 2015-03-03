package com.gpcsoft.epp.project.domain;

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

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 *  Comments: <strong>TaskPlan</strong>项目经办人表<br/>            		
 *	<br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 权限平台     		
 *  <br/>Create Date：2010-4-14 上午02:59:07 by yemx    					                            
 *  <br/>Modified Date:  2010-4-14 上午02:59:07 by yemx                                 
 *  @gpcsoft.package packageDir="com.gpcsoft.es.project"
 *  @gpcsoft.page domain="project" project="es"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="项目经办人"    
 *  @since 0.4
 *  @version: 0.5
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PROJ_LinkGovMan")
@SuppressWarnings("serial")
public class ProjectLinkGovMan implements GpcBaseObject, IPropertyCUserTime, IPropertyUUserTime {

	@Id
	@Column(name = "LinkGovMan_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // ID
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="PROJ_ID") 
	private Project project; // 项目
	
	@Column(name = "LINKGOVMAN", length = 50)
	private String linkGovMan; // 经办人
	
	@Column(name = "NAME", length = 50)
	private String name; // 姓名

	@Column(name="TELEPHONE",length=50)
	private String telephone; //电话

	@Column(name="FAX",length=20)
	private String fax; //传真
	
	@Column(name="EMAIL",length=60)
	private String email; //邮箱
	
	@Column(name="MOBILE",length=50)
	private String mobile; //手机
	
	@Column(name="POSTCODE",length=6)
	private String postCode; //邮编
	
	@Column(name="ADDRESS",length=200)
	private String address; //地址
	
	@Column(name="REMARK",length=500)
	private String remark; //备注
	
	@Column(name = "USE_STATUS", length = 2)
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
	@JoinColumn(
	name="MODIFY_USER", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
   	private User updateUser;
    
    //修改时间
	@Column(name = "MODIFY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	/**
	 * @gpcsoft.property title="备注"
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	 * @gpcsoft.property title="经办人"
	 */
	public String getLinkGovMan() {
		return linkGovMan;
	}

	public void setLinkGovMan(String linkGovMan) {
		this.linkGovMan = linkGovMan;
	}

	/**
	 * @gpcsoft.property title="姓名"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @gpcsoft.property title="电话"
	 */
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @gpcsoft.property title="传真"
	 */
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @gpcsoft.property title="邮箱"
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @gpcsoft.property title="手机"
	 */
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @gpcsoft.property title="邮编"
	 */
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @gpcsoft.property title="地址"
	 */
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

}
