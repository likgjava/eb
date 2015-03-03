package com.gpcsoft.epp.requirement.domain;

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
import com.gpcsoft.srplatform.auth.domain.User;


/**
 * @author liuke
 * @since 0.5
 * @version: 0.6
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.requirement"
 * @gpcsoft.page domain="planform/requirement" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="采购需求附件"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PUR_REQ_ATTACHMENT")
public class PurReqAttachment implements GpcBaseObject{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PUR_REQ_ATTACHMENT_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键

	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
    @JoinColumn(name = "PREQ_ENTRY_ID")
	private PreqEntry preEntry;     //需求条目
	
	@Column(name = "ATTACH_ID", length = 50)
	private String attachId; //附件ID

	@Column(name = "ATTACH_TYPE", length = 50)
	private String attachType; //需求附件类型
	
	@Column(name = "REMARK", length = 500)
	private String remark; //备注
	
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
	/**
	 * @gpcsoft.property title="需求条目ID"
	 */
	public PreqEntry getPreEntry() {
		return preEntry;
	}

	public void setPreEntry(PreqEntry preEntry) {
		this.preEntry = preEntry;
	}
	/**
	 * @gpcsoft.property title="附件ID"
	 */
	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}
	/**
	 * @gpcsoft.property title="附件类型"
	 */
	public String getAttachType() {
		return attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}
	/**
	 * @gpcsoft.property title="备注"
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	public String getObjId() {
		// TODO Auto-generated method stub
		return this.objId;
	}
	
		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
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
