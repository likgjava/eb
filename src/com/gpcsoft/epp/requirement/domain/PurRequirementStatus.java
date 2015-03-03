package com.gpcsoft.epp.requirement.domain;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
/**
 * Comments: <strong>Role</strong>角色信息 <br/>
 * 按<code>type</code>可以分为两种：1：机构角色 2：用户角色<br/>
 * <code>isDefault</code>为默认角色，应当有6个， 分别为"supplier"供应商，"buyer"采购人，"agent"代理机构，
 * 机构角色和用户角色各三个 <br/>
 * 继承<code>BaseDHtmlTree</code><br/>
 * <br/>
 * Project: srplatform <br/>
 * Module ID: <br/>
 * 
 * Create Date：2010-4-14 <br/>
 * Modified By：liuke <br/>
 * CopyRright (c)2008-xxxx: 珠海政采软件技术有限公司 <br/>
 * Modified Date: 2010-4-14 修改为注解的方式
 * 
 * @author liuke
 * @since 0.5
 * @version: 0.6
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.requirement"
 * @gpcsoft.page domain="planform/requirement" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="采购需求表状态"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PUR_REQUIREMENT_STATUS")
public class PurRequirementStatus implements GpcBaseObject{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PREQ_ID", length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@Column(name = "STATUS", length = 1)
	private String status; //需求状态1、已创建2、已提交3、退回4、已修改（针对退回的需求再次保存，设置为这个状态）5、已确认
	
	/********************************GET和SET方法**********************************************/
	/**
	 * @gpcsoft.property title="需求状态"
	 */
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getObjId() {
		// TODO Auto-generated method stub
		return this.objId;
	}

	public void setCreateTime(Date arg0) {
		// TODO Auto-generated method stub
		
	}

}
