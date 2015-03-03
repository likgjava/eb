package com.gpcsoft.agreement.bargin.require.domain;
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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.goods.goods.domain.GoodsAccessories;

/** 
 *  Comments: <strong>需求商品配件</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   agreement                    					          
 *  <br/>Module ID: 协议供货   		
 *  <br/>Create Date：2010-4-7 下午03:50:06 by yucy    					                            
 *  <br/>Modified Date:  2010-4-7 下午03:50:06 by yucy           
 *                          
 *  @since 0.5
 *  @version: 0.5 
 *  
 *  @gpcsoft.package packageDir="com.gpcsoft.agreement.bargin.require.domain"
 *  @gpcsoft.page domain="require" project="agreement"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="需求商品配件"
 */ 

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_REQUIRE_GOODS_ACCESS")
public class RequireGoodsAccessories implements GpcBaseObject {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1249379273192449573L;

	/**记录号*/
    @Id
    @Column(name = "REQUIRE_ACC_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
	
    /**零配件*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="GOODS_ACCESSORIES_ID") 
    @BatchSize(size = 15)
    private GoodsAccessories goodsAccess;
    
    /**需求明细*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="REQUIRE_DTL_ID") 
    @BatchSize(size = 15)
    private RequireItem requireItem;

	/**配件价格*/
	@Column(name = "ACC_PRICE", precision = 16, scale = 6)
    private BigDecimal accessPrice;

	/** @gpcsoft.property title="主键" */
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/** @gpcsoft.property title="需求条目" */
	public RequireItem getRequireItem() {
		return requireItem;
	}

	public void setRequireItem(RequireItem requireItem) {
		this.requireItem = requireItem;
	}
	
	/** @gpcsoft.property title="配件" */
	public GoodsAccessories getGoodsAccess() {
		return goodsAccess;
	}

	public void setGoodsAccess(GoodsAccessories goodsAccess) {
		this.goodsAccess = goodsAccess;
	}

	/** @gpcsoft.property title="配件价格" */
	public BigDecimal getAccessPrice() {
		return accessPrice;
	}

	public void setAccessPrice(BigDecimal accessPrice) {
		this.accessPrice = accessPrice;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date arg0) {
	}
	
}