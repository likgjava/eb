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
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.baseData.domain.District;

/** 
  *  Comments: <strong>行情</strong>            		
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
  *  @gpcsoft.page domain="goods" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="关注的行情"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "GOODS_ATTENTION_PRICE")
public class AttentionPrice implements GpcBaseObject,IPropertyCUserTime {

    /** serialVersionUID */
	private static final long serialVersionUID = 7207657430891187617L;

	/**记录号*/
    @Id
    @Column(name = "GOODS_ATTENTION_PRICE_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
	
	/**商品*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODS_ID")
	private Goods goods;
	
	/**所属机构*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_INFO")
	private OrgInfo attentionOrg;
	
    /** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR_ID") 
    @BatchSize(size = 15)
    private User createUser;
	
    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
	/**关注区域*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISTRICT_ID")
	private District district;
	
	/**平均价格*/
	@Formula("( select avg(gp.prtc_price) from goods_price gp ,goods_price_supplier gs where gp.price_supplier_id = gs.goods_price_supplier_id and gs.goods_id = GOODS_ID and gp.district_id = DISTRICT_ID )" )
	private BigDecimal avgPrice;
	
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

	public OrgInfo getAttentionOrg() {
		return attentionOrg;
	}

	public void setAttentionOrg(OrgInfo attentionOrg) {
		this.attentionOrg = attentionOrg;
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

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public BigDecimal getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}
}