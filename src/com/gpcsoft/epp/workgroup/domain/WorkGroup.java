package com.gpcsoft.epp.workgroup.domain;

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

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.srplatform.auth.domain.User;


/**
 * @author liuke
 * @since 0.5
 * @version: 0.6
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.workgroup"
 * @gpcsoft.page domain="planform/workgroup" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="工作小组"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_WORKGROUP")
public class WorkGroup implements GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime{
	@Id
	@Column(name = "WORKG_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="PROJ_ID") 		
	private Project project; //采购项目
	
	@Column(name = "WORKG_TYPE", length = 50)
	private String workgType; // 小组类型：论证小组，评审小组，开标小组,工作小组
	
	//modify by yangx
	@Transient
	private String workgTypeCN;

	@Column(name = "WORKG_REMARK", length = 500)
	private String workgRemark; // 备注[描述工作组的职责]
	
	
	@LazyCollection(value=LazyCollectionOption.FALSE)
    @OneToMany
    @JoinColumn(name = "WORK_ID") 
    @Cascade({CascadeType.ALL})
    private Set<WorkgMember> memberSet = new HashSet<WorkgMember>();
	
	
	@Column(name = "USE_STATUS")
	private Character useStatus;//使用状态
	
	
	/** 创建人 */
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

    /********************************GET和SET方法**********************************************/
    
    public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
    public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	/**
	 * @gpcsoft.property title="小组类型"
	 * @gpcsoft.validate class="required"
	 */
	public String getWorkgType() {
		return workgType;
	}

	public void setWorkgType(String workgType) {
		this.workgType = workgType;
	}
	/**
	 * @gpcsoft.property title="备注"
	 * 
	 */
	public String getWorkgRemark() {
		return workgRemark;
	}

	public void setWorkgRemark(String workgRemark) {
		this.workgRemark = workgRemark;
	}

	public Character getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Character useStatus) {
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

	public Set<WorkgMember> getMemberSet() {
		return memberSet;
	}

	public void setMemberSet(Set<WorkgMember> memberSet) {
		this.memberSet = memberSet;
	}
	
	public String getWorkgTypeCN() {
		workgTypeCN=WorkGroupTypeEnum.getCN(this.getWorkgType());
		return workgTypeCN;
	}

	public void setWorkgTypeCN(String workgTypeCN) {
		this.workgTypeCN = workgTypeCN;
	}

}
