package com.gpcsoft.goods.goodsclass.domain;

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
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.model.GpcBaseObject;

/** 
  *  Comments: <strong>商品分类品目中间表</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-27 上午10:06:32 by yucy    					                            
  *  <br/>Modified Date:  2010-7-27 上午10:06:32 by yucy                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "GOODS_CLASS_TO_CATEGORY")
public class GoodsClassCategory implements GpcBaseObject {

	/** serialVersionUID */
	private static final long serialVersionUID = -8899334556982094887L;

	@Id
    @Column(name = "GOODS_CLASS_TO_CATEGORY_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**分类*/
    @ManyToOne(fetch=FetchType.LAZY , optional=true)
    @JoinColumn(name="GOODS_CLASS_ID")
    @BatchSize(size = 15)
    private GoodsClass goodsClass;
    
    /**采购品目*/
    @ManyToOne(fetch=FetchType.LAZY)
    @BatchSize(size = 15)
    @JoinColumn(name="PUR_CATEGORY_ID")
    private PurCategory purCategory;

	public String getObjId() {
		return objId;
	}
	
	public void setObjId(String objId) {
		this.objId = objId;
	}

	public GoodsClass getGoodsClass() {
		return goodsClass;
	}

	public void setGoodsClass(GoodsClass goodsClass) {
		this.goodsClass = goodsClass;
	}

	public PurCategory getPurCategory() {
		return purCategory;
	}

	public void setPurCategory(PurCategory purCategory) {
		this.purCategory = purCategory;
	}

	public void setCreateTime(Date arg0) {
		
	}
    
	public Date getCreateTime() {
		return null;
	}
    
}