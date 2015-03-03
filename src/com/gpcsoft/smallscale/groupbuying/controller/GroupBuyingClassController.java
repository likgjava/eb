package com.gpcsoft.smallscale.groupbuying.controller;

import java.util.HashMap;
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

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuyingClass;
import com.gpcsoft.smallscale.groupbuying.service.GroupBuyingClassService;

/**
 * @gpcsoft.view value="groupBuyingClassFormView"
 * url="view/smallscale/groupbuying/group_buying_class_form.jsp"
 */
@Controller
@Scope("request")
@SessionAttributes(types={GroupBuyingClass.class})
@RequestMapping("/GroupBuyingClassController.do")
public class GroupBuyingClassController extends AnnotationMultiController<GroupBuyingClass> {

	@Autowired(required=true) @Qualifier("groupBuyingClassServiceImpl")
	private GroupBuyingClassService groupBuyingClassService;

	/** 
	 * Description :  跳转到新增或修改团购分类页面
	 * Create Date: 2011-6-21上午11:16:04 by likg  Modified Date: 2011-6-21上午11:16:04 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateOrUpdateView")
	public ModelAndView toCreateOrUpdateView(String objId, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取团购分类信息
		GroupBuyingClass groupBuyingClass = null;
		if(StringUtils.hasLength(objId)) {
			groupBuyingClass = groupBuyingClassService.get(objId);
		} else {
			groupBuyingClass = new GroupBuyingClass();
		}
		model.put("groupBuyingClass", groupBuyingClass);
		
		return new ModelAndView("groupBuyingClassFormView", model);
	}
	
	/** 
	 * Description :  保存团购分类信息
	 * Create Date: 2011-6-21下午04:41:07 by likg  Modified Date: 2011-6-21下午04:41:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveGroupBuyingClass")
	public ModelAndView saveGroupBuyingClass(GroupBuyingClass groupBuyingClass, HttpServletRequest request, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//保存团购分类信息
		groupBuyingClassService.saveGroupBuyingClass(groupBuyingClass);
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  修改团购分类的排序序号
	 * Create Date: 2011-5-16下午04:20:32 by likg  Modified Date: 2011-5-16下午04:20:32 by likg
	 * @param   objId:要排序的团购分类的Id	isToUp:排序方向
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=updateSort")
	public ModelAndView updateSort(String objId, boolean isToUp, HttpServletRequest request) throws Exception {
		
		//修改团购分类的排序序号
		groupBuyingClassService.updateSort(objId, isToUp);
		
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
}
