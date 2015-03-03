package com.gpcsoft.serve.hotel.domain;



import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>GuestRoomPrice</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   serve                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-12-6 下午04:44:05 by guoyr    					                            
  *  <br/>Modified Date:  2010-12-6 下午04:44:05 by guoyr   
  *  @gpcsoft.package packageDir="com.gpcsoft.serve.hotel"
  *  @gpcsoft.page domain="hotel" project="serve"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="客房价格"                              
  *  @since 0.5
  *  @version: 0.5 
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "SERVE_GUESTROOM_PRICE")
@OrderProperty(property="createTime", flag="true")
public class GuestRoomPrice  implements GpcBaseObject, IPropertyCUserTime, IPropertyUUserTime {

	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "GUESTROOM_PRICE_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; 				//主键ID
	
	/** 所属客房 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name = "GUESTROOM_ID") 
	@BatchSize(size = 15)
	private GuestRoom guestRoom;	
	
	/** 开始时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_TIME")
	private Date startTime;
	
	/** 结束时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_TIME")
	private Date endTime;

	/**协议价*/
	@Column(name = "AGREE_PRICE")
	private BigDecimal agreePrice;

	/**是否含早餐*/
	@Column(name = "HAS_BREAKFAST")
	private Boolean hasBreakfast;
	
	/**早餐数量*/
	@Column(name = "BREAKFAST_NUM")
	private Integer breakfastNum;
	
	/**备注*/
	@Column(name = "REMARK", length = 500)
	private String remark;

	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATOR_ID")
	@BatchSize(size = 15)
	private User createUser;

	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;

	/** 修改人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="MODIFIER_ID")
	@BatchSize(size = 15)
	private User updateUser;

	/** 修改时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_TIME")
	private Date updateTime;
	
	
	public BigDecimal getAgreePrice() {
		return agreePrice;
	}
	public Integer getBreakfastNum() {
		return breakfastNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public User getCreateUser() {
		return createUser;
	}
	public Date getEndTime() {
		return endTime;
	}
	public GuestRoom getGuestRoom() {
		return guestRoom;
	}
	public Boolean getHasBreakfast() {
		return hasBreakfast;
	}
	public String getObjId() {
		return objId;
	}
	public String getRemark() {
		return remark;
	}
	public Date getStartTime() {
		return startTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public User getUpdateUser() {
		return updateUser;
	}
	public void setAgreePrice(BigDecimal agreePrice) {
		this.agreePrice = agreePrice;
	}
	public void setBreakfastNum(Integer breakfastNum) {
		this.breakfastNum = breakfastNum;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public void setGuestRoom(GuestRoom guestRoom) {
		this.guestRoom = guestRoom;
	}
	public void setHasBreakfast(Boolean hasBreakfast) {
		this.hasBreakfast = hasBreakfast;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}
	
}
