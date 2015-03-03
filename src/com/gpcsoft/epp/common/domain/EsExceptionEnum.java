package com.gpcsoft.epp.common.domain;


/** 
  *  Comments: <strong>执行平台异常枚举类</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-7-1 上午11:29:49 by Administrator    					                            
  *  <br/>Modified Date:  2010-7-1 上午11:29:49 by Administrator  
  *  
  *  
  * *********************************************************************
  *  在当前异常类里的定义描述,必须按照规范格式输写,定期会对不规范的属性清理 *
  *  清理工作引起的BUG将由对应的人员承担后果                              *
  * *********************************************************************
  * DEMO
  * 异常格式   [组件名]_[功能名称]_[详细描述] = "配置参数"
  */ 
public class EsExceptionEnum {
	//没有选择申报书条目
	public static String TASKPLANSUB_ISSELECT = "es.taskplansub.is_not_select";
	
	//申报书明细的数量不正确
	public static String TASKPLANSUB_NO_QUANTITY = "es.taskplansub.no_quantity";
	
	//小组成员已存在
	public static String WORKGROUP_MEMBER_ISEXISTED = "es.workgroup.member.is_existed";

	//项目不存在
	public static String PROJECT_ISNOTEXISTED = "es.project.is_not_existed";
	// 生成编号(采购申报书、招标项目、合同 获取前缀编号异常)
	public static String GENERATOR_CODE_GET_PREFIX_CODE_ISNULL = "es.generator.code.get_prefix_code";
	// 生成编号(采购申报书、招标项目、合同)
	public static String GENERATOR_CODE_IS_DB_READONLY = "es.generator.code.is_db_readonly";
	// 结束时间要大于报名开始时间
	public static String SINGUP_TIME = "es.bulletin.judge_SingUpTime";
	// 投标开始时间要大于报名结束时间
	public static String TENDER_SINGUP_TIME = "es.bulletin.judge_TenderSingUpTime";
	// 开标开始时间要大于投标开始时间
	public static String TENDER_TIME = "es.bulletin.judge_TenderTime";
    //投标未开始
    public static String BEFORE_TENDER_START ="es.bid.supplierbid.update";
    //投标结束时间已到
    public static String AFTER_TENDER_END ="es.bid.supplierbid.downdate";
    //获取开标列表失败
    public static String GET_OPENBID_LIST_IS_ERROR = "es.projreview.openbidGeneralview.get_open_bid_list";
    // 保存指标失败
    public static String UPDATE_CONGRUOUS_FACTOR_IS_ERROR = "es.planform.eval.congruous_factor.pack_id_is_null";

    public static String EVAL_STIME_IS_NONE ="es.project.evalStartTime_isNone"; //评标开始时间未设置
    
    public static String EVAL_ETIME_IS_NONE ="es.project.evalEndTime_isNone"; //评标结束时间未设置
    
    public static String BEFORE_EVAL_TIME ="es.project.evalStartTime_before"; //未到评标时间
    
    public static String AFTER_EVAL_TIME ="es.project.evalEndTime_after";  //评标时间已过
    // 获取符合性指标信息失败
    public static String GET_FACTOR_INFO_IS_ERROR = "es.planform.bid.bid.pack_ids_is_null";
    //不能在获取专家
    public static String IS_NOT_OBTAINEXPERT = "es.expert.is_not_obtain";
    //要先设置规则
    public static String IS_NOT_SETEXPERTRULE = "es.expert.is_not_set_expertRule";
    
    // 批量审核采购资金失败
    public static String AUDIT_ALL_TASKPLAN_ERROR = "es.taskplan.ids_is_null";
    // 批量审核大宗申报书失败
    public static String AUDIT_ALL_STAPLE_TASKPLAN_ERROR = "es.taskplan.staple_ids_is_null";
    public static String DOC_IS_NONE = "es.doc.is_null"; //文件不存在
    public static String DOCBUYRECORD_IS_NONE = "es.docbuyrecord.is_null"; //文件购买记录不存在
    public static String DOCBUYRECORD_IS_NOTPAY = "es.docbuyrecord.is_not_pay"; //没有支付购买文件
    
    public static String SIGNUP_START_TIME_IS_NONE = "es.project.signupStartDate_isNone";//报名开始时间未设置
    
    public static String SIGNUP__END_TIME_IS_NONE = "es.project.signupEndDate_isNone";//报名结束时间未设置
    
    public static String SUBMIT__START_TIME_IS_NONE = "es.project.submitStartDate_isNone";//投标开始时间未设置
    
    public static String SUBMIT__END_TIME_IS_NONE = "es.project.submitEndDate_isNone";//投标结束时间未设置
    
    public static String SIGNUP_TIME = "es.project.judge_SignupDate";//报名结束时间应该大于报名开始时间
    
    public static String SIGNUP_SUBMIT_TIME = "es.project.judge_Signup_Submit";//投标开始时间应该大于报名结束时间
    
    public static String SUBMIT_TIME = "es.project.judge_SubmitDate";//投标结束时间应该大于投标开始时间
    
    public static String SUBMIT_EVAL_TIME = "es.project.judge_Submit_Eval";//评标开始时间应该大于投标结束时间
    
    public static String EVAL_TIME = "es.project.judge_EvalDate";//评标结束时间应该大于评标开始时间
   
    public static String BEFORE_SIGNUP_TIME = "es.project.signupStartDate_before";//未到报名时间
    
    public static String AFTER_SIGNUP_TIME = "es.project.signupEndDate_after";//报名时间已过
    
    public static String BEFORE_SUBMIT_TIME = "es.project.submitStartDate_before";//未到投标时间
    
    public static String AFTER_SUBMIT_TIME = "es.project.submitEndDate_after";//投标时间已过
    
    public static String IS_NOT_OBTAINEXPERT_FOROUT = "es.expert.is_not_obtain_forout";//不能从外库获取专家
   
    public static String PROJECT_IMPLSTATUS_SUSPENDEDED ="es.project.implstatus_suspended";//项目已处于暂停状态
    
    public static String PROJECT_IMPLSTATUS_STOPPED= "es.project.implstatus_stopped";//项目已处于终止状态
    
    public static String SAVE_IMPORT_FACTOR_ERROR = "es.eval.params_is_error";// 引入系统指标失败
    
    public static String BAILRECORDID_ISNOTEXISTED = "es.bailrecord.bailrecord_isnotexisted";//保证金记录表不存在
    
    public static String BAILRECORDID_BAILSTATUS_UNERCEIVED = "es.bailrecord.bailStatus_unreceived";//保证金未交付
    
    public static String BAILRECORDID_BAILSTATUS_ACCEPTED = "es.bailrecord.bailStatus_accepted";//保证金已交付
    
    public static String BAILRECORDID_BAILSTATUS_REFUNDED = "es.bailrecord.bailStatus_refunded";//保证金已退回
    
    public static String RESET_PROJECT_PLAN_IS_ERROR = "es.project_plan.reset_plan_error";// 重置项目计划失败
    
    public static String CASN_NOT_INSERT = "es.common.casn_not_insert";//检查证书是否已插好
    
    public static String CASN_NOT_MATCH = "es.common.casn_not_match";//证书绑定信息与当前插入密钥的信息不匹配

    public static String TAKE_UNIT = "es.expert.take_unit";//没有生成采购单位信息
    
    public static String TAKE_CATEGORY = "es.expert.take_category";//没有生成品目信息

    public static String IS_NOT_EBUYMETHOD = "es.expert.is_not_ebuyMethods";//没有外库对应采购方式
    
    public static String TRANS_EXPERTRULE_FAIL = "es.expert.trans_expertrule_fail";//传输专家规则失败
    
    public static String SYS_CONFIG_ITEM_CODE_IS_ERROR = "es.sysConfig.item.code.is_null";// 系统配置条目编号已经存在
    
    public static String INSERT_WORK_TASK_ERROR = "es.worktask.insert_task_is_error"; // 新增待办任务失败
    
    public static String INSERT_WORK_TASK_PARAMS_ERROR = "es.worktask.insert_task_params_error";// 参数为Null或不合法
    
    public static String IS_NOT_BULLETINSTRING = "es.expert.is_not_bulletinstring";//没有配置相应信息
    
    public static String IS_NOT_STARTBULLETINSERVICE = "es.expert.is_not_startbulletinservice";//没有启动发布公告的服务
    
    public static String GET_WAITPROC_WORK_TASK_ERROR = "es.waitproc_work_task.get_task_error";// 获取待办任务异常
    
    public static String GET_WAITPROC_WORK_TASK_ROWS = "es.waitproc_work_task.get_task_rows";// 获取到多条待办记录
    
    public static String GET_WORK_TASK_SERVICE = "es.work_task.get_service_null";// 获取待办任务服务接口异常
    
    public static String GET_PROJECT_PLAN_ID_IS_ERROR = "es.project_paln.get_id_is_error";// 获取项目计划业务ID异常
    
    public static String WAITPROC_WORK_TASK_CODE_IS_REQUIRED = "es.waitproc_work_task.code_is_required";// 待办任务编号不是唯一
    
    public static String FINISH_PLAN_IS_ERROR = "es.project_paln.finish_is_error";// 完成项目计划异常
    
    public static String GET_PROJECT_PLAN_IS_ERROR = "es.project_plan.get_project_plan_is_error";//获取项目计划异常
    
    public static String GET_PROJECT_PLAN_PARAMS_IS_ERROR = "es.project_plan.get_project_plan_params_is_error";// 参数为NULL或无此记录
    
    public static String GET_PROJECT_PLAN_PARAMS_IS_NOT_ONLY = "es.project_plan.get_project_plan_params_is_not_only";// 参数为NULL或不是唯一
    
    public static String SIGNUPRECORD_IS_NONE ="es.singuprecord_is_none";  //还未报名
    
    public static String SIGNUPRECORD_IS_NO_PASS = "es.singuprecord_is_no_pass"; //报名未通过
    
    public static String SIGNUPRECORD_WAIT_AUDIT = "es.singuprecord_wait_audit"; //报名审核中

    public static String SIGNUPRECORD_WAIT = "es.singuprecord_wait"; //还有供应商报名待审核
    
    public static String NO_SETUP_PROJECT_MONITOR_PERSON = "es.project_not_monitor_person";// 未设置监管人
    
    public static String SUPPLIER_LESS_PACKAGE = "es.supplier_less_package";//包组所投供应商不足

    public static String SUPPLIER_LESS = "es.supplier_less";//包组所投供应商不足

    public static String SUPPLIER_MORE = "es.supplier_more";//包组所投供应商超过

    public static String SUPPLIER_NO = "es.supplier_no";//包组所投供应商没有

    public static String SUPPLIER_NOTNUM = "es.supplier_notnum";//系统没有配置开标供应商数量
    
    public static String  SUPPLIER_NOTIN_BAILRECORD = "es.supplier_notin_bailrecord";//有供应未录入保证金缴纳情况，请录入！
    
    public static String  PROJECT_NO_DIVIDE_PACK = "es.project_no_divide_pack";//没有分包

    public static String  SCORE_IS_NOT_EQULS = "es.score_is_not_equls";//指标分类的权重值与该分类下的指标分值总和不相等!

    public static String  WEIGHTCOEFFICIENT = "es.weightcoefficient";//指标分类的权重值总和不等于一百!

    public static String  PROJECT_IS_COMPLETE = "es.project_is_complete";//项目完整
    
    public static String  PACKAGE_NOTHAVE_OPNBIDMEMEBER  = "es.package_nothave_openbidmember";//包组未组建开标小组成员
    
    public static String  SUPPLIER_NO_PAYBAILMONEY = "es.supplier_no_paybailmoney";//有供应商保证金未缴纳，无法开标！
    
    public static String  SUPPLIER_IN_SIGNUP = "es.supplier_in_signup";//该供应商已报名

    public static String  CONSIGN_ATT_NULL = "es.consign_att_null";//委托协议附件为空
    
    public static String  CONTRACT_ATT_NULL = "es.contract_att_null";//合同附件为空
    
    public static String  EXPERTRULE_SAVERULEFAILURE_BECAUSEDBYPROJECTIDISNULL = "es.expertRule.saveRuleFailure.beCausedByProjectIdIsNull";//在修改专家抽取规则时,由于项目Id为空,级联更新项目评审时间失败!

    public static String  PROJPLAN_NOT_START = "es.projplan_not_start";//还未启动，不允许操作!请与相关人员联系！

    public static String  PROJPLAN_NOT_END = "es.projplan_not_end";//还未结束，不允许操作!请与相关人员联系！
    
    public static String  OPENBID_NOT_START = "es.openbid_not_start";//还未开标，不能录入！

    public static String  NOT_DELPROJECT = "es.not_del_project";//项目正在进行中，不能删除！

    public static String  IS_NOT_AGENT= "es.is_not_agent";//没有设置代理机构，不能抽取！
    
    public static String  PROJECTID_NOT_EXISTS ="es.projectid_not_exists";//无项目主键
    
    public static String  MEETROOM_NOT_DELETE ="es.meetroom_not_delete";   //评标室不能删除
    
    public static String ES_BULLETIN_AUDITSTATUS_IS_INVALID = "es.bulletin_auditstatus_is_invalid";//无效的公告审核状态
    
    public static String ES_SIGNUPRECORD_AUDITSTATUS_IS_INVALID = "es.signuprecord_auditstatus_is_invalid";//无效的供应商报名审核状态
    
    public static String ES_PROJECT_PLAN_INVALID_STATUS = "es.project_plan.invalid_status";//无效的状态
    
    public static String ES_NOWTIME_AFTER_ENDTIME = "es.nowtime_after_endtime";    //会员有效期已过，无法报名。
    
    public static String EPP_SRVCSBSC_NOPAYRECORD = "epp.serviceSubscribe.nopayrecord";    //没有缴费记录！
    public static String ES_PAYNUM_LESS_PROJNUM = "es.paynum_less_projnum";   //缴费次数小于参与项目次数，无法报名。
    
    public static String ES_SYSTEM_ERROR = "服务器错误！";
    
    public static String ES_ISNOT_MONEY = "es.is_not_money";
    
    public static String PROJECT_IS_SUSPENDED ="es.project_is_suspended";//该项目已暂停
    
    public static String PROJECT_IS_TERMINATED ="es.project_is_terminated";//该项目已终止
    
    public static String ES_ERROR_NULLKEY ="es.error.nullkey";//主键为空
    
    public static String ES_ERROR_NULLOBJECT ="es.error.nullobject";//对象为空
    
    public static String ES_ERROR_EXPECTEDFORTHEONE ="es.error.expectedfortheone";//数据异常,期望1个值
    
    public static String NOT_SAVE_OPENBIDGENERALVITEN ="es.not_save_openBidGeneralViten";

    public static String NOT_HAVE_ORGINFO ="es.not_have_orginfo";
    
} 
