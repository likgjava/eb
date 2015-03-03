package com.gpcsoft.epp.project.domain;

import java.util.Date;

import javax.persistence.Transient;

public class ProjectView {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private String objId; //主键
	
	
	private String agenciesId;//代理机构	
	
	private String agenciesName;//代理机构	
	
	private String projCode;//项目编号
	
	private String projName;//项目名称
	
	private String ebuyMethod;//采购方式
	
	@Transient
	private String ebuyMethodCN;
	
	private String projSummary;//项目摘要
	
	
	private Date signUpSTime;//报名开始时间
	
	private Date signUpETime;//报名结束时间
	
	private Date tenderStartTime;//投标开始时间
	
	private Date tenderEndTime;//投标结束时间
	
	private Date planStartDate;//计划开始时间

	private Date planEndDate;//计划结束时间
	
	private Date startDate;//计划实际开始时间
	
	private Date endDate;//计划实际结束时间
	
	private String parentId;//上级项目ID
	
	private String managerId;//项目负责人
	
	private String managerName;//项目负责人
	
	private String monitorId;//项目监管人
	
	private String monitorName;//项目监管人
	
	private String projProcessStatus;//项目过程状态
	
	@Transient
	private String projProcessStatusCN;//项目过程状态
	
	
	private String projImplStatus;//项目实施状态
	
	@Transient
	private String projImplStatusCN;//项目实施状态
	
	private String auditStatus;//项目审核状态
	
	private String templateId;//计划模版Id

	private String useStatus;//使用状态
	
	@Transient
	private String useStatusCN;//使用状态
}
