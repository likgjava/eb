package com.gpcsoft.epp.expertrule.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

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
import com.gpcsoft.core.tree.BaseTree;
import com.gpcsoft.core.tree.TreeProperty;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>Goods</strong>            		
  *	 <br/> 采购品目  		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: Demo示例     		
  *  <br/>Create Date：2009-12-29 上午09:52:36 by wangsw    					                            
  *  <br/>Modified Date:  2009-12-29 上午09:52:36 by wangsw                                 
  *      
  *  @since 0.5
  *  @version: 0.5 
  *  
  * @gpcsoft.package packageDir="com.gpcsoft.epp.expertrule"
 * @gpcsoft.page domain="planform/expertrule" project="es"
  * @gpcsoft.domain
  * @gpcsoft.title value="专家资格品目"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "PURCATALOG_CATEGORY_EXP")
//注意TreeProperty这个注解必须
//分别代表：顶部图标、节点图标、树标题、树显示文本在domain字的private属性，  其中父节点属性默认是  parent, 如果改变需写 parent=xxx
@TreeProperty(topIcon="tombs.gif", nodeIcon="_treelogo.gif", title="采购品目", text="categoryName")
public class ExpertRulePurchaseCategory implements GpcBaseObject, BaseTree<ExpertRulePurchaseCategory>{ //BaseDHtmlTree注意

	private static final long serialVersionUID = 1L;

	/** 主键 */
	@Id
	@Column(name = "ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/**
	 * 父品目
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 15)
	@JoinColumn(name="PARENT_ID") 		
	private ExpertRulePurchaseCategory parent;
	
	/**
	 * 品目编码
	 */
	@Column(name = "CATEGORY_CODE")
	private String categoryCode;
	/**
	 * 品目名称
	 */
	@Column(name = "CATEGORY_NAME")
	private String categoryName;
	
	/**
	 * 品目说明
	 */
	@Column(name = "REMARKS")
	private String remarks;
 
	//发布时间
    @Column(name = "RELEASE_DATE", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseTime;

	/**
	 * 发布状态
	 */
	@Column(name = "RELEASE_STATUS")
	private String releaseStatus;
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATE_USER", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User createUser;

    //创建时间
    @Column(name = "CREATE_DATE", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
	
    /** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="UPDATE_USER", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User updateUser;

    //创建时间
    @Column(name = "UPDATE_DATE", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    
    @Column(name = "CATEGORY_SORT")
	private String categorySort; 
    
    /**
	 * 是否为叶子节点
	 */
    @Column(name = "PURCATEGORY_IS_LEAF" ,columnDefinition = "default='1'")
	private Boolean isLeaf;				
    


	/**
	 * 简称
	 */
    @Column(name = "SHORT_SPELL_NAME")
	private Boolean shotSpellName;				
    
    /**
	 * 排序
	 */
    
	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
   
    
  
	
    
    public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getShotSpellName() {
		return shotSpellName;
	}

	public void setShotSpellName(Boolean shotSpellName) {
		this.shotSpellName = shotSpellName;
	}

	public String getCategorySort() {
		return categorySort;
	}

	public void setCategorySort(String categorySort) {
		this.categorySort = categorySort;
	}

     public Date getCreateTime() {
			return createTime;
	}

	public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
	
	

	public String getRemarks() {
		return remarks;
	}

	public String getReleaseStatus() {
		return releaseStatus;
	}

	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	
	/**
	 * @gpcsoft.property title="parent<中文解释>"
	 */
	public ExpertRulePurchaseCategory getParent() {
		return parent;
	}

	/** parent<中文解释> */
	public void setParent(ExpertRulePurchaseCategory parent) {
		this.parent = parent;
	}

	/**
	 * @gpcsoft.property title="categoryCode<中文解释>"
	 */
	public String getCategoryCode() {
		return categoryCode;
	}

	/** categoryCode<中文解释> */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	/**
	 * @gpcsoft.property title="categoryName<中文解释>"
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/** categoryName<中文解释> */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	

	/** objId<中文解释> */
	public void setObjId(String objId) {
		this.objId = objId;
	}

	/**
	 * @gpcsoft.property title="objId<中文解释>"
	 */
	public String getObjId() {
		return objId;
	}

	
	
	public int hashCode(){
		return UUID.randomUUID().toString().hashCode();  
	}  
	
	public boolean equals(Object object){
		if(object instanceof ExpertRulePurchaseCategory){
			ExpertRulePurchaseCategory purchaseCategory=(ExpertRulePurchaseCategory)object;
			if(purchaseCategory.getObjId().equals(this.objId))
				return true;
		}
		return false;  
	}

	public Set getChildren() {
		
		return null;
	}

	 
	
}