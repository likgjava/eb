package com.gpcsoft.bizplatform.organization.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.gpcsoft.core.model.GpcBaseObject;

/*
create or replace view org_info_view
(org_id, org_info_id, org_code, org_name, in_terrace, org_status, create_time, org_type)
as
select s.supplier_id, o.org_info_id, o.org_code, o.org_name, o.in_terrace, s.status, s.create_time, 'SUPPLIER' from org_info o, spl_supplier s
 where o.supplier_id=s.supplier_id
union
select b.buyer_id, o.org_info_id,  o.org_code, o.org_name, o.in_terrace, b.status, b.create_time, 'BUYER' from org_info o, buy_buyer b
 where o.buyer_id=b.buyer_id
union
select a.agent_id, o.org_info_id,  o.org_code, o.org_name, o.in_terrace, a.status, a.create_time, 'AGENT' from org_info o, agency_agent a
 where o.agency_id=a.agent_id
union
select v.supervision_id, o.org_info_id,  o.org_code, o.org_name, o.in_terrace, v.status, v.create_time, 'SUPERVISION' from org_info o, spvs_supervision v
 where o.supervision_id=v.supervision_id
union
select c.cmpt_dep_id, o.org_info_id,  o.org_code, o.org_name, o.in_terrace, c.status, c.create_time, 'CMPT_DEP' from org_info o, SPVS_CMPT_DEP c
 where o.cmpt_dep_id=c.cmpt_dep_id;
*/

/**
 * 版权信息： 政采软件版权所有，未经珠海市政采软件技术有限公司的书面同意，不得拷贝，传播本文件及其相关信息的任何内容。
 * 项目：     政府采购超源业务平台
 * JDK版本：  1.5
 * 版本：     3.0
 * 说明：     本文件包含"机构信息视图"的代码。
 * 修订历史：
 * 序号：     1
 * 日期：     2010-4-7
 * 修改人：   liujf    
 * 修改说明：（为什么修改，修改了什么）
 **/
@Entity
@Table(name = "ORG_INFO_VIEW")
public class OrgInfoView implements GpcBaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5412289244992660454L;

	@Id
	@Column(name = "ORG_ID",length = 36)
	private String objId;

	@Column(name = "ORG_INFO_ID",length = 36)
	private String orgInfoId;

	@Column(name = "ORG_CODE", length = 100)
	private String orgCode;

	@Column(name = "ORG_NAME", length = 100)
	private String orgName;

	@Column(name = "ORG_STATUS", length = 50)
	private String status;
    
    @Column(name = "ORG_TYPE", length = 100)
	private String orgType;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME")
	private Date createTime;

    @Column(name = "IS_OFF", length = 50)
	private String isOff; /*禁用启用，1.禁用 2.启用。默认启用*/

    @Column(name = "VER_NO", length = 50)
	private String verNo;
    
    @Column(name = "HISTORY_ID", length = 36)
	private String historyId;

	@Column(name = "IN_TERRACE", length = 100)
	private String inTerrace;
	
    @Transient
	private String statusCN;/*机构状态*/
    
    @Transient
	private String isOffCN;/*禁用启用*/

	@Transient 
	private String orgTypeCN; /*供应商,采购人,代理机构,监管机构,主管部门*/
	
	public String getObjId() {
		return objId;
	}
	
	public String getOrgCode() {
		return orgCode;
	}
	
	public String getOrgName() {
		return orgName;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getOrgType() {
		return orgType;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public String getOrgInfoId() {
		return orgInfoId;
	}

	public void setOrgInfoId(String orgInfoId) {
		this.orgInfoId = orgInfoId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIsOff() {
		return isOff;
	}

	public void setIsOff(String isOff) {
		this.isOff = isOff;
	}

	public String getStatusCN() {
		return statusCN;
	}

	public void setStatusCN(String statusCN) {
		this.statusCN = statusCN;
	}

	public String getHistoryId() {
		return historyId;
	}

	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}

	public String getVerNo() {
		return verNo;
	}

	public void setVerNo(String verNo) {
		this.verNo = verNo;
	}

	public String getIsOffCN() {
		return isOffCN;
	}

	public void setIsOffCN(String isOffCN) {
		this.isOffCN = isOffCN;
	}

	public String getOrgTypeCN() {
		return orgTypeCN;
	}

	public void setOrgTypeCN(String orgTypeCN) {
		this.orgTypeCN = orgTypeCN;
	}

	public String getInTerrace() {
		return inTerrace;
	}

	public void setInTerrace(String inTerrace) {
		this.inTerrace = inTerrace;
	}

}
