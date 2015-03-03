package com.gpcsoft.smallscale.groupbuying.domain;

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
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>团购分类</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   smallscale                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2011-6-21 上午08:49:53 by likg    					                            
  *  <br/>Modified Date:  2011-6-21 上午08:49:53 by likg                                   
  *  <p>@since 0.5
  *
  * @gpcsoft.package packageDir="com.gpcsoft.smallscale.groupbuying"
  * @gpcsoft.page domain="GroupBuyingClass"
  * @hibernate.class table="EPS_GROUP_BUYING_CLASS"
  * @author Administrator
  * @version 1.0
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate=true, dynamicInsert=true)
@Table(name="EPS_GROUP_BUYING_CLASS")
@OrderProperty(property="sort", flag="false")
public class GroupBuyingClass implements GpcBaseObject ,IPropertyCUserTime {

	/** serialVersionUID */
	private static final long serialVersionUID = 1512449265633544574L;

	/**记录号*/
    @Id
    @Column(name = "GROUP_BUYING_CLASS_ID", length=50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**商品分类*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="GOODS_CLASS_ID")
    @BatchSize(size=15)
    private GoodsClass goodsClass;
    
    /**团购分类名称*/
    @Column(name="NAME", length=100)
    private String name;
    
    /**是否显示在首页*/
    @Column(name="IS_SHOW_INDEX", length=1)
    private Boolean isShowIndex;
    
	/**排序*/
    @Column(name = "SORT")
    private Long sort;
    
    /**创建人*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="CREATOR_ID")  
    @BatchSize(size = 15)
    private User createUser;
    
    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsShowIndex() {
		return isShowIndex;
	}

	public void setIsShowIndex(Boolean isShowIndex) {
		this.isShowIndex = isShowIndex;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
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

    
    
}
