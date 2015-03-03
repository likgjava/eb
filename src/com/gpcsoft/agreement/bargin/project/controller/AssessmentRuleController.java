package com.gpcsoft.agreement.bargin.project.controller;

import java.util.ArrayList;
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

import com.gpcsoft.agreement.bargin.project.domain.AssessmentRule;
import com.gpcsoft.agreement.bargin.project.service.AssessmentRuleService;
import com.gpcsoft.agreement.bargin.require.domain.RequireItem;
import com.gpcsoft.agreement.bargin.require.service.RequireItemService;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
 * @gpcsoft.view value="assessmentRuleFormView"
 *  url="view/agreement/bargin/project/assessmentrule/assessment_rule_form.jsp"
 *  
 * @gpcsoft.view value="assessmentRuleListView"
 *  url="view/agreement/bargin/project/assessmentrule/assessment_rule_list.jsp"
 *
 * @gpcsoft.view value="chooseAssessmentRuleListView"
 *  url="view/agreement/bargin/project/assessmentrule/assessment_rule_choose_list.jsp"
 *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={AssessmentRule.class})
@RequestMapping("/AssessmentRuleController.do")//页面请求路径,可修改
public class AssessmentRuleController extends AnnotationMultiController<AssessmentRule> {

	@Autowired(required=true) @Qualifier("assessmentRuleServiceImpl")
	private AssessmentRuleService assessmentRuleService;
	
	@Autowired(required=true) @Qualifier("requireItemServiceImpl")
	private RequireItemService requireItemServiceImpl;
	
	/** 
	 * Description :  跳转到规则表单
	 * Create Date: 2011-8-25上午11:27:58 by yucy  Modified Date: 2011-8-25上午11:27:58 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toCreateOrUpdateRule")
	ModelAndView toCreateOrUpdateRule( String objId , HttpServletRequest request ) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("assessmentRule", StringUtils.hasLength(objId)? assessmentRuleService.get(objId) : new AssessmentRule());
		return new ModelAndView("assessmentRuleFormView",model);
	}
	
	/** 
	 * Description :  跳转到规则管理列表
	 * Create Date: 2011-8-25上午11:27:58 by yucy  Modified Date: 2011-8-25上午11:27:58 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toAssessmentRuleList")
	ModelAndView toAssessmentRuleList ( HttpServletRequest request ) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("assessmentRuleListView",model);
	}
	
	/** 
	 * Description :  跳转到选择规则页面
	 * Create Date: 2011-8-25上午11:27:58 by yucy  Modified Date: 2011-8-25上午11:27:58 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toChooseAssessmentRuleList")
	ModelAndView toChooseAssessmentRuleList ( HttpServletRequest request ) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		//取出项目评审规则
		List<RequireItem> requireItemList =  requireItemServiceImpl.getRequireItemsByProjId(request.getParameter("projId"));
		List<String> categoryIds = new ArrayList<String>();
		for(RequireItem requireItem :  requireItemList) categoryIds.add(requireItem.getPurCategory().getObjId());//取出品目集合
		model.put("categoryIds", categoryIds);
		List<AssessmentRule> assessmentRuleList =assessmentRuleService.getAssessmentRuleByCategoryIds(model);
		model.put("assessmentRuleList", assessmentRuleList);
		return new ModelAndView("chooseAssessmentRuleListView",model);
	}
	
    /** 
     * Description :  保存规则
     * Create Date: 2011-8-25下午01:36:31 by yucy  Modified Date: 2011-8-25下午01:36:31 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=saveAssessmentRule")
    public ModelAndView saveAssessmentRule(AssessmentRule assessmentRule, HttpServletRequest request, SessionStatus status) throws Exception {
    	String returnMessage = "success";
        Map<String, Object> model = new HashMap<String, Object>();
        
        //附件处理
        if(request instanceof MultipartHttpServletRequest){
        	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        	CommonsMultipartFile file = null;
        	file = (CommonsMultipartFile)multipartRequest.getFile("assessment_File") ;
        	if(file.getSize()!=0){
        		try {
        			Object o = AttachmentUtil.uploadFile(request,"assessment_File");
        			if(o instanceof GpcBaseObject){
        				Attachment attachment = (Attachment)o;
        				if(assessmentRule.getAssessmentFile()!=null){
        					attachment.setObjId(assessmentRule.getAssessmentFile().getObjId());
        				}
        				assessmentRule.setAssessmentFile(attachment);
        			}
        		}catch(Exception de) {
        			returnMessage = StringUtils.string2ASCII(de.getMessage());
        		}
        	}
        }
        
        //保存
        assessmentRuleService.save(assessmentRule);
        status.setComplete();
        model.put(Constants.JSON_RESULT,returnMessage);
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
}
