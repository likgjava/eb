package com.gpcsoft.goods.goodsprice.domain;

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
import com.gpcsoft.goods.goods.domain.GoodsOptionalFitting;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>选配价格</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 协议供货-行情     		
  *  <br/>Create Date：2010-4-16 下午03:16:35 by yucy    					                            
  *  <br/>Modified Date:  2010-4-16 下午03:16:35 by yucy                                   
  *  
  *   @since 0.5
  *   @version: 0.5 
  *   
  *  @gpcsoft.package packageDir="com.gpcsoft.goods.goodsprice"
  *  @gpcsoft.page domain="GoodsOptFitPrice" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="选配价格"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "GOODS_OPT_FIT_PRICE")
public class GoodsOptFitPrice implements GpcBaseObject,IPropertyCUserTime,IPropertyUUserTime {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1598421473847241257L;

	/**记录号*/
    @Id
    @Column(name = "GOODS_OPT_FIT_PRICE_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
	
	/**商品行情*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODS_PRICE_ID")
	private GoodsPrice goodsPrice;
	
	/**选配*/
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODS_OPT_FIT_ID")
    private GoodsOptionalFitting goodsOptionalFitting;
	
    /**相对价格*/
    @Column(name = "RELATIVE_PRICE" )
    private BigDecimal relativePrice;
    
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

	public GoodsPrice getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(GoodsPrice goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public GoodsOptionalFitting getGoodsOptionalFitting() {
		return goodsOptionalFitting;
	}

	public void setGoodsOptionalFitting(GoodsOptionalFitting goodsOptionalFitting) {
		this.goodsOptionalFitting = goodsOptionalFitting;
	}

	public BigDecimal getRelativePrice() {
		return relativePrice;
	}

	public void setRelativePrice(BigDecimal relativePrice) {
		this.relativePrice = relativePrice;
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