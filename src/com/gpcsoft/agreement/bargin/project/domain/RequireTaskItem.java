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

import com.gpcsoft.agreement.bargin.require.domain.RequireItem;
import com.gpcsoft.agreement.order.domain.ProtaskItem;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>需求条目与任务书条目中间表</strong>            		
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
 *  @gpcsoft.page domain="project" project="order"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="需求条目与任务书条目关联表"
 */ 

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREEMENT_REQUIRE_TASK")
public class RequireTaskItem implements GpcBaseObject,IPropertyCUserTime {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -3922965995626701072L;

	/**ID*/
    @Id
    @Column(name = "REQUIRE_TASK_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
	
    /**需求条目*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="REQUIRE_ITEM_ID") 
    @BatchSize(size = 15)
    private RequireItem requireItem;
    
    /**任务书条目*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="TASK_ITEM_ID") 
    @BatchSize(size = 15)
    private ProtaskItem protaskItem;
    
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

	public RequireItem getRequireItem() {
		return requireItem;
	}

	public void setRequireItem(RequireItem requireItem) {
		this.requireItem = requireItem;
	}

	public ProtaskItem getProtaskItem() {
		return protaskItem;
	}

	public void setProtaskItem(ProtaskItem protaskItem) {
		this.protaskItem = protaskItem;
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