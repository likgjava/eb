package com.gpcsoft.epp.project.domain;

import javax.persistence.Column;
import javax.persistence.Transient;

/** 
  *  Comments: <strong>招标项目视图，记录招标项目的相关业务状态</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-7-30 下午04:00:22 by liuy    					                            
  *  <br/>Modified Date:  2010-7-30 下午04:00:22 by liuy  
  */ 
public class ProjectStatusView {
	private String projId;
	
	private String purBulletinStatus; //招标公告状态
	
	@Column(name = "ProcessStatus")
	private String projProcessStatus;//项目过程状态
	
	@Transient
	private String projProcessStatusCN;//项目过程状态
	
	@Column(name = "implStatus", length = 2)
	private String projImplStatus;//项目实施状态
	
	@Transient
	private String projImplStatusCN;//项目实施状态
	
	@Column(name = "AuditStatus", length = 2)
	private String auditStatus;//项目审核状态

	@Column(name = "UseStatus", length = 2)
	private String useStatus;//使用状态
	
	@Transient
	private String useStatusCN;//使用状态
}
