package com.gpcsoft.serve.hotel.domain;



import java.math.BigDecimal;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.serve.hotel.enumeration.HotelEnum;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.baseData.domain.District;

/** 
  *  Comments: <strong>Hotel</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   serve                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-12-6 下午04:44:05 by guoyr    					                            
  *  <br/>Modified Date:  2010-12-6 下午04:44:05 by guoyr   
  *  @gpcsoft.package packageDir="com.gpcsoft.serve.hotel"
  *  @gpcsoft.page domain="hotel" project="serve"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="酒店"                              
  *  @since 0.5
  *  @version: 0.5 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "SERVE_HOTEL")
@OrderProperty(property="createTime", flag="true")
public class Hotel  implements GpcBaseObject, IPropertyCUserTime, IPropertyUUserTime{

	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "HOTEL_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; 				//主键ID
	
	/**编号*/
	@Column(name = "HOTEL_CODE", length = 100)
	private String hotelCode;
	
	/**所属机构*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name = "ORG_INFO_ID") 
	@BatchSize(size = 15)
	private OrgInfo orgInfo;	
	
	/**名称*/
	@Column(name = "HOTEL_NAME", length = 50)
	private String hotelName;
	
	/**地址*/
	@Column(name = "HOTEL_ADDRESS", length = 100)
	private String hotelAddress;
	
	/** 开业时间  */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_TIME")
	private Date startTime;
	
	/** 区域 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="DISTRICT_ID")
	@BatchSize(size = 15)
	private District district;
	
	/**星级*/
	@Column(name = "STAR", length = 2)
	private String star;
	
	@Transient
	private String starCN;
	
	/**周围环境*/
	@Column(name = "SURROUNDINGS", length = 250)
	private String surroundings;
	
	/**酒店概况*/
	@Column(name = "HOTEL_DESC", length = 500)
	private String hotelDesc;
	
	/**详细介绍*/
	@Column(name = "HOTEL_DETAIL", length = 2000)
	private String hotelDetail;
	
	/**主图*/
	@Column(name = "PICTURE_ID", length = 50)
	private String picture;
	
	/**附图*/
	@Column(name = "ADDITION_PICTURE", length = 50)
	private String additionPicture;
	
	/**酒店服务项目*/
	@Column(name = "SERVICE_ITEMS", length = 250)
	private String serviceItems;
	
	@Transient
	private String serviceItemsName;
	
	/**餐饮设施*/
	@Column(name = "FOOD_FACILITIES", length = 250)
	private String foodFacilities;
	
	@Transient
	private String foodFacilitiesName;
	
	/**娱乐设施*/
	@Column(name = "FUN_FACILITIES", length = 250)
	private String funFacilities;
	
	@Transient
	private String funFacilitiesName;
	
	/**客房设施和服务*/
	@Column(name = "GUESTROOM_FACILITIES", length = 250)
	private String guestRoomFacilities;
	
	@Transient
	private String guestRoomFacilitiesName;
	
	/**可接受信用卡类型*/
	@Column(name = "CREDIT_CARD_TYPE", length = 250)
	private String creditCardType;
	
	@Transient
	private String creditCardTypeName;
	
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
	
	/**审核状态*/
	@Column(name = "AUDIT_STATUS", length = 2)
	private String auditStatus;
	
	@Transient
	private String auditStatusCN;
	
	/**使用状态*/
	@Column(name = "USE_STATUS", length = 2)
	private String useStatus;
	
	@Transient
	private String useStatusCN;
	
	/**是否启用*/
	@Column(name = "IS_OFF", length = 2)
	private Boolean isOff;
	
	@Transient
	private String isOffCN;
	
	/**联系电话*/
	@Column(name = "CONTACT", length = 12)
	private String contact;
	
	/**传真*/
	@Column(name = "FAX", length = 12)
	private String fax;
	
	
	/**客房*/
	@LazyCollection(value=LazyCollectionOption.EXTRA)
    @OneToMany
    @Cascade({CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "HOTEL_ID")
	@OrderBy("retailPrice asc")
    private Set<GuestRoom> guestRooms = new HashSet<GuestRoom>();
	
	/**客房冗余字段，以逗号分割*/
	@Column(name = "GUEST_ROOM_TYPE", length = 250)
	private String guestRoomType;
	
	/**客房数*/
	@Formula("(select nvl(sum(e.GUESTROOM_NUM),0) from SERVE_GUESTROOM e where e.HOTEL_ID = HOTEL_ID)")
	private Integer guestRoomCount;
	
	/** 会议室 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "HOTEL_ID") 
	@OrderBy("marketPrice asc")
	private Set<MeetingRoom> meetingRooms = new HashSet<MeetingRoom>();
	
	/**客房冗余字段，以逗号分割，包含会议室类型和人数*/
	@Column(name = "MEETING_ROOM_TYPE", length = 2)
	private String meetingRoomType;
	
	/**
	 * 评价总分
	 */
    @Formula("(select nvl(avg(e.HOTEL_EVALUATE_LEVEL),0) from SERVE_HOTEL_EVLAUATE e where e.HOTEL_ID = HOTEL_ID)")
    private BigDecimal evalSum;
	
	 
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public OrgInfo getOrgInfo() {
		return orgInfo;
	}
	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelAddress() {
		return hotelAddress;
	}
	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getSurroundings() {
		return surroundings;
	}
	public void setSurroundings(String surroundings) {
		this.surroundings = surroundings;
	}
	public String getHotelDesc() {
		return hotelDesc;
	}
	public void setHotelDesc(String hotelDesc) {
		this.hotelDesc = hotelDesc;
	}
	public String getHotelDetail() {
		return hotelDetail;
	}
	public void setHotelDetail(String hotelDetail) {
		this.hotelDetail = hotelDetail;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getAdditionPicture() {
		return additionPicture;
	}
	public void setAdditionPicture(String additionPicture) {
		this.additionPicture = additionPicture;
	}
	public String getServiceItems() {
		return serviceItems;
	}
	public void setServiceItems(String serviceItems) {
		this.serviceItems = serviceItems;
	}
	public String getFoodFacilities() {
		return foodFacilities;
	}
	public void setFoodFacilities(String foodFacilities) {
		this.foodFacilities = foodFacilities;
	}
	public String getFunFacilities() {
		return funFacilities;
	}
	public void setFunFacilities(String funFacilities) {
		this.funFacilities = funFacilities;
	}
	public String getGuestRoomFacilities() {
		return guestRoomFacilities;
	}
	public void setGuestRoomFacilities(String guestRoomFacilities) {
		this.guestRoomFacilities = guestRoomFacilities;
	}
	public String getCreditCardType() {
		return creditCardType;
	}
	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
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
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	public Boolean getIsOff() {
		return isOff;
	}
	public void setIsOff(Boolean isOff) {
		this.isOff = isOff;
	}
	
	public String getAuditStatusCN() {
		this.auditStatusCN = HotelEnum.getAuditStatusCN(this.auditStatus);
		return this.auditStatusCN;
	}

	public void setAuditStatusCN(String auditStatusCN) {
		this.auditStatusCN = auditStatusCN;
	}
	

	public String getUseStatusCN() {
		this.useStatusCN = HotelEnum.getUseStatusCN(this.useStatus);
		return useStatusCN;
	}

	public void setUseStatusCN(String useStatusCN) {
		this.useStatusCN = useStatusCN;
	}
	
	public String getStarCN() {
		this.starCN = HotelEnum.getStarCN(this.star);
		return starCN;
	}
	public void setStarCN(String starCN) {
		this.starCN = starCN;
	}
	public String getServiceItemsName() {
		if(this.serviceItems != null){
			String temp = this.serviceItems.replace("##||##", "@@@@");
			String args[] = temp.split("@@@@");
			if(args != null && args.length > 1){
				this.serviceItemsName = args[1]==null?"":args[1];
			}else{
				this.serviceItemsName = "";
			}
		}
		return serviceItemsName;
	}
	public void setServiceItemsName(String serviceItemsName) {
		this.serviceItemsName = serviceItemsName;
	}
	public String getFoodFacilitiesName() {
		if(this.foodFacilities != null){
			String temp = this.foodFacilities.replace("##||##", "@@@@");
			String args[] = temp.split("@@@@");
			if(args != null && args.length > 1){
				this.foodFacilitiesName = args[1]==null?"":args[1];
			}else{
				this.foodFacilitiesName = "";
			}
		}
		return foodFacilitiesName;
	}
	public void setFoodFacilitiesName(String foodFacilitiesName) {
		this.foodFacilitiesName = foodFacilitiesName;
	}
	public String getFunFacilitiesName() {
		if(this.funFacilities != null){
			String temp = this.funFacilities.replace("##||##", "@@@@");
			String args[] = temp.split("@@@@");
			if(args != null && args.length > 1){
				this.funFacilitiesName = args[1]==null?"":args[1];
			}else{
				this.funFacilitiesName = "";
			}
		}
		return funFacilitiesName;
	}
	public void setFunFacilitiesName(String funFacilitiesName) {
		this.funFacilitiesName = funFacilitiesName;
	}
	public String getGuestRoomFacilitiesName() {
		if(this.guestRoomFacilities != null){
			String temp = this.guestRoomFacilities.replace("##||##", "@@@@");
			String args[] = temp.split("@@@@");
			if(args != null && args.length > 1){
				this.guestRoomFacilitiesName = args[1]==null?"":args[1];
			}else{
				this.guestRoomFacilitiesName = "";
			}
		}
		return guestRoomFacilitiesName;
	}
	public void setGuestRoomFacilitiesName(String guestRoomFacilitiesName) {
		this.guestRoomFacilitiesName = guestRoomFacilitiesName;
	}
	public String getCreditCardTypeName() {
		if(this.creditCardType != null){
			String temp = this.creditCardType.replace("##||##", "@@@@");
			String args[] = temp.split("@@@@");
			if(args != null && args.length > 1){
				this.creditCardTypeName = args[1]==null?"":args[1];
			}else{
				this.creditCardTypeName = "";
			}
		}
		return creditCardTypeName;
	}
	public void setCreditCardTypeName(String creditCardTypeName) {
		this.creditCardTypeName = creditCardTypeName;
	}
	public Set<GuestRoom> getGuestRooms() {
		return guestRooms;
	}
	public void setGuestRooms(Set<GuestRoom> guestRooms) {
		this.guestRooms = guestRooms;
	}
	public Set<MeetingRoom> getMeetingRooms() {
		return meetingRooms;
	}
	public void setMeetingRooms(Set<MeetingRoom> meetingRooms) {
		this.meetingRooms = meetingRooms;
	}
	public String getGuestRoomType() {
		return guestRoomType;
	}
	public void setGuestRoomType(String guestRoomType) {
		this.guestRoomType = guestRoomType;
	}
	public String getMeetingRoomType() {
		return meetingRoomType;
	}
	public void setMeetingRoomType(String meetingRoomType) {
		this.meetingRoomType = meetingRoomType;
	}
	public Integer getGuestRoomCount() {
		return guestRoomCount;
	}
	public void setGuestRoomCount(Integer guestRoomCount) {
		this.guestRoomCount = guestRoomCount;
	}
	
	public String getIsOffCN() {
		if(null != isOff && isOff){
			isOffCN = "已启用";
		}else {
			isOffCN = "已禁用";
		}
		return isOffCN;
	}
	public void setIsOffCN(String isOffCN) {
		this.isOffCN = isOffCN;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public BigDecimal getEvalSum() {
		return evalSum;
	}
	public void setEvalSum(BigDecimal evalSum) {
		this.evalSum = evalSum;
	}
	
	
}
