package com.gpcsoft.epp.resproject.domain;

import java.math.BigDecimal;

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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_MONEY_SOURCE")
public class MoneySource extends WorkFlowModel implements GpcBaseObject{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MONEY_SOURCE_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;                 //主键                        
	
	@Column(name="MONEY")
	private BigDecimal money;            //金额
	
	@Column(name="REMARK")
	private String remark;               //备注       
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="ZJLY_CATEGORY_ID", updatable = false)	 
	@BatchSize(size = 15)
	private MoneySourceCategory moneySourceCategory;  //资金来源类型
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="PROJECT_ID", updatable = false)	 
	@BatchSize(size = 15)
	private ResProject resProject;  //对应的项目
	
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="PROJECT_ITEM_ID", updatable = false)	 
	@BatchSize(size = 15)
	private ResProjectItem resProjectItem;  //对应的项目条目


	public String getObjId() {
		return objId;
	}


	public void setObjId(String objId) {
		this.objId = objId;
	}


	public BigDecimal getMoney() {
		return money;
	}


	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public MoneySourceCategory getMoneySourceCategory() {
		return moneySourceCategory;
	}

	public void setMoneySourceCategory(MoneySourceCategory moneySourceCategory) {
		this.moneySourceCategory = moneySourceCategory;
	}
	
	public ResProject getResProject() {
		return resProject;
	}

	public void setResProject(ResProject resProject) {
		this.resProject = resProject;
	}

	public ResProjectItem getResProjectItem() {
		return resProjectItem;
	}

	public void setResProjectItem(ResProjectItem resProjectItem) {
		this.resProjectItem = resProjectItem;
	}
}
