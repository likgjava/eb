package com.gpcsoft.pubservice.application.concern.web.controller;

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

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.pubservice.application.concern.domain.ConcernGroup;
import com.gpcsoft.pubservice.application.concern.service.ConcernGroupService;

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ConcernGroup.class})
@RequestMapping("/ConcernGroupController.do")//页面请求路径,可修改
public class ConcernGroupController extends AnnotationMultiController<ConcernGroup> {

	@Autowired(required=true) @Qualifier("concernGroupServiceImpl")
	private ConcernGroupService concernGroupService;

	/** 
	 * Description : 保存关注分组 
	 * Create Date: 2010-11-3下午06:01:11 by guoyr  Modified Date: 2010-11-3下午06:01:11 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveConcernGroup")
	public ModelAndView saveConcernGroup(HttpServletRequest request,ConcernGroup concernGroup) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		concernGroupService.saveConcernGroup(concernGroup);
		model.put(Constants.JSON_RESULT, "添加分组成功");
		model.put("concernGroup", concernGroup);
		return new ModelAndView(Constants.JSON_VIEW,model);	
	}
	
	/** 
	 * Description : 修改分组 
	 * Create Date: 2010-11-8下午04:51:22 by guoyr  Modified Date: 2010-11-8下午04:51:22 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateConcernGroup")
	public ModelAndView updateConcernGroup(HttpServletRequest request,ConcernGroup concernGroup) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		if(concernGroupService.updateConcernGroup(concernGroup)){
			model.put(Constants.JSON_RESULT, "修改分组成功");
		}
		model.put("concernGroup", concernGroup);
		return new ModelAndView(Constants.JSON_VIEW,model);	
	}
}
