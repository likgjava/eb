package com.gpcsoft.pubservice.application.advertisement.domain;

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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.pubservice.application.advertisement.enumeration.AdvertisingMessage;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.baseData.domain.Dictionary;

/**
 * 
  *  Comments: <strong>广告位</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2011-3-21 下午01:23:45 by zhaojf    					                            
  *  <br/>Modified Date:  2011-3-21 下午01:23:45 by zhaojf                                   
  *<p>@since 0.5
  *   @version: 0.5
  *   
  *   @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.advertisement"
  *   @gpcsoft.page domain="advertisement" project="pubservice/application"
  *   @gpcsoft.domain
  *   @gpcsoft.title value="广告位" 
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_PUB_ADVERTISING_POSITION")
@OrderProperty(property="positionName",flag="true")
public class AdvertisingPosition implements GpcBaseObject,IPropertyCUserTime,IPropertyUUserTime{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**主键ID */
	@Id
	@Column(name="ADVER_POSITION_ID", length=50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name="paymentableGenerator",strategy="uuid.hex")
	private String objId;
	
    /**位置字典项*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="POSITION_DICTIONARY_ID") 
    @BatchSize(size = 15)
    private Dictionary positionDictionary;
	
	/**文件名称 */
	@Column(name="POSITION_NAME")
	private String positionName;
	
    /**广告文件类型  00.图片文件,01.flash文件 02.跑马灯*/
    @Column(name = "ADVER_TYPE")
    private String adverType;
	
	/**广告长度 */
	@Column(name="ADVER_LENGTH")
	private Integer adverLength;
	
	/**广告宽度 */
	@Column(name="ADVER_WIDTH")
	private Integer adverWidth;
	
	/**广告文件最大值   容量 */
	@Column(name="ADVER_FILE_MAXVALUE")
	private Integer adverFileMaxValue;
	
	/** 资费 */
	@Column(name = "ADVER_OUTLAY", precision = 16, scale = 4)
	private BigDecimal adverOutlay;
	
	 /**创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="CREATOR_ID")  
    @BatchSize(size = 15)
    private User createUser;
	
	/**创建时间	 */
	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	/**修改人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
	@JoinColumn(name="MODIFIER")//关联的外键  
	@BatchSize(size = 15)//批量抓取
	private User updateUser;
	 
	/**修改时间 */
	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFIER_TIME")
	private Date updateTime;

	/**广告文件类型 */
	@Transient
	private String adverTypeCN;
	
	
	/** adverTypeCN */
	public void setAdverTypeCN(String adverTypeCN) {
		this.adverTypeCN = adverTypeCN;
	}

	/**
	 * @gpcsoft.property title="adverTypeCN"
	 */
	public String getAdverTypeCN() {
		this.adverTypeCN = AdvertisingMessage.getAdverTypeCN(this.adverType);
		return this.adverTypeCN;
	}

	/**
	 * @gpcsoft.property title="objId"
	 */
	public String getObjId() {
		return objId;
	}

	/** objId */
	public void setObjId(String objId) {
		this.objId = objId;
	}

	/**
	 * @gpcsoft.property title="positionDictionary"
	 */
	public Dictionary getPositionDictionary() {
		return positionDictionary;
	}

	/** positionDictionary */
	public void setPositionDictionary(Dictionary positionDictionary) {
		this.positionDictionary = positionDictionary;
	}

	/**
	 * @gpcsoft.property title="positionName"
	 */
	public String getPositionName() {
		return positionName;
	}

	/** positionName */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * @gpcsoft.property title="adverType"
	 */
	public String getAdverType() {
		return adverType;
	}

	/** adverType */
	public void setAdverType(String adverType) {
		this.adverType = adverType;
	}

	/**
	 * @gpcsoft.property title="adverLength"
	 */
	public Integer getAdverLength() {
		return adverLength;
	}

	/** adverLength */
	public void setAdverLength(Integer adverLength) {
		this.adverLength = adverLength;
	}

	/**
	 * @gpcsoft.property title="adverWidth"
	 */
	public Integer getAdverWidth() {
		return adverWidth;
	}

	/** adverWidth */
	public void setAdverWidth(Integer adverWidth) {
		this.adverWidth = adverWidth;
	}

	/**
	 * @gpcsoft.property title="adverFileMaxValue"
	 */
	public Integer getAdverFileMaxValue() {
		return adverFileMaxValue;
	}

	/** adverFileMaxValue */
	public void setAdverFileMaxValue(Integer adverFileMaxValue) {
		this.adverFileMaxValue = adverFileMaxValue;
	}

	/**
	 * @gpcsoft.property title="adverOutlay"
	 */
	public BigDecimal getAdverOutlay() {
		return adverOutlay;
	}

	/** adverOutlay */
	public void setAdverOutlay(BigDecimal adverOutlay) {
		this.adverOutlay = adverOutlay;
	}

	/**
	 * @gpcsoft.property title="createUser"
	 */
	public User getCreateUser() {
		return createUser;
	}

	/** createUser */
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	/**
	 * @gpcsoft.property title="createTime"
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/** createTime */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @gpcsoft.property title="updateUser"
	 */
	public User getUpdateUser() {
		return updateUser;
	}

	/** updateUser */
	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * @gpcsoft.property title="updateTime"
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/** updateTime */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
