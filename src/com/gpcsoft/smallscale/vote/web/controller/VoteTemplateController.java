package com.gpcsoft.smallscale.vote.web.controller;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.expert.domain.ExpertInfo;
import com.gpcsoft.smallscale.vote.domain.VoteAssesseExpert;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedThing;
import com.gpcsoft.smallscale.vote.domain.VotePointer;
import com.gpcsoft.smallscale.vote.domain.VoteTemplate;
import com.gpcsoft.smallscale.vote.domain.VoteUnitGroup;
import com.gpcsoft.smallscale.vote.service.VoteAssesseExpertService;
import com.gpcsoft.smallscale.vote.service.VoteTemplateService;
import com.gpcsoft.smallscale.vote.service.VoteUnitGroupService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
  * @gpcsoft.view value="voteTemplateFormView"
  *  url="view/smallscale/vote/vote_template_add.jsp"
  * @gpcsoft.view value="voteTemplateListView"
  *  url="view/smallscale/vote/vote_template_list.jsp"
  * @gpcsoft.view value="voteTemplateDetailView"
  *  url="view/smallscale/vote/vote_template_detail.jsp"
  * @gpcsoft.view value="votePointerMaintenaceView"
  *  url="view/smallscale/vote/vote_pointer_maintenace.jsp"
  * @gpcsoft.view value="voteAssessedThingMaintenaceView"
  *  url="view/smallscale/vote/vote_assessedThing_maintenace.jsp"
  * @gpcsoft.view value="templateStatisticView"
  *  url="view/smallscale/vote/show_template_statistic.jsp"
  * @gpcsoft.view value="voteUnitGroupListView"
  *  url="view/smallscale/vote/vote_unit_group_list.jsp"
  * @gpcsoft.view value="voteUnitGroupFormView"
  *  url="view/smallscale/vote/vote_unit_group_add.jsp"
  * @gpcsoft.view value="voteAssesseExpertListView"
  *  url="view/smallscale/vote/vote_assesse_expert_list.jsp"
  * @gpcsoft.view value="voteAssesseExpertFormView"
  *  url="view/smallscale/vote/vote_assesse_expert_add.jsp"
  * @gpcsoft.view value="voteTemplateMediumListView"
  *  url="view/smallscale/vote/vote_template_medium_list.jsp"
  * @gpcsoft.view value="voteAssessedListView"
  *  url="view/smallscale/vote/vote_assessed_list.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={VoteTemplate.class})
@RequestMapping("/VoteTemplateController.do")//页面请求路径,可修改
public class VoteTemplateController extends AnnotationMultiController<VoteTemplate> {

	@Autowired(required=true) @Qualifier("voteTemplateServiceImpl")
	private VoteTemplateService voteTemplateService;
	
	@Autowired(required=true) @Qualifier("voteUnitGroupServiceImpl")
	private VoteUnitGroupService voteUnitGroupService;
	
	@Autowired(required=true) @Qualifier("voteAssesseExpertServiceImpl")
	private VoteAssesseExpertService voteAssesseExpertService;

	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentServiceImpl;
	
	/**
	 * Description :  查看主题、评选分组、指标维护、参选对象、评审专家、合作媒体、投票管理
	 * Create Date: 2011-2-17上午09:00:04 by zhaojf  Modified Date: 2011-2-17上午09:00:04 by zhaojf
	 * @param   operateType
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=viewVoteTemplate")
	public ModelAndView viewVoteTemplate(String objId,String operateType,SessionStatus status) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		String returnView = "voteTemplateDetailView";//查看主题页面
		
		//主题信息
		VoteTemplate voteTemplate = voteTemplateService.get(objId);
		model.put("voteTemplate", voteTemplate);
		
		//评选组列表
		List<VoteUnitGroup> voteUnitGroupList = voteUnitGroupService.getVoteUnitGroupListByTemplateId(objId);
		model.put("voteUnitGroupList", voteUnitGroupList);
		
		if(operateType == null){
			//指标信息
			List<VotePointer> votePointerList = voteTemplateService.findPointOfTemplate(objId);
			model.put("votePointerList", votePointerList);
			
			//参选对象
			List<VoteAssessedThing> voteAssessThingList = voteTemplateService.findAssessedThingOfTemplate(objId);
			model.put("voteAssessThingList", voteAssessThingList);
			model.put("trNumCount", voteAssessThingList.size());
		}
		
		//指标列表页面
		if("pointerMaintenace".equals(operateType)){
			returnView = "votePointerMaintenaceView";
		}
		//参选对象列表页面
		if("assessedThingMaintenace".equals(operateType)){
			returnView = "voteAssessedThingMaintenaceView";
		}
		//评选分组列表页面
		if("unitGroup".equals(operateType)){
			returnView = "voteUnitGroupListView";
		}
		//评审专家列表页面
		if("assesseExpert".equals(operateType)){
			returnView = "voteAssesseExpertListView";
		}
		//协助媒体列表页面
		if("templateMedium".equals(operateType)){
			returnView = "voteTemplateMediumListView";
		}
		//投票管理列表页面
		if("voteAssessList".equals(operateType)){
			returnView = "voteAssessedListView";
		}
		
		status.setComplete();
		return new ModelAndView(returnView,model);
	}
	
	/**
	 * Description :  跳转到新增主题、修改主题页面
	 * Create Date: 2011-2-21上午09:49:54 by zhaojf  Modified Date: 2011-2-21上午09:49:54 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toCreateOrUpdateVoteTemplate")
	public ModelAndView toCreateOrUpdateVoteTemplate(String objId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		if(StringUtils.hasLength(objId)){
			VoteTemplate voteTemplate = voteTemplateService.get(objId);
			
			model.put("voteTemplate", voteTemplate);
		}else{
			VoteTemplate voteTemplate = new VoteTemplate();
			//自动生成主题编号
			voteTemplate.setTemplateCode(voteTemplateService.generateVoteTemplateCode());
			
			//获取当前用户  为默认评价专员
			Employee emp = AuthenticationHelper.getCurrentUser(true).getEmp();
			voteTemplate.setAppraiseCommissioner(emp.getName());
			voteTemplate.setCommissionerEmail(emp.getEmail());
			voteTemplate.setCommissionerPhone(emp.getMobile());
			voteTemplate.setCommissionerTel(emp.getTelOffice());
			
			model.put("voteTemplate", voteTemplate);
		}
		return new ModelAndView("voteTemplateFormView",model);
	}

	/**
	 * Description :  删除主题(确定删除时将同时删除主题下的指标、评选组、评选单位、投票表数据、指标分值表)
	 * Create Date: 2011-2-22下午02:12:40 by zhaojf  Modified Date: 2011-2-22下午02:12:40 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=removeVoteTemplate")
	public ModelAndView removeVoteTemplate(String objId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		voteTemplateService.removeVoteTemplate(objId);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description :  主题统计(主题下的评选单位的参评人数和最终成绩)
	 * Create Date: 2011-3-3上午09:21:55 by zhaojf  Modified Date: 2011-3-3上午09:21:55 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toTemplateStatistic")
	public ModelAndView toTemplateStatistic(String objId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		//主题信息
		VoteTemplate voteTemplate = voteTemplateService.get(objId);
		model.put("voteTemplate", voteTemplate);
		
		//评选单位信息
		List<Object> templateStatisticList = voteTemplateService.toTemplateStatistic(objId);
		model.put("templateStatisticList", templateStatisticList);
		
		return new ModelAndView("templateStatisticView",model);
	}

	/**
	 * Description :  获取评选组列表
	 * Create Date: 2011-4-25下午03:17:21 by zhaojf  Modified Date: 2011-4-25下午03:17:21 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=getVoteUnitGroupList")
	public ModelAndView getVoteUnitGroupList(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		String templateId = request.getParameter("templateId");
		paramsMap.put("templateId", templateId);
		
		String groupName = request.getParameter("groupName");
		paramsMap.put("groupName", groupName);
		
		//获取评选组
		Page page = prePage(request);
		Page<VoteUnitGroup> pageData = voteTemplateService.getVoteUnitGroupList(page, paramsMap);
		endPage(model, pageData, request);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}

	/**
	 * Description :  跳转到新增评选组(addOrUpdate)、修改评选组页面(addOrUpdate)
	 * Create Date: 2011-4-25下午04:23:00 by zhaojf  Modified Date: 2011-4-25下午04:23:00 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toCreateOrUpdateVoteUnitGroup")
	public ModelAndView toCreateOrUpdateVoteUnitGroup(String objId,String templateId,String operateType) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		String returnview = "voteUnitGroupFormView";
		if(StringUtils.hasLength(objId)){//修改
			VoteUnitGroup voteUnitGroup = voteUnitGroupService.get(objId);
			model.put("voteUnitGroup", voteUnitGroup);
		}else{//新增
			VoteUnitGroup voteUnitGroup = new VoteUnitGroup();
			VoteTemplate voteTemplate = new VoteTemplate();
			voteTemplate.setObjId(templateId);
			
			voteUnitGroup.setVoteTemplate(voteTemplate);
			model.put("voteUnitGroup", voteUnitGroup);
		}
		if(StringUtils.hasLength(operateType) && "getGroup".equals(operateType)){
			returnview = Constants.JSON_VIEW;
		}
		
		return new ModelAndView(returnview,model);
	}

	/**
	 * Description :  删除评选组
	 * Create Date: 2011-4-25下午05:03:00 by zhaojf  Modified Date: 2011-4-25下午05:03:00 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=removeVoteUnitGroup")
	public ModelAndView removeVoteUnitGroup(String objId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		voteUnitGroupService.remove(objId, VoteUnitGroup.class);
		model.put(Constants.JSON_RESULT, "success");
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description :  保存评选组
	 * Create Date: 2011-4-26上午09:50:30 by zhaojf  Modified Date: 2011-4-26上午09:50:30 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=saveVoteUnitGroup")
	public ModelAndView saveVoteUnitGroup(VoteUnitGroup voteUnitGroup,SessionStatus status) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		voteUnitGroupService.save(voteUnitGroup);
		status.setComplete();
		model.put(Constants.JSON_RESULT, "success");
		return new ModelAndView(Constants.JSON_VIEW,model);
	}

	/**
	 * Description :  跳转到新增评审专家、修改评审专家页面
	 * Create Date: 2011-5-9上午09:50:23 by zhaojf  Modified Date: 2011-5-9上午09:50:23 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toAddOrUpdateVoteAssesseExpert")
	public ModelAndView toAddOrUpdateVoteAssesseExpert(String objId,String templateId,String operateType) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		VoteAssesseExpert voteAssesseExpert = new VoteAssesseExpert();
		if(StringUtils.hasLength(objId)){//修改
			voteAssesseExpert = voteAssesseExpertService.get(objId);
		}else{//新增
			VoteTemplate voteTemplate = new VoteTemplate();
			voteTemplate.setObjId(templateId);
			voteAssesseExpert.setVoteTemplate(voteTemplate);
			
			VoteUnitGroup voteUnitGroup = new VoteUnitGroup();
			voteAssesseExpert.setVoteUnitGroup(voteUnitGroup);
			
			ExpertInfo expertInfo = new ExpertInfo();
			voteAssesseExpert.setExpertInfo(expertInfo);
		}
		model.put("voteAssesseExpert", voteAssesseExpert);
		
		//评选组列表
		List<VoteUnitGroup> voteUnitGroupList = voteUnitGroupService.getVoteUnitGroupListByTemplateId(voteAssesseExpert.getVoteTemplate().getObjId());
		model.put("voteUnitGroupList", voteUnitGroupList);
		
		return new ModelAndView("voteAssesseExpertFormView",model);
	}

	/**
	 * Description :  获取评审专家列表
	 * Create Date: 2011-5-9上午10:18:20 by zhaojf  Modified Date: 2011-5-9上午10:18:20 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=getVoteAssesseExpertList")
	public ModelAndView getVoteAssesseExpertList(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		String templateId = request.getParameter("templateId");
		paramsMap.put("voteTemplateId", templateId);
		
		String expertName = request.getParameter("expertName");
		paramsMap.put("expertName", expertName);
		
		String voteUnitGroupId = request.getParameter("voteUnitGroupId");
		paramsMap.put("voteUnitGroupId", voteUnitGroupId);
		
		//评审专家列表
		Page page = prePage(request);
		Page<VoteAssesseExpert> pageData = voteTemplateService.getVoteAssesseExpertList(page, paramsMap);
		endPage(model, pageData, request);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}

	/**
	 * Description :  保存评审专家 
	 * Create Date: 2011-5-9上午10:24:46 by zhaojf  Modified Date: 2011-5-9上午10:24:46 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=saveVoteAssessExpert")
	public ModelAndView saveVoteAssessExpert(VoteAssesseExpert voteAssesseExpert,HttpServletRequest request,SessionStatus status) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		String returnMessage = "success";
		//附件处理
        if(request instanceof MultipartHttpServletRequest){
        	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        	CommonsMultipartFile file = null;
        	file = (CommonsMultipartFile)multipartRequest.getFile("pictureFile") ;
        	if(file.getSize()!=0){
        		try {
        			//商品图片
        			Object o = AttachmentUtil.uploadFile(request,"pictureFile");
        			if(o instanceof GpcBaseObject){
        				Attachment attachment = (Attachment)o;
        				attachmentServiceImpl.save(attachment);
        				voteAssesseExpert.setExpertPic(attachment.getObjId());
        			}
        		}catch(Exception de) {
        			returnMessage = StringUtils.string2ASCII(de.getMessage());
        		}
        	}
        }
        voteAssesseExpert.setVoteUnitGroupName(voteUnitGroupService.get(voteAssesseExpert.getVoteUnitGroup().getObjId()).getGroupName());
		voteAssesseExpertService.save(voteAssesseExpert);
		status.setComplete();
		model.put(Constants.JSON_RESULT, returnMessage);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}

	/**
	 * Description :  删除评审专家
	 * Create Date: 2011-5-9上午10:28:11 by zhaojf  Modified Date: 2011-5-9上午10:28:11 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=removeVoteAssesseExpert")
	public ModelAndView removeVoteAssesseExpert(String objId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		voteAssesseExpertService.remove(objId, VoteAssesseExpert.class);
		model.put(Constants.JSON_RESULT, "success");
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
}
