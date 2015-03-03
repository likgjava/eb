package com.gpcsoft.epp.taskplan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSumEnum;
import com.gpcsoft.epp.taskplan.service.TaskPlanMDetailService;

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={TaskPlanMDetail.class})
@RequestMapping("/TaskPlanMDetailController.do")//页面请求路径,可修改
public class TaskPlanMDetailController extends AnnotationMultiController<TaskPlanMDetail> {

	@Autowired(required=true) @Qualifier("taskPlanMDetailServiceImpl")
	private TaskPlanMDetailService taskPlanMDetailService;

	/** 
	 * FuncName:getSubNotSumDetailsByOrg
	 * Description : 根据机构，获得其下级机构的未汇总的资金明细 
	 * @param orgId:机构主键,taskType:申报书类型
	 * @return ModelAndView
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier:liangxj     
	 * @Modified Date:  2010-6-3 下午04:12:39 
	 */
	@RequestMapping(params = "method=getSubNotSumDetailsByOrg")
	public ModelAndView getSubNotSumDetailsByOrg(String orgId,String taskType,String ebuyMethod)throws Exception {
		logger.debug("\nes=TaskPlanMDetailController||getSubNotSumDetailsByOrg\n");
		List<TaskPlanMDetail> taskPlanMDetails = taskPlanMDetailService.getSubNotSumDetailsByOrg(orgId,TaskPlanSumEnum.SUMMARY, taskType,ebuyMethod);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put(Page.DATA, taskPlanMDetails);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
