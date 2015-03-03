package com.gpcsoft.agreement.bargin.bulletin.controller;

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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.bargin.bulletin.service.BulletinAgreementService;
import com.gpcsoft.agreement.bargin.buyresult.service.BuyResultServiceXygh;
import com.gpcsoft.bizplatform.base.common.util.FileOperateUtil;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.cms.util.FileUtils;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.service.BulletinService;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.epp.buyresult.domain.ResultTypeEnum;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

/**
 * @gpcsoft.view value="bulletinNoticeForm"
 *  url="view/agreement/bargin/bulletin/publish_bulletin_notice_form.jsp"
 * @gpcsoft.view value="publishBulletinForm"
 *  url="view/agreement/bargin/bulletin/publish_bulletin_form.jsp"
 * @gpcsoft.view value="previewBulletinView"
 *  url="view/agreement/bargin/bulletin/bulletin_view.jsp"
 * @gpcsoft.view value="bulletinDetailView"
 *  url="view/pubservice/bizdata/bulletin/bulletin_detail.jsp"
 *  
 * @gpcsoft.view value="toPreBulletinListView"
 *  url="view/agreement/bargin/bulletin/publish_bulletin_notice_manager.jsp"
 *  
 * @gpcsoft.view value="toPreBulletinDetailView"
 *  url="view/agreement/bargin/bulletin/publish_bulletin_notice_detail.jsp"
 *  
 * @gpcsoft.view value="toPreBulletinAuditView"
 *  url="view/agreement/bargin/bulletin/publish_bulletin_notice_audit_form.jsp"
 *  
 * @gpcsoft.view value="bulletinManagerForm"
 *  url="view/agreement/bargin/bulletin/publish_bulletin_manager_form.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Bulletin.class})
@RequestMapping("/BulletinAgreementController.do")//页面请求路径,可修改
public class BulletinAgreementController extends AnnotationMultiController<Bulletin> {
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	@Autowired(required=true) @Qualifier("bulletinAgreementServiceImpl")
	private BulletinAgreementService bulletinAgreementService;
	@Autowired(required=true) @Qualifier("bulletinServiceImpl")
	private BulletinService bulletinService;
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	@Autowired(required=true) @Qualifier("buyResultServiceXyghImpl")
	private BuyResultServiceXygh buyResultServiceXygh;
	
	/** 
	 * Description : 跳转到公告页面 
	 * Create Date: 2010-10-8下午04:55:02 by guoyr  Modified Date: 2010-10-8下午04:55:02 by guoyr
	 * @param   
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toBulletinForm")
	public ModelAndView toBulletinForm(HttpServletRequest request,String objId, String projectId) throws Exception{
			Map<String, Object> model = new HashMap<String, Object>();
			Bulletin bulletin = null;
			String contentsString = "";
			String viewName = "bulletinNoticeForm";
			String ftlName = messageSource.getMessage(CustomerMessage.BULLETIN_NOTICE_TEMPLATE);// 预告模板名称
			Project project = null;
			Map<String, Object> templateMap = new HashMap<String, Object>();
			// 如果是根据项目发布公告，读取公告的模板
			if(StringUtils.hasLength(projectId)){
				project = projectService.get(projectId);
				
				// 默认加载采购公告的模板
				ftlName = messageSource.getMessage(CustomerMessage.BULLETIN_TEMPLATE);
				
				// 默认为采购公告
				String bulletinType = BulletinTypeEnum.BARGIN_BULLETIN;
				
				// 如果指定了公告的模板
				if(StringUtils.hasLength(request.getParameter("bulletinTemplate"))){
					ftlName = request.getParameter("bulletinTemplate");
					
				}
				
				// 如果指定了公告的类型
				if(StringUtils.hasLength(request.getParameter("bulletinType"))){
					bulletinType = request.getParameter("bulletinType");
				}
				viewName = "publishBulletinForm";
				model.put("project", project);
				bulletin = bulletinAgreementService.getProjectBulletin(project.getObjId(),bulletinType);
				
				// 如果该项目存在公告，则按修改操作
				if(null != bulletin){
					objId = bulletin.getObjId();
				}else{
					templateMap = bulletinAgreementService.getBulletinTemplateMap(project);
					
					//取得查看成交结果数据
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("projectId", projectId);
					Map<String, Object> buyResultData =  buyResultServiceXygh.getBuyResultData(param) ;
					templateMap.put("buyResult", buyResultData.get("buyResult"));
					List<BuyWinner> buyWinnerList = new ArrayList<BuyWinner>();
					for(BuyWinner buyWinner:(List<BuyWinner>)buyResultData.get("buyWinnerList")){
						if(ResultTypeEnum.DEAL_YES.equals(buyWinner.getResultType())){
							buyWinnerList.add(buyWinner);
						}
					}
					templateMap.put("buyWinnerList", buyWinnerList);
				}
			}
			
			// 如果是修改，则直接读取公告的内容 
			if(StringUtils.hasLength(objId)){
				if(null == bulletin){// 如果项目中存在公告，则不需要再查询
					bulletin = bulletinService.get(objId);
				}
				Attachment attachment = bulletin.getContent();			
				String attachPath = attachment.getPath()==null?"":attachment.getPath();
				contentsString = FileOperateUtil.readFileByLines(getDefaultPath()+attachPath+attachment.getFileName());				
			}else {// 如果是起草，读取模板的内容
				bulletin = new Bulletin();
				contentsString = freeMarkerService.getFreeMarkerContent(ftlName, templateMap);
			}
			bulletin.setBullContents(contentsString);
			bulletin.setProject(project);
			bulletin.setBullType(request.getParameter("bulletinType"));
			model.put("bulletin", bulletin);
			
			
			if(StringUtils.hasLength(request.getParameter("viewName"))){//可能跳转到管理员修改新增
				viewName = request.getParameter("viewName");
			}
			
			return new ModelAndView(viewName, model);
	}
	/** 
	 * Description : 发布公告 
	 * Create Date: 2010-10-11上午11:51:06 by guoyr  Modified Date: 2010-10-11上午11:51:06 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPublistBulletinFormView")
	public ModelAndView toPublistBulletinFormView(HttpServletRequest request,String objId, String projectId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Bulletin bulletin = null;
		String contentsString = "";
		String viewName = "publishBulletinForm";
		if(StringUtils.hasLength(projectId)){
			Project project = projectService.get(projectId);
			model.put("project", project);
			QueryObject<Bulletin> queryObject = new QueryObjectBase<Bulletin>();
			queryObject.setEntityClass(Bulletin.class);
			queryObject.getQueryParam().and(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,projectId));
			bulletin = bulletinService.findByQuery(queryObject).get(0);
			if(null != bulletin){
				Attachment attachment = bulletin.getContent();
				String attachPath = attachment.getPath()==null?"":attachment.getPath();
				contentsString = FileUtils.readFileByLines(getDefaultPath()+attachPath+attachment.getFileName());
			}
			// 预览公告内容
			if(project.getProjProcessStatus().equals(ProjProcessStatusEnum.SUBMIT_PURCHASE_BULLETIN)){
				viewName = "previewBulletinView";
			}
		}
		bulletin.setBullContents(contentsString);
		model.put("bulletin", bulletin);
		model.put("contentsString", contentsString);
		return new ModelAndView(viewName, model);
	}
	
	/** 
	 * Description : 保存公告 
	 * Create Date: 2010-10-8下午05:56:48 by guoyr  Modified Date: 2010-10-8下午05:56:48 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveBulletin")
	public ModelAndView saveBulletin(HttpServletRequest request, Bulletin bulletin, SessionStatus status) throws Exception{
		Map<String, Object> model=new HashMap<String, Object>();
		bulletinAgreementService.saveBulletinAgreement(bulletin);
		status.setComplete();
		return new ModelAndView(com.gpcsoft.core.Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  读取excel内容，批量保存采购公告
	 * Create Date: 2011-3-17下午03:31:54 by sunl  Modified Date: 2011-3-17下午03:31:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=saveBulletinBatch")
	public ModelAndView saveBulletinBatch(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String result="";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		Iterator it=multipartRequest.getFileNames();
		if(it == null){
			result = "不是有效的导入文件！";
		}else{
			for(;it.hasNext(); ){
				String fName=(String) it.next();
				result = bulletinAgreementService.saveBulletinBatch(request,multipartRequest.getFile(fName));
			}
		}
		model.put(Constants.JSON_RESULT,StringUtils.string2ASCII(result));
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	// 过滤当前用户
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected QueryObject onFind(QueryObject query, HttpServletRequest request)throws Exception {
		User user = AuthenticationHelper.getCurrentUser(true);
		query.getQueryParam().and(new QueryParam("createUser.objId",QueryParam.OPERATOR_EQ,user.getObjId()));
		return super.onFind(query, request);
	}
	
	/** 
	 * Description :  跳转到采购预告管理 
	 * Create Date: 2011-3-11下午02:27:00 by yucy  Modified Date: 2011-3-11下午02:27:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPreBulletinList")
	public ModelAndView toPreBulletinList(HttpServletRequest request) throws Exception{
		Map<String, Object> model=new HashMap<String, Object>();
		return new ModelAndView("toPreBulletinListView", model);
	}
	
	/** 
	 * Description :  跳转到审核页面
	 * Create Date: 2011-3-11下午04:02:22 by yucy  Modified Date: 2011-3-11下午04:02:22 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPreBulletinAudit")
	public ModelAndView toPreBulletinAudit(HttpServletRequest request ,String objId) throws Exception{
		Map<String, Object> model=new HashMap<String, Object>();
		if(StringUtils.hasLength(objId)){
			Bulletin bulletin = bulletinService.get(objId);
			Attachment attachment = bulletin.getContent();				
			String attachPath = attachment.getPath()==null?"":attachment.getPath();
			bulletin.setBullContents(FileOperateUtil.readFileByLines(getDefaultPath()+attachPath+attachment.getFileName()));
			model.put("bulletin", bulletin);
		}
		return new ModelAndView("toPreBulletinAuditView", model);
	}
	
	/** 
	 * Description :  跳转到查看页面
	 * Create Date: 2011-3-11下午04:09:25 by yucy  Modified Date: 2011-3-11下午04:09:25 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPreBulletinDetail")
	public ModelAndView toPreBulletinDetail(HttpServletRequest request ,String objId) throws Exception{
		Map<String, Object> model=new HashMap<String, Object>();
		if(StringUtils.hasLength(objId)){
			Bulletin bulletin = bulletinService.get(objId);
			Attachment attachment = bulletin.getContent();			
			String attachPath = attachment.getPath()==null?"":attachment.getPath();
			bulletin.setBullContents(FileOperateUtil.readFileByLines(getDefaultPath()+attachPath+attachment.getFileName()));
			model.put("bulletin", bulletin);
		}
		return new ModelAndView("toPreBulletinDetailView", model);
	}

	/** 
	 * Description :  更新公告的状态
	 * Create Date: 2011-3-11下午05:05:28 by yucy  Modified Date: 2011-3-11下午05:05:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateStatus")
	public ModelAndView updateStatus(HttpServletRequest request ,String objIds) throws Exception{
		Map<String, Object> model=new HashMap<String, Object>();
		Map<String, Object> param=new HashMap<String, Object>();

		if(StringUtils.hasLength(objIds)){
			param.put("auditStatus", request.getParameter("auditStatus"));
			param.put("relStatus", request.getParameter("relStatus"));
			bulletinAgreementService.updateStatus(objIds, param);
		}
		return new ModelAndView(com.gpcsoft.core.Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获得配置文件中的文件存储路径
	 * Create Date: 2011-6-24下午05:05:28 by sunl  Modified Date: 2011-6-24下午05:05:28 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private String getDefaultPath() throws Exception {
		return messageSource.getMessage("uploadUrl");
	}
}
