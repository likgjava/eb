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
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;

/** 
 *  Comments: <strong>推荐商品品牌</strong>            		
 *	<br/>		        																							
 *  <br/>CopyRright (c)2011-03-25:   珠海政采软件技术有限公司    		
 *  <br/>Project:   goods                    					          
 *  <br/>Module ID: 商品库    		
 *  <br/>Create Date：2011-5-16 下午09:15:36 by likg    					                            
 *  <br/>Modified Date:  2011-5-16 下午09:15:36 by likg                                   
 *  <p>@since 0.5
 *  @version: 0.5 
 *   
 *   
 * @gpcsoft.package packageDir="com.gpcsoft.goods.goods"
 * @gpcsoft.page domain="RecommendGoodsBrand"
 * @hibernate.class table="RECOMMEND_GOODS_BRAND"
 * @author Administrator
 * @version 1.0
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "RECOMMEND_GOODS_BRAND")
public class RecommendGoodsBrand implements GpcBaseObject {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 4084345881318127625L;

	/** 主键 */
	@Id
    @Column(name = "ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 推荐的商品品牌 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="GOODS_BRAND_ID")
	@BatchSize(size = 15)
	private GoodsBrand goodsBrand;
	
	/** 推荐人 */
	@Column(name = "RECOMMENDER", length = 50)
	private String recommender;
	
	/** 推荐理由 */
	@Column(name = "REASON", length = 1000)
	private String reason;
	
	/** 创建时间 */
	@Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TIME")
	private Date createTime;
	
	/** 排序序号 */
	@Column(name = "SORT", length = 6)
	private Long sort;

	/** @gpcsoft.property title="主键" */
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/** @gpcsoft.property title="商品品牌" */
	public GoodsBrand getGoodsBrand() {
		return goodsBrand;
	}

	public void setGoodsBrand(GoodsBrand goodsBrand) {
		this.goodsBrand = goodsBrand;
	}

	/** @gpcsoft.property title="推荐人" */
	public String getRecommender() {
		return recommender;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}

	/** @gpcsoft.property title="推荐理由" */
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	/** @gpcsoft.property title="创建时间" */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** @gpcsoft.property title="序号" */
	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
}
