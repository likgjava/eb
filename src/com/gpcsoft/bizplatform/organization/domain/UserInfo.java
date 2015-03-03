package com.gpcsoft.bizplatform.organization.domain;
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
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.srplatform.auth.domain.Employee;

/**
 * 机构信息
 * @gpcsoft.package packageDir="com.gpcsoft.eps.organization"
 * @gpcsoft.page domain="organization"
 * @hibernate.class table="USER_INFO"
 * @author Administrator
 * @version 1.0
 * @created 08-三月-2010 10:41:18
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "USER_INFO")
public class UserInfo implements GpcBaseObject{

	/** serialVersionUID */
    private static final long serialVersionUID = 4300282297503759581L;
    
    /** 主键 */
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "USR_ID", unique = true, nullable = false, length = 50)
    private String objId;
    
	/** 员工 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="EMP_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private Employee employee;
    
	/** 机构 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="ORG_INFO_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private OrgInfo orgInfo;
    
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public OrgInfo getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCreateTime(Date arg0) {
		// TODO Auto-generated method stub
		
	}
	
}