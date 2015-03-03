package com.gpcsoft.smallscale.point.domain;

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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.srplatform.auth.domain.User;


/** 
 *  Comments: <strong>推广人信息</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2010-xxxx   		
 *  <br/>Project:                       					          
 *  <br/>Module ID:    		
 *  <br/>Create Date：2010-9-26 			                            
 *  <br/>Modified Date:  2010-9-26                          
 *   
 *  @gpcsoft.package packageDir="com.gpcsoft.smallscale.point"
 *  @gpcsoft.page domain="point" 
 *  @gpcsoft.domain
 *  @gpcsoft.title value="推广人信息" 
 */ 
@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_PROMOTER_INFO")
public class PromoterInfo implements GpcBaseObject {
	

	/** 主键ID */
	@Id
    @Column(name = "PROMOTER_INFO_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	
	
	/** 类型（ 1 友善大使 2诚信大使 3爱心大使） */
	@Column(name = "PROMOTER_TYPE",length=2)
	private String promoterType;
	

	
	/** 关联用户人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="USER_ID")  
	@BatchSize(size = 15)//批量抓取
	private User user;
	
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_Time")
	private Date createTime;  



	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getPromoterType() {
		return promoterType;
	}

	public void setPromoterType(String promoterType) {
		this.promoterType = promoterType;
	}

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
	

}
