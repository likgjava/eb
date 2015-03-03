package com.gpcsoft.agreement.bargin.project.domain;
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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>评审规则表</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   order                    					          
 *  <br/>Create Date：2010-9-29 下午03:50:06 by sunl    					                            
 *  <br/>Modified Date:  2010-9-29 下午03:50:06 by sunl           
 *                          
 *  @since 0.5
 *  @version: 0.5 
 *  
 *  @gpcsoft.package packageDir="com.gpcsoft.agreement.bargin.project"
 *  @gpcsoft.page domain="project" project="project"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="评审规则表"
 */ 

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_ASSESSMENT_RULE")
public class AssessmentRule implements GpcBaseObject,IPropertyCUserTime {

	/** serialVersionUID */
	private static final long serialVersionUID = -1642506943007403411L;

	/**ID*/
    @Id
    @Column(name = "ASSESSMENT_RULE_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**规则名称*/
    @Column(name = "ASSESSMENT_RULE_NAME")
    private String name;
    
	/**附件*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="ASSESSMENT_FILE")	 
    @Cascade({CascadeType.ALL})
	@BatchSize(size = 15)
	private Attachment assessmentFile;
	
    /**品目id*/
    @Column(name = "CATEGORY_ID")
    private String categoryId;
    
    /**品目名称*/
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
    
    /** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="CREATOR") 
    @BatchSize(size = 15)
    private User createUser;
    
    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

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

	public Attachment getAssessmentFile() {
		return assessmentFile;
	}

	public void setAssessmentFile(Attachment assessmentFile) {
		this.assessmentFile = assessmentFile;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
}