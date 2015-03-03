package com.gpcsoft.bizplatform.base.evaluate.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.model.GpcBaseObject;


/** 
  *  Comments: <strong>评价指标</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   bizplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-7-20 下午03:12:40 by yucy    					                            
  *  <br/>Modified Date:  2010-7-20 下午03:12:40 by yucy                                   
  *	  @since 0.5
  *   @version: 0.5 
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PUB_EVALUATE_QUOTA")
public class Quota implements GpcBaseObject {

	/** serialVersionUID */
	private static final long serialVersionUID = 1138903322202346810L;

	/**主键*/
	@Id
	@Column(name = "QUOTA_ID",length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
    
	/**指标名称*/
	@Column(name = "QUOTA_NAME", length = 200)
	private String name;

	/**比重*/
	@Column(name = "QUOTA_PROPORTION")
	private BigDecimal proportion;
	
	/**角色类型*/
	@Column(name = "QUOTA_TYPE")
	private String type;
	
	public String getObjId() {
		return objId;
	}
	
	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getProportion() {
		return proportion;
	}

	public void setProportion(BigDecimal proportion) {
		this.proportion = proportion;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getTypeCN(){
		return OrganizationEnum.getOrgTypeCN(this.getType());
	}

	public void setCreateTime(Date arg0) {
		
	}
	
	public Date getCreateTime() {
		return null;
	}
	
	
	
	
}