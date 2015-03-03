package com.gpcsoft.agreement.bargin.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.bargin.project.domain.ProjectSignInfo;
import com.gpcsoft.agreement.bargin.project.service.ProjectSignInfoService;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="projectSignInfoLoadView"
  *  url="view/agreement/bargin/project/project_info_sign_div.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ProjectSignInfo.class})
@RequestMapping("/ProjectSignInfoController.do")//页面请求路径,可修改
public class ProjectSignInfoController extends AnnotationMultiController<ProjectSignInfo> {

	@Autowired(required=true) @Qualifier("projectSignInfoServiceImpl")
	private ProjectSignInfoService projectSignInfoService;

	/** 
	 * Description :  获得信息
	 * Create Date: 2010-12-2上午11:25:22 by yucy  Modified Date: 2010-12-2上午11:25:22 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getInfoByProject")
	public ModelAndView getInfoByProject( HttpServletRequest request ,String projectId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		ProjectSignInfo Info =  projectSignInfoService.getSignInfoByProjectId(projectId);
		model.put("Info",null!=Info?Info:new ProjectSignInfo());
		return new ModelAndView("projectSignInfoLoadView",model);
	}
	
}
