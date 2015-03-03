package com.gpcsoft.epp.project.controller;

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
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;

/** 
 * 
 */ 
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ProjectRule.class,Project.class})
@RequestMapping("/ProjectRuleController.do")//页面请求路径,可修改
public class ProjectRuleController extends AnnotationMultiController<ProjectRule>{

	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	/** 
	 * FuncName:updateProjectTime
	 * Description :  维护项目时间：报名时间,结束报名时间,投标开始时间,投标结束时间,开标时间
	 * @param   request,stauts,projectRule:项目规则
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-11-10下午05:29:31 
	 * @Modifier  yangx
	 * @Modified Date: 2010-11-10下午05:29:31   
	 */
	@RequestMapping(params = "method=updateProjectTime")
	public ModelAndView updateProjectTime(HttpServletRequest request,SessionStatus stauts,ProjectRule projectRule)throws Exception{
		logger.debug("\nes=ProjectRuleController||updateProjectTime\n");
		projectService.updateProjectTime(projectRule);
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:updateProjectEvalTime
	 * Description :   更新项目评标时间
	 * @param   request,stauts,projectRule:项目规则
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-11-10下午05:29:31 
	 * @Modifier  yangx
	 * @Modified Date: 2010-11-10下午05:29:31   
	 */
	@RequestMapping(params = "method=updateProjectEvalTime")
	public ModelAndView updateProjectEvalTime(HttpServletRequest request,SessionStatus stauts,ProjectRule projectRule)throws Exception{
		logger.debug("\nes=ProjectRuleController||updateProjectEvalTime\n");
		projectService.updateProjectevalTime(projectRule);
		stauts.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
}
