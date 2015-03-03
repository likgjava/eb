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
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>商品评价</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-27 下午12:45:45 by yucy    					                            
  *  <br/>Modified Date:  2010-7-27 下午12:45:45 by yucy                                   
  *   @gpcsoft.package packageDir="com.gpcsoft.goods.goods"
  *   @gpcsoft.page domain="goods" 
  *   @gpcsoft.domain
  *   @gpcsoft.title value="商品评价"
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "GOODS_EVALUATE")
public class GoodsEvaluate implements GpcBaseObject ,IPropertyCUserTime{
    

    /** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**记录号*/
    @Id
    @Column(name = "GOODS_EVALUATE_ID")
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /** 商品Id */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="GOODS_ID") 
    @BatchSize(size = 15)
    private Goods goods;
    
    /**评价标题*/
    @Column(name = "GOODS_EVALUATE_TITEL")
    private String title;
    
    /**评价级别*/
    @Column(name = "GOODS_EVALUATE_LEVEL")
    private Integer level;
    
    /**评价描述*/
    @Column(name = "GOODS_EVALUATE_REMARK")
    private String remark;
    
    /**评价人中文名*/
    @Column(name = "CREATOR_NAME")
    private String rateName;

    /** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
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

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public String getRateName() {
		return rateName;
	}

	public void setRateName(String rateName) {
		this.rateName = rateName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}