package com.gpcsoft.epp.purchasedoc.domain;
import java.util.Date;
import java.util.List;

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
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @author liuke
 * @since 0.5
 * @version: 0.6
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.purchasedoc"
 * @gpcsoft.page domain="planform/purchasedoc" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="采购文件论证"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PROOF_OPINION")
public class ProofOpinion extends WorkFlowModel implements GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime
{
	@Id
	@Column(name = "MEM_PROOF_O_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@Column(name = "WORKG_M_ID", length = 50)
	private String workgMId; //成员ID

	@Column(name = "NAME", length = 50)
	private String name; //名称
	
	@Column(name = "PHONE", length = 50)
	private String phone; //电话
	
	@Column(name = "TENDERID", length = 50)
	private String tenderId; //项目ID
	
	@Column(name = "FILEID", length = 50)
	private String fileId; //招标文件ID
	
	@Column(name = "OPINION", length = 4000)
	private String opinion; //论证意见
	
	@Column(name = "USE_STATUS")
	private String useStatus;//使用状态

	@Column(name="ATTACH_REAL_ID",length=36)
	private String attachRelaId; //关联附件ID
	
	@Transient
	private List<Attachment> attachmentlist;//关联附件对象
	
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
    public List<Attachment> getAttachmentlist() {
		return attachmentlist;
	}

	public void setAttachmentlist(List<Attachment> attachmentlist) {
		this.attachmentlist = attachmentlist;
	}
    
    public String getAttachRelaId() {
		return attachRelaId;
	}

	public void setAttachRelaId(String attachRelaId) {
		this.attachRelaId = attachRelaId;
	}
    
    public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	public String getWorkgMId() {
		return workgMId;
	}

	public void setWorkgMId(String workgMId) {
		this.workgMId = workgMId;
	}
	/**
	 * @gpcsoft.property title="姓名"
	 * @gpcsoft.validate class="required"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @gpcsoft.property title="电话"
	 * @gpcsoft.validate class="required"
	 */
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    
	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}
	/**
	 * @gpcsoft.property title="论证意见"
	 * @gpcsoft.validate class="required"
	 */
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	public String getObjId() {
		// TODO Auto-generated method stub
		return this.objId;
	}
	
	public void setObjId(String objId) {
		this.objId = objId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}