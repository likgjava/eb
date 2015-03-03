package com.gpcsoft.epp.bulletin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
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
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.signuprecord.domain.SignUpRespone;
import com.gpcsoft.epp.signuprecord.service.SignUpResponeService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @gpcsoft.view value="toPurBulletinRead"
 *  url="view/es/planform/bulletin/bulletinAuditView.jsp"
 * @gpcsoft.view value="bulletinView"
 *  url="view/es/planform/bulletin/bulletinView.jsp"
 *  @gpcsoft.view value="blank"
 *  url="view/es/common/blank.jsp"
 * @gpcsoft.view value="bulletinFormView"
 *  url="view/es/planform/bulletin/bulletinForm.jsp"
 * @gpcsoft.view value="toSubmitPurBulletin"
 *  url="view/es/planform/bulletin/submitBulletinForm.jsp"
  * @gpcsoft.view value="bulletinTreeFormView"
  *  url="view/es/planform/bulletin/bulletinTreeForm.jsp"
  * @gpcsoft.view value="bulletinListView"
  *  url="view/es/planform/bulletin/bulletinList.jsp"
  * @gpcsoft.view value="bulletinDetailView"
  *  url="view/es/planform/bulletin/bulletinDetail.jsp"
  * @gpcsoft.view value="purBulletin"
  *  url="view/es/planform/bulletin/purBulletin.jsp"
  *  @gpcsoft.view value="resultBulletinView"
  *  url="view/es/planform/bulletin/resultBulletin.jsp"
  *  @gpcsoft.view value="resultBulletin"
  *  url="view/es/planform/bulletin/resultBulletinView.jsp"
  *  @gpcsoft.view value="toBulletinInfoByObjId"
  *  url="view/es/planform/bulletin/bulletinViewForList.jsp"
  *  @gpcsoft.view value="toBulletinAuditInfoByObjId"
  *  url="view/es/planform/bulletin/bulletinAuditForList.jsp"
  *  @gpcsoft.view value="toPurBulletinForm"
  *  url="view/es/planform/bulletin/pruBulletinForm.jsp"
  *  @gpcsoft.view value="toResultPublicityOrBulletinForm"
  *  url="view/es/planform/bulletin/resultPublicityOrBulletinForm.jsp"
  *  @gpcsoft.view value="getMoreForBulletinList"
  *  url="view/es/planform/bulletin/moreForBulletinList.jsp"
  *  @gpcsoft.view value="resultBulletinAuditView"
  *  url="view/es/planform/bulletin/resultBulletinAudit.jsp"
  *  @gpcsoft.view value="resultBulletinAffirmView"
  *  url="view/es/planform/bulletin/resultBulletinAffirm.jsp"
  *  @gpcsoft.view value="resultBulletinReleaseView"
  *  url="view/es/planform/bulletin/resultBulletinRelease.jsp"
  *  @gpcsoft.view value="purBulletinAudit"
  *  url="view/es/planform/bulletin/purBulletinAudit.jsp"
  *  @gpcsoft.view value="purBulletinAffirm"
  *  url="view/es/planform/bulletin/purBulletinAffirm.jsp"
  *  @gpcsoft.view value="purBulletinLeaderAffirm"
  *  url="view/es/planform/bulletin/purBulletinLeaderAffirm.jsp"
  *  @gpcsoft.view value="purBulletinRelease"
  *  url="view/es/planform/bulletin/purBulletinRelease.jsp"
  *  @gpcsoft.view value="correctionsBulletin"
  *  url="view/es/planform/bulletin/correctionsBulletin.jsp"
  *  @gpcsoft.view value="correctionsBulletinAudit"
  *  url="view/es/planform/bulletin/correctionsBulletinAudit.jsp"
  *  @gpcsoft.view value="correctionsBulletinAffirm"
  *  url="view/es/planform/bulletin/correctionsBulletinAffirm.jsp"
  *  @gpcsoft.view value="correctionsBulletinRelease"
  *  url="view/es/planform/bulletin/correctionsBulletinRelease.jsp"
  *  @gpcsoft.view value="suspendBulletin"
  *  url="view/es/planform/bulletin/suspendBulletin.jsp"
  *  @gpcsoft.view value="suspendBulletinAudit"
  *  url="view/es/planform/bulletin/suspendBulletinAudit.jsp"
  *  @gpcsoft.view value="suspendBulletinAffirm"
  *  url="view/es/planform/bulletin/suspendBulletinAffirm.jsp"
  *  @gpcsoft.view value="suspendBulletinRelease"
  *  url="view/es/planform/bulletin/suspendBulletinRelease.jsp"
  *  @gpcsoft.view value="resultPublicity"
  *  url="view/es/planform/bulletin/resultPublicity.jsp"
  *  @gpcsoft.view value="resultPublicityView"
  *  url="view/es/planform/bulletin/resultPublicityView.jsp"
  *  @gpcsoft.view value="toPublicityOrBulletin"
  *  url="view/es/planform/bulletin/bulletinOrPublicityAudit.jsp"
  *  @gpcsoft.view value="getBulletinAuditByObjId"
  *  url="view/es/planform/bulletin/bulletinAudit.jsp"
  *  @gpcsoft.view value="bulletinViewByApply"
  *  url="view/es/planform/bulletin/bulletinViewByApply.jsp"
  *  @gpcsoft.view value="bulletinViewByApply1"
  *  url="view/es/planform/bulletin/bulletinViewByApply1.jsp"
  *  @gpcsoft.view value="toPurOrPublicityBulletin"
  *  url="view/es/planform/bulletin/bulletinTable.jsp"
  *  @gpcsoft.view value="resultPublicityAudit"
  *  url="view/es/planform/bulletin/resultPublicityAudit.jsp"
  *  @gpcsoft.view value="resultPublicityAffirm"
  *  url="view/es/planform/bulletin/resultPublicityAffirm.jsp"
  *  @gpcsoft.view value="resultPublicityRelease"
  *  url=" view/es/planform/bulletin/resultPublicityRelease.jsp"
  *  @gpcsoft.view value="blankPageView"
  *  url=" view/es/common/blank.jsp"
  *  @gpcsoft.view value="draftVariationBulletin"
  *  url="view/es/planform/bulletin/variationBulletinFrom.jsp"
  *  @gpcsoft.view value="auditVariationBulletin"
  *  url="view/es/planform/bulletin/auditVariationBulletinFrom.jsp"
  *  @gpcsoft.view value="toUpdatePurchaseBulletin"
  *  url="view/es/planform/bulletin/updateBulletinForm.jsp"
  *  @gpcsoft.view value="toUpdateResultBulletin"
  *  url="view/es/planform/bulletin/updateResultBulletinView.jsp"
  *  @gpcsoft.view value="toModifyVariationBulletin"
  *  url="view/es/planform/bulletin/updateVariationBulletinFrom.jsp"
  *  @gpcsoft.view value="variationBulletinList"
  *  url="view/es/planform/bulletin/variationBulletinListForSupplier.jsp"
  *  @gpcsoft.view value="bulletinViewVariation"
  *  url="view/es/planform/bulletin/bulletinViewVariation.jsp"
  *  @gpcsoft.view value="toResultPublicity"
  *  url="view/es/planform/bulletin/resultPublicityForAudit.jsp"
  *  @gpcsoft.view value="toUpdateResultPublicity"
  *  url="view/es/planform/bulletin/updateResultPublicityForm.jsp"
  *  @gpcsoft.view value="toResultBulletinTab"
  *  url="view/es/planform/bulletin/resultBulletinTab.jsp"
  *  @gpcsoft.view value="toResultBulletinTabForSupervise"
  *  url="view/es/planform/bulletin/resultBulletinTabForSupervise.jsp"
  *  @gpcsoft.view value="toViewResultBulletinForBuyer"
  *  url="view/es/planform/bulletin/resultBulletinTabForBuyer.jsp"
  *  @gpcsoft.view value="toViewResultBulletinForSupplierTab"
  *  url="view/es/planform/bulletin/resultBulletinTabForSupplierTab.jsp"
  *  @gpcsoft.view value="resultPublicityTabView"
  *  url="view/es/planform/bulletin/resultPublicityTab.jsp"
  *  @gpcsoft.view value="resultPublicityTabForSuperviseView"
  *  url="view/es/planform/bulletin/resultPublicityTabForSupervise.jsp"
  *  @gpcsoft.view value="resultPublicityTabForSupplierTabView"
  *  url="view/es/planform/bulletin/resultPublicityTabForSupplierTab.jsp"
  *  @gpcsoft.view value="viewBulletinList"
  *  url="view/es/planform/bulletin/viewBulletinList.jsp"
  *  @gpcsoft.view value="getRelBulletinList"
  *  url="view/es/planform/bulletin/relBulletinListForSupervise.jsp"
  *  @gpcsoft.view value="viewBulletinForRel"
  *  url="view/es/planform/bulletin/bulletinViewForRel.jsp"
  *  
  */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Bulletin.class})
@RequestMapping("/BulletinController.do")//页面请求路径,可修改
@SuppressWarnings(value="unchecked")
public class BulletinController extends AnnotationMultiController<Bulletin> {

	@Autowired(required=true) @Qualifier("bulletinServiceImpl")
	private BulletinService bulletinService;
	
	@Autowired(required=true) @Qualifier("signUpResponeServiceImpl")
	private SignUpResponeService signUpResponeService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	/** 
	 * FuncName:getBulletinAuditByObjId
	 * Description:根据公告ID跳转到审核公告页面
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-6-21上午09:56:48 
	 * @Modifier    yangx
	 * @Modified Date: 2010-6-21上午09:56:48 
	 */	
	@RequestMapping(params="method=getBulletinAuditByObjId")
	public ModelAndView getBulletinAuditByObjId(HttpServletRequest request)throws Exception {
		logger.debug("\nes=BulletinController||getBulletinAuditByObjId\n");
		String objId = request.getParameter("objId");
		String divTarget = request.getParameter("divTarget");
		String divTargetUrl = request.getParameter("divTargetUrl");
		String fromList = request.getParameter("fromList");//是否从列表跳转过来的标志
		String fromDesk = request.getParameter("fromDesk");//是否从桌面跳转过来的标志
		String returnUrl="";
		String supplier ="";
		Map model=new HashMap();
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);//根据公告ID获取公告 
		List<SignUpRespone> list = signUpResponeService.getSignUpResponeListByProjectIdAndSupplierId(bulletin.getProject().getObjId(),supplier);
		if(list.size()==0){
			model.put("signUpResponeNum", "none");
		}
		model.put("bulletin", bulletin);
		model.put("fromList", fromList);
		model.put("fromDesk", fromDesk);
		model.put("divTarget", divTarget);
		model.put("divTargetUrl", divTargetUrl);
		if(bulletin!=null&&bulletin.getBullType()!=null&&(BulletinTypeEnum.RESULT_BULLETIN).equals(bulletin.getBullType())){
			returnUrl = "toPublicityOrBulletin";//中标公告
		}else if(bulletin!=null&&bulletin.getBullType()!=null&&(BulletinTypeEnum.RESULT_PREVIEW).equals(bulletin.getBullType())){
			returnUrl = "toResultPublicity";//中标公示
		}else if(bulletin!=null&&bulletin.getBullType()!=null&&(BulletinTypeEnum.PURCHASE_BULLETIN).equals(bulletin.getBullType())){
			returnUrl = "getBulletinAuditByObjId";//招标公告
		}else if (bulletin!=null&&bulletin.getBullType()!=null&&(BulletinTypeEnum.VARIATION_BULLETIN).equals(bulletin.getBullType())) {
			returnUrl = "auditVariationBulletin";//更正公告
		}
		return new ModelAndView(returnUrl, model);
	}

	/** 
	 * FuncName:getBulletinListByBullType
	 * Description:根据公告类型、状态获取公告page对象
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-6-22上午09:39:11 
	 * @Modifier  yangx
	 * @Modified Date: 2010-6-22上午09:39:11 
	 */
	@RequestMapping(params="method=getBulletinListByBullType")
	public ModelAndView getBulletinListByBullType(HttpServletRequest request)throws Exception {
		logger.debug("\nes=BulletinController||getBulletinListByBullType\n");
		String bullType = request.getParameter("bullType");
		String auditStatus = request.getParameter("auditStatus");
		Map model=new HashMap();
		Page page = prePage(request);
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		QueryObject queryObject = new QueryObjectBase();
		queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,bullType));
		queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,auditStatus));
		if(orgInfo.getAgencyId()!=null){
			queryObject.getQueryParam().and(new QueryParam("managerID",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));//代理机构 
		}
		Page<Bulletin> pageData = bulletinService.getBulletinListByObject(page,queryObject);
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}

	/** 
	 * FuncName:getBulletinByBullType
	 * Description :  根据公告类型、公告ID跳转到不同的公告修改页面
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-6-22上午11:01:07 
	 * @Modifier  yangx
	 * @Modified Date: 2010-6-22上午11:01:07 
	 */
	@RequestMapping(params="method=getBulletinByBullType")
	public ModelAndView getBulletinByBullType(HttpServletRequest request)throws Exception {
		logger.debug("\nes=BulletinController||getBulletinByBullType\n");
		String bullType = request.getParameter("bullType");
		String objId = request.getParameter("objId");
		String fromList = request.getParameter("fromList");//是否从列表跳转过来的标志
		String fromDesk = request.getParameter("fromDesk");//是否从桌面跳转过来的标志
		String returnUlr = "";
		Map model=new HashMap();
		model.put("fromList",fromList);
		model.put("fromDesk",fromDesk);
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);//根据公告ID获取公告
		ProjectRule projectRule = null;
		if (bulletin.getProject()!=null) {
			projectRule = projectService.getProjectRuleByProjectId(bulletin.getProject().getObjId());
		}
		model.put("bulletin",bulletin);
		model.put("project",bulletin.getProject());
		model.put("projectRule",projectRule);
		if(bullType!=null&&(BulletinTypeEnum.PURCHASE_BULLETIN).equals(bullType)){
			returnUlr = "bulletinFormView";//跳转到起草采购公告页面
		}else if(bullType!=null&&(BulletinTypeEnum.VARIATION_BULLETIN).equals(bullType)){
			returnUlr = "blank";//暂时无
		}else if(bullType!=null&&(BulletinTypeEnum.RESULT_BULLETIN).equals(bullType)){
			returnUlr = "resultBulletin";//跳转到起草结果公告页面
		}else if(bullType!=null&&(BulletinTypeEnum.RESULT_PREVIEW).equals(bullType)){
			returnUlr = "resultPublicityView";//跳转到起草结果公示页面
		}else{
			returnUlr = "blank";//暂时无
		}
		return new ModelAndView(returnUlr,model);
	}
	
	/** 
	 * FuncName:getPurBulletinBySuperviseList
	 * Description :  [采购办]获取公告
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-7-2上午11:24:58 
	 * @Modifier   yangx
	 * @Modified Date: 2010-7-2上午11:24:58 
	 */
	@RequestMapping(params="method=getPurBulletinBySuperviseList")
	public ModelAndView getPurBulletinBySuperviseList(HttpServletRequest request)throws Exception {
		logger.debug("\nes=BulletinController||getPurBulletinBySuperviseList\n");
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
	 * FuncName:toBulletinInfoByObjId
	 * Description : 根据公告ID跳转到公告详细信息查看页面 
	 * @param   request
	 * @return ModelAndView
	 * @author yangx
	 * @Create Date: 2010-7-7上午11:07:22 
	 * @Modifier yangx
	 * @Modified Date: 2010-7-7上午11:07:22   
	 */
	@RequestMapping(params="method=toBulletinInfoByObjId")
	public ModelAndView toBulletinInfoByObjId(HttpServletRequest request)throws Exception {
		logger.debug("\nes=BulletinController||toBulletinInfoByObjId\n");
		String fromS = request.getParameter("fromS");
		String backUrl = request.getParameter("backUrl");
		String returnUrl ="toBulletinInfoByObjId";
		Map model=new HashMap();
		String objId = request.getParameter("objId");
		if(objId==null||"".equals(objId)){
			returnUrl = "blank"; 	//如果公告Id不存在跳转到无数据页面
		}
		if (fromS!=null&&!"".equals(fromS)) {
			returnUrl = "viewBulletinForRel";
		}
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);//根据公告ID获取公告
		model.put("bulletin",bulletin);
		model.put("backUrl",backUrl);
		return new ModelAndView(returnUrl, model);
	}
	
	/**
	 * FuncName: toBulletinAuditInfoByObjId
	 * Description:根据公告ID跳转到公告审核页面 
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-7-7上午11:27:24 
	 * @Modifier yangx
	 * @Modified Date: 2010-7-7上午11:27:24  
	 */
	@RequestMapping(params="method=toBulletinAuditInfoByObjId")
	public ModelAndView toBulletinAuditInfoByObjId(HttpServletRequest request)throws Exception {
		logger.debug("\nes BulletinController||toBulletinAuditInfoByObjId\n");
		String returnUrl ="toBulletinAuditInfoByObjId";
		Map model=new HashMap();
		String objId = request.getParameter("objId");
		if(objId==null||"".equals(objId)){
			returnUrl = "blank"; 	//如果公告Id不存在跳转到无数据页面
		}
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);//根据公告ID获取公告
		model.put("bulletin",bulletin);
		return new ModelAndView(returnUrl,model);
	}
	
	/** 
	 * FuncName:getBulletinList
	 * Description :  获取公告列表
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-7-8上午09:54:07 
	 * @Modifier yangx
	 * @Modified Date: 2010-7-8上午09:54:07 
	 */
	@RequestMapping(params="method=getBulletinList")
	public ModelAndView getBulletinList(HttpServletRequest request)throws Exception {
		logger.debug("\nes=BulletinController||getBulletinList\n");
		String bullType = request.getParameter("bullType");//公告类型 
		String auditStatus = request.getParameter("auditStatus");//公告状态
		String bulletinNo = request.getParameter("bulletinNo");//公告编号
		String bullTitle = request.getParameter("bullTitle");//公告标题
		String bullTypes = request.getParameter("bullTypes"); //公告类型
		String useStatus = request.getParameter("useStatus"); //是否正式
		String purcategorycode = request.getParameter("purCategoryCode");//采购品目编号
		String newPurcategorycode = "";
		if(purcategorycode!=null){
			newPurcategorycode = getPurcategorycode(purcategorycode);
		}
		User user=AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = null;
		if(user!=null){
			 orgInfo = (OrgInfo)user.getOrgInfo();
		}
		Map model=new HashMap();
		Page page = prePage(request);
		QueryObject queryObject = new QueryObjectBase();
		if(bullTypes!=null&&!"".equals(bullTypes)){//判断查询条件中公告类型是否存在
			queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_IN,bullTypes));
		}else{
			queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_IN,bullType));
		}
		queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,useStatus));
		queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,auditStatus));
		queryObject.getQueryParam().and(new QueryParam("bulletinNo",QueryParam.OPERATOR_EQ,bulletinNo));
		queryObject.getQueryParam().and(new QueryParam("bullTitle",QueryParam.OPERATOR_EQ,bullTitle));
		
		
		queryObject.getQueryParam().and(new QueryParam("purcategorycode",QueryParam.OPERATOR_EQ,newPurcategorycode));
		
		 if(orgInfo!=null&&orgInfo.getBuyerId()!=null){//采购人
			queryObject.getQueryParam().and(new QueryParam("buyerId",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
		}else if(orgInfo!=null&&orgInfo.getAgencyId()!=null){//代理机构
			queryObject.getQueryParam().and(new QueryParam("monitorId",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
		}
		Page<Bulletin> pageData = bulletinService.getBulletinListByObject(page, queryObject);
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * FuncName:getMoreForBulletinList
	 * Description:跳转到公告信息列表：《更多》功能
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-7-13上午10:18:06   
	 * @Modifier yangx
	 * @Modified Date: 2010-7-13上午10:18:06 
	 */
	@RequestMapping(params="method=getMoreForBulletinList")
	public ModelAndView getMoreForBulletinList(HttpServletRequest request)throws Exception {
		logger.debug("\nes=BulletinController||getMoreForBulletinList\n");
		String auditStatus = request.getParameter("auditStatus");
		String bullType = request.getParameter("bullType");
		Map model = new HashMap();
		model.put("auditStatus", auditStatus);
		model.put("bullType", bullType);
		return new ModelAndView("getMoreForBulletinList", model);
	}
	
	/** 
	 * FuncName:toBulletinPrintPageByType
	 * Description :  代理机构：根据公告类型、项目Id跳转到公告打印预览页面页面
	 * @param   request,bulletin:公告
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-8-10下午05:41:48 
	 * @Modifier   yangx
	 * @Modified Date: 2010-8-10下午05:41:48 
	 */
	@RequestMapping(params="method=toBulletinPrintPageByType")
	public ModelAndView toBulletinPrintPageByType(HttpServletRequest request,Bulletin bulletin)throws Exception {
		logger.debug("\nes=BulletinController||toBulletinPrintPageByType\n");
		request.getSession().setAttribute("content", bulletin.getBullContents());
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:toViewBulletinListPage
	 * Description :跳转到公告管理页面
	 * @param request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-12-9上午10:48:02 
	 * @Modifier liuke
	 * @Modified Date: 2010-12-9上午10:48:02 
	 */
	@RequestMapping(params="method=toViewBulletinListPage")
	public ModelAndView toViewBulletinListPage(HttpServletRequest request)throws Exception {
		logger.debug("\nes=BulletinController||toViewBulletinListPage\n");
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orginfo = (OrgInfo) user.getOrgInfo();
		String userType="";
		if(orginfo.getBuyerId()!=null&&!"".equals(orginfo.getBuyerId())){ //当前用户是采购人
			userType = "buyer";
		}else if(orginfo.getAgencyId()!=null&&!"".equals(orginfo.getAgencyId())){  //当前用户是代理机构
			userType = "agency";
		}else if(orginfo.getSupervisionId()!=null&&!"".equals(orginfo.getSupervisionId())){//当前用户是采购办
			userType = "supervision";
		}else if(orginfo.getSupplierId()!=null&&!"".equals(orginfo.getSupplierId())){  //当前用户是供应商
			userType = "supplier";
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userType", userType);
		return new ModelAndView("viewBulletinList",model);
	}
	/** 
	 * Funcname:getBulletinListForRel
	 * Description: 获取待发送的公告列表
	 * @Create Date: 2011-1-15下午12:08:43 by   
	 * @author yangx
	 * @Modified Date: 2011-1-15下午12:08:43 
	 * @author yangx
	 * @param   request
	 * @return  request
	 * @Exception   
	 */
	@RequestMapping(params="method=getBulletinListForRel")
	public ModelAndView getBulletinListForRel(HttpServletRequest request)throws Exception {
		logger.debug("\nes=BulletinController||getBulletinListForRel\n");
		String bullType = request.getParameter("bullType");//公告类型 
		String auditStatus = request.getParameter("auditStatus");//公告状态
		String bulletinNo = request.getParameter("bulletinNo");//公告编号
		String bullTitle = request.getParameter("bullTitle");//公告标题
		String bullTypes = request.getParameter("bullTypes"); //公告类型
		String useStatus = request.getParameter("useStatus"); //是否正式
		String status = request.getParameter("status"); //是否发送成功
		User user=AuthenticationHelper.getCurrentUser();
		Map model=new HashMap();
		Page page = prePage(request);
		QueryObject queryObject = new QueryObjectBase();
		if(bullTypes!=null&&!"".equals(bullTypes)){//判断查询条件中公告类型是否存在
			queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_IN,bullTypes));
		}else{
			queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_IN,bullType));
		}
		queryObject.getQueryParam().and(new QueryParam("status",QueryParam.OPERATOR_EQ,status));
		queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,useStatus));
		queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,auditStatus));
		queryObject.getQueryParam().and(new QueryParam("bulletinNo",QueryParam.OPERATOR_EQ,bulletinNo));
		queryObject.getQueryParam().and(new QueryParam("bullTitle",QueryParam.OPERATOR_EQ,bullTitle));
		queryObject.getQueryParam().and(new QueryParam("monitorId",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
		Page<Bulletin> pageData = bulletinService.getBulletinListForRel(page, queryObject);
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}

	/** 
	 * Funcname:relBulletinForSupervise
	 * Description :  手动发送公告
	 * @Create Date: 2011-1-15下午12:25:43 
	 * @author  yangx  
	 * Modified Date: 2011-1-15下午12:25:43 
	 * @author  yangx
	 * @param   request
	 * @return  ModelAndView
	 * @Exception   
	 */
	@RequestMapping(params="method=relBulletinForSupervise")
	public ModelAndView relBulletinForSupervise(HttpServletRequest request)throws Exception {
		String bulletinIds = request.getParameter("bulletinIds");
		bulletinService.saveRelBulletin(bulletinIds);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Funcname:getBulletinByObjId
	 * Description:根据公告Id获取公告内容
	 * @Create Date: 2011-2-16下午01:50:58 
	 * @author  yangx  
	 * Modified Date: 2011-2-16下午01:50:58 
	 * @author yangx
	 * @param   request
	 * @return  ModelAndView
	 * @Exception   
	 */
	public ModelAndView getBulletinByObjId(HttpServletRequest request)throws Exception {
		String bulletinId = request.getParameter("objId");
		Bulletin bulletin = bulletinService.getBulletinByObjId(bulletinId);
		Map model=new HashMap();
		model.put("bulletin", bulletin);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	
	
	/**
	 * FuncName: getPurcategorycode
	 * Description :  处理品目查询条件
	 * @param 
	 * @param purcategorycode
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-10-12  下午03:49:11
	 * @Modifier: liuke
	 * @Modified Date:2011-10-12  下午03:49:11
	 */
	private String getPurcategorycode(String purcategorycode){
		
		String[] purcategoryArray = purcategorycode.split(",");
		
		List<String> oneLevel = new ArrayList<String>();
		Map<String,String> purcategoryMap = new HashMap<String, String>();
		for (int i=0;i <purcategoryArray.length; i++) {
			String purcategory = (String)purcategoryArray[i] ;
			if(purcategory.length()==1){ //如果一级品目存在，说明二级品目全部选中
				oneLevel.add(purcategory);
			}
			purcategoryMap.put(purcategory, "true");
		} 
		
		String purcategorycodeInfo = "";
		 if(oneLevel.isEmpty()){
			 for (int i = 0; i < purcategoryArray.length; i++) {
					String purcategory = purcategoryArray[i];
						purcategorycodeInfo += purcategory+",";
					}
		 }else{
			 for (Iterator iterator = oneLevel.iterator(); iterator.hasNext();) {
					String level = (String) iterator.next();
					purcategorycodeInfo += level + ",";	
					for (int i = 0; i < purcategoryArray.length; i++) {
						 String purcategory = purcategoryArray[i];
						 if(purcategory.indexOf(level)!=-1){
							 purcategoryMap.put(purcategory, "false");
						 }
					}
				}
			 for (Iterator iterator2 = purcategoryMap.keySet().iterator(); iterator2.hasNext();) {
					String key = (String) iterator2.next();
					if("true".equals(purcategoryMap.get(key))){
						purcategorycodeInfo += key + ",";
					}
				}
		 }
		if(purcategorycodeInfo.length()>0){
			purcategorycodeInfo = purcategorycodeInfo.substring(0, purcategorycodeInfo.length()-1);
		}
		return purcategorycodeInfo;
	}
	
}
