package com.gpcsoft.epp.projrule.domain;
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
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;
/**
 * Comments: <strong>Role</strong>角色信息 <br/>
 * 按<code>type</code>可以分为两种：1：机构角色 2：用户角色<br/>
 * <code>isDefault</code>为默认角色，应当有6个， 分别为"supplier"供应商，"buyer"采购人，"agent"代理机构，
 * 机构角色和用户角色各三个 <br/>
 * 继承<code>BaseDHtmlTree</code><br/>
 * <br/>
 * Project: srplatform <br/>
 * Module ID: <br/>
 * 
 * Create Date：2010-4-14 <br/>
 * Modified By：liuke <br/>
 * CopyRright (c)2008-xxxx: 珠海政采软件技术有限公司 <br/>
 * Modified Date: 2010-4-14 修改为注解的方式
 * 
 * @author liuke
 * @since 0.5
 * @version: 0.6
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.projrule"
 * @gpcsoft.page domain="planform/projrule" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="标评室预定信息"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_BID_ROOM")
public class BidRoom extends WorkFlowModel implements GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime{
	@Id
	@Column(name = "BID_ROOM_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@ManyToOne(fetch=FetchType.EAGER)
	@BatchSize(size = 20)
	@JoinColumn(name="MEETING_ROOM_ID") 		
	private MeetRoom meetRoom; //标评室
	
	@Column(name = "BID_ROOM_NAME", length = 100)
	private String bidRoomName; // 标评室名称
	
	@Column(name = "BID_ROOM_ADDRESS", length = 500)
	private String bidRoomAddress; // 标评室地址
	
	@Column(name = "START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;//开始时间
	
	@Column(name = "END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;//结束时间
	
	@Column(name = "REPLAY_REQUIREMENT", length = 500)
	private String replayRequirement; //会议室申请要求
		
	@ManyToOne(fetch=FetchType.EAGER)
	@BatchSize(size = 20)
	@JoinColumn(name="PROJ_PACK_ID") 		
	private Project project; //项目信息
	
	@Column(name = "REMARK", length = 500)
	private String remark; //会议室申请要求
	
	@Column(name = "TIME_BUCKET", length = 50)
	private String timeBucket; //会议室申请要求
	
	@Column(name = "USE_STATUS")
	private String useStatus;//使用状态
	
	
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
	
	public String getTimeBucket() {
		return timeBucket;
	}

	public void setTimeBucket(String timeBucket) {
		this.timeBucket = timeBucket;
	}
	public MeetRoom getMeetRoom() {
		return meetRoom;
	}

	public void setMeetRoom(MeetRoom meetRoom) {
		this.meetRoom = meetRoom;
	}

	public String getBidRoomName() {
		return bidRoomName;
	}

	public void setBidRoomName(String bidRoomName) {
		this.bidRoomName = bidRoomName;
	}

	public String getBidRoomAddress() {
		return bidRoomAddress;
	}

	public void setBidRoomAddress(String bidRoomAddress) {
		this.bidRoomAddress = bidRoomAddress;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getReplayRequirement() {
		return replayRequirement;
	}

	public void setReplayRequirement(String replayRequirement) {
		this.replayRequirement = replayRequirement;
	}

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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
