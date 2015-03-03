package com.gpcsoft.epp.bulletin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.service.BulletinService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

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
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Bulletin.class})
@RequestMapping("/VariationBulletinController.do")//页面请求路径,可修改
@SuppressWarnings(value="unchecked")
public class VariationBulletinController extends AnnotationMultiController<Bulletin>{
	
	@Autowired(required=true) @Qualifier("bulletinServiceImpl")
	private BulletinService bulletinService;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	/** 
	 * FuncName:toViewVariationBulletinForSupplier
	 * Description :  供应商：桌面根据更正公告Id查看更正公告
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-7-30上午10:47:58 
	 * @Modifier yangx
	 * @Modified Date: 2010-7-30上午10:47:58 
	 */
	@RequestMapping(params="method=toViewVariationBulletinForSupplier")
	public ModelAndView toViewVariationBulletinForSupplier(HttpServletRequest request)throws Exception {
		logger.debug("\n VariationBulletinController||toViewVariationBulletinForSupplier\n");
		String objId = request.getParameter("objId");//获取项目ID
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);//根据结果公告ID获取结果公告
		Map model=new HashMap();
		model.put("bulletin", bulletin);
		String returnUrl = "blank";
		if(bulletin!=null&&bulletin.getObjId()!=null){
			returnUrl = "bulletinView";
		}
		return new ModelAndView(returnUrl,model);
	}
	
	/** 
	 * FuncName:toViewVariationBulletinListForSupplier
	 * Description :供应商：桌面根据项目Id跳转到更正公告列表
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-7-30上午10:47:58 
	 * @Modifier   yangx 
	 * @Modified Date: 2010-7-30上午10:47:58 
	 */
	@RequestMapping(params="method=toViewVariationBulletinListForSupplier")
	public ModelAndView toViewVariationBulletinListForSupplier(HttpServletRequest request)throws Exception {
		logger.debug("\n VariationBulletinController||toViewVariationBulletinListForSupplier\n");
		String projectId = request.getParameter("projectId");//获取项目ID
		List<Bulletin> listBulletin = bulletinService.getVariationBulletinListByProjectId(projectId);//根据结果公告ID获取结果公告
		Map model=new HashMap();
		model.put("listBulletin", listBulletin);
		String returnUrl = "blank";
		if(listBulletin!=null&&listBulletin.size()>0){
			returnUrl = "variationBulletinList";
		}
		return new ModelAndView(returnUrl,model);
	}
	
	/** 
	 * FuncName:toViewVariationBulletinForSupplierByObjId
	 * Description :  供应商：桌面根据更正公告Id查看更正公告
	 * @param   request
	 * @return ModelAndView
	 * @author yangx  
	 * @Create Date: 2010-7-30上午10:47:58 
	 * @Modifier yangx
	 * @Modified Date: 2010-7-30上午10:47:58 
	 */
	@RequestMapping(params="method=toViewVariationBulletinForSupplierByObjId")
	public ModelAndView toViewVariationBulletinForSupplierByObjId(HttpServletRequest request)throws Exception {
		logger.debug("\n VariationBulletinController||toViewVariationBulletinForSupplierByObjId\n");
		String objId = request.getParameter("objId");//获取项目ID
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);//根据结果公告ID获取结果公告
		Map model=new HashMap();
		model.put("bulletin", bulletin);
		String returnUrl = "blank";
		if(bulletin!=null&&bulletin.getObjId()!=null){
			returnUrl = "bulletinViewVariation";
		}
		return new ModelAndView(returnUrl,model);
	}
	
	/** 
	 * FuncName:toDraftVariationBulletin
	 * Description :代理机构：根据项目Id跳转到起草更正公告
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-1下午05:46:18    
	 * @Modifier yangx
	 * @Modified Date: 2010-9-1下午05:46:18     
	 */
	@RequestMapping(params="method=toDraftVariationBulletin")
	public ModelAndView toDraftVariationBulletin(HttpServletRequest request)throws Exception {
		logger.debug("\n VariationBulletinController||toDraftVariationBulletin\n");
		String projectId = request.getParameter("projectId");//获取项目ID
		User user = AuthenticationHelper.getCurrentUser();
		Bulletin bulletin = new Bulletin();
		Map model = new HashMap();
		model.put("projectId", projectId);
		Project project = projectService.getProjectByObjId(projectId);//根据项目ID获取项目
		String companyName = user.getEmp().getCompany().getName();//代理机构名称
		String companyAddress = user.getEmp().getCompany().getAddress();//代理机构地址
		String name = user.getEmp().getName();//代理机构联系人名称
		String tel = user.getEmp().getCompany().getTel();//代理机构电话
		String fax = user.getEmp().getCompany().getFax();//代理机构传真
		if (companyAddress==null) {
			companyAddress = "";
		}
		if (tel==null) {
			tel="";
		}
		if (fax==null) {
			fax="";
		}
		Map templateMap = new HashMap();
		templateMap.put("companyName", companyName);
		templateMap.put("companyAddress", companyAddress);
		templateMap.put("name", name);
		templateMap.put("tel", tel);
		templateMap.put("fax", fax);
		templateMap.put("project", project);
		bulletin.setBulletinNo(project.getProjCode());//起草更正公告时默认初始化值
		bulletin.setBullTitle(project.getProjName());
		bulletin.setProject(project);
		bulletin.setBullContents(freeMarkerService.getFreeMarkerContent("variationBulletin.ftl", templateMap));//输出绑定的结果
		model.put("bulletin", bulletin);
		return new ModelAndView("draftVariationBulletin", model);
	}
	
	/** 
	 * FuncName:toModifyVariationBulletin
	 * Description :  修改更正公告
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-2上午10:04:19 
	 * @Modifier  yangx 
	 * @Modified Date: 2010-9-2上午10:04:19  
	 */
	@RequestMapping(params="method=toModifyVariationBulletin")
	public ModelAndView toModifyVariationBulletin(HttpServletRequest request)throws Exception {
		logger.debug("\n VariationBulletinController||toModifyVariationBulletin\n");
		String variationId = request.getParameter("variationId");//获取公告ID
		Bulletin bulletin = bulletinService.getBulletinByObjId(variationId);
		Map model = new HashMap();
		model.put("bulletin", bulletin);
		return new ModelAndView("toModifyVariationBulletin", model);
	}
	
	/** 
	 * FuncName:saveVariationBulletin
	 * Description :代理机构：保存更正公告
	 * @param   request,bulletin:更正公告,stauts
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-1下午06:14:52 
	 * @Modifier   yangx
	 * @Modified Date: 2010-9-1下午06:14:52 
	 */
	@RequestMapping(params="method=saveVariationBulletin")
	public ModelAndView saveVariationBulletin(HttpServletRequest request,Bulletin bulletin,SessionStatus stauts)throws Exception {
		logger.debug("\n VariationBulletinController||saveVariationBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_TEMP);//设置状态为临时
		bulletin.setBullType(BulletinTypeEnum.VARIATION_BULLETIN);//公告类型
		bulletinService.saveVariationBulletin(bulletin,null);// 保存变更公告
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:saveUpdateVariationBulletin
	 * Description :  代理机构：保存修改更正公告
	 * @param   request,bulletin:更正公告,stauts
	 * @return ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-1下午06:14:52 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-1下午06:14:52 
	 */
	@RequestMapping(params="method=saveUpdateVariationBulletin")
	public ModelAndView saveUpdateVariationBulletin(HttpServletRequest request,Bulletin bulletin,SessionStatus stauts)throws Exception {
		logger.debug("\n VariationBulletinController||saveUpdateVariationBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_TEMP);//设置状态为临时
		bulletin.setBullType(BulletinTypeEnum.VARIATION_BULLETIN);//公告类型
		bulletinService.saveUpdateVariationBulletin(bulletin,null);// 保存变更公告
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:submitVariationBulletin
	 * Description:代理机构：提交更正公告
	 * @param   request,bulletin:更正公告,stauts
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-1下午06:14:52 
	 * @Modifier   yangx 
	 * @Modified Date: 2010-9-1下午06:14:52 
	 */
	@RequestMapping(params="method=submitVariationBulletin")
	public ModelAndView submitVariationBulletin(HttpServletRequest request,Bulletin bulletin,SessionStatus stauts)throws Exception {
		logger.debug("\n VariationBulletinController||submitVariationBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//设置状态为正式
		bulletin.setBullType(BulletinTypeEnum.VARIATION_BULLETIN);//公告类型
		bulletin.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);
		bulletinService.saveSubmitVariationBulletin(bulletin,null);// 保存变更公告
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:submitUpdateVariationBulletin
	 * Description :  代理机构：提交修改更正公告
	 * @param   request,bulletin:更正公告,stauts
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-1下午06:14:52 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-1下午06:14:52 
	 */
	@RequestMapping(params="method=submitUpdateVariationBulletin")
	public ModelAndView submitUpdateVariationBulletin(HttpServletRequest request,Bulletin bulletin,SessionStatus stauts)throws Exception {
		logger.debug("\n VariationBulletinController||submitUpdateVariationBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//设置状态为正式
		bulletin.setBullType(BulletinTypeEnum.VARIATION_BULLETIN);//公告类型
		bulletin.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);
		bulletinService.saveSubmitVariationBulletin(bulletin,null);// 保存变更公告
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName: toViewVariationBulletinByObjId
	 * Description : 根据更正公告ID跳转到公告查看页面 
	 * @param   request
	 * @return ModelAndView
	 * @author yangx
	 * @Create Date: 2010-7-7上午11:07:22   
	 * @Modifier yangx
	 * @Modified Date: 2010-7-7上午11:07:22 
	 */
	@RequestMapping(params="method=toViewVariationBulletinByObjId")
	public ModelAndView toViewVariationBulletinByObjId(HttpServletRequest request)throws Exception {
		logger.debug("\n VariationBulletinController||toViewVariationBulletinByObjId\n");
		Map model=new HashMap();
		String objId = request.getParameter("objId");
		if(objId==null||"".equals(objId))return new ModelAndView("blank", model);//如果公告Id不存在跳转到无数据页面
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);//根据公告ID获取公告
		model.put("bulletin",bulletin);
		return new ModelAndView("bulletinView", model);
	}
	
	/** 
	 * FuncName:toAuditVariationBulletinByObjId
	 * Description :采购办：根据更正公告ID跳转审核页面
	 * @param   request
	 * @return  ModelAndView
	 * @author  yangx
	 * @Create Date: 2010-9-7下午06:14:42 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午06:14:42  
	 */
	@RequestMapping(params="method=toAuditVariationBulletinByObjId")
	public ModelAndView toAuditVariationBulletinByObjId(HttpServletRequest request)throws Exception {
		logger.debug("\n VariationBulletinController||toAuditVariationBulletinByObjId\n");
		String objId = request.getParameter("objId");
		Map model=new HashMap();
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);//根据公告ID获取公告
		model.put("bulletin", bulletin);
		return new ModelAndView("auditVariationBulletin", model);//更正公告公告
	}
	
	/** 
	 * FuncName:auditVariationBulletin
	 * Description :采购办：根据状态审核变更公告
	 * @param   ids:变更公告主键,passStatus:审核状态,bulletin:公告对象,stauts
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-7下午06:22:02 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-7下午06:22:02  
	 */
	@RequestMapping(params="method=auditVariationBulletin")
	public ModelAndView auditVariationBulletin(String ids,String passStatus,Bulletin bulletin,SessionStatus stauts)throws Exception {
		logger.debug("\n VariationBulletinController||auditVariationBulletin\n");
		if(ids==null||ids.equals("")){
			ids = bulletin.getObjId();
		}
		bulletinService.saveVariationBulletinForAudit(ids,passStatus);//保存审核结果
		stauts.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
}
