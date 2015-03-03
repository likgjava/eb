package com.gpcsoft.serve.hotel.domain;



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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.serve.hotel.enumeration.HotelEnum;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>MeetingRoom</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   serve                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-12-6 下午04:44:05 by guoyr    					                            
  *  <br/>Modified Date:  2010-12-6 下午04:44:05 by guoyr   
  *  @gpcsoft.package packageDir="com.gpcsoft.serve.hotel"
  *  @gpcsoft.page domain="hotel" project="serve"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="会议室"                              
  *  @since 0.5
  *  @version: 0.5 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "SERVE_MEETING_ROOM")
@OrderProperty(property="createTime", flag="true")
public class MeetingRoom  implements GpcBaseObject, IPropertyCUserTime, IPropertyUUserTime {

	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MEETING_ROOM_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; 				//主键ID
	
	/**编号*/
	@Column(name = "MEETING_ROOM_CODE", length = 100)
	private String meetingRoomCode;
	
	/**酒店*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name = "HOTEL_ID") 
	@BatchSize(size = 15)
	private Hotel hotel;	
	
	/**会议室类型*/
	@Column(name = "MEETING_ROOM_TYPE", length = 2)
	private String meetingRoomType;
	
	@Transient
	private String meetingRoomTypeCN;
	
	/**坐席人数*/
	@Column(name = "CONTAIN_NUM")
	private Long containNum;
	
	/**市场价*/
	@Column(name = "MARKET_PRICE")
	private BigDecimal marketPrice;
	
	/**价格单位*/
	@Column(name = "UNITVALUE", length = 5)
	private String unit;
	
	@Transient
	private String unitCN;

	/**会议室描述*/
	@Column(name = "MEETING_ROOM_DESC", length = 500)
	private String meetingRoomFesc;
	
	/**图片*/
	@Column(name = "PICTURE_ID", length = 50)
	private String picture;
	
	/**会议室设施*/
	@Column(name = "MEETING_ROOM_FACILITIES", length = 2)
	private String meetingRoomFacilities;
	
	@Transient
	private String meetingRoomFacilitiesName;
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATOR_ID")
	@BatchSize(size = 15)
	private User createUser;

	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 7)
	private Date createTime;

	/** 修改人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="MODIFIER_ID")
	@BatchSize(size = 15)
	private User updateUser;

	/** 修改时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_TIME", length = 7)
	private Date updateTime;
	
	/**会议室人数范围*/
	@Column(name = "MEETING_NUM_RANG", length = 2)
	private String meetingNumRang;
	
	@Transient
	private String meetingNumRangCN;
	
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getMeetingRoomCode() {
		return meetingRoomCode;
	}
	public void setMeetingRoomCode(String meetingRoomCode) {
		this.meetingRoomCode = meetingRoomCode;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public String getMeetingRoomType() {
		return meetingRoomType;
	}
	public void setMeetingRoomType(String meetingRoomType) {
		this.meetingRoomType = meetingRoomType;
	}
	public Long getContainNum() {
		return containNum;
	}
	public void setContainNum(Long containNum) {
		this.containNum = containNum;
	}
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getMeetingRoomFesc() {
		return meetingRoomFesc;
	}
	public void setMeetingRoomFesc(String meetingRoomFesc) {
		this.meetingRoomFesc = meetingRoomFesc;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getMeetingRoomFacilities() {
		return meetingRoomFacilities;
	}
	public void setMeetingRoomFacilities(String meetingRoomFacilities) {
		this.meetingRoomFacilities = meetingRoomFacilities;
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
	public String getMeetingRoomTypeCN() {
		this.meetingRoomTypeCN = HotelEnum.getMeetingRoomTypeCN(this.meetingRoomType);
		return meetingRoomTypeCN;
	}
	public void setMeetingRoomTypeCN(String meetingRoomTypeCN) {
		this.meetingRoomTypeCN = meetingRoomTypeCN;
	}
	public String getMeetingRoomFacilitiesName() {
		if(this.meetingRoomFacilities != null){
			String temp = this.meetingRoomFacilities.replace("##||##", "@@@@");
			String args[] = temp.split("@@@@");
			if(args != null && args.length > 1){
				this.meetingRoomFacilitiesName = args[1]==null?"":args[1];
			}else{
				this.meetingRoomFacilitiesName = "";
			}
		}
		return meetingRoomFacilitiesName;
	}
	public void setMeetingRoomFacilitiesName(String meetingRoomFacilitiesName) {
		this.meetingRoomFacilitiesName = meetingRoomFacilitiesName;
	}
	public String getUnitCN() {
		this.unitCN = HotelEnum.getUnitCN(this.unit);
		return unitCN;
	}
	public void setUnitCN(String unitCN) {
		this.unitCN = unitCN;
	}
	public String getMeetingNumRang() {
		return meetingNumRang;
	}
	public void setMeetingNumRang(String meetingNumRang) {
		this.meetingNumRang = meetingNumRang;
	}
	public String getMeetingNumRangCN() {
		return meetingNumRangCN;
	}
	public void setMeetingNumRangCN(String meetingNumRangCN) {
		this.meetingNumRangCN = HotelEnum.getMeetingNumRangCN(this.meetingNumRang);
		this.meetingNumRangCN = meetingNumRangCN;
	}
}
