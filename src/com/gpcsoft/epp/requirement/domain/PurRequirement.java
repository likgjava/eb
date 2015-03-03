package com.gpcsoft.epp.requirement.domain;

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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.epp.consign.domain.Consign;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @author liuke
 * @since 0.5
 * @version: 0.6
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.requirement"
 * @gpcsoft.page domain="planform/requirement" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="采购需求"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PUR_REQUIREMENT")
public class PurRequirement implements GpcBaseObject, IPropertyCUserTime,
		IPropertyUUserTime {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PREQ_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键

	@Column(name = "NAME", length = 50)
	private String name; // 需求名称

	/** 委托书 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "CONS_ID")// 关联的外键
	@BatchSize(size = 15)// 批量抓取
	private Consign consign;

	@Column(name = "SUBMIT_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date submitTime;// 提出时间

	@Column(name = "PREQ_ABOUT", length = 500)
	private String preqAbout; // 需求主要内容描述

	@Column(name = "USE_STATUS", length = 2)
	private String useStatus; // 使用状态[00:临时;01:正式;02:作废]
	
	@Column(name = "ATTACH_REAL_ID")
	private String requireAtt;  //需求附件

	/** 创建人 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "CREATE_USER", updatable = false)// 关联的外键
	@BatchSize(size = 15)// 批量抓取
	private User createUser;

	// 创建时间
	@Column(name = "CREATE_TIME", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	// 修改人
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "MODIFY_USER")// 关联的外键
	@BatchSize(size = 15)// 批量抓取
	private User updateUser;

	// 修改时间
	@Column(name = "MODIFY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	/** 需求明细 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "PREQ_ID") 
	private Set<PreqEntry> preqEntrys = new HashSet<PreqEntry>(0);

	/******************************** GET和SET方法 **********************************************/

	/**
	 * @gpcsoft.query op="like"
	 * @gpcsoft.property title="需求名称"
	 * @gpcsoft.validate class="required"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @gpcsoft.property title="提交时间"
	 * 
	 */
	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	/**
	 * @gpcsoft.property title="需求描述"
	 * 
	 */
	public String getPreqAbout() {
		return preqAbout;
	}

	public void setPreqAbout(String preqAbout) {
		this.preqAbout = preqAbout;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
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

	/**
	 * @gpcsoft.property title="委托书"
	 * 
	 */
	public Consign getConsign() {
		return consign;
	}

	public void setConsign(Consign consign) {
		this.consign = consign;
	}

	/**
	 * @gpcsoft.property title="需求文件"
	 * 
	 */
	public String getRequireAtt() {
		return requireAtt;
	}

	public void setRequireAtt(String requireAtt) {
		this.requireAtt = requireAtt;
	}

	public Set<PreqEntry> getPreqEntrys() {
		return preqEntrys;
	}

	public void setPreqEntrys(Set<PreqEntry> preqEntrys) {
		this.preqEntrys = preqEntrys;
	}

	
}
