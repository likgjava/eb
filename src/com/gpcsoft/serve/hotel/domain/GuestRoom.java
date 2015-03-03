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
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.serve.hotel.enumeration.HotelEnum;
import com.gpcsoft.srplatform.auth.domain.User;

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
  *  @gpcsoft.title value="客房"                              
  *  @since 0.5
  *  @version: 0.5 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "SERVE_GUESTROOM")
@OrderProperty(property="createTime", flag="true")
public class GuestRoom  implements GpcBaseObject, IPropertyCUserTime, IPropertyUUserTime {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "GUESTROOM_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/**编号*/
	@Column(name = "GUESTROOM_CODE", length = 100)
	private String guestroomCode;
	
	/**酒店*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name = "HOTEL_ID") 
	@BatchSize(size = 15)
	private Hotel hotel;	
	
	/** 客房类型[01:标准房，02:商务房，03：高级房，04:豪华房] */
	@Column(name = "TYPE", length = 2)
	private String guestRoomType;
	
	@Transient
	private String guestRoomTypeCN;
	
	 /**面积*/
    @Column(name = "AREA")
    private BigDecimal area;
    
	/**门市价*/
    @Column(name = "RETAIL_PRICE")
    private BigDecimal retailPrice;
    
	/**图片*/
	@Column(name = "PICTURE_ID", length = 50)
	private String picture;
    
    /** 床型[01:单人床，02:双人床，03:大床] */
	@Column(name = "BED_TYPE", length = 2)
	private String bedType;
	
	@Transient
	private String bedTypeCN;
	
	/** 早餐类型[00:无早，01:单早，02：双早] */
	@Column(name = "BREAKFAST_TYPE", length = 2)
	private String breakfastType;
	
	@Transient
	private String breakfastTypeCN;
	
	/**楼层*/
	@Column(name = "FLOOR")
	private String floor;
	
	/**宽带*/
	@Column(name = "BROADBAND", length = 100)
	private String broadband;
	
	/**房间描述*/
	@Column(name = "GUESTROOM_DESC", length = 500)
	private String guestroomDesc;
	
	/**房间数量*/
	@Column(name = "GUESTROOM_NUM")
	private Integer guestroomNum ;
	
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
	
	/** 客房价格 */
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany
	@Cascade({CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "GUESTROOM_ID")
	private Set<GuestRoomPrice> guestRoomPrices = new HashSet<GuestRoomPrice>();

	public BigDecimal getArea() {
		return area;
	}

	public String getBedType() {
		return bedType;
	}

	public String getBedTypeCN() {
		this.bedTypeCN = HotelEnum.getBedTypeCN(this.bedType);
		return bedTypeCN;
	}

	public String getBreakfastType() {
		return breakfastType;
	}

	public String getBreakfastTypeCN() {
		this.breakfastTypeCN = HotelEnum.getBreakfastTypeCN(this.breakfastType);
		return breakfastTypeCN;
	}

	public String getBroadband() {
		return broadband;
	}

	public Date getCreateTime() {
		return createTime;
	}
	
	public User getCreateUser() {
		return createUser;
	}

	public String getFloor() {
		return floor;
	}

	public String getGuestroomCode() {
		return guestroomCode;
	}

	public String getGuestroomDesc() {
		return guestroomDesc;
	}

	public Integer getGuestroomNum() {
		return guestroomNum;
	}
	public Set<GuestRoomPrice> getGuestRoomPrices() {
		return guestRoomPrices;
	}
	public String getGuestRoomType() {
		return guestRoomType;
	}
	public String getGuestRoomTypeCN() {
		this.guestRoomTypeCN = HotelEnum.getGuestRoomTypeCN(this.guestRoomType);
		return guestRoomTypeCN;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public String getObjId() {
		return objId;
	}
	public String getPicture() {
		return picture;
	}
	public BigDecimal getRetailPrice() {
		return retailPrice;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public User getUpdateUser() {
		return updateUser;
	}
	public void setArea(BigDecimal area) {
		this.area = area;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	public void setBedTypeCN(String bedTypeCN) {
		this.bedTypeCN = bedTypeCN;
	}
	public void setBreakfastType(String breakfastType) {
		this.breakfastType = breakfastType;
	}
	public void setBreakfastTypeCN(String breakfastTypeCN) {
		this.breakfastTypeCN = breakfastTypeCN;
	}
	public void setBroadband(String broadband) {
		this.broadband = broadband;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public void setGuestroomCode(String guestroomCode) {
		this.guestroomCode = guestroomCode;
	}
	public void setGuestroomDesc(String guestroomDesc) {
		this.guestroomDesc = guestroomDesc;
	}
	public void setGuestroomNum(Integer guestroomNum) {
		this.guestroomNum = guestroomNum;
	}
	public void setGuestRoomPrices(Set<GuestRoomPrice> guestRoomPrices) {
		this.guestRoomPrices = guestRoomPrices;
	}
	public void setGuestRoomType(String guestRoomType) {
		this.guestRoomType = guestRoomType;
	}
	public void setGuestRoomTypeCN(String guestRoomTypeCN) {
		this.guestRoomTypeCN = guestRoomTypeCN;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}
	
}
