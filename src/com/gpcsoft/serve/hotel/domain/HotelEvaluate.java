package com.gpcsoft.serve.hotel.domain;
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
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>酒店评价</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-27 下午12:45:45 by yucy    					                            
  *  <br/>Modified Date:  2010-7-27 下午12:45:45 by yucy                                   
  *   @gpcsoft.package packageDir="com.gpcsoft.serve.hotel"
  *   @gpcsoft.page domain="serve" 
  *   @gpcsoft.domain
  *   @gpcsoft.title value="酒店评价"
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "SERVE_HOTEL_EVLAUATE")
public class HotelEvaluate implements GpcBaseObject ,IPropertyCUserTime{
    
	/** serialVersionUID */
	private static final long serialVersionUID = -3753658062134762489L;

	/**记录号*/
    @Id
    @Column(name = "HOTEL_EVALUATE_ID")
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /** 酒店Id */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="HOTEL_ID") 
    @BatchSize(size = 15)
    private Hotel hotel;
    
    /**评价标题*/
    @Column(name = "HOTEL_EVALUATE_TITEL")
    private String title;
    
    /**评价级别*/
    @Column(name = "HOTEL_EVALUATE_LEVEL")
    private Integer level;
    
    /**评价描述*/
    @Column(name = "HOTEL_EVALUATE_REMARK")
    private String remark;
    
    /**评价人中文名*/
    @Column(name = "CREATOR_NAME")
    private String rateName;

    /** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR_ID") 
    @BatchSize(size = 15)
    private User createUser;
    
    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public String getRateName() {
		return rateName;
	}

	public void setRateName(String rateName) {
		this.rateName = rateName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}