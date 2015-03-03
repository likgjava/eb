package com.gpcsoft.epp.workgroup.domain;

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
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @author liuke
 * @since 0.5
 * @version: 0.6
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.workgroup"
 * @gpcsoft.page domain="planform/workgroup" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="工作小组成员"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_WORKG_MEMBER")
public class WorkgMember extends WorkFlowModel implements GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "WORKG_M_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@ManyToOne(fetch=FetchType.EAGER)
	@BatchSize(size = 20)
	@JoinColumn(name="WORK_ID") 		
	private WorkGroup workGroup; //工作组
	
	@Column(name = "WORKG_M_NAME", length = 50)
	private String workgmName; // 成员名称
	
	@Column(name = "WORKG_M_TYPE", length = 50)
	private String workgmType; // 成员类型：[00：采购人代表;01：专家;02:经办人；03：监察人员等]
	
	@Transient
	private String workgmTypeCN;
	
	@Column(name = "WORKG_M_DUTY", length = 50)
	private String workgmDuty; // 职称[一般在成员为专家时需要]

	@Column(name = "WORKG_M_SPECIALITY", length = 50)
	private String workgmSpeciality; //专业[一般在成员为专家时需要]
	
	@Column(name = "WORKG_M_ACCOUNT", length = 50)
	private String workgmAccount; //登录帐号
	
	@Column(name = "WORKG_M_PASSWORD", length = 50)
	private String workgmPassWord; //登录密码
	
	@Column(name = "WORKG_M_CERT", length = 50)
	private String workgmCert; //成员证书文件[KEY对应的证书，一般用于开标人]
	
	@Column(name = "WORKG_M_ISLEADER")
	private String workgmIsLeader; //00:组长01:副组长
	
	@Transient
	private String workgmIsLeaderCN;
	@Column(name = "WORKG_M_SIGNIN_REMARK", length = 50)
	private String workgmSigninRemark; //签到申请[专家签到使用]
	
	@Column(name = "SIGNIN_STATUS", length = 2)
	private String signinStatus; //签到状态[00:未签到;01：已签到]
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="SUBPROJID") 
	private Project subProject; //包组
	
	@Column(name = "WORKG_M_LINKERPHONE")
	private String linkerPhone; //联系电话

	@Column(name = "WORKG_M_API_ID")
	private String workgMAPIId; //外库唯一标识ID
	
	@Column(name = "ISAMOUNT")
	private String isAmount;//正选专家：00,备选专家：01

	@Transient
	private String signinStatusCN;  //签到状态
	
	@Column(name = "USE_STATUS")
	private Character useStatus;//使用状态
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User user;
	
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
	@JoinColumn(name="MODIFY_USER", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User updateUser;
    
    //修改时间
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    
	@Column(name = "WORKG_M_Company", length = 50)
	private String workgmCompany; // 成员所在单位
	
    /********************************GET和SET方法**********************************************/
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public WorkGroup getWorkGroup() {
		return workGroup;
	}

	public void setWorkGroup(WorkGroup workGroup) {
		this.workGroup = workGroup;
	}
	
	public String getSigninStatus() {
		return signinStatus;
	}

	public void setSigninStatus(String signinStatus) {
		this.signinStatus = signinStatus;
	}
	
	public String getIsAmount() {
		return isAmount;
	}

	public void setIsAmount(String isAmount) {
		this.isAmount = isAmount;
	}

	/**
	 * @gpcsoft.property title="签到状态"
	 */
	public String getSigninStatusCN() {
		if(null == signinStatus){
			signinStatus = SigninTypeEnum.SIGNIN_NO;
		}
		this.signinStatusCN = SigninTypeEnum.getCN(this.getSigninStatus());
		return signinStatusCN;
	}

	public void setSigninStatusCN(String signinStatusCN) {
		this.signinStatusCN = signinStatusCN;
	}
	
	
	/**
	 * @gpcsoft.property title="成员名称"
	 * @gpcsoft.validate class="required"
	 */
	public String getWorkgmName() {
		return workgmName;
	}

	public void setWorkgmName(String workgmName) {
		this.workgmName = workgmName;
	}
	/**
	 * @gpcsoft.property title="成员类型"
	 * @gpcsoft.validate class="required"
	 */
	public String getWorkgmType() {
		return workgmType;
	}

	public void setWorkgmType(String workgmType) {
		this.workgmType = workgmType;
	}
	/**
	 * @gpcsoft.property title="职称"
	 * @gpcsoft.validate class="required"
	 */
	public String getWorkgmDuty() {
		return workgmDuty;
	}

	public void setWorkgmDuty(String workgmDuty) {
		this.workgmDuty = workgmDuty;
	}
	/**
	 * @gpcsoft.property title="专业"
	 * @gpcsoft.validate class="required"
	 */
	public String getWorkgmSpeciality() {
		return workgmSpeciality;
	}

	public void setWorkgmSpeciality(String workgmSpeciality) {
		this.workgmSpeciality = workgmSpeciality;
	}
	/**
	 * @gpcsoft.property title="登陆账号"
	 * @gpcsoft.validate class="required"
	 */
	public String getWorkgmAccount() {
		return workgmAccount;
	}

	public void setWorkgmAccount(String workgmAccount) {
		this.workgmAccount = workgmAccount;
	}
	/**
	 * @gpcsoft.property title="登陆密码"
	 * @gpcsoft.validate class="required"
	 */
	public String getWorkgmPassWord() {
		return workgmPassWord;
	}

	public void setWorkgmPassWord(String workgmPassWord) {
		this.workgmPassWord = workgmPassWord;
	}
    
	public String getWorkgmCert() {
		return workgmCert;
	}

	public void setWorkgmCert(String workgmCert) {
		this.workgmCert = workgmCert;
	}


	public String getWorkgmSigninRemark() {
		return workgmSigninRemark;
	}

	/**
	 * @gpcsoft.property title="是否为组长"
	 * @gpcsoft.validate class="required"
	 */
	public String getWorkgmIsLeader() {
		return workgmIsLeader;
	}

	public void setWorkgmIsLeader(String workgmIsLeader) {
		this.workgmIsLeader = workgmIsLeader;
	}

	public void setWorkgmSigninRemark(String workgmSigninRemark) {
		this.workgmSigninRemark = workgmSigninRemark;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getSubProject() {
		return subProject;
	}

	public void setSubProject(Project subProject) {
		this.subProject = subProject;
	}

	public String getLinkerPhone() {
		return linkerPhone;
	}

	public void setLinkerPhone(String linkerPhone) {
		this.linkerPhone = linkerPhone;
	}

	public String getWorkgMAPIId() {
		return workgMAPIId;
	}

	public void setWorkgMAPIId(String workgMAPIId) {
		this.workgMAPIId = workgMAPIId;
	}

	public String getWorkgmTypeCN() {
		this.workgmTypeCN = WorkMemberTypeEnum.getCN(this.getWorkgmType());
		return workgmTypeCN;
	}

	public void setWorkgmTypeCN(String workgmTypeCN) {
		this.workgmTypeCN = workgmTypeCN;
	}

	public String getWorkgmIsLeaderCN() {
		this.workgmIsLeaderCN = WorkMemberTypeEnum.workgmIsLeaderCN(this.getWorkgmIsLeader().trim());
		return workgmIsLeaderCN;
	}
	public void setWorkgmIsLeaderCN(String workgmIsLeaderCN) {
		this.workgmIsLeaderCN = workgmIsLeaderCN;
	}
	public String getWorkgmCompany() {
		return workgmCompany;
	}

	public void setWorkgmCompany(String workgmCompany) {
		this.workgmCompany = workgmCompany;
	}
}
