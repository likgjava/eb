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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.agreement.order.domain.ProtaskItem;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.epp.project.domain.Project;

/** 
 *  Comments: <strong>项目与任务书中间表</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   order                    					          
 *  <br/>Create Date：2010-9-29 下午03:50:06 by sunl    					                            
 *  <br/>Modified Date:  2010-9-29 下午03:50:06 by sunl           
 *                          
 *  @since 0.5
 *  @version: 0.5 
 *  
 *  @gpcsoft.package packageDir="com.gpcsoft.agreement.bargin.project.domain"
 *  @gpcsoft.page domain="project" project="order"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="项目与任务书关联表"
 */ 

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_TEND_M_TASK")
public class ProjectTaskItem implements GpcBaseObject {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -3922965995626701072L;

	/**ID*/
    @Id
    @Column(name = "TEND_M_TASK_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
	
    /**项目*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="TenderId") 
    @BatchSize(size = 15)
    private Project project;
    
    /**任务书条目*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="TASK_PLAN_SUB_ID") 
    @BatchSize(size = 15)
    private ProtaskItem protaskItem;
    
    /**采购主体*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="BUY_MAIN_BODY") 
    @BatchSize(size = 15)
    private OrgInfo buyMainBody;
    
    public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ProtaskItem getProtaskItem() {
		return protaskItem;
	}

	public void setProtaskItem(ProtaskItem protaskItem) {
		this.protaskItem = protaskItem;
	}

	public OrgInfo getBuyMainBody() {
		return buyMainBody;
	}

	public void setBuyMainBody(OrgInfo buyMainBody) {
		this.buyMainBody = buyMainBody;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date arg0) {
	}
}