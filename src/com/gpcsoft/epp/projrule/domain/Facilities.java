package com.gpcsoft.epp.projrule.domain;
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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_LIST_OF_FACILITIES")
public class Facilities extends WorkFlowModel implements GpcBaseObject {

	private static final long serialVersionUID = -8358187304386709746L;

	@Id
	@Column(name = "FACILITIES_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@ManyToOne(fetch=FetchType.EAGER)
	@BatchSize(size = 20)
	@JoinColumn(name="MEET_ROOM_ID") 		
	private MeetRoom meetRoom; //开标评室Id
	
	@Column(name = "FACILITIES_NAME")
	private String facilitiesName; //设施 名称
	
	@Column(name = "FACILITIES_TYPE")
	private String facilitiesType; //设施类型[00:电脑;01:摄像头]
	
	
	@Column(name = "FACILITIES_METHOD")
	private String facilitiesMethod;//使用方式[00:主控;01辅助等]
	
	
	@Column(name = "FACILITIES_MEMO")
	private String facilitiesMemo;//设施描述
	
	@Column(name = "FACILITIES_ADDR")
	private String facilitiesAddr;//设施访问地址

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public MeetRoom getMeetRoom() {
		return meetRoom;
	}

	public void setMeetRoom(MeetRoom meetRoom) {
		this.meetRoom = meetRoom;
	}

	public String getFacilitiesName() {
		return facilitiesName;
	}

	public void setFacilitiesName(String facilitiesName) {
		this.facilitiesName = facilitiesName;
	}

	public String getFacilitiesType() {
		return facilitiesType;
	}

	public void setFacilitiesType(String facilitiesType) {
		this.facilitiesType = facilitiesType;
	}

	public String getFacilitiesMemo() {
		return facilitiesMemo;
	}

	public void setFacilitiesMemo(String facilitiesMemo) {
		this.facilitiesMemo = facilitiesMemo;
	}

	public String getFacilitiesAddr() {
		return facilitiesAddr;
	}

	public void setFacilitiesAddr(String facilitiesAddr) {
		this.facilitiesAddr = facilitiesAddr;
	}

	public String getFacilitiesMethod() {
		return facilitiesMethod;
	}

	public void setFacilitiesMethod(String facilitiesMethod) {
		this.facilitiesMethod = facilitiesMethod;
	}
	
}
