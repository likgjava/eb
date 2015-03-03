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

import com.gpcsoft.agreement.bargin.project.domain.ProjectPayInfo;
import com.gpcsoft.agreement.bargin.project.service.ProjectPayInfoService;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="projectPayInfoLoadView"
  *  url="view/agreement/bargin/project/project_info_pay_div.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ProjectPayInfo.class})
@RequestMapping("/ProjectPayInfoController.do")//页面请求路径,可修改
public class ProjectPayInfoController extends AnnotationMultiController<ProjectPayInfo> {

	@Autowired(required=true) @Qualifier("projectPayInfoServiceImpl")
	private ProjectPayInfoService projectPayInfoService;
	
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
		ProjectPayInfo Info = projectPayInfoService.getPayInfoByProjectId(projectId);
		model.put("Info",null!=Info?Info:new ProjectPayInfo());
		return new ModelAndView("projectPayInfoLoadView",model);
	}
}
