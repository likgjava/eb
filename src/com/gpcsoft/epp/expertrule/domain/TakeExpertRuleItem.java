package com.gpcsoft.epp.expertrule.domain;

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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>TakeExpertRuleItem</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 执行平台    		
  *  <br/>Create Date：2010-8-30 下午07:23:05 by yangx    					                            
  *  <br/>Modified Date:  2010-8-30 下午07:23:05 by yangx                                   
  *	  @since 0.5
  *   @version: 0.5 
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_EXPERTCONDITION")
public class TakeExpertRuleItem extends WorkFlowModel implements GpcBaseObject ,IPropertyCUserTime {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "EXPERTCONDITION_ID", unique = true, nullable = false, length = 36)
	private String objId;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="TAKEEXPERTRULE_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private TakeExpertRule takeExpertRule;
	
	@Column(name = "AMOUNT")
	private String amount;//抽取的正选专家
	
	@Column(name = "SUB_AMOUNT")
	private String subAmount;//备选专家 
	
	@Column(name = "CITY_CODE")
	private String cityCode;//评审区域 
	
	@Transient          
	private PurCategory purcategory;//评选品目 
	
	@Column(name = "EXPERT_CATEGORY_ID")
	private String purCategoryId;//评选品目 
	
	@Column(name = "EXPERT_LEVEL")
	private String expertLevel;//抽取类型   1:正常抽取 2:只抽主评
	
	@Column(name = "SPECIALTY_YEAR")
	private String specialtyYear;//专业工龄 
	
	@Column(name = "AGE")
	private String age;//年龄 
	
	@Transient
	private String ageStart;
	@Transient
	private String ageEnd;
	
	@Column(name = "EXPERT_GROUP")
	private String expertGroup;//专家类型 
	
	@Column(name = "TOP_EDUC")
	private String topEduc;//学历
	
	/** 创建时间 */
    @Column(name = "CRE_TIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CRE_USER_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User createUser;

	@Transient
	private String contents;//显示抽取条件内容
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSubAmount() {
		return subAmount;
	}

	public void setSubAmount(String subAmount) {
		this.subAmount = subAmount;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public PurCategory getPurcategory() {
		return purcategory;
	}

	public void setPurcategory(PurCategory purcategory) {
		this.purcategory = purcategory;
	}

	public String getPurCategoryId() {
		return purCategoryId;
	}

	public void setPurCategoryId(String purCategoryId) {
		this.purCategoryId = purCategoryId;
	}

	public String getExpertLevel() {
		return expertLevel;
	}

	public void setExpertLevel(String expertLevel) {
		this.expertLevel = expertLevel;
	}

	public String getSpecialtyYear() {
		return specialtyYear;
	}

	public void setSpecialtyYear(String specialtyYear) {
		this.specialtyYear = specialtyYear;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getExpertGroup() {
		return expertGroup;
	}

	public void setExpertGroup(String expertGroup) {
		this.expertGroup = expertGroup;
	}

	public String getTopEduc() {
		return topEduc;
	}

	public void setTopEduc(String topEduc) {
		this.topEduc = topEduc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public TakeExpertRule getTakeExpertRule() {
		return takeExpertRule;
	}

	public void setTakeExpertRule(TakeExpertRule takeExpertRule) {
		this.takeExpertRule = takeExpertRule;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getAgeStart() {
		return ageStart;
	}

	public void setAgeStart(String ageStart) {
		this.ageStart = ageStart;
	}

	public String getAgeEnd() {
		return ageEnd;
	}

	public void setAgeEnd(String ageEnd) {
		this.ageEnd = ageEnd;
	}
}
