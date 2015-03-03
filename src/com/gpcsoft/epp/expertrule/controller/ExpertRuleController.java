package com.gpcsoft.epp.expertrule.controller;

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

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.expertrule.domain.ExpertRuleTypeEnum;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRule;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRuleItem;
import com.gpcsoft.epp.expertrule.service.ExpertRuleService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.workgroup.domain.WorkGroupTypeEnum;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.eprocurement.taker.provider.CodePO;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
/**
*  @gpcsoft.view value="obtainExpertForProject"
*  	url="view/es/planform/expertrule/obtain_Expert_List.jsp"
*  @gpcsoft.view value="getExpertRuleByProjectId"
*  	url="view/es/planform/expertrule/expertRuleTable.jsp"
*  @gpcsoft.view value="getExpertRuleBySubProjectId"
*  	url="view/es/planform/expertrule/expertRule_drawn.jsp"
*  @gpcsoft.view value="getExpertByProjectId"
* 	url="view/es/planform/expertrule/takeExpertTable.jsp"
*  @gpcsoft.view value="getUnit"
*  	url="view/es/planform/expertrule/experts_unit.jsp"
*  @gpcsoft.view value="toExtractionCondition"
*  	url="view/es/planform/expertrule/expert_condition.jsp"
*  @gpcsoft.view value="printView"
*  	url="view/es/planform/expertrule/print.jsp"
*/
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={TakeExpertRule.class,TakeExpertRuleItem.class})
@RequestMapping("/ExpertruleController.do")//页面请求路径,可修改
public class ExpertRuleController extends AnnotationMultiController<TakeExpertRule> {

	@Autowired(required=true) @Qualifier("expertRuleServiceImpl")
	private ExpertRuleService expertRuleService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	/** 
	 * Description :  保存专家规则
	 * Create Date: 2010-8-2下午02:57:00 by yangx  Modified Date: 2010-8-2下午02:57:00 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=saveExpertRule")
	public ModelAndView saveExpertRule(HttpServletRequest request,SessionStatus stauts,TakeExpertRule expertRule)throws Exception {
		logger.debug("\nExpertruleController||saveExpertRule\n");
		expertRule.setExpertRuleType(ExpertRuleTypeEnum.ACCREDITATION_RULE);
		expertRuleService.saveExpertRule(expertRule);//保存专家规则
		stauts.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  根据包组Id跳转到查看规则页面或提交规则页面
	 * Create Date: 2010-8-7下午05:43:05 by yangx  Modified Date: 2010-8-7下午05:43:05 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getExpertRuleBySubProjectId")
	 public ModelAndView getExpertRuleBySubProjectId(HttpServletRequest request)throws Exception {
		 logger.debug("\nes ExpertruleController||getExpertRuleBySubProjectId\n");
		 String subProjectId = request.getParameter("subProjectId");
		 String projectId = request.getParameter("projectId");
		 Map<String,Object> model = new HashMap<String,Object>();	
		 TakeExpertRule takeExpertRule = expertRuleService.toCreateOrUpdateTakeExpertRule(projectId);
		 String meetRoom = expertRuleService.getMeetRoomByProjectId(projectId);
		 model.put("takeExpertRule", takeExpertRule);
		 model.put("subProjectId", subProjectId);
		 model.put("projectId", projectId);
		 model.put("meetRoom", meetRoom);
		 return new ModelAndView("getExpertRuleBySubProjectId", model);
	 }
	
	/** 
	 * Description :  评审专家申请打印预览
	 * Create Date: 2010-10-15下午01:35:30 by wangcl  Modified Date: 2010-10-15下午01:35:30 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPrint")
	 public ModelAndView toPrint(String projectId){
		 logger.debug("\nes ExpertruleController||toPrint\n");
		 TakeExpertRule  rule = expertRuleService.getByProjectId(projectId);
		 ProjectRule prule =  projectService.getProjectRuleByProjectId(projectId);
		 Map<String,Object> model = new HashMap<String,Object>();	
		 model.put("rule", rule);
		 model.put("prule", prule);
		 model.put("user", AuthenticationHelper.getCurrentUser(true));
		 return new ModelAndView("printView", model);
	}
		
	/** 
	 * Description :  获取品目[来源：本地]
	 * Create Date: 2010-8-19上午09:25:33 by yangx  Modified Date: 2010-8-19上午09:25:33 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getPurCategory")
	public ModelAndView getPurCategory(HttpServletRequest request)throws Exception {
		logger.debug("\nExpertruleController||getPurCategory\n");
		List<PurCategory> listPurCategory = expertRuleService.getPurCategory();//获取品目
		Map<String,Object> model = new HashMap<String,Object>();
		model.put(Constants.JSON_RESULT, listPurCategory);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :   获取单位[来源：本地]
	 * Create Date: 2010-8-20下午01:13:56 by yangx  Modified Date: 2010-8-20下午01:13:56 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getUnit")
	public ModelAndView getUnit(HttpServletRequest request)throws Exception {
		logger.debug("\nExpertruleController||getUnit\n");
		String unitType = request.getParameter("unitType");
		List<CodePO> unitArr = expertRuleService.getInfoForFile(SeparationEnum.UNIT);//获取单位
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("unitArr", unitArr);
		model.put("unitType", unitType);
		return new ModelAndView("getUnit", model);
	}
	
	/** 
	 * Description :  通过WebService读取信息写到指定位置
	 * Create Date: 2010-8-30下午03:45:16 by yangx  Modified Date: 2010-8-30下午03:45:16 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=writeInfoForWebService")
	public ModelAndView writeInfoForWebService(HttpServletRequest request)throws Exception {
		logger.debug("\nExpertruleController||writeInfoForWebService\n");
		String infoType = request.getParameter("infoType");
		expertRuleService.saveInfoForWebService(infoType);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  根据单位名称进行模糊查询单位信息
	 * Create Date: 2010-8-23下午03:35:19 by yangx  Modified Date: 2010-8-23下午03:35:19 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=searchUnitByName")
	public ModelAndView searchUnitByName(HttpServletRequest request) throws Exception {
		logger.debug("\nExpertruleController||searchUnitByName\n");
		String unitName = request.getParameter("unitName");
		List<CodePO> unitArr = expertRuleService.getUnitByName(unitName);//获取单位
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("unitArr", unitArr);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  保存专家规则条目
	 * Create Date: 2010-8-30下午08:31:57 by yangx  Modified Date: 2010-8-30下午08:31:57 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveExpertRuleCondition")
	public ModelAndView saveExpertRuleCondition(HttpServletRequest request,SessionStatus stauts,TakeExpertRuleItem takeExpertRuleItem) throws Exception {
		logger.debug("\nExpertruleController||saveExpertRuleCondition\n");
		expertRuleService.saveExpertRuleCondition(takeExpertRuleItem);
		stauts.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  提交规则、同步专家库
	 * Create Date: 2010-8-30下午09:21:49 by yangx  Modified Date: 2010-8-30下午09:21:49 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=createTakeProject")
	public ModelAndView createTakeProject(HttpServletRequest request,SessionStatus stauts,TakeExpertRule expertRule) throws Exception {
		logger.debug("\nExpertruleController||createTakeProject\n");
		String takeExpertRuleId = request.getParameter("takeExpertRuleId");
		expertRuleService.saveSubmitExpertRule(expertRule,takeExpertRuleId);
		stauts.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  跳转到显示抽取条件页面
	 * Create Date: 2010-8-31下午02:19:42 by yangx  Modified Date: 2010-8-31下午02:19:42 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toExtractionCondition")
	public ModelAndView toExtractionCondition(HttpServletRequest request) throws Exception {
		logger.debug("\nExpertruleController||toExtractionCondition\n");
		String takeExpertRuleId = request.getParameter("takeExpertRuleId");
		List<TakeExpertRuleItem> listTakeExpertRuleItem = expertRuleService.getTakeExpertRuleItemBytakeExpertRuleId(takeExpertRuleId);
		Map<String,Object> model = new HashMap<String,Object>();
		if (listTakeExpertRuleItem!=null&&listTakeExpertRuleItem.size()>0) {
			model.put("itemFlag","yes");
			model.put("listTakeExpertRuleItem", listTakeExpertRuleItem);
		}else {
			model.put("itemFlag","no");
		}
		return new ModelAndView("toExtractionCondition", model);
	}	
	
	/** 
	 * Description :  根据规则明细Id查询规则明细
	 * Create Date: 2010-8-31下午04:06:03 by yangx  Modified Date: 2010-8-31下午04:06:03 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toExpertRuleItem")
	public ModelAndView toExpertRuleItem(HttpServletRequest request) throws Exception {
		logger.debug("\nExpertruleController||toExpertRuleItem\n");
		String objId = request.getParameter("objId");
		TakeExpertRuleItem takeExpertRuleItem = (TakeExpertRuleItem)expertRuleService.getTakeExpertRuleItemByObjId(objId);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("takeExpertRuleItem",takeExpertRuleItem);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  根据规则明细Id删除规则明细
	 * Create Date: 2010-8-31下午04:20:47 by yangx  Modified Date: 2010-8-31下午04:20:47 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=removeExpertRuleItem")
	public ModelAndView removeExpertRuleItem(HttpServletRequest request) throws Exception {
		logger.debug("\nExpertruleController||removeExpertRuleItem\n");
		String objId = request.getParameter("objId");
		expertRuleService.remove(objId,TakeExpertRuleItem.class);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	 /** 
	 * Description :  根据项目Id获取包组列表
	 * Create Date: 2010-8-7下午03:33:16 by yangx  Modified Date: 2010-8-7下午03:33:16 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getExpertByProjectId")
	 public ModelAndView getExpertByProjectId(HttpServletRequest request)throws Exception {
		String projectId = request.getParameter("projectId");//项目Id
		List<Project> subProjectList = projectService.getSubProjectByProjectId(projectId);
		Map<String,Object> model = new HashMap<String,Object>();	
		model.put("subProjectList", subProjectList);
		return new ModelAndView("getExpertByProjectId", model);
	 }

	/** 
	 * Description :  显示某项目所有标段的开标小组明细
	 * Create Date: 2010-8-5上午10:42:45 by yangx  Modified Date: 2010-8-5上午10:42:45 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toWorkGroupForProject")
	public ModelAndView toWorkGroupForProject(HttpServletRequest request,String subProjectId)throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();	
		String fromTab = request.getParameter("fromTab");
		Project project=expertRuleService.saveOrgetAllGroupMenber(subProjectId, WorkGroupTypeEnum.APPRAISAL);
		List<WorkgMember> listWorkgMember = expertRuleService.getWorkMemberListByUserIdAndProjectId(WorkGroupTypeEnum.APPRAISAL, subProjectId);
		TakeExpertRule takeExpertRule = expertRuleService.getExpertRuleBySubProjectId(subProjectId);//获取专家规则
		if(takeExpertRule!=null) {//判断是否已存在专家
			if(takeExpertRule.getAmount().equals(Integer.toString(listWorkgMember.size()))) {//判断正选专家人数是否已够
				model.put("obtain","no");//不能在获取
			}
		}
		model.put("project", project);
		model.put("subProjectId",subProjectId);
		model.put("fromTab", fromTab);
		model.put("listWorkgMember", listWorkgMember);
		return new ModelAndView("obtainExpertForProject", model);
	}
}
