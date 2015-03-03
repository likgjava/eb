package com.gpcsoft.epp.inqpbulletin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.service.BulletinService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.domain.SignUprecordEnum;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.ListBind;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

/**
  *  @gpcsoft.view value="inqpBulletinTableView"
  *  url="view/es/inquiryprice/inqpbulletin/inqpBulletinTable.jsp"
  *  @gpcsoft.view value="inqpBulletinAuditView"
  *  url="view/es/inquiryprice/inqpbulletin/inqpBulletinAuditView.jsp"
  *  @gpcsoft.view value="blankView"
  *  url="view/es/common/blank.jsp"
  *  @gpcsoft.view value="inqpBulletinView"
  *  url="view/es/inquiryprice/inqpbulletin/inqpBulletinView.jsp"  
  *  @gpcsoft.view value="inqpBulletinFormView"
  *  url="view/es/inquiryprice/inqpbulletin/inqpBulletinForm.jsp"
  *  @gpcsoft.view value="moreForinqpBulletinListView"
  *  url="view/es/inquiryprice/inqpbulletin/moreForinqpBulletinList.jsp"
  *  @gpcsoft.view value="inqpBulletinViewByApply1View"
  *  url="view/es/inquiryprice/inqpbulletin/inqpBulletinViewByApply1.jsp"
  *  @gpcsoft.view value="inqpBulletinViewByApplyView"
  *  url="view/es/inquiryprice/inqpbulletin/inqpBulletinViewByApply.jsp"
  *  @gpcsoft.view value="updateInqpBulletinFormView"
  *  url="view/es/inquiryprice/inqpbulletin/updateInqpBulletinForm.jsp"
  *  
  *  
  *  
 */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Bulletin.class})
@RequestMapping("/InqpbulletinController.do")//页面请求路径,可修改
@SuppressWarnings(value="unchecked")
public class InqpbulletinController extends AnnotationMultiController<Bulletin>{

	@Autowired(required=true) @Qualifier("bulletinServiceImpl")
	private BulletinService bulletinService;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;	
    
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	/**
	 * 
	 * Description : 跳转询价公告显示页面:起草
	 * Create Date:  by yangx
	 * @param   HttpServletRequest request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=toDraftPurBulletin")
	public ModelAndView toDraftPurBulletin(HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpbulletinController||toDraftPurBulletin\n");
		String projectId = request.getParameter("projectId");//获取项目ID
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		Map model = new HashMap();
		model.put("projectId", projectId);
		Bulletin bulletin = bulletinService.getInqpBulletinByProjectId(projectId);//根据项目ID获取公告
		String returnName = "inqpBulletinFormView";//跳转到起草采购公告页面
		if(null != bulletin && null != bulletin.getObjId()){//若有公告，则跳到公告显示页面，若无公告，则跳到新增页面
			model.put("bulletin", bulletin);
			returnName = "updateInqpBulletinFormView";
		}else{
		Project project = projectService.getProjectByObjId(projectId);//根据项目ID获取项目
		String buyer = project.getBuyersName();
		String totalPrice = projectService.getSumTaskPlanDetailProjectId(project.getObjId());//预算
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);
		String companyName = user.getEmp().getCompany().getName();
		Map templateMap = new HashMap();
		templateMap.put("companyName", companyName);
		templateMap.put("project", project);
		templateMap.put("buyer", buyer);
		templateMap.put("today", new Date());
		templateMap.put("totalPrice", totalPrice);
		bulletin = new Bulletin();
		bulletin.setBulletinNo(bulletinService.createBulletinCodeByQO(null, project.getEbuyMethod()));// 起草采购公告时默认初始化值
		bulletin.setBullTitle(project.getProjName());
		bulletin.setTenderStartTime(projectRule.getSubmitStTime());//投标开始时间
		bulletin.setTenderEndTime(projectRule.getSubmitETime());//开标开始时间
		bulletin.setSignUpSTime(projectRule.getSignUpSTime());//报名开始时间
		bulletin.setSignUpETime(projectRule.getSignUpETime());//报名结束时间
		bulletin.setBullContents(freeMarkerService.getFreeMarkerContent("inqpBulletin.ftl", templateMap));//设置采购公告模板      输出绑定的结果 
		model.put("bulletin", bulletin);
		}
		if(orgInfo.getAgencyId()!=null&&bulletin.getObjId()!=null&&(CommonEnum.USER_STATUS_FORMAL).equals(bulletin.getUseStatus())&&!(AuditStatusEnum.AUDIT_NO_PASS).equals(bulletin.getAuditStatus())){
			returnName = "inqpBulletinView";//跳转到查看页面
			}
		return new ModelAndView(returnName, model);
	}

	/**
	 * 
	 * Description :  采购办：根据项目Id跳转招标公告审核页面
	 * Create Date: 2010-8-3下午04:04:04 by liuke  Modified Date: 2010-8-3下午04:04:04 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toPurBulletinRead")
	public ModelAndView toPurBulletinRead(HttpServletRequest request)throws Exception{
		logger.debug("\nes InqpbulletinController||toPurBulletinRead\n");
		String projectId=request.getParameter("projectId"); //获取项目ID
		Map model=new HashMap();
		model.put("projectId", projectId);
		Bulletin bulletin = bulletinService.getInqpBulletinByProjectId(projectId);//根据项目ID获取公告
		model.put("bulletin", bulletin);
		model.put("fromList", "yes");
		String returnName = "inqpBulletinAuditView";
		if(bulletin==null||(bulletin!=null&&bulletin.getAuditStatus()==null)){// 判断是否有询价公告、审核状态是否存在  无询价公告或是审核状态不存在跳转到无数据页面 
			returnName = "blankView";
		} else if(bulletin.getAuditStatus()!=null&&!(AuditStatusEnum.WAIT_AUDIT).equals(bulletin.getAuditStatus())){
			returnName = "inqpBulletinView";// 跳转到查看页面 
		}
		return new ModelAndView(returnName, model);
	}
	
	/**
	 * 
	 * Description :  保存询价公告:起草
	 * Create Date: 2010-8-3下午04:18:27 by liuke  Modified Date: 2010-8-3下午04:18:27 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=saveDraftPurBulletin")
	public ModelAndView saveDraftPurBulletin(HttpServletRequest request, Bulletin bulletin, ListBind list,SessionStatus stauts)throws Exception{
		logger.debug("\nes InqpbulletinController||saveDraftPurBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_TEMP);//设置使用状态为临时
		bulletin.setBullType(BulletinTypeEnum.INQPBULLETIN_BULLETIN);//公告类型:询价公告 
		Project project = projectService.getProjectByObjId(bulletin.getProject().getObjId());
		bulletin.setPurcategoryIds(project.getPurCategoryIds());
		bulletin.setPurcategoryNames(project.getPurCategoryNames());
		if((bulletin.getSignUpETime()).before((bulletin.getSignUpSTime()))){ //判断报名结束时间是否大于报名开始时间 
			throw new EsException(messageSource.getMessage(EsExceptionEnum.SIGNUP_TIME));
		}
		if((bulletin.getTenderStartTime()).before((bulletin.getSignUpETime()))){//判断投标开始时间是否大于报名结束时间 
			throw new EsException(messageSource.getMessage(EsExceptionEnum.SIGNUP_SUBMIT_TIME));
		}
		if((bulletin.getTenderEndTime()).before((bulletin.getTenderStartTime()))){//判断投标结束时间是否大于投标开始时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.SUBMIT_TIME));
		}
		bulletinService.saveInqpBulletinAndProjSchedule(bulletin,ProjProcessStatusEnum.DRAFT_PURCHASE_BULLETIN,list);//保存询价公告 
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		Map model=new HashMap();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * 
	 * Description :  保存询价公告：提交
	 * Create Date: 2010-8-3下午04:18:27 by liuke  Modified Date: 2010-8-3下午04:18:27 by liuke
	 * Modified Date: 2011-3-11 15:38 by zhouzhanghe
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=saveSubmitPurBulletin")
	public ModelAndView saveSubmitPurBulletin(HttpServletRequest request, Bulletin bulletin, ListBind list,SessionStatus stauts)throws Exception{
		logger.debug("\nes InqpbulletinController||saveSubmitPurBulletin\n");
		bulletin.setBullType(BulletinTypeEnum.INQPBULLETIN_BULLETIN);// 公告类型:询价公告 
		if((bulletin.getSignUpETime()).before((bulletin.getSignUpSTime()))){	// 判断报名结束时间是否大于报名开始时间 
			throw new EsException(messageSource.getMessage(EsExceptionEnum.SINGUP_TIME));
		}
		if((bulletin.getTenderStartTime()).before((bulletin.getSignUpETime()))){ // 判断投标开始时间是否大于报名结束时间 
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TENDER_SINGUP_TIME));
		}
		if((bulletin.getTenderEndTime()).before((bulletin.getTenderStartTime()))){ // 判断投标结束时间是否大于投标开始时间 
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TENDER_TIME));
		}
		
		Project project = projectService.getProjectByObjId(bulletin.getProject().getObjId());
		bulletin.setPurcategoryIds(project.getPurCategoryIds());
		bulletin.setPurcategoryNames(project.getPurCategoryNames());
		
		bulletinService.saveSubmitInqpBulletinAndProjSchedule(bulletin,ProjProcessStatusEnum.SUBMIT_PURCHASE_BULLETIN,list);	// 保存提交询价公告 
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		Map model=new HashMap();
		model.put(Constants.JSON_RESULT, "success");
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到公告信息列表：(更多)功能
	 * Create Date: 2010-7-13上午10:18:06 by yangx  Modified Date: 2010-7-13上午10:18:06 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getMoreForBulletinList")
	public ModelAndView getMoreForBulletinList(HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpbulletinController||getMoreForBulletinList\n");
		String auditStatus = request.getParameter("auditStatus");
		String bullType = request.getParameter("bullType");
		Map model = new HashMap();
		model.put("auditStatus", auditStatus);
		model.put("bullType", bullType);
		return new ModelAndView("moreForinqpBulletinListView", model);
	}
	
	/**
	 * 
	 * Description :询价公告审核  
	 * Create Date: 2010-8-3下午05:47:30 by liuke  Modified Date: 2010-8-3下午05:47:30 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=purbulletinAudit")
	public ModelAndView purbulletinAudit(HttpServletRequest request,Bulletin bulletin,SessionStatus stauts)throws Exception{
		logger.debug("\nes InqpbulletinController||purbulletinAudit\n");
		String passStatus=request.getParameter("passStatus");//审批是否通过：通过(01)、不通过(02)
		if(passStatus!=null&&(AuditStatusEnum.AUDIT_PASS).equals(passStatus)){//审批通过 
			bulletin.setAuditStatus(AuditStatusEnum.AUDIT_PASS);
		}else{// 审批不通过 
			bulletin.setAuditStatus(AuditStatusEnum.AUDIT_NO_PASS);
		}
		User user = AuthenticationHelper.getCurrentUser();
		bulletinService.saveInqpBulletinForAudit(bulletin, user);// 保存审批状态 
		stauts.setComplete();// 清除Session
		Map model=new HashMap();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * 
	 * Description :  获取公告列表
	 * Create Date: 2010-8-4上午11:01:40 by liuke  Modified Date: 2010-8-4上午11:01:40 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=getBulletinList")
	public ModelAndView getBulletinList(HttpServletRequest request)throws Exception{
		logger.debug("\nes InqpbulletinController||getBulletinList\n");
		String bullType = request.getParameter("bullType");	/** 公告类型 */
		String auditStatus = request.getParameter("auditStatus");/** 公告状态 */
		String bulletinNo = request.getParameter("bulletinNo");/** 公告编号 */
		String bullTitle = request.getParameter("bullTitle");	/** 公告标题 */
		String bullTypes = request.getParameter("bullTypes");/** 公告类型 */
		User user=AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		Map model=new HashMap();
		Page page = prePage(request);
		QueryObject queryObject = new QueryObjectBase();
		if(bullTypes!=null&&!"".equals(bullTypes)){/** 判断查询条件中公告类型是否存在 */
			queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_IN,bullTypes));
		}else{
			queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_IN,bullType));
		}
		queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,auditStatus));
		queryObject.getQueryParam().and(new QueryParam("bulletinNo",QueryParam.OPERATOR_EQ,bulletinNo));
		queryObject.getQueryParam().and(new QueryParam("bullTitle",QueryParam.OPERATOR_EQ,bullTitle));
		if(orgInfo.getBuyerId()!=null){	/** 采购人 */
			queryObject.getQueryParam().and(new QueryParam("buyerId",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
		}
		else if(orgInfo.getAgencyId()!=null){/** 代理机构 */
			queryObject.getQueryParam().and(new QueryParam("monitorId",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
		}
		Page<Bulletin> pageData = bulletinService.getBulletinListByObject(page, queryObject);
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  根据公告ID跳转到审核询价公告页面（待办点击公告进入）
	 * Create Date: 2010-6-21上午09:56:48 by yangx  Modified Date: 2010-6-21上午09:56:48 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */	
	@RequestMapping(params="method=getBulletinAuditByObjId")
	public ModelAndView getBulletinAuditByObjId(HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpbulletinController||getBulletinAuditByObjId\n");
		String objId = request.getParameter("objId");
		String divTarget = request.getParameter("divTarget");
		String divTargetUrl = request.getParameter("divTargetUrl");
		String fromList = request.getParameter("fromList");//是否从列表跳转过来的标志
		String fromDesk = request.getParameter("fromDesk");//是否从桌面跳转过来的标志
		String returnUrl = "";
		Map model=new HashMap();
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);// 根据公告ID获取公告
		model.put("projectId", bulletin.getProject().getObjId());
		model.put("bulletin", bulletin);
		model.put("fromList", fromList);
		model.put("fromDesk", fromDesk);
		model.put("divTarget", divTarget);
		model.put("divTargetUrl", divTargetUrl);
		if(bulletin!=null&&bulletin.getBullType()!=null&&(BulletinTypeEnum.INQPBULLETIN_BULLETIN).equals(bulletin.getBullType())){
			returnUrl = "inqpBulletinAuditView";//询价公告
		}
		return new ModelAndView(returnUrl, model);
	}

	/** 
	 * Description :  [采购办]获取询价公告(获得待办中列表)
	 * Create Date: 2010-7-2上午11:24:58 by liuke  Modified Date: 2010-7-2上午11:24:58 by liuke
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getPurBulletinBySuperviseList")
	public ModelAndView getPurBulletinBySuperviseList(HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpbulletinController||getPurBulletinBySuperviseList\n");
		String bullType = request.getParameter("bullType");
		String auditStatus = request.getParameter("auditStatus");
		User user=AuthenticationHelper.getCurrentUser();
		Map model=new HashMap();
		Page page = prePage(request);
		QueryObject queryObject = new QueryObjectBase();
		queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_IN,bullType));
		queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,auditStatus));
		queryObject.getQueryParam().and(new QueryParam("monitorId",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
		Page<Bulletin> pageData = bulletinService.getBulletinListByObject(page,queryObject);
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  根据公告ID跳转到内容公告页面
	 * Create Date: 2010-6-21上午09:56:48 by yangx  Modified Date: 2010-6-21上午09:56:48 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */	
	@RequestMapping(params="method=getBulletinByObjId")
	public ModelAndView getBulletinByObjId(HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpbulletinController||getBulletinByObjId\n");
		String objId = request.getParameter("objId");
		String returnUrl = request.getParameter("returnUrl");
		Map model=new HashMap();
		model.put("returnUrl", returnUrl);
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);/** 根据公告ID获取公告 */
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		SignUprecord signUprecord = null;
		String returnName = "inqpBulletinView";
		if(bulletin.getProject()!=null){	/** 根据项目Id和操作人查询报名信息 */
			signUprecord = signUprecordService.getSignUprecordBySupplierId(bulletin.getProject().getObjId(), user);
		}
		model.put("bulletin", bulletin);
		model.put("signUprecord", signUprecord);
		if(signUprecord!=null){
			model.put("signUprecordMsg", SignUprecordEnum.SIGNUP_RECORD_FLAG);	/** 有报名信息标志 */
		}else{
			model.put("signUprecordMsg", SignUprecordEnum.NO_SIGNUP_RECORD_FLAG);	/** 无报名信息标志 */
		}
		if(orgInfo!=null&&orgInfo.getSupplierId()!=null&&!"".equals(orgInfo.getSupplierId())){	/** 判断是否为供应商 */
			ProjectRule projectRule = projectService.getProjectRuleByProjectId(bulletin.getProject().getObjId());
			Date nowDate = new Date();
			if(nowDate.after(projectRule.getSignUpSTime())&&nowDate.before(projectRule.getSignUpETime())){/** 判断能否报名 */
				model.put("isApply",SignUprecordEnum.CAN_SIGNUP_RECORD);/** 可以报名标志 */
			}else{
				model.put("isApply",SignUprecordEnum.CAN_NOT_SIGNUP_RECORD);/** 不可以报名标志 */
			}
			model.put("orgInfoId",orgInfo.getObjId());
			if(bulletin.getBullType()!=null&&(BulletinTypeEnum.INQPBULLETIN_BULLETIN).equals(bulletin.getBullType())){
				returnName = "inqpBulletinViewByApplyView";/** 跳转到报名页面 */
			}
		}
		return new ModelAndView(returnName, model);	/** 跳转到查看页面 */
	}
	
	/**
	 * 
	 * Description : 代理机构：保存修改询价公告
	 * Create Date:  by yangx
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=saveUpdateDraftPurBulletin")
	public ModelAndView saveUpdateDraftPurBulletin(HttpServletRequest request, Bulletin bulletin,ListBind list, SessionStatus stauts)throws Exception{
		logger.debug("\nes InqpbulletinController||saveUpdateDraftPurBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_TEMP);//设置使用状态为临时
		bulletin.setBullType(BulletinTypeEnum.INQPBULLETIN_BULLETIN);//公告类型:询价公告
		if ((bulletin.getSignUpETime()).before((bulletin.getSignUpSTime()))) {//判断报名结束时间是否大于报名开始时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.SINGUP_TIME));
		}
		if ((bulletin.getTenderStartTime()).before((bulletin.getSignUpETime()))) {//判断投标开始时间是否大于报名结束时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TENDER_SINGUP_TIME));
		}
		if ((bulletin.getTenderEndTime()).before((bulletin.getTenderStartTime()))) {//判断投标结束时间是否大于投标开始时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TENDER_TIME));
		}
		bulletinService.saveUpdateInqpBulletinAndProjSchedule(bulletin,ProjProcessStatusEnum.DRAFT_PURCHASE_BULLETIN,list);//保存采购公告与报名指标
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * 
	 * Description : 代理机构：提交修改询价公告
	 * Create Date:  by yangx
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=saveUpdateSubmitPurBulletin")
	public ModelAndView saveUpdateSubmitPurBulletin(HttpServletRequest request, Bulletin bulletin,ListBind list, SessionStatus stauts)throws Exception{
		logger.debug("\nes InqpbulletinController||saveUpdateSubmitPurBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//设置使用状态为正式
		bulletin.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);//设置审核状态为待审核
		bulletin.setBullType(BulletinTypeEnum.INQPBULLETIN_BULLETIN);//公告类型
		if ((bulletin.getSignUpETime()).before((bulletin.getSignUpSTime()))) {//判断报名结束时间是否大于报名开始时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.SINGUP_TIME));
		}
		if ((bulletin.getTenderStartTime()).before((bulletin.getSignUpETime()))) {//判断投标开始时间是否大于报名结束时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TENDER_SINGUP_TIME));
		}
		if ((bulletin.getTenderEndTime()).before((bulletin.getTenderStartTime()))) {//判断投标结束时间是否大于投标开始时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TENDER_TIME));
		}
		bulletinService.saveSubmitInqpBulletinAndProjSchedule(bulletin,ProjProcessStatusEnum.SUBMIT_PURCHASE_BULLETIN,list);//保存审核采购公告
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	/** 
	 * Description :  根据项目ID跳转到公告内容页面
	 * Create Date: 2010-6-21上午09:56:48 by liuke  Modified Date: 2010-6-21上午09:56:48 by liuke
	 * @param   
	 * @return  
	 * @Exception   
	 */	
	@RequestMapping(params="method=toBulletinByProjectId")
	public ModelAndView toBulletinByProjectId(HttpServletRequest request)throws Exception {
		String projectId = request.getParameter("projectId");
		String bullType = request.getParameter("bullType");
		Map model=new HashMap();
		Bulletin bulletin = bulletinService.getBulletinByProjectId(projectId,bullType);/** 根据项目ID获取公告 */
		model.put("bulletin", bulletin);
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		SignUprecord signUprecord = null;
		String returnName = "inqpBulletinView";/** 跳转到查看页面 */
		if(bulletin!=null){
			if(bulletin.getProject()!=null){
				signUprecord = signUprecordService.getSignUprecordBySupplierId(bulletin.getProject().getObjId(), user);
			}
			model.put("signUprecord", signUprecord);
			if(orgInfo!=null&&orgInfo.getSupplierId()!=null&&!"".equals(orgInfo.getSupplierId())){	/** 判断是否为供应商 */
				Date nowDate = new Date();
				if(bulletin.getSignUpSTime()!=null&&bulletin.getSignUpETime()!=null){	/** 判断能否报名 */
				if(nowDate.after(bulletin.getSignUpSTime())&&nowDate.before(bulletin.getSignUpETime())){
					model.put("isApply",SignUprecordEnum.CAN_SIGNUP_RECORD);/** 可以报名 */
				}else{	
					model.put("isApply",SignUprecordEnum.CAN_NOT_SIGNUP_RECORD); /** 不可以报名 */
				}
				}
				model.put("orgInfoId",orgInfo.getObjId());
				returnName = "inqpBulletinViewByApply1View";/** 跳转到查看页面 */
			}
			else if(orgInfo!=null&&orgInfo.getBuyerId()!=null&&!"".equals(orgInfo.getBuyerId())){	/** 判断是否为采购人 */
				if(bulletin.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_PASS).equals(bulletin.getAuditStatus())){
					returnName = "inqpBulletinView";/** 跳转到查看页面 */
				}else{
					returnName = "blankView";
				}
			}	
		}else{
			returnName = "blankView";
		}
		return new ModelAndView(returnName, model);
	}
	
	
	/**
	 * FuncName: saveUpdateSubmitPurBulletin
	 * Description :  创建或修改对象
	 * @param 
	 * @param request
	 * @param bulletin
	 * @param list
	 * @param stauts
	 * @return
	 * @throws Exception ModelAndView
	 * @author: liuke
	 * @Create Date:2011-7-5  下午03:48:29
	 * @Modifier: liuke
	 * @Modified Date:2011-7-5  下午03:48:29
	 */
	@RequestMapping(params="method=toInqpbulletinByProjectId")
	public ModelAndView toInqpbulletinByProjectId(HttpServletRequest request)throws Exception{
		logger.debug("\nes InqpbulletinController||toInqpbulletinByProjectId\n");
		String projectId = request.getParameter("projectId");
		String bullType = request.getParameter("bullType");
		Map model=new HashMap();
		Bulletin bulletin = bulletinService.getBulletinByProjectId(projectId,bullType);/** 根据项目ID获取公告 */
		String returnName = "inqpBulletinView";
		model.put("fromType", "fromDesk");
		if(null!=bulletin){ //如果招标文件不为空
			model.put("bulletin", bulletin);
		}else{
			returnName = "blankView";
		}
		return new ModelAndView(returnName,model);	
	}
	
}
