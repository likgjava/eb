package com.gpcsoft.bizplatform.organization.controller;


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

import com.gpcsoft.bizplatform.organization.domain.IllegalRec;
import com.gpcsoft.bizplatform.organization.service.IllegalRecService;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

/**
 * @gpcsoft.view value="illegalRecFormView"
 *  url="view/bizplatform/organization/illegalrecord/illegal_record_form.jsp"
 *  
 * @gpcsoft.view value="illegalRecDetailView"
 *  url="view/bizplatform/organization/illegalrecord/illegal_record_detail.jsp"
 *  
 * @gpcsoft.view value="illegalRecListView"
 *  url="view/bizplatform/organization/illegalrecord/illegal_record_list.jsp"
 *  
 * @gpcsoft.view value="illegalRecOrgListView"
 *  url="view/bizplatform/organization/illegalrecord/illegal_record_org_list.jsp"
 *  
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={IllegalRec.class})
@RequestMapping("/IllegalRecController.do")//页面请求路径,可修改
public class IllegalRecController extends AnnotationMultiController<IllegalRec> {

	@Autowired(required=true) @Qualifier("illegalRecServiceImpl")
	private IllegalRecService illegalRecService;
	
	/** 
	 * Description :  新增或保存违法记录
	 * Create Date: 2011-7-12下午04:53:08 by yucy  Modified Date: 2011-7-12下午04:53:08 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateOrUpdateIllegalRec")   
	public ModelAndView toCreateOrUpdateIllegalRec(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		String objId = request.getParameter("objId");
		model.put("illegalRec", StringUtils.hasLength(objId)?illegalRecService.get(objId):new IllegalRec() );
    	return new ModelAndView("illegalRecFormView", model);
	}
	
	/** 
	 * Description :  查看
	 * Create Date: 2011-7-12下午04:53:08 by yucy  Modified Date: 2011-7-12下午04:53:08 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toViewIllegalRec")   
	public ModelAndView toViewIllegalRec(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		String objId = request.getParameter("objId");
		model.put("illegalRec", illegalRecService.get(objId) );
    	return new ModelAndView("illegalRecDetailView", model);
	}
	
	/** 
	 * Description :  跳转到违法记录机构列表
	 * Create Date: 2011-7-12下午04:53:08 by yucy  Modified Date: 2011-7-12下午04:53:08 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toIllegalRecOrgList")   
	public ModelAndView toIllegalRecOrgList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		model.put("currentOrgId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
    	return new ModelAndView("illegalRecOrgListView", model);
	}
	
	
}
