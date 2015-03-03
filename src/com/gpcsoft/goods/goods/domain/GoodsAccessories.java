package com.gpcsoft.goods.goods.domain;
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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>零配件</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-27 上午11:50:28 by yucy    					                            
  *  <br/>Modified Date:  2010-7-27 上午11:50:28 by yucy                                   
  *  <p>@since 0.5
  *   
  * @gpcsoft.package packageDir="com.gpcsoft.goods.GoodsAccessories"
  * @gpcsoft.page domain="GoodsAccessories"
  * @hibernate.class table="GOODS_ACCESSORIES"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "GOODS_ACCESSORIES")
public class GoodsAccessories implements GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime {
    /** serialVersionUID */
    private static final long serialVersionUID = 4300282297503759581L;

    /**零配件Id*/
    @Id
    @Column(name = "GOODS_ACCESSORIES_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**主商品*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODS_ID")
	private Goods goods;
	
	/**配件商品*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCESSORYGOODS_ID")
	private Goods accessoryGoods;
	
	/**数量*/
	@Column(name = "ACCESSORY_QTY", precision = 16, scale = 6)
    private BigDecimal accessoryQty;
	
	/**是否启用*/
	@Column(name = "IS_OFF", length = 50)
	private String isOff; /*禁用启用，2.禁用 1.启用。默认启用*/

	/** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="CREATOR") 
    @BatchSize(size = 15)
    private User createUser;
    
    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    /** 修改人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="MODIFIER")
    @BatchSize(size = 15)
    private User updateUser;
    
    /**修改时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_TIME")
    private Date updateTime;
    
    public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	public Goods getAccessoryGoods() {
		return accessoryGoods;
	}

	public void setAccessoryGoods(Goods accessoryGoods) {
		this.accessoryGoods = accessoryGoods;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
    public String getIsOff() {
		return isOff;
	}

	public void setIsOff(String isOff) {
		this.isOff = isOff;
	}

	public BigDecimal getAccessoryQty() {
		return accessoryQty;
	}

	public void setAccessoryQty(BigDecimal accessoryQty) {
		this.accessoryQty = accessoryQty;
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
}