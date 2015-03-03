package com.gpcsoft.agreement.order.domain;

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

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;

/** 
  *  Comments: <strong>订单与任务关联对象</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 协议供货-订单     		
  *  <br/>Create Date：2010-4-8 上午01:57:19 by yucy    					                            
  *  <br/>Modified Date:  2010-4-8 上午01:57:19 by yucy                                   
  *  
  *   @since 0.5
  *   @version: 0.5 
  *   
  *  @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.order"
  *  @gpcsoft.page domain="order" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="订单与任务关联对象"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_ORDER_PROTASK")
public class OrderProtask implements GpcBaseObject{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**主键*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ORDER_TASK_ID", unique = true, nullable = false, length = 50)
	private String objId;

	/**订单明细*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_DTL_ID")
	private OrderItem orderItem;
	
	/**任务书明细*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TASK_ITEM_ID")
	private ProtaskItem protaskItem;

	/**抵消金额*/
	@Column(name = "ORDER_TASK_TOTAL", precision = 16, scale = 6)
	private BigDecimal orderTaskTotal;
	
	/**抵消数量*/
	@Column(name = "ORDER_TASK_QTY", precision = 16, scale = 6)
	private BigDecimal orderTaskQty;
	
	public void setObjId(String objId){
		this.objId = objId;
	}
	
	public String getObjId() {
		return this.objId;
	}

	/** @gpcsoft.property title="订单明细"  */
	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
	
	/** @gpcsoft.property title="任务书明细"  */
	public ProtaskItem getProtaskItem() {
		return protaskItem;
	}

	public void setProtaskItem(ProtaskItem protaskItem) {
		this.protaskItem = protaskItem;
	}
	
	/** @gpcsoft.property title="抵消金额"  */
	public BigDecimal getOrderTaskTotal() {
		return orderTaskTotal;
	}

	public void setOrderTaskTotal(BigDecimal orderTaskTotal) {
		this.orderTaskTotal = orderTaskTotal;
	}

	/** @gpcsoft.property title="抵消数量"  */
	public BigDecimal getOrderTaskQty() {
		return orderTaskQty;
	}

	public void setOrderTaskQty(BigDecimal orderTaskQty) {
		this.orderTaskQty = orderTaskQty;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCreateTime(Date createTime) {
		// TODO Auto-generated method stub
		
	}
}