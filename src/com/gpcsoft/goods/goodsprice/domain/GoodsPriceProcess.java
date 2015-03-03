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

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.srplatform.baseData.domain.District;

/** 
  *  Comments: <strong>行情统计单元</strong>            		
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
  *  @gpcsoft.page domain="GoodsPriceProcess" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="行情统计单元"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PROCESS_GOODS_PRICE")
public class GoodsPriceProcess implements GpcBaseObject {

    /** serialVersionUID */
	private static final long serialVersionUID = -179662526345327228L;

	/**记录号*/
    @Id
    @Column(name = "PROCESS_GOODS_PRICE_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
	
	/**供应商*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPLIER_ID")
	private GoodsPriceSupplier goodsPriceSupplier;
	
    /**金额*/
    @Column(name = "AMOUNT" )
	private BigDecimal amount;
    
	/**月*/
    @Column(name = "MONTH" )
	private Integer month;
    
    /**年*/
    @Column(name = "YEAR" )
	private Integer year;
    
    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

	/**供应商*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISTRICT_ID")
	private District district;
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public GoodsPriceSupplier getGoodsPriceSupplier() {
		return goodsPriceSupplier;
	}

	public void setGoodsPriceSupplier(GoodsPriceSupplier goodsPriceSupplier) {
		this.goodsPriceSupplier = goodsPriceSupplier;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
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
	
}