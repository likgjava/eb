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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.epp.resproject.domain.ResProjectItem;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>ProjectItemTend</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2011-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   zh-epp                    					          
 *  <br/>Module ID:     		
 *  <br/>Create Date：2011-12-21 下午08:04:26 by chenhongjun    					                            
 *  <br/>Modified Date:  2011-12-21 下午08:04:26 by chenhongjun                                   
 *<p>@since 0.5
 *   @version: 0.5 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_TEND_PROJECT_ITEM")
@SuppressWarnings("serial")
public class ProjectItemTend implements  GpcBaseObject, IPropertyCUserTime, IPropertyUUserTime{

	@Id
	@Column(name = "TEND_PROJECT_ITEM_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // ID
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="TENDERID") 
	private Project project; // 招标项目
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="PROJECT_ITEM_ID") 
	private ResProjectItem resProjectItem; // 招标项目
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ResProjectItem getResProjectItem() {
		return resProjectItem;
	}

	public void setResProjectItem(ResProjectItem resProjectItem) {
		this.resProjectItem = resProjectItem;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/* Description :
	 * @param   
	 * @return  
	 * @Exception   
	 *  Create Date: 2011-12-21下午08:05:47 by chenhongjun  
	 *  Modified Date: 2011-12-21下午08:05:47 by chenhongjun 
	 * @see com.gpcsoft.core.model.GpcBaseObject#getCreateTime()
	 *
	 */
	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	/* Description :
	 * @param   
	 * @return  
	 * @Exception   
	 *  Create Date: 2011-12-21下午08:05:47 by chenhongjun  
	 *  Modified Date: 2011-12-21下午08:05:47 by chenhongjun 
	 * @see com.gpcsoft.core.model.GpcBaseObject#getObjId()
	 *
	 */
	public String getObjId() {
		return objId;
	}

	/* Description :
	 * @param   
	 * @return  
	 * @Exception   
	 *  Create Date: 2011-12-21下午08:05:47 by chenhongjun  
	 *  Modified Date: 2011-12-21下午08:05:47 by chenhongjun 
	 * @see com.gpcsoft.core.model.GpcBaseObject#setCreateTime(java.util.Date)
	 *
	 */
	public void setCreateTime(Date arg0) {
		// TODO Auto-generated method stub
		
	}

	/* Description :
	 * @param   
	 * @return  
	 * @Exception   
	 *  Create Date: 2011-12-21下午08:05:47 by chenhongjun  
	 *  Modified Date: 2011-12-21下午08:05:47 by chenhongjun 
	 * @see com.gpcsoft.core.model.IPropertyCUserTime#getCreateUser()
	 *
	 */
	public User getCreateUser() {
		// TODO Auto-generated method stub
		return null;
	}

	/* Description :
	 * @param   
	 * @return  
	 * @Exception   
	 *  Create Date: 2011-12-21下午08:05:47 by chenhongjun  
	 *  Modified Date: 2011-12-21下午08:05:47 by chenhongjun 
	 * @see com.gpcsoft.core.model.IPropertyCUserTime#setCreateUser(com.gpcsoft.srplatform.auth.domain.User)
	 *
	 */
	public void setCreateUser(User arg0) {
		// TODO Auto-generated method stub
		
	}

	/* Description :
	 * @param   
	 * @return  
	 * @Exception   
	 *  Create Date: 2011-12-21下午08:05:47 by chenhongjun  
	 *  Modified Date: 2011-12-21下午08:05:47 by chenhongjun 
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
	 *  Create Date: 2011-12-21下午08:05:47 by chenhongjun  
	 *  Modified Date: 2011-12-21下午08:05:47 by chenhongjun 
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
	 *  Create Date: 2011-12-21下午08:05:47 by chenhongjun  
	 *  Modified Date: 2011-12-21下午08:05:47 by chenhongjun 
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
	 *  Create Date: 2011-12-21下午08:05:47 by chenhongjun  
	 *  Modified Date: 2011-12-21下午08:05:47 by chenhongjun 
	 * @see com.gpcsoft.core.model.IPropertyUTime#setUpdateTime(java.util.Date)
	 *
	 */
	public void setUpdateTime(Date arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
