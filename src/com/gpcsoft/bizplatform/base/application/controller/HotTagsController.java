package com.gpcsoft.bizplatform.base.application.controller;

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

import com.gpcsoft.bizplatform.base.application.domain.HotTags;
import com.gpcsoft.bizplatform.base.application.service.HotTagsService;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="hotTagsFormView"
  *  url="view/pubservice/application/hottags/hottags_form.jsp"
  * @gpcsoft.view value="hotTagsTreeFormView"
  *  url="view/pubservice/application/HotTags/hotTagsTreeForm.jsp"
  * @gpcsoft.view value="hotTagsListView"
  *  url="view/pubservice/application/hottags/hottags_list.jsp"
  * @gpcsoft.view value="hotTagsDetailView"
  *  url="view/pubservice/application/HotTags/hotTagsDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={HotTags.class})
@RequestMapping("/HotTagsController.do")//页面请求路径,可修改
public class HotTagsController extends AnnotationMultiController<HotTags> {

	@Autowired(required=true) @Qualifier("hotTagsServiceImpl")
	private HotTagsService hotTagsService;

	/** 
	 * Description :  跳转到新增或修改页面
	 * Create Date: 2010-10-7下午01:26:32 by yucy  Modified Date: 2010-10-7下午01:26:32 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toCreateOrUpDateTags" )
	public ModelAndView toCreateOrUpDateTags(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		if(StringUtils.hasLength(request.getParameter("objId"))){
			model.put("hotTags", hotTagsService.get(request.getParameter("objId")));
		}else{
			model.put("hotTags", new HotTags());
		}
		return new ModelAndView("hotTagsFormView",model);
	}
	
}
