package com.gpcsoft.epp.expertrule.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.eprocurement.taker.provider.CodePO;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>Expertrule</strong>            		
 *	 <br/>抽取专家规则	        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司     		
 *  <br/>Project:   agreement                    					          
 *  <br/>Module ID: 执行平台--专家规则
 *  <br/>Create Date：2010-8-2 下午02:37:42 by yangx    					                            
 *  <br/>Modified Date:  2010-8-2 下午02:37:42 by yangx                                   
 *   @gpcsoft.package packageDir="com.gpcsoft.epp.expertrule"
 *   @gpcsoft.page domain="expertrule" project="es/planform"
 *   @gpcsoft.domain
 *   @gpcsoft.title value="专家规则"
 */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_TAKEEXPERTRULE")
public class TakeExpertRule extends WorkFlowModel implements GpcBaseObject ,IPropertyCUserTime {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "TAKEEXPERTRULE_ID", unique = true, nullable = false, length = 36)
	private String objId;
	
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	@OneToMany(mappedBy="takeExpertRule",fetch=FetchType.LAZY) //映射为单一对多关系
	@Cascade({CascadeType.ALL})
	private Set<TakeExpertRuleItem> items = new HashSet<TakeExpertRuleItem>();//条目集
	
	@Column(name = "AMOUNT")
	private String amount;//抽取的正选专家

	@Column(name = "SUB_AMOUNT")
	private String subAmount;//备选专家 
	
	@Column(name = "ASSEMBLE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date assembleTime;//集合时间
	
	@Column(name = "ASSEMBLE_ADDR")
	private String assembleAddr;//集合地点
	
	@Column(name = "BID_STARTTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bidStarttime;//评审开始时间
	
	@Column(name = "BID_ENDTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bidEndtime;//评审结束时间
	
	@Column(name = "OUT_BUYER_IDS")
	private String outBuyerIds;//回避单位 Id
	
	@Column(name = "OUT_BUYER_NAMES")
	private String outBuyerNames;//回避单位 名称
	
	@Column(name = "BUYER_NAME_IDS")
	private String buyerNameIds;//采购单位 Id
	
	@Column(name = "BUYER_NAMES")
	private String buyerNames;//采购单位 名称
	
	@Column(name = "EXPERT_ID")
	private String expertId;//回避专家
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="SUBTENDERID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Project subProjectId;//关联的包组ID
	
	@Column(name = "EXPERT_LEVEL")
	private String expertLevel;//抽取类型   1:正常抽取 2:只抽主评
	
//	@ManyToOne(fetch=FetchType.LAZY, optional=true)
//	@JoinColumn(name="EXPERT_CATEGORY_ID")//关联的外键	 
//	@BatchSize(size = 15)//批量抓取	
	@Transient
	private PurCategory purCategory;//评选品目 
	
	@Column(name = "EXPERT_CATEGORY_ID")
	private String purCategoryId;//评选品目 
	
	@Column(name = "BID_ROOM")
	private String bidRoom;//评审室
	
	@Transient
	private List<CodePO> bidRooms;//评审室

	@Column(name = "CITY_CODE")
	private String cityCode;//评审区域 

	@Transient
	private List<CodePO> cityCodeName;//评审区域 
	
	@Transient
	private String cityNames;//评审区域  所有条目专家的评审区域合集打印预览用 add by wangcl
	
	@Column(name = "SPECIALTY_YEAR")
	private String specialtyYear;//专业工龄 
	
	@Column(name = "EXPERT_GROUP")
	private String expertGroup;//专家类型 
	
	@Transient
	private List<CodePO> expertGroupName;//专家类型名称
	
	@Column(name = "TOP_EDUC")
	private String topEduc;//学历	

	@Transient
	private List<CodePO> topEducName;//学历名称	
	
	@Column(name = "AGE")
	private String age;//年龄 
	
	@Column(name = "EXPERTRULE_TYPE")
	private String ExpertRuleType; // 规则类型：论证规则，评审规则，开标规则,工作规则
	
	@Transient
	private String ExpertRuleTypeCN;

	@Column(name = "USE_STATUS", length = 2)
	private String useStatus;//使用状态:00临时,01正式
	
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
	private String isSubRule;
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	public String getExpertRuleType() {
		return ExpertRuleType;
	}
	
	public void setExpertRuleType(String expertRuleType) {
		ExpertRuleType = expertRuleType;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Project getSubProjectId() {
		return subProjectId;
	}

	public void setSubProjectId(Project subProjectId) {
		this.subProjectId = subProjectId;
	}

	public PurCategory getPurCategory() {
		return purCategory;
	}

	public void setPurCategory(PurCategory purCategory) {
		this.purCategory = purCategory;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public String getExpertRuleTypeCN() {
		ExpertRuleTypeCN=ExpertRuleTypeEnum.getCN(this.getExpertRuleTypeCN());
		return ExpertRuleTypeCN;
	}

	public void setExpertRuleTypeCN(String expertRuleTypeCN) {
		this.ExpertRuleTypeCN = expertRuleTypeCN;
	}

	public Date getBidStarttime() {
		return bidStarttime;
	}

	public void setBidStarttime(Date bidStarttime) {
		this.bidStarttime = bidStarttime;
	}

	public String getOutBuyerIds() {
		return outBuyerIds;
	}

	public void setOutBuyerIds(String outBuyerIds) {
		this.outBuyerIds = outBuyerIds;
	}


	public String getBuyerNameIds() {
		return buyerNameIds;
	}

	public void setBuyerNameIds(String buyerNameIds) {
		this.buyerNameIds = buyerNameIds;
	}

	public String getBuyerNames() {
		return buyerNames;
	}

	public void setBuyerNames(String buyerNames) {
		this.buyerNames = buyerNames;
	}

	public String getSubAmount() {
		return subAmount;
	}

	public void setSubAmount(String subAmount) {
		this.subAmount = subAmount;
	}

	public Date getAssembleTime() {
		return assembleTime;
	}

	public void setAssembleTime(Date assembleTime) {
		this.assembleTime = assembleTime;
	}

	public String getAssembleAddr() {
		return assembleAddr;
	}

	public void setAssembleAddr(String assembleAddr) {
		this.assembleAddr = assembleAddr;
	}

	public Date getBidEndtime() {
		return bidEndtime;
	}

	public void setBidEndtime(Date bidEndtime) {
		this.bidEndtime = bidEndtime;
	}

	public String getExpertId() {
		return expertId;
	}

	public void setExpertId(String expertId) {
		this.expertId = expertId;
	}

	public String getExpertLevel() {
		return expertLevel;
	}

	public void setExpertLevel(String expertLevel) {
		this.expertLevel = expertLevel;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getSpecialtyYear() {
		return specialtyYear;
	}

	public void setSpecialtyYear(String specialtyYear) {
		this.specialtyYear = specialtyYear;
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

	public String getOutBuyerNames() {
		return outBuyerNames;
	}

	public void setOutBuyerNames(String outBuyerNames) {
		this.outBuyerNames = outBuyerNames;
	}

	public String getBidRoom() {
		return bidRoom;
	}

	public void setBidRoom(String bidRoom) {
		this.bidRoom = bidRoom;
	}

	public String getPurCategoryId() {
		return purCategoryId;
	}

	public void setPurCategoryId(String purCategoryId) {
		this.purCategoryId = purCategoryId;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public List<CodePO> getBidRooms() {
		return bidRooms;
	}

	public void setBidRooms(List<CodePO> bidRooms) {
		this.bidRooms = bidRooms;
	}

	public List<CodePO> getCityCodeName() {
		return cityCodeName;
	}

	public void setCityCodeName(List<CodePO> cityCodeName) {
		this.cityCodeName = cityCodeName;
	}

	public List<CodePO> getExpertGroupName() {
		return expertGroupName;
	}

	public void setExpertGroupName(List<CodePO> expertGroupName) {
		this.expertGroupName = expertGroupName;
	}

	public List<CodePO> getTopEducName() {
		return topEducName;
	}

	public void setTopEducName(List<CodePO> topEducName) {
		this.topEducName = topEducName;
	}

	public String getIsSubRule() {
		return isSubRule;
	}

	public void setIsSubRule(String isSubRule) {
		this.isSubRule = isSubRule;
	}

	public Set<TakeExpertRuleItem> getItems() {
		return items;
	}

	public void setItems(Set<TakeExpertRuleItem> items) {
		this.items = items;
	}

	public String getCityNames() {
		return cityNames;
	}

	public void setCityNames(String cityNames) {
		this.cityNames = cityNames;
	}
	
	
}
