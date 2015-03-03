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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
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
 * @gpcsoft.title value="标评室信息"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_MEET_ROOM")
public class MeetRoom implements GpcBaseObject,IPropertyCUserTime, IPropertyUUserTime{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MEET_ROOM_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@Column(name = "MEET_ROOM_NAME", length = 100)
	private String meetRoomName; // 标评室名称
	
	@Column(name = "MEET_ROOM_ADDRESS", length = 500)
	private String meetRoomAddress; // 标评室地址
	
	@Column(name = "IS_TEMP", length = 1)
	private Character isTemp; // 是否为临时标评室  0为否  1 为是
	
	@Column(name = "USE_STATUS")
	private Character useStatus;//使用状态
	
	@Column(name = "ROOM_TYPE")
	private String roomType;//评标室类型 ：  00  开标室   01 评标室 
	
	@Transient
	private String roomTypeCN;  
	
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
	public String getRoomTypeCN() {
		this.roomTypeCN = RoomTypeEnum.getRoomTypeCN(this.getRoomType());
		return roomTypeCN;
	}

	public void setRoomTypeCN(String roomTypeCN) {
		this.roomTypeCN = roomTypeCN;
	}
	
	/**
	 * @gpcsoft.property title="标评室名称"
	 */
	public String getMeetRoomName() {
		return meetRoomName;
	}
    
	public void setMeetRoomName(String meetRoomName) {
		this.meetRoomName = meetRoomName;
	}
	/**
	 * @gpcsoft.property title="标评室地址"
	 */
	public String getMeetRoomAddress() {
		return meetRoomAddress;
	}

	public void setMeetRoomAddress(String meetRoomAddress) {
		this.meetRoomAddress = meetRoomAddress;
	}
	/**
	 * @gpcsoft.property title="是否为临时标评室"
	 */
	public Character getIsTemp() {
		return isTemp;
	}

	public void setIsTemp(Character isTemp) {
		this.isTemp = isTemp;
	}
	/**
	 * @gpcsoft.property title="标评室类型"
	 */
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
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
     
	

}
