package com.gpcsoft.epp.project.domain;

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
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>SuperiorObj</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2011-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   zh-epp                    					          
 *  <br/>Module ID:     		
 *  <br/>Create Date：2011-12-22 下午05:36:13 by chenhongjun    					                            
 *  <br/>Modified Date:  2011-12-22 下午05:36:13 by chenhongjun                                   
 *<p>@since 0.5
 *   @version: 0.5 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_SUPERIOR")
public class SuperiorObj  implements  GpcBaseObject, IPropertyCUserTime, IPropertyUUserTime {

	@Id
	@Column(name = "ECP_SUPERIOR_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/**
	 * 监理对象名称
	 */
	@Column(name="SUPERIOR_NAME")
	private String superiorName; 

	
	/**
	 * 类型:00:房屋建筑工程,01:市政公用设施,02:交通工程,03:设备安装工程,04:装饰装修工程,05:其它工程
	 */
	@Column(name="SUPERIOR_CODE")
	private String superiorCode; 
	
	/**
	 * 面积-内容描述(m2
	 */
	@Column(name="ENG_AREA")
	private String area; 
	
	/**
	 * 结构形式
	 */
	@Column(name="STRUCT_STYLE")
	private String structStyle; 
	
	/**
	 * 层数-内容描述
	 */
	@Column(name="LEVEL_NUM_VAR")
	private String levelNumVar; 
	
	/**
	 * 跨度内容描述(m)
	 */
	@Column(name="SPAN_VAR")
	private String spanVar; 
	
	/**
	 * 檐口高度(m)
	 */
	@Column(name="CORNICE_VAR")
	private String corniceVar; 
	
	/**
	 * 工程类型[一类,二类,三类]
	 */
	@Column(name="ENG_TYPE")
	private String engType; 
	
	/**
	 * 发包范围
	 */
	@Column(name="AWARD_BOUND")
	private String awardBound; 
	
	/**
	 * 规模
	 */
	@Column(name="CONSTRUCT_SCALE")
	private String constructScale; 
	
	/**
	 * 招标项目Id
	 * 
	 */
	@Column(name="TENDER_ID")
	private String tenderId; 
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CreUser", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User createUser;

    //创建时间
    @Column(name = "CreDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Column(name = "Template_ID", length = 50)
	private String templateId;//计划模版Id

	@Column(name = "UseStatus", length = 2)
	private String useStatus;//使用状态

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getSuperiorName() {
		return superiorName;
	}

	public void setSuperiorName(String superiorName) {
		this.superiorName = superiorName;
	}

	public String getSuperiorCode() {
		return superiorCode;
	}

	public void setSuperiorCode(String superiorCode) {
		this.superiorCode = superiorCode;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStructStyle() {
		return structStyle;
	}

	public void setStructStyle(String structStyle) {
		this.structStyle = structStyle;
	}

	public String getLevelNumVar() {
		return levelNumVar;
	}

	public void setLevelNumVar(String levelNumVar) {
		this.levelNumVar = levelNumVar;
	}

	public String getSpanVar() {
		return spanVar;
	}

	public void setSpanVar(String spanVar) {
		this.spanVar = spanVar;
	}

	public String getCorniceVar() {
		return corniceVar;
	}

	public void setCorniceVar(String corniceVar) {
		this.corniceVar = corniceVar;
	}

	public String getEngType() {
		return engType;
	}

	public void setEngType(String engType) {
		this.engType = engType;
	}

	public String getAwardBound() {
		return awardBound;
	}

	public void setAwardBound(String awardBound) {
		this.awardBound = awardBound;
	}

	public String getConstructScale() {
		return constructScale;
	}

	public void setConstructScale(String constructScale) {
		this.constructScale = constructScale;
	}

	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
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

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	/* Description :
	 * @param   
	 * @return  
	 * @Exception   
	 *  Create Date: 2011-12-22下午05:46:55 by chenhongjun  
	 *  Modified Date: 2011-12-22下午05:46:55 by chenhongjun 
	 * @see com.gpcsoft.core.model.IPropertyUUserTime#getUpdateUser()
	 *
	 */
	public User getUpdateUser() {
		// TODO Auto-generated method stub
		return null;
	}

	/* Description :
	 * @param   
	 * @return  
	 * @Exception   
	 *  Create Date: 2011-12-22下午05:46:55 by chenhongjun  
	 *  Modified Date: 2011-12-22下午05:46:55 by chenhongjun 
	 * @see com.gpcsoft.core.model.IPropertyUUserTime#setUpdateUser(com.gpcsoft.srplatform.auth.domain.User)
	 *
	 */
	public void setUpdateUser(User arg0) {
		// TODO Auto-generated method stub
		
	}

	/* Description :
	 * @param   
	 * @return  
	 * @Exception   
	 *  Create Date: 2011-12-22下午05:46:55 by chenhongjun  
	 *  Modified Date: 2011-12-22下午05:46:55 by chenhongjun 
	 * @see com.gpcsoft.core.model.IPropertyUTime#getUpdateTime()
	 *
	 */
	public Date getUpdateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	/* Description :
	 * @param   
	 * @return  
	 * @Exception   
	 *  Create Date: 2011-12-22下午05:46:55 by chenhongjun  
	 *  Modified Date: 2011-12-22下午05:46:55 by chenhongjun 
	 * @see com.gpcsoft.core.model.IPropertyUTime#setUpdateTime(java.util.Date)
	 *
	 */
	public void setUpdateTime(Date arg0) {
		// TODO Auto-generated method stub
		
	}
	   
	     

}
