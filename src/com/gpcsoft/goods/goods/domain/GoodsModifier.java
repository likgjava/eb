package com.gpcsoft.goods.goods.domain;
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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>商品维护商</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-27 下午02:15:55 by yucy    					                            
  *  <br/>Modified Date:  2010-7-27 下午02:15:55 by yucy                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "GOODS_MODIFIER")
@OrderProperty( property="goodsClass.objId" ,flag = "true" )
public class GoodsModifier implements GpcBaseObject ,IPropertyUUserTime,IPropertyCUserTime {

    /** serialVersionUID */
	private static final long serialVersionUID = 6458215734945445261L;

	@Id
    @Column(name = "GOODS_MODIFIER_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**商品维护商*/
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="SUPPLIER_ID")
    @BatchSize(size = 15)
    private OrgInfo supplier;
    
    /**商品分类列表*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="GOODS_CLASS_ID")    
    @BatchSize(size = 15)
    private GoodsClass goodsClass;
    
    /**品牌*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="GOODS_BRAND_ID")
    @BatchSize(size = 15)
    private GoodsBrand goodsBrand;

    /**创建人*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATOR_ID", updatable = false)	 
	@BatchSize(size = 15)
    private User createUser;
    
	/**创建时间*/
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /** 修改人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="MODIFIER_ID", updatable = false) 
	@BatchSize(size = 15)
    private User updateUser;

	/**修改时间*/
    @Temporal(TemporalType.DATE)
    @Column(name = "MODIFY_TIME")
    private Date updateTime;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public OrgInfo getSupplier() {
		return supplier;
	}

	public void setSupplier(OrgInfo supplier) {
		this.supplier = supplier;
	}

	public GoodsClass getGoodsClass() {
		return goodsClass;
	}

	public void setGoodsClass(GoodsClass goodsClass) {
		this.goodsClass = goodsClass;
	}

	public GoodsBrand getGoodsBrand() {
		return goodsBrand;
	}

	public void setGoodsBrand(GoodsBrand goodsBrand) {
		this.goodsBrand = goodsBrand;
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