package com.gpcsoft.bizplatform.base.evaluate.controller;


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

import com.gpcsoft.bizplatform.base.evaluate.domain.Quota;
import com.gpcsoft.bizplatform.base.evaluate.service.QuotaService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="quotaFormView"
  *  url="view/bizplatform/base/quota/quota_form.jsp"
  */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Quota.class})
@RequestMapping("/QuotaController.do")//页面请求路径,可修改
public class QuotaController extends AnnotationMultiController<Quota> {

	@Autowired(required=true) @Qualifier("quotaServiceImpl")
	private QuotaService quotaService;
	
	/** 
	 * Description :  新增或保存指标
	 * Create Date: 2010-7-21上午10:19:52 by yucy  Modified Date: 2010-7-21上午10:19:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateOrUpdateQuota")   
	public ModelAndView toCreateOrUpdateQuota(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Quota quota = null;
		if(null!=request.getParameter("objId")&&!"".equals(request.getParameter("objId"))){
			quota = quotaService.get(request.getParameter("objId"));
		}else {
			quota = new Quota();
		}
		model.put("quota", quota);
    	ModelAndView mv = new ModelAndView("quotaFormView", model);
    	return mv;
	}
	
	
	/** 
	 * Description :  删除指标项
	 * Create Date: 2010-7-26下午03:26:13 by yucy  Modified Date: 2010-7-26下午03:26:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=delQuota")   
	public ModelAndView delQuota(HttpServletRequest request, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		
		String quotaIds = request.getParameter("quotaIds");
		
		if(null!=quotaIds&&!"".equals(quotaIds)){
			String[] quotaIdArray = quotaIds.split(",");
			int result = quotaService.delQuota(quotaIdArray);		//删除指标项(检查可删性)
			
			if(result>0){
				model.put(Constants.JSON_RESULT, "删除成功!");
			}else{
				model.put(Constants.JSON_RESULT, "删除失败!指标已投入使用");
			}
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
}
