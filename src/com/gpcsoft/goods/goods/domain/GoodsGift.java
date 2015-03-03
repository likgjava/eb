package com.gpcsoft.goods.goods.domain;

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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>商品优惠礼包</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 小额交易平台
  *  <br/>Create Date：2011-1-7 上午08:57:51 by likg    					                            
  *  <br/>Modified Date:  2011-1-7 上午08:57:51 by likg                                   
  *  <p>@since 0.5
  *   @version: 0.5 
  *   
  * @gpcsoft.package packageDir="com.gpcsoft.goods"
  * @gpcsoft.page domain="goods"
  * @hibernate.class table="GOODS_GIFT"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "GOODS_GIFT")
public class GoodsGift implements GpcBaseObject,IPropertyCUserTime {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 主键 */
    @Id
    @Column(name = "GOODS_GIFT_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /** 商品 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODS_ID")
	@BatchSize(size = 15)
	private Goods goods;
	
	/** 供应商 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="SUPPLIER_ID")
    @BatchSize(size = 15)
	private OrgInfo supplier;
    
    /** 优惠品名称 */
	@Column(name = "GIFT_NAME", length = 100)
	private String giftName;
	
	/** 优惠品描述 */
	@Column(name = "GIFT_DESC", length = 500)
	private String giftDesc;
	
	/** 优惠品图片 */
	@Column(name = "GIFT_PICTURE", length = 50)
	private String giftPicture;
    
    /** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="CREATOR_ID") 
    @BatchSize(size = 15)
    private User createUser;
    
    /** 创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    /** 修改人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="MODIFIER_ID")
    @BatchSize(size = 15)
    private User updateUser;
    
    /** 修改时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_TIME")
    private Date updateTime;

	public Date getCreateTime() {
		return createTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public String getGiftDesc() {
		return giftDesc;
	}

	public String getGiftName() {
		return giftName;
	}

	public String getGiftPicture() {
		return giftPicture;
	}

	public Goods getGoods() {
		return goods;
	}

	public String getObjId() {
		return objId;
	}

	public OrgInfo getSupplier() {
		return supplier;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public void setGiftDesc(String giftDesc) {
		this.giftDesc = giftDesc;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public void setGiftPicture(String giftPicture) {
		this.giftPicture = giftPicture;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setSupplier(OrgInfo supplier) {
		this.supplier = supplier;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}
    
    
}
