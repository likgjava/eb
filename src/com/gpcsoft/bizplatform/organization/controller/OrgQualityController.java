package com.gpcsoft.bizplatform.organization.controller;

import java.util.ArrayList;
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

import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationDefine;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.domain.OrgQuality;
import com.gpcsoft.bizplatform.organization.domain.QualificationDetail;
import com.gpcsoft.bizplatform.organization.service.OrgQualityService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

/**
  * @gpcsoft.view value="orgQualityManage"
  *  url="view/bizplatform/organization/manager/org_quality_manage.jsp"
  * @gpcsoft.view value="orgQualityAuditView"
  *  url="view/bizplatform/organization/manager/org_quality_audit.jsp"
  * @gpcsoft.view value="orgQualityFormView"
  *  url="view/bizplatform/organization/manager/org_quality_form.jsp"
  * @gpcsoft.view value="orgQualityAuditDetail"
  *  url="view/bizplatform/organization/manager/org_quality_auditview.jsp"
  *  
  * @gpcsoft.view value="qualityDetailView"
  *  url="view/bizplatform/organization/manager/org_quality_view.jsp"
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OrgQuality.class})
@RequestMapping("/OrgQualityController.do")//页面请求路径,可修改
public class OrgQualityController extends AnnotationMultiController<OrgQuality> {

	@Autowired(required=true) @Qualifier("orgQualityServiceImpl")
	private OrgQualityService orgQualityService;
	
    /** 
     * Description :  跳转到维护资质信息页面。
     * Create Date: 2010-8-2下午10:02:35 by yucy  Modified Date: 2010-8-2下午10:02:35 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=toOrgQualityManageView")
    public ModelAndView toOrgQualityManageView(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	List<OrgQuality> orgQuality = orgQualityService.getOrgQuality(AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
    	model.put("orgQuality", orgQuality);
    	return new ModelAndView("orgQualityManage",model);
    }
    
    /** 
     * Description :  跳转到审核资质页面
     * Create Date: 2010-8-2下午10:02:48 by yucy  Modified Date: 2010-8-2下午10:02:48 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=toOrgQualityAuditView")
    public ModelAndView toOrgQualityAuditView(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	OrgInfo orgInfo = new OrgInfo();
    	model.put("orgInfo", orgInfo);
    	return new ModelAndView("orgQualityAuditView",model);
    }
    
        /**
     * Description :获取资质分类下资质信息 （资质分类下联动菜单数据拼装）
     * Create Date: 2010-5-28 by liqy  Modified Date: 2010-5-28 by liqy
     * @param   
     * @return  
     * @Exception
     */
    @RequestMapping(params = "method=getQualityGradesSelect")
    public ModelAndView getQualityGradesSelect(String defindId) throws Exception {  
    	Map<String, Object> model = new HashMap<String, Object>();
    	List<Object> modelReturnList=new ArrayList<Object>();
		model.put(Constants.JSON_RESULT,orgQualityService.getQualityGradesSelect(defindId,modelReturnList));
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
    
	/** 
	 * Description :  新增或修改资质(跳转页面)
	 * Create Date: 2010-7-21上午10:19:52 by yucy  Modified Date: 2010-7-21上午10:19:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateOrUpdateOrgQuality")   
	public ModelAndView toCreateOrUpdateOrgQuality(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		OrgQuality orgQuality = null;
		if(null!=request.getParameter("objId")&&!"".equals(request.getParameter("objId"))){
 			model = orgQualityService.getCreateOrUpdateOrgQuality(request.getParameter("objId"));
		}else {
			orgQuality = new OrgQuality();
			model.put("orgQuality", orgQuality);
		}
    	ModelAndView mv = new ModelAndView("orgQualityFormView", model);
    	return mv;
	}
	

    
    /** 
     * Description :  新增保存资质信息 
     * Create Date: 2010-8-2上午11:08:26 by yucy  Modified Date: 2010-8-2上午11:08:26 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=saveQualificationInfo")
    public ModelAndView saveQualificationInfo(OrgQuality qualification,HttpServletRequest request,SessionStatus status) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	String paramJson = request.getParameter("paramJson");
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("paramJson", paramJson);
    	Boolean result = orgQualityService.saveQualificationInfo(qualification,param);
    	if(result){
    		model.put(Constants.JSON_RESULT,"操作成功!");
    	}
    	status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
    
    
	/** 
	 * Description :  跳转审核页面
	 * Create Date: 2010-8-3上午12:32:40 by yucy  Modified Date: 2010-8-3上午12:32:40 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toAuditQualityView")   
	public ModelAndView toAuditQualityView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		OrgQuality orgQuality = null;
		if(null!=request.getParameter("objId")&&!"".equals(request.getParameter("objId"))){
			orgQuality = orgQualityService.get(request.getParameter("objId"));
			Set defineSet = orgQuality.getQualificationClass().getSubQualification();
			Set detailSet = orgQuality.getQualificationDetailSet();
			List<QualificationDefine> defineList = new ArrayList<QualificationDefine>();
			List<QualificationDetail> detailList = new ArrayList<QualificationDetail>();
			defineList.addAll(defineSet);
			detailList.addAll(detailSet);
			model.put("detailList", detailList);
		}else {
			orgQuality = new OrgQuality();
		}
		model.put("orgQuality", orgQuality);
    	ModelAndView mv = new ModelAndView("orgQualityAuditDetail", model);
    	return mv;
	}
	
    /** 
     * Description :  跳转到查看资质页面
     * Create Date: 2010-8-2下午10:02:48 by yucy  Modified Date: 2010-8-2下午10:02:48 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toQualityView")
    public ModelAndView toQualityView(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();   
		OrgQuality orgQuality = null;
		if(null!=request.getParameter("objId")&&!"".equals(request.getParameter("objId"))){
			orgQuality = orgQualityService.get(request.getParameter("objId"));
			Set defineSet = orgQuality.getQualificationClass().getSubQualification();
			Set detailSet = orgQuality.getQualificationDetailSet();
			List<QualificationDefine> defineList = new ArrayList<QualificationDefine>();
			List<QualificationDetail> detailList = new ArrayList<QualificationDetail>();
			defineList.addAll(defineSet);
			detailList.addAll(detailSet);
			model.put("detailList", detailList);
		}else {
			orgQuality = new OrgQuality();
		}
		model.put("orgQuality", orgQuality);
    	ModelAndView mv = new ModelAndView("qualityDetailView", model);
    	return mv;
    }
	
    /** 
     * Description :  审核资质
     * Create Date: 2010-8-3上午01:17:16 by yucy  Modified Date: 2010-8-3上午01:17:16 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=saveAuditStatus")
    public ModelAndView saveAuditStatus(OrgQuality orgQuality,HttpServletRequest request,SessionStatus status) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	
    	String objId = request.getParameter("objId");
    	String statu = request.getParameter("statu");
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("objId", objId);
    	param.put("statu", statu);
    	param.put("orgQuality", orgQuality);
    	
    	
    	Boolean result = orgQualityService.saveAuditStatus(param);
    	if(result){
    		model.put(Constants.JSON_RESULT,"操作成功!");
    	}
    	status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
    
    /**
     * Description :删除资质信息（新建不通过删除和变更不同过删除处理） 
     * Create Date: 2010-6-4 by yucy  Modified Date: 2010-6-4 by yucy
     * @param   
     * @return  
     * @Exception
     */
    @RequestMapping(params = "method=removeQualificationInfo")
    public ModelAndView removeQualificationInfo(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	String quolityIds = request.getParameter("quolityIds");
    	Boolean result = orgQualityService.removeQualificationInfo(quolityIds);
    	if(result){
    		model.put(Constants.JSON_RESULT,"删除成功!");
    	}else{
    		model.put(Constants.JSON_RESULT,"删除失败!");
    	}
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
    
    /**
     * Description :资质信息对象唯一性判断 
     * Create Date: 2010-6-1 by liqy  Modified Date: 2010-6-1 by liqy
     * @param   
     * @return  
     * @Exception
     */
    @RequestMapping(params = "method=isUniqueQualification")
    public ModelAndView isUniqueQualification(String objectId,String quaGradesId,String objectName) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	Boolean result = orgQualityService.isUniqueQualification(objectId,quaGradesId,objectName);
    	String messageReturn = "";
    	if(result)messageReturn = messageSource.getMessage("base.qualification.qualification.isUniqueTrue");
		model.put(Constants.JSON_RESULT,messageReturn);
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
    
    
    
}
