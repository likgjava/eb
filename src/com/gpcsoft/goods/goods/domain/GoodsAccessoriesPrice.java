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
import com.gpcsoft.goods.goodsprice.domain.GoodsPrice;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceSupplier;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>零配件价格</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-27 上午11:50:28 by yucy    					                            
  *  <br/>Modified Date:  2010-7-27 上午11:50:28 by yucy                                   
  *  <p>@since 0.5
  *   
  * @gpcsoft.package packageDir="com.gpcsoft.goods.GoodsAccessoriesPrice"
  * @gpcsoft.page domain="GoodsAccessoriesPrice"
  * @hibernate.class table="GOODS_ACCESSORIES_PRICE"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "GOODS_ACCESSORIES_PRICE")
public class GoodsAccessoriesPrice implements GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime {
    /** serialVersionUID */
    private static final long serialVersionUID = 4300282297503759581L;

    /**零配件价格Id*/
    @Id
    @Column(name = "GOODS_ACCESSORIES_PRICE_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**零配件*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODS_ACCESSORIES_ID")
	private GoodsAccessories goodsAccessories;
	
	/**行情供应商id*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="SUPPLIER_ID")
    @BatchSize(size = 15)
	private GoodsPriceSupplier supplier;
    
    /**行情*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="GOODS_PRICE_ID")
    @BatchSize(size = 15)
	private GoodsPrice goodsPrice;
	
    /**价格*/
    @Column(name = "ACCESSORIES_PRICE" )
    private BigDecimal accessoryPrice;

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
	
    public GoodsAccessories getGoodsAccessories() {
		return goodsAccessories;
	}

	public void setGoodsAccessories(GoodsAccessories goodsAccessories) {
		this.goodsAccessories = goodsAccessories;
	}

	public GoodsPriceSupplier getSupplier() {
		return supplier;
	}

	public void setSupplier(GoodsPriceSupplier supplier) {
		this.supplier = supplier;
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

	public GoodsPrice getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(GoodsPrice goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public BigDecimal getAccessoryPrice() {
		return accessoryPrice;
	}

	public void setAccessoryPrice(BigDecimal accessoryPrice) {
		this.accessoryPrice = accessoryPrice;
	}
}