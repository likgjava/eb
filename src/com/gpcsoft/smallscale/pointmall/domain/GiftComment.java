package com.gpcsoft.smallscale.pointmall.domain;

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
 *  Comments: <strong>礼品点评</strong>            		
 *  
 *  @gpcsoft.package packageDir="com.gpcsoft.smallscale.pointmall"
 *  @gpcsoft.page domain="pointmall" project="smallscale"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="礼品点评"
 */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "SM_GIFT_COMMENT")
public class GiftComment implements GpcBaseObject, IPropertyCUserTime {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**主键ID */
	@Id
	@Column(name="COMMENT_ID", length=50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name="paymentableGenerator",strategy="uuid.hex")
	private String objId;
	
	/**礼品id  */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="GIFT_ID")  
    @BatchSize(size = 15)
	private Gift gift;
	
    /**评价标题*/
    @Column(name = "GIFT_COMMENT_TITEL")
    private String title;
    
    /**评价级别*/
    @Column(name = "GIFT_COMMENT_LEVEL")
    private Integer level;
	
	/**评价描述  */
	@Column(name="GIFT_COMMENT", length=1000)
	private String comment;
	
    /**评价人中文名*/
    @Column(name = "CREATOR_NAME")
    private String rateName;
	
	 /**创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR_ID")  
    @BatchSize(size = 15)
    private User createUser;
	
	/**创建时间	 */
	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_TIME")
	private Date createTime;

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
	 * @gpcsoft.property title="gift"
	 */
	public Gift getGift() {
		return gift;
	}

	/** gift */
	public void setGift(Gift gift) {
		this.gift = gift;
	}

	/**
	 * @gpcsoft.property title="comment"
	 */
	public String getComment() {
		return comment;
	}

	/** comment */
	public void setComment(String comment) {
		this.comment = comment;
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
	 * @gpcsoft.property title="title"
	 */
	public String getTitle() {
		return title;
	}

	/** title */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @gpcsoft.property title="level"
	 */
	public Integer getLevel() {
		return level;
	}

	/** level */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @gpcsoft.property title="rateName"
	 */
	public String getRateName() {
		return rateName;
	}

	/** rateName */
	public void setRateName(String rateName) {
		this.rateName = rateName;
	}


}
